package ru.hse.roguelike.model.characters

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.util.Position

internal class HeroTest {

    @Test
    fun attackEnemy() {
        val fakeCell = Cell(
            Position(0, 0), Position(10, 10),
            emptyList(),
            emptyList()
        )

        val positionsInCell = listOf(
            fakeCell.getRandomPosition(),
            fakeCell.getRandomPosition(),
            fakeCell.getRandomPosition()
        )

        val enemies = positionsInCell.map { Enemy(it) }

        val cell = Cell(
            Position(0, 0), Position(10, 10),
            enemies = positionsInCell.map { Enemy(it) },
            emptyList()
        )

        val hero = Hero(cell.getRandomPosition())
        hero.attackEnemy(cell)
        hero.attackEnemy(cell)

        Assertions.assertNotEquals(enemies.toString(), cell.enemies.toString())
    }
}