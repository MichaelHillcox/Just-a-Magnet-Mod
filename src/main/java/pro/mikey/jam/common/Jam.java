package pro.mikey.jam.common;

import pro.mikey.jam.common.proxy.CommonProxy;
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

@Mod(modid= Jam.MOD_ID, name= Jam.MOD_NAME, version = Jam.VERSION, guiFactory = "pro.mikey.jam.common.proxy.JamGuiFactory")
public class Jam {
    public static final String MOD_ID = "justamagnet";
    public static final String MOD_NAME = "JaM - Just a Magnet";
    public static final String VERSION = "1.1.0";

    public static final SimpleNetworkWrapper NetworkInstance = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
    public static Configuration config;

    @Instance(MOD_ID)
    public static Jam instance;

    @SidedProxy(clientSide="pro.mikey.jam.common.proxy.ClientProxy", serverSide = "pro.mikey.jam.common.proxy.ServerProxy")
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
