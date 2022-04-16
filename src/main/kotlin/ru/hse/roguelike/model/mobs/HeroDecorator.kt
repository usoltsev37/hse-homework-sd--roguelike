package ru.hse.roguelike.model.mobs

import ru.hse.roguelike.util.Position

class HeroDecorator(private val mob: Mob): Mob() {

    override var health: Int = mob.health
    override var position: Position = mob.position
    override val strength: Int = mob.strength


    override fun attack(other: Mob) {
        mob.attack(other)
        //TODO: additional logic
    }


}