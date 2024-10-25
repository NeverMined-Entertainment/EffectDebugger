package org.nevermined.effectdebugger.core.effects;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ParticleEffect implements Effect {
    private final Particle particle;
    private final String[] optionalData = new String[2];

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
        player.spawnParticle(particle, player.getLocation(), 1);
    }

    public void emmit(Player player, Vector offset)
    {
        player.spawnParticle(particle, player.getLocation().add(offset), 1);
    }

    public void emmit(Player player, int count)
    {
        player.spawnParticle(particle, player.getLocation(), count);
    }

    public void emmit(Player player, Vector offset, int count)
    {
        player.spawnParticle(particle, player.getLocation().add(offset), count);
    }

    public <T> void emmit(Player player, Vector offset, int count, T data)
    {
        player.spawnParticle(particle.builder().data(data).particle(), player.getLocation().add(offset), count);
    }
}
