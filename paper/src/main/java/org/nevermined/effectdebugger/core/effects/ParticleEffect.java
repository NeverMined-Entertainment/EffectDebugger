package org.nevermined.effectdebugger.core.effects;

import lombok.Getter;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.nevermined.effectdebugger.EffectDebugger;
import org.nevermined.effectdebugger.utils.VectorUtils;

@SuppressWarnings("LombokGetterMayBeUsed")
public class ParticleEffect implements Effect {
    @Getter private final Particle particle;
    private final String[] optionalData = new String[1];

    public ParticleEffect(Particle particle) {
        this.particle = particle;
        optionalData[0] = "Data: " + (particle.getDataType() != Void.class ? particle.getDataType().getTypeName() : "void");
    }

    @Override
    public String getIdentifier() {
        return particle.toString();
    }

    @Override
    public String[] getOptionalData() {
        return optionalData;
    }

    @Override
    public boolean isDataRequired() {
        return particle.getDataType() != Void.class;
    }

    @Override
    public EffectType getType() {
        return EffectType.PARTICLE_EFFECT;
    }

    @Override
    public String getCommand() {
        StringBuilder builder = new StringBuilder();
        builder.append("edebug ").append(getType().getEffectType());
        builder.append(" ").append(getSuggestion());
        builder.append(" ").append(EffectDebugger.getGlobalConfig().particleEffectConfig().getDefaultParticleCount());
        builder.append(" ").append(EffectDebugger.getGlobalConfig().particleEffectConfig().getDefaultParticleSpawnOffset().toString().replaceAll(",", " "));
        builder.append(" ").append(EffectDebugger.getGlobalConfig().particleEffectConfig().getDefaultParticleExtra());
        if (isDataRequired())
            builder.append(" ");
        return builder.toString();
    }

    @Override
    public void emmit(Player player) {
        player.spawnParticle(particle,
                VectorUtils.applyOffset(player.getEyeLocation(), EffectDebugger.getGlobalConfig().particleEffectConfig().getDefaultParticleSpawnOffset()),
                EffectDebugger.getGlobalConfig().particleEffectConfig().getDefaultParticleCount());
    }

    public void emmit(Player player, Vector offset)
    {
        player.spawnParticle(particle,
                VectorUtils.applyOffset(player.getEyeLocation(), offset),
                EffectDebugger.getGlobalConfig().particleEffectConfig().getDefaultParticleCount());
    }

    public void emmit(Player player, int count)
    {
        player.spawnParticle(particle,
                VectorUtils.applyOffset(player.getEyeLocation(), EffectDebugger.getGlobalConfig().particleEffectConfig().getDefaultParticleSpawnOffset()),
                count);
    }

    public void emmit(Player player, Vector offset, int count)
    {
        player.spawnParticle(particle, VectorUtils.applyOffset(player.getEyeLocation(), offset), count);
    }

    public void emmit(Player player, Vector offset, int count, double extra)
    {
        player.spawnParticle(particle, VectorUtils.applyOffset(player.getEyeLocation(), offset), count, 0f, 0f, 0f, extra);
    }

    public <T> void emmit(Player player, Vector offset, int count, T data)
    {
        player.spawnParticle(particle, VectorUtils.applyOffset(player.getEyeLocation(), offset), count, data);
    }

    public <T> void emmit(Player player, Vector offset, int count, double extra, T data)
    {
        player.spawnParticle(particle, VectorUtils.applyOffset(player.getEyeLocation(), offset), count, 0f, 0f, 0f, extra, data);
    }
}
