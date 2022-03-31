package ru.hse.roguelike.model.characters

import kotlinx.serialization.Serializable
import ru.hse.roguelike.util.Position

@Serializable
class Enemy(override val position: Position,
            override var health: Int,
            override val strength: Int): Movable()