package net.chocomint.xchemical.block.entity;

import net.chocomint.xchemical.item.inventory.ImplementedInventory;
import net.chocomint.xchemical.recipe.ConstructorRecipe;
import net.chocomint.xchemical.screen.ConstructorScreenHandler;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ConstructorBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
	private final int progressbarLength = 42;
	private final int seconds = 3;

	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

	protected final PropertyDelegate propertyDelegate;
	private int progress = 0;
	private int maxProgress = progressbarLength * seconds;

	public ConstructorBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.CONSTRUCTOR_BLOCK_ENTITY, pos, state);
		this.propertyDelegate = new PropertyDelegate() {
			public int get(int index) {
				return switch (index) {
					case 0 -> ConstructorBlockEntity.this.progress;
					case 1 -> ConstructorBlockEntity.this.maxProgress;
					default -> 0;
				};
			}

			public void set(int index, int value) {
				switch (index) {
					case 0 -> ConstructorBlockEntity.this.progress = value;
					case 1 -> ConstructorBlockEntity.this.maxProgress = value;
				}
			}

			public int size() {
				return 2;
			}
		};
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	@Override
	public Text getDisplayName() {
		return new TranslatableText("block.xchemical.constructor");
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
		return new ConstructorScreenHandler(syncId, inv, this, this.propertyDelegate);
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

	public static void tick(World world, BlockPos pos, BlockState state, ConstructorBlockEntity entity) {
		if (!world.isClient()) {
			if (hasRecipe(entity)) {
				entity.progress++;
				if (entity.progress > entity.maxProgress) {
					craftItem(entity);
				}
			} else {
				entity.resetProgress();
			}
		}
	}

	private static boolean hasRecipe(ConstructorBlockEntity entity) {
		World world = entity.world;
		SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
		for (int i = 0; i < entity.inventory.size(); i++) {
			inventory.setStack(i, entity.getStack(i));
		}

		Optional<ConstructorRecipe> match = world.getRecipeManager()
				.getFirstMatch(ConstructorRecipe.Type.INSTANCE, inventory, world);

		return match.isPresent() && !entity.getStack(0).isEmpty()
				&& canInsertAmountIntoOutputSlot(inventory)
				&& canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
	}

	private static void craftItem(ConstructorBlockEntity entity) {
		System.out.println("craft");
		World world = entity.world;
		SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
		for (int i = 0; i < entity.inventory.size(); i++) {
			inventory.setStack(i, entity.getStack(i));
		}

		Optional<ConstructorRecipe> match = world.getRecipeManager()
				.getFirstMatch(ConstructorRecipe.Type.INSTANCE, inventory, world);

		if(match.isPresent()) {
			// how to get the amount of the item in ingredients?
			entity.removeStack(0, 1);
			entity.setStack(1, new ItemStack(match.get().getOutput().getItem(),
					entity.getStack(1).getCount() + 1));

			entity.resetProgress();
		}
	}

	private void resetProgress() {
		this.progress = 0;
	}

	private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
		return inventory.getStack(1).getItem() == output.getItem() || inventory.getStack(1).isEmpty();
	}

	private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
		return inventory.getStack(1).getMaxCount() > inventory.getStack(1).getCount();
	}
}
