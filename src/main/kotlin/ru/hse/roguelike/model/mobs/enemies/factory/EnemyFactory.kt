package ru.hse.roguelike.model.mobs.enemies.factory

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.Position

/**
 * Abstraction for factory that creates enemies
 */
interface EnemyFactory {

    /**
     * Create Enemy.
     * @param position the Position of  the enemy.
     */
    fun createEnemy(position: Position) : Enemy

}
