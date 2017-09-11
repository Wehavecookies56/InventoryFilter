package uk.co.wehavecookies56.inventoryfilter;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent e) {
        for (Item i : CommonProxy.ITEMS) {
            registerModel(i);
        }
    }

    public static void registerModel(Item i) {
        ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(InventoryFilter.MODID + ":" + i.getRegistryName().getResourcePath(), "inventory"));
    }

}
