package com.gr00ze.training.render;

import com.gr00ze.training.block.BlockList;
import com.gr00ze.training.block.blockentity.TrainingCustomBlockEntity;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.fabricmc.fabric.api.renderer.v1.render.BlockVertexConsumerProvider;
import net.minecraft.client.MinecraftClient;

import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.BlockRenderManager;

import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.gr00ze.training.Training.MOD_ID;
import static com.gr00ze.training.util.RegisterFunctions.id;
import static net.minecraft.client.gl.RenderPipelines.RENDERTYPE_END_PORTAL_SNIPPET;

public class BlockEntityRenderExamples {
    public static void exampleVertexCustomRenderLayer(Vector3f center, MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        // Render layer definition
        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.of(
                "web_layer",
                1024,
                RenderPipeline.builder(RENDERTYPE_END_PORTAL_SNIPPET)
                        .withLocation("pipeline/end_portal")
                        .withShaderDefine("PORTAL_LAYERS", 15)
                        .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
                        .withVertexFormat(VertexFormats.POSITION, VertexFormat.DrawMode.TRIANGLES)
                        .build(),
                RenderLayer.MultiPhaseParameters.builder()
                        .texture(RenderPhase.Textures.create()
                                .add(id("textures/example_cobweb.png"),false)
                                .add(id("textures/example_cobweb.png"),false)
                                .build())
                        .build(false)

        ));

        // Shape
        matrices.push();
        matrices.translate(center.x, center.y, center.z);
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        consumer.vertex(matrix, 0.5f, 1.0f, 0.5f).color(255, 0, 0, 255);  // Punto superiore
        consumer.vertex(matrix, 1.0f, 0.0f, 0f).color(0, 255, 0, 255);  // Punto sinistro
        consumer.vertex(matrix, 0f, 0.0f, 0f).color(0, 0, 255, 255);  // Punto destro

