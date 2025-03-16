package com.gr00ze.training.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class TrainingCustomBlockItem extends BlockItem {
    public TrainingCustomBlockItem(Block block, Settings settings) {
        super(block, settings);
    }
    public static Item.Settings getItemSetting(){
        return new Settings();
    }
}
