package ru.hse.roguelike.model.mobs.enemies.factory

import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.Position

interface EnemyFactory {

    fun createEnemy(position: Position) : Enemy

}
