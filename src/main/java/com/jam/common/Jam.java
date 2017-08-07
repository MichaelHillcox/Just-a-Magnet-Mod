package com.jam.common;

import com.jam.client.proxy.ClientProxy;
import com.jam.common.config.ConfigHandler;
import com.jam.common.lib.Ref;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by MiKeY on 07/08/17.
 */

@Mod(modid= Ref.MOD_ID, name=Ref.MOD_NAME, version = Ref.VERSION, guiFactory = Ref.GUI_FACTORY)
public class Jam {

    public static Boolean jamEnabled = false;
    public static KeyBinding jamToggle = null;
    public static int jamRange = 2;
    public static double jamSpeed = 0.02;

    public static Configuration config;

    @Instance(Ref.MOD_ID)
    public static Jam instance;

    @SidedProxy(clientSide="com.jam.client.proxy.ClientProxy")
    private static ClientProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        System.out.println("Jam Pre Init");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.proxyInit();
    }

}
