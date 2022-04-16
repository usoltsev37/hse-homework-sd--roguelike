package ru.hse.roguelike.model.items

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.characters.Hero
import ru.hse.roguelike.util.Constants
import kotlin.random.Random


/**
 * Item which can be consumed.
 * @see Item
 * @see ItemType
 */
@Serializable
class ConsumableItem(
    override val name: String,
    override val itemType: ItemType
) : Item() {

    private val healthAmount: Int = Random.nextInt(1, 11)

    override val description: String
        get() = """${name.take(Constants.HUD_WIDTH)} 
            |healthAmount = $healthAmount
        """.trimMargin()

    override fun use(hero: Hero) {
        hero.health += healthAmount
        hero.inventory.remove(this)
    }
}