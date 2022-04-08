package ru.hse.roguelike.model.map

import ru.hse.roguelike.util.*
import kotlinx.serialization.Serializable

@Serializable
class Passage(val fromCell: Cell, val toCell: Cell, val from: Position, val to: Position, val dim: Int) {

    @Deprecated("different passages for different cells")
    fun getStartPos(cell: Cell): Position {
        if (from.x < cell.rightTopPos.x && from.x > cell.leftBottomPos.x &&
            from.y > cell.leftBottomPos.y && from.y < cell.rightTopPos.y) {
            return from
        }
        if (to.x < cell.rightTopPos.x && to.x > cell.leftBottomPos.x &&
            to.y > cell.leftBottomPos.y && to.y < cell.rightTopPos.y) {
            return to
        }
        throw IllegalArgumentException("Wrong Cell: ${cell.leftBottomPos}, ${cell.rightTopPos}")

    }
}