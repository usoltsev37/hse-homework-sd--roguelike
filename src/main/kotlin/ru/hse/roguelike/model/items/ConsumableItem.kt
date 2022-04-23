package ru.hse.roguelike.model.items

import ru.hse.roguelike.model.mobs.AbstractHero
import ru.hse.roguelike.util.Constants
import java.lang.Integer.min
import kotlin.random.Random


/**
 * Item which can be consumed.
 * @see Item
 * @see ItemType
 */
class ConsumableItem(
    override val name: String,
    override val itemType: ItemType
) : Item() {

    private val healthAmount: Int = Random.nextInt(1, 11)

    override val description: String
        get() = """${name.take(Constants.HUD_WIDTH)} 
            |healthAmount = $healthAmount
        """.trimMargin()

    override fun use(hero: AbstractHero) {
        hero.health = min(hero.health + healthAmount, hero.maxHealth)
        hero.inventory.remove(this)
    }
}