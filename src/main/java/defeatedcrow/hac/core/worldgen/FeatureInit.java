package defeatedcrow.hac.core.worldgen;

import java.util.function.Supplier;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.worldgen.vein.OreveinFeature;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FeatureInit {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.Keys.FEATURES, ClimateCore.MOD_ID);
	public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, ClimateCore.MOD_ID);

	public static final RegistryObject<Feature> ORE_VEIN = register("ore_vein_dc", () -> new OreveinFeature(NoneFeatureConfiguration.CODEC));

	public static void init() {}

	public static RegistryObject<Feature> register(String name, Supplier<Feature> item) {
		return FEATURES.register(name, item);
	}
}
