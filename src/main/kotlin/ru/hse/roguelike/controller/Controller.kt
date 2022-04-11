package ru.hse.roguelike.controller

import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.ui.window.Window

/**
 * The Controller of the game.
 */
class Controller(window: Window, model: GameModel) {

    private val activities: List<Activity>

    init {
        val gameActivity = GameActivity(model)
        val viewActivity = ViewActivity(window, model)
        activities = listOf(gameActivity, viewActivity)

        viewActivity.initState(model)
    }

    /**
     * Sends the event to the Controller for processing.
     * @param eventType Event from keyboard.
     */
    fun update(eventType: EventType) {
        activities.forEach { it.handleEvent(eventType) }
    }
}
