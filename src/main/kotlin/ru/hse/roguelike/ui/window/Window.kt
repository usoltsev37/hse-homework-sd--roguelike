package ru.hse.roguelike.ui.window

import ru.hse.roguelike.ui.image.Image

/**
 * Abstraction to represent the entire game window.
 */
interface Window {

    /**
     * Show given image on the window.
     * @param image Given image
     */
    fun show(image: Image)

    /**
     * Create the main empty window-sized image and store them.
     */
    fun getDefaultInstance(): Image

    /**
     * Get the main image.
     * If it is not exists, method calls `getDefaultInstance`
     */
    fun getCurrentInstance(): Image
}