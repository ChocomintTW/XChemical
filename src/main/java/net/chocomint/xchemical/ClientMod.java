package net.chocomint.xchemical;

import net.chocomint.xchemical.block.ModBlocks;
import net.chocomint.xchemical.screen.AnalyzerScreen;
import net.chocomint.xchemical.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class ClientMod implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ANALYZER, RenderLayer.getCutout());

		ScreenRegistry.register(ModScreenHandlers.ANALYZER_SCREEN_HANDLER, AnalyzerScreen::new);
	}
}
