package ru.hse.roguelike.model.mobs.enemies.factories

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.model.mobs.enemies.ToxicMold
import ru.hse.roguelike.model.mobs.enemies.movement.AggressiveMoveStrategy
import ru.hse.roguelike.model.mobs.enemies.movement.CowardMoveStrategy
import ru.hse.roguelike.model.mobs.enemies.movement.MoveStrategy
import ru.hse.roguelike.model.mobs.enemies.movement.PassiveMoveStrategy
import ru.hse.roguelike.util.Position
import kotlin.random.Random

/**
 * Abstraction for factory that creates enemies
 */
abstract class EnemyFactory {

    /**
     * Create Enemy.
     * @param position the Position of  the enemy.
     */
    abstract fun createEnemy(position: Position): Enemy

    fun createCloneableEnemy(position: Position): Enemy {
        return ToxicMold(position)
    }

    protected fun getRandomEnemyStrategy(): MoveStrategy =
        when (Random.nextInt(3)) {
            0 -> AggressiveMoveStrategy
            1 -> PassiveMoveStrategy
            else -> CowardMoveStrategy
        }

}
