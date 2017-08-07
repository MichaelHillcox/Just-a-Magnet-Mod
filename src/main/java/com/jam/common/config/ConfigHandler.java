package com.jam.common.config;

import com.jam.common.Jam;
import com.jam.common.lib.Ref;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

/**
 * Created by MiKeY on 07/08/17.
 */
public class ConfigHandler {
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if( event.getModID().equals(Ref.MOD_ID) )
            SyncConfig( Jam.config );
    }

    public static void init(File suggestedConfig) {
        Jam.config = new Configuration( suggestedConfig );
        Jam.config.load();

       SyncConfig(Jam.config);
    }

    private static void SyncConfig( Configuration config ) {
        config.setCategoryComment(Configuration.CATEGORY_GENERAL, "Use the in-game config editor.");
        //getInt(String name, String category, int defaultValue, int minValue, int maxValue, String comment, String langKey)
        Jam.jamRange = config.getInt("range", Configuration.CATEGORY_GENERAL,10, 1, 60, "The range that magnet will pick up blocks");
        Jam.jamSpeed = config.getFloat("speed", Configuration.CATEGORY_GENERAL,0.02f, 0.01f, 0.1f, "The Speed that the item will be brought to you");

        if( config.hasChanged() )
            config.save();
    }
}
