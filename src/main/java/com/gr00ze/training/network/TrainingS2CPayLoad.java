package com.gr00ze.training.network;

import com.gr00ze.training.util.RegisterFunctions;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;


public record TrainingS2CPayLoad(int myVariable) implements CustomPayload {
    private static final Identifier ID = RegisterFunctions.id("training_s2c");
    public static final Id<TrainingS2CPayLoad> PAYLOAD_ID = new Id<>(ID);
    public static final PacketCodec<PacketByteBuf, TrainingS2CPayLoad> CODEC =
            PacketCodec.of(TrainingS2CPayLoad::encode, TrainingS2CPayLoad::new);

    //Decode alternative
    public TrainingS2CPayLoad(PacketByteBuf packetByteBuf) {
        this(packetByteBuf.readInt());
    }

    public static void encode(TrainingS2CPayLoad payLoad, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeInt(payLoad.myVariable());
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return PAYLOAD_ID;
    }
}
