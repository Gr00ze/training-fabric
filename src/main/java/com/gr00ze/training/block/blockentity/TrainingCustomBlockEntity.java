package com.gr00ze.training.block.blockentity;

import com.gr00ze.training.block.BlockList;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TrainingCustomBlockEntity extends BlockEntity {
    private boolean isRotating = false;
    private float rotationAngle = 0.0f;

    public TrainingCustomBlockEntity(BlockPos pos, BlockState state) {
        super(BlockList.CUSTOM_BLOCK_ENTITY, pos, state);
    }


    public void changeRotationState(){
        isRotating = !isRotating;

    }

    public boolean isRotating() {
        return isRotating;
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, TrainingCustomBlockEntity blockEntity) {
        if (world == null || blockEntity == null || !blockEntity.isRotating) return;


        float time = world.getTime();

        float speedFactor = (float) (Math.sin(time * 0.1));


        float rotationSpeed = speedFactor * 20f;

        blockEntity.rotationAngle += rotationSpeed;



    }

    public float getRotationAngle() {
        return rotationAngle;
    }


}
