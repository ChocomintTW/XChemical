package net.chocomint.xchemical.recipe;

import net.chocomint.xchemical.XChemical;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {
	public static ConstructorRecipe.Type CONSTRUCTOR_RECIPE_TYPE;

	public static void register() {
		Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(XChemical.MOD_ID, ConstructorRecipe.Serializer.ID),
				ConstructorRecipe.Serializer.INSTANCE);

		CONSTRUCTOR_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE,
				new Identifier(XChemical.MOD_ID, ConstructorRecipe.Type.ID), ConstructorRecipe.Type.INSTANCE);
	}
}
