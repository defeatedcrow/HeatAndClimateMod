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

public class SaplingSumac extends SaplingBaseBlock {

	public SaplingSumac(CropTier t) {
		super(t);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.SUMAC;
	}

	@Override
	public Item getCropItem(CropTier t) {
		switch (t) {
		case COMMON:
			return FoodInit.CROP_SU_MANGO.get();
		case RARE:
			return FoodInit.CROP_SU_CASHEW.get();
		case EPIC:
			return FoodInit.CROP_SU_PISTACHIO.get();
		default:
			return FoodInit.CROP_SU_LACQUER.get();
		}
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
		if (t == CropTier.EPIC) {
			return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT, SoilType.SAND);
		} else {
			return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
		}
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		if (t == CropTier.WILD) {
			return ImmutableList.of(DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
		} else if (t == CropTier.EPIC) {
			return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
		} else {
			return ImmutableList.of(DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT, DCHeatTier.BOIL);
		}
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		if (t == CropTier.RARE || t == CropTier.EPIC) {
			return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL);
		} else {
			return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL, DCHumidity.WET);
		}
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		switch (t) {
		case WILD:
			return ImmutableList.of("SWAMP", "FOREST");
		case COMMON:
			return ImmutableList.of("JUNGLE");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		switch (t) {
		case WILD, COMMON:
			return ImmutableList.of("CONIFEROUS", "COLD");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "mango";
		if (tier == CropTier.RARE)
			return "cashew";
		if (tier == CropTier.EPIC)
			return "pistachio";
		return "lacquer";
	}

	@Override
	protected void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t) {
		// 各種サイズが違う
		level.random.nextInt(4);
		int h = 6 + level.random.nextInt(8);
		int r = 4;
		BlockState log = FoodInit.LOG_SU_MANGO.get().defaultBlockState();
		BlockState leaves = FoodInit.LEAVES_SU_MANGO.get().defaultBlockState().setValue(DCState.FLAG, true);
		if (t == CropTier.WILD) {
			log = FoodInit.LOG_SU_LACQUER.get().defaultBlockState();
			leaves = FoodInit.LEAVES_SU_LACQUER.get().defaultBlockState().setValue(DCState.FLAG, true);
			h = 5 + level.random.nextInt(4);
		} else if (t == CropTier.RARE) {
			leaves = FoodInit.LEAVES_SU_CASHEW.get().defaultBlockState().setValue(DCState.FLAG, true);
			h = 5 + level.random.nextInt(4);
		} else if (t == CropTier.RARE) {
			leaves = FoodInit.LEAVES_SU_PISTACHIO.get().defaultBlockState().setValue(DCState.FLAG, true);
			h = 4 + level.random.nextInt(4);
		}

		int m = ((LeavesCropBlockDC) leaves.getBlock()).getSeasonLeafStage(level, pos, leaves);
		leaves = leaves.setValue(DCState.STAGE6, m);

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;

		if (!level.isClientSide) {
			if (replaceCheck(level, pos, h))
				return;

			if (h > 9) {
				SaplingBeech.growBigTree2(level, pos, h, log, leaves);
			} else if (h > 5) {
				SaplingBeech.growBigTree(level, pos, h, log, leaves);
			} else {
				growTree(level, pos, h, log, leaves);
			}

		}
	}

}
