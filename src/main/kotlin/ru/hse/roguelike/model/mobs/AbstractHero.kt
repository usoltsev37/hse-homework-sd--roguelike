package ru.hse.roguelike.model.mobs

import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.model.items.ItemType
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.Constants.LEVEL_UPDATE_HEALTH
import ru.hse.roguelike.util.Constants.LEVEL_UPDATE_STRENGTH
import ru.hse.roguelike.util.Position
import kotlin.math.sqrt

abstract class AbstractHero(position: Position, override var health: Int, strength: Int) :
    Mob(position, strength, "H") {

    var armour: Int = 0
    var xp: Int = 0
    var level: Int = 0
    var maxHealth: Int = Constants.MAX_HEALTH
    var currMaxXp: Int = 2

    /**
    The first 6 elements are worn on the hero (isEquiped = True)
     */
    val inventory: MutableList<Item> = ArrayList(Constants.COUNT_COLUMNS)

    /**
     * Update XP
     * @param strength of the enemy we attack
     */
    protected fun updateXp(strength: Int) {
        xp += calcXp(strength)
        if (xp >= currMaxXp) {
            xp %= currMaxXp
            updateLevel()
        }
    }

    private fun updateLevel() {
        currMaxXp *= 2
        level += 1
        if (level % 2 == 0) {
            strength += LEVEL_UPDATE_STRENGTH
        } else {
            maxHealth += LEVEL_UPDATE_HEALTH
        }
        health = maxHealth
    }

    private fun calcXp(strength: Int): Int {
        return sqrt(strength.toDouble()).toInt()
    }

    fun calcAttackStrength(otherStrength: Int): Int {
        return (1 - (1 / (2.0 * ItemType.MAX_ARMOUR)) * armour).toInt() * otherStrength
    }
}