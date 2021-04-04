package pro.mikey.jam;

import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;
import pro.mikey.jam.packets.UpdateSettingsPacket;

import java.util.List;

@Mod.EventBusSubscriber
public class ClientTick {
    // This runs for every player so every player has the same delay
    public static int timeout = 30;

    @SubscribeEvent
    public static void tickEvent(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        // Don't run every tick, only every 30 ticks
        if (timeout > 0) {
            timeout--;
            return;
        }

        timeout = 30;

        PlayerEntity player = event.player;

        CompoundNBT tag = player.getPersistentData();
        if (!tag.getBoolean(JamNbtKeys.ENABLED)) {
            return;
        }

        boolean directTeleport = tag.getBoolean(JamNbtKeys.TELEPORT);
        int range = tag.getInt(JamNbtKeys.RANGE);
        double speed = tag.getDouble(JamNbtKeys.SPEED);

        AxisAlignedBB area = new AxisAlignedBB(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range);

        // Grab a list of the items around the player
        List<ItemEntity> floatingItems = event.player.getEntityWorld().getEntitiesWithinAABB(ItemEntity.class, area);
        List<ExperienceOrbEntity> floatingOrbs = event.player.getEntityWorld().getEntitiesWithinAABB(ExperienceOrbEntity.class, area);

        if (floatingItems.isEmpty()) {
            return;
        }

        for (ItemEntity item : floatingItems) {
            if (directTeleport) {
                ItemStack stack = item.getItem();
                int itemCount = stack.getCount();

                int hook = net.minecraftforge.event.ForgeEventFactory.onItemPickup(item, player);
                if (hook < 0) {
                    continue;
                }

                if (!player.addItemStackToInventory(stack)) {
                    continue;
                }

                ForgeEventFactory.onItemPickup(item, player);
                player.onItemPickup(player, itemCount);

                if (stack.isEmpty()) {
                    item.remove();
                    stack.setCount(itemCount);
                }

                player.addStat(Stats.ITEM_PICKED_UP.get(stack.getItem()), itemCount);
            } else {
                item.addVelocity((player.posX - item.posX) * speed, (((player.getEyePosition(1.0f)).y + 1) - item.posY) * speed, (player.posZ - item.posZ) * speed);
            }
        }

        for (ExperienceOrbEntity orb : floatingOrbs) {
            orb.addVelocity((player.posX - orb.posX) * speed, (((player.getEyePosition(1.0f)).y + 1) - orb.posY) * speed, (player.posZ - orb.posZ) * speed);
        }
    }

    @SubscribeEvent
    public static void playerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        CompoundNBT data = event.getPlayer().getPersistentData();
        if (!data.contains(JamNbtKeys.ENABLED)) {
            data.putBoolean(JamNbtKeys.ENABLED, false);
        }
        if (!data.contains(JamNbtKeys.TELEPORT)) {
            data.putBoolean(JamNbtKeys.TELEPORT, false);
        }
        if (!data.contains(JamNbtKeys.RANGE)) {
            data.putInt(JamNbtKeys.RANGE, 20);
        }
        if (!data.contains(JamNbtKeys.SPEED)) {
            data.putDouble(JamNbtKeys.SPEED, 0.2f);
        }

        System.out.println(event.getPlayer());
        Jam.HANDLER.sendTo(new UpdateSettingsPacket(data.getBoolean(JamNbtKeys.ENABLED), data.getBoolean(JamNbtKeys.TELEPORT), data.getInt(JamNbtKeys.RANGE), data.getDouble(JamNbtKeys.SPEED)), ((ServerPlayerEntity) event.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }
}
