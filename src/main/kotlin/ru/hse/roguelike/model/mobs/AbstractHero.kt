package ru.hse.roguelike.model.mobs

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.items.Item

@Serializable
abstract class AbstractHero: Mob() {

    abstract val inventory: MutableList<Item>
    abstract var armor: Int
}