package org.nevermined.effectdebugger.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.wyne.wutils.i18n.I18n;
import org.bukkit.entity.Player;
import org.nevermined.effectdebugger.config.GlobalConfig;
import org.nevermined.effectdebugger.gui.cache.ItemCacheProvider;

public class MainGui extends BaseGui<Gui> {

    public MainGui(GlobalConfig globalConfig, ItemCacheProvider itemCacheProvider, Player player)
    {
        gui = Gui.gui()
                .title(I18n.global.getLegacyPlaceholderComponent(player.locale(), player, "main-gui-header"))
                .rows(1)
                .disableAllInteractions()
                .create();
        gui.getFiller().fill(ItemBuilder.from(globalConfig.mainGuiConfig().getFiller().getMaterial()).asGuiItem());
        buildEffectGuis(globalConfig, itemCacheProvider, player);
    }

    private void buildEffectGuis(GlobalConfig globalConfig, ItemCacheProvider itemCacheProvider, Player player)
    {
        GuiItem soundsItem = globalConfig.mainGuiConfig().getSoundsItem().buildLegacyGuiItem(event ->
                        new EffectsGui(this, globalConfig.scrollGuiConfig(), itemCacheProvider.getSoundItemsCache(), "sounds-gui-header", player).openGui(player), player);
        gui.setItem(globalConfig.mainGuiConfig().getSoundsItem().getSlot(), soundsItem);
    }

}
