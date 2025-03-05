package com.gr00ze.training.block;

import com.gr00ze.training.BlockList;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TrainingCustomBlockEntity extends BlockEntity {
    private boolean LightOn = false;

    public TrainingCustomBlockEntity(BlockPos pos, BlockState state) {
        super(BlockList.TRAINING_BLOCK_ENTITY, pos, state);
    }


    public void doSomething(){
        LightOn = !LightOn;
    }

    public boolean isLightOn() {
        return LightOn;
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, TrainingCustomBlockEntity blockEntity) {

    }
}
