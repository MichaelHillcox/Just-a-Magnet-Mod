package com.jam.client;

import com.jam.client.Capability.BlackList;
import com.jam.client.Capability.BlacklistProvider;
import com.jam.client.Capability.IBlacklist;
import com.jam.common.Jam;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

/**
 * Created by MiKeY on 07/08/17.
 */
public class ClientTick {
    @SubscribeEvent
    public void tickEvent( TickEvent.PlayerTickEvent event ) {
        if( event.phase != TickEvent.Phase.END )
            return;

        EntityPlayer player = event.player;
        NBTTagCompound tag = player.getEntityData();
        if (!tag.getBoolean("jam_toggle_on"))
            return;

        IBlacklist blacklist = event.player.getCapability(BlacklistProvider.BLACKLIST_CAPABILITY, null);
        List<ItemStack> items = blacklist.getItems();

        System.out.println(items.size());

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

    @SubscribeEvent
    public void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        IBlacklist blacklist = event.player.getCapability(BlacklistProvider.BLACKLIST_CAPABILITY, null);
        blacklist.addItem(new ItemStack(Item.getItemById(1)));
    }
}
