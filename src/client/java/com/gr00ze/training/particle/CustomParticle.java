package com.gr00ze.training.particle;

import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class CustomParticle extends SpriteBillboardParticle {
    protected CustomParticle(ClientWorld world, double x, double y, double z, double speedX, double speedY, double speedZ) {
        super(world, x,y,z,speedX,speedY,speedZ);
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        super.render(vertexConsumer, camera, tickDelta);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;
        public Factory(SpriteProvider spriteProvider){
            this.spriteProvider = spriteProvider;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double speedX, double speedY, double speedZ) {
            CustomParticle customParticle = new CustomParticle(world, x,y,z,speedX,speedY,speedZ);
            customParticle.setSprite(spriteProvider);
            return customParticle;
        }
    }
}
