package pro.mikey.jam;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pro.mikey.jam.packets.TogglePacket;
import pro.mikey.jam.packets.UpdateSettingsPacket;

@Mod(Jam.MOD_ID)
public class Jam {
    public static final String MOD_ID = "justamagnet";
    public static final Logger LOGGER = LogManager.getLogger();

    private static final String PROTOCOL_VERSION = Integer.toString(1);
    public static final SimpleChannel HANDLER = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(MOD_ID, "main"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );

    public Jam() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        HANDLER.registerMessage(0, TogglePacket.class, TogglePacket::encode, TogglePacket::decode, TogglePacket::handle);
        HANDLER.registerMessage(1, UpdateSettingsPacket.class, UpdateSettingsPacket::encode, UpdateSettingsPacket::decode, UpdateSettingsPacket::handle);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ClientSetup.init();
    }
}
