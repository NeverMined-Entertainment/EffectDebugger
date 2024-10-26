package org.nevermined.effectdebugger.config;

import com.google.inject.Singleton;
import lombok.Getter;
import me.wyne.wutils.config.ConfigEntry;
import me.wyne.wutils.config.configurables.GuiItemConfigurable;
import me.wyne.wutils.config.configurables.MaterialConfigurable;
import org.bukkit.Material;

import java.util.ArrayList;

@Singleton
@Getter
public class MainGuiConfig {

    @ConfigEntry(section = "GUI.Main Gui", path = "main-gui-filler")
    private MaterialConfigurable filler = new MaterialConfigurable(Material.CYAN_STAINED_GLASS_PANE);

    @ConfigEntry(section = "GUI.Main Gui", path = "main-gui-sounds")
    private GuiItemConfigurable soundsItem = new GuiItemConfigurable(
            "main-gui-sounds",
            Material.NOTE_BLOCK,
            4,
            -1,
            new ArrayList<>(),
            null, null
    );

    @ConfigEntry(section = "GUI.Main Gui", path = "main-gui-particles")
    private GuiItemConfigurable particlesItem = new GuiItemConfigurable(
            "main-gui-particles",
            Material.NETHER_STAR,
            5,
            -1,
            new ArrayList<>(),
            null, null
    );

    @ConfigEntry(section = "GUI.Main Gui", path = "main-gui-effects")
    private GuiItemConfigurable effectsItem  = new GuiItemConfigurable(
            "main-gui-effects",
            Material.DRAGON_BREATH,
            3,
            -1,
            new ArrayList<>(),
            null, null
    );

    @ConfigEntry(section = "GUI.Main Gui", path = "main-gui-entity-effects")
    private GuiItemConfigurable entityEffectsItem = new GuiItemConfigurable(
            "main-gui-entity-effects",
            Material.TOTEM_OF_UNDYING,
            13,
            -1,
            new ArrayList<>(),
            null, null
    );

}
