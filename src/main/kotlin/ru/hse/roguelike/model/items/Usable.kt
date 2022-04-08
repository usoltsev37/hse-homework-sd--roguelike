package ru.hse.roguelike.model.items


interface Usable {
    fun getHealthIncrease(): Int
    fun getStrengthIncrease(): Int
}
