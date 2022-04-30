package ru.hse.roguelike.model.mobs

import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.Position

/**
 * The main character the player will play as. Has an inventory that contains picked up items.
 */
class Hero(position: Position) : AbstractHero(position, Constants.MAX_HEALTH, 10) {

    override fun attack(other: Mob) {
        other.health -= strength
        if (other.isDead) {
            updateXp(other.strength)
        }
    }

}