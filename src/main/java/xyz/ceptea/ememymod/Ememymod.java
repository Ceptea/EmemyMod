package xyz.ceptea.ememymod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.ceptea.ememymod.ememy.EmemyManager;
import xyz.ceptea.ememymod.player.DataPlayers;
import xyz.ceptea.ememymod.util.Config;

public class Ememymod implements ModInitializer {
	public static final String MOD_ID = "ememymod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static EmemyManager ememies = new EmemyManager();
	static DataPlayers players = new DataPlayers();
	KeyBinding openEmemyGui = KeyBindingHelper.registerKeyBinding(new KeyBinding(
        "Open Ememy Gui",
		InputUtil.Type.KEYSYM,
		GLFW.GLFW_KEY_K,
        "Ememy Mod"
	));
	@Override
	public void onInitialize() {
		ememies = Config.load();
		if (ememies == null) {
			ememies = new EmemyManager();
			LOGGER.info("No config found..");

		}
		Config.save();



		ClientTickEvents.END_CLIENT_TICK.register(mc -> {
			if (openEmemyGui.isPressed()) {
				mc.setScreen(new EmemyScreen());
			}
			players.onTick();
		});
	}
}