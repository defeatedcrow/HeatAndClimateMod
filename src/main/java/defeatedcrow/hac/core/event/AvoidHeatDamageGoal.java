package defeatedcrow.hac.core.event;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class AvoidHeatDamageGoal extends FleeSunGoal {

	private final Level level;
	public Vec3 avoidPos;

	public AvoidHeatDamageGoal(PathfinderMob mob, double speed, Vec3 pos) {
		super(mob, speed);
		level = mob.getLevel();
		avoidPos = pos;
	}

	@Override
	public boolean canUse() {
		if (this.mob.getTarget() != null) {
			return false;
		} else if (avoidPos == null) {
			return false;
		} else if (this.getDamage() < 1.0F) {
			return false;
		} else {
			return this.setWantedPos();
		}
	}

	private float getDamage() {
		if (this.mob.getPersistentData().contains("dcs_lastDamage")) {
			return this.mob.getPersistentData().getFloat("dcs_lastDamage");
		}
		return 0F;
	}

	@Override
	@Nullable
	protected Vec3 getHidePos() {
		RandomSource randomsource = this.mob.getRandom();
		BlockPos blockpos = this.mob.blockPosition();

		if (avoidPos != null)
			for (int i = 0; i < 10; ++i) {
				BlockPos blockpos1 =
						blockpos.offset(randomsource.nextInt(10) - 5, randomsource.nextInt(6) - 3, randomsource.nextInt(10) - 5);
				if (avoidPos.distanceTo(Vec3.atBottomCenterOf(blockpos1)) > 3.0D && this.mob.getWalkTargetValue(blockpos1) < 0.0F) {
					return Vec3.atBottomCenterOf(blockpos1);
				}
			}

		return null;
	}

}
