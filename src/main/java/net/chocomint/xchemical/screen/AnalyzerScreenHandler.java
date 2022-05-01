package net.chocomint.xchemical.screen;

import net.chocomint.xchemical.screen.slot.ResultSlot;
import net.chocomint.xchemical.screen.slot.AnalyzerInputSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class AnalyzerScreenHandler extends ScreenHandler {
	private final Inventory inventory;

	public AnalyzerScreenHandler(int syncId, PlayerInventory playerInventory) {
		this(syncId, playerInventory, new SimpleInventory(9));
	}

	public AnalyzerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
		super(ModScreenHandlers.ANALYZER_SCREEN_HANDLER, syncId);
		checkSize(inventory, 9);
		this.inventory = inventory;
		inventory.onOpen(playerInventory.player);

		this.addSlot(new AnalyzerInputSlot(inventory, 0, 26, 39));

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				this.addSlot(new ResultSlot(inventory, 1 + i * 4 + j, 80 + 18 * j, 30 + 18 * i));
			}
		}

		addPlayerInventory(playerInventory);
		addPlayerHotbar(playerInventory);
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return this.inventory.canPlayerUse(player);
	}

	@Override
	public ItemStack transferSlot(PlayerEntity player, int invSlot) {
		ItemStack newStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(invSlot);
		if (slot != null && slot.hasStack()) {
			ItemStack originalStack = slot.getStack();
			newStack = originalStack.copy();
			if (invSlot < this.inventory.size()) {
				if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
				return ItemStack.EMPTY;
			}

			if (originalStack.isEmpty()) {
				slot.setStack(ItemStack.EMPTY);
			} else {
				slot.markDirty();
			}
		}

		return newStack;
	}

	private void addPlayerInventory(PlayerInventory playerInventory) {
		for (int i = 0; i < 3; ++i) {
			for (int l = 0; l < 9; ++l) {
				this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
			}
		}
	}

	private void addPlayerHotbar(PlayerInventory playerInventory) {
		for (int i = 0; i < 9; ++i) {
			this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
		}
	}
}
