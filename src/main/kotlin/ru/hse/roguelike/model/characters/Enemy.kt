package ru.hse.roguelike.model.characters

import kotlinx.serialization.Serializable
import ru.hse.roguelike.util.Position
import kotlin.random.Random

@Serializable
class Enemy(
    override var position: Position,
    override var health: Int = Random.nextInt(10, 20),
    override val strength: Int = Random.nextInt(1, 10)
) : Movable()