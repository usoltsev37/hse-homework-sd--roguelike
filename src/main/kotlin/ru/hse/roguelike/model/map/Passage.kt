package ru.hse.roguelike.model.map

import ru.hse.roguelike.util.*
import kotlinx.serialization.Serializable

@Serializable
data class Passage(val from: Position, val to: Position, val dim: Int)