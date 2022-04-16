package ru.hse.roguelike.model.mobs

import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.Position

/**
 * The main character the player will play as. Has an inventory that contains picked up items.
 */
class Hero(
    override var position: Position,
    override var health: Int = 50,
    override var maxHealth: Int = 50,
    override var strength: Int = 10,
    override var armor: Int = 0,
    override var xp: Int = 0,
    override var level: Int = 0

) : AbstractHero() {

    /**
    The first 6 elements are worn on the hero (isEquiped = True)
     */
    override val inventory: MutableList<Item> = ArrayList(Constants.COUNT_COLUMNS)

    override fun attack(other: Mob) {
        other.health -= strength
        if (other.isDead) {
            updateXp(other.strength)
        }
    }

}