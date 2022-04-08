package ru.hse.roguelike.model.map

import ru.hse.roguelike.model.characters.Enemy
import ru.hse.roguelike.model.items.Item
import kotlinx.serialization.Serializable
import ru.hse.roguelike.util.*

@Serializable
class Cell(val leftBottomPos: Position, val rightTopPos: Position, val enemies: List<Enemy>,
           val items: FreeItems, val passages: MutableList<Passage> = ArrayList(), var visited: Boolean = false) {

    val width = rightTopPos.x - leftBottomPos.x
    val height = rightTopPos.y - leftBottomPos.y

    override fun toString(): String {
        return "{$leftBottomPos, $rightTopPos}"
    }

}