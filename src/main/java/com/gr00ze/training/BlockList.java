package com.gr00ze.training;

import com.gr00ze.training.block.TrainingCustomBlock;
import com.gr00ze.training.block.TrainingCustomBlockWithEntity;
import com.gr00ze.training.block.blockentity.TrainingCustomBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;

import static com.gr00ze.training.util.RegisterFunctions.*;

public class BlockList {

    public static final Block
        //SIMPLE BLOCK WITH SIMPLE SETTINGS
        SIMPLE_BLOCK = registerBlock("simple_block", AbstractBlock.Settings.create()),
        //BLOCK WITH CUSTOM CLASS AND CUSTOM SETTINGS AND ITEM WITH DEFAULT SETTINGS
        CUSTOM_BLOCK = registerBlockAndItem("custom_block", TrainingCustomBlock::new, TrainingCustomBlock.getBlockSettings(),new Item.Settings()),
        //BLOCK WITH BLOCK ENTITY AND CUSTOM SETTINGS
        CUSTOM_BLOCK_WITH_ENTITY = registerBlockAndItem("custom_block_with_entity", TrainingCustomBlockWithEntity::new, TrainingCustomBlockWithEntity.getBlockSettings(), new Item.Settings());


    //BLOCK ENTITY
    public static final BlockEntityType<TrainingCustomBlockEntity> CUSTOM_BLOCK_ENTITY = registerBlockEntity("custom_block_entity",TrainingCustomBlockEntity::new, CUSTOM_BLOCK_WITH_ENTITY);



    public static void initialize(){}
}
