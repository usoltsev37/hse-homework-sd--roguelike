package ru.hse.roguelike.model.mobs.enemies

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.mobs.enemies.movement.MoveStrategy
import ru.hse.roguelike.util.Position

@Serializable
/**
 * Enemy implementation with a lot of health and low strength
 */
class TankEnemy(override var position: Position, override val moveStrategy: MoveStrategy) : Enemy() {
    override val name: String = "T"
    override val step: Int = DEFAULT_STEP

    init {
        health *= 3
        strength = 1
    }
}
