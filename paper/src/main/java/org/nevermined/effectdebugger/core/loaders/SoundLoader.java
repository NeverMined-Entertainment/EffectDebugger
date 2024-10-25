package org.nevermined.effectdebugger.core.loaders;

import org.bukkit.Sound;
import org.nevermined.effectdebugger.core.effects.SoundEffect;

import java.util.HashMap;
import java.util.Map;

public class SoundLoader implements EffectLoader<SoundEffect> {
    @Override
    public Map<String, SoundEffect> loadEffects() {
        Map<String, SoundEffect> sounds = new HashMap<>();
        for (Sound sound : Sound.values())
        {
            sounds.put(sound.toString(), new SoundEffect(sound));
        }
        return sounds;
    }

}
