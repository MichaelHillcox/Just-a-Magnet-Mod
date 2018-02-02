package com.jam.common.server;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandler implements IMessageHandler<Packet, IMessage> {
    @Override
    public IMessage onMessage(Packet message, MessageContext ctx) {

        EntityPlayerMP serverPlayer = ctx.getServerHandler().player;


        return null;
    }
}
