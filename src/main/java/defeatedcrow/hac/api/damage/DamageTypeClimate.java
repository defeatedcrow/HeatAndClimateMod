package defeatedcrow.hac.api.damage;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class DamageTypeClimate {

	public static final ResourceKey<DamageType> CLIMATE_HEAT = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(ClimateCore.MOD_ID, "heat"));
	public static final ResourceKey<DamageType> CLIMATE_COLD = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(ClimateCore.MOD_ID, "cold"));
	public static final ResourceKey<DamageType> CLIMATE_WATER = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(ClimateCore.MOD_ID, "water"));
	public static final ResourceKey<DamageType> CLIMATE_DRY = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(ClimateCore.MOD_ID, "dry"));
	public static final ResourceKey<DamageType> CLIMATE_WIND = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(ClimateCore.MOD_ID, "wind"));
	public static final ResourceKey<DamageType> CLIMATE_SAFFOCATION = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(ClimateCore.MOD_ID, "suffocation"));
	public static final ResourceKey<DamageType> MACHINE = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(ClimateCore.MOD_ID, "machine"));

}
