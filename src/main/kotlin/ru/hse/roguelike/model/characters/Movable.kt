package ru.hse.roguelike.model.characters

import ru.hse.roguelike.util.Position

abstract class Movable {
    abstract var position: Position
    abstract var health: Int
    abstract val strength: Int

    val isDead: Boolean
        get() = health <= 0

    fun attack(other: Movable) {
        health -= other.strength
    }
}