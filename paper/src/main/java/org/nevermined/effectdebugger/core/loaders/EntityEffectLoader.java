package org.nevermined.effectdebugger.core.loaders;

import org.bukkit.EntityEffect;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EntityEffectLoader implements EffectLoader<org.nevermined.effectdebugger.core.effects.EntityEffect> {
    @Override
    public Map<String, org.nevermined.effectdebugger.core.effects.EntityEffect> loadEffects() {
        Map<String, org.nevermined.effectdebugger.core.effects.EntityEffect> entityEffects = new HashMap<>();
        Arrays.stream(EntityEffect.values())
                .filter(e -> e.getApplicable() == LivingEntity.class || e.getApplicable() == Player.class)
                .forEach(e -> entityEffects.put(e.toString(), new org.nevermined.effectdebugger.core.effects.EntityEffect(e)));
        return entityEffects;
    }
}
