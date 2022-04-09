package ru.hse.roguelike.model.characters

import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.Position

class Hero(
    override var position: Position,
    override var health: Int = 50,
    override var strength: Int = 10,
    var armor: Int = 0,
    private var selectedItem: Int = 0

) : Movable() {

    /*
    The first 6 elements are worn on the hero (isEquiped = True)
     */
    val inventory: MutableList<Item> = ArrayList(Constants.COUNT_COLUMNS)

    fun selectedItemMoveLeft() {
        if (selectedItem > 1) {
            selectedItem--
        }
    }

    fun selectedItemMoveRight() {
        selectedItem++
    }

    fun selectedItemMoveUp() {
        if (selectedItem > 5) {
            selectedItem -= Constants.COUNT_COLUMNS
        }
    }

    fun selectedItemMoveDown() {
        selectedItem += Constants.COUNT_COLUMNS
    }
}