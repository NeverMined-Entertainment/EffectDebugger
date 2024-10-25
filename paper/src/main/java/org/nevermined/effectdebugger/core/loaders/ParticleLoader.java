package org.nevermined.effectdebugger.core.loaders;

import org.bukkit.Particle;
import org.nevermined.effectdebugger.core.effects.ParticleEffect;

import java.util.HashMap;
import java.util.Map;

public class ParticleLoader implements EffectLoader<ParticleEffect> {
    @Override
    public Map<String, ParticleEffect> loadEffects() {
        Map<String, ParticleEffect> particles = new HashMap<>();
        for (Particle particle : Particle.values())
        {
            particles.put(particle.toString(), new ParticleEffect(particle));
        }
        return particles;
    }
}
