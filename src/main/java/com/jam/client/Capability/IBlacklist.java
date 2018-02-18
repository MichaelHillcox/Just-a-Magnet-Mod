package com.jam.client.Capability;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface IBlacklist {

    public void addItem(ItemStack item);
    public void removeItem(ItemStack item);

    public List<ItemStack> getItems();

}