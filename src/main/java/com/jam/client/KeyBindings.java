package com.jam.client;

import com.jam.client.proxy.ClientProxy;
import com.jam.common.Jam;
import com.jam.common.server.JamPacketHandler;
import com.jam.common.server.Packet;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Created by MiKeY on 07/08/17.
 */
public class KeyBindings {

    private static Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        // Check if we should be watching for inputs
        if( (FMLClientHandler.instance().isGUIOpen(GuiChat.class)) || (mc.currentScreen != null) || (mc.world == null) )
            return;

        // Toggle Our enabled state
        if(ClientProxy.jamToggle.isPressed()) {
            Jam.jamEnabled = !Jam.jamEnabled;
            if( Jam.jamEnabled )
                mc.ingameGUI.getChatGUI().printChatMessage( new TextComponentString(I18n.format("jam.toggle.enabled")));
            else
                mc.ingameGUI.getChatGUI().printChatMessage( new TextComponentString(I18n.format("jam.toggle.disabled")));

            if( mc.world.isRemote ) {
                JamPacketHandler.NetworkInstace.sendToServer(new Packet(Jam.jamEnabled));
            }
        }

    }
}
