package com.gr00ze.training.screen;

import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

import static com.gr00ze.training.util.RegisterFunctions.registerScreenType;

public class ScreenTypes {
    public static ScreenHandlerType<CustomScreenHandler> CUSTOM_SCREEN_TYPE = registerScreenType("custom_screen", CustomScreenHandler::new, FeatureFlags.DEFAULT_ENABLED_FEATURES);
    public static void initialize(){};
}
