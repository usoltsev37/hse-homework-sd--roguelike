package ru.hse.roguelike.util

import com.googlecode.lanterna.TerminalPosition
import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.model.map.Cell

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

fun Position.isInCell(cell: Cell): Boolean {
    return cell.leftBottomPos.x <= x && x <= cell.rightTopPos.x &&
            cell.leftBottomPos.y <= y && y <= cell.rightTopPos.y
}

fun findCellByPoint(point: Position?, cells: List<Cell>): Cell? {
    if (point == null) {
        return null
    }
    for (cell in cells) {
        if (point.isInCell(cell)) {
            return cell
        }
    }
    return null
}
