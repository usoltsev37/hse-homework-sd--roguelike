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
        when (model.mode) {
            GameModel.Mode.GAME -> {
                val curPos = model.hero.position
                val prevPos = when (eventType) {
                    EventType.UP -> curPos.lower()
                    EventType.DOWN -> curPos.upper()
                    EventType.LEFT -> curPos.right()
                    EventType.RIGHT -> curPos.left()
                    else -> return
                }
                if (curPos != mapView.heroPos) {
                    mapView.setHeroPosition(curPos, prevPos)
                }
            }
            GameModel.Mode.INVENTORY -> {
                when (eventType) {
                    EventType.INVENTORY -> {
                        inventoryView.setInventory(model.hero.inventory)
                        inventoryView.setStats(0, 0, model.hero.health, model.hero.strength)
                        //TODO()
                    } // поменять VIEW
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
                    }
                    else -> {}
                }
                inventoryView.show()
//                TODO("Изменить курсор на View")
            }
        }
    }

    /**
     * Init state of the ViewActivity.
     * @param model the Game Model.
     */
    fun initState(model: GameModel) {
        model.currMap.cells.map { mapView.setCell(it) }
        mapView.setHeroPosition(model.currMap.cells.first().leftBottomPos)
        mapView.show()

        hudView.setStats(model.hero)
        hudView.show()
    }
}
