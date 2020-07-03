package pro.mikey.jam.client.Capability;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public interface IBlacklist {

    public void addItem(ItemStack item);
    public void removeItem(ItemStack item);
    public void setItems(List<ItemStack> items);

    public List<ItemStack> getItems();
}