package com.gr00ze.training.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;

public class TrainingScreen extends Screen {

    protected TrainingScreen() {
        super(Text.literal("Training Screen"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context); // sfondo semi-trasparente
        context.fill(50, 50, 200, 150, 0x88000000); // rettangolo grigio
        context.drawText(this.textRenderer, "Hello Screen!", 60, 60, 0xFFFFFFFF, false);

        // Esempio: disegna un item
        ItemStack stack = new ItemStack(Items.DIAMOND);
        context.drawItem(stack, 70, 80);

        super.render(context, mouseX, mouseY, delta);
    }


    public void renderBackground(DrawContext context) {
        context.fill(0, 0, this.width, this.height, 0xFF202020); // sfondo uniforme
    }
}

