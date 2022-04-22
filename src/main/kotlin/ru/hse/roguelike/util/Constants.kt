package ru.hse.roguelike.util

object Constants {
    const val DEFAULT_MAP_WIDTH = 80
    const val DEFAULT_MAP_HEIGHT = 24

    const val MAX_TERMINAL_WIDTH = 240
    const val MAX_TERMINAL_HEIGHT = 80

    const val MIN_RECT_DIM_SIZE = 5

    const val ENEMY_PROB = 50
    const val ITEM_PROB = 50

    const val COUNT_COLUMNS = 6

    const val HUD_WIDTH = 20

    const val CONFUSE_PROB = 30

    const val LEVEL_UPDATE_STRENGTH = 3
    const val LEVEL_UPDATE_HEALTH = 10

    const val MAX_MAP_HEIGHT = MAX_TERMINAL_HEIGHT
    const val MAX_MAP_WIDTH = MAX_TERMINAL_WIDTH - HUD_WIDTH
}
