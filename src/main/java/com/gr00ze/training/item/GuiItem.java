package com.gr00ze.training.item;

import com.gr00ze.training.screen.CustomScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class GuiItem extends TrainingCustomItem{
    public GuiItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {


        user.openHandledScreen(new SimpleNamedScreenHandlerFactory(
                (syncId, stacks, player)-> new CustomScreenHandler(syncId, stacks),
                Text.literal("Title")));
        return ActionResult.CONSUME;
    }
}
