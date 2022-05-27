package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.model.mobs.Hero
import ru.hse.roguelike.model.mobs.Mob
import ru.hse.roguelike.model.mobs.enemies.movement.MoveStrategy
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.getClosestRandomPosition
import ru.hse.roguelike.util.x
import ru.hse.roguelike.util.y
import kotlin.math.abs

/**
 * Enemy that randomly spawns on the Map and attacks Hero when he gets too close.
 */
abstract class Enemy(
    position: Position,
    health: Int, strength: Int,
    name: String,
    private val moveStrategy: MoveStrategy,
    private val stepSize: Int = 1
) : Mob(position, health, strength, name) {

    /**
     * Enemy movement strategy
     * @param heroPos position of Hero
     */
    fun getNextPosition(heroPos: Position): Position {
        val yDist = abs(position.y - heroPos.y)
        val xDist = abs(position.x - heroPos.x)
        val step = if (yDist < stepSize) {
            yDist
        } else if (xDist < stepSize) {
            xDist
        } else {
            stepSize
        }

        return if (confused) {
            position.getClosestRandomPosition()
        } else {
            moveStrategy.getNextPosition(position, heroPos, step)
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