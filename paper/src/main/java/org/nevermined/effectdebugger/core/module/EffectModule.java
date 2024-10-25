package org.nevermined.effectdebugger.core.module;

import com.google.inject.AbstractModule;
import org.nevermined.effectdebugger.core.EffectProvider;

public class EffectModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(EffectProvider.class);
    }
}
