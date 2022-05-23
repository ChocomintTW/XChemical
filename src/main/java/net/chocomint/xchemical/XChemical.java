package net.chocomint.xchemical;

import net.chocomint.xchemical.block.ModBlocks;
import net.chocomint.xchemical.block.entity.ModBlockEntities;
import net.chocomint.xchemical.event.ModEvents;
import net.chocomint.xchemical.item.ModItems;
import net.chocomint.xchemical.recipe.ModRecipes;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class XChemical implements ModInitializer {

	public static final String MOD_ID = "xchemical";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModBlockEntities.registerModBlockEntities();

		ModEvents.registerServerEvents();
		ModRecipes.register();

//		ServerTickEvents.START_WORLD_TICK.register(world -> {
//		});
	}
}
