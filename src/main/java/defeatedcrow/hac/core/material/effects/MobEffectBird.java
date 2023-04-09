package defeatedcrow.hac.core.material.effects;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class MobEffectBird extends MobEffectDC {

	final boolean fish;

	public MobEffectBird(boolean b, String n, MobEffectCategory cat, int color) {
		super(n, cat, color);
		fish = b;
	}

	@Override
	public void applyEffectTick(LivingEntity liv, int amp) {
		if (liv.level.isClientSide && liv instanceof Player player && camMovement(player)) {
			if (fish && player.isInWaterOrBubble()) {
				if (ClimateCore.proxy.keyFowardPushed()) {
					Vec3 v = player.getDeltaMovement();
					float f = player.yHeadRot;
					float f2 = Mth.sin(-f * Mth.DEG_TO_RAD);
					float f3 = Mth.cos(f * Mth.DEG_TO_RAD);
					double lx = 0.7D * Mth.abs(f2);
					double lz = 0.7D * Mth.abs(f3);
					double d1 = v.x + 0.2D * f2;
					double d2 = v.y;
					double d3 = v.z + 0.2D * f3;
					d1 = Mth.clamp(d1, -lx, lx);
					d3 = Mth.clamp(d3, -lz, lz);
					Vec3 v2 = new Vec3(d1, d2, d3);
					player.setDeltaMovement(v2);
				}
				if (ClimateCore.proxy.keyJumpPushed()) {
					Vec3 v = player.getDeltaMovement();
					double d1 = v.x;
					double d2 = v.y + 0.2D;
					double d3 = v.z;
					d2 = Mth.clamp(d2, -0.3D, 0.3D);
					Vec3 v2 = new Vec3(d1, d2, d3);
					player.setDeltaMovement(v2);
				}
				if (ClimateCore.proxy.keySneakPushed()) {
					Vec3 v = player.getDeltaMovement();
					double d = v.y - 0.2D;
					d = Mth.clamp(d, -0.3D, 0.3D);
					Vec3 v2 = new Vec3(v.x, d, v.z);
					player.setDeltaMovement(v2);
				}
			} else {
				if (ClimateCore.proxy.keyJumpPushed() && !player.isInWaterOrRain()) {
					Vec3 v = player.getDeltaMovement();
					double d1 = v.x;
					double d2 = v.y + 0.2D;
					double d3 = v.z;
					if (ClimateCore.proxy.keyFowardPushed() && !player.isOnGround()) {
						float f = player.yHeadRot;
						float f2 = Mth.sin(-f * Mth.DEG_TO_RAD);
						float f3 = Mth.cos(f * Mth.DEG_TO_RAD);
						double lx = 0.7D * Mth.abs(f2);
						double lz = 0.7D * Mth.abs(f3);
						d1 += 0.2D * f2;
						d3 += 0.2D * f3;
						d1 = Mth.clamp(d1, -lx, lx);
						d3 = Mth.clamp(d3, -lz, lz);
					}
					d2 = Mth.clamp(d2, -0.5D, 0.5D);
					Vec3 v2 = new Vec3(d1, d2, d3);
					player.setDeltaMovement(v2);
				}
				if (ClimateCore.proxy.keySneakPushed() && !player.isInWaterOrRain() && !player.isOnGround()) {
					Vec3 v = player.getDeltaMovement();
					Vec3 v2 = new Vec3(v.x, 0D, v.z);
					player.setDeltaMovement(v2);
				}
			}
		}

	}

	@Override
	public boolean isDurationEffectTick(int dur, int amp) {
		return true;
	}

	private boolean camMovement(Player player) {
		return !player.isSleeping() && !player.isPassenger();
	}
}
