package ru.hse.roguelike.controller

import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.ui.hud.HudView
import ru.hse.roguelike.ui.inventory.InventoryView
import ru.hse.roguelike.ui.map.MapView

class ViewActivity(
    private val mapView: MapView,
    private val hudView: HudView,
    private val inventoryView: InventoryView
): Activity {

    override fun handleEvent(eventType: EventType, model: GameModel) {
        when (model.mode) {
            GameModel.Mode.GAME -> {
                if (eventType == EventType.INVENTORY) {

                    // поменять VIEW
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
                        inventoryView.setCurrentPosition(model.currentItem)
                    }
                    EventType.DOWN -> {
                        inventoryView.setCurrentPosition(model.currentItem)
                    }
                    EventType.LEFT -> {
                        inventoryView.setCurrentPosition(model.currentItem)
                    }
                    EventType.RIGHT -> {
                        inventoryView.setCurrentPosition(model.currentItem)
                    }
                    EventType.ENTER -> {
                        inventoryView.setSelectedPosition(model.selectedItem)
                    }
                }
                inventoryView.show()
//                TODO("Изменить курсор на View")
            }
        }
    }

    fun initState(model: GameModel) {
        model.curMap.cells.map { mapView.setCell(it)}
        mapView.setHeroPosition(model.curMap.cells.first().leftBottomPos)
        mapView.show()

        hudView.setStats(model.hero)
        hudView.show()
    }
}
