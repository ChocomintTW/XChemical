package net.chocomint.xchemical.util.damage;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

public class SodiumExplosionDamageSource extends DamageSource {
	protected SodiumExplosionDamageSource(String name) {
		super(name);
	}

	@Override
	public Text getDeathMessage(LivingEntity entity) {
		return new TranslatableText("death.xchemical.sodium_explosion", entity.getDisplayName());
	}

	public static DamageSource explosion(@Nullable Explosion explosion) {
		return SodiumExplosionDamageSource.explosion(explosion != null ? explosion.getCausingEntity() : null);
	}

	public static DamageSource explosion(@Nullable LivingEntity attacker) {
		return (new SodiumExplosionDamageSource("sodium_explosion")).setScaledWithDifficulty().setExplosive();
	}
}
