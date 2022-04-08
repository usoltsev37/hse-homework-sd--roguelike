package ru.hse.roguelike.ui.inventory

import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.ui.View
import ru.hse.roguelike.util.Position

interface InventoryView : View {

    fun setInventory(items: List<Item>)
    fun setCurrentPosition(position: Position)
    fun setSelectedPosition(position: Position?)
    fun setStats(level: Int, experience: Int, health: Int, strength: Int)
}