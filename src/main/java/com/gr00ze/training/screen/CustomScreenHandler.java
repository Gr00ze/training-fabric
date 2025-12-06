package com.gr00ze.training.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;


public class CustomScreenHandler extends ScreenHandler {
    private final Inventory customInventory;
    public CustomScreenHandler(int syncId, PlayerInventory stacks) {

        super(ScreenTypes.CUSTOM_SCREEN_TYPE, syncId);

        // Creo un inventario locale da 2 slot
        this.customInventory = new SimpleInventory(2);

        // Slot personalizzati
        this.addSlot(new Slot(customInventory, 0, 44, 20)); // esempio posizione x=44, y=20
        this.addSlot(new Slot(customInventory, 1, 44, 36));

        // Slot per l'inventario del giocatore (3 righe x 9 colonne)
        int startX = 8;
        int startY = 84;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(stacks, col + row * 9 + 9, startX + col * 18, startY + row * 18));
            }
        }

        // Hotbar del giocatore (9 slot)
        int hotbarY = startY + 58;
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(stacks, col, startX + col * 18, hotbarY));
        }
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        if (!player.getWorld().isClient) {
            // Esempio: restituisci gli item del custom inventory al giocatore
            for (int i = 0; i < customInventory.size(); i++) {
                ItemStack stack = customInventory.getStack(i);
                if (!stack.isEmpty()) {
                    player.getInventory().offerOrDrop(stack);
                }
            }
        }
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
