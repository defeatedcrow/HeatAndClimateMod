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
import defeatedcrow.hac.api.climate.IBiomeClimateRegister;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.event.WorldHeatTierEvent;
import defeatedcrow.hac.core.climate.Climate;
import defeatedcrow.hac.core.climate.WeatherChecker;
import defeatedcrow.hac.core.config.CoreConfigDC;
import defeatedcrow.hac.core.util.DCTimeHelper;
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

	private static Map<ResourceLocation, Climate> recipes;
	private static List<ResourceLocation> seasons;

	public static BiomeClimateRegister INSTANCE = new BiomeClimateRegister();
	private static IForgeRegistry<Biome> reg = ForgeRegistries.BIOMES;

	private BiomeClimateRegister() {
		this.recipes = new HashMap<ResourceLocation, Climate>();
		this.seasons = new ArrayList<ResourceLocation>();
	}

	@Override
	public void addBiomeClimate(Biome biome, DCHeatTier temp, DCHumidity hum, DCAirflow airflow) {
		if (biome != null && reg.containsValue(biome)) {
			ResourceLocation name = reg.getKey(biome);
			Climate clm = new Climate(temp, hum, airflow);
			recipes.put(name, clm);
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
		return recipes;
	}

	@Override
	public List<ResourceLocation> getNoSeasonList() {
		return seasons;
	}

	@Override
	public Optional<IClimate> getClimateFromBiome(Level world, BlockPos pos) {
		if (world.isLoaded(pos)) {
			Holder<Biome> biome = world.getBiome(pos);
			ResourceLocation name = DCUtil.getLocationName(biome).orElse(DCUtil.DUMMY);
			return getClimateFromBiome(name);
		}
		return Optional.empty();
	}

	private Optional<IClimate> getClimateFromList(ResourceLocation id) {
		if (id != null && recipes.containsKey(id)) {
			return Optional.ofNullable(recipes.get(id));
		}
		return Optional.empty();
	}

	@Override
	public Optional<IClimate> getClimateFromBiome(ResourceLocation biomeID) {
		Optional<IClimate> clm = getClimateFromList(biomeID);
		return clm;
	}

	private static final PerlinSimplexNoise TEMPERATURE_NOISE = new PerlinSimplexNoise(new WorldgenRandom(
			new LegacyRandomSource(1234L)), ImmutableList.of(0));

	@Override
	public DCHeatTier getHeatTier(Level world, BlockPos pos) {
		if (world.isLoaded(pos)) {
			Holder<Biome> b = world.getBiome(pos);
			ResourceLocation dim = world.dimension().location();
			Optional<DCHeatTier> ret = getRegisteredHeatTier(reg.getKey(b.get()));
			float temp = ret.map(h -> h.getBiomeTemp()).orElse(b.get().getBaseTemperature());

			// Biome補正
			if (b.is(BiomeTags.IS_NETHER)) {
				temp += 2.0F;
			}

			// 高度補正
			float f1 = (float) (TEMPERATURE_NOISE.getValue(pos.getX() / 8.0F, pos.getZ() / 8.0F, false) * 8.0D);
			float f2 = (f1 + pos.getY() - 80.0F) * 0.05F / 40.0F;
			if (f2 < -1.0F)
				f2 = -1.0F;
			temp -= f2;

			if (CoreConfigDC.enableWeatherEffect) {
				float offset = WeatherChecker.getTempOffsetFloat(dim, world.getBiome(pos).is(BiomeTags.IS_NETHER));
				temp += offset;
			}

			if (CoreConfigDC.enableTimeEffect) {
				float offset = DCTimeHelper.getTimeOffset(world, b);
				temp += offset;
			}

			DCHeatTier current = DCHeatTier.getTypeByBiomeTemp(temp);

			WorldHeatTierEvent event = new WorldHeatTierEvent(world, pos, current, true);
			current = event.result();

			return current;

		} else {
			return DCHeatTier.NORMAL;
		}
	}

	@Override
	public DCAirflow getAirflow(Level world, BlockPos pos) {
		if (world.isLoaded(pos)) {
			Holder<Biome> biome = world.getBiome(pos);
			DCAirflow ret = getRegisteredAirflow(reg.getKey(biome.get())).orElse(DCAirflow.NORMAL);
			if (biome.is(BiomeTags.IS_MOUNTAIN) || biome.is(BiomeTags.IS_HILL))
				return DCAirflow.FLOW;
			return ret;
		}
		return DCAirflow.NORMAL;
	}

	@Override
	public DCHumidity getHumidity(Level world, BlockPos pos) {
		if (world.isLoaded(pos)) {
			Holder<Biome> biome = world.getBiome(pos);
			DCHumidity hum = getRegisteredHumidity(reg.getKey(biome.get())).orElse(DCHumidity.NORMAL);
			if (biome.is(Biomes.IS_WATER) || biome.is(Biomes.IS_WET) || biome.get().getDownfall() > 0.8F) {
				return DCHumidity.WET;
			} else if (biome.is(Biomes.IS_DRY) || biome.is(Biomes.IS_SPARSE)) {
				return DCHumidity.DRY;
			}
			return hum;
		}
		return DCHumidity.NORMAL;
	}

	@Override
	public Optional<DCHeatTier> getRegisteredHeatTier(ResourceLocation biomeID) {
		Optional<IClimate> clm = getClimateFromList(biomeID);
		return clm.map(ret -> ret.getHeat());
	}

	@Override
	public Optional<DCAirflow> getRegisteredAirflow(ResourceLocation biomeID) {
		Optional<IClimate> clm = getClimateFromList(biomeID);
		return clm.map(ret -> ret.getAirflow());
	}

	@Override
	public Optional<DCHumidity> getRegisteredHumidity(ResourceLocation biomeID) {
		Optional<IClimate> clm = getClimateFromList(biomeID);
		return clm.map(ret -> ret.getHumidity());
	}

}
