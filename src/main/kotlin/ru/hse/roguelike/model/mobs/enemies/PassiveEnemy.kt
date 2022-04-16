package ru.hse.roguelike.model.mobs.enemies

import kotlinx.serialization.Serializable
import ru.hse.roguelike.util.Position

@Serializable
class PassiveEnemy(override var position: Position): Enemy() {
    override fun getNextPosition(heroPos: Position): Position {
        return position
    }
}