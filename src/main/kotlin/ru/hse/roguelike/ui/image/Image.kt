package ru.hse.roguelike.ui.image

import com.googlecode.lanterna.TextColor
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.TextType

abstract class Image {
    abstract val height: Int
    abstract val width: Int

    abstract fun getCrop(height: Int, width: Int, upperLeft: Position): Image

    abstract fun printText(text: String, position: Position, textType: TextType = TextType.USUAL)

    abstract fun printText(text: String, position: Position, backgroundColor: TextColor, foregroundColor: TextColor)

    abstract fun markPosition(position: Position, textType: TextType)

    abstract fun clear()

    abstract fun fill(backgroundColor: TextColor)
}