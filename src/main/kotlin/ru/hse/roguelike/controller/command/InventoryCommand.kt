package ru.hse.roguelike.controller.command

import ru.hse.roguelike.model.GameModel

class InventoryCommand(model: GameModel) : Command(model) {

    override fun innerExecute() {
        when (model.mode) {
            GameModel.Mode.INVENTORY -> model.mode = GameModel.Mode.GAME
            GameModel.Mode.GAME -> model.mode = GameModel.Mode.INVENTORY
        }
    }
}