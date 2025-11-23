package com.gr00ze.training;

import com.gr00ze.training.network.NetworkClient;
import com.gr00ze.training.particle.ParticleRegistry;
import com.gr00ze.training.render.RendererList;
import com.gr00ze.training.screen.ScreenInitializer;
import net.fabricmc.api.ClientModInitializer;

public class TrainingClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		//BadWayToRegisterRendering.initialize();
		RendererList.initialize();
        ParticleRegistry.registerParticles();
        ScreenInitializer.initialize();
        NetworkClient.initialize();
	}
}