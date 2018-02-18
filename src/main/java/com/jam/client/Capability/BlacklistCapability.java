package com.jam.client.Capability;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

import javax.annotation.Nullable;

public class BlacklistCapability implements IStorage<IBlacklist> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IBlacklist> capability, IBlacklist instance, EnumFacing side) {
        NBTTagList tagList = new NBTTagList();

        for (ItemStack item : instance.getItems()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag = item.writeToNBT(tag);

            tagList.appendTag(tag);
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
