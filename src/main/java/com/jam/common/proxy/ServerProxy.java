package com.jam.common.proxy;

import com.jam.client.ClientTick;
import com.jam.common.server.ServerTick;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    public void init(FMLInitializationEvent event) {

//        MinecraftForge.EVENT_BUS.register( new ServerTick() );
        MinecraftForge.EVENT_BUS.register( new ClientTick() );
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }
}
