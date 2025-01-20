package com.gr00ze.training.block;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public class TrainingCustomBlock extends Block {


    public TrainingCustomBlock(Settings settings) {
        super(settings);
    }

    public static  AbstractBlock.Settings getBlockSettings(){
        return AbstractBlock.Settings.create();
    }





}
