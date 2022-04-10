package ru.hse.roguelike.controller

import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.model.characters.Hero
import ru.hse.roguelike.util.Constants.INITIAL_LEVEL
import ru.hse.roguelike.util.Constants.INITIAL_POSITION

class Controller(
    gameActivity: GameActivity,
    viewActivity: ViewActivity
) {

    private val activities: List<Activity> = listOf(gameActivity, viewActivity)
    private val model = GameModel(INITIAL_LEVEL, Hero(INITIAL_POSITION))

    init {
        viewActivity.initState(model)
    }

    fun update(eventType: EventType) {
        activities.forEach { it.handleEvent(eventType, model) }
    }
}
