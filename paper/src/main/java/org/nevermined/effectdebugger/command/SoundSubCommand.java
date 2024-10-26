package org.nevermined.effectdebugger.command;

import dev.jorel.commandapi.IStringTooltip;
import dev.jorel.commandapi.arguments.*;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.entity.Player;
import org.nevermined.effectdebugger.config.SoundEffectConfig;
import org.nevermined.effectdebugger.core.EffectProvider;

@SuppressWarnings("DataFlowIssue")
public class SoundSubCommand extends EffectSubCommand {

    private final SoundEffectConfig config;

    public SoundSubCommand(EffectProvider effectProvider, SoundEffectConfig soundEffectConfig) {
        super(effectProvider);
        this.config = soundEffectConfig;
        subCommand = new LiteralArgument("sound")
                .withPermission("edebug.sound")
                .then(new StringArgument("effectKey")
                        .replaceSuggestions(ArgumentSuggestions.stringsWithTooltipsCollection(info ->
                                getEffectProvider().getSoundEffectMap().values().stream().map(soundEffect -> (IStringTooltip)soundEffect).toList()))
                        .executesPlayer(this::executeEffectEmmit)
                        .then(new FloatArgument("volume")
                                .replaceSuggestions(ArgumentSuggestions.strings("[volume]"))
                                .executesPlayer(this::executeVolumeSoundEmmit)
                                .then(new FloatArgument("pitch")
                                        .replaceSuggestions(ArgumentSuggestions.strings("[pitch]"))
                                        .executesPlayer(this::executeVolumePitchSoundEmmit))));
    }

    private void executeVolumeSoundEmmit(Player sender, CommandArguments args) throws WrapperCommandSyntaxException
    {
        String effectKey = getEffectKey(sender, args);
        float volume = args.getByClassOrDefault("volume", float.class, config.getDefaultVolume());
        getEffectProvider().getSoundEffect(effectKey).emmit(sender, volume, config.getDefaultPitch());
    }

    private void executeVolumePitchSoundEmmit(Player sender, CommandArguments args) throws WrapperCommandSyntaxException
    {
        String effectKey = getEffectKey(sender, args);
        float volume = args.getByClassOrDefault("volume", float.class, config.getDefaultVolume());
        float pitch = args.getByClassOrDefault("pitch", float.class, config.getDefaultPitch());
        getEffectProvider().getSoundEffect(effectKey).emmit(sender, volume, pitch);
    }

}
