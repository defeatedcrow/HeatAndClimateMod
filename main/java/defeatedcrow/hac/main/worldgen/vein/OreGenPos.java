package defeatedcrow.hac.main.worldgen.vein;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import defeatedcrow.hac.main.api.orevein.EnumVein;
import defeatedcrow.hac.main.config.WorldGenConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

/**
 * HaC鉱脈は生成座標決定に再現性がある<br>
 * OreGenPosはチャンク毎にどの位置にどの鉱脈が生成されるかを扱う
 */
public class OreGenPos {

	private static int redP = WorldGenConfig.depositGen[0];
	private static int greenP = WorldGenConfig.depositGen[1];
	private static int blueP = WorldGenConfig.depositGen[2];
	private static int whiteP = WorldGenConfig.depositGen[3];
	private static int blackP = WorldGenConfig.depositGen[4];
	private static int guanoP = WorldGenConfig.depositGen[5];
	private static int netherP = WorldGenConfig.depositGen[6];

	public static final OreGenPos INSTANCE = new OreGenPos();

	private static Set<ChunkPos> windmillPos = new HashSet<>();
	private static Set<ChunkPos> caravanPos = new HashSet<>();

	public OreVein[] getVeins(int cx, int cz, World world) {
		long seed = world.getSeed() + cx + cz * 31;
		Random rand = new Random(seed);
		rand.nextInt();
		rand.nextInt(100);
		rand.nextInt(20);
		rand.nextInt(30);
		rand.nextInt(40);
		rand.nextInt(60);
		rand.nextInt(8);
		int x = cx << 4;
		int z = cz << 4;

		OreVein[] ret = new OreVein[4];

		int y1 = 10 + rand.nextInt(20);

		int x1 = x + 8 + rand.nextInt(8);
		int z1 = z + 8 + rand.nextInt(8);
		BlockPos pos1 = new BlockPos(x1, y1, z1);
		int rand1 = rand.nextInt(100);
		if (rand1 < blackP) {
			ret[0] = getVeinFromSeed(world, pos1, EnumVein.BLACK, seed);
		}

		int y2 = 25 + rand.nextInt(30);

		int x2 = x + 8 + rand.nextInt(8);
		int z2 = z + 8 + rand.nextInt(8);
		BlockPos pos2 = new BlockPos(x2, y2, z2);
		int rand2 = rand.nextInt(100);
		Biome biome2 = world.getBiome(pos2);
		if (BiomeDictionary.hasType(biome2, BiomeDictionary.Type.BEACH) && rand2 < guanoP) {
			ret[1] = getVeinFromSeed(world, pos2, EnumVein.GUANO, seed);
		} else if (BiomeDictionary.hasType(biome2, BiomeDictionary.Type.OCEAN)) {
			if (y2 > 45 && rand2 < guanoP) {
				ret[1] = getVeinFromSeed(world, pos2, EnumVein.GUANO, seed);
			} else if (rand2 < blueP) {
				ret[1] = getVeinFromSeed(world, pos2, EnumVein.BLUE, seed);
			}
		} else {
			if (rand2 < redP && (BiomeDictionary.hasType(biome2, BiomeDictionary.Type.SANDY) || BiomeDictionary
					.hasType(biome2, BiomeDictionary.Type.DRY))) {
				ret[1] = getVeinFromSeed(world, pos2, EnumVein.RED, seed);
			}
			if ((100 - rand2) < blueP && (BiomeDictionary.hasType(biome2, BiomeDictionary.Type.SWAMP) || BiomeDictionary
					.hasType(biome2, BiomeDictionary.Type.WATER) || BiomeDictionary
							.hasType(biome2, BiomeDictionary.Type.SNOWY) || BiomeDictionary
									.hasType(biome2, BiomeDictionary.Type.COLD))) {
				ret[1] = getVeinFromSeed(world, pos2, EnumVein.BLUE, seed);
			}
			if (rand2 < greenP && (BiomeDictionary.hasType(biome2, BiomeDictionary.Type.FOREST) || BiomeDictionary
					.hasType(biome2, BiomeDictionary.Type.JUNGLE) || BiomeDictionary
							.hasType(biome2, BiomeDictionary.Type.CONIFEROUS) || BiomeDictionary
									.hasType(biome2, BiomeDictionary.Type.LUSH))) {
				ret[1] = getVeinFromSeed(world, pos2, EnumVein.GREEN, seed);
			}
			if ((100 - rand2) < whiteP && (BiomeDictionary
					.hasType(biome2, BiomeDictionary.Type.PLAINS) || BiomeDictionary
							.hasType(biome2, BiomeDictionary.Type.SAVANNA))) {
				ret[1] = getVeinFromSeed(world, pos2, EnumVein.WHITE, seed);
			}
		}

		int y3 = 85 + rand.nextInt(40);
		int x3 = x + 8 + rand.nextInt(8);
		int z3 = z + 8 + rand.nextInt(8);
		BlockPos pos3 = new BlockPos(x3, y3, z3);
		int rand3 = rand.nextInt(100);
		Biome biome3 = world.getBiome(pos3);
		if (rand3 < redP && (BiomeDictionary.hasType(biome3, BiomeDictionary.Type.MESA) || BiomeDictionary
				.hasType(biome2, BiomeDictionary.Type.MOUNTAIN) || BiomeDictionary
						.hasType(biome2, BiomeDictionary.Type.HILLS))) {
			ret[2] = getVeinFromSeed(world, pos3, EnumVein.HIGH_RED, seed);
		}

		int y4 = 140 + rand.nextInt(60);
		int x4 = x + 8 + rand.nextInt(8);
		int z4 = z + 8 + rand.nextInt(8);
		BlockPos pos4 = new BlockPos(x4, y4, z4);
		int rand4 = rand.nextInt(100);
		Biome biome4 = world.getBiome(pos4);
		if (rand4 < redP) {
			ret[3] = getVeinFromSeed(world, pos4, EnumVein.HIGH_RED, seed);
		}
		return ret;
	}

