package ru.hse.roguelike.model.map

import ru.hse.roguelike.util.*
import kotlinx.serialization.Serializable

@Serializable
class Passage(val from: Position, val to: Position, val dim: Int) {

    @Deprecated("different passages for different cells")
    fun getStartPos(cell: Cell): Position {
        val maybeStart1 = from
        val maybeStart2 = to

        if (maybeStart1.x < cell.rightTopPos.x && maybeStart1.x > cell.leftBottomPos.x &&
            maybeStart1.y > cell.leftBottomPos.y && maybeStart1.y < cell.rightTopPos.y) {
            return maybeStart1
        }
        if (maybeStart2.x < cell.rightTopPos.x && maybeStart2.x > cell.leftBottomPos.x &&
            maybeStart2.y > cell.leftBottomPos.y && maybeStart2.y < cell.rightTopPos.y) {
            return maybeStart2
        }
        throw IllegalArgumentException("Wrong Cell: ${cell.leftBottomPos}, ${cell.rightTopPos}")

    }
}