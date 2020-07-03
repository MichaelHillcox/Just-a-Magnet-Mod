package pro.mikey.jam.client.Capability;

import net.minecraft.item.ItemStack;

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

    @Override
    public void setItems(List<ItemStack> items) {
        blacklist.addAll(items);
    }

}
