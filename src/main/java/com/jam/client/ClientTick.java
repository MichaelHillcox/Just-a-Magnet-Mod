package com.jam.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by MiKeY on 07/08/17.
 */
public class ClientTick {
    private static final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void tickEnd( TickEvent.ClientTickEvent event ) {

    }
}
