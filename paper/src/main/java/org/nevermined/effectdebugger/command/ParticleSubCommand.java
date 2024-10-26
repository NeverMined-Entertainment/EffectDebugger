package org.nevermined.effectdebugger.command;

import dev.jorel.commandapi.IStringTooltip;
import dev.jorel.commandapi.SuggestionInfo;
import dev.jorel.commandapi.arguments.*;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.nevermined.effectdebugger.config.ParticleEffectConfig;
import org.nevermined.effectdebugger.core.EffectProvider;
import org.nevermined.effectdebugger.core.effects.ParticleEffect;
import org.nevermined.effectdebugger.core.parser.DataParserProvider;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ParticleSubCommand extends EffectSubCommand {

    private final ParticleEffectConfig config;

    public ParticleSubCommand(EffectProvider effectProvider, ParticleEffectConfig particleEffectConfig) {
        super(effectProvider);
        this.config = particleEffectConfig;
        subCommand = new LiteralArgument("particle")
                .withPermission("edebug.particle")
                .then(new StringArgument("effectKey")
                        .replaceSuggestions(ArgumentSuggestions.stringsWithTooltipsCollection(info ->
                                getEffectProvider().getParticleEffectMap().values().stream().map(particleEffect -> (IStringTooltip)particleEffect).toList()))
                        .executesPlayer(this::executeEffectEmmit)
                        .then(new IntegerArgument("count")
                                .replaceSuggestions(ArgumentSuggestions.strings("[count]"))
                                .executesPlayer(this::executeCountParticleEmmit)
                                .then(new LocationArgument("offset", LocationType.PRECISE_POSITION)
                                        .replaceSafeSuggestions(SafeSuggestions.suggest(new Location(null,
                                            config.getDefaultParticleSpawnOffset().getX(),
                                            config.getDefaultParticleSpawnOffset().getY(),
                                            config.getDefaultParticleSpawnOffset().getZ())))
                                        .executesPlayer(this::executeCountOffsetParticleEmmit)
                                        .then(new StringArgument("data")
                                                .replaceSuggestions(ArgumentSuggestions.stringCollection(this::getDataSuggestions))
                                                .executesPlayer(this::executeCountOffsetDataEffectEmmit))))
);
    }

    @SuppressWarnings("DataFlowIssue")
    private Collection<String> getDataSuggestions(SuggestionInfo<CommandSender> info)
    {
        Set<String> suggestions = new HashSet<>();

        String effectKey = info.previousArgs().getOrDefaultRaw("effectKey", "");
        if (getEffectProvider().getParticleEffect(effectKey) == null)
            return suggestions;

        ParticleEffect effect = getEffectProvider().getParticleEffect(effectKey);
        if (effect.getParticle().getDataType() == Void.class)
            return suggestions;
        return DataParserProvider.getDataParser(effect.getParticle().getDataType()).getSuggestions();
    }

    @SuppressWarnings("DataFlowIssue")
    protected void executeCountParticleEmmit(Player sender, CommandArguments args) throws WrapperCommandSyntaxException
    {
        String effectKey = getEffectKey(sender, args);
        int count = args.getByClassOrDefault("count", int.class, config.getDefaultParticleCount());
        getEffectProvider().getParticleEffect(effectKey).emmit(sender, count);
    }

    @SuppressWarnings("DataFlowIssue")
    protected void executeCountOffsetParticleEmmit(Player sender, CommandArguments args) throws WrapperCommandSyntaxException
    {
        String effectKey = getEffectKey(sender, args);
        int count = args.getByClassOrDefault("count", int.class, config.getDefaultParticleCount());
        Vector defaultOffset = config.getDefaultParticleSpawnOffset();
        Location offset = args.getByClassOrDefault("offset", Location.class, new Location(null, defaultOffset.getX(), defaultOffset.getY(), defaultOffset.getZ()));
        getEffectProvider().getParticleEffect(effectKey).emmit(sender, offset.toVector(), count);
    }

    @SuppressWarnings("DataFlowIssue")
    protected void executeCountOffsetDataEffectEmmit(Player sender, CommandArguments args) throws WrapperCommandSyntaxException
    {
        String effectKey = getEffectKey(sender, args);
        int count = args.getByClassOrDefault("count", int.class, config.getDefaultParticleCount());
        Vector defaultOffset = config.getDefaultParticleSpawnOffset();
        Location offset = args.getByClassOrDefault("offset", Location.class, new Location(null, defaultOffset.getX(), defaultOffset.getY(), defaultOffset.getZ()));
        String data = args.getOrDefaultRaw("data", "");
        ParticleEffect effect = getEffectProvider().getParticleEffect(effectKey);
        getEffectProvider().getParticleEffect(effectKey).emmit(sender, offset.toVector(), count, DataParserProvider.getDataParser(effect.getParticle().getDataType()).getData(data));
    }

}
