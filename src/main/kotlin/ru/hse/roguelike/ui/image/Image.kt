package ru.hse.roguelike.ui.image

import com.googlecode.lanterna.TextColor
import ru.hse.roguelike.util.Position

/**
 * Rectangle abstraction for a component part of views.
 */
abstract class Image {

    /**
     * Height of image in units of measurement of the length of the framework used.
     */
    abstract val height: Int

    /**
     * Width of image in units of measurement of the length of the framework used.
     */
    abstract val width: Int

    /**
     * Get crop of current image.
     * @param height Crop height
     * @param width Crop width
     * @param upperLeft Position of upper-left corner of the crop
     */
    abstract fun getCrop(height: Int, width: Int, upperLeft: Position): Image

    /**
     * Print given text on the image.
     * @param text Given text to output
     * @param position Position at the image to start printing
     * @param backgroundColor Color of background for printing
     * @param foregroundColor Color of foreground for printing
     */
    abstract fun printText(
        text: String,
        position: Position,
        backgroundColor: TextColor = TextColor.ANSI.DEFAULT,
        foregroundColor: TextColor = TextColor.ANSI.DEFAULT
    )

    /**
     * Fill position with some color.
     * @param position Position at the image for filling
     * @param backgroundColor Color of background for filling
     */
    abstract fun markPosition(position: Position, backgroundColor: TextColor)

    /**
     * Clear image from text and colors.
     */
    abstract fun clear()

    /**
     * Fill the image with given color.
     * @param backgroundColor Color to fill
     */
    abstract fun fill(backgroundColor: TextColor)
}