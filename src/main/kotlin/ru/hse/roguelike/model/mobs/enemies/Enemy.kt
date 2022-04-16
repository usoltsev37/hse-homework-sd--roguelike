package ru.hse.roguelike.model.mobs.enemies

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.mobs.Mob
import ru.hse.roguelike.util.Position
import kotlin.random.Random

/**
 * Enemy that randomly spawns on the Map and attacks Hero when he gets too close.
 */
@Serializable
sealed class Enemy : Mob() {

    override var health: Int = Random.nextInt(10, 20)
    override var strength: Int = Random.nextInt(1, 10)

    /**
     * Enemy movement strategy
     * @param heroPos position of Hero
     */
    abstract fun getNextPosition(heroPos: Position): Position

    override fun attack(other: Mob) {
        other.health -= strength
    }

}