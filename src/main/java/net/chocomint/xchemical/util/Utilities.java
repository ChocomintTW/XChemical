package net.chocomint.xchemical.util;

import net.chocomint.xchemical.block.entity.AnalyzerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utilities {

	public static boolean inHand(PlayerEntity player, Item inHandItem) {
		return player.getMainHandStack().getItem() == inHandItem || player.getOffHandStack().getItem() == inHandItem;
	}

	public static boolean probability(double prob) {
		Random random = new Random();
		return random.nextDouble() <= prob;
	}

	public static boolean range(int val, int low, int high) {
		return val >= low && val <= high;
	}

	public static boolean isListEqual(List<ItemStack> l1, List<ItemStack> l2) {
		if (l1 == null || l2 == null)
			return false;
		if (l1.size() != l2.size())
			return false;
		else {
			for (int i = 0; i < l1.size(); i++) {
				if (!l1.get(i).isItemEqual(l2.get(i)))
					return false;
			}
			return true;
		}
	}

	public static boolean canInsert(List<ItemStack> analyzer_result, List<ItemStack> ingredient_result, int result_size) {
		if (ingredient_result == null || analyzer_result.isEmpty() || ingredient_result.isEmpty() ||
				analyzer_result.size() + ingredient_result.size() <= AnalyzerBlockEntity.RESULT_SIZE)
			return true;
		else {
			List<ItemStack> mix = mixLists(analyzer_result, ingredient_result);
			for (int i = 0; i < mix.size(); i++) {
				ItemStack stack = mix.get(i);
				if (stack.getCount() > 64) {
					mix.add(new ItemStack(stack.getItem(), stack.getCount() - 64));
					mix.set(i, new ItemStack(stack.getItem(), 64));
				}
			}
			return mix.size() <= result_size;
		}
	}

	public static List<ItemStack> mixLists(List<ItemStack> l1, List<ItemStack> l2) {
		// mix list
		List<ItemStack> mix = copyFrom(l1);
		List<ItemStack> copied2 = copyFrom(l2);
		while (!copied2.isEmpty()) {
			boolean hasEqual = false;
			for (int i = 0; i < l1.size(); i++) {
				ItemStack stack = copied2.get(0);

				if (stack.getItem() == l1.get(i).getItem()) {

					stack.increment(l1.get(i).getCount());
					mix.set(i, stack);

					hasEqual = true;
					break;
				}
			}
			if (!hasEqual)
				mix.add(copied2.get(0));

			copied2.remove(0);
		}
		return mix;
	}

	public static List<ItemStack> copyFrom(List<ItemStack> source) {
		List<ItemStack> result = new ArrayList<>();
		for (ItemStack itemStack : source) {
			result.add(new ItemStack(itemStack.getItem(), itemStack.getCount()));
		}
		return result;
	}

	public static List<ItemStack> toStackList(List<ElementsInfo.CompoundUnit> source) {
		if (source == null)
			return null;
		else {
			List<ItemStack> result = new ArrayList<>();
			for (ElementsInfo.CompoundUnit unit : source) {
				result.add(unit.toStack());
			}
			return result;
		}
	}
}
