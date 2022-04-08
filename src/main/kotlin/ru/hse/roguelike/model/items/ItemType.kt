package ru.hse.roguelike.model.items


interface ItemType {
    fun getHealthIncrease() : Int
    fun getStrengthIncrease() : Int
    fun getDescription() : String
}
