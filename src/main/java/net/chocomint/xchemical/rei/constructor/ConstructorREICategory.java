package net.chocomint.xchemical.rei.constructor;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.chocomint.xchemical.XChemical;
import net.chocomint.xchemical.block.ModBlocks;
import net.chocomint.xchemical.item.ModItems;
import net.chocomint.xchemical.item.custom.CompoundItem;
import net.chocomint.xchemical.rei.ModREICategoryIdentifiers;
import net.chocomint.xchemical.util.DrawUtil;
import net.chocomint.xchemical.util.ElementsInfo;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ConstructorREICategory implements DisplayCategory<ConstructorREIDisplay> {
	private static final Identifier TEXTURE = new Identifier(XChemical.MOD_ID, "textures/gui/rei/constructor_rei_gui.png");

	private final EntryStack<ItemStack> icon = EntryStacks.of(ModBlocks.CONSTRUCTOR);

	@Override
	public CategoryIdentifier<? extends ConstructorREIDisplay> getCategoryIdentifier() {
		return ModREICategoryIdentifiers.CONSTRUCTOR;
	}

	@Override
	public Renderer getIcon() {
		return icon;
	}

	@Override
	public Text getTitle() {
		return new TranslatableText("rei.xchemical.constructor");
	}

	@Override
	public @NotNull List<Widget> setupDisplay(ConstructorREIDisplay display, Rectangle bounds) {
		List<Widget> widgets = new ArrayList<>();
		List<ElementsInfo.CompoundUnit> units = display.getCompoundList();
		EntryStack<?> output = display.getOutputEntries().get(0).get(0);

		ItemStack stack = new ItemStack(ModItems.COMPOUND);
		units.forEach(u -> CompoundItem.putCompoundUnit(stack, u));

		int x = bounds.getMinX(), y = bounds.getMinY();
		int w = bounds.getWidth(), h = bounds.getHeight();
		widgets.add(Widgets.createRecipeBase(bounds));
		widgets.add(Widgets.createDrawableWidget(((helper, matrices, mouseX, mouseY, delta) ->
				DrawUtil.drawOverlay(helper, matrices, TEXTURE, x, y, 0, 0, w, h))));

		widgets.add(Widgets.createSlot(new Point(x + 31 , y + 24)).entry(EntryStacks.of(stack)).disableBackground());
		widgets.add(Widgets.createSlot(new Point(x + 103 , y + 24)).entry(output).disableBackground());

		return  widgets;
	}
}
