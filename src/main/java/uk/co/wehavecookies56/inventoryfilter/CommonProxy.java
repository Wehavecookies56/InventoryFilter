package uk.co.wehavecookies56.inventoryfilter;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy {

    public static Item filter;

    public static CreativeTabs tab = new Tab();

    public static class Tab extends CreativeTabs {
        public Tab() {
            super(CreativeTabs.getNextID(), "inventory_filter_tab");
        }

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(filter);
        }
    }

    public static final Item[] ITEMS = {
            filter = new ItemFilter()
    };

    public void preInit(FMLPreInitializationEvent e) {

    }

    public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new ItemFilter());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        for (Item i : CommonProxy.ITEMS) {
            e.getRegistry().register(i);
        }
    }

}
