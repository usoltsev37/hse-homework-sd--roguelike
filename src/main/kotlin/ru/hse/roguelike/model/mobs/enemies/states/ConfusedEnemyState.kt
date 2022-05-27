package ru.hse.roguelike.model.mobs.enemies.states

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.getClosestRandomPosition

object ConfusedEnemyState: EnemyState {
    override fun getNextPosition(heroPos: Position, enemy: Enemy): Position {
        return enemy.position.getClosestRandomPosition()
    }
}