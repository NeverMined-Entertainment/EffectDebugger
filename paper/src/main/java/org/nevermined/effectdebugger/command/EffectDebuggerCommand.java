package org.nevermined.effectdebugger.command;

import com.google.inject.Singleton;
import dev.jorel.commandapi.CommandTree;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.arguments.MultiLiteralArgument;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.command.CommandSender;
import org.nevermined.effectdebugger.EffectDebugger;
import org.nevermined.effectdebugger.core.EffectProvider;

@Singleton
public class EffectDebuggerCommand {

    private final EffectDebugger plugin;
    private final EffectProvider effectProvider;

    public EffectDebuggerCommand(EffectDebugger plugin, EffectProvider effectProvider) {
        this.plugin = plugin;
        this.effectProvider = effectProvider;
        registerMainCommand();
    }

    private void registerMainCommand()
    {

    }

}
