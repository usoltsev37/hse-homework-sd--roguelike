package ru.hse.roguelike.model.items

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import ru.hse.roguelike.model.mobs.Hero
import ru.hse.roguelike.util.Position

internal class ConsumableItemTest {

    @Test
    fun use() {
        repeat(100) {
            val hero = Hero(Position(0, 0))

            val item = Item.getRandomItem()
            hero.inventory.add(item)

            Assertions.assertEquals(1, hero.inventory.size)

            hero.health -= 5
            val oldHealth = hero.health

            if (hero.inventory[0] is ConsumableItem) {
                hero.inventory[0].use(hero)
                Assertions.assertTrue(hero.health > oldHealth)
                Assertions.assertTrue(hero.maxHealth >= hero.health)
                Assertions.assertTrue(hero.inventory.isEmpty())
            }
        }
    }
}