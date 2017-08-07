package com.jam.client.proxy;

import com.jam.client.ClientTick;
import com.jam.client.KeyBindings;
import com.jam.common.Jam;
import com.jam.common.lib.Ref;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

/**
 * Created by MiKeY on 07/08/17.
 */
public class ClientProxy {
    public void proxyInit() {
        // Register Toggle Key
        Jam.jamToggle = new KeyBinding(I18n.format("jam.toggle.desc"), Keyboard.KEY_NUMPAD3, Ref.MOD_NAME);
        ClientRegistry.registerKeyBinding( Jam.jamToggle );

        // Register Events
        MinecraftForge.EVENT_BUS.register( new KeyBindings() );
        MinecraftForge.EVENT_BUS.register( new ClientTick() );
    }
}
