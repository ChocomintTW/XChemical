package net.chocomint.xchemical.block.entity;

import net.chocomint.xchemical.item.ModItems;
import net.chocomint.xchemical.item.custom.CompoundItem;
import net.chocomint.xchemical.item.custom.ElementItem;
import net.chocomint.xchemical.item.inventory.ImplementedInventory;
import net.chocomint.xchemical.screen.CompoundMixerScreen;
import net.chocomint.xchemical.screen.CompoundMixerScreenHandler;
import net.chocomint.xchemical.util.ElementsInfo;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
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

import java.util.concurrent.atomic.AtomicBoolean;

public class CompoundMixerBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);

	private final PropertyDelegate propertyDelegate;
	private int progress = 0;
	private int maxProgress = 106;
	private boolean startProgress = false;

	public CompoundMixerBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.COMPOUND_MIXER_BLOCK_ENTITY, pos, state);
		this.propertyDelegate = new PropertyDelegate() {
			@Override
			public int get(int index) {
				return switch (index) {
					case 0 -> CompoundMixerBlockEntity.this.progress;
					case 1 -> CompoundMixerBlockEntity.this.maxProgress;
					default -> 0;
				};
			}

			@Override
			public void set(int index, int value) {
				switch (index) {
					case 0 -> CompoundMixerBlockEntity.this.progress = value;
					case 1 -> CompoundMixerBlockEntity.this.maxProgress = value;
				}
			}

			@Override
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
		return new TranslatableText("block.xchemical.compound_mixer");
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
		return new CompoundMixerScreenHandler(syncId, inv, this, this.propertyDelegate);
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

	public static void tick(World world, BlockPos pos, BlockState state, CompoundMixerBlockEntity entity) {
		if (!world.isClient())
		{
			ServerPlayNetworking.registerGlobalReceiver(CompoundMixerScreen.COMPOUND_MIXER_RECEIVER,
					(server, player, handler1, buf, responseSender) -> entity.startProgress = true);

			if (!entity.allEmpty() && entity.startProgress) {
				entity.progress++;
				if (entity.progress > entity.maxProgress) {
					craftItem(entity);
					entity.startProgress = false;
				}
			} else {
				entity.resetProgress();
				entity.startProgress = false;
			}
		}
	}

	private static void craftItem(CompoundMixerBlockEntity entity) {
		ItemStack result = new ItemStack(ModItems.COMPOUND, 1);
		for (int i = 0; i < entity.size() - 1; i++) {
			ItemStack stack = entity.getStack(i);
			if (stack.getItem() instanceof ElementItem) {
				CompoundItem.putCompoundUnit(result, new ElementsInfo.CompoundUnit((ElementItem) stack.getItem(), stack.getCount()));
			}
			entity.removeStack(i);
		}

		entity.setStack(8, result);

		entity.resetProgress();
	}

	public boolean allEmpty() {
		for (int i = 0; i < 8; i++) {
			if (!this.getStack(0).isEmpty())
				return false;
		}
		return true;
	}

	private void resetProgress() {
		this.progress = 0;
	}

	public static String worldClass(World world) {
		return world.isClient() ? "Client" : "Server";
	}
}
