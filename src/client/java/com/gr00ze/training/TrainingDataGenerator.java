package com.gr00ze.training;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import com.gr00ze.training.provider.*;

public class TrainingDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

		//What is the difference between 1 pack or multiple?
		//
		FabricDataGenerator.Pack trainingPack = fabricDataGenerator.createPack();

		fabricDataGenerator.createPack().addProvider(TrainingLangProvider::new);
		fabricDataGenerator.createPack().addProvider(TrainingModelProvider::new);
		fabricDataGenerator.createPack().addProvider(TrainingRecipeProvider::new);

	}







}
