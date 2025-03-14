package xyz.ceptea.ememymod.player;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import xyz.ceptea.ememymod.ememy.Ememy;
import xyz.ceptea.ememymod.mixin.AccessorSoundInstance;
import xyz.ceptea.ememymod.util.Chat;

public class DataPlayer {
    MinecraftClient mc = MinecraftClient.getInstance();
    String name;
    Entity entity;
    public DataPlayer(String name) {
        this.name = name;
        entity = getEntity();

        if (entity !=null) {
            if (Ememy.with(this.getName())) {
                Chat.send(String.format("&c%s entered your render distance.",
                        this.getName()));
                var master = PositionedSoundInstance.master(SoundEvents.BLOCK_NOTE_BLOCK_BELL, 1.0F);
                AccessorSoundInstance vol = (AccessorSoundInstance) master;
                vol.setVolume(2500);
                mc.getSoundManager().play(master);
            }

        }

    }

    public PlayerEntity getEntity() {
        for (Entity entity: mc.world.getEntities()) {
            if (entity instanceof PlayerEntity player) {
                if (player.getGameProfile().getName().equalsIgnoreCase(name)) {
                    return player;
                }
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public boolean isPresentInWorld() {
        return getEntity() != null;
    }
}
