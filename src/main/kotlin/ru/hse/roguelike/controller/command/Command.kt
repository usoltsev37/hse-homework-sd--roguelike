package ru.hse.roguelike.controller.command

import ru.hse.roguelike.model.GameModel

/**
 * Implementation of "Command" pattern for interaction between controller and model.
 * @param model Game model
 */
abstract class Command(
    protected val model: GameModel
) {

    /**
     * Execute command
     */
    fun execute() {
        innerExecute()

        if (model.mode == GameModel.Mode.GAME) {
            model.fight()
            model.updateState()
        }

        if (model.hero.isDead) {
            model.isEndGame = true
        }
    }

    protected abstract fun innerExecute()
}