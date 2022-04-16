package ru.hse.roguelike.model.mobs

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.util.Constants.LEVEL_UPDATE_HEALTH
import ru.hse.roguelike.util.Constants.LEVEL_UPDATE_STRENGTH
import kotlin.math.sqrt

@Serializable
abstract class AbstractHero: Mob() {

    abstract val inventory: MutableList<Item>
    abstract var armor: Int
    abstract var xp: Int
    abstract var level: Int
    private val valueToUpdateXp: Int = 3
    private var currMaxXp: Int = valueToUpdateXp
    abstract var maxHealth: Int

    /**
     * Update XP
     * @param strength of the enemy we attack
     */
    protected fun updateXp(strength: Int) {
        xp += calcXp(strength)
        if (xp > currMaxXp) {
            xp %= currMaxXp
            updateLevel()
        }
    }

    private fun updateLevel() {
        currMaxXp *= valueToUpdateXp
        level += 1
        if (level % 2 == 0) {
            strength += LEVEL_UPDATE_STRENGTH
        } else {
            health += LEVEL_UPDATE_HEALTH
            maxHealth += LEVEL_UPDATE_HEALTH
        }
    }

    private fun calcXp(strength: Int): Int {
        return sqrt(strength.toDouble()).toInt()
    }
}