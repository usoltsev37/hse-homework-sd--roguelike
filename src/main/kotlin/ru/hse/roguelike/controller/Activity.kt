package ru.hse.roguelike.controller

import ru.hse.roguelike.controller.EventType
import ru.hse.roguelike.model.GameModel

interface Activity {
    fun handleEvent(eventType: EventType, model: GameModel)
}
