package li.cil.scannable.common.neoforge.capabilities;

import li.cil.scannable.api.API;
import li.cil.scannable.common.energy.neoforge.ScannerEnergyStorage;
import li.cil.scannable.common.inventory.ScannerContainer;
import li.cil.scannable.common.item.Items;
import li.cil.scannable.common.item.ScannerModuleItem;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities.EnergyStorage;
import net.neoforged.neoforge.capabilities.Capabilities.ItemHandler;
import net.neoforged.neoforge.capabilities.ItemCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.wrapper.InvWrapper;

@EventBusSubscriber(modid = API.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class Capabilities {

    private static final RegistryAccess BUILTIN_ACCESS =
        RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);

    public static final class ScannerModule {
        public static final ItemCapability<li.cil.scannable.api.scanning.ScannerModule, Void> ITEM =
            ItemCapability.createVoid(new ResourceLocation(API.MOD_ID, "scanner_module"),
                li.cil.scannable.api.scanning.ScannerModule.class);
    }

    @SubscribeEvent
    public static void initialize(final RegisterCapabilitiesEvent event) {
        event.registerItem(ItemHandler.ITEM, (stack, unused) ->
                new InvWrapper(ScannerContainer.of(stack, BUILTIN_ACCESS)),
            Items.SCANNER.get());

        event.registerItem(EnergyStorage.ITEM, (stack, unused) -> ScannerEnergyStorage.of(stack),
            Items.SCANNER.get());

        event.registerItem(ScannerModule.ITEM, (stack, unused) ->
                ((ScannerModuleItem) stack.getItem()).getModule(),
            Items.RANGE_MODULE.get(),
            Items.ENTITY_MODULE.get(),
            Items.FRIENDLY_ENTITY_MODULE.get(),
            Items.HOSTILE_ENTITY_MODULE.get(),
            Items.BLOCK_MODULE.get(),
            Items.COMMON_ORES_MODULE.get(),
            Items.RARE_ORES_MODULE.get(),
            Items.FLUID_MODULE.get(),
            Items.CHEST_MODULE.get());
    }
}

