package org.nevermined.effectdebugger.command;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.jorel.commandapi.CommandTree;
import org.nevermined.effectdebugger.EffectDebugger;
import org.nevermined.effectdebugger.core.EffectProvider;

@Singleton
public class EffectDebuggerCommand {

    private final SoundSubCommand soundSubCommand;

    @Inject
    public EffectDebuggerCommand(EffectDebugger plugin, EffectProvider effectProvider) {
        soundSubCommand = new SoundSubCommand(effectProvider);
        registerMainCommand();
    }

    private void registerMainCommand()
    {
        new CommandTree("edebug")
                .then(soundSubCommand.getSubCommand())
                .register();
    }

}
