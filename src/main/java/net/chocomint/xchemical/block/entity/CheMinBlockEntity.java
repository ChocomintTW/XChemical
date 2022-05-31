package net.chocomint.xchemical.block.entity;

import net.chocomint.xchemical.item.custom.ElementItem;
import net.chocomint.xchemical.item.inventory.ImplementedInventory;
import net.chocomint.xchemical.recipe.CheMinRecipe;
import net.chocomint.xchemical.screen.CheMinScreenHandler;
import net.chocomint.xchemical.util.Utilities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Pair;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CheMinBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(8, ItemStack.EMPTY);

	private final PropertyDelegate propertyDelegate;
	private int progress = 0;
	private int maxProgress = 35 * 10;
	private int fuelTime = 0;
	private int burnTime = 0;

	public CheMinBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.CHEMIN_BLOCK_ENTITY, pos, state);
		this.propertyDelegate = new PropertyDelegate() {
			@Override
			public int get(int index) {
				return switch (index) {
					case 0 -> CheMinBlockEntity.this.progress;
					case 1 -> CheMinBlockEntity.this.maxProgress;
					case 2 -> CheMinBlockEntity.this.fuelTime;
					case 3 -> CheMinBlockEntity.this.burnTime;
					default -> 0;
				};
			}

			@Override
			public void set(int index, int value) {
				switch (index) {
					case 0 -> CheMinBlockEntity.this.progress = value;
					case 1 -> CheMinBlockEntity.this.maxProgress = value;
					case 2 -> CheMinBlockEntity.this.fuelTime = value;
					case 3 -> CheMinBlockEntity.this.burnTime = value;
				};
			}

			@Override
			public int size() {
				return 4;
			}
		};
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	@Override
	public Text getDisplayName() {
		return new TranslatableText("block.xchemical.chemin");
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
		return new CheMinScreenHandler(syncId, inv, this, this.propertyDelegate);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		Inventories.readNbt(nbt, this.inventory);
	}

	@Override
	public void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, this.inventory);
	}

	public static void tick(World world, BlockPos pos, BlockState state, CheMinBlockEntity entity) {
		if (!world.isClient()) {

			if (entity.isBurning())
				entity.burnTime--;
			else {
				if (entity.isProcessing()) {
					int f = AbstractFurnaceBlockEntity.createFuelTimeMap().getOrDefault(entity.getStack(CheMinScreenHandler.FUEL_SLOT).getItem(), 0);
					if (f == 0)
						entity.resetProgress();
					else {
						entity.removeStack(CheMinScreenHandler.FUEL_SLOT, 1);
						entity.burnTime = entity.fuelTime = f;
					}
				}
			}

//			Block b = Block.getBlockFromItem(entity.getStack(CheMinScreenHandler.ORE_SLOT).getItem());
			SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
			for (int i = 0; i < entity.inventory.size(); i++) {
				inventory.setStack(i, entity.getStack(i));
			}
			Optional<CheMinRecipe> match = world.getRecipeManager().getFirstMatch(CheMinRecipe.Type.INSTANCE, inventory, world);

			if (match.isPresent()) {
				entity.progress++;
				if (entity.progress > entity.maxProgress) {

					List<ItemStack> result = new ArrayList<>();
					for (int i = 2; i <= 7; i++) {
						ItemStack s = entity.getStack(i);
						if (!s.isEmpty()) result.add(s);
					}

					List<ItemStack> element_stacks = new ArrayList<>();
					for (int i = 0; i < match.get().times(); i++) {
						for (Pair<ElementItem, Double> pair : match.get().getElements()) {
							if (Utilities.probability(pair.getRight())) {
								element_stacks.add(new ItemStack(pair.getLeft(), 1));
							}
						}
					}

					if (Utilities.canInsert(result, element_stacks, 6)) {
						List<ItemStack> mix = Utilities.mixLists(result, element_stacks);
						for (int i = 0; i < mix.size(); i++)
							entity.setStack(i + 2, mix.get(i));
					}

					entity.removeStack(0, 1);
					entity.resetProgress();
				}
			}
			else entity.resetProgress();
		}
	}

	private boolean isBurning() {
		return this.burnTime > 0;
	}

	private boolean isProcessing() {
		return this.progress > 0;
	}

	private void resetProgress() {
		this.progress = 0;
	}
}
