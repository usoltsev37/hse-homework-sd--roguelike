package ru.hse.roguelike.model.mobs

import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.Position
import kotlin.random.Random

/**
 * Adds 'confuse enemy' functionality for Hero
 * @param hero decorated hero
 */
class HeroDecorator(private val hero: AbstractHero) : AbstractHero() {

    override var health: Int by hero::health

    override var maxHealth: Int by hero::maxHealth

    override var position: Position by hero::position

    override var strength: Int by hero::strength

    override var armour: Int by hero::armour

    override var xp: Int by hero::xp

    override var currMaxXp: Int by hero::currMaxXp

    override var level: Int by hero::level

    override val inventory: MutableList<Item> by hero::inventory


    override fun attack(other: Mob) {
        hero.attack(other)
        if (Random.nextInt(100) < Constants.CONFUSE_PROB) {
            confuseEnemy(other)
        }
    }

    private fun confuseEnemy(other: Mob) {
        (other as Enemy).confused = true
    }


}