package org.nevermined.effectdebugger.gui.cache;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import org.nevermined.effectdebugger.config.GlobalConfig;
import org.nevermined.effectdebugger.core.EffectProvider;

@Singleton
@Getter
public class ItemCacheProvider {

    private final EffectProvider effectProvider;
    private final GlobalConfig globalConfig;

    private final EffectItemCache soundItemsCache;

    @Inject
    public ItemCacheProvider(EffectProvider effectProvider, GlobalConfig globalConfig) {
        this.effectProvider = effectProvider;
        this.globalConfig = globalConfig;
        this.soundItemsCache = new EffectItemCache();
        reloadItems();
    }

    public void reloadItems()
    {
        soundItemsCache.reloadItems(effectProvider.getSoundEffectMap(), globalConfig.mainGuiConfig().getSoundsItem().getMaterial());
    }

}
