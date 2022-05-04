package ru.hse.roguelike.controller.command

import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.util.upper


class UpCommand(model: GameModel) : Command(model) {

    override fun innerExecute() {
        when (model.mode) {
            GameModel.Mode.INVENTORY -> model.currentItemMoveUp()
            GameModel.Mode.GAME -> model.moveHero(model.hero.position.upper())
        }
    }
}