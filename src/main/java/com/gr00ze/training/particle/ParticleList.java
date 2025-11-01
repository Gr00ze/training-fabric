package com.gr00ze.training.particle;

import com.gr00ze.training.util.RegisterFunctions;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;

public class ParticleList {
    public static SimpleParticleType CUSTOM_SIMPLE_PARTICLE = RegisterFunctions.registerParticle("custom_simple_particle", FabricParticleTypes.simple());
    public static void initialize(){}
}
