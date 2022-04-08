package ru.hse.roguelike.model.items

enum class ItemType(val value: Int) {
    Helmet(0),
    Chestplate(1),
    Leggings(2),
    Boots(3),
    Sword(4),
    Bow(5);

    companion object {
        fun getHealthIncrease(type: ItemType) : Int {
            when (type) {
                Helmet -> return 5
                Chestplate -> return 20
                Leggings -> return 15
                Boots -> return 5
                Sword -> return 0
                Bow -> return 0
                else -> throw IllegalArgumentException()
            }
        }

        fun getStrengthIncrease(type: ItemType) : Int {
            when (type) {
                Helmet -> return 0
                Chestplate -> return 0
                Leggings -> return 0
                Boots -> return 0
                Sword -> return 10
                Bow -> return 10
                else -> throw IllegalArgumentException()
            }
        }

        fun getDescription(type: ItemType) : String {
            return type.toString()
        }

        fun fromInt(value: Int) = values().first { it.value == value }
    }

}
