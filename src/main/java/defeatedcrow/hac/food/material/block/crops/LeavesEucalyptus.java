package defeatedcrow.hac.food.material.block.crops;

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

public class LeavesEucalyptus extends LeavesCropBlockDC {

	public LeavesEucalyptus() {
		super(CropType.MYRTLE, CropTier.WILD, false);
		this.setSeason(EnumSeason.WINTER_EARLY, EnumSeason.WINTER_LATE);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/leaves_myrtle_eucalyptus";
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.MYRTLE;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.LEAVES;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return FoodInit.BLOCK_MY_EUCALYPTUS.get();
	}

	@Override
	public Item getCropItem(CropTier t) {
		return Item.BY_BLOCK.get(FoodInit.BLOCK_MY_EUCALYPTUS.get());
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_MY_EUCALYPTUS.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_MY_GUAVA.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_MY_CLOVE.get());
		default:
			return Optional.empty();
		}
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.MUD, SoilType.SAND);
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
		return ImmutableList.of("SAVANNA, RIVER, SWAMP");
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return ImmutableList.of("SNOWY", "COLD");
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		return "eucalyptus";
	}

}
