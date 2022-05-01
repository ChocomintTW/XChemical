package net.chocomint.xchemical.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class AnalyzerRecipe implements Recipe<SimpleInventory> {

	private final Identifier id;
//	private final ItemStack output;
//	private final DefaultedList<Ingredient> recipeItems;
	private final ItemStack ingredient;
	private final List<ItemStack> result;


	public AnalyzerRecipe(Identifier id, ItemStack ingredient, List<ItemStack> result) {
		this.id = id;
		this.ingredient = ingredient;
		this.result = result;
	}

	@Override
	public boolean matches(SimpleInventory inventory, World world) {
		return inventory.getStack(0) == ingredient;
	}

	@Override
	@Deprecated
	public ItemStack craft(SimpleInventory inventory) {
		return null;
	}

	public List<ItemStack> generateResults(SimpleInventory inventory) {
		return result;
	}

	@Override
	public boolean fits(int width, int height) {
		return true;
	}

	@Override
	@Deprecated
	public ItemStack getOutput() {
		return ItemStack.EMPTY;
	}

	public List<ItemStack> getResults(SimpleInventory inventory) {
		return new ArrayList<>(result);
	}

	@Override
	public Identifier getId() {
		return id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}

	public static class Type implements RecipeType<AnalyzerRecipe> {
		private Type() { }
		public static final Type INSTANCE = new Type();
		public static final String ID = "analyzer";
	}

	public static class Serializer implements RecipeSerializer<AnalyzerRecipe> {
		public static final Serializer INSTANCE = new Serializer();
		public static final String ID = "analyzer";

		@Override
		public AnalyzerRecipe read(Identifier id, JsonObject json) {
			ItemStack ingredient = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "ingredient"));

			List<ItemStack> result = new ArrayList<>();

//			ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result"));
//			int lava = JsonHelper.getInt(json, "lava");
//
//			JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
//			DefaultedList<Ingredient> inputs = DefaultedList.ofSize(ingredients.size(), Ingredient.EMPTY);
//
//			for (int i = 0; i < inputs.size(); i++) {
//				inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
//			}

			return new AnalyzerRecipe(id, ingredient, result);
		}

		@Override
		public AnalyzerRecipe read(Identifier id, PacketByteBuf buf) {
			ItemStack ingredient = null;

			List<ItemStack> result = new ArrayList<>();

//			ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result"));
//			int lava = JsonHelper.getInt(json, "lava");
//
//			JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
//			DefaultedList<Ingredient> inputs = DefaultedList.ofSize(ingredients.size(), Ingredient.EMPTY);
//
//			for (int i = 0; i < inputs.size(); i++) {
//				inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
//			}

			return new AnalyzerRecipe(id, ingredient, result);
		}

		@Override
		public void write(PacketByteBuf buf, AnalyzerRecipe recipe) {
			buf.writeInt(recipe.getIngredients().size());
			for (Ingredient ing : recipe.getIngredients()) {
				ing.write(buf);
			}
			buf.writeItemStack(recipe.getOutput());
		}
	}
}
