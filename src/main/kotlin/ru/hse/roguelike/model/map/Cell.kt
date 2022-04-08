package ru.hse.roguelike.model.map

import ru.hse.roguelike.model.characters.Enemy
import ru.hse.roguelike.model.items.Item
import kotlinx.serialization.Serializable
import ru.hse.roguelike.util.*

@Serializable
class Cell(val leftBottomPos: Position, val rightTopPos: Position, val enemies: List<Enemy>,
           val items: FreeItems, val passages: MutableList<Passage> = ArrayList(), var visited: Boolean = false) {

    val width = leftBottomPos.x - rightTopPos.x
    val height = leftBottomPos.y - rightTopPos.y

    override fun toString(): String {
        return "{$leftBottomPos, $rightTopPos}"
    }

}