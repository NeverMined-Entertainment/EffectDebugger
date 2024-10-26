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
    public boolean isDataRequired() {
        return false;
    }

    @Override
    public EffectType getType() {
        return EffectType.ENTITY_EFFECT;
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public String getCommand() {
        StringBuilder builder = new StringBuilder();
        builder.append("edebug ").append(getType().getEffectType());
        builder.append(" ").append(getSuggestion());
        return builder.toString();
    }

    @Override
    public void emmit(Player player) {
        player.playEffect(entityEffect);
    }
}
