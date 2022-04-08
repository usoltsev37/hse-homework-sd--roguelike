package ru.hse.roguelike.controller

import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.terminal.Terminal

enum class ButtonType {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    EXIT,
    ENTER
}

class Input(val terminal: Terminal) {

    fun read() : ButtonType {
        val keyType = terminal.readInput().keyType
        when (keyType) {
            KeyType.ArrowUp -> return ButtonType.UP
            KeyType.ArrowDown -> return ButtonType.DOWN
            KeyType.ArrowLeft -> return ButtonType.LEFT
            KeyType.ArrowRight -> return ButtonType.RIGHT
            KeyType.Escape -> return ButtonType.EXIT
            KeyType.Enter -> return ButtonType.ENTER
            else -> {
                throw IllegalArgumentException("Unknown Command")
            }
        }
    }
}