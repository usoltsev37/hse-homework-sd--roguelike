package ru.hse.roguelike.controller

import ru.hse.roguelike.EventType
import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.ui.window.Window

/**
 * The Controller of the game.
 */
class Controller(window: Window, model: GameModel) {

    private val activities: List<Activity>
    var isEndGame = false

    init {
        val gameActivity = GameActivity(model, isEndGame)
        val viewActivity = ViewActivity(window, model, isEndGame)
        activities = listOf(gameActivity, viewActivity)

        viewActivity.initState(model)
    }

    /**
     * Sends the event to the Controller for processing.
     * @param eventType Event from keyboard.
     */
    fun update(eventType: EventType) {
        activities.forEach { it.handleEvent(eventType) }
        isEndGame = activities[0].isEndGame or activities[1].isEndGame
    }
}
