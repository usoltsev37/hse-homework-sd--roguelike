package ru.hse.roguelike

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import ru.hse.roguelike.controller.*
import ru.hse.roguelike.ui.hud.HudView
import ru.hse.roguelike.ui.hud.HudViewImpl
import ru.hse.roguelike.ui.inventory.InventoryView
import ru.hse.roguelike.ui.inventory.InventoryViewImpl
import ru.hse.roguelike.ui.map.MapView
import ru.hse.roguelike.ui.map.MapViewImpl
import ru.hse.roguelike.ui.window.WindowImpl
import ru.hse.roguelike.ui.window.Window
import ru.hse.roguelike.util.Constants

fun main() {
    val terminalFactory = DefaultTerminalFactory()
    terminalFactory.setInitialTerminalSize(TerminalSize(Constants.DEFAULT_MAP_WIDTH, Constants.DEFAULT_MAP_HEIGHT))
    val terminal = terminalFactory.createTerminal()

    val mainWindow: Window = WindowImpl(terminal)
    val mapView: MapView = MapViewImpl(mainWindow)
    val hudView: HudView = HudViewImpl(mainWindow)
    val inventoryView: InventoryView = InventoryViewImpl(mainWindow)

    val gameActivity = GameActivity()
    val viewActivity = ViewActivity(mapView, hudView, inventoryView)

    terminal.use {
        it.enterPrivateMode()

        val input = Input(it)
        val controller = Controller(gameActivity, viewActivity)

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
