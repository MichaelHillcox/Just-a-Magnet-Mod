package com.jam.client;

import net.minecraftforge.common.MinecraftForge;

/**
 * Created by MiKeY on 07/08/17.
 */
public class ClientProxy {
    public void proxyInit() {
        MinecraftForge.EVENT_BUS.register( new KeyBindings() );
        MinecraftForge.EVENT_BUS.register( new ClientTick() );
    }
}
