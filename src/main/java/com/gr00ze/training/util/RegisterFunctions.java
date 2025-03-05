package com.gr00ze.training.util;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;


import static com.gr00ze.training.Training.MOD_ID;


public class RegisterFunctions {

    public static Identifier id(String objectId){
        return Identifier.of(MOD_ID, objectId);
    }
    //BLOCK
    //WRONG

    public static Block registerBlock(String blockId, AbstractBlock.Settings settings){
        Identifier id = id(blockId);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        settings.registryKey(key);
        Block block = new Block(settings);
        return Registry.register(Registries.BLOCK, key, block);
    }
    //RIGHT
    @FunctionalInterface
    public interface BlockFactory<B extends Block> {
        B create(Block.Settings settings);
    }
    public static Block registerBlock(String blockId, BlockFactory<Block> factory , AbstractBlock.Settings settings){
        Identifier id = id(blockId);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        settings.registryKey(key);
        Block block = factory.create(settings);
        return Registry.register(Registries.BLOCK, key, block);


    }

    public static <B extends BlockEntity> BlockEntityType<B> registerBlockEntity(String blockEntityId, FabricBlockEntityTypeBuilder.Factory<B> factory, Block ...blocks){
        Identifier id = id(blockEntityId);
        RegistryKey<BlockEntityType<?>> key = RegistryKey.of(RegistryKeys.BLOCK_ENTITY_TYPE, id);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, key, FabricBlockEntityTypeBuilder.create(factory, blocks).build());
    }

    //ITEM
    private static RegistryKey<Item> registerItemKey(Item.Settings settings, Identifier id){
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        settings.registryKey(key);
        return key;
    }

    public static Item registerItem(String itemName, Item.Settings settings, Block block){
        RegistryKey<Item> key = registerItemKey(settings,id(itemName));
        BlockItem item = new BlockItem(block, settings);
        return Registry.register(Registries.ITEM, key, item);
    }

    @FunctionalInterface
    public interface ItemFactory<I extends Item> {
        I create(Item.Settings settings);
    }

    public static <I extends Item> Item registerItem(String itemName, ItemFactory<I> itemFactory, Item.Settings settings) {
        RegistryKey<Item> key = registerItemKey(settings, id(itemName));
        I item = itemFactory.create(settings);
        return Registry.register(Registries.ITEM, key, item);
    }


    //ITEM GROUP
    public static RegistryKey<ItemGroup> registerItemGroup(String groupName, ItemGroup itemGroup){
        RegistryKey<ItemGroup> key = RegistryKey.of(RegistryKeys.ITEM_GROUP, id(groupName));
        Registry.register(Registries.ITEM_GROUP, key, itemGroup);
        return key;
    }

    //ENTITY
    public static EntityType<? extends MobEntity> registerEntityType(String entityID, EntityType.Builder<? extends MobEntity> entityBuilder){
        Identifier identifier = id(entityID);
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, identifier);
        return Registry.register(Registries.ENTITY_TYPE, key, entityBuilder.build(key));
    };
    
    public static void registerMobAttribute(EntityType<? extends MobEntity> entityType, DefaultAttributeContainer.Builder builder){
        FabricDefaultAttributeRegistry.register(entityType, builder.build());
    }

    public static SoundEvent registerSound(String soundName){
        Identifier identifier = id(soundName);
        RegistryKey<SoundEvent> key = RegistryKey.of(RegistryKeys.SOUND_EVENT, identifier);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }
}
