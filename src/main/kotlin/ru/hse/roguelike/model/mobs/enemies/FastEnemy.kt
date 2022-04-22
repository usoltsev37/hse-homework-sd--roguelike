package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.Position

/**
 * Enemy implementation for creates with big step
 */
class FastEnemy(override var position: Position) : Enemy() {
    override val name: String = "F"
    override var step = 2

}
