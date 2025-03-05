package com.gr00ze.training;

import com.gr00ze.training.block.TrainingCustomBlock;
import com.gr00ze.training.block.TrainingCustomBlockWithEntity;
import com.gr00ze.training.block.TrainingCustomBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;


import static com.gr00ze.training.util.RegisterFunctions.registerBlock;
import static com.gr00ze.training.util.RegisterFunctions.registerBlockEntity;

public class BlockList {

    public static final Block
        TRAINING_BLOCK = registerBlock("training_block", AbstractBlock.Settings.create()),
        TRAINING_CUSTOM_BLOCK = registerBlock("training_custom_block", TrainingCustomBlock::new, TrainingCustomBlock.getBlockSettings()),
        TRAINING_BLOCK_WITH_ENTITY = registerBlock("training_custom_block_with_entity", TrainingCustomBlockWithEntity::new, TrainingCustomBlockWithEntity.getBlockSettings());

    public static final BlockEntityType<TrainingCustomBlockEntity> TRAINING_BLOCK_ENTITY = registerBlockEntity("training_custom_block_entity",TrainingCustomBlockEntity::new, TRAINING_BLOCK_WITH_ENTITY);



    public static void initialize(){}
}
