package ru.hse.roguelike.controller.command

import ru.hse.roguelike.model.GameModel
import ru.hse.roguelike.model.items.ConsumableItem

class UseCommand(model: GameModel) : Command(model) {

    override fun innerExecute() {
        when (model.mode) {
            GameModel.Mode.INVENTORY -> {
                if (model.selectedItemPosition != null) {
                    val selectedItem =
                        model.hero.inventory[model.transformPosition2Index(model.selectedItemPosition!!)]
                    if (selectedItem is ConsumableItem) {
                        selectedItem.use(model.hero)
                    }
                }
            }
            GameModel.Mode.GAME -> {
                model.curCell.items.firstOrNull { it.second == model.hero.position }?.first?.let {
                    model.hero.inventory.add(
                        it
                    )
                }
            }
        }
    }
}