package com.gr00ze.training.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class TrainingHandledScreen extends HandledScreen<CustomScreenHandler> implements ScreenHandlerProvider<CustomScreenHandler> {
    public TrainingHandledScreen(CustomScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        // Fill background gray
        context.fill(this.x, this.y, this.x + this.backgroundWidth, this.y + this.backgroundHeight, 0x88000000);

        // Red rect
        context.fill(this.x + 30, this.y + 30, this.x + 130, this.y + 80, 0xFFFF0000);

        // White text
        context.drawText(this.textRenderer, "Hello GUI!", this.x + 40, this.y + 50, 0xFFFFFFFF, false);
    }

    @Override
    public CustomScreenHandler getScreenHandler() {
        return this.handler;
    }
}
