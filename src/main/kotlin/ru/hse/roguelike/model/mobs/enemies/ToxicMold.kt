package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.getClosestRandomPosition

/**
 * One of cloneable enemies
 */
class ToxicMold(position: Position) : CloneableEnemy(position, "M") {

    override fun clone(): CloneableEnemy {
        return ToxicMold(position.getClosestRandomPosition())
    }
}