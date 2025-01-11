package com.gr00ze.training.render;

import com.gr00ze.training.entity.EntityTypeList;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

import static com.gr00ze.training.Training.MOD_ID;

public class BadWayToRegisterRendering {

    public static void initialize(){
        EntityModelLayer DUMMY_LAYER = new EntityModelLayer(Identifier.of(MOD_ID, "dummy_layer"),"main");

        EntityModelLayerRegistry.registerModelLayer(DUMMY_LAYER,()->{
            ModelData modelData = new ModelData();
            modelData.getRoot().addChild("boy",
                    ModelPartBuilder.create().cuboid(0,0,0,16,16,16),
                    ModelTransform.NONE);
            return TexturedModelData.of(modelData,16,16);
        });

        EntityRendererRegistry.register(EntityTypeList.DUMMY_MOB_ENTITY, (ctx) ->
        new MobEntityRenderer<MobEntity, LivingEntityRenderState, EntityModel<LivingEntityRenderState>>
                (ctx, new EntityModel<>(ctx.getPart(DUMMY_LAYER)){}, 1F)
        {
            public static final Identifier TEXTURE = Identifier.of(MOD_ID, "texture");
            @Override
            public LivingEntityRenderState createRenderState() {
                return new LivingEntityRenderState();
            }

            @Override
            public Identifier getTexture(LivingEntityRenderState state) {
                return TEXTURE;
            }
        });
    }
}
