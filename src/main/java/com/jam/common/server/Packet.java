package com.jam.common.server;

import com.jam.common.Jam;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class Packet implements IMessage {

    public Packet() { }

    @Override
    public void fromBytes(ByteBuf buf) { }

    @Override
    public void toBytes(ByteBuf buf) { }

    public static class Handler implements IMessageHandler<Packet, IMessage> {
        @Override
        public IMessage onMessage(Packet message, MessageContext ctx) {

            Jam.jamEnabled = !Jam.jamEnabled;
            if( Jam.jamEnabled )
                ctx.getServerHandler().player.sendMessage(new TextComponentString("[Jam] Enabled"));
            else
                ctx.getServerHandler().player.sendMessage(new TextComponentString("[Jam] Disabled"));
            return null;
        }
    }
}
