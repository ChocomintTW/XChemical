package net.chocomint.xchemical.screen;

import net.chocomint.xchemical.XChemical;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

	public static ScreenHandlerType<AnalyzerScreenHandler> ANALYZER_SCREEN_HANDLER =
			ScreenHandlerRegistry.registerSimple(new Identifier(XChemical.MOD_ID, "analyzer"), AnalyzerScreenHandler::new);
}
