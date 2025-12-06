package com.gr00ze.training.render.item;

import com.gr00ze.training.item.TrainingCustomRenderItem;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Set;

import static com.gr00ze.training.render.RenderUtils.addVertex;
import static com.gr00ze.training.render.RenderExamples.drawDirectionalAxisCirclesExample;

public class TrainingAxisSphereRenderer implements SpecialModelRenderer<TrainingModelRendererData> {
    public static final TrainingAxisSphereRenderer INSTANCE = new TrainingAxisSphereRenderer();

    @Override
    public void render(
            @Nullable TrainingModelRendererData data,
            ItemDisplayContext displayContext,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            int overlay,
            boolean glint
    ) {
        matrices.push();
        applyRotation(displayContext, matrices);

        float radius = data.stack().getOrDefault(TrainingCustomRenderItem.RADIUS, 0.5f);

        drawDirectionalAxisCirclesExample(vertexConsumers, matrices, radius,light);

        matrices.pop();

    }



    @Override
    public void collectVertices(Set<Vector3f> vertices) {

    }

    @Override
    public @Nullable TrainingModelRendererData getData(ItemStack stack) {
        return null;
    }


    private void applyRotation(ItemDisplayContext displayContext, MatrixStack matrices) {

        switch (displayContext) {
            case FIRST_PERSON_RIGHT_HAND -> {
                matrices.translate(0.0, 1, 0.5);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                matrices.scale(0.5f, 0.5f, 0.5f);
            }
            case FIRST_PERSON_LEFT_HAND -> {
                matrices.translate(1, 1, 0.5);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                matrices.scale(0.5f, 0.5f, 0.5f);
            }
            case THIRD_PERSON_RIGHT_HAND -> {
                matrices.translate(0, 1, 0.5);
            }
            case THIRD_PERSON_LEFT_HAND -> {
                matrices.translate(1, 1, 0.5);
            }

            default -> {
                // Fallback: item nel mondo / GUI
                matrices.translate(0, 0, 0);

            }

        }
    }


}
