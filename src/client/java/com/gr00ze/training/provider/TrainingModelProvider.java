package com.gr00ze.training.provider;

import com.gr00ze.training.block.BlockList;
import com.gr00ze.training.item.ItemList;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;


public class TrainingModelProvider extends FabricModelProvider {
    public TrainingModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(BlockList.SIMPLE_BLOCK);
        blockStateModelGenerator.registerSimpleState(BlockList.CUSTOM_BLOCK);
        blockStateModelGenerator.registerSimpleState(BlockList.CUSTOM_BLOCK_WITH_ENTITY);
        blockStateModelGenerator.registerSimpleState(BlockList.CUSTOM_BLOCK_ALTERNATIVE);



    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemList.SIMPLE_ITEM, Models.GENERATED);
        itemModelGenerator.register(ItemList.CUSTOM_ITEM, Models.GENERATED);
        itemModelGenerator.register(ItemList.CUSTOM_SOUND_ITEM, Models.GENERATED);
        itemModelGenerator.register(ItemList.CUSTOM_SCREEN_ITEM, Models.GENERATED);
        itemModelGenerator.register(ItemList.ROPE_ITEM, Models.GENERATED);
    }



}
