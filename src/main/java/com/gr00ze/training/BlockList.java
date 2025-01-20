package com.gr00ze.training;

import com.gr00ze.training.block.TrainingCustomBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

import static com.gr00ze.training.Training.MOD_ID;
import static com.gr00ze.training.util.RegisterFunctions.id;
import static com.gr00ze.training.util.RegisterFunctions.registerBlock;

public class BlockList {

    public static final Block
        TRAINING_BLOCK = registerBlock(AbstractBlock.Settings.create(), id("training_block")),
        TRAINING_CUSTOM_BLOCK = registerBlock(TrainingCustomBlock.getBlockSettings(), id("training_custom_block"));




    public static void initialize(){}
}
