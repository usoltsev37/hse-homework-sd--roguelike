package ru.hse.roguelike.ui.builder

import ru.hse.roguelike.ui.map.MapView
import ru.hse.roguelike.ui.map.MapViewImpl
import ru.hse.roguelike.ui.window.Window

class MapViewBuilder : ViewBuilder {
    override fun setHero() {
        TODO("Not yet implemented")
    }

    override fun setMap() {
        TODO("Not yet implemented")
    }

    fun getResult(window: Window): MapView {
        return MapViewImpl(window) // TODO("Not yet implemented")
    }
}