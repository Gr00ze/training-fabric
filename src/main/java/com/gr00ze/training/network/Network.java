package com.gr00ze.training.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class Network {

    public static void initialize(){
        PayloadTypeRegistry.playC2S().register(TrainingC2SPayLoad.PAYLOAD_ID, TrainingC2SPayLoad.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(TrainingC2SPayLoad.PAYLOAD_ID, NetworkHandlers::handleTrainingC2SPayLoad);

        PayloadTypeRegistry.playS2C().register(TrainingS2CPayLoad.PAYLOAD_ID, TrainingS2CPayLoad.CODEC);
        //Not here
        //ClientPlayNetworking.registerGlobalReceiver(TrainingS2CPayLoad.PAYLOAD_ID, NetworkHandlers::handleTrainingS2CPayLoad);
    };
}
