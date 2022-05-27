package ru.hse.roguelike.model.mobs.enemies.movement

import ru.hse.roguelike.util.Position

/**
 * Interface for move strategy of enemy
 */
interface MoveStrategy {

    /**
     * Get next position of enemy
     * @param ourPos current enemy position
     * @param heroPos player position
     * @param step enemy step size
     * @return next position of enemy
     */
    fun getNextPosition(ourPos: Position, heroPos: Position, step: Int): Position
}
