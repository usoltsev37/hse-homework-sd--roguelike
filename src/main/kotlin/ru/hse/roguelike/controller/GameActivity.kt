package ru.hse.roguelike.controller

import ru.hse.roguelike.EventType
import ru.hse.roguelike.exception.ModelLogicException
import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.model.items.ConsumableItem
import ru.hse.roguelike.model.items.EquipableItem
import ru.hse.roguelike.model.items.ItemType
import ru.hse.roguelike.util.*
import kotlin.random.Random

/**
 * Activity implementation responsible for the Game Model.
 */
class GameActivity(private val model: GameModel, override var isEndGame: Boolean) : Activity {



    override fun handleEvent(eventType: EventType) {
        when (model.mode) {
            GameModel.Mode.GAME -> {
                when (eventType) {
                    EventType.INVENTORY -> model.mode = GameModel.Mode.INVENTORY
                    EventType.UP -> model.moveHero(model.hero.position.upper())
                    EventType.DOWN -> model.moveHero(model.hero.position.lower())
                    EventType.LEFT -> model.moveHero(model.hero.position.left())
                    EventType.RIGHT -> model.moveHero(model.hero.position.right())
                    EventType.USE -> {
                        val itemIndex = model.curCell.items.map { it.second }.indexOf(model.hero.position)
                        if (itemIndex != -1) {
                            model.hero.inventory.add(model.curCell.items[itemIndex].first)
                        }
                    }
                    EventType.REMOVE -> return
                    else -> {}
                }

                model.updateCurrentCell()
                
                model.curCell.enemies.find {
                    it.position == model.hero.position
                }?.let {
                    model.hero.attack(it)
                    it.attack(model.hero)
                }

                model.curCell.enemies.forEach {
                    model.moveEnemy(it)
                }

                model.curCell.visited = true
                model.updateCellsState()
                model.updatePassagesState()
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
        if (model.hero.isDead) {
            isEndGame = true
        }
    }

    private fun processInventoryEnter() {
        val inventoryItems =
            model.hero.inventory.filter { it is ConsumableItem || (it is EquipableItem && !it.isEquiped) }

        if (model.selectedItemPosition != null) {
            val indexSelectedItem = model.transformPosition2Index(model.selectedItemPosition!!) - 6

            if (model.currentItemPosition.first == 0) {
                val currentItem =
                    model.hero.inventory.find { it is EquipableItem && it.isEquiped && it.itemType.value == model.currentItemPosition.second } as EquipableItem
                val selectedItem = inventoryItems[indexSelectedItem] as EquipableItem

                if (selectedItem.itemType.value == model.currentItemPosition.second) {
                    currentItem.unuse(model.hero)
                    selectedItem.use(model.hero)
                }
            }
            model.selectedItemPosition = null
        } else {
            val indexCurrentItem = model.transformPosition2Index(model.currentItemPosition) - 6

            if (indexCurrentItem < 0 || indexCurrentItem >= inventoryItems.size)
                return

            when (val item = inventoryItems[indexCurrentItem]) {
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
