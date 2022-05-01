package net.chocomint.xchemical.util;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import static net.minecraft.util.Formatting.*;

public class ElementsInfo {

	public static final String[] ELEMENTS = {
			"H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne",
			"Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca",
			"Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn",
			"Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr",
			"Nb", "Mo", "Tc", "Rh", "Rh", "Pd", "Ag", "Cd", "In", "Sn",
			"Sb", "Te", "I", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd",
			"Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb",
			"Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg",
			"Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Ac", "Th",
			"Pa", "U"
	};

	public enum Group {
		ALKALI_METAL("alkali_metal", BLUE),
		ALKALINE_EARTH_METAL("alkaline_earth_metal", AQUA),
		TRANSITION_METAL("transition_metal", WHITE),
		POST_TRANSITION_METAL("post_transition_metal", YELLOW),
		METALLOID("metalloid", LIGHT_PURPLE),
		NON_METAL("non_metal", GREEN),
		HALOGEN("halogen", GOLD),
		NOBLE_GAS("noble_gas", DARK_PURPLE),
		LANTHANIDE("lanthanide", RED),
		ACTINIDE("actinide", DARK_RED),
		UNKNOWN("unknown", GRAY);

		final String id;
		final Formatting format;

		Group(String id, Formatting format) {
			this.id = id;
			this.format = format;
		}

		public String getId() {
			return id;
		}

		public Text getTranslation() {
			return new TranslatableText("element.xchemical.group." + id).formatted(format);
		}
	}
}
