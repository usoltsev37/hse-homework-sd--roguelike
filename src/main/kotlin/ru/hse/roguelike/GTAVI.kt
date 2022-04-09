package ru.hse.roguelike

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import ru.hse.roguelike.controller.Controller
import ru.hse.roguelike.controller.Input
import ru.hse.roguelike.controller.EventType
import ru.hse.roguelike.controller.EventInterface
import ru.hse.roguelike.controller.SpecialEventType
import ru.hse.roguelike.util.Constants

fun main() {
    val terminalFactory = DefaultTerminalFactory()
    terminalFactory.setInitialTerminalSize(TerminalSize(Constants.DEFAULT_MAP_WIDTH, Constants.DEFAULT_MAP_HEIGHT))
    val terminal = terminalFactory.createTerminal()
    terminal.use {
        it.enterPrivateMode()

        val input = Input(it)
        val controller = Controller()

        var curEvent: EventInterface = input.read()

        while (curEvent != SpecialEventType.EXIT) {
            controller.update(curEvent as EventType)
            curEvent = input.read()
            while (curEvent == SpecialEventType.UNKNOWN) {
                curEvent = input.read()
            }
        }
    }
}
