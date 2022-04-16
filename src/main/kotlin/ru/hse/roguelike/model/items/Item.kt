package ru.hse.roguelike.model.items

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.mobs.AbstractHero
import ru.hse.roguelike.model.mobs.Hero
import kotlin.random.Random

/**
 * Base class for all items
 * @see ConsumableItem
 * @see EquipableItem
 * @see ItemType
 */
@Serializable
sealed class Item {

    abstract val name: String
    abstract val description: String
    abstract val itemType: ItemType

    /**
     * Use item
     * @param hero who uses the item
     */
    abstract fun use(hero: AbstractHero)

    /**
     * Adds item to hero's inventory
     * @param hero who picks up item
     */
    fun pickUp(hero: Hero) {
        hero.inventory.add(this)
    }

    companion object {
        /**
         * Create concrete item by giving ItemType
         * @param type item type
         * @return item with given type
         */
        fun createItem(type: ItemType): Item {
            return EquipableItem("${type.name.take(8)} #${Random.nextInt(100)}", type, type.getHealthIncrease(), type.getStrengthIncrease())
        }

        /**
         * Get random item
         * @return item with random type
         */
        fun getRandomItem(): Item {
            return if (Random.nextInt(100) < 50) createItem(ItemType.fromInt(Random.nextInt(ItemType.values().size - 1)))
            else ConsumableItem(ItemType.Potion.name, ItemType.Potion)
        }
    }
}
