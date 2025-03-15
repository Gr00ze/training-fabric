package com.gr00ze.training.item;

import com.gr00ze.training.BlockList;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;

import static com.gr00ze.training.util.RegisterFunctions.registerItemGroup;

public class TrainingItemGroup {

    public static final String GROUP_ID = "group.training";// TODO Try display name and registry key different
    public static final ItemGroup TRAINING_ITEM_GROUP = FabricItemGroup.builder()
            .displayName(Text.translatable(GROUP_ID))
            .build();
    public static final RegistryKey<ItemGroup> TRAINING_ITEM_GROUP_KEY = registerItemGroup(GROUP_ID,TRAINING_ITEM_GROUP);

    static void addItems(FabricItemGroupEntries itemGroup){
        //ITEM
        itemGroup.add(ItemList.SIMPLE_ITEM);
        itemGroup.add(ItemList.CUSTOM_ITEM);
        itemGroup.add(ItemList.CUSTOM_SOUND_ITEM);
        //BLOCK ITEM
        itemGroup.add(ItemList.SIMPLE_BLOCK_ITEM);
        itemGroup.add(BlockList.CUSTOM_BLOCK.asItem());
        itemGroup.add(BlockList.CUSTOM_BLOCK_WITH_ENTITY.asItem());



        //YOU CAN GET YOUR BLOCK ITEM FROM THE BLOCK
        //BUT THE BLOCK DON'T HAVE ONE IT WILL CRASH


    }


    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(TRAINING_ITEM_GROUP_KEY).register(TrainingItemGroup::addItems);
    }
}
