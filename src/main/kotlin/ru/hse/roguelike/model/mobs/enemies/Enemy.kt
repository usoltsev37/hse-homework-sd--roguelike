package ru.hse.roguelike.model.mobs.enemies

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.mobs.Hero
import ru.hse.roguelike.model.mobs.Mob
import ru.hse.roguelike.model.mobs.enemies.movement.MoveStrategy
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.getNearestRandomPosition
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

    abstract val step: Int
    protected val DEFAULT_STEP = 1

    abstract val moveStrategy: MoveStrategy

    /**
     * Enemy movement strategy
     * @param heroPos position of Hero
     */
    fun getNextPosition(heroPos: Position): Position {
        val yDist = abs(position.y - heroPos.y)
        val xDist = abs(position.x - heroPos.x)
        val stepSize = if (yDist == 1 || xDist == 1) DEFAULT_STEP else step

        return if (confused) {
            position.getNearestRandomPosition()
        } else {
            moveStrategy.getNextPosition(position, heroPos, stepSize)
        }
    }

    override fun attack(other: Mob) {
        if (other is Hero) {
            other.health -= other.calcAttackStrength(strength)
        } else {
            other.health -= strength
        }
    }

}