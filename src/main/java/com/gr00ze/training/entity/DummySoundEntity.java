package com.gr00ze.training.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class DummySoundEntity extends DummyMobEntity{

    public static final TrackedData<Boolean> IS_PLAYING_SOUND =
            DataTracker.registerData(DummySoundEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public DummySoundEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(IS_PLAYING_SOUND, false);
    }
}
