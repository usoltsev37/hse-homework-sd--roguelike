package ru.hse.roguelike.model.map

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.hse.roguelike.model.characters.Enemy
import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.util.*
import java.io.IOException
import java.lang.Integer.max
import java.lang.Integer.min
import java.nio.file.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText
import kotlin.random.Random

@Serializable
class Map private constructor(val width: Int, val height: Int, val cells: List<Cell>) {

    interface Builder {
        fun withWidth(width: Int): Builder
        fun withHeight(height: Int): Builder
        fun loadFrom(path: Path): Builder
        fun build(): Map
    }

    private class BuilderImpl: Builder {
        private var width: Int = Constants.DEFAULT_MAP_WIDTH
        private var height: Int = Constants.DEFAULT_MAP_HEIGHT
        private var path: Path? = null

        override fun withWidth(width: Int): Builder = apply { this.width = width }
        override fun withHeight(height: Int): Builder = apply { this.height = height }
        override fun loadFrom(path: Path): Builder = apply { this.path = path }

        override fun build(): Map {
            if (path != null) {
                val jsonString = path!!.readText()
                return Json.decodeFromString(jsonString)
            }
            return Map(width, height, doGenerateCells(Position(0, 0), Position(width, height)))
        }

        private fun doGenerateCells(leftBottom: Position, rightTop: Position): List<Cell> {

            val dim = Random.nextInt(2)

            val leftBound = leftBottom[dim] + Constants.MIN_RECT_DIM_SIZE
            val rightBound = rightTop[dim] - Constants.MIN_RECT_DIM_SIZE

            if (leftBound >= rightBound) {
                return if (rightTop.x - leftBottom.x > Constants.MIN_RECT_DIM_SIZE &&
                           rightTop.y - leftBottom.y > Constants.MIN_RECT_DIM_SIZE) listOf(generateCell(leftBottom, rightTop))
                       else emptyList()
            }

            val splitValue = Random.nextInt(leftBound, rightBound)

            val firstCells = if (dim == 0) {
                doGenerateCells(leftBottom, Position(splitValue, rightTop.y))
            } else {
                doGenerateCells(Position(leftBottom.x, splitValue), rightTop)
            }

            val secondCells = if (dim == 0) {
                doGenerateCells(Position(splitValue, leftBottom.y), rightTop)
            } else {
                doGenerateCells(leftBottom, Position(rightTop.x, splitValue))
            }

            generatePaths(firstCells, secondCells, dim)

            return firstCells + secondCells
        }

        private fun generateCell(leftBottom: Position, rightTop: Position): Cell {
            val centre = Position((rightTop.x + leftBottom.x) / 2, (rightTop.y + leftBottom.y) / 2)
            val left = Position(Random.nextInt(leftBottom.x + 1, centre.x), Random.nextInt(leftBottom.y + 1, centre.y))
            val right = Position(Random.nextInt(centre.x + 1, rightTop.x), Random.nextInt(centre.y + 1, rightTop.y))

            val enemies = ArrayList<Enemy>()
            if (Random.nextInt(100) < Constants.ENEMY_PROB) {
                enemies.add(
                    Enemy(Position(Random.nextInt(left.x, right.x), Random.nextInt(left.y, right.y)),
                        Constants.BASE_HEALTH, Constants.BASE_STRENGTH)
                )
            }
            val items = ArrayList<Pair<Item, Position>>()
            if (Random.nextInt(100) < Constants.ITEM_PROB) {
                items.add(
                    Pair(Item.getRandomItem(),
                        Position(Random.nextInt(left.x, right.x), Random.nextInt(left.y, right.y))
                    )
                )
            }
            return Cell(left, right, enemies, items)
        }

