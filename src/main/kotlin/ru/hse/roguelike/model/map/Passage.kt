package ru.hse.roguelike.model.map

import kotlinx.serialization.Serializable
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.x
import ru.hse.roguelike.util.y

/**
 * Represents passages between Cells on Map. Can contain no more than one turn.
 * @see Map
 * @see Cell
 */
@Serializable
class Passage(val from: Position, val to: Position, val dim: Int, val route: List<Position>) {

    var visited = false

    val turnPosition: Position?
        get() {
            if (from.x == to.x || from.y == to.y) {
                return null
            }
            return if (dim == 0) Position(to.x, from.y) else Position(from.x, to.y)
        }
}