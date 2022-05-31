package net.chocomint.xchemical.block.entity;

import net.chocomint.xchemical.item.inventory.ImplementedInventory;
import net.chocomint.xchemical.screen.AnalyzerScreenHandler;
import net.chocomint.xchemical.util.ElementsInfo;
import net.chocomint.xchemical.util.CompoundMap;
import net.chocomint.xchemical.util.Utilities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AnalyzerBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
	public static final int RESULT_SIZE = 8;
	public static boolean CAN_INSERT = true;

	private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);

	public AnalyzerBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ANALYZER_BLOCK_ENTITY, pos, state);
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return inventory;
	}

	@Override
	public Text getDisplayName() {
		return new TranslatableText("block.xchemical.analyzer");
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
		return new AnalyzerScreenHandler(syncId, inv, this);
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

	public static void tick(World world, BlockPos pos, BlockState state, AnalyzerBlockEntity entity) {
		if (!world.isClient()) {

			if (CompoundMap.MAP.isEmpty())
				CompoundMap.init();

			// Vars
			ItemStack ingredient = entity.getStack(0);
			List<ElementsInfo.CompoundUnit> list = CompoundMap.MAP.get(ingredient.getItem());

			// check
			List<ItemStack> result = new ArrayList<>();
			for (int i = 1; i <= 8; i++) {
				ItemStack s = entity.getStack(i);
				if (!s.isEmpty()) result.add(s);
			}

			CAN_INSERT = Utilities.canInsert(result, Utilities.toStackList(list), RESULT_SIZE);

			if (list != null && CAN_INSERT && !ingredient.isEmpty()) {

				List<ItemStack> mix = Utilities.mixLists(result, Utilities.toStackList(list));
				int size = mix.size();
				for (int i = 0; i < size; i++) {
					ItemStack stack = mix.get(i);
					if (stack.getCount() > 64) {
						mix.add(new ItemStack(stack.getItem(), 64));
						mix.set(i, new ItemStack(stack.getItem(), stack.getCount() - 64));
					}
				}
				int cnt = 1;
				for (ItemStack i : mix) {
					entity.setStack(cnt, i);
					cnt++;
				}

				entity.getStack(0).decrement(1);
			}
		}
	}
}
