package pro.mikey.jam.client;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

public class ClientTick {
    @SubscribeEvent
    public void tickEvent(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END)
            return;

        EntityPlayer player = event.player;
        NBTTagCompound tag = player.getEntityData();
        if (!tag.getBoolean("jam_toggle_on"))
            return;

//        IBlacklist blacklist = event.player.getCapability(BlacklistProvider.BLACKLIST_CAPABILITY, null);
//        List<ItemStack> items = blacklist.getItems();
//
//        System.out.println(items.size());

        boolean directTeleport = tag.getBoolean("jam_teleport");
        int range = tag.getInteger("jam_range");
        double speed = tag.getDouble("jam_speed");

        AxisAlignedBB area = new AxisAlignedBB(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range);

        // Grab a list of the items around the player
        List<EntityItem> floatingItems = event.player.getEntityWorld().getEntitiesWithinAABB(EntityItem.class, area);
        List<EntityXPOrb> floatingOrbs = event.player.getEntityWorld().getEntitiesWithinAABB(EntityXPOrb.class, area);

        if (floatingItems.isEmpty())
            return;

        for (EntityItem item : floatingItems) {
            if (directTeleport) {
                ItemStack stack = item.getItem();
                int itemCount = stack.getCount();

                int hook = net.minecraftforge.event.ForgeEventFactory.onItemPickup(item, player);
                if (hook < 0)
                    continue;

                if (!player.addItemStackToInventory(stack))
                    continue;

                FMLCommonHandler.instance().firePlayerItemPickupEvent(player, item);
                player.onItemPickup(player, itemCount);

                if (stack.isEmpty())
                {
                    item.setDead();
                    stack.setCount(itemCount);
                }

                player.addStat(StatList.getObjectsPickedUpStats(stack.getItem()), itemCount);
            }
            else
                item.addVelocity((player.posX - item.posX) * speed, ((player.getPositionEyes(1.0f)).y - item.posY) * speed, (player.posZ - item.posZ) * speed);
        }

        for (EntityXPOrb orb : floatingOrbs) {
            orb.addVelocity((player.posX - orb.posX) * speed, ((player.getPositionEyes(1.0f)).y - orb.posY) * speed, (player.posZ - orb.posZ) * speed);
        }
    }

    @SubscribeEvent
    public void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        NBTTagCompound data = event.player.getEntityData();

        if (!data.hasKey("jam_toggle_on")) data.setBoolean("jam_toggle_on", false);
        if (!data.hasKey("jam_teleport")) data.setBoolean("jam_teleport", false);
        if (!data.hasKey("jam_range")) data.setInteger("jam_range", 20);
        if (!data.hasKey("jam_speed")) data.setDouble("jam_speed", 0.2f);

//        IBlacklist blacklist = event.player.getCapability(BlacklistProvider.BLACKLIST_CAPABILITY, null);
//        blacklist.addItem(new ItemStack(Item.getItemById(1)));
    }
}
