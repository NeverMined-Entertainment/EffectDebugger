package org.nevermined.effectdebugger.config;

import com.google.inject.Singleton;
import lombok.Getter;
import me.wyne.wutils.config.ConfigEntry;
import org.bukkit.util.Vector;

@SuppressWarnings("FieldMayBeFinal")
@Singleton
@Getter
public class ParticleEffectConfig {

    @ConfigEntry(section = "Effects.Particles")
    private Vector defaultParticleSpawnOffset = new Vector(2f, 0f, 1.5f);
    @ConfigEntry(section = "Effects.Particles")
    private int defaultParticleCount = 10;

}
