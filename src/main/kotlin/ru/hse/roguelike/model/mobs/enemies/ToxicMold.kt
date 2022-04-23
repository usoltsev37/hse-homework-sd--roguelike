package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.model.mobs.enemies.movement.MoveStrategy
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.getClosestRandomPosition

/**
 * One of cloneable enemies
 */
class ToxicMold(override var position: Position, override val moveStrategy: MoveStrategy) : CloneableEnemy() {
    override val name: String = "M"

    override fun clone(): CloneableEnemy {
        return ToxicMold(position.getClosestRandomPosition(), moveStrategy)
    }
}