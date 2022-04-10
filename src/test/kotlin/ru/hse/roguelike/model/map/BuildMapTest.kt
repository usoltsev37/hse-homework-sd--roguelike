package ru.hse.roguelike.model.map

import ru.hse.roguelike.util.*
import kotlin.io.path.createTempFile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.Ignore


class BuildMapTest {

    @Test
    fun testCellsIntersection() {
        val map = Map.createMap().build()

        for (cell1 in map.cells) {
            for (cell2 in map.cells) {
                if (cell2 != cell1) {
                    val errorMessage = "cell1 = $cell1, cell2 = $cell2"
                    Assertions.assertFalse(cell2.leftBottomPos.isInCell(cell1), errorMessage)
                    Assertions.assertFalse(cell2.rightTopPos.isInCell(cell1), errorMessage)
                    Assertions.assertFalse(
                        Position(cell2.leftBottomPos.x, cell2.rightTopPos.y).isInCell(cell1),
                        errorMessage
                    )
                    Assertions.assertFalse(
                        Position(cell2.rightTopPos.x, cell2.leftBottomPos.y).isInCell(cell1),
                        errorMessage
                    )
                }
            }
        }
    }

    @Ignore
    @Test //TODO: turns
    fun testConnectedCells() {
        val height = 100
        val width  = 100
        val map = Map.createMap().withWidth(width).withHeight(height).build()
        Assertions.assertEquals(width, map.width)
        Assertions.assertEquals(height, map.height)
        Assertions.assertTrue(map.cells.isNotEmpty())

        visitConnectedCells(map.cells[0], map.cells)
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
        val test1 = Passage(Position(2, 3), Position(7, 6), 0, emptyList())
        Assertions.assertEquals(Position(7, 3), test1.turnPosition)

        val test2 = Passage(Position(7, 6), Position(2, 3), 1, emptyList())
        Assertions.assertEquals(Position(7, 3), test2.turnPosition)

        val test3 = Passage(Position(2, 4), Position(2, 5), 0, emptyList())
        Assertions.assertNull(test3.turnPosition)
    }

    @Test
    fun testPassagesEndsAndTurnsPosition() {
        val map = Map.createMap().withWidth(100).withHeight(100).build()

        val allPassages: MutableList<Passage> = ArrayList()
        for (cell in map.cells) {
            allPassages.addAll(cell.passages)
        }

        for (passage in allPassages) {
            Assertions.assertNotNull(findCellByPoint(passage.from, map.cells))
            Assertions.assertNotNull(findCellByPoint(passage.to, map.cells))
            // Assertions.assertNull(findCellByPoint(passage.turnPosition, map.cells)) TODO
        }
    }

    private fun visitConnectedCells(curCell: Cell, cells: List<Cell>) {
        curCell.visited = true
        for (path in curCell.passages) {
            val toCell = findCellByPoint(path.to, cells)!!
            if (!toCell.visited) {
                visitConnectedCells(toCell, cells)
            }
        }
    }

}