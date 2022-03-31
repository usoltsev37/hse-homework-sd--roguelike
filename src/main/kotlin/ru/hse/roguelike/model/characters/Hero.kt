package ru.hse.roguelike.model.characters

import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.util.Position

class Hero(override val position: Position,
           override var health: Int,
           override val strength: Int): Movable() {

    val inventory: List<Item> = ArrayList()
    val fastInventory: List<Item> = ArrayList()
}