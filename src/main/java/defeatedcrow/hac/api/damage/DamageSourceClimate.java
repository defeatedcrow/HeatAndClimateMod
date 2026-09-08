package defeatedcrow.hac.api.damage;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;

/**
 * 気候によるダメージのDamageSource<br>
 * 専用死亡メッセージあり
 */
public class DamageSourceClimate extends DamageSource {

	public static final String MOD_ID = "dcs_climate";

	public static final ResourceKey<DamageType> HEAT = key("dcs_heat");
	public static final ResourceKey<DamageType> COLD = key("dcs_cold");
	public static final ResourceKey<DamageType> WATER = key("dcs_water");
	public static final ResourceKey<DamageType> DRY = key("dcs_dry");
	public static final ResourceKey<DamageType> WIND = key("dcs_wind");
	public static final ResourceKey<DamageType> SUFFOCATION = key("dcs_suffocation");
	public static final ResourceKey<DamageType> MACHINE = key("dcs_machine");

	private static ResourceKey<DamageType> key(String name) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(MOD_ID, name));
	}

	private static Holder<DamageType> holder(RegistryAccess access, ResourceKey<DamageType> key) {
		return access.registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key);
	}

	public static DamageSourceClimate climateHeatDamage(RegistryAccess access) {
		return new DamageSourceClimate(holder(access, HEAT)).setHeatDamage();
	}

	public static DamageSourceClimate climateColdDamage(RegistryAccess access) {
		return new DamageSourceClimate(holder(access, COLD)).setHeatDamage().setNegativeDamage();
	}

	public static DamageSourceClimate climateWaterDamage(RegistryAccess access) {
		return new DamageSourceClimate(holder(access, WATER)).setHumDamage();
	}

	public static DamageSourceClimate climateDryDamage(RegistryAccess access) {
		return new DamageSourceClimate(holder(access, DRY)).setHumDamage().setNegativeDamage();
	}

	public static DamageSourceClimate climateWindDamage(RegistryAccess access) {
		return new DamageSourceClimate(holder(access, WIND)).setAirDamage();
	}

	public static DamageSourceClimate climateSuffocationDamage(RegistryAccess access) {
		return new DamageSourceClimate(holder(access, SUFFOCATION)).setAirDamage().setNegativeDamage();
	}

	public static DamageSource machineDamage(RegistryAccess access) {
		return new DamageSource(holder(access, MACHINE));
	}

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
