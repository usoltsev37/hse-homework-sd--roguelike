package ru.hse.roguelike.model.mobs.enemies.factories

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.model.mobs.enemies.TankEnemy
import ru.hse.roguelike.util.Position

/**
 * Factory implementation for TankEnemies
 */
class TankEnemyFactory : EnemyFactory() {
    override fun createEnemy(position: Position): Enemy {
        return TankEnemy(position, getRandomEnemyStrategy())
    }
}
