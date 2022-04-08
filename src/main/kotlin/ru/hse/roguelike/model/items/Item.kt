package ru.hse.roguelike.model.items

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.characters.Hero
import kotlin.random.Random

@Serializable
sealed class Item {

    abstract val name: String
    abstract val description: String
    abstract val type: ItemTypeImpl

    fun use(hero: Hero) {
        // TODO
    }

    companion object {
        fun createItem(type: ItemTypeImpl) : Item {
            return EquipableItem(type.toString(), type.getDescription(), type,  type.getHealthIncrease(),  type.getStrengthIncrease())
        }

        fun getRandomItem(): Item {
            return createItem(ItemTypeImpl.fromInt(Random.nextInt(ItemTypeImpl.values().size)))
        }
    }
}
