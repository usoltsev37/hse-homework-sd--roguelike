package ru.hse.roguelike.model.mobs.enemies

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.hse.roguelike.model.mobs.Hero
import ru.hse.roguelike.model.mobs.enemies.movement.AggressiveMoveStrategy
import ru.hse.roguelike.util.Position

class TankEnemyTest {
    @Test
    fun testAttack() {
        val enemy = TankEnemy(Position(0 ,1), AggressiveMoveStrategy())
        val hero = Hero(Position(0 ,0))

        for (i in 1..5) {
            hero.attack(enemy)
            val prevHeroHealth = hero.health
            enemy.attack(hero)
            Assertions.assertEquals(prevHeroHealth - 1,  hero.health)
        }
        Assertions.assertTrue(true)
    }
}