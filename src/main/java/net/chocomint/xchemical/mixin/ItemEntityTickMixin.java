package net.chocomint.xchemical.mixin;

import net.chocomint.xchemical.event.ItemEntityInWaterCallBack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityTickMixin extends Entity {

	public ItemEntityTickMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Shadow public abstract ItemEntity copy();

	@Inject(method = "tick", at = @At(value = "HEAD"), cancellable = true)
	public void tick(CallbackInfo info) {
		ItemEntityInWaterCallBack.EVENT.invoker().interact(copy());
	}
}
