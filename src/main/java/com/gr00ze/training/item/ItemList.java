package com.gr00ze.training.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import static com.gr00ze.training.BlockList.CAOS_BLOCK;
import static com.gr00ze.training.util.RegisterFunctions.*;

public class ItemList{
 public static final Item TRAINING_ITEM = registerItem("caos_item", Item::new, new Item.Settings());
 public static final Item TRAINING_BLOCK_ITEM = registerItem(new Item.Settings(), id("caos_block_item"), CAOS_BLOCK);
 public static final Item TRAINING_CUSTOM_ITEM = registerItem("training_item", TrainingCustomItem::new, new Item.Settings());
 public static final Item TRAINING_SOUND_ITEM = registerItem("training_sound_item", TrainingSoundItem::new ,new Item.Settings());

 public static void initialize(){}




}
