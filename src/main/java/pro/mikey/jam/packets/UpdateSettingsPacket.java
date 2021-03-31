package pro.mikey.jam.packets;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import pro.mikey.jam.ClientSetup;
import pro.mikey.jam.JamNbtKeys;

import java.util.function.Supplier;

public class UpdateSettingsPacket {
    public final boolean enabled;
    public final boolean teleport;
    public final int range;
    public final double speed;

    public UpdateSettingsPacket(boolean enabled, boolean teleport, int range, double speed) {
        this.enabled = enabled;
        this.teleport = teleport;
        this.range = range;
        this.speed = speed;
    }

    public static UpdateSettingsPacket decode(PacketBuffer buf) {
        return new UpdateSettingsPacket(buf.readBoolean(), buf.readBoolean(), buf.readInt(), buf.readDouble());
    }

    public static void handle(UpdateSettingsPacket message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity player;
            if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                player = ClientSetup.getPlayer();
            } else {
                player = ctx.get().getSender();
            }

            if (player == null) {
                return;
            }

            CompoundNBT tag = player.getPersistentData();
            tag.putBoolean(JamNbtKeys.ENABLED, message.enabled);
            tag.putBoolean(JamNbtKeys.TELEPORT, message.teleport);
            tag.putInt(JamNbtKeys.RANGE, message.range);
            tag.putDouble(JamNbtKeys.SPEED, message.speed);
        });
        ctx.get().setPacketHandled(true);
    }

    public void encode(PacketBuffer buf) {
        buf.writeBoolean(this.enabled);
        buf.writeBoolean(this.teleport);
        buf.writeInt(this.range);
        buf.writeDouble(this.speed);
    }
}
