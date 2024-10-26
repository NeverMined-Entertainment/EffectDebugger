package org.nevermined.effectdebugger.command;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.CommandTree;
import dev.jorel.commandapi.arguments.LiteralArgument;
import me.wyne.wutils.i18n.I18n;
import org.nevermined.effectdebugger.EffectDebugger;
import org.nevermined.effectdebugger.config.GlobalConfig;
import org.nevermined.effectdebugger.core.EffectProvider;
import org.nevermined.effectdebugger.gui.MainGui;
import org.nevermined.effectdebugger.gui.cache.ItemCacheProvider;

@Singleton
public class EffectDebuggerCommand {

    private final EffectDebugger plugin;
    private final ItemCacheProvider itemCacheProvider;
    private final GlobalConfig globalConfig;

    private final SoundSubCommand soundSubCommand;
    private final ParticleSubCommand particleSubCommand;
    private final TheEffectSubCommand theEffectSubCommand;
    private final EntityEffectSubCommand entityEffectSubCommand;

    @Inject
    public EffectDebuggerCommand(EffectDebugger plugin, EffectProvider effectProvider, ItemCacheProvider itemCacheProvider, GlobalConfig globalConfig) {
        this.plugin = plugin;
        this.itemCacheProvider = itemCacheProvider;
        this.globalConfig = globalConfig;
        soundSubCommand = new SoundSubCommand(effectProvider, globalConfig.soundEffectConfig());
        particleSubCommand = new ParticleSubCommand(effectProvider, globalConfig.particleEffectConfig());
        theEffectSubCommand = new TheEffectSubCommand(effectProvider);
        entityEffectSubCommand = new EntityEffectSubCommand(effectProvider);
        registerMainCommand();
    }

    private void registerMainCommand()
    {
        new CommandTree("edebug")
                .then(soundSubCommand.getSubCommand())
                .then(particleSubCommand.getSubCommand())
                .then(theEffectSubCommand.getSubCommand())
                .then(entityEffectSubCommand.getSubCommand())
                .then(new LiteralArgument("reload")
                        .withPermission(CommandPermission.OP)
                        .executes((sender, args) -> {
                            plugin.reload();
                            sender.sendMessage(I18n.global.getLegacyPlaceholderComponent(I18n.toLocale(sender), sender, "success-plugin-reloaded"));
                        }))
                .withPermission(CommandPermission.OP)
                .executesPlayer(((sender, args) ->
                {
                    new MainGui(globalConfig, itemCacheProvider, sender).openGui(sender);
                }))
                .register();
    }

}
