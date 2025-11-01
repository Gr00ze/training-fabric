package com.gr00ze.training.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;

import static com.gr00ze.training.entity.EntityTypeBuilderList.*;
import static com.gr00ze.training.util.RegisterFunctions.registerEntityType;
import static com.gr00ze.training.util.RegisterFunctions.registerMobAttribute;

/**
 * This class is meant to be a list of registered entities
 **/

public class EntityTypeList {
    public static EntityType<? extends MobEntity> DUMMY_MOB_ENTITY = registerEntityType("dummy",DUMMY_MOB_ENTITY_BUILDER);
    public static EntityType<? extends MobEntity> DUMMY_AI_ENTITY = registerEntityType("dummy_ai",DUMMY_AI_ENTITY_BUILDER);
    public static EntityType<? extends MobEntity> DUMMY_SOUND_ENTITY = registerEntityType("dummy_sound",DUMMY_SOUND_ENTITY_BUILDER);


    public static void initialize(){
        registerMobAttribute(DUMMY_MOB_ENTITY, DummyMobEntity.createDummyAttributes());
        registerMobAttribute(DUMMY_AI_ENTITY, DummyAIEntity.createDummyAttributes());
        registerMobAttribute(DUMMY_SOUND_ENTITY , DummySoundEntity.createDummyAttributes());




    }
}
