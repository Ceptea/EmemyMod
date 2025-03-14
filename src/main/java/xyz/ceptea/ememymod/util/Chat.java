package xyz.ceptea.ememymod.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Chat {
    static MinecraftClient mc = MinecraftClient.getInstance();
    public static void send(String text) {
        mc.player.sendMessage(Text.of(text.replace("&", "§")), false);
    }
}
