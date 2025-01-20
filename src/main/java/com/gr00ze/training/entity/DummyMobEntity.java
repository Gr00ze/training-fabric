package com.gr00ze.training.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class DummyMobEntity extends PathAwareEntity {
    public DummyMobEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.initGoals();
    }

    public static DefaultAttributeContainer.Builder createDummyAttributes(){
        return createMobAttributes();
    }

    public static EntityType.Builder<? extends PathAwareEntity>  getBuilder(){
        return EntityType.Builder.create(DummyMobEntity::new, SpawnGroup.CREATURE);
    }

    @Override
    public void tick() {
        super.tick();

    }


}
