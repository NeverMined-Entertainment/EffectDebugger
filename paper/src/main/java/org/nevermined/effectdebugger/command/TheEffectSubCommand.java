package org.nevermined.effectdebugger.command;

import dev.jorel.commandapi.IStringTooltip;
import dev.jorel.commandapi.arguments.*;
import org.nevermined.effectdebugger.core.EffectProvider;

public class TheEffectSubCommand extends EffectSubCommand {

    public TheEffectSubCommand(EffectProvider effectProvider) {
        super(effectProvider);
        subCommand = new LiteralArgument("effect")
                .withPermission("edebug.effect")
                .then(new StringArgument("effectKey")
                        .replaceSuggestions(ArgumentSuggestions.stringsWithTooltipsCollection(info ->
                                getEffectProvider().getTheEffectMap().values().stream().map(effect -> (IStringTooltip)effect).toList()))
                        .executesPlayer(this::executeEffectEmmit));
    }

}
