package org.nevermined.effectdebugger.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.wyne.wutils.config.Config;

@Singleton
public record GlobalConfig(SoundEffectConfig soundEffectConfig, ParticleEffectConfig particleEffectConfig) {

    @Inject
    public GlobalConfig
    {
        Config.global.registerConfigObject(soundEffectConfig);
        Config.global.registerConfigObject(particleEffectConfig);
    }


}
