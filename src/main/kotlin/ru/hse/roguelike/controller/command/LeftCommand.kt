package ru.hse.roguelike.controller.command

import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.util.left

class LeftCommand(model: GameModel) : Command(model) {

    override fun innerExecute() {
        when (model.mode) {
            GameModel.Mode.INVENTORY -> model.currentItemMoveLeft()
            GameModel.Mode.GAME -> model.moveHero(model.hero.position.left())
        }
    }
}