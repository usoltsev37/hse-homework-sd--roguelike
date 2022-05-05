package ru.hse.roguelike.controller

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.hse.roguelike.EventType
import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.model.items.ConsumableItem
import ru.hse.roguelike.model.items.EquipableItem
import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.model.items.ItemType
import ru.hse.roguelike.util.Position

class InventoryTest {

    @Test
    fun testOpenCloseInventory() {
        val model = GameModel()
        val gameActivity = GameActivity(model)

        val openCloseInventory = EventType.INVENTORY
        gameActivity.handleEvent(openCloseInventory)
        Assertions.assertEquals(model.mode, GameModel.Mode.INVENTORY)

        gameActivity.handleEvent(openCloseInventory)
        Assertions.assertEquals(model.mode, GameModel.Mode.GAME)
    }

    @Test
    fun testInventoryMovement() {
        val model = GameModel()
        val gameActivity = GameActivity(model)

        val openCloseInventory = EventType.INVENTORY
        gameActivity.handleEvent(openCloseInventory)
        Assertions.assertEquals(model.mode, GameModel.Mode.INVENTORY)

        val leftEvent = EventType.LEFT
        val rightEvent = EventType.RIGHT
        val downEvent = EventType.DOWN
        val upEvent = EventType.UP

        gameActivity.handleEvent(leftEvent)
        Assertions.assertEquals(model.currentItemPosition, Position(1, 0))
        gameActivity.handleEvent(upEvent)
        Assertions.assertEquals(model.currentItemPosition, Position(0, 0))
        repeat(10) { gameActivity.handleEvent(rightEvent) }
        Assertions.assertEquals(model.currentItemPosition, Position(0, 5))
        repeat(20) { gameActivity.handleEvent(downEvent) }
        Assertions.assertEquals(model.currentItemPosition, Position(7, 5))
    }

    @Test
    fun equipAndUseItem() {
        val model = GameModel()
        val gameActivity = GameActivity(model)

        val openCloseInventory = EventType.INVENTORY
        gameActivity.handleEvent(openCloseInventory)
        Assertions.assertEquals(model.mode, GameModel.Mode.INVENTORY)

        val potion = ConsumableItem(ItemType.Potion.name, ItemType.Potion)
        val leggings: EquipableItem = Item.createItem(ItemType.Leggings) as EquipableItem
        model.hero.inventory.add(potion)
        model.hero.inventory.add(leggings)

        gameActivity.handleEvent(EventType.RIGHT)
        gameActivity.handleEvent(EventType.ENTER)

        Assertions.assertTrue(leggings.isEquiped)
        Assertions.assertEquals(2, model.hero.inventory.size)

        gameActivity.handleEvent(EventType.LEFT)
        gameActivity.handleEvent(EventType.ENTER)
        Assertions.assertEquals(1, model.hero.inventory.size)
    }

    @Test
    fun reEquipItem() {
        val model = GameModel()
        val gameActivity = GameActivity(model)

        val openCloseInventory = EventType.INVENTORY
        gameActivity.handleEvent(openCloseInventory)
        Assertions.assertEquals(model.mode, GameModel.Mode.INVENTORY)

        val boots1: EquipableItem = Item.createItem(ItemType.Boots) as EquipableItem
        val boots2: EquipableItem = Item.createItem(ItemType.Boots) as EquipableItem

        model.hero.inventory.add(boots1)
        model.hero.inventory.add(boots2)

        gameActivity.handleEvent(EventType.ENTER)
        Assertions.assertTrue(boots1.isEquiped)
        Assertions.assertFalse(boots2.isEquiped)
        Assertions.assertNull(model.selectedItemPosition)

        gameActivity.handleEvent(EventType.ENTER)
        Assertions.assertEquals(Position(1, 0), model.selectedItemPosition)

        gameActivity.handleEvent(EventType.UP)
        Assertions.assertEquals(Position(1, 0), model.selectedItemPosition)
        Assertions.assertEquals(Position(0, 0), model.currentItemPosition)
        repeat(3) { gameActivity.handleEvent(EventType.RIGHT) }

        gameActivity.handleEvent(EventType.ENTER)
        Assertions.assertNull(model.selectedItemPosition)
        Assertions.assertEquals(2, model.hero.inventory.size)
        Assertions.assertFalse(boots1.isEquiped)
        Assertions.assertTrue(boots2.isEquiped)
    }
}