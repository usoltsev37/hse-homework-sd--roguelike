package ru.hse.roguelike.model

import ru.hse.roguelike.exception.ModelLogicException
import ru.hse.roguelike.model.characters.Enemy
import ru.hse.roguelike.model.characters.Hero
import ru.hse.roguelike.model.characters.Movable
import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.model.map.Map
import ru.hse.roguelike.util.*
import java.util.*

/**
 * Game Model.
 * @param level current Level of the game.
 * @param hero main Hero.
 * @param currMap Map of the game.
 */
class GameModel(var level: Int, val currMap: Map = Map.createMap().withHeight(24).withWidth(50).build()) {

    var mode = Mode.GAME
    var currentItemPosition: Position = Position(1, 0)
        private set
    var selectedItemPosition: Position? = null
    val hero: Hero = Hero(currMap.cells.first().leftBottomPos)
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
        currentItemPosition = with(currentItemPosition) {
            copy(
                first = first + 1
            )
        }
    }

    /**
     * Transform Position into Index of the Inventory array.
     * @param position Position
     */
    fun transformPosition2Index(position: Position): Int {
        return position.first * Constants.COUNT_COLUMNS + position.second
    }

    /**
     * Swap two items into Inventory array.
     */
    fun swapSelectedCurrentItems() {
        Collections.swap(
            hero.inventory,
            transformPosition2Index(selectedItemPosition!!),
            transformPosition2Index(currentItemPosition)
        )
        selectedItemPosition = null
    }

    /**
     * Сell on which the player is now.
     */
    fun getCurrentCell(): Cell {
        curCell = findCellByPoint(hero.position, currMap.cells) ?: curCell
        return curCell
    }

    /**
     * Attack one Movable by another Movable.
     * @param from Movable that attacks.
     * @param to Movable that receives damage.
     */
    fun strikeBlow(from: Movable, to: Movable) {
        from.attack(to)
    }

    /**
     * Return Enemy.
     * @param index index in Enemies array.
     */
    fun getEnemy(index: Int): Enemy {
        return getCurrentCell().enemies[index]
    }

    fun moveHero(newPos: Position) {
        if (canMove(newPos))
            hero.position = newPos
    }

    fun canMove(newPos: Position) = checkOnPassage(newPos) || newPos.isInCell(getCurrentCell())

    private fun checkOnPassage(pos: Position): Boolean {
        for (passage in curCell.passages) {
            if (pos == passage.from || passage.route.contains(pos)) {
                return true
            }
        }
        return false
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
