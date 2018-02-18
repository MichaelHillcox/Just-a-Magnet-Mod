package com.jam.common.proxy;

import com.jam.client.Capability.BlackList;
import com.jam.client.Capability.BlacklistHandler;
import com.jam.client.Capability.BlacklistStorage;
import com.jam.client.Capability.IBlacklist;
import com.jam.client.ClientTick;
import com.jam.common.config.ConfigHandler;
import com.jam.common.server.JamPacketHandler;
import com.jam.common.server.Packet;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {

    }

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register( new ConfigHandler() );
        MinecraftForge.EVENT_BUS.register( new ClientTick() );
        MinecraftForge.EVENT_BUS.register( new BlacklistHandler() );

        JamPacketHandler.NetworkInstance.registerMessage(Packet.Handler.class, Packet.class, 0, Side.SERVER);

        CapabilityManager.INSTANCE.register(IBlacklist.class, new BlacklistStorage(), BlackList.class);
    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
