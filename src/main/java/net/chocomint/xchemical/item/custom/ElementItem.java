package net.chocomint.xchemical.item.custom;

import net.chocomint.xchemical.item.ModItemGroup;
import net.chocomint.xchemical.util.ElementsInfo;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ElementItem extends Item {
	private final int index;
	private final ElementsInfo.Group group;

	public ElementItem(Settings settings, int index, ElementsInfo.Group group) {
		super(settings.group(ModItemGroup.ELEMENT));
		this.index = index;
		this.group = group;
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(this.getElementGroup().getTranslation());
	}

	public int getIndex() {
		return index;
	}

	public ElementsInfo.Group getElementGroup() {
		return group;
	}

	public String getSymbol() {
		return ElementsInfo.ELEMENTS[index - 1];
	}

	@Override
	public String toString() {
		return getSymbol() + " #" + index + " [" + getElementGroup() + "]";
	}
}
