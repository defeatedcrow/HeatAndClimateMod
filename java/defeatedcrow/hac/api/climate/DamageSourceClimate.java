package defeatedcrow.hac.api.climate;

import net.minecraft.util.DamageSource;

public class DamageSourceClimate extends DamageSource {

	public static DamageSourceClimate climateHeatDamage = (new DamageSourceClimate("dcs_heat")).setHeatDamage();
	public static DamageSourceClimate climateColdDamage = (new DamageSourceClimate("dcs_cold"));

	public DamageSourceClimate(String damageTypeIn) {
		super(damageTypeIn);
		this.setDamageBypassesArmor();
	}

	public boolean isHeat;

	private DamageSourceClimate setHeatDamage() {
		this.isHeat = true;
		return this;
	}

	public boolean isHeatDamage() {
		return isHeat;
	}

}
