package ru.hse.roguelike.model.mobs.enemies.factory

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.model.mobs.enemies.TankEnemy
import ru.hse.roguelike.util.Position

class TankEnemyFactory : EnemyFactory {
    override fun createEnemy(position: Position): Enemy {
        return TankEnemy(position)
    }
}