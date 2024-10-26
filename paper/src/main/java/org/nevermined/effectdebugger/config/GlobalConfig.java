package org.nevermined.effectdebugger.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.wyne.wutils.config.Config;

@Singleton
public record GlobalConfig(SoundEffectConfig soundEffectConfig, ParticleEffectConfig particleEffectConfig,
                           MainGuiConfig mainGuiConfig, ScrollGuiConfig scrollGuiConfig) {
    @Inject
    public GlobalConfig
    {
        Config.global.registerConfigObject(soundEffectConfig);
        Config.global.registerConfigObject(particleEffectConfig);
        Config.global.registerConfigObject(mainGuiConfig);
        Config.global.registerConfigObject(scrollGuiConfig);
    }
}
