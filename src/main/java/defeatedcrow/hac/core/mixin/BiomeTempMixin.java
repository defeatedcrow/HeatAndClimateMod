package defeatedcrow.hac.core.mixin;

import javax.annotation.Nonnull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import defeatedcrow.hac.api.event.DCBiomeTempEvent;
import net.minecraft.world.level.biome.Biome;

@Mixin(Biome.class)
public class BiomeTempMixin {

	@Inject(method = "getBaseTemperature", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
	public void hookBiomeBasetemp(@Nonnull CallbackInfoReturnable<Float> callback) {
		float def = callback.getReturnValue();
		DCBiomeTempEvent event = new DCBiomeTempEvent(Biome.class.cast(this), def);
		callback.setReturnValue(event.result());
	}

}
