package ru.hse.roguelike.model.map

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.FreeItems
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.x
import ru.hse.roguelike.util.y

/**
 * Represents a cell on the field where Hero can move. The cage also contains Enemies and Items.
 * @see Passage
 * @see Map
 */
@Serializable
class Cell(
    val leftBottomPos: Position,
    val rightTopPos: Position,
    @Transient val enemies: MutableList<Enemy> = mutableListOf(),
    @Transient val items: FreeItems = mutableListOf(),
    @Required val passages: MutableList<Passage> = ArrayList()
) {

    var visited = false

    val width = rightTopPos.x - leftBottomPos.x + 1
    val height = rightTopPos.y - leftBottomPos.y + 1

    override fun toString(): String {
        return "{$leftBottomPos, $rightTopPos}"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Cell) {
            return false
        }
        return leftBottomPos == other.leftBottomPos &&
                rightTopPos == other.rightTopPos &&
                passages == other.passages
    }

}