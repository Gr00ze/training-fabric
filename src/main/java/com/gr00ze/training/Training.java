package com.gr00ze.training;

import com.gr00ze.training.block.BlockList;
import com.gr00ze.training.entity.EntityTypeList;
import com.gr00ze.training.item.ItemList;
import com.gr00ze.training.item.TrainingItemGroup;
import com.gr00ze.training.network.Network;
import com.gr00ze.training.particle.ParticleList;
import com.gr00ze.training.screen.ScreenTypes;
import com.gr00ze.training.sound.SoundList;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Training implements ModInitializer {
	public static final String MOD_ID = "training";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		ItemList.initialize();
		TrainingItemGroup.initialize();
		BlockList.initialize();
		EntityTypeList.initialize();
		SoundList.initialize();
        ParticleList.initialize();
        ScreenTypes.initialize();
        Network.initialize();
		
	}
}