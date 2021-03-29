package pro.mikey.jam;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import pro.mikey.jam.packets.TogglePacket;

public class KeyPressEvent {

    @SubscribeEvent
    public static void pressEvent(TickEvent.PlayerTickEvent event) {
        if(ClientSetup.toggleJam.isPressed()) {
            Jam.HANDLER.sendToServer(new TogglePacket());
        }
    }
}
