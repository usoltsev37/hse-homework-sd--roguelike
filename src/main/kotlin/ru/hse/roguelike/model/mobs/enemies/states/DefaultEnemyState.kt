package ru.hse.roguelike.model.mobs.enemies.states

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.Position

object DefaultEnemyState: EnemyState {

    override fun getNextPosition(heroPos: Position, enemy: Enemy): Position {
        return enemy.moveStrategy.getNextPosition(enemy.position, heroPos, enemy.getStep(heroPos))
    }
}