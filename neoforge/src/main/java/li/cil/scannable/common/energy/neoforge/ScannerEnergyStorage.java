package li.cil.scannable.common.energy.neoforge;

import li.cil.scannable.common.config.CommonConfig;
import li.cil.scannable.common.item.Items;
import li.cil.scannable.common.item.ScannerItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.neoforge.energy.EnergyStorage;

public final class ScannerEnergyStorage extends EnergyStorage {
    private static final String TAG_ENERGY = "energy";

    private final ItemStack container;

    public ScannerEnergyStorage(final ItemStack container) {
        super(CommonConfig.energyCapacityScanner);
        this.container = container;

        final CompoundTag tag = container.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        if (tag.contains(TAG_ENERGY, Tag.TAG_INT)) {
            this.energy = tag.getInt(TAG_ENERGY);
        }
    }

    public static ScannerEnergyStorage of(final ItemStack container) {
        if (container.getItem() instanceof ScannerItem) {
            return new ScannerEnergyStorage(container);
        } else {
            return new ScannerEnergyStorage(new ItemStack(Items.SCANNER.get()));
        }
    }

    // --------------------------------------------------------------------- //
    // IEnergyStorage

    @Override
    public int receiveEnergy(final int maxReceive, final boolean simulate) {
        if (!CommonConfig.useEnergy) {
            return 0;
        }

        final int received = super.receiveEnergy(maxReceive, simulate);
        if (!simulate && received != 0) {
            writeEnergyToStack();
        }
        return received;
    }

    @Override
    public int extractEnergy(final int maxExtract, final boolean simulate) {
        if (!CommonConfig.useEnergy) {
            return 0;
        }

        final int extracted = super.extractEnergy(maxExtract, simulate);
        if (!simulate && extracted != 0) {
            writeEnergyToStack();
        }
        return extracted;
    }

    private void writeEnergyToStack() {
        CustomData.update(DataComponents.CUSTOM_DATA, container, tag -> {
            if (this.energy <= 0) {
                tag.remove(TAG_ENERGY);
            } else {
                tag.putInt(TAG_ENERGY, this.energy);
            }
        });
    }
}

