package com.gr00ze.training.item;

import com.gr00ze.training.util.RegisterFunctions;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TrainingCustomRenderItem extends Item {
    public static final ComponentType<Float> RADIUS = RegisterFunctions.registerComponent("radius", ComponentType.<Float>builder().codec(Codec.FLOAT));
    public static final ComponentType<Boolean> ELECTRIC = RegisterFunctions.registerComponent("electric", ComponentType.<Boolean>builder().codec(Codec.BOOL));

    public TrainingCustomRenderItem(Settings settings) {
        super(settings);
    }

    public static Settings getItemSetting(){
        return new Settings();
    }

    @Override
    public void postProcessComponents(ItemStack itemStack) {
        super.postProcessComponents(itemStack);

    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack itemStack = new ItemStack(this);
        itemStack.set(RADIUS, 0F);
        itemStack.set(ELECTRIC, false);
        return itemStack;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getItem() instanceof TrainingCustomRenderItem){
            itemStack.set(RADIUS, (itemStack.getOrDefault(RADIUS,0f) + 0.1f) % 1);
            itemStack.set(ELECTRIC, !itemStack.getOrDefault(ELECTRIC,false)  );
        }
        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {



        super.inventoryTick(stack, world, entity, slot);
    }
}
