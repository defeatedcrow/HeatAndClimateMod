package defeatedcrow.hac.core.material.effects;

import java.util.List;

import defeatedcrow.hac.core.material.CoreInit;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Enemy;

public class MobEffectFlag extends MobEffectDC {

	public MobEffectFlag(String n, MobEffectCategory cat, int color) {
		super(n, cat, color);
	}

	@Override
	public void applyEffectTick(LivingEntity liv, int amp) {
		if (!liv.level.isClientSide) {
			double d = 16D + (amp * 16D);
			List<Mob> list = liv.level.getNearbyEntities(Mob.class, TargetingConditions.forCombat().range(d).ignoreLineOfSight().ignoreInvisibilityTesting(), liv, liv.getBoundingBox().inflate(d));
			list.stream().filter(this::notNeutral).forEach(mob -> {
				if (this == CoreInit.FLAG.get())
					mob.setTarget(liv);
				else if (this == CoreInit.CLAIR.get())
					mob.addEffect(new MobEffectInstance(MobEffects.GLOWING, 30));
			});
		}

	}

	private boolean notNeutral(Mob mob) {
		return mob instanceof Enemy && !(mob instanceof NeutralMob);
	}

	@Override
	public boolean isDurationEffectTick(int dur, int amp) {
		return dur % 20 == 0;
	}
}
