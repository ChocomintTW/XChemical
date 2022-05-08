package net.chocomint.xchemical.screen;

import net.chocomint.xchemical.screen.slot.CompoundSlot;
import net.chocomint.xchemical.screen.slot.ElementSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CompoundMixerScreenHandler extends ScreenHandler {
	private final Inventory inventory;
	private final PropertyDelegate propertyDelegate;

	public CompoundMixerScreenHandler(int syncId, PlayerInventory playerInventory) {
		this(syncId, playerInventory, new SimpleInventory(9), new ArrayPropertyDelegate(2));
	}

	public CompoundMixerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate delegate) {
		super(ModScreenHandlers.COMPOUND_MIXER_SCREEN_HANDLER, syncId);
		checkSize(inventory, 9);
		this.inventory = inventory;
		this.propertyDelegate = delegate;
		inventory.onOpen(playerInventory.player);

		this.addSlot(new ElementSlot(inventory, 0, 80, 18));
		this.addSlot(new ElementSlot(inventory, 1, 108, 24));
		this.addSlot(new ElementSlot(inventory, 2, 114, 52));
		this.addSlot(new ElementSlot(inventory, 3, 108, 80));
		this.addSlot(new ElementSlot(inventory, 4, 80, 86));
		this.addSlot(new ElementSlot(inventory, 5, 52, 80));
		this.addSlot(new ElementSlot(inventory, 6, 46, 52));
		this.addSlot(new ElementSlot(inventory, 7, 52, 24));
		this.addSlot(new CompoundSlot(inventory, 8, 80, 52));

		addPlayerInventory(playerInventory);
		addPlayerHotbar(playerInventory);

		addProperties(delegate);
	}

	public boolean isCrafting() {
		return getProgress() > 0;
	}

	public int getProgress() {
		return propertyDelegate.get(0);
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
				this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 109 + i * 18));
			}
		}
	}

	private void addPlayerHotbar(PlayerInventory playerInventory) {
		for (int i = 0; i < 9; ++i) {
			this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 167));
		}
	}
}
