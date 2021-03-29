package pro.mikey.jam;

import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;

import java.util.List;

@Mod.EventBusSubscriber
public class ClientTick {
    @SubscribeEvent
    public static void tickEvent(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        PlayerEntity player = event.player;
        CompoundNBT tag = player.getPersistentData();
        if (!tag.getBoolean("jam_toggle_on")) {
            return;
        }

        System.out.println(FMLLoader.getDist());
        System.out.println("hi");

        //        IBlacklist blacklist = event.player.getCapability(BlacklistProvider.BLACKLIST_CAPABILITY, null);
        //        List<ItemStack> items = blacklist.getItems();
        //
        //        System.out.println(items.size());

        boolean directTeleport = false; //= tag.getBoolean("jam_teleport");
        int range = 30; //tag.getInt("jam_range");
        double speed = .1D; //tag.getDouble("jam_speed");

        AxisAlignedBB area = new AxisAlignedBB(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range);

        // Grab a list of the items around the player
        List<ItemEntity> floatingItems = event.player.getEntityWorld().getEntitiesWithinAABB(ItemEntity.class, area);
        List<ExperienceOrbEntity> floatingOrbs = event.player.getEntityWorld().getEntitiesWithinAABB(ExperienceOrbEntity.class, area);

        if (floatingItems.isEmpty())
            return;

        for (ItemEntity item : floatingItems) {
            if (directTeleport) {
                ItemStack stack = item.getItem();
                int itemCount = stack.getCount();

                int hook = net.minecraftforge.event.ForgeEventFactory.onItemPickup(item, player);
                if (hook < 0)
                    continue;

                if (!player.addItemStackToInventory(stack))
                    continue;

                // TODO: fire right event and fix for items that fail to go into the inventory. The above might do this.
                ForgeEventFactory.onItemPickup(item, player);
//                FMLCommonHandler.instance().firePlayerItemPickupEvent(player, item);
                player.onItemPickup(player, itemCount);

                if (stack.isEmpty())
                {
                    item.remove();
                    stack.setCount(itemCount);
                }

                player.addStat(Stats.ITEM_PICKED_UP.get(stack.getItem()), itemCount);
            }
            else
                item.addVelocity((player.posX - item.posX) * speed, ((player.getEyePosition(1.0f)).y - item.posY) * speed, (player.posZ - item.posZ) * speed);
        }

        for (ExperienceOrbEntity orb : floatingOrbs) {
            orb.addVelocity((player.posX - orb.posX) * speed, ((player.getEyePosition(1.0f)).y - orb.posY) * speed, (player.posZ - orb.posZ) * speed);
        }
    }

    @SubscribeEvent
    public void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        CompoundNBT data = event.getPlayer().getPersistentData();

        if (!data.contains("jam_toggle_on")) data.putBoolean("jam_toggle_on", false);
        if (!data.contains("jam_teleport")) data.putBoolean("jam_teleport", false);
        if (!data.contains("jam_range")) data.putInt("jam_range", 20);
        if (!data.contains("jam_speed")) data.putDouble("jam_speed", 0.2f);
    }
}
