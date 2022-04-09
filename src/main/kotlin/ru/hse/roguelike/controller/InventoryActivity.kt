package ru.hse.roguelike.controller

import ru.hse.roguelike.model.GameModel

class InventoryActivity: Activity {
    override fun handleEvent(eventType: EventType, model: GameModel) {
        when (model.mode) {
            GameModel.Mode.GAME -> {
                if (eventType == EventType.INVENTORY) {
                    model.mode = GameModel.Mode.INVENTORY
                }
            }
            GameModel.Mode.INVENTORY -> {
                when (eventType) {
                    EventType.INVENTORY -> model.mode = GameModel.Mode.GAME
                    EventType.UP -> TODO()
                    EventType.DOWN -> TODO()
                    EventType.LEFT -> TODO()
                    EventType.RIGHT -> TODO()
                    EventType.ENTER -> TODO()
                }
                TODO("Изменить курсор на View")
            }
        }
    }
}
