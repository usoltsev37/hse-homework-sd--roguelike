package ru.hse.roguelike.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.hse.roguelike.util.*


internal class GameModelTest {

    @Test
    fun currentItemMoveLeft() {
        val model = GameModel()

        model.currentItemMoveLeft()
        Assertions.assertEquals(model.currentItemPosition, Position(1, 0))
        model.currentItemMoveLeft()
        Assertions.assertEquals(model.currentItemPosition, Position(1, 0))
    }

    @Test
    fun currentItemMoveRight() {
        val model = GameModel()

        for (i in 1 until Constants.COUNT_COLUMNS) {
            model.currentItemMoveRight()
            Assertions.assertEquals(model.currentItemPosition, Position(1, i))
        }

        model.currentItemMoveRight()
        Assertions.assertEquals(model.currentItemPosition, Position(1, 5))
        model.currentItemMoveRight()
        Assertions.assertEquals(model.currentItemPosition, Position(1, 5))
    }

    @Test
    fun currentItemMoveUp() {
        val model = GameModel()

        model.currentItemMoveUp()
        Assertions.assertEquals(model.currentItemPosition, Position(0, 0))
        model.currentItemMoveUp()
        Assertions.assertEquals(model.currentItemPosition, Position(0, 0))
    }

    @Test
    fun currentItemMoveDown() {
        val model = GameModel()

        for (i in 2 .. 6) {
            model.currentItemMoveDown()
            Assertions.assertEquals(model.currentItemPosition, Position(i, 0))
        }

        model.currentItemMoveDown()
        Assertions.assertEquals(model.currentItemPosition, Position(7, 0))
    }

    @Test
    fun moveHero() {
        repeat(100) {
            val model = GameModel()
            val heroPosition = model.hero.position

            model.moveHero(heroPosition.left())
            Assertions.assertEquals(heroPosition, model.hero.position)
        }
    }
}