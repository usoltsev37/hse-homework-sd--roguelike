package ru.hse.roguelike

import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.terminal.Terminal
import ru.hse.roguelike.controller.Activity
import ru.hse.roguelike.controller.GameActivity
import ru.hse.roguelike.controller.InventoryActivity

enum class EventType {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    EXIT,
    ENTER,
    INVENTORY,
    UNKNOWN
}

class InputActivity(private val terminal: Terminal): Activity {

    private val activities: List<Activity> = listOf(GameActivity(), InventoryActivity())

    fun read() : EventType {
        val keyStroke = terminal.readInput()
        return when (keyStroke.keyType) {
            KeyType.ArrowUp -> EventType.UP
            KeyType.ArrowDown -> EventType.DOWN
            KeyType.ArrowLeft -> EventType.LEFT
            KeyType.ArrowRight -> EventType.RIGHT
            KeyType.Escape -> EventType.EXIT
            KeyType.Enter -> EventType.ENTER
            KeyType.Character -> {
                when (keyStroke.character.lowercaseChar()) {
                    'w' -> EventType.UP
                    's' -> EventType.DOWN
                    'a' -> EventType.LEFT
                    'd' -> EventType.RIGHT
                    'i' -> EventType.INVENTORY
                    else -> EventType.UNKNOWN
                }
            }
            else -> EventType.UNKNOWN
        }
    }

    override fun handleEvent(eventType: EventType) {
        activities.forEach { it.handleEvent(eventType) }
    }


}