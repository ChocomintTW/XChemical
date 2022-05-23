package net.chocomint.xchemical.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.Objects;

import static net.chocomint.xchemical.util.ElementsInfo.*;

public record ConstructorRecipe(Identifier id, ItemStack output,
                                DefaultedList<CompoundUnit> units) implements Recipe<SimpleInventory> {
	@Override
	public boolean matches(SimpleInventory inventory, World world) {
		ItemStack compound = inventory.getStack(0);
		if (compound.getNbt() != null) {
			if (!compound.getNbt().isEmpty()) {
				NbtList nbtList = (NbtList) compound.getNbt().get("Elements");
				if (nbtList != null) {
					for (CompoundUnit u : units) {
						if (!nbtList.contains(u.toNbt()))
							return false;
					}
					return nbtList.size() == units.size();
				}
			}
		}
		return false;
	}

	@Override
	public ItemStack craft(SimpleInventory inventory) {
		return output;
	}

	@Override
	public boolean fits(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getOutput() {
		return output.copy();
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

	public static class Type implements RecipeType<ConstructorRecipe> {
		private Type() {
		}

		public static final Type INSTANCE = new Type();
		public static final String ID = "constructor";
	}

	public static class Serializer implements RecipeSerializer<ConstructorRecipe> {
		public static final Serializer INSTANCE = new Serializer();
		public static final String ID = "constructor";
		// this is the name given in the json file

		@Override
		public ConstructorRecipe read(Identifier id, JsonObject json) {
			ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result"));

			JsonArray elements = JsonHelper.getArray(json, "Elements");
			DefaultedList<CompoundUnit> inputs = DefaultedList.ofSize(elements.size());

			for (int i = 0; i < elements.size(); i++) {
				String symbol = elements.get(i).getAsJsonObject().get("symbol").getAsString();
				int amount = elements.get(i).getAsJsonObject().get("amount").getAsInt();
				inputs.add(new CompoundUnit(SYMBOL_MAP.get(symbol), amount));
				System.out.println(inputs);
			}

			return new ConstructorRecipe(id, output, inputs);
		}

		@Override
		public ConstructorRecipe read(Identifier id, PacketByteBuf buf) {
			NbtList nbtList = Objects.requireNonNull(buf.readNbt()).getList("Elements", 9);
			DefaultedList<CompoundUnit> inputs = DefaultedList.ofSize(buf.readInt());
			if (nbtList != null)
			{
				for (int i = 0; i < nbtList.size(); i++) {
					String symbol = ((NbtCompound) nbtList.get(i)).getString("symbol");
					int amount = ((NbtCompound) nbtList.get(i)).getInt("amount");
					inputs.set(i, new CompoundUnit(SYMBOL_MAP.get(symbol), amount));
				}
			}
			ItemStack output = buf.readItemStack();
			return new ConstructorRecipe(id, output, inputs);
		}

		@Override
		public void write(PacketByteBuf buf, ConstructorRecipe recipe) {
			buf.writeInt(recipe.units.size());

			NbtList nbtList = new NbtList();
			for (CompoundUnit unit : recipe.units) {
				nbtList.add(unit.toNbt());
			}
			NbtCompound nbt = new NbtCompound();
			nbt.put("Elements", nbtList);
			buf.writeNbt(nbt);

			buf.writeItemStack(recipe.getOutput());
		}
	}
}
