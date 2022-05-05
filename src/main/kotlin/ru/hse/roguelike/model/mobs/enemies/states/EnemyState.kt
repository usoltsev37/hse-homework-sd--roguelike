package ru.hse.roguelike.model.mobs.enemies.states

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.Position

sealed interface EnemyState {
    fun getNextPosition(heroPos: Position, enemy: Enemy): Position
}