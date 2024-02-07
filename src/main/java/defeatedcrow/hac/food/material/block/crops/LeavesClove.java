package defeatedcrow.hac.food.material.block.crops;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropStage;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class LeavesClove extends LeavesCropBlockDC {

	public LeavesClove() {
		super(CropType.MYRTLE, CropTier.COMMON, false);
		this.setSeason(EnumSeason.SUMMER_LATE, EnumSeason.AUTUMN_EARLY);
		flowerSeasons.add(EnumSeason.WINTER_LATE);
		cropSeasons.add(EnumSeason.SPRING_EARLY);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/leaves_myrtle_clove";
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
		return FoodInit.BLOCK_MY_CLOVE.get();
	}

	@Override
	public Item getCropItem(CropTier t) {
		return FoodInit.CROP_MY_CLOVE.get();
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
		return ImmutableList.of();
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		return "clove";
	}

	/* 花の収穫ができる */
	@Override
	public boolean canHarvest(BlockState thisState) {
		CropStage stage = this.getCurrentStage(thisState);
		return stage == CropStage.FLOWER || stage == CropStage.GROWN;
	}

	@Override
	public List<ItemStack> getCropItems(BlockState state, int fortune) {
		ItemStack ret = new ItemStack(FoodInit.CROP_MY_CLOVE.get());
		return ImmutableList.of(ret);
	}

}
