package ru.hse.roguelike.controller

import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.terminal.Terminal

/**
 * Abstraction to events from keyboard.
 */
interface EventInterface

/**
 * Event implementation.
 */
enum class EventType: EventInterface {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    ENTER,
    INVENTORY,
    REMOVE,
    USE
}

/**
 * Event implementation for Special Events.
 * Event is Special if we need to frame it as soon as we got it.
 */
enum class SpecialEventType: EventInterface {
    EXIT,
    UNKNOWN
}

/**
 * Keyboard Signal Handler.
 * @param terminal Lanterna's terminal implementation.
 */
class Input(private val terminal: Terminal) {

    /**
     * Reads keyboard input in blocking mode.
     */
    fun read() : EventInterface {
        val keyStroke = terminal.readInput()
        return when (keyStroke.keyType) {
            KeyType.ArrowUp -> EventType.UP
            KeyType.ArrowDown -> EventType.DOWN
            KeyType.ArrowLeft -> EventType.LEFT
            KeyType.ArrowRight -> EventType.RIGHT
            KeyType.Escape -> SpecialEventType.EXIT
            KeyType.Enter -> EventType.ENTER
            KeyType.Character -> {
                when (keyStroke.character.lowercaseChar()) {
                    'w' -> EventType.UP
                    's' -> EventType.DOWN
                    'a' -> EventType.LEFT
                    'd' -> EventType.RIGHT
                    'i' -> EventType.INVENTORY
                    'r' -> EventType.REMOVE
                    ' ' -> EventType.USE
                    else -> SpecialEventType.UNKNOWN
                }
            }
            else -> SpecialEventType.UNKNOWN
        }
    }

}
