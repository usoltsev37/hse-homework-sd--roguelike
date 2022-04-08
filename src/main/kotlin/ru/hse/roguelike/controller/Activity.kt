package ru.hse.roguelike.controller

import ru.hse.roguelike.EventType

interface Activity {
    fun handleEvent(eventType: EventType)
}