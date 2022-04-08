package ru.hse.roguelike.model.items

import kotlinx.serialization.Serializable

@Serializable
enum class ItemType(val value: Int) : Usable {
    Helmet(0) {
        override fun getHealthIncrease(): Int {
            return 5
        }

        override fun getStrengthIncrease(): Int {
            return 0
        }
    },
    ChestPlate(1) {
        override fun getHealthIncrease(): Int {
            return 20
        }

        override fun getStrengthIncrease(): Int {
            return 0
        }
    },
    Leggings(2) {
        override fun getHealthIncrease(): Int {
            return 15
        }

        override fun getStrengthIncrease(): Int {
            return 0
        }
    },
    Boots(3) {
        override fun getHealthIncrease(): Int {
            return 5
        }

        override fun getStrengthIncrease(): Int {
            return 0
        }
    },
    Sword(4) {
        override fun getHealthIncrease(): Int {
            return 0
        }

        override fun getStrengthIncrease(): Int {
            return 20
        }
    },
    Potion(5) {
        override fun getHealthIncrease(): Int {
            return 0
        }

        override fun getStrengthIncrease(): Int {
            return 0
        }
    };

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}
