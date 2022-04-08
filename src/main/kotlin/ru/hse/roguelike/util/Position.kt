package ru.hse.roguelike.util

import com.googlecode.lanterna.TerminalPosition
import ru.hse.roguelike.model.items.Item

typealias Position = Pair<Int, Int>
typealias FreeItems = List<Pair<Item, Position>>

val Position.x: Int
    get() = first

val Position.y: Int
    get() = second

operator fun Position.get(ind: Int): Int {
    if (ind == 0) {
        return first
    }
    if (ind == 1) {
        return second
    }
    throw IllegalArgumentException("Invalid index $ind")
}

fun Position.toLanternaTerminalPosition(): TerminalPosition {
    return TerminalPosition(x, y)
}
