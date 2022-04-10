package ru.hse.roguelike.model

import ru.hse.roguelike.exception.ModelLogicException
import ru.hse.roguelike.model.characters.Hero
import ru.hse.roguelike.model.characters.Enemy
import ru.hse.roguelike.model.map.Map
import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.model.characters.Movable
import ru.hse.roguelike.util.Constants
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.findCellByPoint
import java.util.Collections

/**
 * Game Model.
 * @param level current Level of the game.
 * @param hero main Hero.
 * @param currMap Map of the game.
 */
class GameModel(var level: Int, val hero: Hero, val currMap: Map = Map.createMap().build()) {

    var mode = Mode.GAME
    var currentItemPosition: Position = Position(1, 0)
        private set
    var selectedItemPosition: Position? = null

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
    fun transformPosition2Index(position: Position) : Int {
        return position.first * Constants.COUNT_COLUMNS + position.second
    }

    /**
     * Swap two items into Inventory array.
     */
    fun swapSelectedCurrentItems() {
        Collections.swap(hero.inventory,
            transformPosition2Index(selectedItemPosition!!),
            transformPosition2Index(currentItemPosition))
        selectedItemPosition = null
    }

    /**
     * for Game Mode.
     * Shift Hero Position one up.
     */
    fun moveHeroUp() { // TODO переход героя в другую cell
        if (hero.position.first + 1 < getCurrentCell().rightTopPos.first) {
            hero.position = with(hero.position) {
                copy(
                    first = first + 1
                )
            }
        }
    }

    private fun getCurrentCell(): Cell {
        return findCellByPoint(hero.position, currMap.cells) ?: throw ModelLogicException("Hero position is in the inconsistent state")
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
