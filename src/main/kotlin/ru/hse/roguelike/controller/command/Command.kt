package ru.hse.roguelike.controller.command

import ru.hse.roguelike.model.GameModel

/**
 * Implementation of "Command" pattern for interaction between controller and model.
 * @param model Game model
 */
abstract class Command(
    private val model: GameModel
) {

    /**
     * Execute command
     */
    abstract fun execute()
}