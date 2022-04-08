package ru.hse.roguelike.model.items

import kotlinx.serialization.Serializable

@Serializable
class EquipableItem(override val name: String,
                    override val description: String,
                    override val type: ItemTypeImpl,
                    val healthIncrease: Int, val strengthIncrease: Int,
                    val isEquiped: Boolean = false) : Item()