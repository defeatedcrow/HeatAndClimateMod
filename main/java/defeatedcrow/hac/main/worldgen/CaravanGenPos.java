package defeatedcrow.hac.main.worldgen;

import java.util.Random;

import defeatedcrow.hac.main.config.WorldGenConfig;
import defeatedcrow.hac.main.worldgen.CaravanGenEvent.CaravanType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.BiomeDictionary;

/**
 * キャラバンサライの生成座標
 */
public class CaravanGenPos {

	public static Chunk chunk = null;

	public static int isDupe(int cx, int cz, World world) {
		int count = 0;
		for (int x = -8; x < 9; x++) {
			for (int z = -8; z < 9; z++) {
				if ((x > 0 || z > 0) && canGeneratePos(cx + x, cz + z, world)) {
					count++;
				}
			}
		}
		return count;
	}

	public static boolean isAlreadyHasCaravan(int cx, int cz, World world) {
		for (int x = -2; x < 3; x++) {
			for (int z = -2; z < 3; z++) {
				if (x == 0 && z == 0) {
					continue;
				} else if (canGeneratePos(cx + x, cz + z, world)) {
					return true;
				}
			}
		}
		return false;
	}

	public static int getCaravanPartNum(int cx, int cz, World world) {
		for (int x = 0; x < 3; x++) {
			for (int z = 0; z < 3; z++) {
				if (canGeneratePos(cx + x - 1, cz + z - 1, world)) {
					return x + z * 3;
				}
			}
		}
		return -1;
	}

	public static int getCoreHeight(int cx, int cz, World world) {
		int px = cx << 4;
		int pz = cz << 4;
		int h = -1;
		for (int y = 50; y < 100; y++) {
			BlockPos p1 = new BlockPos(px + 7, y, pz + 7);
			if (getType(world, p1) != CaravanType.BROKEN) {
				h = y + 7;
				return h;
			}
			if (!isReplaceable(world, p1) && isReplaceable(world, p1.up())) {
				h = y;
			}
		}
		return h;
	}

	public static boolean isReplaceable(World world, BlockPos pos) {
		net.minecraft.block.state.IBlockState state = world.getBlockState(pos);
		if (state.getMaterial().isLiquid())
			return false;
		return state.getBlock().isAir(state, world, pos) || state.getMaterial().isReplaceable() || state
				.getMaterial() == Material.LEAVES || state.getMaterial() == Material.WOOD;
	}

	public static boolean isSuitableChunk(int cx, int cz, World world) {
		if (world.isRemote)
			return false;

		int genDim1 = world.provider.getDimension();
		if (genDim1 != 0)
			return false;

		// あまり遠いと生成しない
		if (cx > 2000 || cz > 2000)
			return false;
		if (cx < -2000 || cz < -2000)
			return false;

		return true;
	}

	public static boolean canGeneratePos(int cx, int cz, World world) {
		long seed = world.getSeed() + cx * 31 + cz * 131;
		Random rand = new Random(seed);
		rand.nextFloat();
		rand.nextFloat();
		float r = rand.nextFloat() * 10000F;
		int ri = MathHelper.floor(r);
		if (ri > 0 && ri < WorldGenConfig.caravanGen) {
			return true;
		}
		return false;
	}

	public static int[] getRoomNum(int cx, int cz, World world) {
		long seed = world.getSeed() + cx * 31 + cz * 131;
		Random rand = new Random(seed);
		rand.nextInt(4);
		rand.nextInt(4);
		int[] r = new int[4];
		r[0] = rand.nextInt(4);
		r[1] = rand.nextInt(4);
		r[2] = rand.nextInt(4);
		r[3] = rand.nextInt(4);
		return r;
	}

	public static boolean canGenerateBiome(int cx, int cz, World world) {
		if (world != null) {
			Biome biome = getBiome(cx, cz, world);
			if (biome != null) {
				boolean b1 = BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY) || BiomeDictionary
						.hasType(biome, BiomeDictionary.Type.SAVANNA);
				boolean b2 = !BiomeDictionary.hasType(biome, BiomeDictionary.Type.HILLS) && !BiomeDictionary
						.hasType(biome, BiomeDictionary.Type.MOUNTAIN);
				return b1 && b2;
			}
		}
		return false;
	}

	public static Biome getBiome(int cx, int cz, World world) {
		int x = 8 + cx * 16;
		int z = 8 + cz * 16;
		Biome ret = null;
		Biome[] gen = world.getBiomeProvider().getBiomes(null, cx * 16, cz * 16, 16, 16, false);
		boolean f = world.isBlockLoaded(new BlockPos(x, 0, z));
		if (gen != null) {
			ret = gen[8 + 8 * 16];
			// DCLogger.debugInfoLog("Caravan Test: Loaded " + f + ", Biome" + ret.getBiomeName());
		}
		return ret;
	}

	public static CaravanType getType(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state != null) {
			Block block = state.getBlock();
			if (block == Blocks.IRON_BLOCK) {
				return CaravanType.UNINIT;
			} else if (block == Blocks.DIAMOND_BLOCK) {
				return CaravanType.LOADED;
			} else if (block == Blocks.EMERALD_BLOCK) {
				return CaravanType.STANDBY;
			} else {
				return CaravanType.BROKEN;
			}
		}
		return CaravanType.BROKEN;
	}

}
