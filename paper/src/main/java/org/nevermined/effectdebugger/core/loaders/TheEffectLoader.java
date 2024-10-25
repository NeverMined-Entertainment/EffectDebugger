package org.nevermined.effectdebugger.core.loaders;

import org.nevermined.effectdebugger.core.effects.TheEffect;

import java.util.HashMap;
import java.util.Map;

public class TheEffectLoader implements EffectLoader<TheEffect> {
    @Override
    public Map<String, TheEffect> loadEffects() {
        Map<String, TheEffect> effects = new HashMap<>();
        for (org.bukkit.Effect effect : org.bukkit.Effect.values())
        {
            effects.put(effect.toString(), new TheEffect(effect));
        }
        return effects;
    }
}
