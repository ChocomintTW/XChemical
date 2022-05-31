package net.chocomint.xchemical.rei.constructor;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import net.chocomint.xchemical.recipe.ConstructorRecipe;
import net.chocomint.xchemical.rei.ModREICategoryIdentifiers;
import net.chocomint.xchemical.rei.ModRecipeDisplay;
import net.chocomint.xchemical.util.ElementsInfo;
import net.minecraft.util.collection.DefaultedList;

public class ConstructorREIDisplay extends ModRecipeDisplay<ConstructorRecipe> {
	public ConstructorREIDisplay(ConstructorRecipe recipe) {
		super(recipe);
	}

	public DefaultedList<ElementsInfo.CompoundUnit> getCompoundList() {
		return recipe.units();
	}

	@Override
	public CategoryIdentifier<?> getCategoryIdentifier() {
		return ModREICategoryIdentifiers.CONSTRUCTOR;
	}
}
