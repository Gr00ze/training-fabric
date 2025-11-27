package com.gr00ze.training.model.Item;

import com.gr00ze.training.render.item.TrainingItemSpecialRenderer;
import com.gr00ze.training.render.item.TrainingModelRendererData;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class CustomItemModel implements ItemModel {
    private final ItemModel model;
    public CustomItemModel(ItemModel model){
        this.model = model;
    }

    @Override
    public void update(ItemRenderState state, ItemStack stack, ItemModelManager resolver, ItemDisplayContext displayContext, @Nullable ClientWorld world, @Nullable LivingEntity user, int seed) {
//        ItemRenderState.LayerRenderState layer = state.newLayer();
//        layer.setVertices(this::generateVertices);
//        layer.setRenderLayer(RenderLayer.getLines()); // o un altro render layer adatto
//        layer.setUseLight(true);


        ItemRenderState.LayerRenderState layerRenderState = state.newLayer();

        layerRenderState.setSpecialModel(TrainingItemSpecialRenderer.INSTANCE,
                new TrainingModelRendererData(stack, world, user));
    }
    private Vector3f[] generateVertices() {
        int points = 5;
        Vector3f[] verts = new Vector3f[points];
        for (int i = 0; i < points; i++) {
            float y = i / (float)(points - 1);
            verts[i] = new Vector3f(0f, y, 0f); // linea verticale da 0 a 1
        }
        return verts;
    }


}


