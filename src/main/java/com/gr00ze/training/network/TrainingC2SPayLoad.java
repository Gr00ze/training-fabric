package com.gr00ze.training.network;

import com.gr00ze.training.util.RegisterFunctions;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record TrainingC2SPayLoad(int myVariable) implements CustomPayload{
    private static final Identifier ID = RegisterFunctions.id("training_c2s");
    public static final CustomPayload.Id<TrainingC2SPayLoad> PAYLOAD_ID = new CustomPayload.Id<>(ID);
    public static final PacketCodec<PacketByteBuf, TrainingC2SPayLoad> CODEC =
            PacketCodec.of(TrainingC2SPayLoad::encode,TrainingC2SPayLoad::decode);



    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return PAYLOAD_ID;
    }
    public static void encode(TrainingC2SPayLoad payload,PacketByteBuf buffer){
        buffer.writeInt(payload.myVariable());
    }
    public static TrainingC2SPayLoad decode(PacketByteBuf buffer){
        return new TrainingC2SPayLoad(buffer.readInt());
    }
}
