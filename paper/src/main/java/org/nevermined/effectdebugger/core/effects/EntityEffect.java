package org.nevermined.effectdebugger.core.effects;

import org.bukkit.entity.Player;

public class EntityEffect implements Effect {
    private final org.bukkit.EntityEffect entityEffect;

    public EntityEffect(org.bukkit.EntityEffect entityEffect) {
        this.entityEffect = entityEffect;
    }

    @Override
    public String getIdentifier() {
        return entityEffect.toString();
    }

    @Override
    public String[] getOptionalData() {
        return new String[0];
    }

    @Override
    public void emmit(Player player) {
        player.playEffect(entityEffect);
    }
}
