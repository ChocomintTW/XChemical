package net.chocomint.xchemical.rei.chemin;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.chocomint.xchemical.XChemical;
import net.chocomint.xchemical.block.ModBlocks;
import net.chocomint.xchemical.item.custom.ElementItem;
import net.chocomint.xchemical.rei.ModREICategoryIdentifiers;
import net.chocomint.xchemical.util.DrawUtil;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.collection.DefaultedList;

import java.util.ArrayList;
import java.util.List;

public class CheMinREICategory implements DisplayCategory<CheMinREIDisplay> {
	private static final Identifier TEXTURE = new Identifier(XChemical.MOD_ID, "textures/gui/rei/chemin_rei_gui.png");

	private final EntryStack<ItemStack> icon = EntryStacks.of(ModBlocks.CHEMIN);

	@Override
	public CategoryIdentifier<? extends CheMinREIDisplay> getCategoryIdentifier() {
		return ModREICategoryIdentifiers.CHEMIN;
	}

	@Override
	public Renderer getIcon() {
		return icon;
	}

	@Override
	public Text getTitle() {
		return new TranslatableText("rei.xchemical.chemin");
	}

	@Override
	public List<Widget> setupDisplay(CheMinREIDisplay display, Rectangle bounds) {
		List<Widget> widgets = new ArrayList<>();
		EntryStack<?> ore = display.getInputEntries().get(0).get(0);
		DefaultedList<Pair<ElementItem, Double>> pairList = display.getProbList();

		int x = bounds.getMinX(), y = bounds.getMinY();
		int w = bounds.getWidth(), h = bounds.getHeight();
		widgets.add(Widgets.createRecipeBase(bounds));
		widgets.add(Widgets.createDrawableWidget(((helper, matrices, mouseX, mouseY, delta) ->
				DrawUtil.drawOverlay(helper, matrices, TEXTURE, x, y, 0, 0, w, h))));

		widgets.add(Widgets.createSlot(new Point(x + 13 , y + 24)).entry(ore).disableBackground());

		int cnt = 0;
		for (Pair<ElementItem, Double> p : pairList) {
			Text t = new LiteralText(p.getLeft().getSymbol()).append(" " + p.getRight() * 100 + "%")
					.formatted(p.getLeft().getElementGroup().getFormat());
			if (cnt < 3)
				widgets.add(Widgets.createLabel(new Point(x + 63, y + 16 + 13 * cnt), t));
			// [Label].leftAligned()
			else
				widgets.add(Widgets.createLabel(new Point(x + 111, y + 16 + 13 * (cnt - 3)), t));

			cnt++;
		}

		return widgets;
	}
}
