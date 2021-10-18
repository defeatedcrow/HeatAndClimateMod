package defeatedcrow.hac.main.worldgen;

import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.block.build.BlockSlabBase;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCaravanBase implements IWorldGenerator {

	@Override
	public void generate(Random rand, int cx, int cz, World world, IChunkGenerator gen, IChunkProvider prov) {
		int genDim1 = world.provider.getDimension();
		if ((genDim1 != 0))
			return;

		CaravanData data = CaravanData.getInitialCore(cx, cz, world);

		if (data.type == CaravanData.CaravanType.UNINIT && data.partNum > -1) {
			int num = data.partNum;
			int nx = (num % 3) - 1;
			int nz = (num / 3) - 1;
			int cx2 = cx + nx;
			int cz2 = cz + nz;

			int height = data.height;
			if (height > 10 && height < 100) {
				if (num == 4) {
					generateCore(data, rand, cx, cz, height, world);
					CaravanGenPos.chunk = prov.getLoadedChunk(cx, cz);
					DCLogger.debugInfoLog("Caravanserai Core" + " : " + cx2 + ", " + cz2 + " height " + height);
				} else {
					generatePart(data, rand, cx, cz, world);
				}
			}
		}
	}

	public void generateCore(CaravanData data, Random rand, int cx, int cz, int h, World world) {
		int px = cx << 4;
		int pz = cz << 4;
		int py = h;
		for (int y = -10; y < 40; y++) {
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					BlockPos pos = new BlockPos(px + x, py + y, pz + z);
					IBlockState set = getCoreState(data, x, y, z, rand);
					world.setBlockState(pos, set, 2);
				}
			}
		}
	}

	public void generatePart(CaravanData data, Random rand, int cx, int cz, World world) {
		int px = cx << 4;
		int pz = cz << 4;
		int py = data.height;

		int minX = 0;
		int minZ = 0;
		int maxX = 16;
		int maxZ = 16;
		int nx = (data.partNum % 3) - 1;
		int nz = (data.partNum / 3) - 1;
		int cx2 = cx + nx;
		int cz2 = cz + nz;
		int minX2 = 0;
		int minZ2 = 0;
		int maxX2 = 16;
		int maxZ2 = 16;

		EnumFacing face = EnumFacing.SOUTH;
		if (data.partNum == 7) {
			face = EnumFacing.NORTH;
		}
		if (data.partNum == 3) {
			face = EnumFacing.EAST;
		}
		if (data.partNum == 5) {
			face = EnumFacing.WEST;
		}

		if (nx == -1) {
			maxX = 13;
			maxX2 = 15;
		}
		if (nx == 1) {
			minX = 3;
			minX2 = 1;
		}
		if (nz == -1) {
			maxZ = 13;
			maxZ2 = 15;
		}
		if (nz == 1) {
			minZ = 3;
			minZ2 = 1;
		}

		for (int y = -10; y < 40; y++) {
			for (int x = minX2; x < maxX2; x++) {
				for (int z = minZ2; z < maxZ2; z++) {
					BlockPos pos = new BlockPos(px + x, py + y, pz + z);
					IBlockState set = getPartBase(data, data.partNum, x, y, z, rand);
					if (x >= minX && x < maxX && z >= minZ && z < maxZ) {
						world.setBlockState(pos, set, 2);
					} else if (y > 0) {
						world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
					}
				}
			}
		}

		for (int y = 0; y < 15; y++) {
			for (int x = minX; x < maxX; x++) {
				for (int z = minZ; z < maxZ; z++) {
					BlockPos pos = new BlockPos(px + x, py + y, pz + z);
					int x1 = x;
					int z1 = z;
					if (face == EnumFacing.NORTH) {
						x1 = 15 - x;
						z1 = 15 - z;
					}
					if (face == EnumFacing.EAST) {
						x1 = 15 - z;
						z1 = x;
					}
					if (face == EnumFacing.WEST) {
						x1 = z;
						z1 = 15 - x;
					}
					IBlockState set;
					if (data.isGate) {
						set = getGate(data, x1, y, z1, rand);
					} else if ((data.partNum & 1) == 0) {
						set = getCornerRoom(data, x, y, z, rand);
					} else {
						set = getSmallRoom(data, x1, y, z1, rand);
					}

					if (set != null && set.getBlock() != Blocks.AIR)
						world.setBlockState(pos, set, 2);
				}
			}
		}

		if ((data.partNum & 1) == 1 && !data.isGate) {
			for (int y = 0; y < 15; y++) {
				for (int x = minX; x < maxX; x++) {
					for (int z = minZ; z < maxZ; z++) {
						int x1 = x;
						int z1 = z;
						if (face == EnumFacing.NORTH) {
							x1 = 15 - x;
							z1 = 15 - z;
						}
						if (face == EnumFacing.EAST) {
							x1 = 15 - z;
							z1 = x;
						}
						if (face == EnumFacing.WEST) {
							x1 = z;
							z1 = 15 - x;
						}
						BlockPos pos = new BlockPos(px + x, py + y, pz + z);
						IBlockState set;
						if (data.roomNum == 0) {
							set = getInterior0(data, x1, y, z1, rand, face);
						} else if (data.roomNum == 1) {
							set = getInterior1(data, x1, y, z1, rand, face);
						} else if (data.roomNum == 2) {
							set = getInterior2(data, x1, y, z1, rand, face);
						} else if (data.roomNum == 3) {
							set = getInterior3(data, x1, y, z1, rand, face);
						} else {
							set = Blocks.AIR.getDefaultState();
						}

						if (set != null && set.getBlock() != Blocks.AIR)
							world.setBlockState(pos, set, 2);
					}
				}
			}
		}

	}

	private IBlockState getCoreState(CaravanData data, int x, int y, int z, Random rand) {
		if (y < -3) {
			if (x == 7 && z == 7 && y == -7) {
				return Blocks.IRON_BLOCK.getDefaultState();
			}
			if (x == 8 && z == 8 && y == -7) {
				return MainInit.gemBlock.getDefaultState();
			}
			if (x == 7 && z == 7 && y == -8) {
				return MainInit.clayBricks.getDefaultState().withProperty(DCState.TYPE16, data.partNum);
			}
			if (x == 8 && z == 8 && y == -8) {
				IBlockState state = MainInit.metalBlockAlloy.getDefaultState()
						.withProperty(DCState.TYPE16, data.roomNum);
				if (data.isGate) {
					state = state.withProperty(DCState.TYPE16, 4);
				}
				return state;
			}
			return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
		} else if (y == -3) {
			return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
		} else if (y < 0) {
			if (x > 4 && x < 11 && z > 4 && z < 11) {
				if (x == 5 || x == 10 || z == 5 || z == 10) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else {
					return Blocks.WATER.getDefaultState();
				}
			}
			return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
		} else if (y == 0) {
			if (x > 4 && x < 11 && z > 4 && z < 11) {
				if (x == 5 || x == 10 || z == 5 || z == 10) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else {
					return Blocks.WATER.getDefaultState();
				}
			} else if (x > 2 && x < 13 && z > 2 && z < 13) {
				return Blocks.GRASS.getDefaultState();
			} else if (x > 1 && x < 14 && z > 1 && z < 14) {
				return Blocks.WATER.getDefaultState();
			} else if (x > 0 && x < 15 && z > 0 && z < 15) {
				return Blocks.GRASS.getDefaultState();
			}
			return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 10);
		} else if (y == 1) {
			if (x > 4 && x < 11 && z > 4 && z < 11) {
				if (x == 5 && z == 5) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else if (x == 5 && z == 10) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else if (x == 10 && z == 5) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else if (x == 10 && z == 10) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else if (x == 5) {
					return MainInit.stairsDirtbrick.getDefaultState().withProperty(DCState.FACING, EnumFacing.WEST);
				} else if (x == 10) {
					return MainInit.stairsDirtbrick.getDefaultState().withProperty(DCState.FACING, EnumFacing.EAST);
				} else if (z == 5) {
					return MainInit.stairsDirtbrick.getDefaultState();
				} else if (z == 10) {
					return MainInit.stairsDirtbrick.getDefaultState().withProperty(DCState.FACING, EnumFacing.SOUTH);
				} else {
					return Blocks.AIR.getDefaultState();
				}
			} else if (x > 3 && x < 12 && z > 3 && z < 12) {
				return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
			}
			return Blocks.AIR.getDefaultState();
		} else if (y < 4) {
			if (x == 5 && z == 5) {
				return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
			} else if (x == 5 && z == 10) {
				return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
			} else if (x == 10 && z == 5) {
				return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
			} else if (x == 10 && z == 10) {
				return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
			} else {
				return Blocks.AIR.getDefaultState();
			}
		} else if (y == 4) {
			if (x > 3 && x < 12 && z > 3 && z < 12) {
				if (x > 4 && x < 11 && z > 4 && z < 11) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else {
					return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
				}
			}
			return Blocks.AIR.getDefaultState();
		} else
			return Blocks.AIR.getDefaultState();
	}

	private IBlockState getPartBase(CaravanData data, int num, int x, int y, int z, Random rand) {
		if (y < 0) {
			if (x == 7 && z == 7 && y == -7) {
				return Blocks.IRON_BLOCK.getDefaultState();
			}
			if (x == 8 && z == 8 && y == -7) {
				return MainInit.gemBlock.getDefaultState();
			}
			if (x == 7 && z == 7 && y == -8) {
				return MainInit.clayBricks.getDefaultState().withProperty(DCState.TYPE16, data.partNum);
			}
			if (x == 8 && z == 8 && y == -8) {
				IBlockState state = MainInit.metalBlockAlloy.getDefaultState()
						.withProperty(DCState.TYPE16, data.roomNum);
				if (data.isGate) {
					state = state.withProperty(DCState.TYPE16, 4);
				}
				return state;
			}
			return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
		}
		return Blocks.AIR.getDefaultState();
	}

	private IBlockState getCornerRoom(CaravanData data, int x, int y, int z, Random rand) {
		int num = data.partNum;
		int cx2 = (num % 3) - 1;
		int cz2 = (num / 3) - 1;
		if (cx2 == 1) {
			x = 15 - x;
		}
		if (cz2 == 1) {
			z = 15 - z;
		}

		if (x == 3 && z == 2 && y == 1) {
			IBlockState door = Blocks.OAK_DOOR.getDefaultState();
			if (cz2 == -1) {
				door = door.withProperty(BlockDoor.FACING, EnumFacing.SOUTH);
			}
			return door;
		}
		if (x == 3 && z == 2 && y == 2) {
			IBlockState door = Blocks.OAK_DOOR.getDefaultState()
					.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER);
			if (cz2 == -1) {
				door = door.withProperty(BlockDoor.FACING, EnumFacing.SOUTH);
			}
			return door;
		}

		if (x == 8 && z == 8 && y == 5) {
			return ModuleConfig.build_advanced ? MainInit.chain.getDefaultState() : Blocks.ACACIA_FENCE
					.getDefaultState();
		}
		if (x == 8 && z == 8 && y == 4) {
			return ModuleConfig.build_advanced ? MainInit.chandelierGypsum.getDefaultState() : MainInit.chalLamp
					.getDefaultState().withProperty(DCState.TYPE16, 2);
		}
		if (x == 5 && z == 3 && y == 1) {
			return MainInit.chalLamp.getDefaultState().withProperty(DCState.TYPE16, 9);
		}

		if (y == 0) {
			if (x > 3 && x < 12 && z > 3 && z < 12) {
				return ModuleConfig.build_advanced ? MainInit.carpetRed.getDefaultState() : Blocks.STAINED_HARDENED_CLAY
						.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.RED);
			} else if (x < 11 && z < 11) {
				return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 10);
			} else {
				return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
			}
		}

		if (x > 1 && x < 13 && z > 1 && z < 13) {
			if (x == 2 || x == 12 || z == 2 || z == 12) {
				if (y < 3) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else if (y == 3) {
					if (rand.nextBoolean()) {
						return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
					} else {
						return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
								.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
					}
				} else if (y < 8) {
					return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
							.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
				} else if (y == 8) {
					return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
				}
			}
			if (x > 2 && x < 12 && z > 2 && z < 12) {
				if (y == 6) {
					return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
							.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
				} else if (y == 7) {
					return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
				}
			}
		}

		if ((x < 2 && z == 5) || (x < 2 && z == 10) || (x == 5 && z < 2) || (x == 10 && z < 2)) {
			if (y < 3) {
				return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
			} else if (y == 3) {
				if (rand.nextBoolean()) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else {
					return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
							.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
				}
			} else if (y < 6) {
				return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
						.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
			} else if (y == 6) {
				return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
			}
		}
		if (x < 2 && z > 5 && z < 10) {
			if (y == 5) {
				return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
						.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
			} else if (y == 6) {
				return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
			}
		}
		if (z < 2 && x > 5 && x < 10) {
			if (y == 5) {
				return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
						.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
			} else if (y == 6) {
				return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
			}
		}
		if (x < 2 && z == 4 && y == 5) {
			return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
		}
		if (z < 2 && x == 4 && y == 5) {
			return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
		}

		return Blocks.AIR.getDefaultState();
	}

	private IBlockState getSmallRoom(CaravanData data, int x, int y, int z, Random rand) {
		int num = data.partNum;
		int cx2 = (num % 3) - 1;
		int cz2 = (num / 3) - 1;
		EnumFacing face = EnumFacing.SOUTH;
		int x1 = x;
		int z1 = z;
		if (num == 7) {
			face = EnumFacing.NORTH;
		}
		if (num == 3) {
			face = EnumFacing.EAST;
		}
		if (num == 5) {
			face = EnumFacing.WEST;
		}

		if (y == 0) {
			if (z1 < 11) {
				return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 10);
			}
			return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
		}

		if (z1 == 4) {
			if (y < 4) {
				switch (x1) {
				case 0:
				case 5:
				case 10:
				case 15:
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				default:
					return Blocks.AIR.getDefaultState();
				}
			}
			if (y == 4) {
				switch (x1) {
				case 2:
				case 3:
				case 7:
				case 8:
				case 12:
				case 13:
					return Blocks.AIR.getDefaultState();
				default:
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				}
			}
			if (y == 5) {
				return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
			}
			return Blocks.AIR.getDefaultState();
		}

		if (z1 == 5 || z1 == 10) {
			if (y < 3) {
				if (z1 == 5) {
					switch (x1) {
					case 2:
					case 3:
					case 7:
					case 8:
					case 12:
					case 13:
						return Blocks.AIR.getDefaultState();
					default:
						return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
					}
				}
				return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
			} else if (y == 3) {
				if (rand.nextBoolean()) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else {
					return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
							.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
				}
			} else if (y < 6) {
				return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
						.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
			} else if (y == 6) {
				return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
			}
		}

		if (z1 > 5 && z1 < 10) {
			if (y == 5) {
				return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
						.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
			} else if (y == 6) {
				return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
			}
		}

		return Blocks.AIR.getDefaultState();
	}

	private IBlockState getGate(CaravanData data, int x, int y, int z, Random rand) {
		int num = data.partNum;
		int cx2 = (num % 3) - 1;
		int cz2 = (num / 3) - 1;
		int x1 = x;
		int z1 = z;
		EnumFacing face = EnumFacing.SOUTH;
		if (num == 7) {
			face = EnumFacing.NORTH;
		}
		if (num == 3) {
			face = EnumFacing.EAST;
		}
		if (num == 5) {
			face = EnumFacing.WEST;
		}

		if (y == 0) {
			if (z1 < 11) {
				return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 10);
			}
			return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
		}

		if (x1 == 3 && z1 == 4 && y < 3) {
			return Blocks.AIR.getDefaultState();
		}
		if (x1 == 12 && z1 == 4 && y < 3) {
			return Blocks.AIR.getDefaultState();
		}
		if (x1 == 6 && z1 == 4 && y < 5) {
			return Blocks.AIR.getDefaultState();
		}
		if (x1 == 9 && z1 == 4 && y < 5) {
			return Blocks.AIR.getDefaultState();
		}
		if (x1 == 6 && z1 == 11 && y < 5) {
			return Blocks.AIR.getDefaultState();
		}
		if (x1 == 9 && z1 == 11 && y < 5) {
			return Blocks.AIR.getDefaultState();
		}
		if (x1 == 2 && z1 == 7 && y < 3) {
			return Blocks.AIR.getDefaultState();
		}
		if (x1 == 13 && z1 == 7 && y < 3) {
			return Blocks.AIR.getDefaultState();
		}

		if (x1 == 5 && z1 == 5 && y < 7) {
			return Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING, face);
		}

		if (z1 == 7 && y == 1 && (x1 == 7 || x1 == 8)) {
			return Blocks.BIRCH_FENCE_GATE.getDefaultState().withProperty(BlockFenceGate.FACING, face);
		}

		if (z1 > 6 && z1 < 9 && y == 2 && (x1 == 6 || x1 == 9)) {
			return Blocks.AIR.getDefaultState();
		}

		if (z1 > 6 && z1 < 9 && y == 7 && (x1 == 6 || x1 == 9)) {
			return Blocks.AIR.getDefaultState();
		}
		if (z1 > 6 && z1 < 9 && y == 8 && (x1 == 6 || x1 == 9)) {
			return Blocks.AIR.getDefaultState();
		}

		if (x1 < 2 && y == 5 && z1 == 4) {
			return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
		}
		if (x1 > 13 && y == 5 && z1 == 4) {
			return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
		}

		if (x1 > 3 && x1 < 12 && (z1 == 3 || z1 == 12)) {
			if (y < 9) {
				if (x1 == 4 || x1 == 11) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else {
					return Blocks.AIR.getDefaultState();
				}
			}
			if (y == 9) {
				if (x1 > 5 && x1 < 10) {
					return Blocks.AIR.getDefaultState();
				} else {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				}
			}
			if (y == 10) {
				if (z1 == 3)
					return MainInit.stairsDirtbrick.getDefaultState().withProperty(DCState.FACING, face);
				else
					return MainInit.stairsDirtbrick.getDefaultState().withProperty(DCState.FACING, face.getOpposite());
			}
			if (y == 11) {
				return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
			}
		}

		if ((x1 < 2 && z1 == 5) || (x1 < 2 && z1 == 10) || (x1 > 13 && z1 == 5) || (x1 > 13 && z1 == 10)) {
			if (y < 3) {
				return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
			} else if (y == 3) {
				if (rand.nextBoolean()) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else {
					return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
							.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
				}
			} else if (y < 6) {
				return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
						.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
			} else if (y == 6) {
				return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
			}
		}
		if (x1 < 2 && z1 > 5 && z1 < 10) {
			if (y == 5) {
				return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
						.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
			} else if (y == 6) {
				return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
			}
		}
		if (x1 > 13 && z1 > 5 && z1 < 10) {
			if (y == 5) {
				return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
						.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
			} else if (y == 6) {
				return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
			}
		}

		if (x1 > 1 && x1 < 7 && z1 > 3 && z1 < 12) {
			if (x1 == 2 || x1 == 6 || z1 == 4 || z1 == 11) {
				if (y < 12) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else if (y == 12) {
					return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
				}
			} else {
				if (y == 11 || y == 6) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else if (y == 12) {
					return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
				}
			}
		}

		if (x1 > 8 && x1 < 14 && z1 > 3 && z1 < 12) {
			if (x1 == 9 || x1 == 13 || z1 == 4 || z1 == 11) {
				if (y < 12) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else if (y == 12) {
					return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
				}
			} else {
				if (y == 6 || y == 11) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else if (y == 12) {
					return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
				}
			}
		}

		if (x1 > 6 && x1 < 9 && z1 > 3 && z1 < 12) {
			if (z1 == 4 || z1 == 11) {
				if (y > 5 && y < 12) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else if (y == 12) {
					return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
				}
			} else {
				if (y == 6) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else if (y == 12) {
					return MainInit.halfSlab.getDefaultState().withProperty(BlockSlabBase.TYPE, 5);
				}
			}
		}

		return Blocks.AIR.getDefaultState();
	}

	// 普通の寝室
	private IBlockState getInterior0(CaravanData data, int x, int y, int z, Random rand, EnumFacing face) {
		if ((x == 3 || x == 8 || x == 13) && z == 4 && y == 2) {
			return MainInit.wallLamp.getDefaultState().withProperty(DCState.TYPE4, 1).withProperty(DCState.FACING, face
					.getOpposite());
		}
		if (z > 4 && z < 11) {
			if ((x == 0 || x == 1 || x == 8 || x == 14 || x == 15) && y == 1) {
				if (z == 8) {
					return Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT)
							.withProperty(BlockBed.FACING, face);
				}
				if (z == 9) {
					return Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD)
							.withProperty(BlockBed.FACING, face);
				}
			}
			if ((x == 3 || x == 8 || x == 13) && z == 5 && y < 3) {
				return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
			}
			if ((x == 2 || x == 7 || x == 12) && z == 5) {
				if (y == 1) {
					return Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER)
							.withProperty(BlockDoor.FACING, face);
				}
				if (y == 2) {
					return Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER)
							.withProperty(BlockDoor.FACING, face);
				}
			}
			if (x == 5 || x == 10) {
				if (y < 3) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else if (y < 6) {
					return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
							.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
				}
			}
		}
		return Blocks.AIR.getDefaultState();
	}

	// 倉庫
	private IBlockState getInterior1(CaravanData data, int x, int y, int z, Random rand, EnumFacing face) {
		if ((x == 3 || x == 8 || x == 13) && z == 4 && y == 2) {
			return MainInit.wallLamp.getDefaultState().withProperty(DCState.TYPE4, 1).withProperty(DCState.FACING, face
					.getOpposite());
		}
		if (z > 4 && z < 11) {
			if ((x == 3 || x == 8 || x == 13) && z == 5 && y < 3) {
				return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
			}
			if ((x == 2 || x == 7 || x == 12) && z == 5) {
				if (y == 1) {
					return Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER)
							.withProperty(BlockDoor.FACING, face);
				}
				if (y == 2) {
					return Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER)
							.withProperty(BlockDoor.FACING, face);
				}
			}
			if (x == 5 || x == 10) {
				if (y < 3) {
					return MainInit.builds.getDefaultState().withProperty(DCState.TYPE16, 7);
				} else if (y < 6) {
					return Blocks.STAINED_HARDENED_CLAY.getDefaultState()
							.withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
				}
			}
		}
		return Blocks.AIR.getDefaultState();
	}

	// カフェ
	private IBlockState getInterior2(CaravanData data, int x, int y, int z, Random rand, EnumFacing face) {
		if ((x == 5 || x == 10) && z == 6 && y == 3) {
			return MainInit.wallLamp.getDefaultState().withProperty(DCState.TYPE4, 1)
					.withProperty(DCState.FACING, face);
		}
		if (ModuleConfig.build_advanced) {
			if ((x > 5 && x < 10) && z == 9 && y == 3) {
				return MainInit.displayShelf.getDefaultState().withProperty(DCState.FACING, face.getOpposite());
			}
			if ((x == 0 || x == 15) && z == 8 && y == 4) {
				return MainInit.chandelierGypsum.getDefaultState();
			}
			if (z == 9 && y == 1) {
				switch (x) {
				case 0:
				case 4:
				case 11:
				case 15:
					return MainInit.tableWood.getDefaultState();
				default:
					return Blocks.AIR.getDefaultState();
				}
			}
			if (z == 8 && y == 1) {
				switch (x) {
				case 2:
				case 3:
				case 12:
				case 13:
					return Blocks.AIR.getDefaultState();
				case 1:
					return MainInit.stoolRed.getDefaultState().withProperty(DCState.FACING, face.rotateY()
							.getOpposite());
				case 14:
					return MainInit.stoolRed.getDefaultState().withProperty(DCState.FACING, face.rotateY());
				default:
					return MainInit.tableWood.getDefaultState();
				}
			}
			if (z == 7 && y == 1) {
				switch (x) {
				case 5:
				case 7:
				case 9:
					return MainInit.stoolRed.getDefaultState().withProperty(DCState.FACING, face.getOpposite());
				case 0:
				case 15:
					return MainInit.tableWood.getDefaultState();
				default:
					return Blocks.AIR.getDefaultState();
				}
			}
		}
		return Blocks.AIR.getDefaultState();
	}

	// 店
	private IBlockState getInterior3(CaravanData data, int x, int y, int z, Random rand, EnumFacing face) {
		if ((x == 5 || x == 10) && z == 6 && y == 2) {
			return MainInit.wallLamp.getDefaultState().withProperty(DCState.TYPE4, 1)
					.withProperty(DCState.FACING, face);
		}
		if (z == 9 && y == 1) {
			switch (x) {
			case 0:
			case 15:
				return Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, face.getOpposite());
			case 2:
			case 13:
				return ModuleConfig.build_advanced ? MainInit.tableWood.getDefaultState() : Blocks.PLANKS
						.getDefaultState();
			default:
				return Blocks.AIR.getDefaultState();
			}
		}
		if (z == 8 && y == 1) {
			switch (x) {
			case 2:
			case 13:
				return ModuleConfig.build_advanced ? MainInit.tableWood.getDefaultState() : Blocks.PLANKS
						.getDefaultState();
			default:
				return Blocks.AIR.getDefaultState();
			}
		}
		if (z == 7 && y == 1) {
			switch (x) {
			case 0:
			case 1:
			case 2:
			case 13:
			case 14:
			case 15:
				return ModuleConfig.build_advanced ? MainInit.tableWood.getDefaultState() : Blocks.PLANKS
						.getDefaultState();
			default:
				return Blocks.AIR.getDefaultState();
			}
		}
		return Blocks.AIR.getDefaultState();
	}

}
