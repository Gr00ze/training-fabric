package com.gr00ze.training.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class ParticleRegistry {
    public static void registerParticles(){
        ParticleFactoryRegistry.getInstance().register(ParticleList.CUSTOM_SIMPLE_PARTICLE, CustomParticle.Factory::new);
    }
}
