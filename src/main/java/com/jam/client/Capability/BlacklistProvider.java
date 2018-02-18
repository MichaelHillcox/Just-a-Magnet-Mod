package com.jam.client.Capability;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlacklistProvider implements ICapabilityProvider {


    @CapabilityInject(IBlacklist.class)
    public static final Capability<IBlacklist> BLACKLIST_CAPABILITY = null;
    private IBlacklist instance = BLACKLIST_CAPABILITY.getDefaultInstance();
    private IBlacklist capability = null;

    public BlacklistProvider() {
        setCapability(new BlackList());
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == BLACKLIST_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == BLACKLIST_CAPABILITY ? BLACKLIST_CAPABILITY.cast(this.instance) : null;
    }

    public void setCapability(IBlacklist capability) {
        this.capability = capability;
    }
}
