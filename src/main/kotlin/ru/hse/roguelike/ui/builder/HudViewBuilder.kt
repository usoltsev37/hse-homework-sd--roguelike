package ru.hse.roguelike.ui.builder

import ru.hse.roguelike.ui.hud.HudView
import ru.hse.roguelike.ui.hud.HudViewImpl
import ru.hse.roguelike.ui.window.Window

class HudViewBuilder : ViewBuilder {
    override fun setHero() {
        TODO("Not yet implemented")
    }

    override fun setMap() {
        TODO("Not yet implemented")
    }

    fun getResult(window: Window): HudView {
        return HudViewImpl(window) // TODO("Not yet implemented")
    }
}