package com.jam.common;

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

        Jam.jamRange = config.getInt("range", Configuration.CATEGORY_GENERAL, 10, 1, 60, "The range that magnet will pick up blocks");
        Jam.jamSpeed = config.getFloat("speed", Configuration.CATEGORY_GENERAL, 0.02f, 0.01f, 0.1f, "The Speed that the item will be brought to you");
        Jam.jamTeleport = config.getBoolean("shouldTeleport", Configuration.CATEGORY_GENERAL, false, "Whether or not items will move to your or just teleport instantly :D");

        if (config.hasChanged())
            config.save();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Jam.MOD_ID))
            SyncConfig(Jam.config);
    }
}