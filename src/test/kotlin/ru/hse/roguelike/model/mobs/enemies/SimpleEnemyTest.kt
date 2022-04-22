package ru.hse.roguelike.model.mobs.enemies

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.hse.roguelike.model.mobs.Hero
import ru.hse.roguelike.util.Position

class SimpleEnemyTest {
    @Test
    fun testinvariant() {
        for (i in 1..5) {
            val enemyTank = TankEnemy(Position(0, 1))
            val enemySimple = SimpleEnemy(Position(1, 0))

            Assertions.assertTrue(enemyTank.strength <= enemySimple.strength)
            Assertions.assertTrue(enemyTank.health >= enemySimple.health)
        }
        Assertions.assertTrue(true)
    }
}