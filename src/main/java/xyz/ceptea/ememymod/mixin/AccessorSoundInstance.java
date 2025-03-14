package xyz.ceptea.ememymod.mixin;

import net.minecraft.client.sound.AbstractSoundInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
@Mixin(AbstractSoundInstance.class)
public interface AccessorSoundInstance {
    @Accessor("volume")
    void setVolume(float volume);
}
