package uk.co.wehavecookies56.inventoryfilter;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemFilterHandler {

    @CapabilityInject(IFilterHandler.class)
    private static final Capability<IFilterHandler> FILTER_HANDLER = null;

    public static void init() {
        CapabilityManager.INSTANCE.register(ItemFilterHandler.IFilterHandler.class, new Storage(), ItemFilterHandler.Default.class);
    }

    @SubscribeEvent
    public void attach(AttachCapabilitiesEvent<ItemStack> e) {
        if (e.getObject().getItem() instanceof IFilter) {
            e.addCapability(new ResourceLocation(InventoryFilter.MODID, "filter_handler"), new Provider());
        }
    }

    public enum Mode {
        BLACKLIST, WHITELIST
    }

    public static IFilterHandler getHandler(ItemStack stack) {
        if (stack.hasCapability(FILTER_HANDLER, null)) {
            return stack.getCapability(FILTER_HANDLER, null);
        }
        return null;
    }

    public static boolean hasHandler(ItemStack stack) {
        if (stack.hasCapability(FILTER_HANDLER, null)) {
            return true;
        }
        return false;
    }

    public interface IFilterHandler {
        Mode getListMode();
        void setMode(Mode mode);
        ItemStackHandler getItemFilterHandler();
        ItemStackHandler getModulesHandler();
        void setItemFilterHandler(ItemStackHandler handler);
        void setModulesHandler(ItemStackHandler handler);
    }

    public static class Default implements IFilterHandler {
        private Mode mode = Mode.BLACKLIST;
        private ItemStackHandler filterHandler = new ItemStackHandler(36);
        private ItemStackHandler moduleHandler = new ItemStackHandler(1);
        @Override
        public Mode getListMode() {
            return mode;
        }

        @Override
        public void setMode(Mode mode) {
            this.mode = mode;
        }

        @Override
        public ItemStackHandler getItemFilterHandler() {
            return filterHandler;
        }

        @Override
        public ItemStackHandler getModulesHandler() {
            return moduleHandler;
        }

        @Override
        public void setItemFilterHandler(ItemStackHandler handler) {
            this.filterHandler = handler;
        }

        @Override
        public void setModulesHandler(ItemStackHandler handler) {
            this.moduleHandler = handler;
        }
    }

    public static class Storage implements Capability.IStorage<IFilterHandler> {

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IFilterHandler> capability, IFilterHandler instance, EnumFacing side) {
            final NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("mode", instance.getListMode().ordinal());
            tag.setTag("filterHandler", instance.getItemFilterHandler().serializeNBT());
            tag.setTag("moduleHandler", instance.getModulesHandler().serializeNBT());
            return null;
        }

        @Override
        public void readNBT(Capability<IFilterHandler> capability, IFilterHandler instance, EnumFacing side, NBTBase nbt) {
            final NBTTagCompound tag = (NBTTagCompound) nbt;
            instance.setMode(Mode.values()[tag.getInteger("mode")]);
            instance.getItemFilterHandler().deserializeNBT(tag.getCompoundTag("filterHandler"));
            instance.getModulesHandler().deserializeNBT(tag.getCompoundTag("moduleHandler"));
        }
    }

    public static class Provider implements ICapabilitySerializable<NBTTagCompound> {
        IFilterHandler instance = FILTER_HANDLER.getDefaultInstance();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == FILTER_HANDLER;
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            return hasCapability(capability, facing) ? FILTER_HANDLER.cast(instance) : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return (NBTTagCompound) FILTER_HANDLER.getStorage().writeNBT(FILTER_HANDLER, instance, null);
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            FILTER_HANDLER.getStorage().readNBT(FILTER_HANDLER, instance, null, nbt);
        }
    }

}
