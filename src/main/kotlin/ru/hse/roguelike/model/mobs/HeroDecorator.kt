package ru.hse.roguelike.model.mobs

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.Constants
import kotlin.random.Random

/**
 * Adds 'confuse enemy' functionality for Hero
 * @param hero decorated hero
 */
class HeroDecorator(private val hero: AbstractHero) : AbstractHero(hero.position, hero.health, hero.strength) {

    override fun attack(other: Mob) {
        hero.attack(other)
        if (Random.nextInt(100) < Constants.CONFUSE_PROB) {
            confuseEnemy(other)
        }
        updateStats()
    }

    private fun updateStats() {
        health = hero.health
        maxHealth = hero.maxHealth
        strength = hero.strength
        xp = hero.xp
        currMaxXp = hero.currMaxXp
        armour = hero.armour
        level = hero.level
    }

    private fun confuseEnemy(other: Mob) {
        (other as Enemy).confused = true
    }


}