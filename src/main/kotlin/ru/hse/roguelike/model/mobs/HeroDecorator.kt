package ru.hse.roguelike.model.mobs

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.Position
import kotlin.random.Random

@Serializable
class HeroDecorator(private val hero: AbstractHero): AbstractHero() {

    override var health: Int = hero.health
    override var maxHealth: Int = hero.maxHealth
    override var position: Position = hero.position
    override var strength: Int = hero.strength
    override var armor: Int = hero.armor
    override var xp: Int = hero.xp
    override var level: Int = hero.level
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