package ru.hse.roguelike.model.mobs

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.Position
import kotlin.random.Random

@Serializable
class HeroDecorator(private val hero: AbstractHero): AbstractHero() {

    override var health: Int
        get() = hero.health
        set(value) { hero.health = value }

    override var maxHealth: Int
        get() = hero.maxHealth
        set(value) { hero.health = value }

    override var position: Position
        get() = hero.position
        set(value) {hero.position = value}

    override var strength: Int
        get() = hero.strength
        set(value) { hero.strength = value }

    override var armor: Int
        get() = hero.armor
        set(value) {hero.armor = value}

    override var xp: Int
        get() = hero.xp
        set(value) {hero.xp = value}

    override var currMaxXp: Int
        get() = hero.currMaxXp
        set(value) {hero.currMaxXp = value}

    override var level: Int
        get() = hero.level
        set(value) {hero.level = value}

    override val inventory: MutableList<Item> = hero.inventory


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