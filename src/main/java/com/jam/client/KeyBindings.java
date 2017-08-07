package com.jam.client;

import com.jam.common.Jam;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/**
 * Created by MiKeY on 07/08/17.
 */
public class KeyBindings {

    private Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        // Check if we should be watching for inputs
        if( (FMLClientHandler.instance().isGUIOpen(GuiChat.class)) || (mc.currentScreen != null) || (mc.world == null) )
            return;

        // Toggle Our enabled state
        if(Jam.jamToggle.isPressed()) {
            Jam.jamEnabled = !Jam.jamEnabled;
            if( Jam.jamEnabled )
                mc.ingameGUI.getChatGUI().printChatMessage( new TextComponentString(I18n.format("jam.toggle.enabled")));
            else
                mc.ingameGUI.getChatGUI().printChatMessage( new TextComponentString(I18n.format("jam.toggle.disabled")));
        }

    }
}
