package com.jam.client;

import com.jam.Jam;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
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
    public void tickEnd( TickEvent.PlayerTickEvent event ) {

        // I want to see how well this runs with a large amount of items before I give this function it's own
        // threaded process.

        // Get the player
        if(!Jam.jamEnabled)
            return;

        EntityPlayer player = event.player;

        range = Jam.jamRange;
        // Grab a list of the items around the player
        List<EntityItem> floatingItems = event.player.getEntityWorld().getEntitiesWithinAABB(
                EntityItem.class,
                player.getEntityBoundingBox().expand(range, range, range)
        );

        if( floatingItems.isEmpty() )
            return;

        for( EntityItem item : floatingItems )
            item.addVelocity( (player.posX - item.posX)*speed, (player.posY - item.posY)*speed, (player.posZ - item.posZ)*speed );
    }
}
