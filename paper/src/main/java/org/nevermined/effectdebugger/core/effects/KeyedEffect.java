package org.nevermined.effectdebugger.core.effects;

import net.kyori.adventure.key.Key;

public interface KeyedEffect extends Effect {

    Key getKey();

}
