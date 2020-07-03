package pro.mikey.jam.client.screens;

import pro.mikey.jam.common.Jam;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.common.config.ConfigElement;

public class JamGuiConfig extends GuiConfig {
    public JamGuiConfig(GuiScreen parentScreen) {
        super(parentScreen,
                new ConfigElement(Jam.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                Jam.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(Jam.config.toString()));
    }
}