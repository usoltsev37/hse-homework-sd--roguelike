package ru.hse.roguelike

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import ru.hse.roguelike.util.Constants

fun main() {
    val terminalFactory = DefaultTerminalFactory()
    terminalFactory.setInitialTerminalSize(TerminalSize(Constants.DEFAULT_MAP_WIDTH, Constants.DEFAULT_MAP_HEIGHT))
    val terminal = terminalFactory.createTerminal()
    terminal.use {
        it.enterPrivateMode()

        val input = InputActivity(it)

        var curEvent = EventType.UNKNOWN
        while (curEvent != EventType.EXIT) {
            input.handleEvent(curEvent)
            curEvent = input.read()
        }
    }
}
