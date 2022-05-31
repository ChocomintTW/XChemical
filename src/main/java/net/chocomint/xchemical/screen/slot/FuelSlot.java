package net.chocomint.xchemical.screen.slot;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class FuelSlot extends Slot {
	public FuelSlot(Inventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}

	public boolean canInsert(ItemStack stack) {
		return AbstractFurnaceBlockEntity.canUseAsFuel(stack);
	}
}
