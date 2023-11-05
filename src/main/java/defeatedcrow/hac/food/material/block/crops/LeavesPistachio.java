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
import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.core.climate.DCTimeHelper;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class LeavesPistachio extends LeavesCropBlockDC {

	public LeavesPistachio() {
		super(CropType.SUMAC, CropTier.EPIC, true);
		this.setSeason(EnumSeason.SPRING_LATE, EnumSeason.SUMMER_LATE);
		this.cropSeasons.add(EnumSeason.AUTUMN_EARLY);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/leaves_sumac_pistachio";
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.SUMAC;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.LEAVES;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return FoodInit.BLOCK_SU_PISTACHIO.get();
	}

	@Override
	public Item getCropItem(CropTier t) {
		return FoodInit.CROP_SU_PISTACHIO.get();
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_SU_LACQUER.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_SU_MANGO.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_SU_CASHEW.get());
		case EPIC:
			return Optional.of(FoodInit.BLOCK_SU_PISTACHIO.get());
		default:
			return Optional.empty();
		}
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.SAND);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		return Lists.newArrayList();
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		return "lacquer";
	}

	@Override
	public void afterHarvest(Level world, BlockPos pos, BlockState thisState) {
		if (thisState != null && thisState.getBlock() instanceof IClimateCrop) {
			BlockState next = this.getHarvestedState(thisState);
			int m = this.getSeasonLeafStage(world, pos, next);
			EnumSeason season = DCTimeHelper.getSeasonEnum(world);
			if (m > 3 && season == EnumSeason.AUTUMN_EARLY) {
				m = 1;
			}
			world.setBlock(pos, next, m);
		}
	}

}
