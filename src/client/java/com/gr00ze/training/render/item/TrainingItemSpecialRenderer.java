package com.gr00ze.training.render.item;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.model.special.SpecialModelRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TrainingItemSpecialRenderer implements SpecialModelRenderer<TrainingModelRendererData> {
    public static final TrainingItemSpecialRenderer INSTANCE = new TrainingItemSpecialRenderer();

    @Override
    public void render(@Nullable TrainingModelRendererData data, ItemDisplayContext displayContext, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, boolean glint) {
        matrices.push();
        matrices.translate(new Vec3d(0,2,0));
        matrices.scale(20,20,20);
        // esempio: un mini fulmine randomico davanti al modello dell’item
        List<Vec3d> points = generateLightning(
                new Vec3d(0,0,0),
                new Vec3d(0,0,0.5),
                8,
                0.05
        );

        VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getLines());

        for (int i = 0; i < points.size()-1; i++) {
            Vec3d p1 = points.get(i);
            Vec3d p2 = points.get(i+1);

            vc.vertex(matrices.peek(), (float)p1.x, (float)p1.y, (float)p1.z)
                    .color(80, 150, 255, 255)
                    .light(light)
                    .normal(0,0,1);

            vc.vertex(matrices.peek(), (float)p2.x, (float)p2.y, (float)p2.z)
                    .color(80, 150, 255, 255)
                    .light(light)
                    .normal(0,0,1);
        }

        matrices.pop();

    }

    @Override
    public void collectVertices(Set<Vector3f> vertices) {

    }

    @Override
    public @Nullable TrainingModelRendererData getData(ItemStack stack) {
        return null;
    }
    public static List<Vec3d> generateLightning(
            Vec3d start, Vec3d end, int segments, double randomness) {

        List<Vec3d> pts = new ArrayList<>();
        pts.add(start);

        for (int i = 1; i < segments; i++) {
            double t = (double) i / segments;
            Vec3d p = start.lerp(end, t);

            // jitter casuale per effetto elettrico
            p = p.add(
                    (Math.random()-0.5)*randomness,
                    (Math.random()-0.5)*randomness,
                    (Math.random()-0.5)*randomness
            );

            pts.add(p);
        }

        pts.add(end);
        return pts;
    }

}
