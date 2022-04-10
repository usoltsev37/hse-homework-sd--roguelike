package ru.hse.roguelike.controller

import ru.hse.roguelike.exception.ModelLogicException
import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.model.items.ConsumableItem
import ru.hse.roguelike.model.items.EquipableItem
import ru.hse.roguelike.model.items.ItemType

/**
 * Activity implementation responsible for the Game Model.
 */
class GameActivity: Activity {
    override fun handleEvent(eventType: EventType, model: GameModel) {
        when (model.mode) {
            GameModel.Mode.GAME -> {
                when (eventType) {
                    EventType.INVENTORY -> model.mode = GameModel.Mode.INVENTORY
                    EventType.UP -> model.moveHeroUp()
                    EventType.DOWN -> model.moveHeroDown()
                    EventType.LEFT -> model.moveHeroLeft()
                    EventType.RIGHT -> model.moveHeroRight()
                    EventType.ENTER -> {
                        model.hero.attackEnemy(model.getCurrentCell())
                    }
                    EventType.REMOVE, EventType.USE -> return
                }
            }
            GameModel.Mode.INVENTORY -> {
                when (eventType) {
                    EventType.INVENTORY -> model.mode = GameModel.Mode.GAME
                    EventType.UP -> model.currentItemMoveUp()
                    EventType.DOWN -> model.currentItemMoveDown()
                    EventType.LEFT -> model.currentItemMoveLeft()
                    EventType.RIGHT -> model.currentItemMoveRight()
                    EventType.ENTER -> {
                        if (model.selectedItemPosition != null) {
                            val indexCurrentItem = model.transformPosition2Index(model.currentItemPosition)
                            val indexSelectedItem = model.transformPosition2Index(model.selectedItemPosition!!)
                            if (model.currentItemPosition.first == 0 && model.hero.inventory[indexCurrentItem] is EquipableItem) {
                                val currentItem = model.hero.inventory[indexCurrentItem] as EquipableItem
                                if (model.hero.inventory[indexSelectedItem] is EquipableItem) {
                                    val selectedItem = model.hero.inventory[indexSelectedItem] as EquipableItem
                                    if (selectedItem.itemType.value == model.currentItemPosition.second) {
                                        currentItem.use(model.hero)
                                        selectedItem.use(model.hero)
                                        model.swapSelectedCurrentItems()
                                    }
                                } else {
                                    return
                                }
                            }
                            model.swapSelectedCurrentItems()
                        } else {
                            model.selectedItemPosition = model.currentItemPosition
                            if (model.currentItemPosition.first == 0 &&
                                model.currentItemPosition.second == ItemType.Sword.value ||
                                model.currentItemPosition.second == ItemType.Potion.value) {
                                val weapon = model.hero.inventory[model.transformPosition2Index(model.currentItemPosition)]
                                if (weapon is EquipableItem) {
                                    model.hero.currWeapon =  weapon
                                } else {
                                    throw ModelLogicException("First 6 elements of the inventory must be EquipableItem")
                                }
                            }
                        }
                    }
                    EventType.REMOVE -> {
                        if (model.selectedItemPosition != null) {
                            model.hero.inventory.removeAt(model.transformPosition2Index(model.selectedItemPosition!!))
                        }
                    }
                    EventType.USE -> {
                        if (model.selectedItemPosition != null) {
                            val selectedItem = model.hero.inventory.get(model.transformPosition2Index(model.selectedItemPosition!!))
                            if (selectedItem is ConsumableItem) {
                                selectedItem.use(model.hero)
                            }
                        }
                    }
                }
            }
        }
    }
}
