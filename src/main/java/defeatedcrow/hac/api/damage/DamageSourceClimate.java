package defeatedcrow.hac.api.damage;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;

/**
 * 気候によるダメージのDamageSource<br>
 * 専用死亡メッセージあり
 */
public class DamageSourceClimate extends DamageSource {

	public static DamageSourceClimate climateHeatDamage = new DamageSourceClimate(
			Holder.direct(new DamageType("dcs_heat", DamageScaling.ALWAYS, 0.1F))).setHeatDamage();
	public static DamageSourceClimate climateColdDamage = new DamageSourceClimate(
			Holder.direct(new DamageType("dcs_cold", DamageScaling.ALWAYS, 0.1F))).setHeatDamage().setNegativeDamage();
	public static DamageSourceClimate climateWaterDamage = new DamageSourceClimate(
			Holder.direct(new DamageType("dcs_water", DamageScaling.ALWAYS, 0.1F))).setHumDamage();
	public static DamageSourceClimate climateDryDamage = new DamageSourceClimate(
			Holder.direct(new DamageType("dcs_dry", DamageScaling.ALWAYS, 0.1F))).setHumDamage().setNegativeDamage();
	public static DamageSourceClimate climateWindDamage = new DamageSourceClimate(
			Holder.direct(new DamageType("dcs_wind", DamageScaling.ALWAYS, 0.1F))).setAirDamage();
	public static DamageSourceClimate climateSuffocationDamage = new DamageSourceClimate(
			Holder.direct(new DamageType("dcs_suffocation", DamageScaling.ALWAYS, 0.1F))).setAirDamage().setNegativeDamage();
	public static DamageSource machineDamage = new DamageSource(Holder.direct(new DamageType("dcs_machine", DamageScaling.ALWAYS, 0.1F)));

	public DamageSourceClimate(Holder<DamageType> type) {
		super(type);
	}

	public boolean isHeat;
	public boolean isNegative;
	public boolean isHum;
	public boolean isAir;

	private DamageSourceClimate setBypassesArmor() {
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
