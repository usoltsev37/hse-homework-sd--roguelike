package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.x
import ru.hse.roguelike.util.y
import kotlin.math.abs

class CowardEnemy(position: Position): Enemy(position) {

    override fun move(heroPos: Position) {
        val yDist = abs(position.y - heroPos.y)
        val xDist = abs(position.x - heroPos.x)

        val direction = if (position.x < heroPos.x || position.y < heroPos.y) -1 else 1

        position = if (xDist <= yDist) Position(position.x + direction, position.y)
                   else Position(position.x, position.y + 1)
    }
}