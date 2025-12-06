package com.gr00ze.training.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;

import static com.gr00ze.training.util.RegisterFunctions.registerItemGroup;

public class TrainingItemGroup {
    //Step 1 choose the id
    public static final String GROUP_ID = "group.training";// TODO Try display name and registry key different
    //Step 2 create the group
    public static final ItemGroup TRAINING_ITEM_GROUP = FabricItemGroup.builder()
            .displayName(Text.translatable(GROUP_ID))
            .build();
    //Step 3 create the key and associate the group
    public static final RegistryKey<ItemGroup> TRAINING_ITEM_GROUP_KEY = registerItemGroup(GROUP_ID,TRAINING_ITEM_GROUP);
    //Step 4 create the list of the item to add
    static void addItems(FabricItemGroupEntries itemGroup){
        //ITEM
        itemGroup.add(ItemList.SIMPLE_ITEM);
        itemGroup.add(ItemList.CUSTOM_ITEM);
        itemGroup.add(ItemList.CUSTOM_SOUND_ITEM);
        itemGroup.add(ItemList.CUSTOM_SCREEN_ITEM);
        itemGroup.add(ItemList.CUSTOM_RENDERING_ITEM);
        //BLOCK ITEM
        itemGroup.add(ItemList.SIMPLE_BLOCK_ITEM);
        itemGroup.add(ItemList.CUSTOM_BLOCK_ITEM);
        itemGroup.add(ItemList.CUSTOM_BLOCK_WITH_ENTITY_ITEM);
        itemGroup.add(ItemList.CUSTOM_BLOCK_ALTERNATIVE_ITEM);



        //YOU CAN GET YOUR BLOCK ITEM FROM THE BLOCK
        //BUT THE BLOCK DON'T HAVE ONE IT WILL CRASH


    }


    public static void initialize() {
        //Step 5 insert the items on the group
        ItemGroupEvents.modifyEntriesEvent(TRAINING_ITEM_GROUP_KEY).register(TrainingItemGroup::addItems);
    }
}
