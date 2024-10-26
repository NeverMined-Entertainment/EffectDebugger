package org.nevermined.effectdebugger.command;

import dev.jorel.commandapi.CommandAPIBukkit;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandArguments;
import me.wyne.wutils.i18n.I18n;
import me.wyne.wutils.i18n.language.replacement.Placeholder;
import org.bukkit.entity.Player;
import org.nevermined.effectdebugger.core.EffectProvider;

public abstract class EffectSubCommand {

    private final EffectProvider effectProvider;

    public EffectSubCommand(EffectProvider effectProvider) {
        this.effectProvider = effectProvider;
    }

    public abstract Argument<String> getSubCommand();

    @SuppressWarnings("DataFlowIssue")
    protected void executeEffectEmmit(Player sender, CommandArguments args) throws WrapperCommandSyntaxException
    {
        String effectKey = getEffectKey(sender, args);
        effectProvider.getEffect(effectKey).emmit(sender);
    }

    protected String getEffectKey(Player sender, CommandArguments args) throws WrapperCommandSyntaxException
    {
        String effectKey = args.getOrDefaultRaw("effectKey", "");

        if (effectProvider.getEffect(effectKey) == null)
            throw CommandAPIBukkit.failWithAdventureComponent(I18n.global.getLegacyPlaceholderComponent(sender.locale(), sender, "error-effect-not-found",
                    Placeholder.replace("effect-key", effectKey)));

        return effectKey;
    }

    protected EffectProvider getEffectProvider() {
        return effectProvider;
    }
}
