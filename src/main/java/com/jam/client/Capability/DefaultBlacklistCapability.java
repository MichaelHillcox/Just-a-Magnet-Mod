package com.jam.client.Capability;

import net.minecraft.entity.item.EntityItem;

import java.util.ArrayList;
import java.util.List;

public class DefaultBlacklistCapability implements IBlacklist {

    private List<String> blacklist = new ArrayList<>();

    public DefaultBlacklistCapability() {
    }

    @Override
    public void addItem(String item) {
        blacklist.add( item );
    }

    @Override
    public void removeItem(String item) {
        blacklist.remove( item );
    }

    @Override
    public List<String> getItems() {
        return blacklist;
    }
}
