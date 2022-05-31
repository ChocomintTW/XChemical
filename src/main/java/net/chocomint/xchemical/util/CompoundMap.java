package net.chocomint.xchemical.util;

import net.chocomint.xchemical.XChemical;
import net.chocomint.xchemical.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static net.chocomint.xchemical.util.ElementsInfo.CompoundUnit;

public class CompoundMap {
	public static Map<Item, List<CompoundUnit>> MAP = new HashMap<>();

	public static void init() {
		// Tag
		fromTag(ModTags.Blocks.SiO2, Compounds.SiO2(2));
		fromTag(ModTags.Blocks.WOODEN, Compounds.CHON(2, 5, 1, 1));

		// Same Item Register
		fromList(List.of(Items.COAL, Items.CHARCOAL), List.of(new CompoundUnit(ModItems.CARBON, 8)));
		fromList(List.of(Items.AMETHYST_SHARD, Items.QUARTZ), Compounds.SiO2(3));

		// Single Register
		MAP.put(Items.IRON_INGOT, List.of(new CompoundUnit(ModItems.IRON, 2)));
		MAP.put(Items.COPPER_INGOT, List.of(new CompoundUnit(ModItems.COPPER, 2)));
		MAP.put(Items.GOLD_INGOT, List.of(new CompoundUnit(ModItems.GOLD, 2)));
		MAP.put(Items.LAPIS_LAZULI, List.of(
				new CompoundUnit(ModItems.SODIUM, 2), new CompoundUnit(ModItems.CALCIUM, 2),
				new CompoundUnit(ModItems.ALUMINIUM, 2), new CompoundUnit(ModItems.SILICON, 2),
				new CompoundUnit(ModItems.OXYGEN, 6), new CompoundUnit(ModItems.SULFUR, 1),
				new CompoundUnit(ModItems.CHLORINE, 1)
		));
		MAP.put(Items.EMERALD, List.of(
				new CompoundUnit(ModItems.BERYLLIUM, 1), new CompoundUnit(ModItems.ALUMINIUM, 1),
				new CompoundUnit(ModItems.SILICON, 2), new CompoundUnit(ModItems.OXYGEN, 6)
		));

		XChemical.LOGGER.info("Initialize Recipe Map");
	}

	public static void fromTag(TagKey<Block> tag, List<CompoundUnit> list) {
		Optional<RegistryEntryList.Named<Block>> entryList = Registry.BLOCK.getEntryList(tag);
		if (entryList.isPresent()) {
			for (RegistryEntry<Block> b : entryList.get())
				MAP.put(b.value().asItem(), list);
		}
	}

	public static void fromList(List<Item> itemList, List<CompoundUnit> list) {
		for (Item i : itemList)
			MAP.put(i, list);
	}

	public static class Compounds {
		public static List<CompoundUnit> SiO2(int n) {
			return List.of(new CompoundUnit(ModItems.SILICON, n), new CompoundUnit(ModItems.OXYGEN, n * 2));
		}

		public static List<CompoundUnit> CHON(int C, int H, int O, int N) {
			return List.of(
					new CompoundUnit(ModItems.CARBON, C), new CompoundUnit(ModItems.HYDROGEN, H),
					new CompoundUnit(ModItems.OXYGEN, O), new CompoundUnit(ModItems.NITROGEN, N)
			);
		}
	}
}
