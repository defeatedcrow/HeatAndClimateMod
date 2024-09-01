package defeatedcrow.hac.core.mixin;

import javax.annotation.Nonnull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

@Mixin(NearestAttackableTargetGoal.class)
public class AttackTargetGoalMixin {

	@Inject(method = "canUse", at = @At(value = "HEAD"), cancellable = true, remap = true)
	public void hookUseGoal(@Nonnull CallbackInfoReturnable<Boolean> callback) {
		NearestAttackableTargetGoal goal = NearestAttackableTargetGoal.class.cast(this);
		if (ConfigCommonBuilder.INSTANCE.enMobTarget.get()) {
			if (goal.mob != null) {
				int i = goal.mob.getPersistentData().getInt("dcs_fulfill_interval");
				if (i > 0) {
					callback.setReturnValue(false);
				}
			}
		}
	}
}
