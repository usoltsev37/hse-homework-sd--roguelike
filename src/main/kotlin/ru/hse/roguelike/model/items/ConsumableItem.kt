package ru.hse.roguelike.model.items

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.characters.Hero
import kotlin.random.Random

@Serializable
class ConsumableItem(
    override val name: String,
    override val itemType: ItemType
) : Item() {

    private val healthAmount: Int = Random.nextInt(1, 11)

    override val description: String
        get() = "healthAmount = $healthAmount"

    override fun use(hero: Hero) {
        hero.health += healthAmount
    }
}