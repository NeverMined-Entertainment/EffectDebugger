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

    private final EffectItemCache soundItemCache;
    private final EffectItemCache particleItemCache;
    private final EffectItemCache effectItemCache;
    private final EffectItemCache entityEffectItemCache;

    @Inject
    public ItemCacheProvider(EffectProvider effectProvider, GlobalConfig globalConfig) {
        this.effectProvider = effectProvider;
        this.globalConfig = globalConfig;
        this.soundItemCache = new EffectItemCache();
        this.particleItemCache = new EffectItemCache();
        this.effectItemCache = new EffectItemCache();
        this.entityEffectItemCache = new EffectItemCache();
        reloadItems();
    }

    public void reloadItems()
    {
        soundItemCache.reloadItems(effectProvider.getSoundEffectMap(), globalConfig.mainGuiConfig().getSoundsItem().getMaterial());
        particleItemCache.reloadItems(effectProvider.getParticleEffectMap(), globalConfig.mainGuiConfig().getParticlesItem().getMaterial());
        effectItemCache.reloadItems(effectProvider.getTheEffectMap(), globalConfig.mainGuiConfig().getEffectsItem().getMaterial());
        entityEffectItemCache.reloadItems(effectProvider.getEntityEffectMap(), globalConfig.mainGuiConfig().getEntityEffectsItem().getMaterial());
    }

}
