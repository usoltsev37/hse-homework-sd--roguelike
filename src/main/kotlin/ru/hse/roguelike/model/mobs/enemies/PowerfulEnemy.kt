package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.model.mobs.Mob
import ru.hse.roguelike.util.Position

class PowerfulEnemy(override var position: Position) : Enemy() {
    override val name: String = "P"
    override val step: Int = DEFAULT_STEP

    override fun attack(other: Mob) {
        other.health -= strength
        strength += 5
        other.strength -= 5
    }
}
