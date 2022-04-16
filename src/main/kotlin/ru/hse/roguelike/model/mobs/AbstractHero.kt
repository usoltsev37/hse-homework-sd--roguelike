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
    abstract var maxHealth: Int
    abstract var currMaxXp: Int

    override val name: String = "H"

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
        currMaxXp *= 2
        level += 1
        if (level % 2 == 0) {
            strength += LEVEL_UPDATE_STRENGTH
        }
        maxHealth += LEVEL_UPDATE_HEALTH
        health = maxHealth
    }

    private fun calcXp(strength: Int): Int {
        return sqrt(strength.toDouble()).toInt()
    }
}