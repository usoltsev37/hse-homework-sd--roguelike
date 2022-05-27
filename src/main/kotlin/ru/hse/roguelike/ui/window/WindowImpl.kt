package ru.hse.roguelike.ui.window

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.graphics.BasicTextImage
import com.googlecode.lanterna.terminal.Terminal
import ru.hse.roguelike.ui.image.Image
import ru.hse.roguelike.ui.image.ImageImpl

/**
 * Window implementation via Lanterna
 * @param terminal Lanterna's terminal implementation
 */
class WindowImpl(
    private val terminal: Terminal
) : Window {
    private val textGraphics = terminal.newTextGraphics()
    private val height = terminal.terminalSize.rows
    private val width = terminal.terminalSize.columns

    private lateinit var currentImage: Image

    override fun show(image: Image) {
        textGraphics.drawImage(TerminalPosition.TOP_LEFT_CORNER, image as ImageImpl)
        terminal.flush()
    }

    override fun getDefaultInstance(): Image {
        currentImage = ImageImpl(
            BasicTextImage(
                TerminalSize(width, height),
                TextCharacter.DEFAULT_CHARACTER
            )
        )

        return currentImage
    }

    override fun getCurrentInstance(): Image =
        if (!this::currentImage.isInitialized)
            getDefaultInstance()
        else
            currentImage

    override fun clear() {
        terminal.clearScreen()
    }
}