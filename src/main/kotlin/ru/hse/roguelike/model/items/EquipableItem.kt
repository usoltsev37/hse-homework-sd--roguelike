package ru.hse.roguelike.model.items

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.characters.Hero

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

    override val description: String
        get() = "health increase = $healthIncrease, strength increase = $strengthIncrease"

}