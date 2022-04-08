package ru.hse.roguelike.ui.hud

import ru.hse.roguelike.ui.View

interface HudView : View {

    fun setStats(/* TODO some stats */)
    fun setFastInventory(/* TODO fastInventory: List<Item> */)
}