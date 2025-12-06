package com.gr00ze.training.render;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector2f;
import org.joml.Vector3f;

import java.awt.*;

public class RenderUtils {
    public static void addVertex(
            MatrixStack.Entry entry,
            VertexConsumer vertexConsumer,
            Vector3f position,
            Color color,
            int light,
            int overlay,
            Vector3f normal,
            Vector2f texture) {
        vertexConsumer.vertex(entry.getPositionMatrix(), position.x, position.y, position.z)
                .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())
                .texture(texture.getX(), texture.getY())
                .overlay(overlay)
                .light(light)
                .normal(entry, normal.x, normal.y, normal.z);
    }
    public static void addVertex(
            MatrixStack.Entry entry,
            VertexConsumer vertexConsumer,
            float px, float py, float pz,
            int red, int green, int blue, int alpha,
            int light,
            int overlay,
            float nx, float ny, float nz,
            float u, float v
    ) {
        vertexConsumer.vertex(entry.getPositionMatrix(), px, py, pz)
                .color(red, green, blue, alpha)
                .texture(u, v)  // Corretto UV mapping
                .overlay(overlay)
                .light(light)
                .normal(entry, nx, ny, nz);
    }

    public static void drawFacesVertexColor(VertexConsumer consumer, MatrixStack.Entry e, Vector3f[] verts, int[][] faces, int argb) {
        for (int[] face : faces) {
            Vector3f[] quadVerts = new Vector3f[4];
            for (int i = 0; i < 4; i++) {
                quadVerts[i] = verts[face[i]];
            }
            drawQuadVertexColor(consumer, e, quadVerts, argb);
        }
    }

    public static void drawQuadVertexColor(VertexConsumer consumer, MatrixStack.Entry e, Vector3f[] verts, int argb) {
        for (Vector3f v : verts) {
            consumer.vertex(e, v).color(argb);
        }
    }
}
