package ru.hse.roguelike.model.mobs.enemies

import ru.hse.roguelike.util.Position

class TankEnemy(override var position: Position) : Enemy() {
    override val name: String = "T"
    override val step: Int = DEFAULT_STEP
    override var health: Int = 50
    override var strength: Int = 1
}