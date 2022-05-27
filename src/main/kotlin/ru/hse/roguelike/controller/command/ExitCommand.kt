package ru.hse.roguelike.controller.command

import ru.hse.roguelike.model.GameModel

class ExitCommand(model: GameModel) : Command(model) {

    override fun innerExecute() {
        when (model.mode) {
            GameModel.Mode.INVENTORY -> TODO()
            GameModel.Mode.GAME -> TODO()
        }
    }
}