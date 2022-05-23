package net.chocomint.xchemical.item.custom;

import net.chocomint.xchemical.item.ModItemGroup;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.chocomint.xchemical.util.ElementsInfo.CompoundUnit;
import static net.chocomint.xchemical.util.ElementsInfo.SYMBOL_MAP;

public class CompoundItem extends Item {
	public CompoundItem(Settings settings) {
		super(settings.group(ModItemGroup.XCHEMICAL));
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		List<CompoundUnit> list = getElementList(stack);
		if (!list.isEmpty())
		{
			LiteralText t = new LiteralText("");
			for (CompoundUnit unit : list) {
				t.append(unit.toText()).append(" ");
			}
			tooltip.add(t);
		}
	}

	public static List<CompoundUnit> getElementList(ItemStack stack) {
		NbtList nbtList = (NbtList) stack.getOrCreateNbt().get("Elements");
		if (nbtList != null)
		{
			List<CompoundUnit> list = new ArrayList<>();
			for (NbtElement element : nbtList) {
				String symbol = ((NbtCompound) element).getString("symbol");
				int amount = ((NbtCompound) element).getInt("amount");
				list.add(new CompoundUnit(SYMBOL_MAP.get(symbol), amount));
			}
			return list;
		}
		return new ArrayList<>();
	}

	public static void putCompoundUnit(ItemStack stack, CompoundUnit unit) {
		if (!stack.getOrCreateNbt().contains("Elements", 9)) {
			stack.getOrCreateNbt().put("Elements", new NbtList());
		}

		NbtList nbtList = stack.getOrCreateNbt().getList("Elements", 10);
		nbtList.add(unit.toNbt());
	}
}
