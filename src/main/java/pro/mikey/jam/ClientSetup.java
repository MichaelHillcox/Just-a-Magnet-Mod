package pro.mikey.jam;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.ForgeI18n;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import org.lwjgl.glfw.GLFW;

import java.util.function.Supplier;

public class ClientSetup {
    public static KeyBinding toggleJam = new KeyBinding(ForgeI18n.parseMessage("jam.toggle.key_magnet"), GLFW.GLFW_KEY_KP_3, ForgeI18n.parseMessage("jam.mod_name"));
    public static KeyBinding openGui = new KeyBinding(ForgeI18n.parseMessage("jam.toggle.key_gui"), GLFW.GLFW_KEY_KP_4, ForgeI18n.parseMessage("jam.mod_name"));

    public static void init() {
        ClientRegistry.registerKeyBinding(toggleJam);
        ClientRegistry.registerKeyBinding(openGui);
    }

    public static PlayerEntity getPlayerFromContext(Supplier<NetworkEvent.Context> ctx) {
        return ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT
            ? Minecraft.getInstance().player
            : ctx.get().getSender();
    }
}
