package com.jam;

import com.jam.client.ClientProxy;
import com.jam.common.Ref;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by MiKeY on 07/08/17.
 */

@Mod(modid= Ref.MOD_ID, name=Ref.MOD_NAME, version = Ref.VERSION)
public class Jam {

    public static Boolean jamEnabled = false;
    public static KeyBinding jamToggle = null;
    public static int jamRange = 2;
    public static double jamSpeed = 0.02;

    @Instance(Ref.MOD_ID)
    public static Jam instance;

    @SidedProxy(clientSide="com.jam.client.ClientProxy")
    private static ClientProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println("Jam Pre Init");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.proxyInit();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
