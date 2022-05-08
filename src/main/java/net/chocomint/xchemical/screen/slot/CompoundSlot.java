package net.chocomint.xchemical.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class CompoundSlot extends Slot {
	public CompoundSlot(Inventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}

	@Override
	public boolean canInsert(ItemStack stack) {
		return stack.getItem() == Items.GLASS_BOTTLE;
	}

	@Override
	public int getMaxItemCount() {
		return 1;
	}
}
