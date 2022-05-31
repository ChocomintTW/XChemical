package net.chocomint.xchemical.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import net.chocomint.xchemical.XChemical;
import net.chocomint.xchemical.rei.chemin.CheMinREIDisplay;
import net.chocomint.xchemical.rei.constructor.ConstructorREIDisplay;
import net.minecraft.util.Identifier;

public class ModREICategoryIdentifiers {
	public static final CategoryIdentifier<ConstructorREIDisplay> CONSTRUCTOR =
			CategoryIdentifier.of(new Identifier(XChemical.MOD_ID, "constructor"));
	public static final CategoryIdentifier<CheMinREIDisplay> CHEMIN =
			CategoryIdentifier.of(new Identifier(XChemical.MOD_ID, "chemin"));
}
