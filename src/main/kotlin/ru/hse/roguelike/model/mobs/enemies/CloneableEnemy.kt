package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.getNearestRandomPosition
import ru.hse.roguelike.util.isFree
import kotlin.random.Random


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