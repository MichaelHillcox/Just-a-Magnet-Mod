package com.jam.client.Capability;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import java.util.ArrayList;
import java.util.List;

public class BlackList implements IBlacklist {

    private List<ItemStack> blacklist = new ArrayList<>();

    public BlackList() {
    }

    @Override
    public void addItem(ItemStack item) {
        blacklist.add( item );
    }

    @Override
    public void removeItem(ItemStack item) {
        blacklist.remove( item );
    }

    @Override
    public List<ItemStack> getItems() {
        return blacklist;
    }

}
