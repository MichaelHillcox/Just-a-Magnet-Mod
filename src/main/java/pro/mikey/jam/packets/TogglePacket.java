package pro.mikey.jam.packets;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import pro.mikey.jam.JamNbtKeys;

import java.util.function.Supplier;

public class TogglePacket {

    public TogglePacket() {
    }

    public static TogglePacket decode(PacketBuffer buf) {
        return new TogglePacket();
    }

    public static void handle(TogglePacket message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity player = ctx.get().getSender();
            if (player == null) {
                return;
            }

            CompoundNBT tag = player.getPersistentData();
            tag.putBoolean(JamNbtKeys.ENABLED, !tag.getBoolean(JamNbtKeys.ENABLED));
            if (tag.getBoolean(JamNbtKeys.ENABLED)) {
                player.displayClientMessage(new TranslationTextComponent("jam.toggle.enabled"), false);
            } else {
                player.displayClientMessage(new TranslationTextComponent("jam.toggle.disabled"), false);
            }
        });
        ctx.get().setPacketHandled(true);
    }

    public void encode(PacketBuffer buf) {
    }
}
