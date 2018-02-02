package com.jam.client.proxy;

import com.jam.client.ClientTick;
import com.jam.client.KeyBindings;
import com.jam.common.lib.Ref;
import com.jam.common.proxy.IProxy;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;

/**
 * Created by MiKeY on 07/08/17.
 */
public class ClientProxy implements IProxy {

    public static KeyBinding jamToggle = null;

    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    public void init(FMLInitializationEvent event) {
        // Register Toggle Key
        jamToggle = new KeyBinding(I18n.format("jam.toggle.desc"), Keyboard.KEY_NUMPAD3, Ref.MOD_NAME);
        ClientRegistry.registerKeyBinding( jamToggle );

        // Register Events
        MinecraftForge.EVENT_BUS.register( new KeyBindings() );
        MinecraftForge.EVENT_BUS.register( new ClientTick() );
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }
}
