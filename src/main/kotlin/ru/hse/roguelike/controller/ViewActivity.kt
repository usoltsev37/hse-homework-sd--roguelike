package ru.hse.roguelike.controller

import ru.hse.roguelike.EventType
import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.ui.hud.HudView
import ru.hse.roguelike.ui.hud.HudViewImpl
import ru.hse.roguelike.ui.inventory.InventoryView
import ru.hse.roguelike.ui.inventory.InventoryViewImpl
import ru.hse.roguelike.ui.map.MapView
import ru.hse.roguelike.ui.map.MapViewImpl
import ru.hse.roguelike.ui.window.Window
import ru.hse.roguelike.util.left
import ru.hse.roguelike.util.lower
import ru.hse.roguelike.util.right
import ru.hse.roguelike.util.upper

/**
 * Activity implementation responsible for the View.
 */
class ViewActivity(window: Window, private val model: GameModel) : Activity {

    private val mapView: MapView = MapViewImpl(window, model.hero.position)
    private val hudView: HudView = HudViewImpl(window)
    private val inventoryView: InventoryView = InventoryViewImpl(window)

    override fun handleEvent(eventType: EventType) {
        if (model.mode == GameModel.Mode.GAME) {
            handleEventInGameMode(eventType)
        } else if (model.mode == GameModel.Mode.INVENTORY) {
            handleEventInInventoryMode(eventType)
        }
    }


    private fun handleEventInGameMode(eventType: EventType) {
        when (eventType) {
            EventType.USE -> {
                val itemIndex = model.curCell.items.map { it.second }.indexOf(model.hero.position)
                if (itemIndex != -1) {
                    model.curCell.items.removeAt(itemIndex) // TODO: move action in GameActivity
                    hudView.setMessage(
                        "You found:\n" + model.hero.inventory.last().description
                    )
                }
            }
            else -> {}
        }

        val curPos = model.hero.position
        val prevPos = when (eventType) {
            EventType.UP -> curPos.lower()
            EventType.DOWN -> curPos.upper()
            EventType.LEFT -> curPos.right()
            EventType.RIGHT -> curPos.left()
            else -> curPos
        }

        model.currMap.cells.forEach { mapView.setCell(it) }

        if (curPos != mapView.heroPos) {
            mapView.setHeroPosition(curPos, prevPos)
        } else {
            mapView.setHeroPosition(curPos)
        }
        mapView.show()

        hudView.setStats(model.hero)
        hudView.show()
    }

    private fun handleEventInInventoryMode(eventType: EventType) {
        when (eventType) {
            EventType.INVENTORY -> {
                inventoryView.setInventory(model.hero.inventory)
                inventoryView.setStats(0, 0, model.hero.health, model.hero.strength)
            }
            EventType.UP -> {
                inventoryView.setCurrentPosition(model.currentItemPosition)
            }
            EventType.DOWN -> {
                inventoryView.setCurrentPosition(model.currentItemPosition)
            }
            EventType.LEFT -> {
                inventoryView.setCurrentPosition(model.currentItemPosition)
            }
            EventType.RIGHT -> {
                inventoryView.setCurrentPosition(model.currentItemPosition)
            }
            EventType.ENTER -> {
                inventoryView.setSelectedPosition(model.selectedItemPosition)
                inventoryView.setInventory(model.hero.inventory)
            }
            else -> {}
        }
        inventoryView.show()
    }


    /**
     * Init state of the ViewActivity.
     * @param model the Game Model.
     */
    fun initState(model: GameModel) {
        val initialCell = model.currMap.cells.first()
        initialCell.visited = true

        model.currMap.cells.forEach { mapView.setCell(it) }
        mapView.setHeroPosition(model.hero.position)
        mapView.show()

        hudView.setStats(model.hero)
        hudView.show()
    }
}
