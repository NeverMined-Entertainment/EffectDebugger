package org.nevermined.effectdebugger.config;

import com.google.inject.Singleton;
import lombok.Getter;
import me.wyne.wutils.config.ConfigEntry;
import me.wyne.wutils.config.configurables.GuiItemConfigurable;
import me.wyne.wutils.config.configurables.MaterialConfigurable;
import org.bukkit.Material;

import java.util.ArrayList;

@SuppressWarnings("FieldMayBeFinal")
@Singleton
@Getter
public class ScrollGuiConfig {

    @ConfigEntry(section = "GUI.Scroll Gui", path = "scroll-gui-footer")
    private MaterialConfigurable footer = new MaterialConfigurable(Material.CYAN_STAINED_GLASS_PANE);

    @ConfigEntry(section = "GUI.Scroll Gui", path = "scroll-gui-previous")
    private GuiItemConfigurable navigationPrevious = new GuiItemConfigurable(
            "scroll-gui-previous",
            Material.PAPER,
            48,
            -1,
            new ArrayList<>(),
            null, null
    );

    @ConfigEntry(section = "GUI.Scroll Gui", path = "scroll-gui-next")
    private GuiItemConfigurable navigationNext = new GuiItemConfigurable(
            "scroll-gui-next",
            Material.PAPER,
            50,
            -1,
            new ArrayList<>(),
            null, null
    );

    @ConfigEntry(section = "GUI.Scroll Gui", path = "scroll-gui-back")
    private GuiItemConfigurable navigationBack = new GuiItemConfigurable(
            "scroll-gui-back",
            Material.RED_STAINED_GLASS_PANE,
            53,
            -1,
            new ArrayList<>(),
            null, null
    );

    @ConfigEntry(section = "GUI.Scroll Gui", path = "scroll-gui-count")
    private GuiItemConfigurable navigationCount = new GuiItemConfigurable(
            "<page-count>",
            Material.BOOK,
            49,
            -1,
            new ArrayList<>(),
            null, null
    );

}
