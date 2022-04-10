package ru.hse.roguelike.controller

import ru.hse.roguelike.model.GameModel
import java.util.Collections

class GameActivity: Activity {
    override fun handleEvent(eventType: EventType, model: GameModel) {
        when (model.mode) {
            GameModel.Mode.GAME -> {
                if (eventType == EventType.INVENTORY) {
                    model.mode = GameModel.Mode.INVENTORY
                }
            }
            GameModel.Mode.INVENTORY -> {
                //   TODO   Меняем состояние инвенторя героя
                // удалять элементы инвенторя
                // использовать элементы (съедать)
                // менять тип оружия


                when (eventType) {
                    EventType.INVENTORY -> model.mode = GameModel.Mode.GAME
                    EventType.UP -> model.currentItemMoveUp()
                    EventType.DOWN -> model.currentItemMoveDown()
                    EventType.LEFT -> model.currentItemMoveLeft()
                    EventType.RIGHT -> model.currentItemMoveRight()
                    EventType.ENTER -> {
                        if (model.selectedItem != null) {
                            Collections.swap(model.hero.inventory, model.transformPosition2Index(model.selectedItem!!), model.transformPosition2Index(model.currentItem))
                            model.selectedItem = null
                        } else {
                            model.selectedItem = model.currentItem
                        }
                    }
                    EventType.REMOVE -> {
                        if (model.selectedItem != null) {
                            model.hero.inventory.removeAt(model.transformPosition2Index(model.selectedItem!!))
                        }
                    }
                    EventType.USE -> {
                        if (model.selectedItem != null) {
                            model.hero.inventory.get(model.transformPosition2Index(model.selectedItem!!)).use(model.hero)
                        }
                    }
                }
            }
        }
    }
}