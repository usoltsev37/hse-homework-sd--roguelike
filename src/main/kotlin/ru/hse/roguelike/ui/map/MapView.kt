package ru.hse.roguelike.ui.map

import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.ui.View
import ru.hse.roguelike.util.Position

interface MapView : View {

    fun setCell(cell: Cell)
    fun setHeroPosition(position: Position)
}