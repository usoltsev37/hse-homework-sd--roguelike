package ru.hse.roguelike.model.mobs.enemies.factory

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.model.mobs.enemies.SimpleEnemy
import ru.hse.roguelike.util.Position

/**
 * Factory implementation for SimpleEnemies
 */
class SimpleEnemyFactory : EnemyFactory {
    override fun createEnemy(position: Position): Enemy {
        return SimpleEnemy(position)
    }
}