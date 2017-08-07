package com.jam;

import com.jam.client.ClientProxy;
import com.jam.common.Ref;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by MiKeY on 07/08/17.
 */

@Mod(modid= Ref.MOD_ID, version = Ref.VERSION)
public class Jam {

    public static Boolean jamEnabled = false;

    @Mod.Instance(Ref.MOD_ID)
    public static Jam instance;

    @SidedProxy(clientSide="com.jam.client.ClientProxy")
    private static ClientProxy proxy;

    @SubscribeEvent
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println("Jam Pre Init");
    }

    @SubscribeEvent
    public void init(FMLInitializationEvent event) {

    }

    @SubscribeEvent
    public void postInit(FMLPostInitializationEvent event) {

    }

}
