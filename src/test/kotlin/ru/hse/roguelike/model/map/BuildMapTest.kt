package ru.hse.roguelike.model.map

import ru.hse.roguelike.util.*

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class BuildMapTest {

    @Test
    fun testConnectedCells() {
        val height = 100
        val width  = 100
        val map = Map.createMap().withWidth(width).withHeight(height).build()
        Assertions.assertEquals(width, map.width)
        Assertions.assertEquals(height, map.height)
        Assertions.assertTrue(map.cells.isNotEmpty())

        println(map.cells.size)

        visitConnectedCells(map.cells[0])
        var visitedNum = 0
        map.cells.forEach { if (it.visited) visitedNum += 1 }

        Assertions.assertEquals(map.cells.size, visitedNum)
    }

    @Test
    fun testNoIntersection() {
        val map = Map.createMap().build()

        for (cell1 in map.cells) {
            for (cell2 in map.cells) {
                Assertions.assertFalse(isPointInCell(cell1, cell2.leftBottomPos))
                Assertions.assertFalse(isPointInCell(cell1, cell2.rightTopPos))
                Assertions.assertFalse(isPointInCell(cell1, Position(cell2.leftBottomPos.x, cell2.rightTopPos.y)))
                Assertions.assertFalse(isPointInCell(cell1, Position(cell2.rightTopPos.x, cell2.leftBottomPos.y)))
            }
        }
    }

    private fun isPointInCell(cell: Cell, point: Position): Boolean {
        return cell.leftBottomPos.x < point.x && point.x < cell.rightTopPos.x &&
                cell.leftBottomPos.y < point.y && point.y < cell.rightTopPos.y
    }

    private fun visitConnectedCells(curCell: Cell) {
        curCell.visited = true
        for (path in curCell.passages) {
            if (!path.toCell.visited) {
                visitConnectedCells(path.toCell)
            }
        }
    }
}