package com.jam.client.gui;

import com.jam.common.Jam;
import com.jam.common.lib.Ref;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.common.config.ConfigElement;

/**
 * Created by MiKeY on 07/08/17.
 */
public class JamGuiConfig extends GuiConfig {
    public JamGuiConfig(GuiScreen parentScreen) {
        super(parentScreen,
                new ConfigElement(Jam.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                Ref.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(Jam.config.toString()));
    }
}
