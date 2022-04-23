package ru.hse.roguelike.model.mobs.enemies.factories

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.model.mobs.enemies.PowerfulEnemy
import ru.hse.roguelike.util.Position

/**
 * Factory implementation for PowerfulEnemies
 */
class PowerfulEnemyFactory : EnemyFactory() {
    override fun createEnemy(position: Position): Enemy {
        return PowerfulEnemy(position, getRandomEnemyStrategy())
    }
}
