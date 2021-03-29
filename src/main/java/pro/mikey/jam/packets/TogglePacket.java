package pro.mikey.jam.packets;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TogglePacket {

    public TogglePacket() { }
    public static TogglePacket decode(PacketBuffer buf) {
        return new TogglePacket();
    }

    public void encode(PacketBuffer buf) { }

    public static void handle(TogglePacket message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity player = ctx.get().getSender();
            if (player == null)
                return;

            // TODO: translate this.
            CompoundNBT tag = player.getPersistentData();
            tag.putBoolean("jam_toggle_on", !tag.getBoolean("jam_toggle_on"));
            if( tag.getBoolean("jam_toggle_on") )
                player.sendMessage(new StringTextComponent("[Jam] Enabled"));
            else
                player.sendMessage(new StringTextComponent("[Jam] Disabled"));
        });
        ctx.get().setPacketHandled(true);
    }
}
