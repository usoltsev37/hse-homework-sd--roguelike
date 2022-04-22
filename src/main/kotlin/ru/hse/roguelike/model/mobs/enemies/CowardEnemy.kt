package ru.hse.roguelike.model.mobs.enemies

import kotlinx.serialization.Serializable
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.x
import ru.hse.roguelike.util.y
import kotlin.math.abs

@Serializable
class CowardEnemy(override var position: Position): Enemy() {

    override val name: String = "Z"
    override fun getNextPosition(heroPos: Position): Position {
        val yDist = abs(position.y - heroPos.y)
        val xDist = abs(position.x - heroPos.x)

        val direction = if (position.x < heroPos.x || position.y < heroPos.y) -1 else 1

        return if (xDist <= yDist) Position(position.x + direction, position.y)
                   else Position(position.x, position.y + 1)
    }
}