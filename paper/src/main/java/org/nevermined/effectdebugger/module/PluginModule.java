package org.nevermined.effectdebugger.module;

import com.google.inject.AbstractModule;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.nevermined.effectdebugger.EffectDebugger;

public class PluginModule extends AbstractModule {

    private final EffectDebugger plugin;

    public PluginModule(EffectDebugger plugin)
    {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(JavaPlugin.class)
                .toInstance(plugin);
        bind(EffectDebugger.class)
                .toInstance(plugin);
        bind(FileConfiguration.class)
                .toInstance(plugin.getConfig());
    }

}
