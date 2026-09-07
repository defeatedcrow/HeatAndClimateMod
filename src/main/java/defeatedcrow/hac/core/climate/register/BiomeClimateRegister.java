package defeatedcrow.hac.core.climate.register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.api.climate.IBiomeClimateRegister;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.event.WorldHeatTierEvent;
import defeatedcrow.hac.core.climate.Climate;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.core.climate.WeatherChecker;
import defeatedcrow.hac.core.config.ConfigCommonBuilder;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import net.minecraftforge.common.Tags.Biomes;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class BiomeClimateRegister implements IBiomeClimateRegister {

	private static Map<ResourceLocation, Climate> regMap;
	private static List<ResourceLocation> seasons;

	public static BiomeClimateRegister INSTANCE = new BiomeClimateRegister();
	private static IForgeRegistry<Biome> reg = ForgeRegistries.BIOMES;

	private BiomeClimateRegister() {
		BiomeClimateRegister.regMap = new HashMap<>();
		BiomeClimateRegister.seasons = new ArrayList<>();
	}

	@Override
	public void addBiomeClimate(Biome biome, DCHeatTier temp, DCHumidity hum, DCAirflow airflow) {
		if (biome != null && reg.containsValue(biome)) {
			ResourceLocation name = reg.getKey(biome);
			Climate clm = new Climate(temp, hum, airflow);
			regMap.put(name, clm);
		}
	}

	@Override
	public void addBiomeClimate(Biome biome, DCHeatTier temp, DCHumidity hum, DCAirflow airflow, boolean hasSeason) {
		if (biome != null && reg.containsValue(biome) && !hasSeason) {
			ResourceLocation name = reg.getKey(biome);
			if (!seasons.contains(name))
				seasons.add(name);
		}
		addBiomeClimate(biome, temp, hum, airflow);
	}

	@Override
	public void setNoSeason(Biome biome) {
		if (biome != null && reg.containsValue(biome)) {
			ResourceLocation name = reg.getKey(biome);
			if (!seasons.contains(name))
				seasons.add(name);
		}
	}

	@Override
	public Map<ResourceLocation, ? extends IClimate> getClimateList() {
		return regMap;
	}

	@Override
	public List<ResourceLocation> getNoSeasonList() {
		return seasons;
	}

	@Override
	public Optional<IClimate> getClimateFromBiome(Level world, BlockPos pos) {
		if (world.isLoaded(pos)) {
			Holder<Biome> biome = world.getBiome(pos);
			ResourceLocation name = DCUtil.getLocationName(biome)
			    .orElse(DCUtil.DUMMY);
			return getClimateFromBiome(name);
		}
		return Optional.empty();
	}

	private Optional<IClimate> getClimateFromList(ResourceLocation id) {
		if (id != null && regMap.containsKey(id)) {
			return Optional.ofNullable(regMap.get(id));
		}
		return Optional.empty();
	}

	@Override
	public Optional<IClimate> getClimateFromBiome(ResourceLocation biomeID) {
		Optional<IClimate> clm = getClimateFromList(biomeID);
		return clm;
	}

	private static final PerlinSimplexNoise TEMPERATURE_NOISE = new PerlinSimplexNoise(new WorldgenRandom(new LegacyRandomSource(1234L)), ImmutableList.of(0));

	@Override
	public DCHeatTier getHeatTier(Level world, BlockPos pos) {
		if (world.isLoaded(pos)) {
			boolean isNether = world.getBiome(pos)
			    .is(BiomeTags.IS_NETHER);
			boolean isEnd = world.getBiome(pos)
			    .is(BiomeTags.IS_END);
			float temp = getBiomeTemp(world, pos);

			DCHeatTier current = DCHeatTier.getTypeByBiomeTemp(temp);

			if (isNether && temp == 240F) {
				current = DCHeatTier.INFERNO;
			}

			WorldHeatTierEvent event = new WorldHeatTierEvent(world, pos, current, true);
			current = event.result();

			return current;

		} else {
			return DCHeatTier.NORMAL;
		}
	}

	public float getBiomeTemp(Level world, BlockPos pos) {
		if (world.isLoaded(pos)) {
			Holder<Biome> b = world.getBiome(pos);
			ResourceLocation dim = world.dimension()
			    .location();
			Optional<DCHeatTier> ret = getRegisteredHeatTier(reg.getKey(b.get()));
			float temp = ret.map(DCHeatTier::getBiomeTemp)
			    .orElse(b.get()
			        .getBaseTemperature());
			boolean isNether = world.getBiome(pos)
			    .is(BiomeTags.IS_NETHER);
			boolean isEnd = world.getBiome(pos)
			    .is(BiomeTags.IS_END);

			if (isNether) {
				if (ConfigCommonBuilder.INSTANCE.enInferno.get())
					temp = 240F;
				else
					temp += 4.0F + ConfigCommonBuilder.INSTANCE.getSeasonTempOffset(EnumSeason.SCORCHER);
			} else if (isEnd) {
				temp += -2.0F + ConfigCommonBuilder.INSTANCE.getSeasonTempOffset(EnumSeason.ABSOLUTE);
			} else {
				// 高度補正
				float f1 = (float) (TEMPERATURE_NOISE.getValue(pos.getX() / 8.0F, pos.getZ() / 8.0F, false) * 8.0D);
				float f2 = (f1 + pos.getY() - 80.0F) * 0.05F / 40.0F;
				if (f2 < -1.0F)
					f2 = -1.0F;
				temp -= f2;

				float offset = WeatherChecker.getTempOffsetFloat(dim, isNether);
				temp += offset;

				float offset2 = DCTimeHelper.getTimeOffset(world, b);
				temp += offset2;
			}

			return temp;

		} else {
			return 0.6F;
		}
	}

	@Override
	public DCAirflow getAirflow(Level world, BlockPos pos) {
		if (world.isLoaded(pos)) {
			Holder<Biome> biome = world.getBiome(pos);
			DCAirflow ret = getRegisteredAirflow(reg.getKey(biome.get())).orElse(DCAirflow.NORMAL);
			if (ret == DCAirflow.NORMAL) {
				if (biome.is(BiomeTags.IS_MOUNTAIN) || biome.is(BiomeTags.IS_HILL))
					return DCAirflow.FLOW;
			}
			return ret;
		}
		return DCAirflow.NORMAL;
	}

	@Override
	public DCHumidity getHumidity(Level world, BlockPos pos) {
		if (world.isLoaded(pos)) {
			Holder<Biome> biome = world.getBiome(pos);
			DCHumidity hum = getRegisteredHumidity(reg.getKey(biome.get())).orElse(DCHumidity.NORMAL);
			if (hum == DCHumidity.NORMAL) {
				if (biome.is(Biomes.IS_WATER) || biome.is(Biomes.IS_WET) || biome.get()
				    .getModifiedClimateSettings().downfall() > 0.8F) {
					return DCHumidity.WET;
				} else if (biome.is(Biomes.IS_DRY) || biome.get()
				    .getModifiedClimateSettings().downfall() <= 0.3F) {
					return DCHumidity.DRY;
				}
			}
			return hum;
		}
		return DCHumidity.NORMAL;
	}

	@Override
	public Optional<DCHeatTier> getRegisteredHeatTier(ResourceLocation biomeID) {
		Optional<IClimate> clm = getClimateFromList(biomeID);
		return clm.map(IClimate::getHeat);
	}

	@Override
	public Optional<DCAirflow> getRegisteredAirflow(ResourceLocation biomeID) {
		Optional<IClimate> clm = getClimateFromList(biomeID);
		return clm.map(IClimate::getAirflow);
	}

	@Override
	public Optional<DCHumidity> getRegisteredHumidity(ResourceLocation biomeID) {
		Optional<IClimate> clm = getClimateFromList(biomeID);
		return clm.map(IClimate::getHumidity);
	}

}
