package ru.hse.roguelike.ui.inventory

import ru.hse.roguelike.ui.View

interface InventoryView : View {

    fun setInventory(/* TODO inventory: List<Item>*/)
    fun setFastInventory(/* TODO fastInventory: List<Item>*/)
    fun setCurrentPosition(/* TODO position: Position */)
    fun setSelectedPosition(/* TODO position: Position */)
    fun setStats(/* TODO some stats */)
}