package xyz.ceptea.ememymod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import xyz.ceptea.ememymod.ememy.Ememy;
import xyz.ceptea.ememymod.player.DataPlayers;

public class EmemyScreen extends Screen {
    protected EmemyScreen() {
        super(Text.of("Ememy Screen"));
    }

    @Override
    protected void init() {
        super.init();
        MinecraftClient mc = MinecraftClient.getInstance();
        var w2 = new TextFieldWidget(mc.textRenderer,100,20,Text.of(""));
        var w = ButtonWidget.builder(Text.literal("Add Ememy"), button -> {
            Ememymod.ememies.add(w2.getText());
            mc.setScreen(new EmemyScreen());
        }).dimensions(16,16, 100, 20).build();
        w2.setPlaceholder(Text.of("Ememy Name"));
        w2.setX(16);
        w2.setX(100+16+4);
        w2.setY(16);
        addDrawableChild(w);
        addDrawableChild(w2);

        int y = 20*2+4;
        for (Ememy ememy: Ememymod.ememies.getEmemies()) {
            var text = ememy.getName();
            if (Ememymod.players.isOnPlayerList(ememy.getName())) {
                text = "§a" + text;
            } else {
                text = "§c" + text;
            }
            var emb = ButtonWidget.builder(Text.of(text), button -> {
                Ememymod.ememies.remove(ememy.getName());
                mc.setScreen(new EmemyScreen());
            }).dimensions(16,y, 100, 20).build();
            y +=20+4;
            addDrawableChild(emb);
        }



    }
}
