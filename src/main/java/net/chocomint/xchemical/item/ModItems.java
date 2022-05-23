package net.chocomint.xchemical.item;

import net.chocomint.xchemical.XChemical;
import net.chocomint.xchemical.item.custom.CompoundItem;
import net.chocomint.xchemical.item.custom.ElementItem;
import net.chocomint.xchemical.util.ElementsInfo;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.chocomint.xchemical.util.ElementsInfo.Group.*;

public class ModItems {
	// https://onlinetexttools.com/convert-text-to-image?input=Ga&background-color=rgba(255%2C%20166%2C%20166%2C%200)&width=100&height=100&text-color=rgb(255%2C%20255%2C%2085)&font-size=80&typeface=sans-serif&typeface-url=&align-horizontal=center&align-vertical=bottom&bold=true&italic=false&padding=&text-shadow=&line-height=&format=png

	// Maybe I can make a book to introduce like "Chemist's Journey"
	// https://www.mcmod.cn/class/19.html

	public static final ElementItem HYDROGEN = registerElement("hydrogen", 1, NON_METAL);
	public static final ElementItem HELIUM = registerElement("helium", 2, NOBLE_GAS);
	public static final ElementItem LITHIUM = registerElement("lithium", 3, ALKALI_METAL);
	public static final ElementItem BERYLLIUM = registerElement("beryllium", 4, ALKALINE_EARTH_METAL);
	public static final ElementItem BORON = registerElement("boron", 5, METALLOID);
	public static final ElementItem CARBON = registerElement("carbon", 6, NON_METAL);
	public static final ElementItem NITROGEN = registerElement("nitrogen", 7, NON_METAL);
	public static final ElementItem OXYGEN = registerElement("oxygen", 8, NON_METAL);
	public static final ElementItem FLUORINE = registerElement("fluorine", 9, HALOGEN);
	public static final ElementItem NEON = registerElement("neon", 10, NOBLE_GAS);
	public static final ElementItem SODIUM = registerElement("sodium", 11, ALKALI_METAL);
	public static final ElementItem MAGNESIUM = registerElement("magnesium", 12, ALKALINE_EARTH_METAL);
	public static final ElementItem ALUMINIUM = registerElement("aluminium", 13, POST_TRANSITION_METAL);
	public static final ElementItem SILICON = registerElement("silicon", 14, METALLOID);
	public static final ElementItem PHOSPHOROUS = registerElement("phosphorous", 15, NON_METAL);
	public static final ElementItem SULFUR = registerElement("sulfur", 16, NON_METAL);
	public static final ElementItem CHLORINE = registerElement("chlorine", 17, HALOGEN);
	public static final ElementItem ARGON = registerElement("argon", 18, NOBLE_GAS);
	public static final ElementItem POTASSIUM = registerElement("potassium", 19, ALKALI_METAL);
	public static final ElementItem CALCIUM = registerElement("calcium", 20, ALKALINE_EARTH_METAL);
	public static final ElementItem SCANDIUM = registerElement("scandium", 21, TRANSITION_METAL);
	public static final ElementItem TITANIUM = registerElement("titanium", 22, TRANSITION_METAL);
	public static final ElementItem VANADIUM = registerElement("vanadium", 23, TRANSITION_METAL);
	public static final ElementItem CHROMIUM = registerElement("chromium", 24, TRANSITION_METAL);
	public static final ElementItem MANGANESE = registerElement("manganese", 25, TRANSITION_METAL);
	public static final ElementItem IRON = registerElement("iron", 26, TRANSITION_METAL);
	public static final ElementItem COBALT = registerElement("cobalt", 27, TRANSITION_METAL);
	public static final ElementItem NICKEL = registerElement("nickel", 28, TRANSITION_METAL);
	public static final ElementItem COPPER = registerElement("copper", 29, TRANSITION_METAL);
	public static final ElementItem ZINC = registerElement("zinc", 30, TRANSITION_METAL);
	public static final ElementItem GALLIUM = registerElement("gallium", 31, POST_TRANSITION_METAL);
	public static final ElementItem GERMANIUM = registerElement("germanium", 32, METALLOID);
	public static final ElementItem ARSENIC = registerElement("arsenic", 33, METALLOID);
	public static final ElementItem SELENIUM = registerElement("selenium", 34, NON_METAL);
	public static final ElementItem BROMINE = registerElement("bromine", 35, HALOGEN);
	public static final ElementItem KRYPTON = registerElement("krypton", 36, NOBLE_GAS);
	public static final ElementItem RUBIDIUM = registerElement("rubidium", 37, ALKALI_METAL);
	public static final ElementItem STRONTIUM = registerElement("strontium", 38, ALKALINE_EARTH_METAL);
	public static final ElementItem YTTRIUM = registerElement("yttrium", 39, TRANSITION_METAL);
	public static final ElementItem ZIRCONIUM = registerElement("zirconium", 40, TRANSITION_METAL);
	public static final ElementItem NIOBIUM = registerElement("niobium", 41, TRANSITION_METAL);
	public static final ElementItem MOLYBDENUM = registerElement("molybdenum", 42, TRANSITION_METAL);
	public static final ElementItem TECHNETIUM = registerElement("technetium", 43, TRANSITION_METAL);
	public static final ElementItem RUTHENIUM = registerElement("ruthenium", 44, TRANSITION_METAL);
	public static final ElementItem RHODIUM = registerElement("rhodium", 45, TRANSITION_METAL);
	public static final ElementItem PALLADIUM = registerElement("palladium", 46, TRANSITION_METAL);
	public static final ElementItem SILVER = registerElement("silver", 47, TRANSITION_METAL);
	public static final ElementItem CADMIUM = registerElement("cadmium", 48, TRANSITION_METAL);
	public static final ElementItem INDIUM = registerElement("indium", 49, POST_TRANSITION_METAL);
	public static final ElementItem TIN = registerElement("tin", 50, POST_TRANSITION_METAL);
	public static final ElementItem ANTIMONY = registerElement("antimony", 51, METALLOID);
	public static final ElementItem TELLURIUM = registerElement("tellurium", 52, METALLOID);
	public static final ElementItem IODINE = registerElement("iodine", 53, HALOGEN);
	public static final ElementItem XENON = registerElement("xenon", 54, NOBLE_GAS);

	public static final Item COMPOUND = registerItem("compound", new CompoundItem(new FabricItemSettings()));


	public static ElementItem registerElement(String name, int index, ElementsInfo.Group group) {
		ElementItem element = registerItem(name, new ElementItem(new FabricItemSettings(), index, group));
		ElementsInfo.SYMBOL_MAP.put(ElementsInfo.ELEMENTS[index - 1], element);
		return element;
	}

	public static <I extends Item> I registerItem(String name, I item) {
		return Registry.register(Registry.ITEM, new Identifier(XChemical.MOD_ID, name), item);
	}

	public static void registerModItems() {
		XChemical.LOGGER.info("Registering Mod Items for " + XChemical.MOD_ID);
	}
}
