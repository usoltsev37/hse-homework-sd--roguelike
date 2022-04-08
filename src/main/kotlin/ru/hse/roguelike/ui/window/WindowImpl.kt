package ru.hse.roguelike.ui.window

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.graphics.BasicTextImage
import com.googlecode.lanterna.terminal.Terminal
import ru.hse.roguelike.ui.image.Image
import ru.hse.roguelike.ui.image.ImageImpl

class WindowImpl(
    private val terminal: Terminal
) : Window {
    private val textGraphics = terminal.newTextGraphics()
    private val height = terminal.terminalSize.rows
    private val width = terminal.terminalSize.columns

    override fun show(image: Image) {
        textGraphics.drawImage(TerminalPosition.TOP_LEFT_CORNER, image as ImageImpl)
        terminal.flush()
    }

    override fun getDefaultInstance(): Image = ImageImpl(
        BasicTextImage(
            TerminalSize(width, height),
            TextCharacter.DEFAULT_CHARACTER
        )
    )
}