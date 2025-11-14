package com.gr00ze.training.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class Network {
    public static void initialize(){
        ClientPlayNetworking.registerGlobalReceiver(TrainingS2CPayLoad.PAYLOAD_ID, NetworkHandlers::handleTrainingS2CPayLoad);
    }


}
