package defeatedcrow.hac.food.material.block;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropStage;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SaplingBeech extends ClimateCropBaseBlock {

	public SaplingBeech(CropTier t) {
		super(t);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {

	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext col) {
		return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	}

	/* 苗木なのでコレ自体は成長しない */

	@Override
	public CropStage getCurrentStage(BlockState state) {
		return CropStage.SAPLING;
	}

	@Override
	public BlockState getFeatureState() {
		return this.defaultBlockState();
	}

	@Override
	public BlockState getGrownState() {
		return this.defaultBlockState();
	}

	@Override
	public int getGrowingChance(Level world, BlockPos pos, BlockState thisState) {
		boolean clm = isSuitableForGrowing(world, pos, thisState);
		int ret = clm ? 8 : 50;
		BlockState under = world.getBlockState(pos.below());
		if (getFertile(world, pos.below(), under) > 5) {
			ret /= 2;
		}
		return ret;
	}

	@Override
	public boolean onGrow(Level world, BlockPos pos, BlockState state) {
		if (state.getBlock() instanceof IClimateCrop)
			onGrowingTree(world, pos, state, ((IClimateCrop) state.getBlock()).getTier());
		return true;
	}

	@Override
	public boolean canHarvest(BlockState thisState) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> ret = Lists.newArrayList();
		ret.add(new ItemStack(this));
		return ret;
	}

	/* model */

	@Override
	public String getRegistryName() {
		return "food/sapling_" + getFamily().toString() + "_" + cropTier.toString();
	}

	@Override
	public List<JsonModelDC> getBlockModel() {
		return ImmutableList.of(
			new JsonModelDC("dcs_climate:block/dcs_cross", ImmutableMap.of("cross", "dcs_climate:block/tree/sapling_" + getFamily().toString() + "_" + getSpeciesName(cropTier))));
	}

	@Override
	public Optional<String[]> getModelNameSuffix() {
		return Optional.empty();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_" + getFamily().toString() + "_" + getSpeciesName(cropTier)));
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.BEECH;
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
			return FoodInit.CROP_BH_WALNUT.get();
		case RARE:
			return FoodInit.CROP_BH_SWEET.get();
		default:
			return FoodInit.CROP_BH_COMMON.get();
		}
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_BH_COMMON.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_BH_WALNUT.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_BH_SWEET.get());
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
			return ImmutableList.of(DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
		} else if (t == CropTier.RARE) {
			return ImmutableList.of(DCHeatTier.CRYOGENIC, DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL);
		} else {
			return ImmutableList.of(DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
		}
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
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "walnut";
		if (tier == CropTier.RARE)
			return "sweet";
		return "common";
	}

	protected void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t) {
		// 高さ6~10
		level.random.nextInt(2);
		int h = 6 + level.random.nextInt(5);
		BlockState log = FoodInit.LOG_BH_COMMON.get().defaultBlockState();
		BlockState leaves = FoodInit.LEAVES_BH_COMMON.get().defaultBlockState().setValue(DCState.FLAG, true);
		if (t == CropTier.COMMON) {
			log = FoodInit.LOG_BH_WALNUT.get().defaultBlockState();
			leaves = FoodInit.LEAVES_BH_WALNUT.get().defaultBlockState().setValue(DCState.FLAG, true);
		} else if (t == CropTier.RARE) {
			log = FoodInit.LOG_BH_SWEET.get().defaultBlockState();
			leaves = FoodInit.LEAVES_BH_SWEET.get().defaultBlockState().setValue(DCState.FLAG, true);
		}

		int m = ((LeavesCropBlockDC) leaves.getBlock()).getSeasonLeafStage(level, pos);
		leaves = leaves.setValue(DCState.STAGE6, m);

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;

		if (!level.isClientSide) {
			for (int i = 1; i < h; i++) {
				if (!level.getBlockState(pos.above(i)).getMaterial().isReplaceable())
					return;
			}

			if (h > 10) {
				SaplingBeech.growBigTree2(level, pos, h, log, leaves);
			} else if (h > 6) {
				SaplingBeech.growBigTree(level, pos, h, log, leaves);
			} else {

				// 幹
				for (int i = 0; i < h; i++) {
					level.setBlock(pos.above(i), log, 2);
				}

				// 葉
				level.setBlock(pos.above(h), leaves, 2);
				int h2 = Mth.floor(h / 2F);
				for (int j = h2; j < h + 1; j++) {
					for (int k = -2; k <= 2; k++) {
						for (int l = -2; l <= 2; l++) {
							double lim = 5D + level.random.nextInt(3);
							if (j == h2 || j == h) {
								lim = 4D;
							}
							BlockPos p1 = pos.offset(k, j, l);
							BlockPos p2 = pos.offset(0, j, 0);

							if (p1.distSqr(p2) > lim)
								continue;

							if (level.getBlockState(p1).getBlock() == Blocks.AIR) {
								level.setBlock(p1, leaves, 2);
							}
						}
					}
				}
			}
		}
	}

	// ねじれ型
	public static void growBigTree(Level level, BlockPos pos, int h, BlockState log, BlockState leaves) {
		int h1 = 5 + level.random.nextInt(3);
		int h2 = 4 + level.random.nextInt(3);
		int h3 = 3 + level.random.nextInt(3);
		Direction d1 = Direction.Plane.HORIZONTAL.getRandomDirection(level.random);
		Direction d2 = Direction.Plane.HORIZONTAL.getRandomDirection(level.random);
		Direction d3 = Direction.Plane.HORIZONTAL.getRandomDirection(level.random);
		if (d2 == d1)
			d2 = d1.getClockWise();
		if (d3 == d1 || d3 == d2)
			d3 = (d2 == d1.getOpposite()) ? d1.getClockWise() : d1.getOpposite();
		List<BlockPos> leavesPos = Lists.newArrayList();

		// 幹
		for (int i = 0; i < h; i++) {
			level.setBlock(pos.above(i), log, 2);
		}

		MutableBlockPos mpos = new BlockPos.MutableBlockPos();
		// 枝
		if (h > 8) {
			int ax1 = d1.getStepX();
			int az1 = d1.getStepZ();
			int ax2 = d1.getClockWise().getStepX();
			int az2 = d1.getClockWise().getStepZ();
			BlockState log2 = d1.getAxis() == Direction.Axis.X ? log.setValue(BlockStateProperties.AXIS, Direction.Axis.X) :
					log.setValue(BlockStateProperties.AXIS, Direction.Axis.Z);
			level.setBlock(mpos.setWithOffset(pos, ax1, h1, az1), log2, 2);
			level.setBlock(mpos.setWithOffset(pos, ax1 * 2, h1, az1 * 2), log2, 2);
			level.setBlock(mpos.setWithOffset(pos, ax1 * 2, h1 + 1, az1 * 2), log2, 2);
			level.setBlock(mpos.setWithOffset(pos, (ax1 * 2) + ax2, h1 + 1, (az1 * 2) + az2), log, 2);
			level.setBlock(mpos.setWithOffset(pos, (ax1 * 2) + ax2, h1 + 2, (az1 * 2) + az2), log, 2);
			leavesPos.add(pos.offset(ax1 * 2, h1, az1 * 2));
		}

		if (h > 6) {
			int ax1 = d2.getStepX();
			int az1 = d2.getStepZ();
			int ax2 = d2.getClockWise().getStepX();
			int az2 = d2.getClockWise().getStepZ();
			BlockState log2 = d2.getAxis() == Direction.Axis.X ? log.setValue(BlockStateProperties.AXIS, Direction.Axis.X) :
					log.setValue(BlockStateProperties.AXIS, Direction.Axis.Z);
			level.setBlock(mpos.setWithOffset(pos, ax1, h2, az1), log2, 2);
			level.setBlock(mpos.setWithOffset(pos, ax1 * 2, h2, az1 * 2), log2, 2);
			level.setBlock(mpos.setWithOffset(pos, ax1 * 2, h2 + 1, az1 * 2), log2, 2);
			level.setBlock(mpos.setWithOffset(pos, (ax1 * 2) + ax2, h2 + 1, (az1 * 2) + az2), log, 2);
			level.setBlock(mpos.setWithOffset(pos, (ax1 * 2) + ax2, h2 + 2, (az1 * 2) + az2), log, 2);
			leavesPos.add(pos.offset(ax1 * 2, h2, az1 * 2));
		}

		if (h > 5) {
			int ax1 = d3.getStepX();
			int az1 = d3.getStepZ();
			int ax2 = d3.getClockWise().getStepX();
			int az2 = d3.getClockWise().getStepZ();
			BlockState log2 = d3.getAxis() == Direction.Axis.X ? log.setValue(BlockStateProperties.AXIS, Direction.Axis.X) :
					log.setValue(BlockStateProperties.AXIS, Direction.Axis.Z);
			level.setBlock(mpos.setWithOffset(pos, ax1, h3, az1), log2, 2);
			level.setBlock(mpos.setWithOffset(pos, ax1 * 2, h3, az1 * 2), log2, 2);
			level.setBlock(mpos.setWithOffset(pos, ax1 * 2, h3 + 1, az1 * 2), log2, 2);
			level.setBlock(mpos.setWithOffset(pos, (ax1 * 2) + ax2, h3 + 1, (az1 * 2) + az2), log, 2);
			level.setBlock(mpos.setWithOffset(pos, (ax1 * 2) + ax2, h3 + 2, (az1 * 2) + az2), log, 2);
			leavesPos.add(pos.offset(ax1 * 2, h3, az1 * 2));
		}

		// 葉
		level.setBlock(pos.above(h), leaves, 2);
		int h5 = Mth.floor(h / 2F);
		for (int j = h5 + 2; j < h + 2; j++) {
			for (int k = -3; k <= 3; k++) {
				for (int l = -3; l <= 3; l++) {
					double lim = 5D + level.random.nextInt(3);
					if (j == h5 + 2 || j == h + 1) {
						lim = 4D;
					}

					BlockPos p1 = pos.offset(k, j, l);
					BlockPos p2 = pos.offset(0, j, 0);

					if (p1.distSqr(p2) < lim) {
						if (level.getBlockState(p1).getBlock() == Blocks.AIR) {
							level.setBlock(p1, leaves, 2);
						}
					}
				}
			}
		}

		for (BlockPos pl : leavesPos) {
			for (int j = 0; j < 5; j++) {
				for (int k = -3; k <= 3; k++) {
					for (int l = -3; l <= 3; l++) {
						double lim = 5D;
						if (j == 0 || j == 4) {
							lim = 2D;
						}

						BlockPos p1 = pl.offset(k, j, l);
						BlockPos p2 = pl.offset(0, j, 0);

						if (p1.distSqr(p2) < lim) {
							if (level.getBlockState(p1).getBlock() == Blocks.AIR) {
								level.setBlock(p1, leaves, 2);
							}
						}
					}
				}
			}
		}
	}

	// 笠形 10+
	public static void growBigTree2(Level level, BlockPos pos, int h, BlockState log, BlockState leaves) {
		int h1 = 6 + level.random.nextInt(3);
		int h2 = 6 + level.random.nextInt(3);
		int h3 = 8 + level.random.nextInt(3);
		int h4 = 8 + level.random.nextInt(3);
		List<BlockPos> leavesPos = Lists.newArrayList();
		BlockState logX = log.setValue(BlockStateProperties.AXIS, Direction.Axis.X);
		BlockState logZ = log.setValue(BlockStateProperties.AXIS, Direction.Axis.Z);

		// 幹
		for (int i = 0; i < h; i++) {
			level.setBlock(pos.above(i), log, 2);
		}

		MutableBlockPos mpos = new BlockPos.MutableBlockPos();
		// 枝

		if (h > 12) {

			level.setBlock(mpos.setWithOffset(pos, 1, h1, 0), logX, 2);
			level.setBlock(mpos.setWithOffset(pos, -1, h1, 0), logX, 2);
			level.setBlock(mpos.setWithOffset(pos, 2, h1, 0), logX, 2);
			level.setBlock(mpos.setWithOffset(pos, -2, h1, 0), logX, 2);
			level.setBlock(mpos.setWithOffset(pos, 3, h1, 0), logX, 2);
			level.setBlock(mpos.setWithOffset(pos, -3, h1, 0), logX, 2);
			level.setBlock(mpos.setWithOffset(pos, 0, h2, 1), logZ, 2);
			level.setBlock(mpos.setWithOffset(pos, 0, h2, -1), logZ, 2);
			level.setBlock(mpos.setWithOffset(pos, 0, h2, 2), logZ, 2);
			level.setBlock(mpos.setWithOffset(pos, 0, h2, -2), logZ, 2);
			level.setBlock(mpos.setWithOffset(pos, 0, h2, 3), logZ, 2);
			level.setBlock(mpos.setWithOffset(pos, 0, h2, -3), logZ, 2);
			level.setBlock(mpos.setWithOffset(pos, 1, h3, 0), logX, 2);
			level.setBlock(mpos.setWithOffset(pos, 2, h3, 0), logX, 2);
			level.setBlock(mpos.setWithOffset(pos, 0, h4, -1), logZ, 2);
			level.setBlock(mpos.setWithOffset(pos, 0, h4, -2), logZ, 2);

			leavesPos.add(pos.offset(3, h1, 0));
			leavesPos.add(pos.offset(-3, h1, 0));
			leavesPos.add(pos.offset(0, h2, 3));
			leavesPos.add(pos.offset(0, h2, -3));
			leavesPos.add(pos.offset(2, h3, 0));
			leavesPos.add(pos.offset(0, h4, -2));

		} else {

			level.setBlock(mpos.setWithOffset(pos, 1, h1, 0), logX, 2);
			level.setBlock(mpos.setWithOffset(pos, 2, h1, 0), logX, 2);
			level.setBlock(mpos.setWithOffset(pos, 0, h2, 1), logZ, 2);
			level.setBlock(mpos.setWithOffset(pos, 0, h2, 2), logZ, 2);
			level.setBlock(mpos.setWithOffset(pos, -1, h3 - 2, 0), logX, 2);
			level.setBlock(mpos.setWithOffset(pos, 0, h4 - 2, -1), logZ, 2);

			leavesPos.add(pos.offset(2, h1, 0));
			leavesPos.add(pos.offset(0, h2, 2));
			leavesPos.add(pos.offset(-1, h3 - 2, 0));
			leavesPos.add(pos.offset(0, h4 - 2, -1));
		}

		// 葉
		level.setBlock(pos.above(h), leaves, 2);
		for (int j = 6; j < h + 2; j++) {
			for (int k = -3; k <= 3; k++) {
				for (int l = -3; l <= 3; l++) {
					double lim = 5D + level.random.nextInt(3);
					if (j == 4 || j == h + 1) {
						lim = 4D;
					}

					BlockPos p1 = pos.offset(k, j, l);
					BlockPos p2 = pos.offset(0, j, 0);

					if (p1.distSqr(p2) < lim) {
						if (level.getBlockState(p1).getBlock() == Blocks.AIR) {
							level.setBlock(p1, leaves, 2);
						}
					}
				}
			}
		}

		for (BlockPos pl : leavesPos) {
			for (int j = -2; j < 4; j++) {
				for (int k = -3; k <= 3; k++) {
					for (int l = -3; l <= 3; l++) {
						double lim = 6D;
						if (j == -2 || j == 3) {
							lim = 2D;
						}

						BlockPos p1 = pl.offset(k, j, l);
						BlockPos p2 = pl.offset(0, j, 0);

						if (p1.distSqr(p2) < lim) {
							if (level.getBlockState(p1).getBlock() == Blocks.AIR) {
								level.setBlock(p1, leaves, 2);
							}
						}
					}
				}
			}
		}
	}

}
