package ru.hse.roguelike.ui.image

import com.googlecode.lanterna.TextColor
import ru.hse.roguelike.util.Position

abstract class Image {
    abstract val height: Int
    abstract val width: Int

    abstract fun getCrop(height: Int, width: Int, upperLeft: Position): Image

    abstract fun printText(text: String, position: Position, backgroundColor: TextColor = TextColor.ANSI.DEFAULT, foregroundColor: TextColor = TextColor.ANSI.DEFAULT)

    abstract fun markPosition(position: Position, backgroundColor: TextColor)

    abstract fun clear()

    abstract fun fill(backgroundColor: TextColor)
}