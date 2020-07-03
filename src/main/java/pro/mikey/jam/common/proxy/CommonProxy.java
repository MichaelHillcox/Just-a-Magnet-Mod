package pro.mikey.jam.common.proxy;

import pro.mikey.jam.client.Capability.BlackList;
import pro.mikey.jam.client.Capability.BlacklistHandler;
import pro.mikey.jam.client.Capability.BlacklistStorage;
import pro.mikey.jam.client.Capability.IBlacklist;
import pro.mikey.jam.client.ClientTick;
import pro.mikey.jam.common.Jam;
import pro.mikey.jam.common.ConfigHandler;
import pro.mikey.jam.common.packets.TogglePacket;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {}

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register( new ConfigHandler() );
        MinecraftForge.EVENT_BUS.register( new ClientTick() );
        MinecraftForge.EVENT_BUS.register( new BlacklistHandler() );

        Jam.NetworkInstance.registerMessage(TogglePacket.Handler.class, TogglePacket.class, 0, Side.SERVER);

        CapabilityManager.INSTANCE.register(IBlacklist.class, new BlacklistStorage(), BlackList.class);
    }

    public void postInit(FMLPostInitializationEvent event) {}
}
