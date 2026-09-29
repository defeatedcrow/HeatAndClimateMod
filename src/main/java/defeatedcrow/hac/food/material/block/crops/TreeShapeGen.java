package defeatedcrow.hac.food.material.block.crops;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class TreeShapeGen {

	protected static int replaceCheck(Level level, BlockPos pos, int height) {
		int limit = height;
		for (int i = 1; i <= height; i++) {
			if (!level.getBlockState(pos.above(i)).canBeReplaced() && !level.getBlockState(pos.above(i)).is(BlockTags.LEAVES)) {
				return i - 1;
			}
		}
		return limit;
	}

	// height 1+
	public static void growTreeNoLeaves(Level level, BlockPos pos, int h, BlockState log) {
		if (h < 1)
			return;
		// 幹
		for (int i = 0; i < h; i++) {
			level.setBlock(pos.above(i), log, 2);
		}
	}

	public static void growTreeBigLog(Level level, BlockPos pos, int h, BlockState log) {
		if (h < 1)
			return;
		// 幹周り
		BlockPos p2 = pos;
		for (int i = 0; i < h; i++) {
			for (Direction dir : Direction.Plane.HORIZONTAL) {
				level.setBlock(p2.relative(dir), log, 2);
			}
			p2 = p2.above();
		}
		if (h > 5) {
			p2 = pos;
			int h2 = Mth.floor(h / 2F);
			for (int i = 0; i < h2; i++) {
				level.setBlock(p2.offset(1, 0, 1), log, 2);
				level.setBlock(p2.offset(-1, 0, 1), log, 2);
				level.setBlock(p2.offset(1, 0, -1), log, 2);
				level.setBlock(p2.offset(-1, 0, -1), log, 2);
				p2 = p2.above();
			}
		}
	}

	// height 2+
	public static void growTreeAS(Level level, BlockPos pos, int h, BlockState log, BlockState leaves) {
		if (h < 1)
			return;
		// 幹
		for (int i = 0; i < h; i++) {
			level.setBlock(pos.above(i), log, 2);
		}
		// 葉
		BlockPos p2 = pos.above(h);
		level.setBlock(p2, leaves, 2);
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			level.setBlock(p2.relative(dir), leaves, 2);
		}
		p2 = p2.below();
		for (int k = -1; k <= 1; k++) {
			for (int l = -1; l <= 1; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				placeLeaves(level, p1, dist, leaves);
			}
		}
		if (h > 2) {
			p2 = p2.below();
			for (int k = -2; k <= 2; k++) {
				for (int l = -2; l <= 2; l++) {
					BlockPos p1 = p2.offset(k, 0, l);
					int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
					if (dist < 4)
						placeLeaves(level, p1, dist, leaves);
				}
			}
			if (h > 3) {
				p2 = p2.below();
				for (int k = -2; k <= 2; k++) {
					for (int l = -2; l <= 2; l++) {
						BlockPos p1 = p2.offset(k, 0, l);
						int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
						if (dist < 4)
							placeLeaves(level, p1, dist, leaves);
					}
				}
			}
		}
	}

	// height 5+
	public static void growTreeAM(Level level, BlockPos pos, int h, BlockState log, BlockState leaves) {
		if (h < 5)
			return;
		// 幹
		for (int i = 0; i < h; i++) {
			level.setBlock(pos.above(i), log, 2);
		}
		BlockPos p0 = pos.above(h - 3);
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			BlockState log2 = log;
			if (dir.getAxis() == Axis.Z) {
				log2 = log.setValue(BlockStateProperties.AXIS, Direction.Axis.Z);
			} else {
				log2 = log.setValue(BlockStateProperties.AXIS, Direction.Axis.X);
			}
			level.setBlock(p0.relative(dir), log2, 2);
		}

		// 葉
		BlockPos p2 = pos.above(h);
		level.setBlock(p2, leaves, 2);
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			level.setBlock(p2.relative(dir), leaves, 2);
		}

		p2 = p2.below();
		for (int k = -2; k <= 2; k++) {
			for (int l = -2; l <= 2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < 4)
					placeLeaves(level, p1, dist, leaves);
			}
		}
		p2 = p2.below();
		for (int k = -2; k <= 2; k++) {
			for (int l = -2; l <= 2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < 4)
					placeLeaves(level, p1, dist, leaves);
			}
		}
		for (int m = 0; m < 2; m++) {
			p2 = p2.below();
			for (int k = -3; k <= 3; k++) {
				for (int l = -3; l <= 3; l++) {
					BlockPos p1 = p2.offset(k, 0, l);
					int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
					if (dist > 1 && dist < 6)
						placeLeaves(level, p1, dist, leaves);
				}
			}
		}
		if (h > 6) {
			p2 = p2.below();
			for (int k = -3; k <= 3; k++) {
				for (int l = -3; l <= 3; l++) {
					BlockPos p1 = p2.offset(k, 0, l);
					int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
					if (dist > 2 && dist < 6)
						placeLeaves(level, p1, dist, leaves);
				}
			}
		}
	}

	// height 6+
	public static void growTreeAL(Level level, BlockPos pos, int h, BlockState log, BlockState leaves) {
		if (h < 6)
			return;
		// 幹
		for (int i = 0; i < h; i++) {
			level.setBlock(pos.above(i), log, 2);
		}
		BlockPos p0 = pos.above(h - 3);
		boolean rand = level.getRandom().nextBoolean();
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			BlockState log2 = log;
			BlockPos p02 = p0;
			if (dir.getAxis() == Axis.Z) {
				log2 = log.setValue(BlockStateProperties.AXIS, Direction.Axis.Z);
				if (rand)
					p02 = p0.above();
			} else {
				log2 = log.setValue(BlockStateProperties.AXIS, Direction.Axis.X);
				if (!rand)
					p02 = p0.above();
			}
			level.setBlock(p02.relative(dir), log2, 2);
			level.setBlock(p02.relative(dir, 2), log2, 2);
		}

		// 葉
		BlockPos p2 = pos.above(h);
		level.setBlock(p2, leaves, 2);
		for (int k = -2; k <= 2; k++) {
			for (int l = -2; l <= 2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < 3)
					placeLeaves(level, p1, dist, leaves);
			}
		}
		p2 = p2.below();
		for (int k = -3; k <= 3; k++) {
			for (int l = -3; l <= 3; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < 6)
					placeLeaves(level, p1, dist, leaves);
			}
		}
		p2 = p2.below();
		for (int k = -3; k <= 3; k++) {
			for (int l = -3; l <= 3; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist > 1 && dist < 6)
					placeLeaves(level, p1, dist, leaves);
			}
		}
		for (int m = 0; m < 2; m++) {
			p2 = p2.below();
			for (int k = -4; k <= 4; k++) {
				for (int l = -4; l <= 4; l++) {
					BlockPos p1 = p2.offset(k, 0, l);
					int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
					if (dist > 2 && dist < 7)
						placeLeaves(level, p1, dist, leaves);
				}
			}
		}
	}

	// height 1+
	public static void growTreeBS(Level level, BlockPos pos, int h, BlockState log, BlockState leaves) {
		if (h < 1)
			return;

		if (h == 1) {
			// 幹
			for (int i = 0; i < h; i++) {
				level.setBlock(pos.above(i), log, 2);
			}
			// 葉
			BlockPos p2 = pos.above(h);
			level.setBlock(p2, leaves, 2);
			for (int m = 0; m < h; m++) {
				p2 = p2.below();
				for (Direction dir : Direction.Plane.HORIZONTAL) {
					level.setBlock(p2.relative(dir), leaves, 2);
				}
			}
		} else {
			// 幹
			for (int i = 0; i < h; i++) {
				level.setBlock(pos.above(i), log, 2);
			}
			// 葉
			BlockPos p2 = pos.above(h);
			level.setBlock(p2, leaves, 2);
			for (Direction dir : Direction.Plane.HORIZONTAL) {
				level.setBlock(p2.relative(dir), leaves, 2);
			}
			for (int m = 0; m < h - 1; m++) {
				p2 = p2.below();
				for (int k = -1; k <= 1; k++) {
					for (int l = -1; l <= 1; l++) {
						BlockPos p1 = p2.offset(k, 0, l);
						int dist = Mth.clamp(Mth.floor(p1.distSqr(p2)), 0, 6);
						placeLeaves(level, p1, dist, leaves);
					}
				}
			}
		}
	}

	// height 4+
	public static void growTreeBM(Level level, BlockPos pos, int h, BlockState log, BlockState leaves) {
		if (h < 4)
			return;
		// 幹
		for (int i = 0; i < h; i++) {
			level.setBlock(pos.above(i), log, 2);
		}
		// 葉
		BlockPos p2 = pos.above(h);
		level.setBlock(p2, leaves, 2);
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			level.setBlock(p2.relative(dir), leaves, 2);
		}
		p2 = p2.below();
		for (int k = -1; k <= 1; k++) {
			for (int l = -1; l <= 1; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.floor(p1.distSqr(p2)), 0, 6);
				placeLeaves(level, p1, dist, leaves);
			}
		}
		for (int m = 0; m < h - 3; m++) {
			p2 = p2.below();
			for (int k = -2; k <= 2; k++) {
				for (int l = -2; l <= 2; l++) {
					BlockPos p1 = p2.offset(k, 0, l);
					int dist = Mth.clamp(Mth.floor(p1.distSqr(p2)), 0, 6);
					if (dist < 4)
						placeLeaves(level, p1, dist, leaves);
				}
			}
		}
	}

	// height 6+
	public static void growTreeBL(Level level, BlockPos pos, int h, BlockState log, BlockState leaves) {
		if (h < 6)
			return;
		// 幹
		for (int i = 0; i < h; i++) {
			level.setBlock(pos.above(i), log, 2);
		}
		// 葉
		BlockPos p2 = pos.above(h);
		level.setBlock(p2, leaves, 2);
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			level.setBlock(p2.relative(dir), leaves, 2);
		}
		p2 = p2.below();
		for (int k = -2; k <= 2; k++) {
			for (int l = -2; l <= 2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < 4)
					placeLeaves(level, p1, dist, leaves);
			}
		}
		p2 = p2.below();
		for (int k = -2; k <= 2; k++) {
			for (int l = -2; l <= 2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < 4)
					placeLeaves(level, p1, dist, leaves);
			}
		}
		for (int m = 0; m < h - 4; m++) {
			p2 = p2.below();
			for (int k = -3; k <= 3; k++) {
				for (int l = -3; l <= 3; l++) {
					BlockPos p1 = p2.offset(k, 0, l);
					int dist = Mth.clamp(Mth.floor(p1.distSqr(p2)), 0, 6);
					if (dist < 6)
						placeLeaves(level, p1, dist, leaves);
				}
			}
		}
		p2 = p2.below();
		for (int k = -2; k <= 2; k++) {
			for (int l = -2; l <= 2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < 4)
					placeLeaves(level, p1, dist, leaves);
			}
		}
	}

	public static void growBlanchAS(Level level, BlockPos pos, Direction dir, int h, int r, BlockState log, BlockState leaves) {
		if (h < 1 || r < 0)
			return;
		// 幹
		BlockState log1 = log;
		BlockState log2 = log;
		if (dir.getAxis() == Axis.Z) {
			log2 = log.setValue(BlockStateProperties.AXIS, Direction.Axis.Z);
		} else {
			log2 = log.setValue(BlockStateProperties.AXIS, Direction.Axis.X);
		}

		BlockPos p0 = pos;
		for (int m1 = 0; m1 < r; m1++) {
			p0 = p0.relative(dir);
			level.setBlock(p0, log2, 2);
		}
		p0 = p0.relative(dir);
		level.setBlock(p0, log2, 2);
		for (int m2 = 0; m2 < h; m2++) {
			p0 = p0.above();
			level.setBlock(p0, log1, 2);
		}

		// 葉
		BlockPos p2 = p0.above(2);
		int r2 = r > 2 ? 2 : 1;
		for (int k = -r2; k <= r2; k++) {
			for (int l = -r2; l <= r2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < r2 + r2)
					placeLeaves(level, p1, dist, leaves);
			}
		}
		p2 = p2.below();
		for (int k = -1 - r2; k <= 1 + r2; k++) {
			for (int l = -1 - r2; l <= 1 + r2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < r2 + r2 + 2)
					placeLeaves(level, p1, dist, leaves);
			}
		}
		p2 = p2.below();
		for (int k = -1 - r2; k <= 1 + r2; k++) {
			for (int l = -1 - r2; l <= 1 + r2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < r2 + r2 + 2)
					placeLeaves(level, p1, dist, leaves);
			}
		}
		p2 = p2.below();
		for (int k = -r2; k <= r2; k++) {
			for (int l = -r2; l <= r2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < r2 + r2)
					placeLeaves(level, p1, dist, leaves);
			}
		}
	}

	public static void growBlanchAM(Level level, BlockPos pos, Direction dir, int h, int r, BlockState log, BlockState leaves) {
		if (h < 1 || r < 0)
			return;
		// 幹
		BlockState log1 = log;
		BlockState log2 = log;
		if (dir.getAxis() == Axis.Z) {
			log2 = log.setValue(BlockStateProperties.AXIS, Direction.Axis.Z);
		} else {
			log2 = log.setValue(BlockStateProperties.AXIS, Direction.Axis.X);
		}

		BlockPos p0 = pos;
		int rm = Math.max(1, r - 1);
		for (int m1 = 0; m1 < rm; m1++) {
			p0 = p0.relative(dir);
			level.setBlock(p0, log2, 2);
		}
		p0 = p0.relative(dir);
		level.setBlock(p0, log2, 2);
		for (int m2 = 0; m2 < h; m2++) {
			p0 = p0.above();
			level.setBlock(p0, log1, 2);
		}
		for (int m1 = 0; m1 < r; m1++) {
			p0 = p0.relative(dir);
			level.setBlock(p0, log2, 2);
		}
		p0 = p0.relative(dir);
		level.setBlock(p0, log2, 2);
		for (int m2 = 0; m2 < h; m2++) {
			p0 = p0.above();
			level.setBlock(p0, log1, 2);
		}

		// 葉
		BlockPos p2 = p0.above(2);
		int r2 = Math.min(r + 1, 3);
		for (int k = -r2; k <= r2; k++) {
			for (int l = -r2; l <= r2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < r2 + r2)
					placeLeaves(level, p1, dist, leaves);
			}
		}
		p2 = p2.below();
		for (int k = -1 - r2; k <= 1 + r2; k++) {
			for (int l = -1 - r2; l <= 1 + r2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < r2 + r2 + 2)
					placeLeaves(level, p1, dist, leaves);
			}
		}
		p2 = p2.below();
		for (int k = -1 - r2; k <= 1 + r2; k++) {
			for (int l = -1 - r2; l <= 1 + r2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < r2 + r2 + 2)
					placeLeaves(level, p1, dist, leaves);
			}
		}
		p2 = p2.below();
		for (int k = -r2; k <= r2; k++) {
			for (int l = -r2; l <= r2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < r2 + r2)
					placeLeaves(level, p1, dist, leaves);
			}
		}
	}

	public static void growBlanchAL(Level level, BlockPos pos, Direction dir, int h, int r, BlockState log, BlockState leaves) {
		if (h < 1 || r < 1)
			return;
		// 幹
		BlockState log1 = log;
		BlockState log2 = log;
		BlockState log3 = log;
		if (dir.getAxis() == Axis.Z) {
			log2 = log.setValue(BlockStateProperties.AXIS, Direction.Axis.Z);
			log3 = log.setValue(BlockStateProperties.AXIS, Direction.Axis.X);
		} else {
			log2 = log.setValue(BlockStateProperties.AXIS, Direction.Axis.X);
			log3 = log.setValue(BlockStateProperties.AXIS, Direction.Axis.Z);
		}

		BlockPos p0 = pos;
		for (int m1 = 0; m1 < r; m1++) {
			p0 = p0.relative(dir);
			level.setBlock(p0, log2, 2);
		}
		p0 = p0.relative(dir);
		level.setBlock(p0, log2, 2);
		for (int m2 = 0; m2 < h; m2++) {
			p0 = p0.above();
			level.setBlock(p0, log1, 2);
		}
		for (int m1 = 0; m1 < 2; m1++) {
			p0 = p0.relative(dir);
			level.setBlock(p0, log2, 2);
		}
		p0 = p0.relative(dir);
		level.setBlock(p0, log2, 2);
		for (int m2 = 0; m2 < 2; m2++) {
			p0 = p0.above();
			level.setBlock(p0, log1, 2);
		}
		for (int m3 = 1; m3 <= r; m3++) {
			level.setBlock(p0.relative(dir, m3), log2, 2);
			level.setBlock(p0.relative(dir.getClockWise(), m3), log3, 2);
			level.setBlock(p0.relative(dir.getCounterClockWise(), m3), log3, 2);
		}

		//おまけ
		if (log.is(FoodInit.LOG_MR_RUBBER.get())) {
			for (int m3 = 1; m3 < r; m3++) {
				if (level.getRandom().nextBoolean()) {
					BlockPos p3 = pos.relative(dir, m3);
					for (int m4 = 0; m4 < 10; m4++) {
						p3 = p3.below();
						if (level.getBlockState(p3).getBlock() != Blocks.AIR) {
							break;
						} else {
							placeBlock(level, p3, Blocks.MANGROVE_ROOTS.defaultBlockState());
						}
					}
				}
				if (level.getRandom().nextInt(8) == 0) {
					BlockPos p3 = pos.relative(dir, m3).above();
					placeBlock(level, p3, FoodInit.BLOCK_OR_CATTLEYA.get().defaultBlockState());
				}
			}
		}

		// 葉
		int r2 = Math.min(3, r + 1);
		BlockPos p2 = p0.relative(dir, r - 1);
		for (int k = -r2; k <= r2; k++) {
			for (int l = -r2; l <= r2; l++) {
				for (int m = -1; m <= 2; m++) {
					BlockPos p1 = p2.offset(k, m, l);
					int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l) + Mth.abs(m), 0, 7);
					if (dist < r2 * 2 - 1)
						placeLeaves(level, p1, dist, leaves);
				}
			}
		}
		p2 = p0.relative(dir.getClockWise(), r + 1);
		for (int k = -r2; k <= r2; k++) {
			for (int l = -r2; l <= r2; l++) {
				for (int m = -1; m <= 2; m++) {
					BlockPos p1 = p2.offset(k, m, l);
					int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l) + Mth.abs(m), 0, 7);
					if (dist < r2 * 2)
						placeLeaves(level, p1, dist, leaves);
				}
			}
		}
		p2 = p0.relative(dir.getCounterClockWise(), r + 1);
		for (int k = -r2; k <= r2; k++) {
			for (int l = -r2; l <= r2; l++) {
				for (int m = -1; m <= 2; m++) {
					BlockPos p1 = p2.offset(k, m, l);
					int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l) + Mth.abs(m), 0, 7);
					if (dist < r2 * 2)
						placeLeaves(level, p1, dist, leaves);
				}
			}
		}
	}

	public static void growBlanchBS(Level level, BlockPos pos, Direction dir, int h, int r, BlockState log, BlockState leaves) {
		if (h < 1 || r < 0)
			return;
		// 幹
		BlockState log1 = log;
		BlockState log2 = log;
		if (dir.getAxis() == Axis.Z) {
			log2 = log.setValue(BlockStateProperties.AXIS, Direction.Axis.Z);
		} else {
			log2 = log.setValue(BlockStateProperties.AXIS, Direction.Axis.X);
		}

		BlockPos p0 = pos;
		for (int m1 = 0; m1 < r; m1++) {
			p0 = p0.relative(dir);
			level.setBlock(p0, log2, 2);
		}
		p0 = p0.relative(dir);
		level.setBlock(p0, log1, 2);
		for (int m2 = 0; m2 < h; m2++) {
			p0 = p0.above();
			level.setBlock(p0, log1, 2);
		}
		if (r > 2) {
			for (Direction d3 : Direction.Plane.HORIZONTAL) {
				level.setBlock(p0.relative(d3), log2, 2);
			}
		}

		// 葉
		BlockPos p2 = p0.above(1).relative(dir.getOpposite());
		int r2 = r > 2 ? 3 : 2;
		for (int k = -r2; k <= r2; k++) {
			for (int l = -r2; l <= r2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < r2 + r2)
					placeLeaves(level, p1, dist, leaves);
			}
		}
		p2 = p2.below();
		for (int k = -1 - r2; k <= 1 + r2; k++) {
			for (int l = -1 - r2; l <= 1 + r2; l++) {
				BlockPos p1 = p2.offset(k, 0, l);
				int dist = Mth.clamp(Mth.abs(k) + Mth.abs(l), 0, 7);
				if (dist < r2 + r2 + 2)
					placeLeaves(level, p1, dist, leaves);
			}
		}
	}

	private static void placeBlock(Level level, BlockPos pos, BlockState leaves) {
		if (level.getBlockState(pos).getBlock() == Blocks.AIR) {
			level.setBlock(pos, leaves, 2);
		}
	}

	private static void placeLeaves(Level level, BlockPos pos, int dist, BlockState leaves) {
		if (level.getBlockState(pos).getBlock() == Blocks.AIR) {
			level.setBlock(pos, DCState.setInt(leaves, DCState.DIST, dist), 2);
		}
	}

	public static boolean isLargeTree(Level level, BlockPos pos, BlockState sapling) {
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			BlockState check = level.getBlockState(pos.relative(dir));
			if (check == null || !check.is(sapling.getBlock()))
				return false;
		}
		return true;
	}

	public static boolean hasNextSapling(Level level, BlockPos pos, BlockState sapling) {
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			BlockState check = level.getBlockState(pos.relative(dir));
			if (check != null && check.is(sapling.getBlock()))
				return true;
		}
		return false;
	}

	public static void clearSapling(Level level, BlockPos pos, BlockState sapling) {
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			BlockState check = level.getBlockState(pos.relative(dir));
			if (check != null && check.is(sapling.getBlock()))
				level.setBlock(pos.relative(dir), Blocks.AIR.defaultBlockState(), 2);
		}
	}

}
