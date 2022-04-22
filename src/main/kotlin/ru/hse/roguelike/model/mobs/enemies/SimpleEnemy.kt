package ru.hse.roguelike.model.mobs.enemies

import kotlinx.serialization.Serializable
import ru.hse.roguelike.util.Position

@Serializable
/**
 * Enemy implementation without special features
 */
class SimpleEnemy (override var position: Position) : Enemy() {
    override val name: String = "E"
    override val step: Int = DEFAULT_STEP
}