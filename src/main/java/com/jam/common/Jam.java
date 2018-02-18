package com.jam.common;

import com.jam.client.Capability.BlacklistCapability;
import com.jam.client.Capability.DefaultBlacklistCapability;
import com.jam.client.Capability.IBlacklist;
import com.jam.client.ClientTick;
import com.jam.common.config.ConfigHandler;
import com.jam.common.lib.Ref;
import com.jam.common.proxy.IProxy;
import com.jam.common.server.JamPacketHandler;
import com.jam.common.server.Packet;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by MiKeY on 07/08/17.
 */

@Mod(modid= Ref.MOD_ID, name=Ref.MOD_NAME, version = Ref.VERSION, guiFactory = Ref.GUI_FACTORY)
public class Jam {

    public static Boolean jamEnabled = false;
    public static int jamRange = 2;
    public static double jamSpeed = 0.02;

    public static Configuration config;

    @Instance(Ref.MOD_ID)
    public static Jam instance;

    @SidedProxy(clientSide="com.jam.common.proxy.ClientProxy", serverSide = "com.jam.common.proxy.ServerProxy")
    private static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register( new ConfigHandler() );
        MinecraftForge.EVENT_BUS.register( new ClientTick() );

        ConfigHandler.init(event.getSuggestedConfigurationFile());
        System.out.println("Jam Pre Init");

        JamPacketHandler.NetworkInstance.registerMessage(Packet.Handler.class, Packet.class, 0, Side.SERVER);

        CapabilityManager.INSTANCE.register(IBlacklist.class, new BlacklistCapability(), DefaultBlacklistCapability.class);

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
