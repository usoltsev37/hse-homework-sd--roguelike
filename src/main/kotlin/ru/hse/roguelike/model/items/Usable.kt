package ru.hse.roguelike.model.items

/**
 * Interface for items that can be useful for Hero.
 * @see Item
 * @see ItemType
 */
interface Usable {
    /**
     * The amount of health that the hero will gain when he uses the item
     * @return health increase
     */
    fun getHealthIncrease(): Int
    /**
     * The amount of strength that the hero will gain when he uses the item
     * @return strength increase
     */
    fun getStrengthIncrease(): Int
}
