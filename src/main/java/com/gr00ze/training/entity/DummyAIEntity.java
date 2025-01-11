package com.gr00ze.training.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class DummyAIEntity extends DummyMobEntity{
    public DummyAIEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }


    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new DummyGoal());
    }

    static class DummyGoal extends Goal {


        @Override
        public boolean canStart() {
            return false;
        }
    }
}
