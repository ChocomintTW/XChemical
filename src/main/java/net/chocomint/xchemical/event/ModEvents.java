package net.chocomint.xchemical.event;

import net.chocomint.xchemical.gui.hud.DirectionHud;
import net.chocomint.xchemical.item.ModItems;
import net.chocomint.xchemical.util.damage.SodiumExplosionDamageSource;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

public class ModEvents {

	public static void registerServerEvents() {

		ItemEntityInWaterCallBack.EVENT.register(itemEntity -> {
			World world = itemEntity.getWorld();
			if (!world.isClient() && itemEntity.getStack().getItem() == ModItems.SODIUM
					&& world.getBlockState(new BlockPos(itemEntity.getPos())).getBlock() == Blocks.WATER) {

				float power = 1 + Math.floorDiv(itemEntity.getStack().getCount(), 8);

				world.createExplosion(itemEntity, SodiumExplosionDamageSource.explosion(new Explosion(world, itemEntity,
								itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), power)),
						new ExplosionBehavior(), itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(),
						power, false, Explosion.DestructionType.BREAK);

				((ServerWorld) world).spawnParticles(ParticleTypes.EXPLOSION, itemEntity.getX(),
						itemEntity.getY(), itemEntity.getZ(), (int) power, 2, 2, 2, 1);
				itemEntity.kill();

				return ActionResult.SUCCESS;
			}
			else return ActionResult.FAIL;
		});
	}

	public static void registerClientEvents() {

		HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> new DirectionHud(MinecraftClient.getInstance()).render(matrixStack));
	}
}
