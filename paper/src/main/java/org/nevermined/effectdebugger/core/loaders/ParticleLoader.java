package org.nevermined.effectdebugger.core.loaders;

import org.bukkit.Particle;
import org.nevermined.effectdebugger.core.effects.ParticleEffect;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ParticleLoader implements EffectLoader<ParticleEffect> {
    @Override
    public Map<String, ParticleEffect> loadEffects() {
        Map<String, ParticleEffect> particles = new HashMap<>();
        Arrays.stream(Particle.values())
                .filter(p -> !p.toString().startsWith("LEGACY"))
                .forEach(p -> particles.put(p.toString(), new ParticleEffect(p)));
        return particles;
    }
}
