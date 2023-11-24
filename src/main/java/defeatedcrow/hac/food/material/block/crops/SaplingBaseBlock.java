package defeatedcrow.hac.food.material.block.crops;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import defeatedcrow.hac.api.crop.CropGrowType;
import defeatedcrow.hac.api.crop.CropStage;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.IClimateCrop;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.json.JsonModelDC;
import defeatedcrow.hac.core.json.JsonModelSimpleDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
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
import net.minecraftforge.common.PlantType;

public abstract class SaplingBaseBlock extends ClimateCropBaseBlock {

	public SaplingBaseBlock(CropTier t) {
		super(t);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> def) {

	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext col) {
		return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D);
	}

	@Override
	public PlantType getPlantType(BlockGetter level, BlockPos pos) {
		return net.minecraftforge.common.PlantType.PLAINS;
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
		if (isFarmland(under)) {
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

	@Override
	public boolean isCollectable(ItemStack item) {
		return false;
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
	public List<String> getModelNameSuffix() {
		return Lists.newArrayList();
	}

	@Override
	public JsonModelSimpleDC getItemModel() {
		return new JsonModelDC("minecraft:item/generated", ImmutableMap.of("layer0", "dcs_climate:item/crop/seed_" + getFamily().toString() + "_" + getSpeciesName(cropTier)));
	}

	/* ICropData */

	@Override
	public CropGrowType getGrowType(CropTier t) {
		return CropGrowType.SINGLE;
	}

	@Override
	public ItemLike getSeedItem(CropTier t) {
		return this;
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
	}

	protected abstract void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t);

	protected static boolean replaceCheck(Level level, BlockPos pos, int height) {
		for (int i = 1; i < height; i++) {
			if (!level.getBlockState(pos.above(i)).getMaterial().isReplaceable() && !level.getBlockState(pos.above(i)).is(BlockTags.LEAVES))
				return true;
		}
		return false;
	}

	public static void growSmallTree(Level level, BlockPos pos, int h, BlockState log, BlockState leaves) {
		// 幹
		for (int i = 0; i < h; i++) {
			level.setBlock(pos.above(i), log, 2);
		}

		// 葉
		level.setBlock(pos.above(h), leaves, 2);
		for (int j = 1; j < h; j++) {
			for (int k = -1; k <= 1; k++) {
				for (int l = -1; l <= 1; l++) {
					BlockPos p1 = pos.offset(k, j, l);
					BlockPos p2 = pos.offset(0, j, 0);

					if (p1.equals(p2))
						continue;

					int dist = Math.min(6, Mth.floor(p1.distSqr(p2)));
					placeLeaves(level, p1, dist, leaves);

				}
			}
		}

	}

	public static void growTree(Level level, BlockPos pos, int h, BlockState log, BlockState leaves) {
		// 幹
		for (int i = 0; i < h; i++) {
			level.setBlock(pos.above(i), log, 2);
		}

		// 葉
		level.setBlock(pos.above(h), leaves, 2);
		int h2 = 2;
		for (int j = (h - h2); j < h + 1; j++) {
			for (int k = -2; k <= 2; k++) {
				for (int l = -2; l <= 2; l++) {
					double lim = 6D;
					if (j == h) {
						lim = 3D;
					}
					BlockPos p1 = pos.offset(k, j, l);
					BlockPos p2 = pos.offset(0, j, 0);

					if (p1.equals(p2) || p1.distSqr(p2) > lim)
						continue;

					int dist = Math.min(6, Mth.floor(p1.distSqr(p2)));
					placeLeaves(level, p1, dist, leaves);

				}
			}
		}

	}

	// ねじれ型 6+
	public static void growBigTree(Level level, BlockPos pos, int h, BlockState log, BlockState leaves) {
		int h1 = h - 3 + level.random.nextInt(2);
		int h2 = h - 4 + level.random.nextInt(2);
		int h3 = h - 5 + level.random.nextInt(2);
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
		if (h > 6) {
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

		if (h > 5) {
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

		if (h > 4) {
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
		if (h5 > 6) {
			h5 += 2;
		}
		for (int j = h5; j < h + 2; j++) {
			for (int k = -3; k <= 3; k++) {
				for (int l = -3; l <= 3; l++) {
					double lim = 5D + level.random.nextInt(3);
					if (j == h5 || j == h + 1) {
						lim = 4D;
					}

					BlockPos p1 = pos.offset(k, j, l);
					BlockPos p2 = pos.offset(0, j, 0);

					if (p1.distSqr(p2) < lim) {
						int dist = Math.min(6, Mth.floor(p1.distSqr(p2)));
						placeLeaves(level, p1, dist, leaves);
					}
				}
			}
		}

		for (BlockPos pl : leavesPos) {
			for (int j = -1; j < 4; j++) {
				for (int k = -3; k <= 3; k++) {
					for (int l = -3; l <= 3; l++) {
						double lim = 5D;
						if (j == -1 || j == 3) {
							lim = 2D;
						}

						BlockPos p1 = pl.offset(k, j, l);
						BlockPos p2 = pl.offset(0, j, 0);

						if (p1.distSqr(p2) < lim) {
							int dist = Math.min(6, Mth.floor(p1.distSqr(p2)));
							placeLeaves(level, p1, dist, leaves);
						}
					}
				}
			}
		}
	}

	// 笠形 10+
	public static void growBigTree2(Level level, BlockPos pos, int h, BlockState log, BlockState leaves) {
		int b = 7;
		int h1 = h - b + level.random.nextInt(3);
		int h2 = h - b + level.random.nextInt(3);
		int h3 = h - b + 2 + level.random.nextInt(3);
		int h4 = h - b + 2 + level.random.nextInt(3);
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
		for (int j = 5; j < h + 2; j++) {
			for (int k = -3; k <= 3; k++) {
				for (int l = -3; l <= 3; l++) {
					double lim = 6D + level.random.nextInt(3);
					if (j == 5 || j == h) {
						lim = 4D;
					}
					if (j == h + 1) {
						lim = 3D;
					}

					BlockPos p1 = pos.offset(k, j, l);
					BlockPos p2 = pos.offset(0, j, 0);

					if (p1.distSqr(p2) < lim) {
						int dist = Math.min(6, Mth.floor(p1.distSqr(p2)));
						placeLeaves(level, p1, dist, leaves);
					}
				}
			}
		}

		for (BlockPos pl : leavesPos) {
			for (int j = -2; j < 4; j++) {
				for (int k = -3; k <= 3; k++) {
					for (int l = -3; l <= 3; l++) {
						double lim = 6D;
						if (j == -2 || j > 2) {
							lim = 2D;
						}

						BlockPos p1 = pl.offset(k, j, l);
						BlockPos p2 = pl.offset(0, j, 0);

						if (p1.distSqr(p2) < lim) {
							int dist = Math.min(6, Mth.floor(p1.distSqr(p2)));
							placeLeaves(level, p1, dist, leaves);
						}
					}
				}
			}
		}
	}

	private static void placeLeaves(Level level, BlockPos pos, int dist, BlockState leaves) {
		if (level.getBlockState(pos).getBlock() == Blocks.AIR) {
			level.setBlock(pos, DCState.setInt(leaves, DCState.DIST, dist), 2);
		}
	}

}
