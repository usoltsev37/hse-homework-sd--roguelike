package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.model.mobs.enemies.movement.MoveStrategy
import ru.hse.roguelike.util.Position
import kotlin.random.Random

/**
 * Enemy implementation with a lot of health and low strength
 */
class TankEnemy(position: Position, moveStrategy: MoveStrategy) :
    Enemy(position, Random.nextInt(30, 60), 1, "T", moveStrategy)
