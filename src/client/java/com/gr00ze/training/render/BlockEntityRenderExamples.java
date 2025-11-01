package com.gr00ze.training.render;

import com.gr00ze.training.block.BlockList;
import com.gr00ze.training.block.blockentity.TrainingCustomBlockEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static com.gr00ze.training.Training.MOD_ID;
import static com.gr00ze.training.util.RegisterFunctions.id;
import static net.minecraft.client.render.RenderPhase.*;
import static net.minecraft.client.render.RenderPhase.COLOR_MASK;

public class BlockEntityRenderExamples {
    public static void exampleVertexCustomRenderLayer(Vector3f center, MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        // Render layer definition
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.of(
                "web_layer",
                VertexFormats.POSITION_TEXTURE_COLOR,
                VertexFormat.DrawMode.QUADS,
                262144,
                RenderLayer.MultiPhaseParameters.builder()
                        .texture(RenderPhase.Textures.create()
                                .add(id("textures/example_cobweb.png"),false,false)
                                .add(id("textures/example_cobweb.png"),false,false)
                                .add(id("textures/example_cobweb.png"),false,false)
                                .add(id("textures/example_cobweb.png"),false,false)
                                .add(id("textures/example_cobweb.png"),false,false)
                                .add(id("textures/example_cobweb.png"),false,false)
                                .add(id("textures/example_cobweb.png"),false,false)
                                .add(id("textures/example_cobweb.png"),false,false).build())
                        .program(END_PORTAL_PROGRAM) // Usa uno shader che deforma la texture
                        .transparency(NO_TRANSPARENCY)
                        .depthTest(ALWAYS_DEPTH_TEST)
                        .writeMaskState(COLOR_MASK)
                        .build(false)
        ));
        RenderSystem.enableDepthTest();
        // Shape
        matrices.push();
        matrices.translate(center.x, center.y, center.z);
        matrices.scale(4.2f, 5.9f, 4.1f); // Scala in modo non uniforme per deformare la forma
        matrices.multiply(RotationAxis.POSITIVE_Z.rotation(10)); // Leggera rotazione per evitare simmetria perfetta

        Matrix4f matrix = matrices.peek().getPositionMatrix();

        float x1 = 1.1f, y2 = 0.9f;
        float x2 = -1.2f, y1 = -1.1f;
        float u1 = 0.0f, v1 = 0.0f;
        float u2 = 1.0f, v2 = 1.0f;
        int color = 0xFFFFFFFF; // Bianco con piena opacità

        vertexConsumer.vertex(matrix, x1, y1, 0).texture(0, 0.5F).color(color);
        vertexConsumer.vertex(matrix, x2, y1, 0).texture(0, 1).color(color);
        vertexConsumer.vertex(matrix, x2, y2, 0).texture(1, 1).color(color);
        vertexConsumer.vertex(matrix, x1, y2, 0).texture(1, 1).color(color);

        matrices.pop();
    }


    public static void exampleVertexLoadAndAnimateJsonModel(Vector3f center, TrainingCustomBlockEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        matrices.push();
        matrices.translate(center.x,center.y, center.z);

        matrices.scale(0.5f, 0.5f, 0.5f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getRotationAngle()));
        matrices.translate(-0.5f, 0, -0.5f);

        // Ottieni il modello del blocco
        BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
        BakedModel model = blockRenderManager.getModel(BlockList.CUSTOM_BLOCK_WITH_ENTITY.getDefaultState());

        // Renderizza il modello
        blockRenderManager.getModelRenderer().render(
                entity.getWorld(),
                model,
                entity.getCachedState(),
                entity.getPos(),
                matrices,
                vertexConsumers.getBuffer(RenderLayer.getSolid()),
                false,
                entity.getWorld().getRandom(),
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
        Tessellator tessellator = Tessellator.getInstance();

        //For colors
        BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION_TEXTURE);
        buffer.vertex(matrix, 0.5f, 1.0f, 0.5f).texture(0.5F, 0).color(255, 255, 255, 255).light(light);  // Punto superiore
        buffer.vertex(matrix, 1.0f, 0.0f, 0f).texture(0, 1).color(255, 255, 255, 255).light(light);        // Punto sinistro
        buffer.vertex(matrix, 0f, 0.0f, 0f).texture(1, 1).color(255, 255, 255, 255).light(light);

        RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX);
        RenderSystem.setShaderTexture(0, texture);

        //System.out.println("Texture esiste? " + MinecraftClient.getInstance().getResourceManager().getResource(texture).isPresent());



        BufferRenderer.drawWithGlobalProgram(buffer.end());

        matrices.pop(); // Rimuovi la matrice
    }



    public static void exampleTessellatorRGBTriangle(Vector3f center, MatrixStack matrices) {
        matrices.push();
        matrices.translate(center.x,center.y, center.z);

        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Tessellator tessellator = Tessellator.getInstance();

        //For colors
        BufferBuilder buffer = tessellator.begin(VertexFormat.DrawMode.TRIANGLES, VertexFormats.POSITION_COLOR);
        buffer.vertex(matrix, 0.5f, 1.0f, 0.5f).color(255, 0, 0, 255);  // Punto superiore
        buffer.vertex(matrix, 1.0f, 0.0f, 0f).color(0, 255, 0, 255);  // Punto sinistro
        buffer.vertex(matrix, 0f, 0.0f, 0f).color(0, 0, 255, 255);  // Punto destro
        RenderSystem.setShader(ShaderProgramKeys.POSITION_COLOR);

        RenderSystem.disableCull();
        RenderSystem.enableDepthTest();

        BufferRenderer.drawWithGlobalProgram(buffer.end());

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

        Identifier texture = Identifier.of(MOD_ID, "textures/block/training_custom_block_with_entity.png");
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(texture));

        // Definire vertici con rotazione applicata
        // 🔹 Fronte (Z+)
        drawCube(vertexConsumer, entry, light, overlay);


        matrices.pop();
    }


    private static void drawCube(VertexConsumer vertexConsumer,MatrixStack.Entry entry, int light, int overlay) {
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


}
