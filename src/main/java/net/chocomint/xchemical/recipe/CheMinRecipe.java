package net.chocomint.xchemical.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.chocomint.xchemical.item.custom.ElementItem;
import net.chocomint.xchemical.util.ElementsInfo;
import net.chocomint.xchemical.util.NbtUtils;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Pair;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.Objects;

public record CheMinRecipe(Identifier id, int times, ItemStack ore,
                           DefaultedList<Pair<ElementItem, Double>> elements) implements Recipe<SimpleInventory> {
	@Override
	public boolean matches(SimpleInventory inventory, World world) {
		return inventory.getStack(0).getItem() == ore.getItem();
	}

	public ItemStack getOre() {
		return ore.copy();
	}

	public DefaultedList<Pair<ElementItem, Double>> getElements() {
		return elements;
	}

	@Override
	public boolean fits(int width, int height) {
		return true;
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

	public static class Type implements RecipeType<CheMinRecipe> {
		private Type() {}

		public static final Type INSTANCE = new Type();
		public static final String ID = "chemin";
	}

	public static class Serializer implements RecipeSerializer<CheMinRecipe> {
		public static final Serializer INSTANCE = new Serializer();
		public static final String ID = "chemin";

		@Override
		public CheMinRecipe read(Identifier id, JsonObject json) {
			ItemStack ore = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "ore"));
			int times = JsonHelper.getInt(json, "times");

			JsonArray pools = JsonHelper.getArray(json, "pools");
			DefaultedList<Pair<ElementItem, Double>> list = DefaultedList.ofSize(pools.size());
			for (JsonElement e : pools) {
				ElementItem element = ElementsInfo.SYMBOL_MAP.get(e.getAsJsonObject().get("element").getAsString());
				double prob = e.getAsJsonObject().get("prob").getAsDouble();
				list.add(new Pair<>(element, prob));
			}

			return new CheMinRecipe(id, times, ore, list);
		}

		@Override
		public CheMinRecipe read(Identifier id, PacketByteBuf buf) {
			ItemStack ore = buf.readItemStack();
			int times = buf.readInt();
			NbtList nbtList = (NbtList) Objects.requireNonNull(buf.readNbt()).get("pools");
			assert nbtList != null;
			DefaultedList<Pair<ElementItem, Double>> list = DefaultedList.ofSize(nbtList.size());
			for (NbtElement element : nbtList) {
				String symbol = ((NbtCompound) element).getString("element");
				double prob = ((NbtCompound) element).getDouble("prob");
				list.add(new Pair<>(ElementsInfo.SYMBOL_MAP.get(symbol), prob));
			}
			return new CheMinRecipe(id, times, ore, list);
		}

		@Override
		public void write(PacketByteBuf buf, CheMinRecipe recipe) {
			buf.writeItemStack(recipe.getOre());
			buf.writeInt(recipe.times);
			NbtCompound nbt = new NbtCompound();
			NbtList list = new NbtList();
			for (Pair<ElementItem, Double> p : recipe.getElements()) {
				NbtCompound pair_compound = new NbtCompound();
				pair_compound.putString("element", p.getLeft().getSymbol());
				pair_compound.putDouble("prob", p.getRight());
				list.add(pair_compound);
			}
			nbt.put("pools", list);
			buf.writeNbt(nbt);
		}
	}

	@Override @Deprecated public ItemStack craft(SimpleInventory inventory) {
		return ItemStack.EMPTY;
	}

	@Override @Deprecated public ItemStack getOutput() {
		return ItemStack.EMPTY.copy();
	}
}
