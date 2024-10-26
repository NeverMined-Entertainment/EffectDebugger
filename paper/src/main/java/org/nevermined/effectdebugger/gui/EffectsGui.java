package org.nevermined.effectdebugger.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.components.ScrollType;
import dev.triumphteam.gui.guis.Gui;
import me.wyne.wutils.i18n.I18n;
import org.bukkit.entity.Player;
import org.nevermined.effectdebugger.config.ScrollGuiConfig;
import org.nevermined.effectdebugger.gui.cache.EffectItemCache;

public class EffectsGui extends ScrollGui {

    public EffectsGui(MainGui mainGui, ScrollGuiConfig config, EffectItemCache itemCache, String header, Player player)
    {
        gui = Gui.scrolling()
                .title(I18n.global.getLegacyPlaceholderComponent(player.locale(), player, header))
                .rows(6)
                .pageSize(45)
                .scrollType(ScrollType.VERTICAL)
                .disableAllInteractions()
                .create();
        gui.getFiller().fillBottom(ItemBuilder.from(config.getFooter().getMaterial()).asGuiItem());
        buildCountedNavigation(mainGui, config, player);
        itemCache.getItems().forEach(guiItem -> gui.addItem(guiItem));
    }

}
