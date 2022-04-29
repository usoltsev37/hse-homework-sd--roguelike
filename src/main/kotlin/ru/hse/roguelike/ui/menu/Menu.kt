package ru.hse.roguelike.ui.menu

import ru.hse.roguelike.model.map.Map as GameMap

/**
 * Base game menu.
 */
abstract class Menu {
    /**
     * Display base menu.
     */
    abstract fun display()

    /**
     * Display saving game map into file.
     */
    abstract fun saveMap(map: GameMap)
}