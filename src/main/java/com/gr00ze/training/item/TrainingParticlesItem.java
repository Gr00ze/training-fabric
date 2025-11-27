package com.gr00ze.training.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TrainingParticlesItem extends Item {
    public TrainingParticlesItem(Settings settings) {
        super(settings);
    }

    public static Settings getItemSetting(){
        return new Settings();
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }
}
