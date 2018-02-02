package com.jam.client;

import com.jam.common.Jam;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

/**
 * Created by MiKeY on 07/08/17.
 */
public class ClientTick {
    @SubscribeEvent
    public void tickEvent( TickEvent.PlayerTickEvent event ) {

        // TODO: REVIEW: I want to see how well this runs with a large amount of items before I give this function it's own
        // threaded process.

        // Get the player
        if(!Jam.jamEnabled)
            return;

        int range = Jam.jamRange;
        double speed = Jam.jamSpeed;

        EntityPlayer player = event.player;

        // Grab a list of the items around the player
        List<EntityItem> floatingItems = event.player.getEntityWorld().getEntitiesWithinAABB(
                EntityItem.class,
                new AxisAlignedBB(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range)
        );
        
        if( floatingItems.isEmpty() )
            return;

        for( EntityItem item : floatingItems )
            item.addVelocity( (player.posX - item.posX)*speed, ((player.posY + 2)- item.posY)*speed, (player.posZ - item.posZ)*speed );
    }
}
