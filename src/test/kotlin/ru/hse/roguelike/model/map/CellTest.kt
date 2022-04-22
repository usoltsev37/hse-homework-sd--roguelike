package ru.hse.roguelike.model.map

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.isInCell

internal class CellTest {

    @Test
    fun getRandomPosition() {
        val cell = Cell(Position(0, 0), Position(50, 50), emptyList(), emptyList())

        repeat(100) {
            Assertions.assertTrue(cell.getRandomPosition().isInCell(cell))
        }
    }
}