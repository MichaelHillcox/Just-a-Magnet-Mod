package com.jam.client.Capability;

import net.minecraft.entity.item.EntityItem;

import java.util.List;

public interface IBlacklist {

    public void addItem(String item);
    public void removeItem(String item);

    public List<String> getItems();

}