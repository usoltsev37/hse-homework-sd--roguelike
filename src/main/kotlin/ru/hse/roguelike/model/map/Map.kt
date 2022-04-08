package ru.hse.roguelike.model.map

import java.nio.file.Path
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import ru.hse.roguelike.model.characters.Enemy
import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.*
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
                return listOf(generateCell(leftBottom, rightTop))
            }

            val splitValue = Random.nextInt(leftBound, rightBound)

            val firstCells = if (dim == 0) {
                doGenerateCells(leftBottom, Position(splitValue, rightTop.y))
            } else {
                doGenerateCells(leftBottom, Position(rightTop.x, splitValue))
            }

            val secondCells = if (dim == 0) {
                doGenerateCells(Position(splitValue, leftBottom.y), rightTop)
            } else {
                doGenerateCells(Position(leftBottom.x, splitValue), rightTop)
            }

            generatePaths(firstCells, secondCells, dim)

            return firstCells + secondCells
        }

        private fun generateCell(leftBottom: Position, rightTop: Position): Cell {
            val centre = Position((rightTop.x + leftBottom.x) / 2, (rightTop.y + leftBottom.y) / 2)
            val left = Position(Random.nextInt(leftBottom.x, centre.x), Random.nextInt(leftBottom.y, centre.y))
            val right = Position(Random.nextInt(centre.x, rightTop.x), Random.nextInt(centre.y, rightTop.y))

            //TODO: more enemies and items
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
            // TODO:
            for (firstCell in firstCells) {
                for (secondCell in secondCells) {
                    if (dim == 0) {
                    } else {
                    }
                }
            }
        }

    }

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
