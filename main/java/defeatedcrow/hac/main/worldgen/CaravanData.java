package defeatedcrow.hac.main.worldgen;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.util.BiomeCatchDC;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class CaravanData {

	public final CaravanType type;
	public final int partNum;
	public final int roomNum;
	public final int height;
	public final boolean isGate;

	public CaravanData(CaravanType t, int num, int r, int h, boolean g) {
		type = t;
		partNum = num;
		roomNum = r;
		height = h;
		isGate = g;
	}

	public int getRoomNum() {
		return roomNum & 3;
	}

	public static CaravanData getExistingCore(int cx, int cz, World world) {
		int px = cx << 4;
		int pz = cz << 4;
		BlockPos pos = new BlockPos(px + 7, 2, pz + 7);
		if (world == null || !world.isBlockLoaded(pos)) {
			return new CaravanData(CaravanType.NULL, -1, -1, 1, false);
		} else {
			int h = -1;
			for (int y = 4; y < 100; y++) {
				pos = new BlockPos(px + 7, y, pz + 7);
				IBlockState state = world.getBlockState(pos);
				if (state != null) {
					Block block = state.getBlock();
					if (getType(block) != CaravanType.NULL) {
						h = y;
						break;
					}
				}
			}
			if (h > 0) {
				pos = new BlockPos(px + 7, h, pz + 7);
				IBlockState core = world.getBlockState(pos);
				IBlockState numPos = world.getBlockState(pos.add(0, -1, 0));
				IBlockState roomPos = world.getBlockState(pos.add(1, -1, 1));
				CaravanType type = getType(core.getBlock());
				if (type != CaravanType.NULL) {
					if (numPos.getBlock() == MainInit.clayBricks && roomPos.getBlock() == MainInit.metalBlockAlloy) {
						int num = numPos.getBlock().getMetaFromState(numPos);
						int room = roomPos.getBlock().getMetaFromState(roomPos);
						boolean isGate = room == 4;
						int height = h + 7;
						// DCLogger.debugInfoLog("Caravanserai Loaded" + " : " + cx + ", " + cz + " h " + height + " num
						// " + num);
						return new CaravanData(type, num, room, height, isGate);
					} else {
						int num = CaravanGenPos.getCaravanPartNum(cx, cz, world);
						if (num < 0) {
							return new CaravanData(CaravanType.NULL, -1, -1, 1, false);
						}
						int height = h + 7;
						int t = num / 2;
						int nx = (num % 3) - 1;
						int nz = (num / 3) - 1;
						int cx2 = cx + nx;
						int cz2 = cz + nz;
						int[] roomType = CaravanGenPos.getRoomNum(cx2, cz2, world);
						int room = roomType[t];
						int gate = roomType[4] * 2 + 1;
						boolean isGate = num == gate;
						// DCLogger.debugInfoLog("Caravanserai Loaded2" + " : " + cx + ", " + cz + " h " + height + "
						// num " + num);
						return new CaravanData(type, num, room, height, isGate);
					}
				}
			}
			return new CaravanData(CaravanType.NULL, -1, -1, 1, false);
		}
	}

	public static CaravanData getInitialCore(int cx, int cz, World world) {
		int px = cx << 4;
		int pz = cz << 4;
		BlockPos pos = new BlockPos(px + 7, 2, pz + 7);
		if (world == null) {
			return new CaravanData(CaravanType.NULL, -1, -1, 1, false);
		} else {
			int num = CaravanGenPos.getCaravanPartNum(cx, cz, world);
			if (num < 0) {
				return new CaravanData(CaravanType.NULL, -1, -1, 1, false);
			}
			int t = num / 2;
			int nx = (num % 3) - 1;
			int nz = (num / 3) - 1;
			int cx2 = cx + nx;
			int cz2 = cz + nz;

			boolean check = canGenerateBiome(cx2, cz2, world);
			int check2 = CaravanGenPos.isDupe(cx2, cz2, world);
			// DCLogger.debugInfoLog("test1 Biome: x " + cx2 + ", z " + cz2 + ", " + check);
			// DCLogger.debugInfoLog("test1 DupeCheck: x " + cx2 + ", z " + cz2 + ", " + check2);
			if (!check || check2 != 0) {
				return new CaravanData(CaravanType.NULL, -1, -1, 1, false);
			}

			int height = getCoreHeight(cx2, cz2, world);

			if (height > 0) {
				int[] roomType = CaravanGenPos.getRoomNum(cx2, cz2, world);
				int room = roomType[t];
				int gate = roomType[4] * 2 + 1;
				boolean isGate = num == gate;
				return new CaravanData(CaravanType.UNINIT, num, room, height, isGate);
			}
		}
		return new CaravanData(CaravanType.NULL, -1, -1, 1, false);
	}

	public static CaravanData getForcedData(int cx, int cz, int height, World world, int cx2, int cz2) {
		if (world == null) {
			return new CaravanData(CaravanType.NULL, -1, -1, 1, false);
		} else {
			int n1 = 1 - cx + cx2;
			int n2 = 1 - cz + cz2;
			int num = n1 + (n2 * 3);
			DCLogger.debugInfoLog("Caravanserai Forced Core" + " : " + num);
			if (num < 0) {
				return new CaravanData(CaravanType.NULL, -1, -1, 1, false);
			}
			int t = num / 2;
			int nx = (num % 3) - 1;
			int nz = (num / 3) - 1;

			if (height > 0) {
				int[] roomType = CaravanGenPos.getRoomNum(cx2, cz2, world);
				int room = roomType[t];
				int gate = roomType[4] * 2 + 1;
				boolean isGate = num == gate;
				return new CaravanData(CaravanType.UNINIT, num, room, height, isGate);
			}
		}
		return new CaravanData(CaravanType.NULL, -1, -1, 1, false);
	}

	public static int getCoreHeight(int cx, int cz, World world) {
		int px = cx << 4;
		int pz = cz << 4;
		int h = -1;
		CaravanData ext = getExistingCore(cx, cz, world);
		if (ext.type != CaravanType.NULL) {
			return ext.height;
		}
		for (int y = 10; y < 100; y++) {
			BlockPos p1 = new BlockPos(px + 7, y, pz + 7);
			if (!isReplaceable(world, p1) && isReplaceable(world, p1.up())) {
				h = y;
			}
		}
		return h;
	}

	public static boolean canGenerateBiome(int cx, int cz, World world) {
		if (world != null) {
			int px = cx << 4;
			int pz = cz << 4;
			px += 8;
			pz += 8;
			Biome biome = BiomeCatchDC.getBiome(new BlockPos(px, 1, pz), world);
			if (biome != null) {
				// DCLogger.debugInfoLog("test1 Biome: " + biome.getBiomeName());
				boolean b1 = BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY) || BiomeDictionary
						.hasType(biome, BiomeDictionary.Type.SAVANNA);
				boolean b2 = !BiomeDictionary.hasType(biome, BiomeDictionary.Type.HILLS) && !BiomeDictionary
						.hasType(biome, BiomeDictionary.Type.MOUNTAIN);
				return b1 && b2;
			}
		}
		return false;
	}

	public static boolean isReplaceable(World world, BlockPos pos) {
		net.minecraft.block.state.IBlockState state = world.getBlockState(pos);
		if (state.getMaterial().isLiquid())
			return false;
		return state.getBlock().isAir(state, world, pos) || state.getMaterial().isReplaceable() || state
				.getMaterial() == Material.LEAVES || state.getMaterial() == Material.WOOD || state
						.getMaterial() == Material.CACTUS || state
								.getMaterial() == Material.PLANTS;
	}

	public static CaravanType getType(Block block) {
		if (block == Blocks.IRON_BLOCK) {
			return CaravanType.UNINIT;
		} else if (block == Blocks.DIAMOND_BLOCK) {
			return CaravanType.LOADED;
		} else if (block == Blocks.EMERALD_BLOCK) {
			return CaravanType.STANDBY;
		} else if (block == Blocks.LAPIS_BLOCK) {
			return CaravanType.BROKEN;
		} else {
			return CaravanType.NULL;
		}
	}

	public enum CaravanType {
		UNINIT,
		STANDBY,
		LOADED,
		BROKEN,
		NULL;
	}

}
