package org.nevermined.effectdebugger.config.module;

import com.google.inject.AbstractModule;
import org.nevermined.effectdebugger.config.*;

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SoundEffectConfig.class);
        bind(ParticleEffectConfig.class);
        bind(MainGuiConfig.class);
        bind(ScrollGuiConfig.class);
        bind(GlobalConfig.class);
    }
}
