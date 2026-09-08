package defeatedcrow.hac.core.util;

import java.util.HashMap;
import java.util.Map;

import defeatedcrow.hac.api.damage.DamageTypeClimate;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class DamageSourceClimate {

	private final DamageSource heat;
	private final DamageSource cold;
	private final DamageSource machine;

	public static final Map<ResourceKey<Level>, DamageSourceClimate> INSTANCES = new HashMap<>();

	public DamageSourceClimate(RegistryAccess registryAccess) {
		Registry<DamageType> registry = registryAccess.registryOrThrow(Registries.DAMAGE_TYPE);
		heat = new DamageSource(registry.getHolderOrThrow(DamageTypeClimate.CLIMATE_HEAT));
		cold = new DamageSource(registry.getHolderOrThrow(DamageTypeClimate.CLIMATE_COLD));
		machine = new DamageSource(registry.getHolderOrThrow(DamageTypeClimate.MACHINE));
	}

	public DamageSource getHeat() {
		return heat;
	}

	public DamageSource getCold() {
		return cold;
	}

	public DamageSource getClimateDamage(boolean isCold) {
		return isCold ? cold : heat;
	}

	public DamageSource getMachine() {
		return machine;
	}

	public static DamageSourceClimate getInstance(Level level) {
		if (INSTANCES.containsKey(level.dimension())) {
			return INSTANCES.get(level.dimension());
		} else {
			DamageSourceClimate source = new DamageSourceClimate(level.registryAccess());
			INSTANCES.put(level.dimension(), source);
			return source;
		}
	}

}
