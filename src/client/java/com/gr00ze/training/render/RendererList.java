package com.gr00ze.training.render;

import com.gr00ze.training.block.BlockList;
import com.gr00ze.training.entity.EntityTypeList;
import com.gr00ze.training.model.DummyModel;
import com.gr00ze.training.model.Item.TrainingCustomItemModel;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

import static com.gr00ze.training.render.DummyRenderer.DUMMY_LAYER;
import static com.gr00ze.training.util.RegisterFunctions.id;

public class RendererList {
    public static String ENTITY_TEXTURE_PATH = "textures/entity/";

    public static String getPath(String texture){
        return ENTITY_TEXTURE_PATH + texture;

    }

    public static void initialize(){

        EntityModelLayerRegistry.registerModelLayer(DUMMY_LAYER, DummyModel::getTexturedModelData);

        EntityRendererRegistry.register(EntityTypeList.DUMMY_MOB_ENTITY,DummyRenderer::new);

        EntityRendererRegistry.register(EntityTypeList.DUMMY_AI_ENTITY,DummyRenderer::new);

        EntityRendererRegistry.register(EntityTypeList.DUMMY_SOUND_ENTITY,DummyRenderer::new);
        //A BLOCK ENTITY CAN HAVE ITS RENDERING
        BlockEntityRendererFactories.register(BlockList.CUSTOM_BLOCK_ENTITY, CustomBlockEntityRenderer::new);
        //

        ModelLoadingPlugin.register(pluginContext ->
            pluginContext.modifyItemModelAfterBake().register(
                Event.DEFAULT_PHASE,   // Phase
                (model, ctx) -> {
                    //System.out.printf("Item ID %s%n", ctx.itemId().toString());

                    if (!ctx.itemId().equals(id("custom_rendering_item"))) {
                        return model; // restituisci il modello originale per tutti gli altri
                    }
                    System.out.printf("Called for %s%n", ctx.itemId().toString());
                    return new TrainingCustomItemModel(model);
                }
        ));


        WorldRenderEvents.AFTER_ENTITIES.register((context) -> {
            //MyEffectRenderer.renderSwingCurve(context);
        });


    }




}
