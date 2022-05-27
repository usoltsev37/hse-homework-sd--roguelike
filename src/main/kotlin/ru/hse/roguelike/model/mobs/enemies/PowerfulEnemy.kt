package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.model.mobs.Mob
import ru.hse.roguelike.model.mobs.enemies.movement.MoveStrategy
import ru.hse.roguelike.util.Position
import kotlin.math.max
import kotlin.random.Random

/**
 * Enemy implementation for strong creates
 */
class PowerfulEnemy(position: Position, moveStrategy: MoveStrategy) :
    Enemy(position, Random.nextInt(10, 20), Random.nextInt(1, 10), "P", moveStrategy) {

    override fun attack(other: Mob) {
        other.health -= strength
        strength += 5
        other.strength = max(0, other.strength - 5)
    }
}
