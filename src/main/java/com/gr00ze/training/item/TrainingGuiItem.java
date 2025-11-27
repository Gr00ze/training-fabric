package com.gr00ze.training.item;

import com.gr00ze.training.screen.CustomScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TrainingGuiItem extends TrainingCustomItem{
    public TrainingGuiItem(Settings settings) {
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
