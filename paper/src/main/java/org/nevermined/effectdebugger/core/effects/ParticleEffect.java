package org.nevermined.effectdebugger.core.effects;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.nevermined.effectdebugger.EffectDebugger;
import org.nevermined.effectdebugger.utils.VectorUtils;

public class ParticleEffect implements Effect {
    private final Particle particle;
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

    public <T> void emmit(Player player, Vector offset, int count, T data)
    {
        player.spawnParticle(particle.builder().data(data).particle(), VectorUtils.applyOffset(player.getEyeLocation(), offset), count);
    }
}
