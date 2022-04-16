package ru.hse.roguelike.model.mobs

import ru.hse.roguelike.model.items.EquipableItem
import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.Position
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * The main character the player will play as. Has an inventory that contains picked up items.
 */
class Hero(
    override var position: Position,
    override var health: Int = 50,
    override var strength: Int = 10,
    override var armor: Int = 0

) : AbstractHero() {

    /**
    The first 6 elements are worn on the hero (isEquiped = True)
     */
    override val inventory: MutableList<Item> = ArrayList(Constants.COUNT_COLUMNS)

    override fun attack(other: Mob) {
        other.health -= strength
    }

}