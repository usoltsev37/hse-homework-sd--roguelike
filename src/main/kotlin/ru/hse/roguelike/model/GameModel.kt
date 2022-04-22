package ru.hse.roguelike.model

import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.model.map.Map
import ru.hse.roguelike.model.mobs.AbstractHero
import ru.hse.roguelike.model.mobs.Hero
import ru.hse.roguelike.model.mobs.HeroDecorator
import ru.hse.roguelike.model.mobs.enemies.Enemy
import ru.hse.roguelike.util.*
import java.util.*

/**
 * Game Model.
 * @param level current Level of the game.
 * @param hero main Hero.
 * @param currMap Map of the game.
 */
class GameModel(val currMap: Map) {

    var mode = Mode.GAME
    var currentItemPosition: Position = Position(1, 0)
        private set
    var selectedItemPosition: Position? = null
    val hero: AbstractHero = HeroDecorator(Hero(currMap.cells.first().leftBottomPos))
    var curCell: Cell = getCurrentCell()

    /**
     * for Inventory Mode.
     * Shift currentItemPosition one to the left.
     */
    fun currentItemMoveLeft() {
        if (currentItemPosition.second > 0) {
            currentItemPosition = with(currentItemPosition) {
                copy(
                    second = second - 1
                )
            }
        }
    }

    /**
     * for Inventory Mode.
     * Shift currentItemPosition one to the right.
     */
    fun currentItemMoveRight() {
        if (currentItemPosition.second < Constants.COUNT_COLUMNS - 1) {
            currentItemPosition = with(currentItemPosition) {
                copy(
                    second = second + 1
                )
            }
        }
    }

    /**
     * for Inventory Mode.
     * Shift currentItemPosition one up.
     */
    fun currentItemMoveUp() {
        if (currentItemPosition.first > 0) {
            currentItemPosition = with(currentItemPosition) {
                copy(
                    first = first - 1
                )
            }
        }
    }

    /**
     * for Inventory Mode.
     * Shift currentItemPosition one down.
     */
    fun currentItemMoveDown() { // TODO bottom line should be limited
        if (currentItemPosition.first < 7) {
            currentItemPosition = with(currentItemPosition) {
                copy(
                    first = first + 1
                )
            }
        }
    }

    /**
     * Transform Position into Index of the Inventory array.
     * @param position Position
     */
    fun transformPosition2Index(position: Position): Int {
        return position.first * Constants.COUNT_COLUMNS + position.second
    }

    fun moveHero(newPos: Position) {
        if (canMove(newPos))
            hero.position = newPos
    }
    
    fun moveEnemy(enemy: Enemy) {
        val newPos = enemy.getNextPosition(hero.position)
        if (canMove(newPos)) {
            enemy.position = newPos
        }
    }

    fun updateCellsState() {
        currMap.cells.forEach { cell ->
            cell.enemies.removeIf {
                val newCell = findCellByPoint(it.position, currMap.cells)
                if (newCell != null && newCell != cell) {
                    newCell.enemies.add(it)
                }
                newCell != null && newCell != cell || it.isDead
            }
        }
        //TODO: items
    }

    fun updatePassagesState() {
        currMap.cells.flatMap { it.passages }.filter { it.route.subList(1, it.route.lastIndex).contains(hero.position) }
            .forEach { it.visited = true }
    }

    private fun canMove(newPos: Position) = checkOnPassage(newPos) || newPos.isInCell(getCurrentCell())

    private fun checkOnPassage(pos: Position): Boolean {
        for (passage in curCell.passages) {
            if (passage.route.contains(pos)) {
                return true
            }
        }
        return false
    }

    private fun getCurrentCell(): Cell {
        curCell = findCellByPoint(hero.position, currMap.cells) ?: curCell
        return curCell
    }

    fun updateCurrentCell() {
        curCell = getCurrentCell()
    }

    /**
     * State of the Game.
     * GAME if the main Game window is shown on the screen.
     * INVENTORY if the main Inventory window is shown on the screen.
     */
    enum class Mode {
        GAME,
        INVENTORY
    }

}
