package com.gr00ze.training.item;

import com.gr00ze.training.SoundList;
import com.gr00ze.training.entity.DummySoundEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class TrainingSoundItem extends TrainingCustomItem{
    public TrainingSoundItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity instanceof DummySoundEntity soundEntity){
                soundEntity.playSound(SoundList.TRAINING_SOUND);

            return ActionResult.CONSUME;
        }else{
            return ActionResult.PASS;

        }

    }
}
