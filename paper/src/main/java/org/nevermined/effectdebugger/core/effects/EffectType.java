package org.nevermined.effectdebugger.core.effects;

import lombok.Getter;

@Getter
public enum EffectType {
    SOUND_EFFECT("sound"),
    PARTICLE_EFFECT("particle"),
    THE_EFFECT("effect"),
    ENTITY_EFFECT("entityeffect");

    private final String effectType;

    EffectType(String effectType) {
        this.effectType = effectType;
    }

}
