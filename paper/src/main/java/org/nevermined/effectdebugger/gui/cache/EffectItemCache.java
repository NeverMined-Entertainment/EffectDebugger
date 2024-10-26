package org.nevermined.effectdebugger.gui.cache;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.GuiItem;
import lombok.Getter;
import net.kyori.adventure.text.Component;
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
                        effect.emmit((Player) event.getWhoClicked());
                    }));
        }
    }

}
