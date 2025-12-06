package com.gr00ze.training.keybinding;

import com.gr00ze.training.screen.TrainingScreenForItemComponents;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBindingList {
    public static KeyBinding openScreenKey;

    public static void registerKeybindings() {
        openScreenKey = new KeyBinding(
                "key.trainingmod.openscreen",  // nome
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_J,              // tasto J
                "category.trainingmod"        // categoria
        );
        KeyBindingHelper.registerKeyBinding(openScreenKey);

        ClientTickEvents.END_CLIENT_TICK.register(KeyBindingList::onClientTick);
    }

    // 2. Tick listener
    public static void onClientTick(MinecraftClient client) {
        while (openScreenKey.wasPressed()) {
            client.setScreen(new TrainingScreenForItemComponents());
        }
    }
}
