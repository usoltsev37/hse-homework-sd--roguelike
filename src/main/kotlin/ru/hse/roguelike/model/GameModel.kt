package ru.hse.roguelike.model

import ru.hse.roguelike.model.characters.Hero
import ru.hse.roguelike.model.characters.Enemy
import ru.hse.roguelike.model.map.Map
import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.model.characters.Movable
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.Position
import java.util.Collections

class GameModel(var level: Int, val hero: Hero, val curMap: Map = Map.createMap().build()) {

    var mode = Mode.GAME
    var currentItemPosition: Position = Position(1, 0)
        private set
    var selectedItemPosition: Position? = null

    fun currentItemMoveLeft() {
        if (currentItemPosition.second > 0) {
            currentItemPosition = with(currentItemPosition) {
                copy(
                    second = second - 1
                )
            }
        }
    }

    fun currentItemMoveRight() {
        if (currentItemPosition.second < Constants.COUNT_COLUMNS - 1) {
            currentItemPosition = with(currentItemPosition) {
                copy(
                    second = second + 1
                )
            }
        }
    }

    fun currentItemMoveUp() {
        if (currentItemPosition.first > 0) {
            currentItemPosition = with(currentItemPosition) {
                copy(
                    first = first - 1
                )
            }
        }
    }

    fun currentItemMoveDown() { // TODO bottom line should be limited
        currentItemPosition = with(currentItemPosition) {
            copy(
                first = first + 1
            )
        }
        println("kek")
    }

    fun transformPosition2Index(position: Position) : Int {
        return position.first * Constants.COUNT_COLUMNS + position.second
    }

    fun swapSelectedCurrentItems() {
        Collections.swap(hero.inventory,
            transformPosition2Index(selectedItemPosition!!),
            transformPosition2Index(currentItemPosition))
        selectedItemPosition = null
    }

    // TODO Calculate Cell Index from hero position - Cell First Ever Rotates
    private fun getCurrentCell(): Cell {
        return curMap.cells[0]
    }

    fun strikeBlow(from: Movable, to: Movable) {
        from.attack(to)
    }

    fun getEnemy(index: Int): Enemy {
        return getCurrentCell().enemies[index]
    }

    enum class Mode {
        GAME,
        INVENTORY
    }

}
