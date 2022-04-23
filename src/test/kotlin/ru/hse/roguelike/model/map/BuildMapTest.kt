package ru.hse.roguelike.model.map

import ru.hse.roguelike.util.*
import kotlin.io.path.createTempFile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.hse.roguelike.model.mobs.enemies.factories.DefaultEnemyFactory


class BuildMapTest {

    @Test
    fun testCellsIntersection() {
        val map = Map.createMap().withEnemyFactory(DefaultEnemyFactory()).build()

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

    @Test
    fun testConnectedCells() {
        val height = 100
        val width  = 100
        val map = Map.createMap().withWidth(width).withHeight(height).withEnemyFactory(DefaultEnemyFactory()).build()
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
        val map = Map.createMap().withHeight(50).withWidth(100).withEnemyFactory(DefaultEnemyFactory()).build()

        for (cell in map.cells) {
            val errorMessage = "cell = $cell"
            Assertions.assertTrue(cell.height > 1, errorMessage)
            Assertions.assertTrue(cell.width > 1, errorMessage)
        }
    }

    @Test
    fun testSerialization() {
        val map = Map.createMap().withEnemyFactory(DefaultEnemyFactory()).build()

        val tmpFile = createTempFile()
        map.save(tmpFile)

        val loadedMap = Map.createMap().loadFrom(tmpFile).build()

        Assertions.assertEquals(map.height, loadedMap.height)
        Assertions.assertEquals(map.width, loadedMap.width)
        Assertions.assertTrue(loadedMap.cells.containsAll(loadedMap.cells))
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