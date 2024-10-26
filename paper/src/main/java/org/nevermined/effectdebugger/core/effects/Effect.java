package org.nevermined.effectdebugger.core.effects;

import dev.jorel.commandapi.BukkitTooltip;
import dev.jorel.commandapi.IStringTooltip;
import org.bukkit.entity.Player;

import java.util.Arrays;

public interface Effect extends IStringTooltip {

    String getIdentifier();
    String[] getOptionalData();
    boolean isDataRequired();
    EffectType getType();
    void emmit(Player player);
    default String getSuggestion() {
        return getIdentifier();
    }
    default com.mojang.brigadier.Message getTooltip() {
        if (getOptionalData().length == 1)
            return BukkitTooltip.messageFromString(getOptionalData()[0]);
        return Arrays.stream(getOptionalData())
                .reduce((s1, s2) -> s1 + ", " + s2)
                .map(BukkitTooltip::messageFromString)
                .orElse(BukkitTooltip.messageFromString(""));
    }

}
