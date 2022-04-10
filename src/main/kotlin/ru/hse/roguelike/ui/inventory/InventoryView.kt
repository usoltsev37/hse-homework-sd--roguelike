package ru.hse.roguelike.ui.inventory

import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.ui.View
import ru.hse.roguelike.util.Position

/**
 * View of hero stats and inventory.
 */
interface InventoryView : View {
    /**
     * Set given items as hero inventory view.
     * @param items Given items to display
     */
    fun setInventory(items: List<Item>)

    /**
     * Set current position of cursor at the inventory view.
     * @param position Given position at the inventory
     */
    fun setCurrentPosition(position: Position)

    /**
     * Set selected position at the inventory view.
     * It is used to equip and unequip items.
     * @param position Given position at the inventory
     */
    fun setSelectedPosition(position: Position?)

    /**
     * Set hero stats at the inventory view.
     * @param level Hero level
     * @param experience Current amount of experience the hero has
     * @param health Current amount of health points the hero has
     * @param strength Current amount of strength points the hero has
     */
    fun setStats(level: Int, experience: Int, health: Int, strength: Int)
}