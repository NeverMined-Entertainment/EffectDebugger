package org.nevermined.effectdebugger.config;

import com.google.inject.Singleton;
import lombok.Getter;
import me.wyne.wutils.config.ConfigEntry;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
@Singleton
public class SoundEffectConfig {
    @ConfigEntry(section = "Effects.Sounds")
    private double defaultVolume = 1.0d;
    @ConfigEntry(section = "Effects.Sounds")
    private double defaultPitch = 1.0d;

    public float getDefaultVolume() {
        return (float) defaultVolume;
    }

    public float getDefaultPitch() {
        return (float) defaultPitch;
    }
}
