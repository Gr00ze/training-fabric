package com.gr00ze.training.render.item;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public record TrainingModelRendererData(ItemStack stack, ClientWorld world, LivingEntity user) {}