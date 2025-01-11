package com.gr00ze.training.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;

public class TrainingScreen extends Screen{
    Screen parent = null;
    public TrainingScreen(Text title) {
        super(title);
    }

    public TrainingScreen(Text title, Screen parent) {
        super(title);
        this.parent = parent;
    }
    @Override
    protected void init() {
        super.init();
        addDrawable(ButtonWidget.builder(Text.of("Button Name"), (buttonWidget)->
                this.client.getToastManager()
                        .add(SystemToast.create(this.client, SystemToast.Type.PERIODIC_NOTIFICATION, Text.of("Template Mod"), Text.of("Example Description"))))
                .dimensions(10,10,100,10).build());

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawText(this.textRenderer, "Example text", 10, 10, 0XFF0000, true);

    }

    @Override
    public void close() {
        this.client.setScreen(parent);
    }
}
