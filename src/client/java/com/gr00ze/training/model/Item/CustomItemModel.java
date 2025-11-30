package com.gr00ze.training.model.Item;

import com.gr00ze.training.render.item.TrainingItemSpecialRenderer;
import com.gr00ze.training.render.item.TrainingItemSpecialRenderer2;
import com.gr00ze.training.render.item.TrainingModelRendererData;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public record CustomItemModel(ItemModel model) implements ItemModel {

    @Override
    public void update(
            ItemRenderState state,
            ItemStack stack,
            ItemModelManager resolver,
            ItemDisplayContext displayContext,
            @Nullable ClientWorld world,
            @Nullable LivingEntity user,
            int seed
    ) {
        //This render the json model
        model.update(state, stack, resolver, displayContext, world, user, seed);

        ItemRenderState.LayerRenderState layerRenderState = state.newLayer();

        layerRenderState.setSpecialModel(
                TrainingItemSpecialRenderer.INSTANCE,
                new TrainingModelRendererData(stack, world, user)
        );

        ItemRenderState.LayerRenderState layerRenderState2 = state.newLayer();

        layerRenderState2.setSpecialModel(
                TrainingItemSpecialRenderer2.INSTANCE,
                new TrainingModelRendererData(stack, world, user)
        );
    }

}


