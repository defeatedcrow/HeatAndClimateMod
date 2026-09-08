package defeatedcrow.hac.core.material;

import defeatedcrow.hac.api.damage.DamageTypeClimate;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DeathMessageType;

public class DamageTypeInit {

	public static void bootstrap(BootstapContext<DamageType> context) {

		context.register(DamageTypeClimate.CLIMATE_HEAT, new DamageType("dcs_climate.heat", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.1F, DamageEffects.BURNING, DeathMessageType.DEFAULT));
		context.register(DamageTypeClimate.CLIMATE_COLD, new DamageType("dcs_climate.cold", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.1F, DamageEffects.FREEZING, DeathMessageType.DEFAULT));
		context.register(DamageTypeClimate.CLIMATE_WATER, new DamageType("dcs_climate.water", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.1F, DamageEffects.DROWNING, DeathMessageType.DEFAULT));
		context.register(DamageTypeClimate.CLIMATE_DRY, new DamageType("dcs_climate.dry", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.1F, DamageEffects.THORNS, DeathMessageType.DEFAULT));
		context.register(DamageTypeClimate.CLIMATE_WIND, new DamageType("dcs_climate.wind", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.1F, DamageEffects.DROWNING, DeathMessageType.DEFAULT));
		context.register(DamageTypeClimate.CLIMATE_SAFFOCATION, new DamageType("dcs_climate.saffocation", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.1F, DamageEffects.DROWNING, DeathMessageType.DEFAULT));
		context.register(DamageTypeClimate.MACHINE, new DamageType("dcs_climate.machine", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.1F));

	}

}
