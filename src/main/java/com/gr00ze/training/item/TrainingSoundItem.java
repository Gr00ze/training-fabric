package com.gr00ze.training.item;

import com.gr00ze.training.sound.SoundList;
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

            return ActionResult.SUCCESS;

        }else{
            return ActionResult.PASS;

        }

        /*
         * SUCCESS = Executed, go next;
         * CONSUME = Executed, stop;
         * PASS = Not Executed, go next;
         * FAIL = Not Executed, stop
         * */

    }
}
