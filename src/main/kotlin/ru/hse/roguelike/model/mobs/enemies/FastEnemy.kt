package ru.hse.roguelike.model.mobs.enemies

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.Position

/**
 * Enemy implementation for creates with big step
 */
@Serializable
class FastEnemy(override var position: Position) : Enemy() {
    override val name: String = "F"
    override var step = 2

}
