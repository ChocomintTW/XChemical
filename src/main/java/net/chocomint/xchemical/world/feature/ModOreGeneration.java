package net.chocomint.xchemical.world.feature;

import net.chocomint.xchemical.block.ModBlocks;
import net.chocomint.xchemical.world.feature.ore.ModOreFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ModOreGeneration {

	public static void generateOres() {
		generator("rare_earth_ore", new OreGenerationReference(
				getOverworldList(ModBlocks.RARE_EARTH_ORE),
				BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				4, 3, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-60), YOffset.aboveBottom(20))
		));
	}

	public record OreGenerationReference(List<Pair<Block, RuleTest>> BRP_list, Predicate<BiomeSelectionContext> biomeSelection,
	                                     GenerationStep.Feature step, int veinSize, int veinsPerChunk,
	                                     HeightRangePlacementModifier modifier) {}

	public static void generator(String name, OreGenerationReference ref) {
		List<OreFeatureConfig.Target> list = new ArrayList<>();
		for (Pair<Block, RuleTest> p : ref.BRP_list)
			list.add(OreFeatureConfig.createTarget(p.getRight(), p.getLeft().getDefaultState()));

		RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE =
				ConfiguredFeatures.register(name, Feature.ORE, new OreFeatureConfig(list, ref.veinSize));

		RegistryEntry<PlacedFeature> ORE_PLACED = PlacedFeatures.register(name + "_placed", ORE,
				ModOreFeatures.modifiersWithCount(ref.veinsPerChunk, ref.modifier));

		BiomeModifications.addFeature(ref.biomeSelection, ref.step, ORE_PLACED.getKey().get());
	}

	// utilities
	private static List<Pair<Block, RuleTest>> getOverworldList(Block block) {
		return List.of(new Pair<>(block, OreConfiguredFeatures.STONE_ORE_REPLACEABLES),
				new Pair<>(block, OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES));
	}
	private static final HeightRangePlacementModifier huAll = HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.TOP);
}
