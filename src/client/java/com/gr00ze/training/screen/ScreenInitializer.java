package com.gr00ze.training.screen;

import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ScreenInitializer {
    public static void initialize(){
        HandledScreens.register(ScreenTypes.CUSTOM_SCREEN_TYPE, TrainingHandledScreen::new);
    }
}
