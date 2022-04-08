package ru.hse.roguelike.model.map

import ru.hse.roguelike.util.*
import kotlin.io.path.createTempFile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class BuildMapTest {

    @Test
    fun testCellsIntersection() {
        val map = Map.createMap().build()

        for (cell1 in map.cells) {
            for (cell2 in map.cells) {
                if (cell2 != cell1) {
                    val errorMessage = "cell1 = $cell1, cell2 = $cell2"
                    Assertions.assertFalse(isPointInCell(cell1, cell2.leftBottomPos), errorMessage)
                    Assertions.assertFalse(isPointInCell(cell1, cell2.rightTopPos), errorMessage)
                    Assertions.assertFalse(
                        isPointInCell(cell1, Position(cell2.leftBottomPos.x, cell2.rightTopPos.y)),
                        errorMessage
                    )
                    Assertions.assertFalse(
                        isPointInCell(cell1, Position(cell2.rightTopPos.x, cell2.leftBottomPos.y)),
                        errorMessage
                    )
                }
            }
        }
    }

    @Test
    fun testConnectedCells() {
        val height = 100
        val width  = 100
        val map = Map.createMap().withWidth(width).withHeight(height).build()
        Assertions.assertEquals(width, map.width)
        Assertions.assertEquals(height, map.height)
        Assertions.assertTrue(map.cells.isNotEmpty())

        visitConnectedCells(map.cells[0], map)
        var visitedNum = 0
        map.cells.forEach { if (it.visited) visitedNum += 1 }

        Assertions.assertEquals(map.cells.size, visitedNum)
    }

    @Test
    fun testCellsSizes() {
        val map = Map.createMap().withHeight(50).withWidth(100).build()

        for (cell in map.cells) {
            val errorMessage = "cell = $cell"
            Assertions.assertTrue(cell.height > 1, errorMessage)
            Assertions.assertTrue(cell.width > 1, errorMessage)
        }
    }

    @Test
    fun testSerialization() {
        val map = Map.createMap().build()

        val tmpFile = createTempFile()
        map.save(tmpFile)

        val loadedMap = Map.createMap().loadFrom(tmpFile).build()

        Assertions.assertEquals(map.height, loadedMap.height)
        Assertions.assertEquals(map.width, loadedMap.width)
        Assertions.assertTrue(loadedMap.cells.containsAll(loadedMap.cells))
    }

    @Test
    fun testPassageTurnPosition() {
        val test1 = Passage(Position(2, 3), Position(7, 6), 0)
        Assertions.assertEquals(Position(7, 3), test1.turnPosition)

        val test2 = Passage(Position(7, 6), Position(2, 3), 1)
        Assertions.assertEquals(Position(7, 3), test2.turnPosition)

        val test3 = Passage(Position(2, 4), Position(2, 5), 0)
        Assertions.assertNull(test3.turnPosition)
    }

    private fun visitConnectedCells(curCell: Cell, map: Map) {
        curCell.visited = true
        for (path in curCell.passages) {
            val toCell = findCellByPoint(path.to, map)
            if (!toCell.visited) {
                visitConnectedCells(toCell, map)
            }
        }
    }

    private fun isPointInCell(cell: Cell, point: Position): Boolean {
        return cell.leftBottomPos.x <= point.x && point.x <= cell.rightTopPos.x &&
                cell.leftBottomPos.y <= point.y && point.y <= cell.rightTopPos.y
    }

    private fun findCellByPoint(point: Position, map: Map): Cell {
        for (cell in map.cells) {
            if (isPointInCell(cell, point)) {
                return cell
            }
        }
        throw IllegalArgumentException("point = $point")
    }
}