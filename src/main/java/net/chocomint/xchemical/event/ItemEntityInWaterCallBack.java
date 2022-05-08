package net.chocomint.xchemical.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.ActionResult;

public interface ItemEntityInWaterCallBack {

	Event<ItemEntityInWaterCallBack> EVENT = EventFactory.createArrayBacked(ItemEntityInWaterCallBack.class, listeners -> itemEntity -> {
		for (ItemEntityInWaterCallBack listener : listeners) {
			ActionResult result = listener.interact(itemEntity);

			if(result != ActionResult.PASS)
				return result;
		}
		return ActionResult.PASS;
	});

	ActionResult interact(ItemEntity itemEntity);
}
