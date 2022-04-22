package ru.hse.roguelike.ui.menu

import ru.hse.roguelike.ui.View

/**
 * View of start game menu.
 */
interface MenuView : View {
    /**
     * Draw menu in lanterna representation.
     */
    fun draw()
}