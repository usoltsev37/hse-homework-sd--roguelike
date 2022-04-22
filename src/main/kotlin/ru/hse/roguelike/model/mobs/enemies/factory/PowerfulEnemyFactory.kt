package ru.hse.roguelike.model.mobs.enemies.factory

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.model.mobs.enemies.PowerfulEnemy
import ru.hse.roguelike.util.Position

class PowerfulEnemyFactory : EnemyFactory {
    override fun createEnemy(position: Position): Enemy {
        return PowerfulEnemy(position)
    }
}
