package defeatedcrow.hac.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import defeatedcrow.hac.core.config.ConfigClientBuilder;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
@Mixin(ParticleEngine.class)
public abstract class ParticleEngineMixin {

	@Inject(method = "createParticle", at = @At(value = "HEAD"), cancellable = true)
	public void hookCreateParticle(ParticleOptions option, double x, double y, double z, double d1, double d2, double d3, CallbackInfoReturnable<Particle> callback) {
		ParticleType type = option.getType();
		boolean ret = false;
		if (ConfigClientBuilder.INSTANCE.disablePotionEffect.get() && type == ParticleTypes.ENTITY_EFFECT) {
			Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
			if (camera.getPosition().distanceToSqr(x, y + 1.0D, z) < 4.0D) {
				ret = true;
			}
		}
		if (ret)
			callback.setReturnValue(null);
	}

}
