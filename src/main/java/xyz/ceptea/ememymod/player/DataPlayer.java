package xyz.ceptea.ememymod.player;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class DataPlayer {
    MinecraftClient mc = MinecraftClient.getInstance();
    String name;
    Entity entity;
    public DataPlayer(String name) {
        this.name = name;
        entity = getEntity();
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
