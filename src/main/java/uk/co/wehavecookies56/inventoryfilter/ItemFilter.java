package uk.co.wehavecookies56.inventoryfilter;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class ItemFilter extends Item implements IFilter {

    ItemStackHandler handler = new ItemStackHandler(36);

    public ItemFilter() {
        setRegistryName("filter");
        setUnlocalizedName(getRegistryName().toString());
        setCreativeTab(CommonProxy.tab);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return super.initCapabilities(stack, nbt);
    }

    @Override
    public IItemHandler filterInventory() {
        return null;
    }

    @SubscribeEvent
    public void onPickUp(EntityItemPickupEvent e) {
        for (int i = 0; i < e.getEntityPlayer().inventory.getSizeInventory(); i++) {
            if (e.getEntityPlayer().inventory.getStackInSlot(i).getItem() == CommonProxy.filter) {
                e.setCanceled(true);
            }
        }
    }

    @Nullable
    @Override
    public Gui moduleGUI() {
        return null;
    }
}
