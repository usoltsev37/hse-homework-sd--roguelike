package ru.hse.roguelike.controller

import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.util.Constants.INITIAL_LEVEL
import ru.hse.roguelike.util.Constants.INITIAL_POSITION

/**
 * The Controller of the game.
 * @param gameActivity the Controller part responsible for the model.
 * @param viewActivity the Controller part responsible for the view.
 */
class Controller(
    gameActivity: GameActivity,
    viewActivity: ViewActivity
) {

    private val activities: List<Activity> = listOf(gameActivity, viewActivity)
    private val model = GameModel(INITIAL_LEVEL)

    init {
        viewActivity.initState(model)
    }

    /**
     * Sends the event to the Controller for processing.
     * @param eventType Event from keyboard.
     */
    fun update(eventType: EventType) {
        activities.forEach { it.handleEvent(eventType, model) }
    }
}
