package ru.hse.roguelike.model.mobs

import ru.hse.roguelike.util.Position

/**
 * Base class for all characters who can move on the Map
 * @see Hero
 * @see Enemy
 */
abstract class Mob {
    abstract var position: Position
    abstract var health: Int
    abstract val strength: Int

    var confused: Boolean = false

    val isDead: Boolean
        get() = health <= 0

    /**
     * Attack other mob
     * @param other who will be attacked
     */
    abstract fun attack(other: Mob)
}