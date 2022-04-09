package ru.hse.roguelike.controller

import ru.hse.roguelike.model.GameModel

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
                    EventType.UP -> model.hero.selectedItemMoveUp()
                    EventType.DOWN -> model.hero.selectedItemMoveDown()
                    EventType.LEFT -> model.hero.selectedItemMoveLeft()
                    EventType.RIGHT -> model.hero.selectedItemMoveRight()
                    EventType.ENTER -> TODO()
                }
                TODO("Изменить курсор на View")
            }
        }
        TODO("Not yet implemented")
    }
}