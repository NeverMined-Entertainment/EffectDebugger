package org.nevermined.effectdebugger.core.loaders;

import org.nevermined.effectdebugger.core.effects.Effect;

import java.util.Map;

public interface EffectLoader<T extends Effect> {

    Map<String, T> loadEffects();

}
