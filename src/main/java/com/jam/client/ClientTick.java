package com.jam.client;

import com.jam.common.Jam;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
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
        EntityPlayer player = event.player;
        NBTTagCompound tag = player.getEntityData();
        if (!tag.getBoolean("jam_toggle_on"))
            return;

        int range = Jam.jamRange;
        double speed = Jam.jamSpeed;

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
