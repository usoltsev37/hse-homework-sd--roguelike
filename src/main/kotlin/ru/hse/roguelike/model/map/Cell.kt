package ru.hse.roguelike.model.map

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.characters.Enemy
import ru.hse.roguelike.util.FreeItems
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.x
import ru.hse.roguelike.util.y
import kotlin.random.Random

@Serializable
class Cell(
    val leftBottomPos: Position, val rightTopPos: Position, val enemies: List<Enemy>,
    val items: FreeItems, val passages: MutableList<Passage> = ArrayList()
) {

    var visited = false

    val width = rightTopPos.x - leftBottomPos.x + 1
    val height = rightTopPos.y - leftBottomPos.y + 1

    override fun toString(): String {
        return "{$leftBottomPos, $rightTopPos}"
    }

    /*
    * Get Random Position from Cell
    */
    fun getRandomPosition(): Position {
        return Position(Random.nextInt(leftBottomPos.x, rightTopPos.x), Random.nextInt(leftBottomPos.y, rightTopPos.y))
    }

}