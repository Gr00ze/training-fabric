package com.gr00ze.training.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.MobEntity;

import static com.gr00ze.training.util.RegisterFunctions.registerEntityType;
import static com.gr00ze.training.util.RegisterFunctions.registerMobAttribute;

public class EntityTypeList {
    public static EntityType<? extends MobEntity> DUMMY_MOB_ENTITY = registerEntityType("dummy",DummyMobEntity.getBuilder());
    public static EntityType<? extends MobEntity> DUMMY_AI_ENTITY = registerEntityType("dummy_ai",DummyAIEntity.getBuilder());
    public static EntityType<? extends MobEntity> DUMMY_SOUND_ENTITY = registerEntityType("dummy_sound",EntityType.Builder.create(DummySoundEntity::new, SpawnGroup.CREATURE).dimensions(2,2));


    public static void initialize(){
        registerMobAttribute(DUMMY_MOB_ENTITY, DummyMobEntity.createDummyAttributes());
        registerMobAttribute(DUMMY_AI_ENTITY, DummyMobEntity.createDummyAttributes());
        registerMobAttribute(DUMMY_SOUND_ENTITY , DummyMobEntity.createDummyAttributes());




    }
}
