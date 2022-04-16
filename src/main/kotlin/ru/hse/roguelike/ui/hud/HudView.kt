package ru.hse.roguelike.ui.hud

import ru.hse.roguelike.model.mobs.AbstractHero
import ru.hse.roguelike.model.mobs.Hero
import ru.hse.roguelike.ui.View

/**
 * View of heads-up display.
 */
interface HudView : View {

    /**
     * Set hero stats at the inventory view.
     * @param hero Given hero
     */
    fun setStats(hero: AbstractHero)
}