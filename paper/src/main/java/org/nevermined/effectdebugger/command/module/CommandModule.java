package org.nevermined.effectdebugger.command.module;

import com.google.inject.AbstractModule;
import org.nevermined.effectdebugger.command.EffectDebuggerCommand;

public class CommandModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(EffectDebuggerCommand.class);
    }
}
