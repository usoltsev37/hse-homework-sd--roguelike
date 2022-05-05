package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.model.mobs.Hero
import ru.hse.roguelike.model.mobs.Mob
import ru.hse.roguelike.model.mobs.enemies.movement.MoveStrategy
import ru.hse.roguelike.model.mobs.enemies.states.ConfusedEnemyState
import ru.hse.roguelike.model.mobs.enemies.states.CowardEnemyState
import ru.hse.roguelike.model.mobs.enemies.states.DefaultEnemyState
import ru.hse.roguelike.model.mobs.enemies.states.EnemyState
import ru.hse.roguelike.util.*
import kotlin.math.abs

/**
 * Enemy that randomly spawns on the Map and attacks Hero when he gets too close.
 */
abstract class Enemy(
    position: Position,
    initHealth: Int, strength: Int,
    name: String,
    internal val moveStrategy: MoveStrategy,
    private val stepSize: Int = 1
) : Mob(position, strength, name) {

    private var enemyState: EnemyState = DefaultEnemyState
    private val stateHealthThreshold: Int = (initHealth * Constants.ENEMY_STATE_HEALTH_THRESHOLD).toInt()

    var confused = false
        set(value) {
            field = value
            enemyState = if (value) ConfusedEnemyState else DefaultEnemyState
        }

    override var health: Int = initHealth
        set(value) {
            field = value
            enemyState = if (field <= stateHealthThreshold) CowardEnemyState else DefaultEnemyState
        }

    /**
     * Enemy movement strategy
     * @param heroPos position of Hero
     */
    fun getNextPosition(heroPos: Position): Position {
        return enemyState.getNextPosition(heroPos, this)
    }

    override fun attack(other: Mob) {
        if (other is Hero) {
            other.health -= other.calcAttackStrength(strength)
        } else {
            other.health -= strength
        }
    }

    internal fun getStep(heroPos: Position): Int {
        val yDist = abs(position.y - heroPos.y)
        val xDist = abs(position.x - heroPos.x)
        return if (yDist < stepSize) {
            yDist
        } else if (xDist < stepSize) {
            xDist
        } else {
            stepSize
        }
    }

}