package net.chocomint.xchemical.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.chocomint.xchemical.block.ModBlocks;
import net.chocomint.xchemical.item.ModItems;
import net.chocomint.xchemical.recipe.CheMinRecipe;
import net.chocomint.xchemical.recipe.ConstructorRecipe;
import net.chocomint.xchemical.recipe.ModRecipes;
import net.chocomint.xchemical.rei.chemin.CheMinREICategory;
import net.chocomint.xchemical.rei.chemin.CheMinREIDisplay;
import net.chocomint.xchemical.rei.constructor.ConstructorREICategory;
import net.chocomint.xchemical.rei.constructor.ConstructorREIDisplay;

public class ModREIPlugin implements REIClientPlugin {
	@Override
	public void registerCategories(CategoryRegistry registry) {
		registry.add(
				new ConstructorREICategory(),
				new CheMinREICategory()
		);

		registry.addWorkstations(ModREICategoryIdentifiers.CONSTRUCTOR, EntryStacks.of(ModBlocks.CONSTRUCTOR));
		registry.addWorkstations(ModREICategoryIdentifiers.CHEMIN, EntryStacks.of(ModBlocks.CHEMIN), EntryStacks.of(ModItems.HHXRF));
	}

	@Override
	public void registerDisplays(DisplayRegistry registry) {
		registry.registerRecipeFiller(ConstructorRecipe.class, ModRecipes.CONSTRUCTOR_RECIPE_TYPE, ConstructorREIDisplay::new);
		registry.registerRecipeFiller(CheMinRecipe.class, ModRecipes.CHEMIN_RECIPE_TYPE, CheMinREIDisplay::new);
	}
}
