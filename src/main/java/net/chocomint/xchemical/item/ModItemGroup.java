package net.chocomint.xchemical.item;

import net.chocomint.xchemical.XChemical;
import net.chocomint.xchemical.block.ModBlocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
	public static final ItemGroup ELEMENT = FabricItemGroupBuilder.build(new Identifier(XChemical.MOD_ID, "element"),
			() -> new ItemStack(ModItems.HYDROGEN));
	public static final ItemGroup MACHINE = FabricItemGroupBuilder.build(new Identifier(XChemical.MOD_ID, "machine"),
			() -> new ItemStack(ModBlocks.ANALYZER));
}
