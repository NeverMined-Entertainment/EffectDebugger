package org.nevermined.effectdebugger.command;

import dev.jorel.commandapi.IStringTooltip;
import dev.jorel.commandapi.SuggestionInfo;
import dev.jorel.commandapi.arguments.*;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.nevermined.effectdebugger.core.EffectProvider;
import org.nevermined.effectdebugger.core.effects.TheEffect;
import org.nevermined.effectdebugger.core.parser.DataParserProvider;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TheEffectSubCommand extends EffectSubCommand {

    public TheEffectSubCommand(EffectProvider effectProvider) {
        super(effectProvider);
        subCommand = new LiteralArgument("effect")
                .withPermission("edebug.effect")
                .then(new StringArgument("effectKey")
                        .replaceSuggestions(ArgumentSuggestions.stringsWithTooltipsCollection(info ->
                                getEffectProvider().getTheEffectMap().values().stream().map(effect -> (IStringTooltip)effect).toList()))
                        .executesPlayer(this::executeEffectEmmit)
                        .then(new StringArgument("data")
                                .replaceSuggestions(ArgumentSuggestions.stringCollection(this::getDataSuggestions))
                                .executesPlayer(this::executeDataEffectEmmit)));
    }

    @SuppressWarnings("DataFlowIssue")
    private Collection<String> getDataSuggestions(SuggestionInfo<CommandSender> info)
    {
        Set<String> suggestions = new HashSet<>();

        String effectKey = info.previousArgs().getOrDefaultRaw("effectKey", "");
        if (getEffectProvider().getTheEffect(effectKey) == null)
            return suggestions;

        TheEffect effect = getEffectProvider().getTheEffect(effectKey);
        if (effect.getEffect().getData() == null)
            return suggestions;
        return DataParserProvider.getDataParser(effect.getEffect().getData()).getSuggestions();
    }

    @SuppressWarnings("DataFlowIssue")
    protected void executeDataEffectEmmit(Player sender, CommandArguments args) throws WrapperCommandSyntaxException
    {
        String effectKey = getEffectKey(sender, args);
        String data = args.getOrDefaultRaw("data", "");
        TheEffect effect = getEffectProvider().getTheEffect(effectKey);
        effect.emmit(sender, DataParserProvider.getDataParser(effect.getEffect().getData()).getData(data));
    }

}
