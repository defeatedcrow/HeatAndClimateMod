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
import defeatedcrow.hac.api.crop.CropStage;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class LeavesHeath extends LeavesCropBlockDC {

	public LeavesHeath() {
		super(CropType.ERICA, CropTier.WILD, false);
		this.setSeason(EnumSeason.SPRING_EARLY, EnumSeason.SPRING_LATE);
		this.flowerSeasons.add(EnumSeason.AUTUMN_LATE);
		this.flowerSeasons.add(EnumSeason.WINTER_EARLY);
		this.flowerSeasons.add(EnumSeason.WINTER_LATE);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/leaves_erica_heath";
	}

	@Override
	protected void checkAndDropBlock(Level world, BlockPos pos, BlockState state) {
		// 自然消滅なし
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.ERICA;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.LEAVES;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return FoodInit.BLOCK_ER_HEATH.get();
	}

	@Override
	public Item getCropItem(CropTier t) {
		return FoodInit.CROP_ER_HEATH.get();
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_ER_HEATH.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_ER_RHODODENDRON.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_ER_BLUEBERRY.get());
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
		return ImmutableList.of(DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
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
		switch (t) {
		case WILD:
			return ImmutableList.of("CONIFEROUS", "COLD", "MOUNTAIN");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		switch (t) {
		case WILD:
			return ImmutableList.of("HOT", "OCEAN");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		return "heath";
	}

	/* 花の収穫ができる */
	@Override
	public boolean canHarvest(BlockState thisState) {
		CropStage stage = this.getCurrentStage(thisState);
		return stage == CropStage.FLOWER || stage == CropStage.GROWN;
	}

	@Override
	public List<ItemStack> getCropItems(BlockState state, int fortune) {
		CropStage stage = this.getCurrentStage(state);
		if (stage == CropStage.FLOWER) {
			ItemStack ret = new ItemStack(FoodInit.CROP_ER_HEATH.get());
			return ImmutableList.of(ret);
		} else {
			ItemStack ret = new ItemStack(getSeedItem(getTier()));
			return ImmutableList.of(ret);
		}
	}

}
