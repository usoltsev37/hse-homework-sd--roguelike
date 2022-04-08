package ru.hse.roguelike
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import ru.hse.roguelike.controller.Input
import ru.hse.roguelike.ui.hud.HudViewImpl
import ru.hse.roguelike.ui.inventory.InventoryViewImpl
import ru.hse.roguelike.ui.map.MapViewImpl
import ru.hse.roguelike.ui.window.WindowImpl
import ru.hse.roguelike.util.Constants

fun main(args: Array<String>) {
    val terminalFactory = DefaultTerminalFactory()
    terminalFactory.setInitialTerminalSize(TerminalSize(Constants.DEFAULT_MAP_WIDTH, Constants.DEFAULT_MAP_HEIGHT))
    val terminal = terminalFactory.createTerminal()
    terminal.use { terminal ->
        terminal.enterPrivateMode()
        val window = WindowImpl(terminal)
        val mapView = MapViewImpl(window)
        val inventoryView = InventoryViewImpl(window)
        val hudView = HudViewImpl(window)

        val input = Input(terminal)

        while (true) {
            input.read()
        }

    }
    terminal.close()
    println("Hello World!")
}