package org.nevermined.effectdebugger.core.effects;

import org.bukkit.entity.Player;

import java.util.List;

public interface Effect {

    String getIdentifier();
    String[] getOptionalData();
    void emmit(Player player);

}
