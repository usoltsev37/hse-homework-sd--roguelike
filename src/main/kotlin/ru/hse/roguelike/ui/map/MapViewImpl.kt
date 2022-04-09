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
        val cellImage = mapImage.getCrop(
            cell.height,
            cell.width,
            upperLeft = cell.leftBottomPos
        )

        if (cell.visited) {
            cellImage.fill(TextColor.ANSI.GREEN_BRIGHT)
        }

        for (enemy in cell.enemies) {
            mapImage.printText("V", enemy.position)
        }

        for (item in cell.items) {
            mapImage.printText("$", item.second)
        }


        val passages = cell.passages
        for (passage in passages) {
            if (/*passage.visited*/true) {
                for (position in passage.route) {
                    if (passage.turnPosition != null) {
                        mapImage.printText(
                            " ",
                            passage.turnPosition!!,
                            TextColor.ANSI.RED_BRIGHT,
                            TextColor.ANSI.RED_BRIGHT
                        )
                    }
                    mapImage.printText(" ", position, TextColor.ANSI.BLUE_BRIGHT, TextColor.ANSI.BLUE_BRIGHT)
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