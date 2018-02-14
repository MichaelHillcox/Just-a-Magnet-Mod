package com.jam.common.proxy;

import com.jam.client.KeyBindingHandler;
import com.jam.common.proxy.IProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by MiKeY on 07/08/17.
 */
public class ClientProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    public void init(FMLInitializationEvent event) {
        KeyBindingHandler.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }
}
