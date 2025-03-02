package com.gr00ze.training.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DummyAIEntity extends DummyMobEntity{
    public DummyAIEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();

    }


    //TO ADD GOALS
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new DummyGoal(this));
    }



    //AI GOAL
    static class DummyGoal extends Goal {
        public DummyAIEntity dummyAIEntity;

        public DummyGoal(DummyAIEntity dummyAIEntity) {
            this.dummyAIEntity = dummyAIEntity;
        }

        @Override
        public boolean canStart() {
            BlockPos pos = new BlockPos(dummyAIEntity.getBlockPos().add(0,-1,0));
            return dummyAIEntity.getWorld().getBlockState(pos) == Blocks.WHITE_WOOL.getDefaultState();
        }

        @Override
        public boolean shouldContinue() {
            return super.shouldContinue();
        }

        @Override
        public boolean canStop() {
            return false;
        }

        @Override
        public void tick() {
            super.tick();
            dummyAIEntity.moveControl.moveTo(dummyAIEntity.getX()+1,dummyAIEntity.getY(),dummyAIEntity.getZ(),1);
        }
    }
}
