package com.gr00ze.training;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

import static com.gr00ze.training.Training.MOD_ID;
import static com.gr00ze.training.util.RegisterFunctions.registerBlock;

public class BlockList {

    public static final
    Block CAOS_BLOCK = registerBlock(AbstractBlock.Settings.create(), Identifier.of(MOD_ID, "caos_block"));




    public static void initialize(){}
}
