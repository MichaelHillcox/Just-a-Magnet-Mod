package com.jam.common.server;

import com.jam.common.Jam;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandler implements IMessageHandler<Packet, IMessage> {
    @Override
    public IMessage onMessage(Packet message, MessageContext ctx) {

//        EntityPlayerMP serverPlayer = ctx.getServerHandler().player;

        Jam.jamEnabled = !Jam.jamEnabled;

        return null;
    }
}
