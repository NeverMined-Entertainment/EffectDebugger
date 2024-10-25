package org.nevermined.effectdebugger.core;

import com.google.inject.Singleton;
import org.nevermined.effectdebugger.core.effects.*;
import org.nevermined.effectdebugger.core.loaders.*;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class EffectProvider {

    private final Map<String, SoundEffect> soundEffectMap = new HashMap<>();
    private final Map<String, ParticleEffect> particleEffectMap = new HashMap<>();
    private final Map<String, TheEffect> theEffectMap = new HashMap<>();
    private final Map<String, EntityEffect> entityEffectMap = new HashMap<>();
    private final Map<String, Effect> effectMap = new HashMap<>();

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

    public Effect getEffect(String identifier)
    {
        return effectMap.get(identifier);
    }

    public SoundEffect getSoundEffect(String identifier)
    {
        return soundEffectMap.get(identifier);
    }

    public ParticleEffect getParticleEffect(String identifier)
    {
        return particleEffectMap.get(identifier);
    }

    public TheEffect getTheEffect(String identifier)
    {
        return theEffectMap.get(identifier);
    }

    public EntityEffect getEntityEffect(String identifier)
    {
        return entityEffectMap.get(identifier);
    }

    public Map<String, SoundEffect> getSoundEffectMap() {
        return soundEffectMap;
    }

    public Map<String, ParticleEffect> getParticleEffectMap() {
        return particleEffectMap;
    }

    public Map<String, TheEffect> getTheEffectMap() {
        return theEffectMap;
    }

    public Map<String, EntityEffect> getEntityEffectMap() {
        return entityEffectMap;
    }

    public Map<String, Effect> getEffectMap() {
        return effectMap;
    }
}
