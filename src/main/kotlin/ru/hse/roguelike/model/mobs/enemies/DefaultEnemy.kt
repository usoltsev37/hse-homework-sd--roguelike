package ru.hse.roguelike.model.mobs.enemies

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.mobs.enemies.movement.MoveStrategy
import ru.hse.roguelike.util.Position

@Serializable
/**
 * Enemy implementation without special features
 */
class DefaultEnemy (override var position: Position, override val moveStrategy: MoveStrategy) : Enemy() {
    override val name: String = "E"
    override val step: Int = DEFAULT_STEP
}