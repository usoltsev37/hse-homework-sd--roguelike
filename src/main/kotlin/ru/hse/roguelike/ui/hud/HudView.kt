package ru.hse.roguelike.ui.hud

import ru.hse.roguelike.model.characters.Hero
import ru.hse.roguelike.ui.View

interface HudView : View {

    fun setStats(hero: Hero)
}