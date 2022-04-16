package ru.hse.roguelike.ui.hud

import com.googlecode.lanterna.TextColor
import ru.hse.roguelike.model.mobs.AbstractHero
import ru.hse.roguelike.model.mobs.Hero
import ru.hse.roguelike.ui.image.Image
import ru.hse.roguelike.ui.window.Window
import ru.hse.roguelike.util.Constants.HUD_WIDTH
import ru.hse.roguelike.util.Position

/**
 * Implementation HUD view via Lanterna
 * @param window Window for output HUD view
 */
class HudViewImpl(
    private val window: Window
) : HudView {
    private val hudImage: Image

    private lateinit var hero: AbstractHero

    init {
        val fullImage = window.getCurrentInstance()
        hudImage = fullImage.getCrop(fullImage.height, HUD_WIDTH, Position(0, 0))
        hudImage.fill(TextColor.ANSI.BLACK_BRIGHT)
    }

    override fun setStats(hero: AbstractHero) {
        this.hero = hero
    }

    override fun show() {
        updateImage()
        window.show(hudImage)
    }

    private fun updateImage() {
        val heroInfo = """
            
            Health: ${hero.health} / 50
            
            Strenght: ${hero.strength}
            
            Armour: ${hero.armor}
            
            Exp: ${hero.xp} / ${hero.currMaxXp}
            
            Level: ${hero.level}
            
        """.trimIndent()

        hudImage.printText(heroInfo, Position(0, 0), TextColor.ANSI.BLACK_BRIGHT, TextColor.ANSI.CYAN)
    }
}