package ru.hse.roguelike.model.mobs.enemies.states

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.model.mobs.enemies.movement.CowardMoveStrategy
import ru.hse.roguelike.util.Position

object CowardEnemyState: EnemyState {

    override fun getNextPosition(heroPos: Position, enemy: Enemy): Position {
        return CowardMoveStrategy.getNextPosition(enemy.position, heroPos, enemy.getStep(heroPos))
    }
}