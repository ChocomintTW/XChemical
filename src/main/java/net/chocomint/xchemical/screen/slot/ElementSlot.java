package net.chocomint.xchemical.screen.slot;

import net.chocomint.xchemical.item.custom.ElementItem;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class ElementSlot extends Slot {
	public ElementSlot(Inventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}

	@Override
	public boolean canInsert(ItemStack stack) {
		return stack.getItem() instanceof ElementItem;
	}
}
