package ru.hse.roguelike.controller.command

import ru.hse.roguelike.model.GameModel

class RemoveCommand(model: GameModel) : Command(model) {

    override fun innerExecute() {
        when (model.mode) {
            GameModel.Mode.INVENTORY -> {
                if (model.selectedItemPosition != null) {
                    model.hero.inventory.removeAt(model.transformPosition2Index(model.selectedItemPosition!!))
                }
            }
            GameModel.Mode.GAME -> {}
        }
    }
}