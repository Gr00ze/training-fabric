package com.gr00ze.training.provider;

import com.gr00ze.training.BlockList;
import com.gr00ze.training.entity.EntityTypeList;
import com.gr00ze.training.item.ItemList;
import com.gr00ze.training.item.TrainingItemGroup;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class TrainingLangProvider extends FabricLanguageProvider {
    public TrainingLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);

    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ItemList.SIMPLE_ITEM, "Training item");
        translationBuilder.add(ItemList.SIMPLE_BLOCK_ITEM, "Training block item");
        translationBuilder.add(ItemList.CUSTOM_ITEM, "Training custom item");
        translationBuilder.add(ItemList.CUSTOM_SOUND_ITEM, "Training sound item");

        translationBuilder.add(BlockList.SIMPLE_BLOCK, "Training block");

        translationBuilder.add(EntityTypeList.DUMMY_MOB_ENTITY, "Dummy mob entity");
        translationBuilder.add(EntityTypeList.DUMMY_AI_ENTITY, "Dummy ai entity");
        translationBuilder.add(EntityTypeList.DUMMY_SOUND_ENTITY, "Dummy sound entity");

        translationBuilder.add(TrainingItemGroup.TRAINING_ITEM_GROUP_KEY, "Training Item Group");



    }
}
