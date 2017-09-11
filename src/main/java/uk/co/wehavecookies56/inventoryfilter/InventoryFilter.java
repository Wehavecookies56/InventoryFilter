package uk.co.wehavecookies56.inventoryfilter;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = InventoryFilter.MODID, version = InventoryFilter.VERSION, name = InventoryFilter.NAME)
public class InventoryFilter {
    public static final String MODID = "inventoryfilter";
    public static final String NAME = "Inventory Filter";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "uk.co.wehavecookies56.inventoryfilter.ClientProxy", serverSide = "uk.co.wehavecookies56.inventoryfilter.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new CommonProxy());
        proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }
}
