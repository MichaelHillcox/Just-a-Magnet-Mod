package pro.mikey.jam.common.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class TogglePacket implements IMessage {

    public TogglePacket() { }

    @Override
    public void fromBytes(ByteBuf buf) { }

    @Override
    public void toBytes(ByteBuf buf) { }

    public static class Handler implements IMessageHandler<TogglePacket, IMessage> {
        @Override
        public IMessage onMessage(TogglePacket message, MessageContext ctx) {

            EntityPlayerMP player = ctx.getServerHandler().player;

            NBTTagCompound tag = player.getEntityData();
            tag.setBoolean("jam_toggle_on", !tag.getBoolean("jam_toggle_on"));
            if( tag.getBoolean("jam_toggle_on") )
                player.sendMessage(new TextComponentString("[Jam] Enabled"));
            else
                player.sendMessage(new TextComponentString("[Jam] Disabled"));
            return null;
        }
    }
}