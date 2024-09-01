package defeatedcrow.hac.core.mixin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

@Mixin(TargetingConditions.class)
public abstract class TargetingConditionsMixin {

	@Inject(method = "test", at = @At(value = "HEAD"), cancellable = true, remap = false)
	public void testFix(@Nullable LivingEntity owner, LivingEntity target, @Nonnull CallbackInfoReturnable<Boolean> callback) {
		if (target == null) {
			callback.setReturnValue(false);
		}
	}

}
