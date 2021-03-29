package pro.mikey.jam;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;
import pro.mikey.jam.packets.TogglePacket;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Jam.MOD_ID)
public class KeyBindingHandler {
    private static KeyBinding toggleJam;

    public static void init() {
        toggleJam = new KeyBinding(I18n.format("jam.toggle.desc"), GLFW.GLFW_KEY_KP_3, Jam.MOD_NAME);
        ClientRegistry.registerKeyBinding(toggleJam);
    }

    @SubscribeEvent
    public static void pressEvent(TickEvent.PlayerTickEvent event) {
        if(toggleJam.isPressed()) {
            Jam.HANDLER.sendToServer(new TogglePacket());
        }
    }
}
