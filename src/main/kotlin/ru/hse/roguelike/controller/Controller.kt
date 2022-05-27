package ru.hse.roguelike.controller

import ru.hse.roguelike.EventType
import ru.hse.roguelike.controller.command.Command
import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.ui.window.Window

/**
 * The Controller of the game.
 */
class Controller(window: Window, model: GameModel, buttonToCommandMap: Map<EventType, Command>) {

    private val activities: List<Activity>

    init {
        val gameActivity = GameActivity(buttonToCommandMap)
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
