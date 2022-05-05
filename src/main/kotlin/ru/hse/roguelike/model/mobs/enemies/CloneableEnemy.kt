package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.model.mobs.enemies.movement.PassiveMoveStrategy
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.getClosestRandomPosition
import ru.hse.roguelike.util.isFree
import kotlin.random.Random

/**
 * Abstraction for cloneable enemy prototypes
 * @see Enemy
 */
abstract class CloneableEnemy(position: Position, name: String) :
    Enemy(position, 15, 1, name, PassiveMoveStrategy, 0) {

    /**
     * Try to initialize cloned enemy.
     * If enemy position is free add enemy to cell's enemies list,
     * otherwise try to generate new closest position
     * @param cell cell where enemy is located
     */
    fun tryInitialize(cell: Cell) {
        var i = 0
        while (i < 10 && !position.isFree(cell)) {
            position = position.getClosestRandomPosition()
            i++
        }
        if (i < 10 && Random.nextInt(100) <= Constants.CLONE_ENEMY_PROB) {
            cell.enemies.add(this)
        }
    }

    /**
     * Clone existing enemy prototype
     * @return cloned enemy
     */
    abstract fun clone(): CloneableEnemy
}