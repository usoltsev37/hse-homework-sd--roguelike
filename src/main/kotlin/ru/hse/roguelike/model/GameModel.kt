package ru.hse.roguelike.model
import ru.hse.roguelike.model.characters.Hero
import ru.hse.roguelike.model.characters.Enemy
import ru.hse.roguelike.model.map.Map
import ru.hse.roguelike.model.map.Cell
import ru.hse.roguelike.model.characters.Movable

class GameModel(var level: Int, val hero: Hero, val curMap: Map = Map.createMap().build()) {

    var mode = Mode.GAME

    // TODO Calculate Cell Index from hero position - Cell First Ever Rotates
    private fun getCurrentCell() : Cell {
        return curMap.cells[0]
    }

    fun strikeBlow(from: Movable, to: Movable) {
        from.attack(to)
    }

    fun getEnemy(index: Int) : Enemy {
        return getCurrentCell().enemies[index]
    }

    enum class Mode {
        GAME,
        INVENTORY
    }

}
