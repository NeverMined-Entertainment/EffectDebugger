package org.nevermined.effectdebugger.command;

import dev.jorel.commandapi.IStringTooltip;
import dev.jorel.commandapi.arguments.*;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.entity.Player;
import org.nevermined.effectdebugger.core.EffectProvider;

public class SoundSubCommand extends EffectSubCommand {

    private final Argument<String> subCommand;

    public SoundSubCommand(EffectProvider effectProvider) {
        super(effectProvider);
        subCommand = new LiteralArgument("sound")
                .withPermission("edebug.sound")
                .then(new StringArgument("effectKey")
                        .replaceSuggestions(ArgumentSuggestions.stringsWithTooltipsCollection(info ->
                                getEffectProvider().getSoundEffectMap().values().stream().map(soundEffect -> (IStringTooltip)soundEffect).toList()))
                        .executesPlayer(this::executeEffectEmmit)
                        .then(new FloatArgument("volume")
                                .replaceSuggestions(ArgumentSuggestions.strings("[volume]")).setOptional(true)
                                .executesPlayer(this::executeVolumeSoundEmmit)
                                .then(new FloatArgument("pitch")
                                        .replaceSuggestions(ArgumentSuggestions.strings("[pitch]")).setOptional(true)
                                        .executesPlayer(this::executeVolumePitchSoundEmmit))));
    }

    @Override
    public Argument<String> getSubCommand()
    {
        return subCommand;
    }

    @SuppressWarnings("DataFlowIssue")
    protected void executeVolumeSoundEmmit(Player sender, CommandArguments args) throws WrapperCommandSyntaxException
    {
        String effectKey = getEffectKey(sender, args);
        float volume = args.getByClassOrDefault("volume", float.class, 1.0f);
        getEffectProvider().getSoundEffect(effectKey).emmit(sender, volume, 1.0f);
    }

    @SuppressWarnings("DataFlowIssue")
    protected void executeVolumePitchSoundEmmit(Player sender, CommandArguments args) throws WrapperCommandSyntaxException
    {
        String effectKey = getEffectKey(sender, args);
        float volume = args.getByClassOrDefault("volume", float.class, 1.0f);
        float pitch = args.getByClassOrDefault("pitch", float.class, 1.0f);
        getEffectProvider().getSoundEffect(effectKey).emmit(sender, volume, pitch);
    }

}
