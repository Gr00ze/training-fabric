package com.gr00ze.training.render;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

public class DummyModel extends EntityModel<LivingEntityRenderState> {


    public DummyModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData(){
        ModelData modelData = new ModelData();
        modelData.getRoot().addChild("boy",
                ModelPartBuilder.create().cuboid(-16,-8,-16,32,32,32),
                ModelTransform.NONE);
        return TexturedModelData.of(modelData,16,16);
    }
}
