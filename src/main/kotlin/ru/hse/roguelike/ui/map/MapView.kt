package ru.hse.roguelike.ui.map

import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.ui.View
import ru.hse.roguelike.util.Position

/**
 * View of game field and hero.
 */
interface MapView : View {

    var heroPos: Position

    /**
     * Set cell on the view with entire information.
     * @param cell Given cell
     */
    fun setCell(cell: Cell)

    /**
     * Set hero on the view.
     * @param position Current hero position
     */
    fun setHeroPosition(position: Position, prevPosition: Position? = null)
}