package ru.hse.roguelike.model.mobs.enemies.movement

import ru.hse.roguelike.util.Position

class PassiveMoveStrategy : MoveStrategy {
    override fun getNextPosition(ourPos: Position, heroPos: Position, step: Int): Position {
        return ourPos
    }
}