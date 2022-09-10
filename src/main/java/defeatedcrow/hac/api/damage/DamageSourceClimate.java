package defeatedcrow.hac.api.damage;

import net.minecraft.world.damagesource.DamageSource;

/**
 * 気候によるダメージのDamageSource<br>
 * 専用死亡メッセージあり
 */
public class DamageSourceClimate extends DamageSource {

	public static DamageSourceClimate climateHeatDamage = new DamageSourceClimate(
			"dcs_heat").setHeatDamage().setBypassesArmor();
	public static DamageSourceClimate climateColdDamage = new DamageSourceClimate(
			"dcs_cold").setHeatDamage().setNegativeDamage().setBypassesArmor();
	public static DamageSourceClimate climateWaterDamage = new DamageSourceClimate(
			"dcs_water").setHumDamage().setBypassesArmor();
	public static DamageSourceClimate climateDryDamage = new DamageSourceClimate(
			"dcs_dry").setHumDamage().setNegativeDamage().setBypassesArmor();
	public static DamageSourceClimate climateWindDamage = new DamageSourceClimate(
			"dcs_wind").setAirDamage().setBypassesArmor();
	public static DamageSourceClimate climateSuffocationDamage = new DamageSourceClimate(
			"dcs_suffocation").setAirDamage().setNegativeDamage().setBypassesArmor();
	public static DamageSource machineDamage = new DamageSource("dcs_machine");

	public DamageSourceClimate(String damageTypeIn) {
		super(damageTypeIn);
	}

	public boolean isHeat;
	public boolean isNegative;
	public boolean isHum;
	public boolean isAir;

	private DamageSourceClimate setBypassesArmor() {
		this.setBypassesArmor();
		return this;
	}

	private DamageSourceClimate setHeatDamage() {
		this.isHeat = true;
		return this;
	}

	public boolean isHeatDamage() {
		return isHeat;
	}

	private DamageSourceClimate setNegativeDamage() {
		this.isNegative = true;
		return this;
	}

	public boolean isNegativeDamage() {
		return isNegative;
	}

	private DamageSourceClimate setHumDamage() {
		this.isHum = true;
		return this;
	}

	public boolean isHumDamage() {
		return isHum;
	}

	private DamageSourceClimate setAirDamage() {
		this.isAir = true;
		return this;
	}

	public boolean isAirDamage() {
		return isAir;
	}

}
