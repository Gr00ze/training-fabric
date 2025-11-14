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
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;


import java.util.function.BiFunction;
import java.util.function.Function;

import static com.gr00ze.training.Training.MOD_ID;


public class RegisterFunctions {

    public static Identifier id(String objectId){
        return Identifier.of(MOD_ID, objectId);
    }
    //BLOCK

    private static RegistryKey<Block> registerBlockKey(Identifier id,Block.Settings settings){
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, id);
        settings.registryKey(key);
        return key;
    }


    /**
     * Register for simple blocks and custom blocks
     * @param blockName Unique block name
     * @param factory is the constructor of your block. Example: {@code Block::new CustomBlock::new}
     * **/
    public static Block registerBlock(String blockName, Function<AbstractBlock.Settings, Block> factory , AbstractBlock.Settings settings){
        settings = settings == null ? AbstractBlock.Settings.create() : settings;
        factory = factory == null ? Block::new : factory;

        RegistryKey<Block> key = registerBlockKey(id(blockName), settings);
        Block block = factory.apply(settings);
        return Registry.register(Registries.BLOCK, key, block);
    }

    /**
     * Register a block but everything you register with this will be a {@code Block} and can't be a {@code CustomBlock}
     * **/

    public static Block registerBlock(String blockName, AbstractBlock.Settings settings){
        return registerBlock(blockName, Block::new ,settings);
    }
    /**
     * Register a block but everything you register with this will be a {@code Block} and can't be a {@code CustomBlock}
     * **/

    public static Block registerBlock(String blockName){
        return registerBlock(blockName, null , null);
    }

    //BLOCK AND BLOCK ITEM
    /**
     * You can register both block and its block item at once
     * @param blockName Unique block name
     * @param blockFactory is the constructor of your block. Example: {@code Block::new CustomBlock::new}
     * @param blockSettings you can specify what property you block can have or just use {@code null}
     * @param itemFactory is the constructor of your item. Example: {@code Item::new CustomItem::new}
     * @return Only the block but un can access item with {@code block.asItem()}
     * **/
    public static Block registerBlockAndItem(String blockName, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings blockSettings, BiFunction<Block, Item.Settings, BlockItem> itemFactory, Item.Settings itemSettings){
        blockSettings = blockSettings == null ? AbstractBlock.Settings.create() : blockSettings;
        itemSettings = itemSettings == null ? new Item.Settings() : itemSettings;
        blockFactory = blockFactory == null ? Block::new : blockFactory;
        itemFactory = itemFactory == null ? BlockItem::new : itemFactory;

        Block block = registerBlock(blockName, blockFactory,blockSettings );
        String itemName = blockName.endsWith("_item") ? blockName : blockName + "_item";
        registerBlockItem(itemName, itemFactory , itemSettings, block);
        return block;
    }
    /**
     *  Same functionality without custom settings
     *  @see #registerBlockAndItem(String, Function, AbstractBlock.Settings, BiFunction, Item.Settings) full method
    **/
    public static Block registerBlockAndItem(String blockName, Function<AbstractBlock.Settings, Block> blockFactory, BiFunction<Block, Item.Settings, BlockItem> itemFactory){
        return  registerBlockAndItem(blockName,blockFactory, null, itemFactory, null);
    }

    /**
     *  Same functionality without custom item and item settings
     *  @see #registerBlockAndItem(String, Function, AbstractBlock.Settings, BiFunction, Item.Settings) full method
     **/
    public static Block registerBlockAndItem(String blockName, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings blockSettings){
        return  registerBlockAndItem(blockName,blockFactory, blockSettings, null, null);
    }

    /**
     *  Same functionality without custom item and item settings
     *  @see #registerBlockAndItem(String, Function, AbstractBlock.Settings, BiFunction, Item.Settings) full method
     **/
    public static Block registerBlockAndItem(String blockName, BiFunction<Block, Item.Settings, BlockItem> itemFactory, Item.Settings itemSettings){
        return  registerBlockAndItem(blockName,null, null, itemFactory, itemSettings);
    }

    /**
     *  Why would you use this overload?
     *  @see #registerBlockAndItem(String, Function, AbstractBlock.Settings, BiFunction, Item.Settings) full method
     **/
    public static Block registerBlockAndItem(String blockName){
        return  registerBlockAndItem(blockName,null, null, null, null);
    }
    //BLOCK ENTITY
    /**
     * To associate a block with entity to a block entity
     * **/
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
    /**
     * Register block item of already given block
     * **/
    public static BlockItem registerBlockItem(String itemName, BiFunction<Block, Item.Settings, BlockItem> factory, Item.Settings settings, Block block){
        settings = settings == null ? new Item.Settings() : settings;
        factory = factory == null ? BlockItem::new : factory;

        RegistryKey<Item> key = registerItemKey(settings,id(itemName));
        BlockItem item = factory.apply(block, settings);
        return Registry.register(Registries.ITEM, key, item);
    }
    /**
     * Simpler way to use:
     * @see #registerBlockItem(String, BiFunction, Item.Settings, Block)
     * **/
    public static Item registerBlockItem(String itemName, Block block){
        return registerBlockItem(itemName, null, null, block);
    }

    public static Item registerItem(String itemName, Function<Item.Settings, Item> factory, Item.Settings settings) {
        settings = settings == null ? new Item.Settings() : settings;
        factory = factory == null ? Item::new : factory;

        RegistryKey<Item> key = registerItemKey(settings, id(itemName));
        Item item = factory.apply(settings);
        return Registry.register(Registries.ITEM, key, item);
    }
    public static <I extends Item> Item registerItem(String itemName) {
        return registerItem(itemName, null, null);
    }


    //ITEM GROUP
    public static RegistryKey<ItemGroup> registerItemGroup(String groupName, ItemGroup itemGroup){
        RegistryKey<ItemGroup> key = RegistryKey.of(RegistryKeys.ITEM_GROUP, id(groupName));
        Registry.register(Registries.ITEM_GROUP, key, itemGroup);
        return key;
    }

    //ENTITY
    public static <M extends MobEntity> EntityType<M> registerEntityType(String entityID, EntityType.Builder<M> entityBuilder){
        Identifier identifier = id(entityID);
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, identifier);
        return Registry.register(Registries.ENTITY_TYPE, key, entityBuilder.build(key));
    }
    
    public static void registerMobAttribute(EntityType<? extends MobEntity> entityType, DefaultAttributeContainer.Builder builder){
        FabricDefaultAttributeRegistry.register(entityType, builder.build());
    }

    public static SoundEvent registerSound(String soundName){
        Identifier identifier = id(soundName);
        RegistryKey<SoundEvent> key = RegistryKey.of(RegistryKeys.SOUND_EVENT, identifier);
        //TODO: DO I HAVE TO USE THE KEY OR NOT?????????
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static <P extends ParticleType<?>> P registerParticle(String particleName, P particleType){
        return Registry.register(Registries.PARTICLE_TYPE, id(particleName), particleType);
    };

    public static <S extends ScreenHandler> ScreenHandlerType<S> registerScreenType(String screenName, ScreenHandlerType.Factory<S> screenHandlerFactory, FeatureSet featureSet){
        return Registry.register(Registries.SCREEN_HANDLER,id(screenName), new ScreenHandlerType<>(screenHandlerFactory, featureSet));
    }
}
