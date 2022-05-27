package ru.hse.roguelike.controller

import ru.hse.roguelike.EventType
import ru.hse.roguelike.controller.command.*

/**
 * Activity implementation responsible for the Game Model.
 */
class GameActivity(
    private val buttonToCommandMap: Map<EventType, Command>
) : Activity {

    override fun handleEvent(eventType: EventType) {
        buttonToCommandMap[eventType]?.execute()
    }
}
