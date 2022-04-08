package ru.hse.roguelike.ui.window

import com.googlecode.lanterna.terminal.Terminal
import ru.hse.roguelike.ui.Frame

class WindowImpl(
    terminal: Terminal
) : Window {
    private val textGraphics = terminal.newTextGraphics()
    private val height = terminal.terminalSize.rows
    private val width = terminal.terminalSize.columns

    override fun show(frame: Frame) {
        TODO("Not yet implemented")
    }
}