package ru.hse.roguelike.ui

import com.googlecode.lanterna.graphics.TextImage

class Frame(private val textImage: TextImage) : TextImage by textImage {
}