package defeatedcrow.hac.main.worldgen;

import java.util.Random;

import defeatedcrow.hac.main.api.orevein.EnumVein;
import defeatedcrow.hac.main.config.WorldGenConfig;
import net.minecraft.util.math.BlockPos;
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

	public static final OreGenPos INSTANCE = new OreGenPos();

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

		int y2 = 30 + rand.nextInt(30);

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
			if ((BiomeDictionary.hasType(biome2, BiomeDictionary.Type.SWAMP) || BiomeDictionary
					.hasType(biome2, BiomeDictionary.Type.WATER) || BiomeDictionary
							.hasType(biome2, BiomeDictionary.Type.SNOWY) || BiomeDictionary
									.hasType(biome2, BiomeDictionary.Type.COLD)) && rand2 < blueP) {
				ret[1] = getVeinFromSeed(world, pos2, EnumVein.BLUE, seed);
			}
			if ((BiomeDictionary.hasType(biome2, BiomeDictionary.Type.FOREST) || BiomeDictionary
					.hasType(biome2, BiomeDictionary.Type.JUNGLE) || BiomeDictionary
							.hasType(biome2, BiomeDictionary.Type.CONIFEROUS) || BiomeDictionary
									.hasType(biome2, BiomeDictionary.Type.LUSH)) && rand2 < greenP) {
				ret[1] = getVeinFromSeed(world, pos2, EnumVein.GREEN, seed);
			}
			if ((BiomeDictionary.hasType(biome2, BiomeDictionary.Type.PLAINS) || BiomeDictionary
					.hasType(biome2, BiomeDictionary.Type.SAVANNA)) && rand2 < whiteP) {
				ret[1] = getVeinFromSeed(world, pos2, EnumVein.WHITE, seed);
			}
		}

		int y3 = 80 + rand.nextInt(40);
		int x3 = x + 8 + rand.nextInt(8);
		int z3 = z + 8 + rand.nextInt(8);
		BlockPos pos3 = new BlockPos(x3, y3, z3);
		int rand3 = rand.nextInt(100);
		Biome biome3 = world.getBiome(pos3);
		if ((BiomeDictionary.hasType(biome3, BiomeDictionary.Type.SANDY) || BiomeDictionary
				.hasType(biome2, BiomeDictionary.Type.DRY) || BiomeDictionary
						.hasType(biome2, BiomeDictionary.Type.MESA) || BiomeDictionary
								.hasType(biome2, BiomeDictionary.Type.MOUNTAIN) || BiomeDictionary
										.hasType(biome2, BiomeDictionary.Type.HILLS)) && rand3 < redP) {
			ret[2] = getVeinFromSeed(world, pos3, EnumVein.RED, seed);
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
		case SKARN:
			return vein.range;
		case SKARN_UNDER:
			return vein.range;
		default:
			return WorldGenConfig.radGen[0];
		}
	}

}
