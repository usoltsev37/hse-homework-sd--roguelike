package ru.hse.roguelike.model.mobs.enemies

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.mobs.Mob
import ru.hse.roguelike.util.Position
import kotlin.random.Random

/**
 * Enemy that randomly spawns on the Map and attacks Hero when he gets too close.
 */
@Serializable
sealed class Enemy(
    override var position: Position,
    override var health: Int = Random.nextInt(10, 20),
    override val strength: Int = Random.nextInt(1, 10)
) : Mob() {

    /**
     * Enemy movement strategy
     * @param heroPos position of Hero
     */
    abstract fun move(heroPos: Position)

    override fun attack(other: Mob) {
        other.health -= strength
    }

}