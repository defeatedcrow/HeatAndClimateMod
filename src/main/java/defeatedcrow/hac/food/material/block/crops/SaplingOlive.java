package defeatedcrow.hac.food.material.block.crops;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SaplingOlive extends SaplingBaseBlock {

	public SaplingOlive(CropTier t) {
		super(t);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.OLIVE;
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_OL_OLIVE.get();
		case RARE -> FoodInit.CROP_OL_OSMANTHUS.get();
		case EPIC -> FoodInit.CROP_OL_JASMINE.get();
		default -> FoodInit.CROP_OL_ASH.get();
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_OL_ASH.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_OL_OLIVE.get());
		case RARE -> Optional.of(FoodInit.BLOCK_OL_OSMANTHUS.get());
		case EPIC -> Optional.of(FoodInit.BLOCK_OL_JASMINE.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		if (t == CropTier.EPIC) {
			return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
		}
		return ImmutableList.of(DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		if (t == CropTier.EPIC) {
			return ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET);
		}
		return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("FOREST", "COLD", "CONIFEROUS");
		case COMMON -> ImmutableList.of("DRY", "PLAINS");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("WET", "HOT");
		case COMMON -> ImmutableList.of("WET", "COLD");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "olive";
		if (tier == CropTier.RARE)
			return "osmanthus";
		if (tier == CropTier.EPIC)
			return "jasmine";
		return "ash";
	}

	@Override
	protected BlockState getLogState(CropTier t) {
		return FoodInit.LOG_OL_ASH.get()
		    .defaultBlockState()
		    .setValue(DCState.WILD, true);
	}

	@Override
	protected BlockState getLeavesState(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.LEAVES_OL_OLIVE.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		case RARE -> FoodInit.LEAVES_OL_OSMANTHUS.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		case EPIC -> FoodInit.LEAVES_OL_JASMINE.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		default -> FoodInit.LEAVES_OL_ASH.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		};
	}

	@Override
	protected void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t) {
		// 各種サイズが違う
		level.random.nextInt(5);
		int h = 8 + level.random.nextInt(5);
		int r = 4;
		BlockState log = getLogState(t);
		BlockState leaves = getLeavesState(t);
		if (t == CropTier.COMMON) {
			h = 5 + level.random.nextInt(4);
			r = 3;
		} else if (t == CropTier.RARE) {
			h = 3 + level.random.nextInt(3);
			r = 2;
		} else if (t == CropTier.EPIC) {
			h = 1 + level.random.nextInt(3);
			r = 2;
		}

		int m = ((LeavesCropBlockDC) leaves.getBlock()).getSeasonLeafStage(level, pos, leaves);
		leaves = leaves.setValue(DCState.STAGE6, m);

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;

		if (!level.isClientSide) {
			if (replaceCheck(level, pos, h))
				return;

			if (t == CropTier.EPIC) {
				growSmallTree(level, pos, h, log, leaves);
			} else if (h > 8) {
				SaplingBeech.growBigTree2(level, pos, h, log, leaves);
			} else if (h > 5) {
				SaplingBeech.growBigTree(level, pos, h, log, leaves);
			} else {
				growTree(level, pos, h, log, leaves);
			}

		}
	}

}
