package net.chocomint.xchemical;

import net.chocomint.xchemical.block.ModBlocks;
import net.chocomint.xchemical.event.ModEvents;
import net.chocomint.xchemical.gui.hud.DirectionHud;
import net.chocomint.xchemical.screen.AnalyzerScreen;
import net.chocomint.xchemical.screen.CompoundMixerScreen;
import net.chocomint.xchemical.screen.ConstructorScreen;
import net.chocomint.xchemical.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class ClientMod implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ANALYZER, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COMPOUND_MIXER, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CONSTRUCTOR, RenderLayer.getCutout());

		ScreenRegistry.register(ModScreenHandlers.ANALYZER_SCREEN_HANDLER, AnalyzerScreen::new);
		ScreenRegistry.register(ModScreenHandlers.COMPOUND_MIXER_SCREEN_HANDLER, CompoundMixerScreen::new);
		ScreenRegistry.register(ModScreenHandlers.CONSTRUCTOR_SCREEN_HANDLER, ConstructorScreen::new);

		ModEvents.registerClientEvents();
	}
}
