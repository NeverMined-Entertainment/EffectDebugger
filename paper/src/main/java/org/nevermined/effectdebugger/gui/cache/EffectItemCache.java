package org.nevermined.effectdebugger.gui.cache;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.GuiItem;
import lombok.Getter;
import me.wyne.wutils.i18n.I18n;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.nevermined.effectdebugger.core.effects.Effect;

import java.util.*;

@Getter
public class EffectItemCache {

    private final List<GuiItem> items = new LinkedList<>();

    public <T extends Effect> void reloadItems(Map<String, T> effects, Material item) {
        items.clear();
        for (String effectKey : effects.keySet().stream().sorted().toList())
        {
            Effect effect = effects.get(effectKey);

            List<Component> lore = new ArrayList<>();
            Arrays.stream(effect.getOptionalData())
                    .forEach(string -> lore.add(Component.text(string).decoration(TextDecoration.ITALIC, false).color(NamedTextColor.WHITE)));

            items.add(ItemBuilder.from(item)
                    .name(Component.text(effect.getIdentifier()).decoration(TextDecoration.ITALIC, false))
                    .lore(lore)
                    .asGuiItem(event -> {
                        if (event.isRightClick())
                        {
                            if (!effect.isDataRequired())
                            {
                                event.getWhoClicked().sendMessage(Component.text("/" + effect.getCommand())
                                        .decorate(TextDecoration.UNDERLINED).color(NamedTextColor.GREEN)
                                        .hoverEvent(HoverEvent.showText(I18n.global.getLegacyPlaceholderComponent(I18n.toLocale(event.getWhoClicked()), event.getWhoClicked(), "info-click-to-suggest")))
                                        .clickEvent(ClickEvent.suggestCommand("/" + effect.getCommand())));
                            }
                            else
                            {
                                event.getWhoClicked().sendMessage(Component.text("/" + effect.getCommand()).append(Component.text("<data>"))
                                        .decorate(TextDecoration.UNDERLINED).color(NamedTextColor.GREEN)
                                        .hoverEvent(HoverEvent.showText(I18n.global.getLegacyPlaceholderComponent(I18n.toLocale(event.getWhoClicked()), event.getWhoClicked(), "info-click-to-suggest")))
                                        .clickEvent(ClickEvent.suggestCommand("/" + effect.getCommand())));
                            }
                            return;
                        }

                        if (effect.isDataRequired())
                        {
                            event.getWhoClicked().sendMessage(
                                    I18n.global.getLegacyPlaceholderComponent(I18n.toLocale(event.getWhoClicked()), event.getWhoClicked(), "error-data-required") // Read line from i18n
                                            .replaceText(TextReplacementConfig.builder().matchLiteral("<command>") // Replace command placeholder with command
                                                    .replacement(Component.text(effect.getCommand()).append(Component.text("<data>"))
                                                            .decorate(TextDecoration.UNDERLINED)
                                                            .hoverEvent(HoverEvent.showText(I18n.global.getLegacyPlaceholderComponent(I18n.toLocale(event.getWhoClicked()), event.getWhoClicked(), "info-click-to-suggest")))
                                                            .clickEvent(ClickEvent.suggestCommand("/" + effect.getCommand()))).build()));
                            return;
                        }
                        effect.emmit((Player) event.getWhoClicked());
                    }));
        }
    }

}
