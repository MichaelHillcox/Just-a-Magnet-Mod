package pro.mikey.jam;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ForgeI18n;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;

public class ClientSetup {
    public static KeyBinding toggleJam = new KeyBinding(ForgeI18n.parseMessage("jam.toggle.desc"), GLFW.GLFW_KEY_KP_3, Jam.MOD_NAME);

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(KeyPressEvent::pressEvent);
        ClientRegistry.registerKeyBinding(toggleJam);
    }
}
