package net.chocomint.xchemical.screen;

import net.chocomint.xchemical.screen.slot.FuelSlot;
import net.chocomint.xchemical.screen.slot.OreSlot;
import net.chocomint.xchemical.screen.slot.ResultSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CheMinScreenHandler extends ScreenHandler {
	public static final int ORE_SLOT = 0;
	public static final int FUEL_SLOT = 1;

	private final Inventory inventory;
	private final PropertyDelegate propertyDelegate;

	public CheMinScreenHandler(int syncId, PlayerInventory playerInventory) {
		this(syncId, playerInventory, new SimpleInventory(8), new ArrayPropertyDelegate(4));
	}

	public CheMinScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate delegate) {
		super(ModScreenHandlers.CHEMIN_SCREEN_HANDLER, syncId);
		checkSize(inventory, 8);
		this.inventory = inventory;
		this.propertyDelegate = delegate;
		inventory.onOpen(playerInventory.player);

		this.addSlot(new OreSlot(inventory, 0, 26, 39)); // ore
		this.addSlot(new FuelSlot(inventory, 1, 60, 22)); // fuel
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++)
				this.addSlot(new ResultSlot(inventory, 2 + i * 3 + j, 98 + 18 * j, 30 + 18 * i));
		}

		addPlayerInventory(playerInventory);
		addPlayerHotbar(playerInventory);

		addProperties(delegate);
	}

	public int getLength() {
		return (int) Math.round((double)propertyDelegate.get(0) / 10);
	}

	public int getBurnHeight() {
		return (int) Math.round((double)propertyDelegate.get(3) / propertyDelegate.get(2) * 14);
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
