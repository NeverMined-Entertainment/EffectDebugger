package org.nevermined.effectdebugger;

import com.google.inject.*;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import me.wyne.wutils.config.Config;
import me.wyne.wutils.i18n.I18n;
import me.wyne.wutils.i18n.language.validation.EmptyValidator;
import me.wyne.wutils.log.BasicLogConfig;
import me.wyne.wutils.log.ConfigurableLogConfig;
import me.wyne.wutils.log.Log;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.nevermined.effectdebugger.command.module.CommandModule;
import org.nevermined.effectdebugger.config.GlobalConfig;
import org.nevermined.effectdebugger.config.module.ConfigModule;
import org.nevermined.effectdebugger.core.module.EffectModule;
import org.nevermined.effectdebugger.module.PluginModule;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;

public final class EffectDebugger extends JavaPlugin {

    private static EffectDebugger instance;

    private Injector injector;
    private GlobalConfig globalConfig;

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
    }

    @Override
    public void onEnable() {
        instance = this;
        CommandAPI.onEnable();

        saveDefaultConfig();
        getConfig().setDefaults(new MemoryConfiguration());
        initializeLogger();
        initializeI18n();

        try {
            injector = Guice.createInjector(
                    Stage.PRODUCTION,
                    new PluginModule(this),
                    new ConfigModule(),
                    new EffectModule(),
                    new CommandModule()
            );
        } catch (CreationException e)
        {
            Log.global.exception("Guice injector creation exception", e);
        }

        initializeConfig();

        try {
            globalConfig = injector.getInstance(GlobalConfig.class);
        } catch (ConfigurationException | ProvisionException e)
        {
            Log.global.exception("Guice configuration/provision exception", e);
        }
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
    }

    private void initializeLogger()
    {
        Log.global = Log.builder()
                .setLogger(getLogger())
                .setConfig(new ConfigurableLogConfig("Global", Config.global, new BasicLogConfig(true, true, true, true)))
                .setLogDirectory(new File(getDataFolder(), "log"))
                .setFileWriteExecutor(Executors.newSingleThreadExecutor())
                .build();
    }

    private void initializeConfig()
    {
        Config.global.setConfigGenerator(this, "config.yml");
        Config.global.generateConfig(getDescription().getVersion());
        reloadConfig();
        Config.global.reloadConfig(getConfig());
    }

    private void initializeI18n()
    {
        I18n.global.loadLanguage("lang/ru.yml", this);
        I18n.global.loadLanguage("lang/en.yml", this);
        I18n.global.loadDefaultPluginLanguage(this);
        I18n.global.setDefaultLanguage(I18n.getDefaultLanguageFile(this));
        I18n.global.loadLanguages(this);
        I18n.global.setStringValidator(new EmptyValidator());
    }

    public void reload()
    {
        reloadConfig();
        Config.global.reloadConfig(getConfig());
        initializeI18n();
    }

    public static GlobalConfig getGlobalConfig()
    {
        return instance.globalConfig;
    }
}
