package ru.hse.roguelike.ui.window

import ru.hse.roguelike.ui.image.Image

interface Window {
    fun show(image: Image)
    fun getDefaultInstance(): Image
}