package ru.hse.roguelike.controller

import ru.hse.roguelike.EventType
import ru.hse.roguelike.controller.command.*
import ru.hse.roguelike.model.GameModel

/**
 * Activity implementation responsible for the Game Model.
 */
class GameActivity(model: GameModel) : Activity {

    private val buttonToCommandMap: Map<EventType, Command>

    init {
        buttonToCommandMap = mapOf(
            EventType.INVENTORY to InventoryCommand(model),
            EventType.UP to UpCommand(model),
            EventType.DOWN to DownCommand(model),
            EventType.LEFT to LeftCommand(model),
            EventType.RIGHT to RightCommand(model),
            EventType.USE to UseCommand(model),
            EventType.REMOVE to RemoveCommand(model),
            EventType.ENTER to EnterCommand(model)
        )
    }

    override fun handleEvent(eventType: EventType) {
        buttonToCommandMap[eventType]?.execute()
    }
}
