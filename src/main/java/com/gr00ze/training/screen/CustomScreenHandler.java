package com.gr00ze.training.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;


public class CustomScreenHandler extends ScreenHandler {

    public CustomScreenHandler(int syncId, PlayerInventory stacks) {
        super(ScreenTypes.CUSTOM_SCREEN_TYPE, syncId);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
