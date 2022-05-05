package ru.hse.roguelike.model.mobs.enemies

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.hse.roguelike.model.mobs.Hero
import ru.hse.roguelike.model.mobs.enemies.movement.AggressiveMoveStrategy
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.x
import kotlin.math.abs

class FastEnemyTest {
    @Test
    fun testStep() {
        val enemy = PowerfulEnemy(Position(0 ,1), AggressiveMoveStrategy)
        val hero = Hero(Position(0 ,0))
        hero.attack(enemy)
        enemy.attack(hero)

        for (i in 1..1000) {
            val prevEnemyPos = enemy.getNextPosition(hero.position)
            if (abs(prevEnemyPos.x - enemy.position.x)  > 2) {
                Assertions.assertTrue(false)
            }
        }
        Assertions.assertTrue(true)
    }
}