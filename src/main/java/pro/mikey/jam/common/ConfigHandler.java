package pro.mikey.jam.common;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

/**
 * Created by MiKeY on 07/08/17.
 */
public class ConfigHandler {
    public static void init(File suggestedConfig) {
        Jam.config = new Configuration(suggestedConfig);
        Jam.config.load();

        SyncConfig(Jam.config);
    }

    private static void SyncConfig(Configuration config) {
        config.setCategoryComment(Configuration.CATEGORY_GENERAL, "Use the in-game config editor.");

        if (config.hasChanged())
            config.save();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Jam.MOD_ID))
            SyncConfig(Jam.config);
    }
}
