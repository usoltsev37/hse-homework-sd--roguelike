package ru.hse.roguelike.model.items

import kotlinx.serialization.Serializable
import ru.hse.roguelike.model.characters.Hero

@Serializable
class EquipableItem(override val name: String,
                    override val description: String,
                    override val itemType: ItemType,
                    val healthIncrease: Int, val strengthIncrease: Int,
                    val isEquiped: Boolean = false) : Item()