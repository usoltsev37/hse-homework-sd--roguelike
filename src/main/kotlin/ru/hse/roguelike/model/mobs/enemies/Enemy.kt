package ru.hse.roguelike.model.mobs.enemies

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.mobs.Mob
import ru.hse.roguelike.model.mobs.enemies.movement.AggressiveMoveStrategy
import ru.hse.roguelike.model.mobs.enemies.movement.MoveStrategy
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.x
import ru.hse.roguelike.util.y
import kotlin.math.abs
import kotlin.random.Random

/**
 * Enemy that randomly spawns on the Map and attacks Hero when he gets too close.
 */
@Serializable
sealed class Enemy : Mob() {

    override var health: Int = Random.nextInt(10, 20)
    override var strength: Int = Random.nextInt(1, 10)

    val moveStrategy: MoveStrategy
        get() = when (Random.nextInt(3)) {
            0 -> AggressiveMoveStrategy()
            else -> AggressiveMoveStrategy()
        }
    abstract val step: Int
    val DEFAULT_STEP = 1
    /**
     * Enemy movement strategy TODO
     * @param heroPos position of Hero
     */
    fun getNextPosition(heroPos: Position): Position {
        val yDist = abs(position.y - heroPos.y)
        val xDist = abs(position.x - heroPos.x)
        if (yDist == 1 || xDist == 1) {
            return moveStrategy.getNextPosition(position, heroPos, DEFAULT_STEP)
        } else {
            return moveStrategy.getNextPosition(position, heroPos, step)
        }

    }

    override fun attack(other: Mob) {
        other.health -= strength
    }

}