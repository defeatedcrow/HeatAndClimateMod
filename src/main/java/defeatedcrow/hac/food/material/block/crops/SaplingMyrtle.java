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

public class SaplingMyrtle extends SaplingBaseBlock {

	public SaplingMyrtle(CropTier t) {
		super(t);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.MYRTLE;
	}

	@Override
	public Item getCropItem(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.CROP_MY_GUAVA.get();
		case RARE -> FoodInit.CROP_MY_CLOVE.get();
		default -> Item.BY_BLOCK.get(FoodInit.BLOCK_MY_EUCALYPTUS.get());
		};
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		return switch (t) {
		case WILD -> Optional.of(FoodInit.BLOCK_MY_EUCALYPTUS.get());
		case COMMON -> Optional.of(FoodInit.BLOCK_MY_GUAVA.get());
		case RARE -> Optional.of(FoodInit.BLOCK_MY_CLOVE.get());
		default -> Optional.empty();
		};
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.MUD, SoilType.SAND);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		if (t == CropTier.RARE) {
			return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT);
		}
		return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		if (t == CropTier.WILD)
			return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL, DCHumidity.WET);
		return ImmutableList.of(DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		return switch (t) {
		case WILD -> ImmutableList.of("SAVANNA, RIVER, SWAMP");
		case COMMON -> ImmutableList.of("JUNGLE");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		return switch (t) {
		case WILD, COMMON -> ImmutableList.of("SNOWY", "COLD");
		default -> Lists.newArrayList();
		};
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "guava";
		if (tier == CropTier.RARE)
			return "clove";
		return "eucalyptus";
	}

	@Override
	protected BlockState getLogState(CropTier t) {
		return FoodInit.LOG_MY_EUCALYPTUS.get()
		    .defaultBlockState()
		    .setValue(DCState.WILD, true);
	}

	@Override
	protected BlockState getLeavesState(CropTier t) {
		return switch (t) {
		case COMMON -> FoodInit.LEAVES_MY_GUAVA.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		case RARE -> FoodInit.LEAVES_MY_CLOVE.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		default -> FoodInit.LEAVES_MY_EUCALYPTUS.get()
		    .defaultBlockState()
		    .setValue(DCState.FLAG, true);
		};
	}

	@Override
	protected void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t) {
		// 各種サイズが違う
		level.random.nextInt(5);
		int h = 10 + level.random.nextInt(5);
		int r = 4;
		BlockState log = getLogState(t);
		BlockState leaves = getLeavesState(t);
		if (t == CropTier.COMMON) {
			h = 4 + level.random.nextInt(2);
		} else if (t == CropTier.RARE) {
			h = 4 + level.random.nextInt(2);
		}

		int m = ((LeavesCropBlockDC) leaves.getBlock()).getSeasonLeafStage(level, pos, leaves);
		leaves = leaves.setValue(DCState.STAGE6, m);

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;

		if (!level.isClientSide) {
			if (replaceCheck(level, pos, h))
				return;

			if (h > 8) {
				growBigTree2(level, pos, h, log, leaves);
			} else if (h > 5) {
				growBigTree(level, pos, h, log, leaves);
			} else {
				growTree(level, pos, h, log, leaves);
			}

		}
	}

}
