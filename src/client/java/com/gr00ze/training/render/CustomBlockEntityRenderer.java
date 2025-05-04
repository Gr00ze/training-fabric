package com.gr00ze.training.render;

import com.gr00ze.training.block.blockentity.TrainingCustomBlockEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Vector3f;

import static com.gr00ze.training.render.BlockEntityRenderExamples.*;
import static com.gr00ze.training.util.RegisterFunctions.id;


public class CustomBlockEntityRenderer implements BlockEntityRenderer<TrainingCustomBlockEntity> {

    public CustomBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {

    }


    @Override
    public void render(TrainingCustomBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        //Render Layer Custom
        exampleVertexCustomRenderLayer(new Vector3f(0, 10, 0), matrices, vertexConsumers);
        //VERTEX CONSUMER

        exampleVertexLoadAndAnimateJsonModel(new Vector3f(2.5F, 1, 0.5F), entity, matrices, vertexConsumers);

        exampleVertexRenderLayerIcosahedronEnd(new Vector3f(3.5F, 1.5F, 0.5F), 0.5f, matrices, vertexConsumers, light, overlay);

        exampleVertexTexturedIcosahedron(new Vector3f(0.5F, 1.5F, 0.5F), 0.5f, id("textures/block/triangle_template.png"), matrices, vertexConsumers, light, overlay);

        exampleVertexTexturedTrianglesInCircle(new Vector3f(1.5F, 1, 0.5F), 20, 0.2f, 0.2f, id("textures/block/training_custom_block.png"), matrices, vertexConsumers, light, overlay);

        exampleVertexTexturedTriangle(new Vector3f(-2F, 1, 0.5F), id("textures/block/triangle_template.png"), matrices, vertexConsumers, light, overlay);

        exampleVertexRotatingCubesDraw(new Vector3f(-0.5F, 1.5F, 0.5F), entity, vertexConsumers, matrices, light, overlay);

        //BUFFER
        exampleTexturedTriangle(new Vector3f(1, 2, 0), id("textures/block/triangle_template.png"), matrices, vertexConsumers, light, overlay);
        exampleTessellatorRGBTriangle(new Vector3f(0, 2, 0), matrices);


    }
}
