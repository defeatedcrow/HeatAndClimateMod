package defeatedcrow.hac.food.material.block.crops;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SaplingPalm extends SaplingBaseBlock {

	public SaplingPalm(CropTier t) {
		super(t);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.PALM;
	}

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.SINGLE;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return this;
	}

	@Override
	public Item getCropItem(CropTier t) {
		switch (t) {
		case COMMON:
			return FoodInit.CROP_PL_DATE.get();
		case RARE:
			return FoodInit.CROP_PL_OIL.get();
		default:
			return FoodInit.CROP_PL_COCONUT.get();
		}
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_PL_COCONUT.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_PL_DATE.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_PL_OIL.get());
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
		switch (t) {
		case WILD:
			return ImmutableList.of("OCEAN", "BEACH", "JUNGLE");
		case COMMON:
			return ImmutableList.of("DESERT", "SAVANNA");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		switch (t) {
		case WILD, COMMON:
			return ImmutableList.of("COLD", "RIVER");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "date";
		if (tier == CropTier.RARE)
			return "oil";
		return "coconut";
	}

	@Override
	protected void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t) {
		level.random.nextInt(4);
		int h = 3 + level.random.nextInt(4);
		BlockState log = FoodInit.LOG_PL_COCONUT.get().defaultBlockState();
		BlockState leaves = FoodInit.LEAVES_PL_COMMON.get().defaultBlockState().setValue(DCState.FLAG, true);
		BlockState crop = FoodInit.CROPBLOCK_PL_COCONUT.get().defaultBlockState();
		if (t == CropTier.COMMON) {
			crop = FoodInit.CROPBLOCK_PL_DATE.get().defaultBlockState();
		} else if (t == CropTier.RARE) {
			crop = FoodInit.CROPBLOCK_PL_OIL.get().defaultBlockState();
		}

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;

		if (!level.isClientSide) {
			if (replaceCheck(level, pos, h))
				return;

			// 幹
			for (int i = 1; i <= h; i++) {
				DCUtil.setBlockIfReplaceable(level, pos.above(i), log, true);
			}
			level.setBlock(pos, log, 2);

			DCUtil.setBlockIfReplaceable(level, pos.offset(0, h + 1, 0), leaves, true);
			DCUtil.setBlockIfReplaceable(level, pos.offset(0, h + 2, 0), leaves, true);
			DCUtil.setBlockIfReplaceable(level, pos.offset(0, h + 3, 0), leaves, true);

			for (Direction dir : Direction.Plane.HORIZONTAL) {

				DCUtil.setBlockIfReplaceable(level, pos.offset(dir.getStepX(), h + 1, dir.getStepZ()), leaves, true);
				DCUtil.setBlockIfReplaceable(level, pos.offset(dir.getStepX() * 2, h + 2, dir.getStepZ() * 2), leaves, true);
				DCUtil.setBlockIfReplaceable(level, pos.offset(dir.getStepX() * 3, h + 2, dir.getStepZ() * 3), leaves, true);
				DCUtil.setBlockIfReplaceable(level, pos.offset(dir.getStepX() * 4, h + 1, dir.getStepZ() * 4), leaves, true);

				DCUtil.setBlockIfReplaceable(level, pos.offset(dir.getStepX() + dir.getClockWise().getStepX(), h + 2, dir.getStepZ() + dir.getClockWise().getStepZ()), leaves, true);
				DCUtil.setBlockIfReplaceable(level, pos.offset(dir.getStepX() * 2 + dir.getClockWise().getStepX() * 2, h + 3, dir.getStepZ() * 2 + dir.getClockWise().getStepZ() * 2), leaves, true);

				DCUtil.setBlockIfReplaceable(level, pos.offset(dir.getStepX(), h, dir.getStepZ()), crop, true);
			}
		}
	}

}
