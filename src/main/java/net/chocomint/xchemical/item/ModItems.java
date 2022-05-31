package net.chocomint.xchemical.item;

import net.chocomint.xchemical.XChemical;
import net.chocomint.xchemical.item.custom.ChemistsManualItem;
import net.chocomint.xchemical.item.custom.CompoundItem;
import net.chocomint.xchemical.item.custom.ElementItem;
import net.chocomint.xchemical.item.custom.HHXRFItem;
import net.chocomint.xchemical.util.ElementsInfo;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.chocomint.xchemical.util.ElementsInfo.Group.*;

public class ModItems {
	// https://onlinetexttools.com/convert-text-to-image?input=Ga&background-color=rgba(255%2C%20166%2C%20166%2C%200)&width=100&height=100&text-color=rgb(255%2C%20255%2C%2085)&font-size=80&typeface=sans-serif&typeface-url=&align-horizontal=center&align-vertical=bottom&bold=true&italic=false&padding=&text-shadow=&line-height=&format=png

	// Maybe I can make a book to introduce like "Chemist's Manual"
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
	public static final ElementItem CAESIUM = registerElement("caesium", 55, ALKALI_METAL);
	public static final ElementItem BARIUM = registerElement("barium", 56, ALKALINE_EARTH_METAL);
	public static final ElementItem LANTHANUM = registerElement("lanthanum", 57, LANTHANIDE);
	public static final ElementItem CERIUM = registerElement("cerium", 58, LANTHANIDE);
	public static final ElementItem PRASEODYMIUM = registerElement("praseodymium", 59, LANTHANIDE);
	public static final ElementItem NEODYMIUM = registerElement("neodymium", 60, LANTHANIDE);
	public static final ElementItem PROMETHIUM = registerElement("promethium", 61, LANTHANIDE);
	public static final ElementItem SAMARIUM = registerElement("samarium", 62, LANTHANIDE);
	public static final ElementItem EUROPIUM = registerElement("europium", 63, LANTHANIDE);
	public static final ElementItem GADOLINIUM = registerElement("gadolinium", 64, LANTHANIDE);
	public static final ElementItem TERBIUM = registerElement("terbium", 65, LANTHANIDE);
	public static final ElementItem DYSPROSIUM = registerElement("dysprosium", 66, LANTHANIDE);
	public static final ElementItem HOLMIUM = registerElement("holmium", 67, LANTHANIDE);
	public static final ElementItem ERBIUM = registerElement("erbium", 68, LANTHANIDE);
	public static final ElementItem THULIUM = registerElement("thulium", 69, LANTHANIDE);
	public static final ElementItem YTTERBIUM = registerElement("ytterbium", 70, LANTHANIDE);
	public static final ElementItem LUTETIUM = registerElement("lutetium", 71, LANTHANIDE);
	public static final ElementItem HAFNIUM = registerElement("hafnium", 72, TRANSITION_METAL);
	public static final ElementItem TANTALUM = registerElement("tantalum", 73, TRANSITION_METAL);
	public static final ElementItem TUNGSTEN = registerElement("tungsten", 74, TRANSITION_METAL);
	public static final ElementItem RHENIUM = registerElement("rhenium", 75, TRANSITION_METAL);
	public static final ElementItem OSMIUM = registerElement("osmium", 76, TRANSITION_METAL);
	public static final ElementItem IRIDIUM = registerElement("iridium", 77, TRANSITION_METAL);
	public static final ElementItem PLATINUM = registerElement("platinum", 78, TRANSITION_METAL);
	public static final ElementItem GOLD = registerElement("gold", 79, TRANSITION_METAL);
	public static final ElementItem MERCURY = registerElement("mercury", 80, TRANSITION_METAL);
	public static final ElementItem THALLIUM = registerElement("thallium", 81, POST_TRANSITION_METAL);
	public static final ElementItem LEAD = registerElement("leaf", 82, POST_TRANSITION_METAL);
	public static final ElementItem BISMUTH = registerElement("bismuth", 83, POST_TRANSITION_METAL);
	public static final ElementItem POLONIUM = registerElement("polonium", 84, METALLOID);
	public static final ElementItem ASTATINE = registerElement("astatine", 85, HALOGEN);
	public static final ElementItem RADON = registerElement("radon", 86, NOBLE_GAS);
	public static final ElementItem FRANCIUM = registerElement("francium", 87, ALKALI_METAL);
	public static final ElementItem RADIUM = registerElement("radium", 88, ALKALINE_EARTH_METAL);
	public static final ElementItem ACTINIUM = registerElement("actinium", 89, ACTINIDE);
	public static final ElementItem THORIUM = registerElement("thorium", 90, ACTINIDE);
	public static final ElementItem PROTACTINIUM = registerElement("protactinium", 91, ACTINIDE);
	public static final ElementItem URANIUM = registerElement("uranium", 92, ACTINIDE);
	public static final ElementItem NEPTUNIUM = registerElement("neptunium", 93, ACTINIDE);
	public static final ElementItem PLUTONIUM = registerElement("plutonium", 94, ACTINIDE);
	public static final ElementItem AMERICIUM = registerElement("americium", 95, ACTINIDE);
	public static final ElementItem CURIUM = registerElement("curium", 96, ACTINIDE);
	public static final ElementItem BERKELIUM = registerElement("berkelium", 97, ACTINIDE);
	public static final ElementItem CALIFORNIUM = registerElement("californium", 98, ACTINIDE);
	public static final ElementItem EINSTEINIUM = registerElement("einsteinium", 99, ACTINIDE);
	public static final ElementItem FERMIUM = registerElement("fermium", 100, ACTINIDE);
	public static final ElementItem MENDELE = registerElement("mendele", 101, ACTINIDE);
	public static final ElementItem NOBELIU = registerElement("nobeliu", 102, ACTINIDE);
	public static final ElementItem LAWRENCIUM = registerElement("lawrencium", 103, ACTINIDE);
	public static final ElementItem RUTHERFORDIUM = registerElement("rutherfordium", 104, TRANSITION_METAL);
	public static final ElementItem DUBNIUM = registerElement("dubnium", 105, TRANSITION_METAL);
	public static final ElementItem SEABORGIUM = registerElement("seaborgium", 106, TRANSITION_METAL);
	public static final ElementItem BOHRIUM = registerElement("bohrium", 107, TRANSITION_METAL);
	public static final ElementItem HASSIUM = registerElement("hassium", 108, TRANSITION_METAL);
	public static final ElementItem MEITNERIUM = registerElement("meitnerium", 109, UNKNOWN);
	public static final ElementItem DARMSTADTIUM = registerElement("darmstadtium", 110, UNKNOWN);
	public static final ElementItem ROENTGENIUM = registerElement("roentgenium", 111, UNKNOWN);
	public static final ElementItem COPERNICIUM = registerElement("copernicium", 112, TRANSITION_METAL);
	public static final ElementItem NIHONIUM = registerElement("nihonium", 113, UNKNOWN);
	public static final ElementItem FLEROVIUM = registerElement("flerovium", 114, UNKNOWN);
	public static final ElementItem MOSCOVIUM = registerElement("moscovium", 115, UNKNOWN);
	public static final ElementItem LIVERMORIUM = registerElement("livermorium", 116, UNKNOWN);
	public static final ElementItem TENNESSINE = registerElement("tennessine", 117, UNKNOWN);
	public static final ElementItem OGANESSON = registerElement("oganesson", 118, UNKNOWN);

	public static final Item COMPOUND = registerItem("compound", new CompoundItem(new FabricItemSettings().group(ModItemGroup.XCHEMICAL)));
	public static final Item HHXRF = registerItem("hhxrf", new HHXRFItem(new FabricItemSettings().group(ModItemGroup.XCHEMICAL)));
//	public static final Item CHEMISTS_MANUAL = registerItem("chemists_manual", new ChemistsManualItem(new FabricItemSettings().group(ModItemGroup.XCHEMICAL)));


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
