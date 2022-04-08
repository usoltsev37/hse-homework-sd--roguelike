package ru.hse.roguelike.model.items

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.characters.Hero
import kotlin.random.Random

@Serializable
sealed class Item {

    abstract val name: String
    abstract val description: String
    abstract val itemType: ItemType

    abstract fun use(hero: Hero)

    fun pickUp(hero: Hero) {
        hero.inventory.add(this)
    }

    companion object {
        fun createItem(type: ItemType): Item {
            return EquipableItem(type.name, type, type.getHealthIncrease(), type.getStrengthIncrease())
        }

        fun getRandomItem(): Item {
            return if (Random.nextInt(100) < 50) createItem(ItemType.fromInt(Random.nextInt(ItemType.values().size)))
            else ConsumableItem(ItemType.Potion.name, ItemType.Potion)
        }
    }
}
