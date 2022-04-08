package ru.hse.roguelike.model.items

import kotlinx.serialization.Serializable

@Serializable
class ConsumableItem(override val name: String,
                     override val description: String,
                     override val itemType: ItemType,
                     val healthAmount: Int) : Item()