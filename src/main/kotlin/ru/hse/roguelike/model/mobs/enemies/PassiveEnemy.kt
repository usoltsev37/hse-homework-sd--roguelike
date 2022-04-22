package ru.hse.roguelike.model.mobs.enemies

import kotlinx.serialization.Serializable
import ru.hse.roguelike.util.Position

@Serializable
class PassiveEnemy(var position: Position) {
    val name: String = "V"
    fun getNextPosition(heroPos: Position): Position {
        return position
    }
}