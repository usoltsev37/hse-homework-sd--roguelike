package ru.hse.roguelike.controller

import ru.hse.roguelike.controller.EventType
import ru.hse.roguelike.model.GameModel

/**
 * Abstraction for entities that can handle events.
 */
interface Activity {
    /**
     * Handle event.
     * @param eventType the Event.
     * @param model the Game Model.
     */
    fun handleEvent(eventType: EventType, model: GameModel)
}
