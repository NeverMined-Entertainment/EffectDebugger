package org.nevermined.effectdebugger.gui.module;

import com.google.inject.AbstractModule;
import org.nevermined.effectdebugger.gui.cache.ItemCacheProvider;

public class GuiModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ItemCacheProvider.class);
    }
}
