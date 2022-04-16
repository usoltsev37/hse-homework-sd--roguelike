package ru.hse.roguelike.ui.map

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor
import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.model.mobs.enemies.AggressiveEnemy
import ru.hse.roguelike.model.mobs.enemies.CowardEnemy
import ru.hse.roguelike.model.mobs.enemies.PassiveEnemy
import ru.hse.roguelike.ui.image.Image
import ru.hse.roguelike.ui.window.Window
import ru.hse.roguelike.util.Constants.HUD_WIDTH
import ru.hse.roguelike.util.Position

/**
 * Implementation map view via Lanterna
 * @param window Window for output map view
 */
class MapViewImpl(
    private val window: Window,
    override var heroPos: Position
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

        if (!cell.visited)
            return

        cellImage.fill(TextColor.ANSI.GREEN_BRIGHT)

        cell.items.forEach { item ->
            mapImage.printText("$", item.second)
        }

        cell.passages.forEach { passage ->
            mapImage.printText(" ", passage.route[1], TextColor.ANSI.BLUE_BRIGHT, TextColor.ANSI.BLACK)
            if (passage.visited) {
                passage.route.subList(1, passage.route.lastIndex).forEach { position ->
                    mapImage.printText(" ", position, TextColor.ANSI.BLUE_BRIGHT, TextColor.ANSI.BLACK)
                }
            }
        }

        cell.enemies.forEach { enemy ->
            mapImage.printText(enemy.name, enemy.position, if (enemy.confused) TextColor.ANSI.MAGENTA else TextColor.ANSI.RED)
        }
    }

    override fun setHeroPosition(position: Position, prevPosition: Position?) {
        prevPosition?.let { pos ->
            prevChar?.let {
                mapImage.printText(it.characterString, pos, it.backgroundColor, it.foregroundColor)
            }
        }
        prevChar = mapImage.getCharacterAt(position)
        mapImage.printText("H", position, TextColor.ANSI.BLACK, TextColor.ANSI.WHITE)
        heroPos = position
    }

    override fun show() {
        window.show(mapImage)
    }
}