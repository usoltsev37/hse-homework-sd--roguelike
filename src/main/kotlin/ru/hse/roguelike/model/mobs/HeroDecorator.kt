package ru.hse.roguelike.model.mobs

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.Position
import kotlin.random.Random

class HeroDecorator(private val mob: Mob): Mob() {

    override var health: Int = mob.health
    override var position: Position = mob.position
    override val strength: Int = mob.strength


    override fun attack(other: Mob) {
        mob.attack(other)
        if (Random.nextInt(100) < Constants.CONFUSE_PROB) {
            confuseEnemy(other)
        }
    }

    private fun confuseEnemy(other: Mob) {
        (other as Enemy).confused = true
    }


}