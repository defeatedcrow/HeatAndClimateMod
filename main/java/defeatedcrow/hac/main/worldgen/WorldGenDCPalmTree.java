package defeatedcrow.hac.main.worldgen;

import java.util.Random;

import defeatedcrow.hac.food.block.crop.BlockSaplingDC;
import defeatedcrow.hac.food.block.crop.BlockSaplingDC2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenDCPalmTree extends WorldGenAbstractTree {
	private final IBlockState LOG;
	private final IBlockState LEAF;
	private final IBlockState LEAF2;
	private final int H;

	public WorldGenDCPalmTree(boolean notify, IBlockState leaf, int i) {
		super(notify);
		LEAF = leaf;
		LEAF2 = Blocks.AIR.getDefaultState();
		LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
		H = i;
	}

	public WorldGenDCPalmTree(boolean notify, IBlockState leaf, IBlockState leaf2, int i) {
		super(notify);
		LEAF = leaf;
		LEAF2 = leaf2;
		LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
		H = i;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int i = rand.nextInt(3) + H;

		boolean flag = true;
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

		if (pos.getY() >= 1 && pos.getY() + i + 2 <= 256) {
			for (int j = pos.getY(); j <= pos.getY() + 3 + i; ++j) {
				int k = 1;

				if (j == pos.getY()) {
					k -= 1;
				}

				if (j >= pos.getY() + 1 + i - 2) {
					k += 1;
				}

				for (int l = pos.getX() - k; l <= pos.getX() + k && flag; ++l) {
					for (int i1 = pos.getZ() - k; i1 <= pos.getZ() + k && flag; ++i1) {
						if (j >= 0 && j < world.getHeight()) {
							if (!this.isReplaceable(world, mutable.setPos(l, j, i1))) {
								flag = false;
							}
						} else {
							flag = false;
						}
					}
				}
			}

			if (!flag) {
				return false;
			} else {
				BlockPos down = pos.down();
				IBlockState state = world.getBlockState(down);

				if (pos.getY() < world.getHeight() - i - 1) {
					state.getBlock().onPlantGrow(state, world, down, pos);

					for (int i2 = pos.getY() - 1 + i; i2 <= pos.getY() + i + 2; ++i2) {
						int y1 = i2 - (pos.getY() + i);

						for (int i3 = pos.getX() - 2; i3 <= pos.getX() + 2; ++i3) {
							int x1 = i3 - pos.getX();

							for (int k1 = pos.getZ() - 2; k1 <= pos.getZ() + 2; ++k1) {
								int z1 = k1 - pos.getZ();

								BlockPos blockpos = mutable.setPos(i3, i2, k1);
								if (y1 == -1) {
									if ((x1 == 0 && Math.abs(z1) == 1) || (z1 == 0 && Math.abs(x1) == 1)) {
										IBlockState state2 = world.getBlockState(blockpos);

										if (state2.getBlock().isAir(state2, world, blockpos)) {
											this.setBlockAndNotifyAdequately(world, blockpos, LEAF2);
										}
									}
								} else if (y1 == 0) {
									if ((Math.abs(x1) == 2 && Math.abs(z1) == 2) || Math.abs(z1) == 0 ||
											Math.abs(x1) == 0) {
										IBlockState state2 = world.getBlockState(blockpos);

										if (state2.getBlock().isAir(state2, world, blockpos)) {
											this.setBlockAndNotifyAdequately(world, blockpos, LEAF);
										}
									}
								} else if (y1 == 1) {
									if (Math.abs(x1) == Math.abs(z1) && (Math.abs(x1) < 2 || Math.abs(z1) < 2)) {
										IBlockState state2 = world.getBlockState(blockpos);

										if (state2.getBlock().isAir(state2, world, blockpos)) {
											this.setBlockAndNotifyAdequately(world, blockpos, LEAF);
										}
									}
								} else if (y1 == 2) {
									if ((Math.abs(x1) == 1 && Math.abs(z1) == 0) || (Math.abs(x1) == 0 &&
											Math.abs(z1) == 1)) {
										IBlockState state2 = world.getBlockState(blockpos);

										if (state2.getBlock().isAir(state2, world, blockpos)) {
											this.setBlockAndNotifyAdequately(world, blockpos, LEAF);
										}
									}
								}
							}
						}
					}
					this.setBlockAndNotifyAdequately(world, mutable.setPos(pos.getX() + 3, pos.getY() + i -
							1, pos.getZ()), LEAF);
					this.setBlockAndNotifyAdequately(world, mutable.setPos(pos.getX() - 3, pos.getY() + i -
							1, pos.getZ()), LEAF);
					this.setBlockAndNotifyAdequately(world, mutable.setPos(pos.getX(), pos.getY() + i - 1, pos.getZ() +
							3), LEAF);
					this.setBlockAndNotifyAdequately(world, mutable.setPos(pos.getX(), pos.getY() + i - 1, pos.getZ() -
							3), LEAF);

					for (int j2 = 0; j2 < i; ++j2) {
						BlockPos upN = pos.up(j2);
						IBlockState state2 = world.getBlockState(upN);

						if (state2.getBlock().isAir(state2, world, upN) ||
								state2.getBlock().isLeaves(state2, world, upN)) {
							this.setBlockAndNotifyAdequately(world, pos.up(j2), LOG);
						}
					}

					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	@Override
	protected boolean canGrowInto(Block block) {
		return (block instanceof BlockSaplingDC) || (block instanceof BlockSaplingDC2) ? true :
				super.canGrowInto(block);
	}
}
