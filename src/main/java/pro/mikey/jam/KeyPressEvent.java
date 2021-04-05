package pro.mikey.jam;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import pro.mikey.jam.packets.TogglePacket;

@Mod.EventBusSubscriber(modid = Jam.MOD_ID, value = Dist.CLIENT)
public class KeyPressEvent {

    @SubscribeEvent
    public static void pressEvent(TickEvent.ClientTickEvent event) {
        if (ClientSetup.toggleJam.consumeClick()) {
            Jam.HANDLER.sendToServer(new TogglePacket());
        }

        if (ClientSetup.openGui.consumeClick()) {
            Minecraft.getInstance().setScreen(new ConfigScreen());
        }
    }
}
