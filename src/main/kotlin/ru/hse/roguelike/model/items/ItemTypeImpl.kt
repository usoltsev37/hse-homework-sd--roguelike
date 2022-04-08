package ru.hse.roguelike.model.items

enum class ItemTypeImpl(val value: Int) : ItemType {
    Helmet(0) {
        override fun getHealthIncrease() : Int {
            return 5
        }

        override fun getStrengthIncrease(): Int {
            return 0
        }
    },
    Chestplate(1) {
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
    };

    override fun getDescription(): String {
        return this.toString()
    }

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}
