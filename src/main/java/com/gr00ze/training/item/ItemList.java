package com.gr00ze.training.item;

import net.minecraft.item.Item;

import static com.gr00ze.training.BlockList.*;
import static com.gr00ze.training.util.RegisterFunctions.*;

public class ItemList{
 //SIMPLE ITEM WITH SIMPLE SETTINGS
 public static final Item SIMPLE_ITEM = registerItem("simple_item");
 //ITEM WITH CUSTOM CLASS AND CUSTOM SETTINGS
 public static final Item CUSTOM_ITEM = registerItem("custom_item", TrainingCustomItem::new, TrainingCustomItem.getItemSetting());
 //CUSTOM ITEM WITH SOUND BEHAVIOUR
 public static final Item CUSTOM_SOUND_ITEM = registerItem("custom_sound_item", TrainingSoundItem::new ,TrainingSoundItem.getItemSetting());

 //ITEM TO PLACE A BLOCK WITH SIMPLE SETTINGS
 public static final Item SIMPLE_BLOCK_ITEM = registerBlockItem( "simple_block_item",SIMPLE_BLOCK);
 public static final Item CUSTOM_BLOCK_ITEM = CUSTOM_BLOCK.asItem();

 public static final Item CUSTOM_BLOCK_WITH_ENTITY_ITEM = CUSTOM_BLOCK_WITH_ENTITY.asItem();
 public static final Item CUSTOM_BLOCK_ALTERNATIVE_ITEM = CUSTOM_BLOCK_ALTERNATIVE.asItem();

 public static void initialize(){}

}
