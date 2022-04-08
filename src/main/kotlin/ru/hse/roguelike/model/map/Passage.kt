package ru.hse.roguelike.model.map

import ru.hse.roguelike.util.*
import kotlinx.serialization.Serializable

@Serializable
class Passage(val from: Position, val to: Position, val dim: Int) {
    val turnPosition: Position?
        get() {
            if (from.x == to.x || from.y == to.y) {
                return null
            }
            return if (dim == 0) Position(to.x, from.y) else Position(from.x, to.y)
        }
}