package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.model.mobs.enemies.movement.MoveStrategy
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.getNearestRandomPosition
import ru.hse.roguelike.util.isFree

class ToxicMold(override var position: Position, override val moveStrategy: MoveStrategy): CloneableEnemy() {
    override val name: String = "M"

    override fun clone(): CloneableEnemy {
        return ToxicMold(position.getNearestRandomPosition(), moveStrategy)
    }
}