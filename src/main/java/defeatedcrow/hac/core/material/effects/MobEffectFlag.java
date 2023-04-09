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
import net.minecraft.world.entity.monster.Monster;

public class MobEffectFlag extends MobEffectDC {

	public MobEffectFlag(String n, MobEffectCategory cat, int color) {
		super(n, cat, color);
	}

	@Override
	public void applyEffectTick(LivingEntity liv, int amp) {
		if (!liv.level.isClientSide) {
			double d = (amp + 1) * 16D;
			List<Monster> list = liv.level.getNearbyEntities(Monster.class, TargetingConditions.forCombat().range(d), liv, liv.getBoundingBox().inflate(d));
			list.stream().filter(this::notNeutral).forEach(mob -> {
				if (this == CoreInit.FLAG.get())
					mob.setTarget(liv);
				else if (this == CoreInit.CLAIR.get())
					mob.addEffect(new MobEffectInstance(MobEffects.GLOWING, 300));
			});
		}

	}

	private boolean notNeutral(Mob mob) {
		return !(mob instanceof NeutralMob);
	}

	@Override
	public boolean isDurationEffectTick(int dur, int amp) {
		return dur % 20 == 0;
	}
}
