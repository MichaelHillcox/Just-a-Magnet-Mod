package com.jam.common;

import com.jam.common.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * Created by MiKeY on 07/08/17.
 */

@Mod(modid= Jam.MOD_ID, name= Jam.MOD_NAME, version = Jam.VERSION, guiFactory = Jam.GUI_FACTORY)
public class Jam {
    public static final String MOD_ID = "justamagnet";
    public static final String MOD_NAME = "JaM - Just a Magnet";
    public static final String VERSION = "1.0.0";
    public static final String GUI_FACTORY = "com.jam.common.proxy.JamGuiFactory";

    public static final SimpleNetworkWrapper NetworkInstance = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);

    public static int jamRange = 2;
    public static double jamSpeed = 0.02;
    public static boolean jamTeleport = false;

    public static Configuration config;

    @Instance(MOD_ID)
    public static Jam instance;

    @SidedProxy(clientSide="com.jam.common.proxy.ClientProxy", serverSide = "com.jam.common.proxy.ServerProxy")
    private static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.init(event.getSuggestedConfigurationFile());
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
