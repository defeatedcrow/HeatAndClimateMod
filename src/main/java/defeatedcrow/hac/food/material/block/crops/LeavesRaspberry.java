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
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class LeavesRaspberry extends LeavesCropBlockDC {

	public LeavesRaspberry() {
		super(CropType.ROSE, CropTier.COMMON, false);
		this.setSeason(EnumSeason.SUMMER_LATE, EnumSeason.AUTUMN_EARLY);
		this.cropSeasons.add(EnumSeason.AUTUMN_LATE);
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/leaves_rose_raspberry";
	}

	@Override
	protected void checkAndDropBlock(Level world, BlockPos pos, BlockState state) {
		// 自然消滅なし
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.ROSE;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.LEAVES;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return FoodInit.BLOCK_RO_RASPBERRY.get();
	}

	@Override
	public Item getCropItem(CropTier t) {
		return FoodInit.CROP_RO_RASPBERRY.get();
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_RO_RUGOSA.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_RO_RASPBERRY.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_RO_DAMASCHENA.get());
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
		return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		switch (t) {
		case WILD:
			return ImmutableList.of("OCEAN", "BEACH", "PLAINS");
		case COMMON:
			return ImmutableList.of("FOREST", "MOUNTAIN");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		switch (t) {
		case WILD:
			return ImmutableList.of("HOT");
		case COMMON:
			return ImmutableList.of("HOT", "LOWLAND");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		return "raspberry";
	}

}
