package ru.hse.roguelike.model.mobs.enemies.movement

import ru.hse.roguelike.util.Position

interface MoveStrategy {
    fun getNextPosition(ourPos: Position, heroPos: Position, step: Int): Position
}
