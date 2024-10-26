package org.nevermined.effectdebugger.core;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.nevermined.effectdebugger.core.effects.*;
import org.nevermined.effectdebugger.core.loaders.*;

import java.util.HashMap;
import java.util.Map;

@Singleton
@Getter
public class EffectProvider {
    private final Map<String, SoundEffect> soundEffectMap = new HashMap<>();
    private final Map<String, ParticleEffect> particleEffectMap = new HashMap<>();
    private final Map<String, TheEffect> theEffectMap = new HashMap<>();
    private final Map<String, EntityEffect> entityEffectMap = new HashMap<>();
    private final Map<String, Effect> effectMap = new HashMap<>();

    @Inject
    public EffectProvider() {
        loadEffects();
    }

    private void loadEffects()
    {
        soundEffectMap.putAll(new SoundLoader().loadEffects());
        particleEffectMap.putAll(new ParticleLoader().loadEffects());
        theEffectMap.putAll(new TheEffectLoader().loadEffects());
        entityEffectMap.putAll(new EntityEffectLoader().loadEffects());
        effectMap.putAll(soundEffectMap);
        effectMap.putAll(particleEffectMap);
        effectMap.putAll(theEffectMap);
        effectMap.putAll(entityEffectMap);
    }

    public @Nullable Effect getEffect(String identifier)
    {
        return effectMap.get(identifier);
    }

    public @Nullable SoundEffect getSoundEffect(String identifier)
    {
        return soundEffectMap.get(identifier);
    }

    public @Nullable ParticleEffect getParticleEffect(String identifier)
    {
        return particleEffectMap.get(identifier);
    }

    public @Nullable TheEffect getTheEffect(String identifier)
    {
        return theEffectMap.get(identifier);
    }

    public @Nullable EntityEffect getEntityEffect(String identifier)
    {
        return entityEffectMap.get(identifier);
    }
}
