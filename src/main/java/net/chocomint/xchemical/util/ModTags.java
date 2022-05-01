package net.chocomint.xchemical.util;

import net.chocomint.xchemical.XChemical;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModTags {

	public static class Blocks {
		public static final TagKey<Block> SiO2 = createTag(XChemical.MOD_ID, "sio2");
		public static final TagKey<Block> WOODEN = createTag(XChemical.MOD_ID, "wooden");

		private static TagKey<Block> createTag(String namespace, String name) {
			return TagKey.of(Registry.BLOCK_KEY, new Identifier(namespace, name));
		}
	}

	public static class Items {

		private static TagKey<Item> createTag(String name) {
			return TagKey.of(Registry.ITEM_KEY, new Identifier("c", name));
		}
	}

}
