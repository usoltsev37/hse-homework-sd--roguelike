package ru.hse.roguelike.controller

import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.model.items.ConsumableItem
import ru.hse.roguelike.model.items.EquipableItem

class GameActivity: Activity {
    override fun handleEvent(eventType: EventType, model: GameModel) {
        when (model.mode) {
            GameModel.Mode.GAME -> {
                when (eventType) {
                    EventType.INVENTORY -> model.mode = GameModel.Mode.INVENTORY
                    EventType.UP -> TODO()
                    EventType.DOWN -> TODO()
                    EventType.LEFT -> TODO()
                    EventType.RIGHT -> TODO()
                    EventType.ENTER -> TODO()
                    EventType.REMOVE -> TODO()
                    EventType.USE -> TODO()
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
