package ru.hse.roguelike.ui.map

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor
import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.ui.image.Image
import ru.hse.roguelike.ui.window.Window
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.Constants.HUD_WIDTH

/**
 * Implementation map view via Lanterna
 * @param window Window for output map view
 */
class MapViewImpl(
    private val window: Window,
    private var heroPos: Position
) : MapView {
    private val mapImage: Image

    init {
        val fullImage = window.getCurrentInstance()
        mapImage = fullImage.getCrop(fullImage.height, fullImage.width - HUD_WIDTH, Position(HUD_WIDTH, 0))
        mapImage.fill(TextColor.ANSI.WHITE)
    }

    var prevChar: TextCharacter? = null

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
            if (/*passage.visited*/ true) {
                if (passage.route.size == 1)
                    continue
                for (position in passage.route.subList(1, passage.route.lastIndex)) {
                    mapImage.printText(" ", position, TextColor.ANSI.BLUE_BRIGHT, TextColor.ANSI.BLACK)
                }
            }
        }
    }

    override fun setHeroPosition(position: Position, prevPosition: Position?) {
        if (position == heroPos) {
            return
        }
        prevPosition?.let { pos ->
            prevChar?.let {
                mapImage.printText(it.characterString, pos, it.backgroundColor, it.foregroundColor)
            }
            prevChar = mapImage.getCharacterAt(position)
        }
        mapImage.printText("H", position, TextColor.ANSI.BLACK, TextColor.ANSI.WHITE)
        heroPos = position
        show()
    }

    override fun show() {
        window.show(mapImage)
    }
}