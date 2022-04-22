package ru.hse.roguelike.model.mobs.enemies

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.hse.roguelike.model.mobs.Hero
import ru.hse.roguelike.model.mobs.enemies.movement.CowardMoveStrategy
import ru.hse.roguelike.util.Position
import kotlin.math.max

class PowerfulEnemyTest {
    @Test
    fun testAttack() {
        val enemy = PowerfulEnemy(Position(0 ,1), CowardMoveStrategy())
        val hero = Hero(Position(0 ,0))
        for (i in 1..10) {
            hero.attack(enemy)
            val prevEnemyStrength = enemy.strength
            val prevHeroStrength = hero.strength
            val prevHeroHealth = hero.health
            enemy.attack(hero)
            Assertions.assertEquals(prevEnemyStrength + 5,  enemy.strength)
            Assertions.assertEquals(max(0, prevHeroStrength - 5),  hero.strength)
            Assertions.assertTrue(hero.strength >= 0)
            Assertions.assertEquals(prevHeroHealth - prevEnemyStrength,  hero.health)
        }
    }
}