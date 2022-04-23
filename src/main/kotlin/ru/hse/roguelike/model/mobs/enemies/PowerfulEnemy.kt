package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.model.mobs.Mob
import ru.hse.roguelike.model.mobs.enemies.movement.MoveStrategy
import ru.hse.roguelike.util.Position
import kotlin.math.max

/**
 * Enemy implementation for strong creates
 */
class PowerfulEnemy(override var position: Position, override val moveStrategy: MoveStrategy) : Enemy() {
    override val name: String = "P"
    override val step: Int = DEFAULT_STEP

    override fun attack(other: Mob) {
        other.health -= strength
        strength += 5
        other.strength = max(0, other.strength - 5)
    }
}
