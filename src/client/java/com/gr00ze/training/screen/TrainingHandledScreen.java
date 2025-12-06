package com.gr00ze.training.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.joml.Matrix3x2fStack;

public class TrainingHandledScreen extends HandledScreen<CustomScreenHandler> {
    public TrainingHandledScreen(CustomScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, Text.literal("Training Title"));



    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        // Fill background gray
        context.fill(this.x, this.y, this.x + this.backgroundWidth, this.y + this.backgroundHeight, 0x88000000);

        // Red border
        context.drawBorder(this.x, this.y, this.backgroundWidth, this.backgroundHeight, 0xFFFF0000);

        // White text
        int fontHeight = this.textRenderer.fontHeight;
        context.drawText(this.textRenderer, "Input", this.x + 70, this.y + 20 + fontHeight/2, 0xFFFFFFFF, true);
        context.drawText(this.textRenderer, "Output", this.x + 70, this.y + 36 + fontHeight/2, 0xFFFFFFFF, true);

        //
        Matrix3x2fStack matrixStack = context.getMatrices();
        matrixStack.pushMatrix();
        matrixStack.scale(1.8F, matrixStack);
        matrixStack.translate(1,-10, matrixStack);
        context.drawItem(this.handler.getSlot(0).getStack(), this.x+10, this.y+20);
        matrixStack.popMatrix();
    }

    @Override
    protected void drawSlots(DrawContext context) {
        super.drawSlots(context);



    }

    @Override
    protected void drawSlot(DrawContext context, Slot slot) {
        super.drawSlot(context, slot);
        context.fill(slot.x, slot.y, slot.x + 16, slot.y + 16, 0x88444444);
    }

    @Override
    public CustomScreenHandler getScreenHandler() {
        return this.handler;
    }
}
