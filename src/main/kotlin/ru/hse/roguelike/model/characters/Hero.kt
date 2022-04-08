package ru.hse.roguelike.model.characters

import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.util.Position

class Hero(
    override var position: Position,
    override var health: Int = 50,
    override var strength: Int = 10,
    var armor: Int = 0
) : Movable() {

    val inventory: MutableList<Item> = ArrayList()
}