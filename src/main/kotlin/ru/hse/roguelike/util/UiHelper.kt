package ru.hse.roguelike.util

import ru.hse.roguelike.model.map.Passage
import kotlin.math.min
import kotlin.math.max

enum class TextType {
    USUAL,
    CURRENT,
    SELECTED
}

fun Passage.getRoute(): List<Position> {
    val route = mutableListOf<Position>()

    if (turnPosition == null) {
        if (from.x == to.x) {
            for (y in min(from.y, to.y)..max(from.y, to.y))
                route.add(Position(from.x, y))
        } else {
            require(from.y == to.y)
            for (x in min(from.x, to.x)..max(from.x, to.x))
                route.add(Position(x, from.y))
        }
    } else {
        if (from.x == turnPosition!!.x) {
            for (y in min(from.y, turnPosition!!.y)..max(from.y, turnPosition!!.y))
                route.add(Position(from.x, y))
        } else {
            require(from.y == turnPosition!!.y)
            for (x in min(from.x, turnPosition!!.x)..max(from.x, turnPosition!!.x))
                route.add(Position(x, from.y))
        }

        if (turnPosition?.x == to.x) {
            for (y in min(turnPosition!!.y, to.y)..max(turnPosition!!.y, to.y))
                route.add(Position(turnPosition!!.x, y))
        } else {
            require(turnPosition!!.y == to.y)
            for (x in min(turnPosition!!.x, to.x)..max(turnPosition!!.x, to.x))
                route.add(Position(x, turnPosition!!.y))
        }
    }

    return route
}