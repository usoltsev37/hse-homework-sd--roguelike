package ru.hse.roguelike.model.mobs.enemies.factory

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.model.mobs.enemies.FastEnemy
import ru.hse.roguelike.util.Position

class FastEnemyFactory : EnemyFactory {
    override fun createEnemy(position: Position): Enemy {
        return FastEnemy(position)
    }
}