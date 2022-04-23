package ru.hse.roguelike.ui.menu

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.gui2.*
import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder
import com.googlecode.lanterna.gui2.dialogs.MessageDialog
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton
import com.googlecode.lanterna.gui2.menu.Menu
import com.googlecode.lanterna.gui2.menu.MenuBar
import com.googlecode.lanterna.gui2.menu.MenuItem
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.Terminal
import ru.hse.roguelike.model.mobs.enemies.factories.DefaultEnemyFactory
import ru.hse.roguelike.model.mobs.enemies.factories.FastEnemyFactory
import ru.hse.roguelike.model.mobs.enemies.factories.PowerfulEnemyFactory
import ru.hse.roguelike.model.mobs.enemies.factories.TankEnemyFactory
import ru.hse.roguelike.util.Constants
import java.io.File
import java.util.regex.Pattern
import kotlin.system.exitProcess
import ru.hse.roguelike.model.map.Map as GameMap


/**
 * MenuView implementation via Lanterna.
 * @param terminal Lanterna's terminal implementation
 * @param mapBuilder Game map builder, it will collect map configuration
 */
class MenuViewImpl(
    terminal: Terminal,
    private val mapBuilder: GameMap.Builder
) : MenuView {
    private val textGUI: MultiWindowTextGUI
    private val window: Window

    init {
        val screen: Screen = TerminalScreen(terminal)
        screen.startScreen()
        textGUI = MultiWindowTextGUI(screen, DefaultWindowManager(), EmptySpace(TextColor.ANSI.BLUE))
        window = BasicWindow()
    }

    override fun draw() {
        val menubar = MenuBar()

        val menuFile = Menu("Game")
        menuFile.add(
            MenuItem(
                "New game..."
            ) {
                val panel = Panel()
                menubar.isVisible = false
                window.component = panel

                panel.layoutManager = GridLayout(2)

                panel.addComponent(Label("Height "))
                val heightText = TextBox()
                    .setValidationPattern(Pattern.compile("[0-9]*"))
                    .addTo(panel)

                panel.addComponent(Label("Width "))
                val widthText = TextBox()
                    .setValidationPattern(Pattern.compile("[0-9]*"))
                    .addTo(panel)

                panel.addComponent(Label("Mob type "))
                val operations = ComboBox<String>()
                operations.addItem("DEFAULT")
                operations.addItem("FAST")
                operations.addItem("POWERFUL")
                operations.addItem("TANK")
                panel.addComponent(operations)

                panel.addComponent(EmptySpace(TerminalSize(0, 0)))
                Button("Start") {
                    val height = heightText.text.toIntOrNull()
                    val width = widthText.text.toIntOrNull()
                    val enemyFactory = when (operations.selectedItem) {
                        "FAST" -> FastEnemyFactory()
                        "POWERFUL" -> PowerfulEnemyFactory()
                        "TANK" -> TankEnemyFactory()
                        else -> DefaultEnemyFactory()
                    }

                    if (height == null || width == null) {
                        MessageDialog.showMessageDialog(
                            textGUI,
                            "!!!",
                            """Height and width fields cannot be empty!""", MessageDialogButton.OK
                        )

                        return@Button
                    }

                    if (height > Constants.MAX_MAP_HEIGHT || width > Constants.MAX_MAP_WIDTH) {
                        MessageDialog.showMessageDialog(
                            textGUI,
                            "!!!",
                            """
                                Please check arguments:
                                max height = ${Constants.DEFAULT_MAP_HEIGHT}
                                max width = ${Constants.DEFAULT_MAP_WIDTH}
                            """.trimIndent(), MessageDialogButton.OK
                        )

                        return@Button
                    }

                    mapBuilder.withHeight(height).withWidth(width).withEnemyFactory(enemyFactory)
                    window.close()
                }.addTo(panel)

                panel.addComponent(EmptySpace(TerminalSize(0, 0)))
                Button("Cancel") {
                    window.component = menubar
                    menubar.isVisible = true
                }.addTo(panel)

            }
        )
        menuFile.add(
            MenuItem(
                "Load game..."
            ) {
                val file: File? = FileDialogBuilder().build().showDialog(textGUI)
                if (file != null) {
                    val mes = MessageDialog.showMessageDialog(
                        textGUI,
                        "Open",
                        """
                            Map: $file
                            Size: 
                            
                            Is everything correct?
                        """.trimIndent(), MessageDialogButton.OK, MessageDialogButton.Cancel
                    )

                    if (mes == MessageDialogButton.OK) {
                        mapBuilder.loadFrom(file.toPath())
                        window.close()
                    }
                }
            }
        )
        menuFile.add(
            MenuItem(
                "Exit"
            ) { exitProcess(0) }
        )

        val menuHelp = Menu("Help")
        menuHelp.add(
            MenuItem(
                "Homepage"
            ) {
                MessageDialog.showMessageDialog(
                    textGUI,
                    "Homepage",
                    "https://github.com/usoltsev37/hse-homework-sd-roguelike",
                    MessageDialogButton.OK
                )
            }
        )
        menuHelp.add(
            MenuItem(
                "About"
            ) {
                MessageDialog.showMessageDialog(
                    textGUI,
                    "About",
                    """
                    GTA VI is amazing rogue-like game developed by
                     three penultimate students from HSE
                    """.trimIndent(),
                    MessageDialogButton.OK
                )
            }
        )


        menubar.add(menuFile).add(menuHelp)

        window.component = menubar
    }

    override fun show() {
        textGUI.addWindowAndWait(window)
    }
}