package ru.hse.roguelike.ui.inventory

import com.googlecode.lanterna.TextColor
import ru.hse.roguelike.model.items.EquipableItem
import ru.hse.roguelike.model.items.Item
import ru.hse.roguelike.ui.image.Image
import ru.hse.roguelike.ui.window.Window
import ru.hse.roguelike.util.Position
import ru.hse.roguelike.util.x
import ru.hse.roguelike.util.y

/**
 * Implementation inventory view via Lanterna
 * @param window Window for output inventory view
 */
class InventoryViewImpl(
    private val window: Window
) : InventoryView {
    private val image = window.getDefaultInstance()
    private val statsImage: Image = image.getCrop(3, image.width, Position(0, 0))
    private val equipmentImage: Image = image.getCrop(5, image.width, Position(0, statsImage.height))
    private val inventoryImage: Image

    private val slotWidth = (image.width - (UI_WIDTH + 1)) / UI_WIDTH

    private var inventoryItems: List<Item> = emptyList()
    private var equipedItems: List<EquipableItem> = emptyList()
    private var currentUiPosition = Position(2, 0)
    private var selectedUiPosition: Position? = null

    init {
        val inventoryImageHeight = image.height - statsImage.height - equipmentImage.height
        inventoryImage = image.getCrop(
            inventoryImageHeight,
            image.width,
            Position(0, image.height - inventoryImageHeight)
        )
    }

    init {
        init()
    }

    private fun init() {
        val horizontalLine = "-".repeat(image.width)
        val verticalSlotLine = "|".repeat(UI_WIDTH + 1).toList().joinToString(separator = " ".repeat(slotWidth))

        statsImage.printText("-".repeat(image.width), Position(0, 0))
        statsImage.printText("-".repeat(image.width), Position(0, 2))

        equipmentImage.printText(horizontalLine, Position(0, 0))
        equipmentImage.printText(
            EQUIPMENT_NAMES.joinToString(
                separator = "|",
                prefix = "|",
                postfix = "|"
            ) { it.take(slotWidth).padEnd(slotWidth) }, Position(0, 1)
        )
        equipmentImage.printText(horizontalLine, Position(0, 2))
        equipmentImage.printText(verticalSlotLine, Position(0, 3))
        equipmentImage.printText(horizontalLine, Position(0, 4))

        inventoryImage.printText(horizontalLine, Position(0, 0))
        inventoryImage.printText(
            ('|' + "Inventory".padStart((image.width + slotWidth) / 2)).padEnd(image.width - 2) + '|',
            Position(0, 1)
        )
        inventoryImage.printText(
            horizontalLine,
            Position(0, 2)
        )

        for (i in 3..inventoryImage.width step 2) {
            inventoryImage.printText(verticalSlotLine, Position(0, i))
            inventoryImage.printText(horizontalLine, Position(0, i + 1))
        }
    }

    override fun setInventory(items: List<Item>) {
        val result = items.partition { it is EquipableItem && it.isEquiped }
        equipedItems = result.first.map { it as EquipableItem }
        inventoryItems = result.second
    }

    override fun setCurrentPosition(position: Position) {
        currentUiPosition = position
        updateImageBySpecialPosition(currentUiPosition, TextColor.ANSI.BLUE)
    }

    override fun setSelectedPosition(position: Position?) {
        selectedUiPosition = position
        selectedUiPosition?.let { updateImageBySpecialPosition(it, TextColor.ANSI.MAGENTA) }
    }

    override fun setStats(level: Int, experience: Int, health: Int, strength: Int) {
        val statsText =
            "| Hero | Level = $level | Experience = $experience/228 | Health = $health | Strength = $strength |"
        statsImage.printText(statsText, Position(0, 1))
    }

    override fun show() {
        updateImage()
        window.show(image)
        image.clear()
        init()
    }

    private fun updateImage() {
        for (i in 0..UI_WIDTH) {
            equipedItems.firstOrNull { it.itemType.value == i }?.let let@{
                equipmentImage.printText(it.description.take(slotWidth), Position(1 + (slotWidth + 1) * i, 3))
            }
        }

        for ((index, item) in inventoryItems.withIndex()) {
            val row = index / UI_WIDTH
            val column = index % UI_WIDTH
            inventoryImage.printText(
                item.description.take(slotWidth),
                Position(1 + (slotWidth + 1) * column, 3 + 2 * row)
            )
        }

        updateImageBySpecialPosition(currentUiPosition, TextColor.ANSI.BLUE)
        selectedUiPosition?.let { updateImageBySpecialPosition(it, TextColor.ANSI.MAGENTA) }
    }

    private fun updateImageBySpecialPosition(position: Position, backgroundColor: TextColor) {
        when (position.x) {
            0 -> equipedItems.getOrNull(position.y)?.description?.take(slotWidth)?.let {
                equipmentImage.printText(
                    it,
                    Position(1 + (slotWidth + 1) * position.x, 3),
                    backgroundColor
                )
            } ?: equipmentImage.markPosition(
                Position(1 + (slotWidth + 1) * position.x, 3),
                backgroundColor
            )
            else -> inventoryItems.getOrNull(position.y + 6 * position.x)?.description?.take(slotWidth)?.let {
                inventoryImage.printText(
                    it,
                    Position(1 + (slotWidth + 1) * position.y, 3 + 2 * (position.x - 2)),
                    backgroundColor
                )
            } ?: inventoryImage.markPosition(
                Position(1 + (slotWidth + 1) * position.y, 3 + 2 * (position.x - 2)),
                backgroundColor
            )
        }
    }

    companion object {
        const val UI_WIDTH = 6
        val EQUIPMENT_NAMES = listOf(
            "Helmet",
            "Chestplate",
            "Leggings",
            "Boots",
            "Sword",
            "Bow"
        )
    }
}