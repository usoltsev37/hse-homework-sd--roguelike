package ru.hse.roguelike.model.mobs.enemies.movement

import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.x
import ru.hse.roguelike.util.y
import kotlin.math.abs

class AggressiveMoveStrategy : MoveStrategy {
    override fun getNextPosition(ourPos: Position, heroPos: Position, step: Int): Position {
        val yDist = abs(ourPos.y - heroPos.y)
        val xDist = abs(ourPos.x - heroPos.x)

        val direction = if (ourPos.x < heroPos.x || ourPos.y < heroPos.y) 1 else -1

        return if (xDist <= yDist) Position(ourPos.x + direction * step, ourPos.y)
            else Position(ourPos.x, ourPos.y + direction * step)
    }
}
