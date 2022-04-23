package ru.hse.roguelike.model.mobs.enemies.factories

import ru.hse.roguelike.model.mobs.enemies.DefaultEnemy
import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.Position

/**
 * Factory implementation for DefaultEnemies
 */
class DefaultEnemyFactory : EnemyFactory() {
    override fun createEnemy(position: Position): Enemy {
        return DefaultEnemy(position, getRandomEnemyStrategy())
    }
}