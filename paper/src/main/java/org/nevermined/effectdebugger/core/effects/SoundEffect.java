package org.nevermined.effectdebugger.core.effects;

import net.kyori.adventure.key.Key;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.nevermined.effectdebugger.EffectDebugger;

public class SoundEffect implements KeyedEffect {
    private final Sound sound;
    private final String[] optionalData = new String[1];

    public SoundEffect(Sound sound) {
        this.sound = sound;
        optionalData[0] = "Key: " + getKey().asString();
    }

    @Override
    public String getIdentifier() {
        return sound.toString();
    }

    @Override
    public String[] getOptionalData() {
        return optionalData;
    }

    @Override
    public void emmit(Player player) {
        player.playSound(net.kyori.adventure.sound.Sound.sound(getKey(), net.kyori.adventure.sound.Sound.Source.MASTER,
                EffectDebugger.getGlobalConfig().soundEffectConfig().getDefaultVolume(), EffectDebugger.getGlobalConfig().soundEffectConfig().getDefaultPitch()));
    }

    public void emmit(Player player, float volume, float pitch)
    {
        player.playSound(net.kyori.adventure.sound.Sound.sound(getKey(), net.kyori.adventure.sound.Sound.Source.MASTER, volume, pitch));
    }

    @Override
    public Key getKey() {
        return sound.key();
    }
}
