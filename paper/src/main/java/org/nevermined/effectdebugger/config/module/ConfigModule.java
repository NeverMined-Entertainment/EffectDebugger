package org.nevermined.effectdebugger.config.module;

import com.google.inject.AbstractModule;
import org.nevermined.effectdebugger.config.GlobalConfig;
import org.nevermined.effectdebugger.config.ParticleEffectConfig;
import org.nevermined.effectdebugger.config.SoundEffectConfig;

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SoundEffectConfig.class);
        bind(ParticleEffectConfig.class);
        bind(GlobalConfig.class);
    }
}
