package com.gr00ze.training.render;

import com.gr00ze.training.entity.EntityTypeList;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

import static com.gr00ze.training.render.DummyRenderer.DUMMY_LAYER;

public class RendererList {

    public static void initialize(){

        EntityModelLayerRegistry.registerModelLayer(DUMMY_LAYER, DummyModel::getTexturedModelData);

        EntityRendererRegistry.register(EntityTypeList.DUMMY_MOB_ENTITY,DummyRenderer::new);

        EntityRendererRegistry.register(EntityTypeList.DUMMY_AI_ENTITY,DummyRenderer::new);

        EntityRendererRegistry.register(EntityTypeList.DUMMY_SOUND_ENTITY,DummyRenderer::new);


    }

}
