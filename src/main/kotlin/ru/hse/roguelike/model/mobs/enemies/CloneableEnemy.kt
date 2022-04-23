package ru.hse.roguelike.model.mobs.enemies

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.util.*
import kotlin.random.Random
import kotlin.random.nextInt


@Serializable
abstract class CloneableEnemy : Enemy() {
    private var myStrength: Int = 1
    private var myHealth: Int = 15

    override var strength: Int by ::myStrength
    override var health: Int by ::myHealth

    override val step: Int = 0

    fun tryInitialize(cell: Cell) {
        var i = 0
        while (i < 10 && !position.isFree(cell)) {
            position = position.getNearestRandomPosition()
            i++
        }
        if (i < 10 && Random.nextInt(100) <= Constants.CLONE_ENEMY_PROB) {
            cell.enemies.add(this)
        }
    }

    abstract fun clone(): CloneableEnemy
}