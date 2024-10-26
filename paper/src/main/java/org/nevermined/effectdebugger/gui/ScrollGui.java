package org.nevermined.effectdebugger.gui;

import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.ScrollingGui;
import me.wyne.wutils.i18n.language.replacement.Placeholder;
import net.kyori.adventure.sound.Sound;
import org.bukkit.entity.Player;
import org.nevermined.effectdebugger.config.ScrollGuiConfig;

public abstract class ScrollGui extends BaseGui<ScrollingGui> {

    protected void buildNavigation(BaseGui previousGui, ScrollGuiConfig config, Player player)
    {
        GuiItem navigationBack = config.getNavigationBack().buildLegacyGuiItem(event -> previousGui.openGui(player), player);
        GuiItem navigationPrevious = config.getNavigationPrevious().buildLegacyGuiItem(event -> {
            event.getWhoClicked().playSound(Sound.sound(org.bukkit.Sound.ITEM_BOOK_PAGE_TURN, Sound.Source.MASTER, 1f, 1f));
            gui.previous();
        }, player);
        GuiItem navigationNext = config.getNavigationNext().buildLegacyGuiItem(event -> {
            event.getWhoClicked().playSound(Sound.sound(org.bukkit.Sound.ITEM_BOOK_PAGE_TURN, Sound.Source.MASTER, 1f, 1f));
            gui.next();
        }, player);
        gui.setItem(config.getNavigationBack().getSlot(), navigationBack);
        gui.setItem(config.getNavigationPrevious().getSlot(), navigationPrevious);
        gui.setItem(config.getNavigationNext().getSlot(), navigationNext);
    }

    protected void buildCountedNavigation(BaseGui previousGui, ScrollGuiConfig config, Player player)
    {
        GuiItem navigationCount = config.getNavigationCount().buildLegacyGuiItem(player, Placeholder.replace("page-count", String.valueOf(1)));
        GuiItem navigationBack = config.getNavigationBack().buildLegacyGuiItem(event -> previousGui.openGui(player), player);
        GuiItem navigationPrevious = config.getNavigationPrevious().buildLegacyGuiItem(event -> {
            event.getWhoClicked().playSound(Sound.sound(org.bukkit.Sound.ITEM_BOOK_PAGE_TURN, Sound.Source.MASTER, 1f, 1f));
            gui.previous();
            gui.updateItem(config.getNavigationCount().getSlot(), config.getNavigationCount().buildLegacyGuiItem(player, Placeholder.replace("page-count", String.valueOf(gui.getCurrentPageNum()))));
        }, player);
        GuiItem navigationNext = config.getNavigationNext().buildLegacyGuiItem(event -> {
            event.getWhoClicked().playSound(Sound.sound(org.bukkit.Sound.ITEM_BOOK_PAGE_TURN, Sound.Source.MASTER, 1f, 1f));
            gui.next();
            gui.updateItem(config.getNavigationCount().getSlot(), config.getNavigationCount().buildLegacyGuiItem(player, Placeholder.replace("page-count", String.valueOf(gui.getCurrentPageNum()))));
        }, player);
        gui.setItem(config.getNavigationCount().getSlot(), navigationCount);
        gui.setItem(config.getNavigationBack().getSlot(), navigationBack);
        gui.setItem(config.getNavigationPrevious().getSlot(), navigationPrevious);
        gui.setItem(config.getNavigationNext().getSlot(), navigationNext);
    }

}
