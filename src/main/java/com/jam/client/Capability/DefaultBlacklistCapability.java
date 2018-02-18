package com.jam.client.Capability;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DefaultBlacklistCapability implements IBlacklist {

    private List<ItemStack> blacklist = new ArrayList<>();

    public DefaultBlacklistCapability() {
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
