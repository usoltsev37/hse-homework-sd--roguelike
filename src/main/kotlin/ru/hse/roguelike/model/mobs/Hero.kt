package ru.hse.roguelike.model.mobs

import ru.hse.roguelike.model.items.EquipableItem
import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.Position
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * The main character the player will play as. Has an inventory that contains picked up items.
 */
class Hero(
    override var position: Position,
    override var health: Int = 50,
    override var strength: Int = 10,
    var armor: Int = 0

) : Mob() {

    /**
    The first 6 elements are worn on the hero (isEquiped = True)
     */
    val inventory: MutableList<Item> = ArrayList(Constants.COUNT_COLUMNS)


    var currWeapon: EquipableItem? = null

    override fun attack(other: Mob) {
        other.health -= strength
    }

    /**
     * Attack Closest Enemy.
     * @param cell current Hero Cell
     */
    fun attackEnemy(cell: Cell) {
        val enemy = getClosestEnemy(cell)
        if (enemy != null) {
            attack(enemy)
        }
    }

    private fun getClosestEnemy(cell: Cell): Enemy? {
        if (cell.enemies.isEmpty()) {
            return null
        }
        var minEnemy: Enemy? = null
        var minValue = distanceBetweenHeroEnemy(cell.enemies[0])
        cell.enemies.forEach { enemy ->
            val currDistance = distanceBetweenHeroEnemy(enemy)
            if (minValue > currDistance) {
                minValue = currDistance
                minEnemy = enemy
            }
        }
        return minEnemy
    }

    private fun distanceBetweenHeroEnemy(enemy: Enemy): Double {
        return sqrt(
            abs(
                (enemy.position.first - position.first).toDouble().pow(2) -
                        (enemy.position.second - position.second).toDouble().pow(2)
            )
        )
    }

}