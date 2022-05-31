package net.chocomint.xchemical.rei.chemin;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.chocomint.xchemical.item.custom.ElementItem;
import net.chocomint.xchemical.recipe.CheMinRecipe;
import net.chocomint.xchemical.rei.ModREICategoryIdentifiers;
import net.minecraft.util.Pair;
import net.minecraft.util.collection.DefaultedList;

import java.util.Collections;
import java.util.List;

public class CheMinREIDisplay implements Display {
	private final CheMinRecipe recipe;

	public CheMinREIDisplay(CheMinRecipe recipe) {
		this.recipe = recipe;
	}


	@Override
	public List<EntryIngredient> getInputEntries() {
		return Collections.singletonList(EntryIngredients.of(recipe.getOre()));
	}

	public DefaultedList<Pair<ElementItem, Double>> getProbList() {
		return recipe.getElements();
	}

	@Override
	public List<EntryIngredient> getOutputEntries() {
		return List.of(EntryIngredient.empty());
	}

	@Override
	public CategoryIdentifier<?> getCategoryIdentifier() {
		return ModREICategoryIdentifiers.CHEMIN;
	}
}
