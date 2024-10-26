package org.nevermined.effectdebugger.core.effects;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("LombokGetterMayBeUsed")
public class TheEffect implements Effect {
    @Getter private final org.bukkit.Effect effect;
    private final String[] optionalData = new String[2];

    public TheEffect(org.bukkit.Effect effect) {
        this.effect = effect;
        optionalData[0] = "Type: " + effect.getType();
        optionalData[1] = "Data: " + (effect.getData() != null ? effect.getData().getTypeName() : "null");
    }

    @Override
    public String getIdentifier() {
        return effect.toString();
    }

    @Override
    public String[] getOptionalData() {
        return optionalData;
    }

    @Override
    public boolean isDataRequired() {
        return effect.getData() != null;
    }

    @Override
    public EffectType getType() {
        return EffectType.THE_EFFECT;
    }

    @Override
    public void emmit(Player player) {
        player.playEffect(player.getLocation(), effect, null);
    }

    public <T> void emmit(Player player, @Nullable T data)
    {
        player.playEffect(player.getLocation(), effect, data);
    }
}
