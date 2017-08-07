package com.jam.client;

import com.jam.Jam;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

/**
 * Created by MiKeY on 07/08/17.
 */
public class ClientTick {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private int range;
    private double speed = 0.05;

    @SubscribeEvent
    public void tickEnd( TickEvent.ClientTickEvent event ) {
        // I want to see how well this runs with a large amount of items before I give this function it's own
        // threaded process.

        // Get the player
        if(!Jam.jamEnabled || mc.theWorld == null || mc.thePlayer == null)
            return;

        range = Jam.jamRange;
        // Grab a list of the items around the player
        List<EntityItem> floatingItems = mc.theWorld.getEntitiesWithinAABB(
                EntityItem.class,
                new AxisAlignedBB(mc.thePlayer.posX - range, mc.thePlayer.posY - range, mc.thePlayer.posZ - range, mc.thePlayer.posX + range, mc.thePlayer.posY + range, mc.thePlayer.posZ + range )
        );

        if( floatingItems.isEmpty() )
            return;

        for( EntityItem item : floatingItems )
            item.addVelocity( (mc.thePlayer.posX - item.posX)*speed, (mc.thePlayer.posY - item.posY)*speed, (mc.thePlayer.posZ - item.posZ)*speed );
    }
}
