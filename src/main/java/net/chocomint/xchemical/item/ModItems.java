package net.chocomint.xchemical.item;

import net.chocomint.xchemical.XChemical;
import net.chocomint.xchemical.item.custom.ElementItem;
import net.chocomint.xchemical.util.ElementsInfo;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.chocomint.xchemical.util.ElementsInfo.Group.*;

public class ModItems {
	// https://onlinetexttools.com/convert-text-to-image?input=Ga&background-color=rgba(255%2C%20166%2C%20166%2C%200)&width=100&height=100&text-color=rgb(255%2C%20255%2C%2085)&font-size=80&typeface=sans-serif&typeface-url=&align-horizontal=center&align-vertical=bottom&bold=true&italic=false&padding=&text-shadow=&line-height=&format=png

	public static final Item HYDROGEN = registerElement("hydrogen", 1, NON_METAL);
	public static final Item HELIUM = registerElement("helium", 2, NOBLE_GAS);
	public static final Item LITHIUM = registerElement("lithium", 3, ALKALI_METAL);
	public static final Item BERYLLIUM = registerElement("beryllium", 4, ALKALINE_EARTH_METAL);
	public static final Item BORON = registerElement("boron", 5, METALLOID);
	public static final Item CARBON = registerElement("carbon", 6, NON_METAL);
	public static final Item NITROGEN = registerElement("nitrogen", 7, NON_METAL);
	public static final Item OXYGEN = registerElement("oxygen", 8, NON_METAL);
	public static final Item FLUORINE = registerElement("fluorine", 9, HALOGEN);
	public static final Item NEON = registerElement("neon", 10, NOBLE_GAS);
	public static final Item SODIUM = registerElement("sodium", 11, ALKALI_METAL);
	public static final Item MAGNESIUM = registerElement("magnesium", 12, ALKALINE_EARTH_METAL);
	public static final Item ALUMINIUM = registerElement("aluminium", 13, POST_TRANSITION_METAL);
	public static final Item SILICON = registerElement("silicon", 14, METALLOID);
	public static final Item PHOSPHOROUS = registerElement("phosphorous", 15, NON_METAL);
	public static final Item SULFUR = registerElement("sulfur", 16, NON_METAL);
	public static final Item CHLORINE = registerElement("chlorine", 17, HALOGEN);
	public static final Item ARGON = registerElement("argon", 18, NOBLE_GAS);
	public static final Item POTASSIUM = registerElement("potassium", 19, ALKALI_METAL);
	public static final Item CALCIUM = registerElement("calcium", 20, ALKALINE_EARTH_METAL);
	public static final Item SCANDIUM = registerElement("scandium", 21, TRANSITION_METAL);
	public static final Item TITANIUM = registerElement("titanium", 22, TRANSITION_METAL);
	public static final Item VANADIUM = registerElement("vanadium", 23, TRANSITION_METAL);
	public static final Item CHROMIUM = registerElement("chromium", 24, TRANSITION_METAL);
	public static final Item MANGANESE = registerElement("manganese", 25, TRANSITION_METAL);
	public static final Item IRON = registerElement("iron", 26, TRANSITION_METAL);
	public static final Item COBALT = registerElement("cobalt", 27, TRANSITION_METAL);
	public static final Item NICKEL = registerElement("nickel", 28, TRANSITION_METAL);
	public static final Item COPPER = registerElement("copper", 29, TRANSITION_METAL);
	public static final Item ZINC = registerElement("zinc", 30, TRANSITION_METAL);
	public static final Item GALLIUM = registerElement("gallium", 31, POST_TRANSITION_METAL);
	public static final Item GERMANIUM = registerElement("germanium", 32, METALLOID);
	public static final Item ARSENIC = registerElement("arsenic", 33, METALLOID);
	public static final Item SELENIUM = registerElement("selenium", 34, NON_METAL);
	public static final Item BROMINE = registerElement("bromine", 35, HALOGEN);
	public static final Item KRYPTON = registerElement("krypton", 36, NOBLE_GAS);
	public static final Item RUBIDIUM = registerElement("rubidium", 37, ALKALI_METAL);
	public static final Item STRONTIUM = registerElement("strontium", 38, ALKALINE_EARTH_METAL);
	public static final Item YTTRIUM = registerElement("yttrium", 39, TRANSITION_METAL);
	public static final Item ZIRCONIUM = registerElement("zirconium", 40, TRANSITION_METAL);
	public static final Item NIOBIUM = registerElement("niobium", 41, TRANSITION_METAL);
	public static final Item MOLYBDENUM = registerElement("molybdenum", 42, TRANSITION_METAL);
	public static final Item TECHNETIUM = registerElement("technetium", 43, TRANSITION_METAL);
	public static final Item RUTHENIUM = registerElement("ruthenium", 44, TRANSITION_METAL);
	public static final Item RHODIUM = registerElement("rhodium", 45, TRANSITION_METAL);
	public static final Item PALLADIUM = registerElement("palladium", 46, TRANSITION_METAL);
	public static final Item SILVER = registerElement("silver", 47, TRANSITION_METAL);
	public static final Item CADMIUM = registerElement("cadmium", 48, TRANSITION_METAL);
	public static final Item INDIUM = registerElement("indium", 49, POST_TRANSITION_METAL);
	public static final Item TIN = registerElement("tin", 50, POST_TRANSITION_METAL);
	public static final Item ANTIMONY = registerElement("antimony", 51, METALLOID);
	public static final Item TELLURIUM = registerElement("tellurium", 52, METALLOID);
	public static final Item IODINE = registerElement("iodine", 53, HALOGEN);
	public static final Item XENON = registerElement("xenon", 54, NOBLE_GAS);


	public static Item registerElement(String name, int index, ElementsInfo.Group group) {
		return registerItem(name, new ElementItem(new FabricItemSettings(), index, group));
	}

	public static Item registerItem(String name, Item item) {
		return Registry.register(Registry.ITEM, new Identifier(XChemical.MOD_ID, name), item);
	}

	public static void registerModItems() {
		XChemical.LOGGER.info("Registering Mod Items for " + XChemical.MOD_ID);
	}
}
