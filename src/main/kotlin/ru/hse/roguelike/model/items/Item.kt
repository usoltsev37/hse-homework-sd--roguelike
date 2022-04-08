package ru.hse.roguelike.model.items

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.characters.Hero
import kotlin.random.Random

@Serializable
sealed class Item {

    abstract val name: String
    abstract val description: String
    abstract val type: ItemType

    fun use(hero: Hero) {
        // TODO
    }

    companion object {
        fun createItem(type: ItemType) : Item {
            return when (type) {
                ItemType.Helmet -> EquipableItem(type.toString(), ItemType.getDescription(type), type, ItemType.getHealthIncrease(type), ItemType.getStrengthIncrease(type))
                ItemType.Chestplate -> EquipableItem(type.toString(), ItemType.getDescription(type), type, ItemType.getHealthIncrease(type), ItemType.getStrengthIncrease(type))
                ItemType.Leggings -> EquipableItem(type.toString(), ItemType.getDescription(type), type, ItemType.getHealthIncrease(type), ItemType.getStrengthIncrease(type))
                ItemType.Boots -> EquipableItem(type.toString(), ItemType.getDescription(type), type, ItemType.getHealthIncrease(type), ItemType.getStrengthIncrease(type))
                ItemType.Sword -> EquipableItem(type.toString(), ItemType.getDescription(type), type, ItemType.getHealthIncrease(type), ItemType.getStrengthIncrease(type))
                ItemType.Bow -> EquipableItem(type.toString(), ItemType.getDescription(type), type, ItemType.getHealthIncrease(type), ItemType.getStrengthIncrease(type))
            }
        }

        fun getRandomItem(): Item {
            return createItem(ItemType.fromInt(Random.nextInt(ItemType.values().size)))
        }
    }
}
