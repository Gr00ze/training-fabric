package com.gr00ze.training.provider;

import com.gr00ze.training.BlockList;
import com.gr00ze.training.item.ItemList;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;


public class TrainingModelProvider extends FabricModelProvider {
    public TrainingModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(BlockList.TRAINING_BLOCK);



    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemList.TRAINING_ITEM, Models.GENERATED);
        itemModelGenerator.register(ItemList.TRAINING_BLOCK_ITEM, Models.GENERATED);//CUBE NOT WORKING
        itemModelGenerator.register(ItemList.TRAINING_CUSTOM_ITEM, Models.GENERATED);
        itemModelGenerator.register(ItemList.TRAINING_SOUND_ITEM, Models.GENERATED);

    }



}
