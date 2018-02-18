package com.jam.client.Capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

import javax.annotation.Nullable;

public class BlacklistCapability implements IStorage<IBlacklist> {

    private String blacklistPrefix = "blacklist_";

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IBlacklist> capability, IBlacklist instance, EnumFacing side) {
        NBTTagList tagList = new NBTTagList();
        int iteration = 0;
        for (String item : instance.getItems()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString(blacklistPrefix+iteration, item);
            tagList.appendTag(tag);
            iteration ++;
        }

        return tagList;
    }

    @Override
    public void readNBT(Capability<IBlacklist> capability, IBlacklist instance, EnumFacing side, NBTBase nbt) {
        if( instance == null )
            return;

        if( nbt != null && nbt instanceof NBTTagList ) {
            NBTTagList list = (NBTTagList) nbt;
            int iteration = 0;
            while( list.iterator().hasNext() ) {
                instance.addItem( list.get(0).toString() );
                iteration ++;
            }
        }
    }
}
