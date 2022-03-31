package ru.hse.roguelike.model.characters

import ru.hse.roguelike.util.*

abstract class Movable {
    abstract val position: Position
    abstract var health: Int
    abstract val strength: Int


    fun attack(other: Movable) {
        health -= other.strength
        if (health <= 0) {
            health = 0
            // TODO: add death
        }
    }
}