package ru.hse.roguelike.model.characters

import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.Position

/**
 * The main character the player will play as. Has an inventory that contains picked up items.
 */
class Hero(
    override var position: Position,
    override var health: Int = 50,
    override var strength: Int = 10,
    var armor: Int = 0

) : Movable() {

    /*
    The first 6 elements are worn on the hero (isEquiped = True)
     */
    val inventory: MutableList<Item> = ArrayList(Constants.COUNT_COLUMNS)

}