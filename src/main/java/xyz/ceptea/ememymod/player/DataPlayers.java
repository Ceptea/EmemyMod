package xyz.ceptea.ememymod.player;

import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.Sound;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import xyz.ceptea.ememymod.ememy.Ememy;
import xyz.ceptea.ememymod.mixin.AccessorSoundInstance;
import xyz.ceptea.ememymod.util.Chat;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DataPlayers {
    ConcurrentLinkedQueue<DataPlayer> players = new ConcurrentLinkedQueue<>();
    MinecraftClient mc = MinecraftClient.getInstance();
    public void onTick() {

        var mc = MinecraftClient.getInstance();
        if (mc.getNetworkHandler() == null) { return; }
        for (PlayerListEntry entry: mc.getNetworkHandler().getPlayerList()) {
            var name = entry.getProfile().getName();
            if (contains(name)) {
            } else {
                if (Ememy.with(name)) {
                    Chat.send(String.format("&c%s &ejoined the game", name));
                }
                add(name);
            }
        }

        for (String player: getAllNames()) {
            if (!isOnPlayerList(player)) {
                players.remove(player);
            }
        }

        for (DataPlayer dataPlayer: players) {
            if (dataPlayer.entity !=dataPlayer.getEntity()) {
                if (dataPlayer.isPresentInWorld()) {
                    if (Ememy.with(dataPlayer.getName())) {
                        Chat.send(String.format("&c%s entered your render distance.",
                                dataPlayer.getName()));
                    }
                    var master = PositionedSoundInstance.master(SoundEvents.BLOCK_NOTE_BLOCK_BELL, 1.0F);
                    AccessorSoundInstance vol = (AccessorSoundInstance) master;
                    vol.setVolume(2500);
                    mc.getSoundManager().play(master);
                } else {
                    if (Ememy.with(dataPlayer.getName())) {
                        Chat.send(String.format("&c%s left your render distance.",
                                dataPlayer.getName()));
                    }
                }

                dataPlayer.entity = dataPlayer.getEntity();

            }



        }
    }

    public boolean isOnPlayerList(String name) {
        for (PlayerListEntry entry: mc.getNetworkHandler().getPlayerList()) {
            if (entry.getProfile().getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(String name) {
        for (DataPlayer dataPlayer: players) {
            if (dataPlayer.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getAllNames() {
        ArrayList<String> names = new ArrayList<>();
        for (DataPlayer dataPlayer: players) {
            names.add(dataPlayer.getName());
        }
        return names;
    }

    public void add(String name) {
        players.add(new DataPlayer(name));
    }

}
