package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.util.Position

class PassiveEnemy(position: Position): Enemy(position) {
    override fun move(heroPos: Position) {}
}