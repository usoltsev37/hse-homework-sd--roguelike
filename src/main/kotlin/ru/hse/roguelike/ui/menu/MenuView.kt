package ru.hse.roguelike.ui.menu

import ru.hse.roguelike.ui.View
import ru.hse.roguelike.model.map.Map as GameMap

/**
 * View of start game menu.
 */
interface MenuView : View {
    /**
     * Draw menu in lanterna representation.
     */
    fun draw()

    /**
     * Save game map into file
     */
    fun saveMap(map: GameMap)
}