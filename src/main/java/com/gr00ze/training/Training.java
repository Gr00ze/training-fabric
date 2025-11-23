package com.gr00ze.training;

import com.gr00ze.training.block.BlockList;
import com.gr00ze.training.entity.EntityTypeList;
import com.gr00ze.training.item.ItemList;
import com.gr00ze.training.item.TrainingItemGroup;
import com.gr00ze.training.network.Network;
import com.gr00ze.training.particle.ParticleList;
import com.gr00ze.training.screen.ScreenTypes;
import com.gr00ze.training.sound.SoundList;
import com.gr00ze.training.util.RegisterFunctions;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

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

        ServerLifecycleEvents.START_DATA_PACK_RELOAD.register((server, resourceManager) -> {
            // Qui puoi leggere il tuo JSON/script con nuovi item
            // E registrarli nel Registry
            try {
                System.out.println("TEST");
                RegisterFunctions.registerItem("dynamic_item_reload");
            } catch (Exception e) {
                e.printStackTrace(); // Qui vedrai lo stacktrace completo in console
            }

        });
//        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
//            dispatcher.register(
//                    literal("give").executes(context -> {
//                        //String targetId = getItemIdFromCommand(context.); // pseudo-funzione
//                        /*
//                        if (targetId.startsWith("mymod:placeholder_")) {
//                            return 1;
//                        }*/
//                        return 0;
//                    })
//            );
//            dispatcher.register(literal("give")
//                    .then(argument("target", ItemStackArgumentType.itemStack())
//                            .suggests((context, builder) -> {
//                                // filtrare o mappare ID placeholder a nomi leggibili
//                                for (int i = 1; i <= 10; i++) {
//                                    builder.suggest("SpadaMagica" + i); // alias in-game
//                                }
//                                return builder.buildFuture();
//                            })
//                    )
//            );
//        });

    }
}