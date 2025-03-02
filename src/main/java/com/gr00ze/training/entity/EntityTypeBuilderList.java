package com.gr00ze.training.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class EntityTypeBuilderList {

    public static EntityType.Builder<DummyMobEntity> DUMMY_MOB_ENTITY_BUILDER = EntityType.Builder.create(DummyMobEntity::new, SpawnGroup.CREATURE);
    public static EntityType.Builder<DummyAIEntity> DUMMY_AI_ENTITY_BUILDER = EntityType.Builder.create(DummyAIEntity::new, SpawnGroup.CREATURE);
    public static EntityType.Builder<DummySoundEntity> DUMMY_SOUND_ENTITY_BUILDER = EntityType.Builder.create(DummySoundEntity::new, SpawnGroup.CREATURE).dimensions(2,2);
}
