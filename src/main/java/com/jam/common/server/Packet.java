package com.jam.common.server;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class Packet implements IMessage {

    private boolean isOn;
    public Packet(boolean toSend) {
        this.isOn = toSend;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        buf.writeBoolean(isOn);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        isOn = buf.readBoolean();
    }
}
