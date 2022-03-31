package ru.hse.roguelike.ui.map

import ru.hse.roguelike.ui.View

interface MapView : View {

    fun setCell(/* TODO cell: Cell */)
    fun setHeroPosition(/* TODO position: Position */)
}