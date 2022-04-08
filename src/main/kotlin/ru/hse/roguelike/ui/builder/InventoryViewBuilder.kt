package ru.hse.roguelike.ui.builder

import ru.hse.roguelike.ui.inventory.InventoryView
import ru.hse.roguelike.ui.inventory.InventoryViewImpl
import ru.hse.roguelike.ui.window.Window

class InventoryViewBuilder : ViewBuilder {
    override fun setHero() {
        TODO("Not yet implemented")
    }

    override fun setMap() {
        TODO("Not yet implemented")
    }

    fun getResult(window: Window): InventoryView {
        return InventoryViewImpl(window) // TODO("Not yet implemented")
    }
}