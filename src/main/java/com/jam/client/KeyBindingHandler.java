package com.jam.client;

import com.jam.common.lib.Ref;
import com.jam.common.server.JamPacketHandler;
import com.jam.common.server.Packet;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

public class KeyBindingHandler {

    private static KeyBinding toggleJam;

    public static void init()
    {
        toggleJam = new KeyBinding(I18n.format("jam.toggle.desc"), Keyboard.KEY_NUMPAD3, Ref.MOD_NAME);
        ClientRegistry.registerKeyBinding(toggleJam);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void pressEvent(TickEvent.PlayerTickEvent event)
    {
        if(toggleJam.isPressed())
        {
            JamPacketHandler.NetworkInstance.sendToServer(new Packet());
        }
    }
}
