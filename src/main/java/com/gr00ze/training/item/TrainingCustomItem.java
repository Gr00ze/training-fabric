package com.gr00ze.training.item;

import net.minecraft.item.Item;

public class TrainingCustomItem extends Item {
    public TrainingCustomItem(Settings settings) {
        super(settings);
    }

    public static Item.Settings getItemSetting(){
        return new Settings();
    }


}
