package defeatedcrow.hac.food.material.block.crops;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class LeavesCoffee extends LeavesCropBlockDC {

	public LeavesCoffee() {
		super(CropType.RUBIA, CropTier.COMMON, false);
		this.setSeason(EnumSeason.SPRING_LATE, EnumSeason.AUTUMN_LATE);
		this.flowerSeasons.add(EnumSeason.SUMMER_EARLY);
		this.cropSeasons.add(EnumSeason.WINTER_EARLY);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/leaves_rubia_coffee";
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.RUBIA;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.LEAVES;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return FoodInit.BLOCK_RU_COFFEE.get();
	}

	@Override
	public Item getCropItem(CropTier t) {
		return FoodInit.CROP_RU_COFFEE.get();
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_RU_GARDENIA.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_RU_COFFEE.get());
		case RARE -> Optional.of(FoodInit.BLOCK_RU_IXORA.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		return ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		return switch (t) {
		case WILD, COMMON -> ImmutableList.of("MOUNTAIN", "FOREST", "JUNGLE");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("COLD", "DRY", "CONIFEROUS");
		case COMMON -> ImmutableList.of("COLD", "CONIFEROUS", "LOWLAND");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		return "coffee";
	}

}
