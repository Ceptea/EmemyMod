package xyz.ceptea.ememymod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class MixinChatHud {
	@Inject(at = @At("HEAD"), method = "addMessage(Lnet/minecraft/text/Text;)V")
	private void addMessage(Text message, CallbackInfo ci) {
		String m = message.getLiteralString();
	}
}