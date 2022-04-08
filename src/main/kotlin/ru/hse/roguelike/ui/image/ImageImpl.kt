package ru.hse.roguelike.ui.image

import com.googlecode.lanterna.SGR
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.graphics.TextImage
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.TextType
import ru.hse.roguelike.util.toLanternaTerminalPosition

class ImageImpl(
    private val textImage: TextImage,
    override val height: Int,
    override val width: Int,
    private val upperLeft: Position
) : TextImage by textImage, Image() {
    private val textGraphics = textImage.newTextGraphics()

    constructor(textImage: TextImage) : this(
        textImage = textImage,
        height = textImage.size.rows,
        width = textImage.size.columns,
        upperLeft = Position(TerminalPosition.TOP_LEFT_CORNER.row, TerminalPosition.TOP_LEFT_CORNER.column)
    )

    override fun getCrop(height: Int, width: Int, upperLeft: Position): Image {
        return ImageImpl(textImage, height, width, upperLeft)
    }

    override fun printText(text: String, position: Position, textType: TextType) {
        val previousColor = textGraphics.backgroundColor

        when (textType) {
            TextType.USUAL ->
                textGraphics.putString(
                    position.toLanternaTerminalPosition() + upperLeft.toLanternaTerminalPosition(),
                    text
                )
            TextType.CURRENT -> {
                textGraphics.backgroundColor = TextColor.RGB(0, 0, 255)

                textGraphics.putString(
                    position.toLanternaTerminalPosition() + upperLeft.toLanternaTerminalPosition(),
                    text
                )
            }
            TextType.SELECTED -> {
                textGraphics.backgroundColor = TextColor.RGB(0, 255, 0)

                textGraphics.putString(
                    position.toLanternaTerminalPosition() + upperLeft.toLanternaTerminalPosition(),
                    text
                )
            }
        }

        textGraphics.backgroundColor = previousColor
    }

    override fun printText(text: String, position: Position, backgroundColor: TextColor, foregroundColor: TextColor) {
        val filledArea = TextCharacter.fromString(text, foregroundColor, backgroundColor, SGR.BORDERED)
        for (i in text.indices) {
            textGraphics.fillRectangle(
                position.toLanternaTerminalPosition() + upperLeft.toLanternaTerminalPosition() + TerminalPosition(i, 0),
                TerminalSize(1, 1), filledArea[i]
            )
        }
    }

    override fun markPosition(position: Position, textType: TextType) {
        val char =
            textGraphics.getCharacter(position.toLanternaTerminalPosition() + upperLeft.toLanternaTerminalPosition()).character
        val previousColor = textGraphics.backgroundColor

        when (textType) {
            TextType.CURRENT -> {
                textGraphics.backgroundColor = TextColor.RGB(0, 0, 255)

                textGraphics.putString(
                    position.toLanternaTerminalPosition() + upperLeft.toLanternaTerminalPosition(),
                    char.toString()
                )
            }

            TextType.SELECTED -> {
                textGraphics.backgroundColor = TextColor.RGB(0, 255, 0)

                textGraphics.putString(
                    position.toLanternaTerminalPosition() + upperLeft.toLanternaTerminalPosition(),
                    char.toString()
                )
            }
        }

        textGraphics.backgroundColor = previousColor
    }

    override fun clear() {
        textGraphics.fill(' ')
    }

    override fun fill(backgroundColor: TextColor) {
        val filledArea = TextCharacter.fromCharacter(' ', backgroundColor, backgroundColor)[0]
        textGraphics.fillRectangle(upperLeft.toLanternaTerminalPosition(), TerminalSize(width, height), filledArea)
    }
}