        matrices.pop();
    }

    static class VertexConverter implements BlockVertexConsumerProvider{
        VertexConsumerProvider provider;
        Identifier texture;
        public VertexConverter(VertexConsumerProvider vertexConsumerProvider){
            this.provider = vertexConsumerProvider;
        }
        public VertexConverter(VertexConsumerProvider vertexConsumerProvider, Identifier texture){
            this.provider = vertexConsumerProvider;
            this.texture = texture;
        }

        private static final Map<BlockRenderLayer, Object> LAYER_MAP = Map.of(
                BlockRenderLayer.SOLID, (Supplier<RenderLayer>) RenderLayer::getSolid,
                BlockRenderLayer.CUTOUT_MIPPED, (Supplier<RenderLayer>) RenderLayer::getCutoutMipped,
                BlockRenderLayer.CUTOUT, (Supplier<RenderLayer>) RenderLayer::getCutout,
                BlockRenderLayer.TRANSLUCENT, (Function<Identifier, RenderLayer>) RenderLayer::getEntityTranslucent,
                BlockRenderLayer.TRIPWIRE, (Supplier<RenderLayer>) RenderLayer::getTripwire // solo questo usa texture
        );
        @Override
        public VertexConsumer getBuffer(BlockRenderLayer layer) {
            Object entry = LAYER_MAP.getOrDefault(layer, (Supplier<RenderLayer>) RenderLayer::getSolid);

            if (entry instanceof Function<?, ?> func) {
                @SuppressWarnings("unchecked")
                Function<Identifier, RenderLayer> f = (Function<Identifier, RenderLayer>) func;
                return provider.getBuffer(f.apply(texture));
            } else if (entry instanceof Supplier<?> supplier) {
                @SuppressWarnings("unchecked")
                Supplier<RenderLayer> s = (Supplier<RenderLayer>) supplier;
                return provider.getBuffer(s.get());
            } else {
                return provider.getBuffer(RenderLayer.getSolid());
            }
        }
    }
    public static void exampleVertexLoadAndAnimateJsonModel(Vector3f center, TrainingCustomBlockEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers) {

        matrices.push();
        matrices.translate(center.x,center.y, center.z);

        matrices.scale(0.5f, 0.5f, 0.5f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getRotationAngle()));
        matrices.translate(-0.5f, 0, -0.5f);

        // Ottieni il modello del blocco
        BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
        BlockStateModel model = blockRenderManager.getModel(BlockList.CUSTOM_BLOCK_WITH_ENTITY.getDefaultState());

        blockRenderManager.getModelRenderer().render(
                entity.getWorld(),
                model,
                entity.getCachedState(),
                entity.getPos(),
                matrices,
                new VertexConverter(vertexConsumers),
                false,
                entity.getCachedState().getRenderingSeed(entity.getPos()),
                OverlayTexture.DEFAULT_UV
        );

        matrices.pop();


    }
    public static void exampleVertexRenderLayerIcosahedronEnd(Vector3f center, float scale, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Vector3f[] ICOSAHEDRON_VERTICES = getIcosahedronVertices();

        int[][] ICOSAHEDRON_FACES = {
                {0, 4, 1}, {0, 9, 4}, {9, 5, 4}, {4, 5, 8}, {4, 8, 1},
                {8, 10, 1}, {8, 3, 10}, {5, 3, 8}, {5, 2, 3}, {2, 7, 3},
                {7, 10, 3}, {7, 6, 10}, {7, 11, 6}, {11, 0, 6}, {0, 1, 6},
                {6, 1, 10}, {9, 0, 11}, {9, 11, 2}, {9, 2, 5}, {7, 2, 11}
        };
        for (int[] face : ICOSAHEDRON_FACES) {
            Vector3f p1 = scaleAndTranslate(ICOSAHEDRON_VERTICES[face[0]], center, scale);
            Vector3f p2 = scaleAndTranslate(ICOSAHEDRON_VERTICES[face[1]], center, scale);
            Vector3f p3 = scaleAndTranslate(ICOSAHEDRON_VERTICES[face[2]], center, scale);

            drawTriangle(p1, p2, p3, matrices, vertexConsumers, light, overlay, RenderLayer.getEndPortal());
        }


    }
    public static void exampleVertexTexturedIcosahedron(Vector3f center, float scale, Identifier texture, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Vector3f[] ICOSAHEDRON_VERTICES = getIcosahedronVertices();

        int[][] ICOSAHEDRON_FACES = {
                {0, 4, 1}, {0, 9, 4}, {9, 5, 4}, {4, 5, 8}, {4, 8, 1},
                {8, 10, 1}, {8, 3, 10}, {5, 3, 8}, {5, 2, 3}, {2, 7, 3},
                {7, 10, 3}, {7, 6, 10}, {7, 11, 6}, {11, 0, 6}, {0, 1, 6},
                {6, 1, 10}, {9, 0, 11}, {9, 11, 2}, {9, 2, 5}, {7, 2, 11}
        };


        for (int[] face : ICOSAHEDRON_FACES) {
            Vector3f p1 = scaleAndTranslate(ICOSAHEDRON_VERTICES[face[0]], center, scale);
            Vector3f p2 = scaleAndTranslate(ICOSAHEDRON_VERTICES[face[1]], center, scale);
            Vector3f p3 = scaleAndTranslate(ICOSAHEDRON_VERTICES[face[2]], center, scale);

            drawTexturedTriangle(p1, p2, p3, texture, matrices, vertexConsumers, light, overlay);
        }
    }

    private static Vector3f[] getIcosahedronVertices() {
        float X = 0.525731112119133606f;
        float Z = 0.850650808352039932f;
        return new Vector3f[]{
                new Vector3f(-X, 0.0f, Z), new Vector3f(X, 0.0f, Z),
                new Vector3f(-X, 0.0f, -Z), new Vector3f(X, 0.0f, -Z),
                new Vector3f(0.0f, Z, X), new Vector3f(0.0f, Z, -X),
                new Vector3f(0.0f, -Z, X), new Vector3f(0.0f, -Z, -X),
                new Vector3f(Z, X, 0.0f), new Vector3f(-Z, X, 0.0f),
                new Vector3f(Z, -X, 0.0f), new Vector3f(-Z, -X, 0.0f)
        };
    }

    // Funzione per scalare e traslare i punti
    private static Vector3f scaleAndTranslate(Vector3f point, Vector3f center, float scale) {
        return new Vector3f(
                center.x + point.x * scale,
                center.y + point.y * scale,
                center.z + point.z * scale
        );
    }

    public static void exampleVertexTexturedTrianglesInCircle(Vector3f center, int numTriangles, float radius, float tipLength, Identifier texture, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay){
        for (int i = 0; i < numTriangles; i++) {
            float angle1 = (float) (2 * Math.PI * i / numTriangles);
            float angle2 = (float) (2 * Math.PI * (i + 1) / numTriangles);

            // Punto centrale in alto (punta del triangolo)
            Vector3f p1 = new Vector3f(
                    (float) Math.cos((angle1 + angle2) / 2) * (radius + tipLength),
                    0,
                    (float) Math.sin((angle1 + angle2) / 2) * (radius + tipLength)
            );

            // Due punti alla base sul cerchio
            Vector3f p2 = new Vector3f(
                    (float) Math.cos(angle1) * radius,
                    0,
                    (float) Math.sin(angle1) * radius
            );

            Vector3f p3 = new Vector3f(
                    (float) Math.cos(angle2) * radius,
                    0,
                    (float) Math.sin(angle2) * radius
            );

            // Disegna il triangolo
            matrices.push();
            matrices.translate(center.x,center.y, center.z);
            drawTexturedTriangle(p1, p2, p3, texture, matrices, vertexConsumers, light, overlay);
            matrices.pop();
        }

    }


    private static void drawTexturedTriangle(Vector3f point1, Vector3f point2, Vector3f point3, Identifier texture, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay){
        matrices.push();
        RenderLayer renderLayer = RenderLayer.getEntityTranslucent(texture);

        drawTriangle(point1, point2, point3, matrices, vertexConsumers, light, overlay, renderLayer);

        matrices.pop();
    }

    private static void drawTriangle(Vector3f point1, Vector3f point2, Vector3f point3, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, RenderLayer renderLayer) {
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(renderLayer);
        MatrixStack.Entry entry = matrices.peek();

        float[][] positions = {
                {point1.x, point1.y, point1.z},
                {point2.x, point2.y, point2.z},
                {point3.x, point3.y, point3.z},
                {point3.x, point3.y, point3.z}
        };

        float[][] uvs = {
                {0.5f, 0.0f},
                {0.0f, 1.0f},
                {1.0f, 1.0f},
                {1.0f, 1.0f}
        };

        float[] normal = {0.0f, 1.0f, 0.0f};

        for (int i = 0; i < positions.length; i++) {
            addVertex(entry, vertexConsumer, positions[i], light, overlay, normal[0], normal[1], normal[2], uvs[i][0], uvs[i][1]);
        }
    }


    public static void exampleVertexTexturedTriangle(Vector3f center, Identifier texture, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        RenderLayer renderLayer = RenderLayer.getEntityCutout(texture);

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(renderLayer);
        MatrixStack.Entry entry = matrices.peek();
        matrices.translate(center.x,center.y, center.z);
        //vertici per un triangolo
        float[][] positions = {
                {0.5f, 1.0f, 0.5f}, // Punto superiore
                {1.0f, 0.0f, 0f},   // Punto in basso a sinistra
                {0.0f, 0.0f, 0f},   // Punto in basso a destra
                {0.0f, 0.0f, 0f}   // Punto in basso a destra duplicato
        };

        float[][] uvs = {
                {0.5f, 0.0f},
                {0.0f, 1.0f},
                {1.0f, 1.0f},
                {1.0f, 1.0f}
        };

        float[] normal = {0.0f, 1.0f, 0.0f}; // Normale verso l'alto

        for (int i = 0; i < positions.length; i++) {
            addVertex(entry, vertexConsumer, positions[i], light, overlay, normal[0], normal[1], normal[2], uvs[i][0], uvs[i][1]);
        }

        matrices.pop();
    }

    public static void exampleTexturedTriangle(Vector3f center, Identifier texture, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        matrices.translate(center.x,center.y, center.z);
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f matrix = entry.getPositionMatrix();
        RenderLayer renderLayer = RenderLayer.of( "triangle_layer",
                64, // buffer size
                RenderPipeline.builder(RenderPipelines.POSITION_TEX_COLOR_SNIPPET) // nessuno snippet per ora
                        .withLocation("pipeline/triangle")
                        .withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR,VertexFormat.DrawMode.TRIANGLES)
                        .build(),
                RenderLayer.MultiPhaseParameters.builder()
                        .texture(RenderPhase.Textures.create()
                                .add(texture, false)
                                .build())
                        .build(false)
        );
        VertexConsumer consumer = vertexConsumers.getBuffer(renderLayer);
        consumer.vertex(matrix, 0.5f, 1.0f, 0.5f).texture(0.5F, 0).color(255, 255, 255, 255).light(light);  // Punto superiore
        consumer.vertex(matrix, 1.0f, 0.0f, 0f).texture(0, 1).color(255, 255, 255, 255).light(light);        // Punto sinistro
        consumer.vertex(matrix, 0f, 0.0f, 0f).texture(1, 1).color(255, 255, 255, 255).light(light);
        matrices.pop(); // Rimuovi la matrice
    }



    public static void exampleRGBTriangle(Vector3f center, MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        matrices.push();
        matrices.translate(center.x,center.y, center.z);
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        RenderLayer renderLayer = RenderLayer.of( "triangle_rgb_layer",
                256, // buffer size
                RenderPipeline.builder(RenderPipelines.POSITION_COLOR_SNIPPET) // nessuno snippet per ora
                        .withLocation("pipeline/triangle_rgb")
                        .withVertexFormat(VertexFormats.POSITION_COLOR,VertexFormat.DrawMode.TRIANGLES)
                        .build(),
                RenderLayer.MultiPhaseParameters.builder()
                        .texture(RenderPhase.Textures.create()
                                .build())
                        .build(false)
        );
        VertexConsumer consumer = vertexConsumers.getBuffer(renderLayer);
        consumer.vertex(matrix, 0.5f, 1.0f, 0.5f).color(255, 0, 0, 255);  // Punto superiore
        consumer.vertex(matrix, 1.0f, 0.0f, 0f).color(0, 255, 0, 255);  // Punto sinistro
        consumer.vertex(matrix, 0f, 0.0f, 0f).color(0, 0, 255, 255);  // Punto destro
        matrices.pop();
    }



    public static void exampleVertexRotatingCubesDraw(Vector3f center, TrainingCustomBlockEntity entity, VertexConsumerProvider vertexConsumers, MatrixStack matrices, int light, int overlay){
        //rotating cube texturized
        matrices.push();
        matrices.translate(center.x,center.y, center.z);
        matrices.scale(0.5f,0.5f,0.5f);
        drawRotatingCube(vertexConsumers, matrices, light, overlay, entity.getRotationAngle() * 0.1f);
        drawRotatingCube(vertexConsumers, matrices, light, overlay, -entity.getRotationAngle()  * 0.1f);
        //CUBO 2
        matrices.pop();
    }

    private static void drawRotatingCube(VertexConsumerProvider vertexConsumers, MatrixStack matrices, int light, int overlay, float angle) {
        matrices.push();
        // Applica rotazione su X e Y
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(angle));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(angle));
        matrices.translate(-0.5f,-0.5f,-0.5f); //centra rotazione

        MatrixStack.Entry entry = matrices.peek();

        Identifier texture = id("textures/block/training_custom_block_with_entity.png");
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(texture));

        // Definire vertici con rotazione applicata
        // 🔹 Fronte (Z+)
        drawCube(vertexConsumer, entry, light, overlay);

        matrices.pop();
    }


    private static void drawCube(VertexConsumer vertexConsumer, MatrixStack.Entry entry, int light, int overlay) {
        float[][] vertices = {
                {0, 0, 0}, {1, 0, 0}, {1, 1, 0}, {0, 1, 0}, // Back (Z-)
                {0, 0, 1}, {1, 0, 1}, {1, 1, 1}, {0, 1, 1}, // Front (Z+)
        };

        int[][] faces = {
                {0, 1, 2, 3,  0, 0, -1}, // Back
                {5, 4, 7, 6,  0, 0, 1},  // Front
                {4, 0, 3, 7, -1, 0, 0}, // Left
                {1, 5, 6, 2,  1, 0, 0},  // Right
                {3, 2, 6, 7,  0, 1, 0},  // Top
                {4, 5, 1, 0,  0, -1, 0}  // Bottom
        };

        for (int[] face : faces) {
            addVertex(entry, vertexConsumer, vertices[face[0]], light, overlay, face[4], face[5], face[6], 0, 0);
            addVertex(entry, vertexConsumer, vertices[face[1]], light, overlay, face[4], face[5], face[6], 1, 0);
            addVertex(entry, vertexConsumer, vertices[face[2]], light, overlay, face[4], face[5], face[6], 1, 1);
            addVertex(entry, vertexConsumer, vertices[face[3]], light, overlay, face[4], face[5], face[6], 0, 1);
        }
    }

    private static void addVertex(MatrixStack.Entry entry, VertexConsumer vertexConsumer, float[] pos, int light, int overlay, float nx, float ny, float nz, float u, float v) {
        vertexConsumer.vertex(entry.getPositionMatrix(), pos[0], pos[1], pos[2])
                .color(255, 255, 255, 255)
                .texture(u, v)  // Corretto UV mapping
                .overlay(overlay)
                .light(light)
                .normal(entry, nx, ny, nz);
    }

    public static void exampleCurve(Vector3f center, MatrixStack matrices, VertexConsumerProvider vertexConsumers){
        //curve generation
        List<Vec3d> curve = new ArrayList<>();
        int segments = 80;
        double a = -10;   // apre verso il basso
        double b = 0.0;    // inclinazione della parabola
        double c = 0.0;    // traslazione verticale

        double minY = -0.5;   // quanto sotto può arrivare
        double maxY =  0.5;   // quanto sopra può salire

        for (int i = 0; i <= segments; i++) {
            double t = (double) i / segments;

            double x = (t - 0.5) * 2;   // larghezza da -1 a +1
            double y = a * x * x + b * x + c;
            //y = Math.max(minY, Math.min(maxY, y));
            if(y > maxY || y < minY)continue;
            curve.add(new Vec3d(x, y, 0));
        }
        //drawing
        VertexConsumer vc = vertexConsumers.getBuffer(RenderLayer.getLines());

        matrices.push();
        matrices.translate(center.x,center.y, center.z);
        for (int i = 0; i < curve.size() - 1; i++) {
            Vec3d p1 = curve.get(i);
            Vec3d p2 = curve.get(i + 1);

            vc.vertex(matrices.peek(), (float)p1.x, (float)p1.y, 0)
                    .color(255, 255, 200, 255)    // giallo/bianco
                    .light(0xF000F0)
                    .normal(1,1,1);

            vc.vertex(matrices.peek(), (float)p2.x, (float)p2.y, 0)
                    .color(255, 255, 200, 255)
                    .light(0xF000F0)
                    .normal(1,1,1);
        }
        matrices.pop();



    }


}
