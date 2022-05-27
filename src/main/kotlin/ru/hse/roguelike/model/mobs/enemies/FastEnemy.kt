package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.model.mobs.enemies.movement.MoveStrategy
import ru.hse.roguelike.util.Position
import kotlin.random.Random

/**
 * Enemy implementation for creates with big step
 */
class FastEnemy(position: Position, moveStrategy: MoveStrategy) :
    Enemy(position, Random.nextInt(10, 20), Random.nextInt(1, 10), "F", moveStrategy, 2)
