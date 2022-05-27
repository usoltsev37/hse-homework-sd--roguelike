package ru.hse.roguelike.model.mobs.enemies

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.hse.roguelike.model.mobs.enemies.movement.AggressiveMoveStrategy
import ru.hse.roguelike.util.Position

class SimpleEnemyTest {
    @Test
    fun testInvariant() {
        for (i in 1..5) {
            val enemyTank = TankEnemy(Position(0, 1), AggressiveMoveStrategy())
            val enemySimple = DefaultEnemy(Position(1, 0), AggressiveMoveStrategy())

            Assertions.assertTrue(enemyTank.strength <= enemySimple.strength)
            Assertions.assertTrue(enemyTank.health >= enemySimple.health)
        }
        Assertions.assertTrue(true)
    }
}