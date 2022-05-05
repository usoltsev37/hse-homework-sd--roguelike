package ru.hse.roguelike.controller.command

import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.model.items.ConsumableItem
import ru.hse.roguelike.model.items.EquipableItem

class EnterCommand(model: GameModel) : Command(model) {

    override fun innerExecute() {
        when (model.mode) {
            GameModel.Mode.INVENTORY -> processInventoryEnter()
            GameModel.Mode.GAME -> {}
        }
    }

    private fun processInventoryEnter() {
        val inventoryItems =
            model.hero.inventory.filter { it is ConsumableItem || (it is EquipableItem && !it.isEquiped) }

        if (model.selectedItemPosition != null) {
            val indexSelectedItem = model.transformPosition2Index(model.selectedItemPosition!!) - 6

            if (model.currentItemPosition.first == 0) {
                val currentItem =
                    model.hero.inventory.find { it is EquipableItem && it.isEquiped && it.itemType.value == model.currentItemPosition.second } as EquipableItem
                val selectedItem = inventoryItems[indexSelectedItem] as EquipableItem

                if (selectedItem.itemType.value == model.currentItemPosition.second) {
                    currentItem.unuse(model.hero)
                    selectedItem.use(model.hero)
                }
            }
            model.selectedItemPosition = null
        } else {
            val indexCurrentItem = model.transformPosition2Index(model.currentItemPosition) - 6

            if (indexCurrentItem < 0 || indexCurrentItem >= inventoryItems.size)
                return

            when (val item = inventoryItems[indexCurrentItem]) {
                is ConsumableItem -> {
                    item.use(model.hero)
                    return
                }
                is EquipableItem -> {
                    if (!model.hero.inventory.any { it is EquipableItem && it.itemType.value == item.itemType.value && it.isEquiped }) {
                        item.use(model.hero)
                        return
                    }
                }
            }

            model.selectedItemPosition = model.currentItemPosition
        }
    }
}