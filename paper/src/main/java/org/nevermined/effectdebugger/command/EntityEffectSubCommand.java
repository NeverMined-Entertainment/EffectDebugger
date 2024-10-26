package org.nevermined.effectdebugger.command;

import dev.jorel.commandapi.IStringTooltip;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.nevermined.effectdebugger.core.EffectProvider;

public class EntityEffectSubCommand extends EffectSubCommand {

    public EntityEffectSubCommand(EffectProvider effectProvider) {
        super(effectProvider);
        subCommand = new LiteralArgument("entityeffect")
                .withPermission("edebug.entityeffect")
                .then(new StringArgument("effectKey")
                        .replaceSuggestions(ArgumentSuggestions.stringsWithTooltipsCollection(info ->
                                getEffectProvider().getEntityEffectMap().values().stream().map(entityEffect -> (IStringTooltip)entityEffect).toList()))
                        .executesPlayer(this::executeEffectEmmit));
    }

}
