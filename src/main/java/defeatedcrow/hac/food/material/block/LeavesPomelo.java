package defeatedcrow.hac.food.material.block;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

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

public class LeavesPomelo extends LeavesCropBlockDC {

	public LeavesPomelo() {
		super(CropType.CITRUS, CropTier.WILD, false);
		this.setSeason(EnumSeason.SPRING_LATE, EnumSeason.WINTER_EARLY);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/leaves_citrus_pomelo";
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.CITRUS;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.LEAVES;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return FoodInit.BLOCK_CT_POMELO.get();
	}

	@Override
	public Item getCropItem(CropTier t) {
		return FoodInit.CROP_CT_POMELO.get();
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_CT_POMELO.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_CT_MANDARIN.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_CT_LEMON.get());
		default:
			return Optional.empty();
		}
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		return ImmutableList.of("JUNGLE", "FOREST");
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		return "pomelo";
	}

}
