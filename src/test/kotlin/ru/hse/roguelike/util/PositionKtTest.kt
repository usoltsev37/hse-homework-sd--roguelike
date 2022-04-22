package ru.hse.roguelike.util

import com.googlecode.lanterna.TerminalPosition
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.hse.roguelike.model.map.Cell


internal class PositionKtTest {

    companion object {
        private val position = Position(30, 28)
    }

    @Test
    fun getX() = Assertions.assertEquals(position.first, position.x)

    @Test
    fun getY() = Assertions.assertEquals(position.second, position.y)


    @Test
    fun get() {
        Assertions.assertEquals(position[0], position.first)
        Assertions.assertEquals(position[1], position.second)
        Assertions.assertThrows(java.lang.IllegalArgumentException::class.java) { position[2] }
    }

    @Test
    fun toLanternaTerminalPosition() {
        val terminalPosition = TerminalPosition(position.first, position.second)
        Assertions.assertEquals(terminalPosition, position.toLanternaTerminalPosition())
    }

    @Test
    fun isInCell() {
        val cell1 = Cell(Position(0, 0), Position(50, 50), emptyList(), emptyList())
        Assertions.assertTrue(position.isInCell(cell1))
        val cell2 = Cell(Position(30, 30), Position(50, 50), emptyList(), emptyList())
        Assertions.assertFalse(position.isInCell(cell2))
        val cell3 = Cell(position, position, emptyList(), emptyList())
        Assertions.assertTrue(position.isInCell(cell3))
    }

    @Test
    fun findCellByPoint() {
        val cells1 = listOf(
            Cell(Position(0, 0), Position(10, 10), emptyList(), emptyList()),
            Cell(Position(20, 25), Position(40, 35), emptyList(), emptyList())
        )
        Assertions.assertEquals(findCellByPoint(position, cells1), cells1[1])
        Assertions.assertEquals(findCellByPoint(Position(5, 4), cells1), cells1[0])
        Assertions.assertNull(findCellByPoint(Position(-1, -1), cells1))
    }

    @Test
    fun plus() {
        val otherPosition = Position(12, 48)
        Assertions.assertEquals(
            Position(position.first + otherPosition.first, position.second + otherPosition.second),
            position + otherPosition
        )
    }

    @Test
    fun upper() {
        val expected = Position(position.first, position.second - 1)
        Assertions.assertEquals(position.upper(), expected)
    }

    @Test
    fun lower() {
        val expected = Position(position.first, position.second + 1)
        Assertions.assertEquals(position.lower(), expected)
    }

    @Test
    fun left() {
        val expected = Position(position.first - 1, position.second)
        Assertions.assertEquals(position.left(), expected)
    }

    @Test
    fun right() {
        val expected = Position(position.first + 1, position.second)
        Assertions.assertEquals(position.right(), expected)
    }
}