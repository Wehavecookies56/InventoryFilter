package uk.co.wehavecookies56.inventoryfilter;

import net.minecraft.client.gui.Gui;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public interface IFilter {

    IItemHandler filterInventory();

    @SideOnly(Side.CLIENT)
    @Nullable
    Gui moduleGUI();

}
