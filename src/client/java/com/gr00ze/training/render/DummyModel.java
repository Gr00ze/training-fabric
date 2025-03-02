package com.gr00ze.training.render;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;

public class DummyModel extends EntityModel<LivingEntityRenderState> {
    private final ModelPart root;

    public DummyModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
    }

    public static TexturedModelData getTexturedModelData(){
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -28.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(24, 0).cuboid(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.0F, -20.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 16).cuboid(-3.0F, -16.0F, -4.0F, 6.0F, 10.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(LivingEntityRenderState state) {
        super.setAngles(state);
    }
}
