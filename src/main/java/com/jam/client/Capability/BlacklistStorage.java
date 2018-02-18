package com.jam.client.Capability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;

public class BlacklistStorage implements IStorage<IBlacklist> {

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
        NBTTagList list = (NBTTagList)nbt;
        for (int i = 0; i < list.tagCount(); i++) {
            instance.addItem(new ItemStack(list.getCompoundTagAt(i)));
        }
        System.out.println("READ THE NBT");
    }
}
