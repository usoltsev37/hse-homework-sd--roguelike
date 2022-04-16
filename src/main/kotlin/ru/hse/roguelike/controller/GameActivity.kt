package ru.hse.roguelike.controller

import ru.hse.roguelike.EventType
import ru.hse.roguelike.exception.ModelLogicException
import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.model.items.ConsumableItem
import ru.hse.roguelike.model.items.EquipableItem
import ru.hse.roguelike.model.items.ItemType
import ru.hse.roguelike.util.left
import ru.hse.roguelike.util.lower
import ru.hse.roguelike.util.right
import ru.hse.roguelike.util.upper

/**
 * Activity implementation responsible for the Game Model.
 */
class GameActivity(private val model: GameModel) : Activity {

    override fun handleEvent(eventType: EventType) {
        when (model.mode) {
            GameModel.Mode.GAME -> {
                when (eventType) {
                    EventType.INVENTORY -> model.mode = GameModel.Mode.INVENTORY
                    EventType.UP -> model.moveHero(model.hero.position.upper())
                    EventType.DOWN -> model.moveHero(model.hero.position.lower())
                    EventType.LEFT -> model.moveHero(model.hero.position.left())
                    EventType.RIGHT -> model.moveHero(model.hero.position.right())
                    EventType.ENTER -> {
                        model.hero.attackEnemy(model.getCurrentCell())
                    }
                    EventType.USE -> {
                        val itemIndex = model.getCurrentCell().items.map { it.second }.indexOf(model.hero.position)
                        if (itemIndex != -1) {
                            model.hero.inventory.add(model.curCell.items[itemIndex].first)
                        }
                    }
                    EventType.REMOVE -> return
                }

                model.getCurrentCell().visited = true
            }
            GameModel.Mode.INVENTORY -> {
                when (eventType) {
                    EventType.INVENTORY -> model.mode = GameModel.Mode.GAME
                    EventType.UP -> model.currentItemMoveUp()
                    EventType.DOWN -> model.currentItemMoveDown()
                    EventType.LEFT -> model.currentItemMoveLeft()
                    EventType.RIGHT -> model.currentItemMoveRight()
                    EventType.ENTER -> processInventoryEnter()
                    EventType.REMOVE -> {
                        if (model.selectedItemPosition != null) {
                            model.hero.inventory.removeAt(model.transformPosition2Index(model.selectedItemPosition!!))
                        }
                    }
                    EventType.USE -> {
                        if (model.selectedItemPosition != null) {
                            val selectedItem =
                                model.hero.inventory.get(model.transformPosition2Index(model.selectedItemPosition!!))
                            if (selectedItem is ConsumableItem) {
                                selectedItem.use(model.hero)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun processInventoryEnter() {
        val inventoryItems =
            model.hero.inventory.filter { it is ConsumableItem || (it is EquipableItem && !it.isEquiped) }

        if (model.selectedItemPosition != null) {
            val indexCurrentItem = model.transformPosition2Index(model.currentItemPosition)
            val indexSelectedItem = model.transformPosition2Index(model.selectedItemPosition!!)

            if (model.currentItemPosition.first == 0) {
                val currentItem = model.hero.inventory[indexCurrentItem] as EquipableItem
                val selectedItem = model.hero.inventory[indexSelectedItem] as EquipableItem

                if (selectedItem.itemType.value == model.currentItemPosition.second) {
                    currentItem.use(model.hero)
                    selectedItem.use(model.hero)
                    model.swapSelectedCurrentItems()
                }
            }
            model.swapSelectedCurrentItems()
        } else {
            val inventoryIndex = model.transformPosition2Index(model.currentItemPosition) - 6

            if (inventoryIndex < 0 || inventoryIndex >= inventoryItems.size)
                return

            when (val item = inventoryItems[inventoryIndex]) {
                is ConsumableItem -> {
                    item.use(model.hero)
                    return
                }
                is EquipableItem -> {
                    if (!model.hero.inventory.any { it is EquipableItem && it.itemType.value == item.itemType.value && it.isEquiped }) {
                        item.use(model.hero)
                        return
                    }
                }
            }

            model.selectedItemPosition = model.currentItemPosition
        }
    }
}