        private fun generatePaths(firstCells: List<Cell>, secondCells: List<Cell>, dim: Int) {
            val otherDim = dim xor 1
            val allCells = firstCells + secondCells
            for (firstCell in firstCells) {
                for (secondCell in secondCells) {
                    if (firstCell.rightTopPos[otherDim] > secondCell.rightTopPos[otherDim] &&
                            secondCell.rightTopPos[otherDim] > firstCell.leftBottomPos[otherDim] ||
                            secondCell.leftBottomPos[otherDim] < firstCell.rightTopPos[otherDim] &&
                            secondCell.leftBottomPos[otherDim] > firstCell.leftBottomPos[otherDim]){

                        val bottomBorder = max(firstCell.leftBottomPos[otherDim], secondCell.leftBottomPos[otherDim])
                        val topBorder = min(firstCell.rightTopPos[otherDim], secondCell.rightTopPos[otherDim])

                        check(bottomBorder < topBorder) {
                            "bottomBorder = $bottomBorder, topBorder = $topBorder, firstCell = $firstCell, secondCell = $secondCell"
                        }

                        val axisValue = Random.nextInt(bottomBorder, topBorder + 1)
                        var fromPos = if (dim == 0) Position(firstCell.rightTopPos.x, axisValue)
                                      else Position(axisValue, firstCell.leftBottomPos.y)
                        val toPos = if (dim == 0) Position(secondCell.leftBottomPos.x, axisValue)
                                    else Position(axisValue, secondCell.rightTopPos.y)

                        var route = ArrayList<Position>()
                        var curPos = fromPos
                        var lastCell = firstCell
                        while (curPos != toPos) {
                            route.add(curPos)
                            val newCell = findCellByPoint(curPos, allCells)
                            if (newCell != null) {
                                lastCell.passages.add(Passage(fromPos, curPos, dim, route))
                                newCell.passages.add(Passage(curPos, fromPos, dim, route.reversed()))
                                fromPos = if (dim == 0) Position(newCell.rightTopPos.x, fromPos.y)
                                          else Position(fromPos.x, newCell.leftBottomPos.y)
                                lastCell = newCell
                                route = ArrayList()
                                curPos = fromPos
                                route.add(curPos)
                            }
                            curPos = if (dim == 0) Position(curPos.x + 1, curPos.y) else Position(curPos.x, curPos.y - 1)
                        }


                        lastCell.passages.add(Passage(fromPos, toPos, dim, route))
                        secondCell.passages.add(Passage(toPos, fromPos, dim, route.reversed()))
                        return
                    }
                }
            }

            if (firstCells.isNotEmpty() && secondCells.isNotEmpty()) {
                // generateTurnForPath(firstCells, secondCells, dim) TODO
            }
        }

        private fun generateTurnForPath(firstCells: List<Cell>, secondCells: List<Cell>, dim: Int) {
            val otherDim = dim xor 1
            val nearestCell = secondCells.withIndex()
                .minByOrNull { firstCells[0].rightTopPos[dim] - it.value.leftBottomPos[dim] }!!.value
            val cell = firstCells.withIndex()
                .minByOrNull { it.value.rightTopPos[dim] - nearestCell.leftBottomPos[dim] }!!.value

            val from = if (dim == 0) Position(cell.rightTopPos.x, Random.nextInt(cell.leftBottomPos.y, cell.rightTopPos.y))
                       else Position(Random.nextInt(cell.leftBottomPos.x, cell.rightTopPos.x), cell.leftBottomPos.y)
            val axisValue = if (nearestCell.rightTopPos[otherDim] < cell.leftBottomPos[otherDim]) nearestCell.rightTopPos[otherDim]
                            else nearestCell.leftBottomPos[otherDim]
            val to = if (dim == 0) Position(Random.nextInt(nearestCell.leftBottomPos.x, nearestCell.rightTopPos.x), axisValue)
                     else Position(axisValue, Random.nextInt(nearestCell.leftBottomPos.y, nearestCell.rightTopPos.y))

            cell.passages.add(Passage(from, to, dim, emptyList()))
            nearestCell.passages.add(Passage(to, from, otherDim, emptyList()))
        }

    }

    @Throws(IOException::class)
    fun save(path: Path) {
        val jsonString = Json.encodeToString(this)
        path.writeText(jsonString)
    }

    companion object {
        fun createMap(): Builder {
            return BuilderImpl()
        }
    }
}
