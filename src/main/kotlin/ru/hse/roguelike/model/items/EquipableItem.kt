package ru.hse.roguelike.model.items

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.mobs.Hero

/**
 * Item which can be equipped.
 * @see Item
 * @see ItemType
 */
@Serializable
class EquipableItem(
    override val name: String,
    override val itemType: ItemType,
    private val healthIncrease: Int, private val strengthIncrease: Int,
    var isEquiped: Boolean = false
) : Item() {

    override fun use(hero: Hero) {
        hero.strength += strengthIncrease
        hero.armor += healthIncrease
        isEquiped = true
    }

    /**
     * Stop using item
     * @param hero who stops using item
     */
    fun unuse(hero: Hero) {
        hero.strength -= strengthIncrease
        hero.armor -= healthIncrease
        isEquiped = false
    }

    override val description: String
        get() = "health increase = $healthIncrease, strength increase = $strengthIncrease"

}