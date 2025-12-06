package com.gr00ze.training.render.item;

import com.gr00ze.training.item.TrainingCustomRenderItem;
import com.gr00ze.training.render.RenderUtils;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class TrainingLightingRenderer implements SpecialModelRenderer<TrainingModelRendererData> {
    public static final TrainingLightingRenderer INSTANCE = new TrainingLightingRenderer();
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
        if (active){
            drawLightning(vertexConsumers, matrices);
        }

        matrices.pop();

    }


    private void drawLightning(VertexConsumerProvider vertexConsumers, MatrixStack matrices) {
        VertexConsumer lightningConsumer = vertexConsumers.getBuffer(RenderLayer.getLightning());
        MatrixStack.Entry e = matrices.peek();

        Vector3f point1 = new Vector3f(1,1,0);
        Vector3f point2 = new Vector3f(0,0,0);

        int lightningColor = 0xFF0000FF;

        List<Vector3f[]> segments = generateLightningSegments(point1, point2, 20, 0.15F);
        for (Vector3f[] pair : segments) {
            drawConcentricSegment(lightningConsumer, e, pair[0], pair[1], 0.5F, lightningColor);
        }
    }

    public static List<Vector3f[]> generateLightningSegments(Vector3f start, Vector3f end, int segments, float deviation) {
        List<Vector3f[]> result = new ArrayList<>();
        Vector3f prev = new Vector3f(start);

        for (int i = 1; i <= segments; i++) {
            float t = (float)i / segments;
            Vector3f next = new Vector3f(
                    start.x + (end.x - start.x) * t,
                    start.y + (end.y - start.y) * t,
                    start.z + (end.z - start.z) * t
            );

            // offset casuale perpendicolare alla direzione
            Vector3f dir = new Vector3f(next).sub(prev).normalize();
            Vector3f offset = randomPerpendicular(dir).mul(deviation * (1 - t)); // deviazione decrescente verso fine
            next.add(offset);

            result.add(new Vector3f[]{new Vector3f(prev), new Vector3f(next)});
            prev.set(next);
        }

        return result;
    }

    private static Vector3f randomPerpendicular(Vector3f dir) {
        Vector3f up = Math.abs(dir.y) < 0.99f ? new Vector3f(0,1,0) : new Vector3f(1,0,0);
        Vector3f perp = new Vector3f(dir).cross(up).normalize();
        Vector3f perp2 = new Vector3f(dir).cross(perp).normalize();

        float a = RANDOM.nextFloat() * 2 - 1;
        float b = RANDOM.nextFloat() * 2 - 1;

        return new Vector3f(perp).mul(a).add(new Vector3f(perp2).mul(b));
    }

    private void drawConcentricSegment(VertexConsumer lightningConsumer, MatrixStack.Entry e, Vector3f point1, Vector3f point2, float radius_Multiplier, int lineColor) {
        int baseRGB = lineColor & 0x00FFFFFF;

        int argb1 = (0x33 << 24) | lerpToWhite(baseRGB, 0.0f);
        int argb2 = (0x55 << 24) | lerpToWhite(baseRGB, 0.5f);
        int argb3 = (0xFF << 24) | lerpToWhite(baseRGB, 1.0f);

        drawSegment(lightningConsumer, e, point1, point2, argb1, 0.1F * radius_Multiplier);
        drawSegment(lightningConsumer, e, point1, point2, argb2, 0.05F * radius_Multiplier);
        drawSegment(lightningConsumer, e, point1, point2, argb3, 0.01F * radius_Multiplier);
    }

    private int lerpToWhite(int rgb, float t) {
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;

        r = (int) (r + (255 - r) * t);
        g = (int) (g + (255 - g) * t);
        b = (int) (b + (255 - b) * t);

        return (r << 16) | (g << 8) | b;
    }
    private void drawSegment(VertexConsumer lightningConsumer, MatrixStack.Entry e, Vector3f point1, Vector3f point2, int argb, float radius){
        Vector3f[] verts = generateFacesVerticesFromPoints(point1, point2, radius);

        int[][] sideFaces = {
                {0, 1, 5, 4},
                {1, 2, 6, 5},
                {2, 3, 7, 6},
                {3, 0, 4, 7}
        };
        RenderUtils.drawFacesVertexColor(lightningConsumer, e, verts, sideFaces, argb);
    }

    private static Vector3f @NotNull [] generateFacesVerticesFromPoints(Vector3f point1, Vector3f point2, float radius) {
        Vector3f dir = new Vector3f(point1).sub(point2).normalize();

        Vector3f up = Math.abs(dir.y) < 0.99f ? new Vector3f(0,1,0) : new Vector3f(1,0,0);


        // Calcola due vettori perpendicolari a dir
        Vector3f perp1 = new Vector3f(dir).cross(up).normalize().mul(radius);
        Vector3f perp2 = new Vector3f(dir).cross(perp1).normalize().mul(radius);

        // 4 vertici alla base (intorno ad A)
        Vector3f v0 = new Vector3f(point1).add(perp1).add(perp2);
        Vector3f v1 = new Vector3f(point1).add(perp1).sub(perp2);
        Vector3f v2 = new Vector3f(point1).sub(perp1).sub(perp2);
        Vector3f v3 = new Vector3f(point1).sub(perp1).add(perp2);

        // 4 vertici in cima (intorno a B)
        Vector3f v4 = new Vector3f(point2).add(perp1).add(perp2);
        Vector3f v5 = new Vector3f(point2).add(perp1).sub(perp2);
        Vector3f v6 = new Vector3f(point2).sub(perp1).sub(perp2);
        Vector3f v7 = new Vector3f(point2).sub(perp1).add(perp2);

        return new Vector3f[]{v0,v1,v2,v3,v4,v5,v6,v7};
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
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90));
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
