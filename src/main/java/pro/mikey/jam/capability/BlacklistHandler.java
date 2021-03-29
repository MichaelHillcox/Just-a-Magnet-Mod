//package pro.mikey.jam.capability;
//
//import pro.mikey.jam.Jam;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.event.AttachCapabilitiesEvent;
//import net.minecraftforge.event.entity.player.PlayerEvent;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//
//public class BlacklistHandler {
//    public static final ResourceLocation BLACKLIST_CAPABILITY = new ResourceLocation(Jam.MOD_ID, "blacklist");
//
//    @SubscribeEvent
//    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
//        if( !(event.getObject() instanceof EntityPlayer) )
//            return;
//
//        event.addCapability(BLACKLIST_CAPABILITY, new BlacklistProvider());
//    }
//
//    @SubscribeEvent
//    public static void EventPlayerClone(PlayerEvent.Clone event) {
//        EntityPlayer player = event.getEntityPlayer();
//
//        IBlacklist data = player.getCapability(BlacklistProvider.BLACKLIST_CAPABILITY, null);
//        IBlacklist oldData = event.getOriginal().getCapability(BlacklistProvider.BLACKLIST_CAPABILITY, null);
//
//        data.setItems(oldData.getItems());
//    }
//}
