package li.cil.scannable.client.neoforge;

import li.cil.scannable.common.container.Containers;
import li.cil.scannable.api.API;

import li.cil.scannable.client.gui.ScannerContainerScreen;
import li.cil.scannable.client.gui.BlockModuleContainerScreen;
import li.cil.scannable.client.gui.EntityModuleContainerScreen;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = "API.MOD_ID", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientScreensNeoForge {
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(Containers.SCANNER_CONTAINER.get(), ScannerContainerScreen::new);
        event.register(Containers.BLOCK_MODULE_CONTAINER.get(), BlockModuleContainerScreen::new);
        event.register(Containers.ENTITY_MODULE_CONTAINER.get(), EntityModuleContainerScreen::new);
    }
}
