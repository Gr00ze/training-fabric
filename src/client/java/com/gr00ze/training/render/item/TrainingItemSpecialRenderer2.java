package com.gr00ze.training.render.item;

import com.gr00ze.training.item.TrainingCustomRenderItem;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;


public class TrainingItemSpecialRenderer2 implements SpecialModelRenderer<TrainingModelRendererData> {
    public static final TrainingItemSpecialRenderer2 INSTANCE = new TrainingItemSpecialRenderer2();
    private static final Random RANDOM = new Random();
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

        boolean active = data.stack().getOrDefault(TrainingCustomRenderItem.ELECTRIC, false);
        drawLightning(vertexConsumers, matrices, light);

        matrices.pop();

    }

    private void drawLightning(VertexConsumerProvider vertexConsumers, MatrixStack matrices, int light) {
        VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getLightning());
        MatrixStack.Entry e = matrices.peek();
        //e.translate(0.3f,0,0.1f);

        int segments = 30;
        Vector3f start = new Vector3f(0, 0, 0);
        Vector3f end   = new Vector3f(0, 1, 0);

        Vector3f p1a = new Vector3f(0, 0, 0);
        Vector3f p2a = new Vector3f(0, 0.5f, 0);
        float thicknessA = 0.05f;
        drawParallelepiped(vc, e, p1a, p2a, thicknessA, light);

        // Secondo parallelepipedo
        Vector3f p1b = new Vector3f(0, 0.5f, 0);
        Vector3f p2b = new Vector3f(0.2f, 1.0f, 0);
        float thicknessB = 0.04f;
        drawParallelepiped(vc, e, p1b, p2b, thicknessB, light);

    }

    private void drawParallelepiped(VertexConsumer vc, MatrixStack.Entry e, Vector3f p1, Vector3f p2, float thickness, int light) {
        Vector3f dir = new Vector3f(p2).sub(p1).normalize();

        // Vettore perpendicolare per la larghezza
        Vector3f up = Math.abs(dir.y) > 0.95f ? new Vector3f(1,0,0) : new Vector3f(0,1,0);
        Vector3f perp1 = new Vector3f(dir).cross(up).normalize().mul(thickness);
        Vector3f perp2 = new Vector3f(dir).cross(perp1).normalize().mul(thickness);

        // 8 vertici del parallelepipedo
        Vector3f v000 = new Vector3f(p1).add(perp1).add(perp2);
        Vector3f v001 = new Vector3f(p1).add(perp1).sub(perp2);
        Vector3f v010 = new Vector3f(p1).sub(perp1).sub(perp2);
        Vector3f v011 = new Vector3f(p1).sub(perp1).add(perp2);

        Vector3f v100 = new Vector3f(p2).add(perp1).add(perp2);
        Vector3f v101 = new Vector3f(p2).add(perp1).sub(perp2);
        Vector3f v110 = new Vector3f(p2).sub(perp1).sub(perp2);
        Vector3f v111 = new Vector3f(p2).sub(perp1).add(perp2);

        int c = 0x80FFFFFF; // colore semitrasparente

        // Funzione helper per creare un quad
        BiConsumer<Vector3f[], Vector3f> drawQuad = (verts, normal) -> {
            vc.vertex(e, verts[0].x, verts[0].y, verts[0].z).color(c).normal(e, normal.x, normal.y, normal.z).light(light);
            vc.vertex(e, verts[1].x, verts[1].y, verts[1].z).color(c).normal(e, normal.x, normal.y, normal.z).light(light);
            vc.vertex(e, verts[2].x, verts[2].y, verts[2].z).color(c).normal(e, normal.x, normal.y, normal.z).light(light);
            vc.vertex(e, verts[3].x, verts[3].y, verts[3].z).color(c).normal(e, normal.x, normal.y, normal.z).light(light);
        };

        // 6 facce del parallelepipedo
        drawQuad.accept(new Vector3f[]{v000, v001, v101, v100}, dir);                 // front
        drawQuad.accept(new Vector3f[]{v010, v011, v111, v110}, new Vector3f(dir).negate()); // back
        drawQuad.accept(new Vector3f[]{v000, v100, v110, v010}, perp1);               // lato 1
        drawQuad.accept(new Vector3f[]{v001, v101, v111, v011}, new Vector3f(perp1).negate()); // lato 2
        drawQuad.accept(new Vector3f[]{v000, v001, v011, v010}, perp2);               // lato 3
        drawQuad.accept(new Vector3f[]{v100, v101, v111, v110}, new Vector3f(perp2).negate()); // lato 4

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
