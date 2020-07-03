package pro.mikey.jam.client.Capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlacklistProvider implements ICapabilitySerializable<NBTBase> {


    @CapabilityInject(IBlacklist.class)
    public static final Capability<IBlacklist> BLACKLIST_CAPABILITY = null;
    private IBlacklist instance = BLACKLIST_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == BLACKLIST_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == BLACKLIST_CAPABILITY ? BLACKLIST_CAPABILITY.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return BLACKLIST_CAPABILITY.getStorage().writeNBT(BLACKLIST_CAPABILITY, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        BLACKLIST_CAPABILITY.getStorage().readNBT(BLACKLIST_CAPABILITY, this.instance, null, nbt);
    }
}
