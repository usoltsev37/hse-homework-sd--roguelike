package ru.hse.roguelike.ui.hud

import ru.hse.roguelike.model.characters.Hero
import ru.hse.roguelike.ui.View

/**
 * View of heads-up display.
 */
interface HudView : View {

    /**
     * Set hero stats at the inventory view.
     * @param hero Given hero
     */
    fun setStats(hero: Hero)

    /**
     * Set informational message
     * @param message Given message
     */
    fun setMessage(message: String)
}