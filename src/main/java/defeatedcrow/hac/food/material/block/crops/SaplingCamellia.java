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
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class SaplingCamellia extends SaplingBaseBlock {

	public SaplingCamellia(CropTier t) {
		super(t);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.CAMELLIA;
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
			return FoodInit.CROP_CM_SCHIMA.get();
		case RARE:
			return FoodInit.CROP_CM_TEA.get();
		default:
			return FoodInit.CROP_CM_OIL.get();
		}
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_CM_OIL.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_CM_SCHIMA.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_CM_TEA.get());
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
		if (t == CropTier.COMMON) {
			return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT);
		}
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
			return ImmutableList.of("MOUNTAIN", "COLD");
		case COMMON:
			return ImmutableList.of("MOUNTAIN", "JUNGLE");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		switch (t) {
		case WILD:
			return ImmutableList.of("HOT", "DRY", "CONIFEROUS");
		case COMMON:
			return ImmutableList.of("COLD", "CONIFEROUS");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "schima";
		if (tier == CropTier.RARE)
			return "tea";
		return "oil";
	}

	@Override
	protected void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t) {
		level.random.nextInt(2);
		int h = 1 + level.random.nextInt(2);
		int r = 2;
		BlockState log = FoodInit.LOG_BH_COMMON.get().defaultBlockState();
		BlockState leaves = FoodInit.LEAVES_CM_OIL.get().defaultBlockState();
		if (t == CropTier.COMMON) {
			leaves = FoodInit.LEAVES_CM_SCHIMA.get().defaultBlockState().setValue(DCState.FLAG, true);
			h = 2 + level.random.nextInt(3);
		} else if (t == CropTier.RARE) {
			leaves = FoodInit.LEAVES_CM_TEA.get().defaultBlockState();
			h = 1;
		}

		int m = ((LeavesCropBlockDC) leaves.getBlock()).getSeasonLeafStage(level, pos, leaves);
		leaves = leaves.setValue(DCState.STAGE6, m);

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;

		if (!level.isClientSide) {
			if (replaceCheck(level, pos, h))
				return;

			if (t == CropTier.COMMON) {
				// 幹
				for (int i = 0; i < h; i++) {
					level.setBlock(pos.above(i), log, 2);
				}

				// 葉
				level.setBlock(pos.above(h), leaves, 2);
				for (int j = h - 1; j < h + 1; j++) {
					for (int k = -r; k <= r; k++) {
						for (int l = -r; l <= r; l++) {
							double lim = 4D;
							if (j == h) {
								lim = 2D;
							}
							BlockPos p1 = pos.offset(k, j, l);
							BlockPos p2 = pos.offset(0, j, 0);

							if (p1.equals(p2) || p1.distSqr(p2) > lim)
								continue;

							if (level.getBlockState(p1).getBlock() == Blocks.AIR) {
								level.setBlock(p1, leaves, 2);
							}

						}
					}
				}
			} else {
				for (int i = 0; i < h; i++) {
					level.setBlock(pos.above(i), leaves, 2);
				}
			}
		}
	}

}