	public OreVein getNetherVeins(int cx, int cz, World world) {
		long seed = world.getSeed() + cx + cz * 31;
		Random rand = new Random(seed);
		rand.nextInt();
		rand.nextInt(100);
		rand.nextInt(8);
		int x = cx << 4;
		int z = cz << 4;

		int y1 = 25 + rand.nextInt(60);

		int x1 = x + 8 + rand.nextInt(8);
		int z1 = z + 8 + rand.nextInt(8);
		BlockPos pos1 = new BlockPos(x1, y1, z1);
		int rand1 = rand.nextInt(100);
		if (rand1 < netherP) {
			return getVeinFromSeed(world, pos1, EnumVein.NETHER, seed);
		}

		return null;
	}

	public OreVein getVeinFromSeed(World world, BlockPos pos, EnumVein type, long seed) {
		if (world != null && pos != null && type != null) {
			Random rand = new Random(seed);
			rand.nextInt();
			int round = getRange(type) + rand.nextInt(3);
			int[] set = new int[round];
			for (int i = 0; i < round; i++) {
				set[i] = rand.nextInt(20);
			}
			OreVein ret = new OreVein(type, pos, round, set);
			return ret;
		}
		return null;
	}

	public class OreVein {
		public final EnumVein type;
		public final BlockPos pos;
		public final int round;
		public final int[] rands;

		public OreVein(EnumVein vine, BlockPos p, int r, int[] s) {
			type = vine;
			pos = p;
			round = r;
			rands = s;
		}
	}

	private int getRange(EnumVein vein) {
		switch (vein) {
		case RED:
			return WorldGenConfig.radGen[0];
		case HIGH_RED:
			return WorldGenConfig.radGen[0];
		case GREEN:
			return WorldGenConfig.radGen[1];
		case BLUE:
			return WorldGenConfig.radGen[2];
		case WHITE:
			return WorldGenConfig.radGen[3];
		case BLACK:
			return WorldGenConfig.radGen[4];
		case GUANO:
			return WorldGenConfig.radGen[5];
		case SKARN_IRON:
			return vein.range;
		case SKARN_COPPER:
			return vein.range;
		case NETHER:
			return vein.range;
		default:
			return WorldGenConfig.radGen[0];
		}
	}

	public static boolean canWindmillGenChunk(int x, int z) {
		ChunkPos target = new ChunkPos(x, z);
		ChunkPos rem = null;
		double c = 0;
		if (!windmillPos.isEmpty()) {
			for (ChunkPos check : windmillPos) {
				int dx = x - check.x;
				int dz = z - check.z;
				double r = Math.sqrt(dx * dx + dz * dz);
				if (r < 16) {
					return false;
				} else {
					if (r > c) {
						c = r;
						rem = check;
					}
				}
			}
		}

		if (rem != null && windmillPos.size() > 2) {
			windmillPos.remove(rem);
		}
		windmillPos.add(target);
		return true;
	}

	public static boolean canCaravanChunk(int x, int z) {
		ChunkPos target = new ChunkPos(x, z);
		ChunkPos rem = null;
		double c = 0;
		for (ChunkPos check : caravanPos) {
			int dx = target.x - check.x;
			int dz = target.z - check.z;
			double r = Math.sqrt(dx * dx + dz * dz);
			if (r < 32) {
				return false;
			} else {
				if (r > c) {
					c = r;
					rem = check;
				}
			}
		}

		if (caravanPos.size() > 2) {
			caravanPos.remove(rem);
		}
		caravanPos.add(target);
		return true;
	}

}
