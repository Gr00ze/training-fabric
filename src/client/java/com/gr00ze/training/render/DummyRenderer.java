package com.gr00ze.training.render;

import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;

import static com.gr00ze.training.Training.MOD_ID;

public class DummyRenderer<E extends MobEntity> extends MobEntityRenderer<E, LivingEntityRenderState, DummyModel> {
    private static final Identifier TEXTURE = Identifier.of(MOD_ID, "dummy");
    public  static final EntityModelLayer DUMMY_LAYER = new EntityModelLayer(Identifier.of(MOD_ID, "dummy_layer"),"main");;
    private static final float SHADOW_SIZE = 0F;

    public DummyRenderer(EntityRendererFactory.Context context){
        super(context,  new DummyModel(context.getPart(DUMMY_LAYER)), SHADOW_SIZE);
    }

    @Override
    public Identifier getTexture(LivingEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
