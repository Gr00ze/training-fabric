package com.gr00ze.training.item;

import net.minecraft.item.Item;

import static com.gr00ze.training.BlockList.*;
import static com.gr00ze.training.util.RegisterFunctions.*;

public class ItemList{
 public static final Item TRAINING_ITEM = registerItem("training_item", Item::new, new Item.Settings());
 public static final Item TRAINING_BLOCK_ITEM = registerItem(new Item.Settings(), id("training_block_item"), TRAINING_BLOCK);
 public static final Item TRAINING_CUSTOM_ITEM = registerItem("training_custom_item", TrainingCustomItem::new, TrainingCustomItem.getItemSetting());
 public static final Item TRAINING_SOUND_ITEM = registerItem("training_sound_item", TrainingSoundItem::new ,TrainingSoundItem.getItemSetting());

 public static void initialize(){}




}
