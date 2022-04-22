package ru.hse.roguelike

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import ru.hse.roguelike.controller.Controller
import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.ui.menu.MenuViewImpl
import ru.hse.roguelike.ui.window.Window
import ru.hse.roguelike.ui.window.WindowImpl
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.model.map.Map as GameMap

/**
 * Point of Entry to the Game.
 */
fun main() {
    val terminalFactory = DefaultTerminalFactory()
    terminalFactory.setInitialTerminalSize(TerminalSize(Constants.DEFAULT_MAP_WIDTH, Constants.DEFAULT_MAP_HEIGHT))
    val terminal = terminalFactory.createTerminal()

    val mainWindow: Window = WindowImpl(terminal)
    val mapBuilder = GameMap.createMap()

    val menu = MenuViewImpl(terminal, mapBuilder)
    menu.draw()
    menu.show()

    val model = GameModel(mapBuilder.build())

    terminal.use {
        it.enterPrivateMode()

        val input = Input(it)
        val controller = Controller(mainWindow, model)

        var curEvent: EventInterface = input.read()

        while (curEvent != SpecialEventType.EXIT) {
            controller.update(curEvent as EventType)
            if (controller.isEndGame) {
                return
            }
            curEvent = input.read()
            while (curEvent == SpecialEventType.UNKNOWN) {
                curEvent = input.read()
            }
        }
    }
}
