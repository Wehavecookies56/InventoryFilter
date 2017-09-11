package uk.co.wehavecookies56.inventoryfilter;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public interface IModule {

    void onPickUp(ItemStack itemStack, EntityItem entity);


}
