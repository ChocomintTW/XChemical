package net.chocomint.xchemical.mixin;

import com.mojang.authlib.GameProfile;
import net.chocomint.xchemical.XChemical;
import net.chocomint.xchemical.util.damage.SodiumExplosionDamageSource;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.Iterator;
import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends PlayerEntity {

	public PlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
		super(world, pos, yaw, profile);
	}

//	@Shadow public abstract World getWorld();
//	@Shadow public abstract UUID getUUID();
//
//	@Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
//	public void onDeath(DamageSource source) {
//		System.out.println(source);
//		if (source instanceof SodiumExplosionDamageSource) {
//			System.out.println("Sodium");
//			MinecraftServer server = getWorld().getServer();
//			System.out.println(server);
//			if (server != null) {
//				ServerPlayerEntity player = server.getPlayerManager().getPlayer(getUUID());
//				Advancement explosion = Advancement.Builder.create().build(new Identifier(XChemical.MOD_ID, "advancements/research/explosion"));
//				assert player != null;
//
//				AdvancementProgress advancementProgress = player.getAdvancementTracker().getProgress(explosion);
//				if (!advancementProgress.isDone()) {
//					for (String string : advancementProgress.getUnobtainedCriteria()) {
//						player.getAdvancementTracker().grantCriterion(explosion, string);
//					}
//				}
//			}
//		}
//	}
}
