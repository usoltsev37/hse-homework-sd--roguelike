package ru.hse.roguelike.ui.map

import com.googlecode.lanterna.TextColor
import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.ui.window.Window
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.getRoute

class MapViewImpl(
    private val window: Window
) : MapView {
    private val mapImage = window.getDefaultInstance()

    init {
        mapImage.fill(TextColor.ANSI.BLACK)
    }

    override fun setCell(cell: Cell) {
        for (i in 0 until cell.height) {
            for (j in 0..cell.width) {
                val cellImage = mapImage.getCrop(
                    cell.height,
                    cell.width,
                    with(cell) {
                        leftBottomPos.copy(
                            second = leftBottomPos.second + height
                        )
                    }
                )
                if (cell.visited) {
                    cellImage.fill(TextColor.ANSI.GREEN_BRIGHT)
                }
            }
        }

        val passages = cell.passages
        for (passage in passages) {
            if (passage.visited) {
                for (position in passage.getRoute()) {

                }
            }
        }
    }

    override fun setHeroPosition(position: Position) {
        mapImage.printText("H", position)
    }

    override fun show() {
        window.show(mapImage)
    }
}