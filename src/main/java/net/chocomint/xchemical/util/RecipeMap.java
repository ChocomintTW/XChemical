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

public class RecipeMap {
	public static Map<Item, List<CompoundUnit>> MAP = new HashMap<>();

	public static void init() {
		// Tag
		generateFromTag(ModTags.Blocks.SiO2, Compounds.SiO2(2));
		generateFromTag(ModTags.Blocks.WOODEN, Compounds.CHON(2, 5, 1, 1));

		// Single Resister
		MAP.put(Items.COAL, List.of(new CompoundUnit(ModItems.CARBON, 8)));
		MAP.put(Items.CHARCOAL, List.of(new CompoundUnit(ModItems.CARBON, 8)));

		XChemical.LOGGER.info("Initialize Recipe Map");
	}

	public static void generateFromTag(TagKey<Block> tag, List<CompoundUnit> list) {
		Optional<RegistryEntryList.Named<Block>> entryList = Registry.BLOCK.getEntryList(tag);
		if (entryList.isPresent()) {
			for (RegistryEntry<Block> b : entryList.get())
				MAP.put(b.value().asItem(), list);
		}
	}

	public static class Compounds {
		public static List<CompoundUnit> SiO2(int n) {
			return List.of(new CompoundUnit(ModItems.SILICON, n), new CompoundUnit(ModItems.OXYGEN, n * 2));
		}

		public static List<CompoundUnit> CHON(int c, int h, int o, int n) {
			return List.of(
					new CompoundUnit(ModItems.CARBON, c), new CompoundUnit(ModItems.HYDROGEN, h),
					new CompoundUnit(ModItems.OXYGEN, o), new CompoundUnit(ModItems.NITROGEN, n)
			);
		}
	}
}
