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

	private static int sedP = WorldGenConfig.depositGen[0];
	private static int kiesP = WorldGenConfig.depositGen[1];
	private static int veinP = WorldGenConfig.depositGen[2];
	private static int lavaP = WorldGenConfig.depositGen[3];
	private static int vugsP = WorldGenConfig.depositGen[4];

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

		int y1 = 5 + rand.nextInt(20);
		int x1 = x + 8 + rand.nextInt(8);
		int z1 = z + 8 + rand.nextInt(8);
		BlockPos pos1 = new BlockPos(x1, y1, z1);
		int rand1 = rand.nextInt(100);
		if (y1 < 18 && rand1 < lavaP) {
			ret[0] = getVeinFromSeed(world, pos1, EnumVein.UNDERLAVA, seed);
		} else if (rand1 < vugsP) {
			ret[0] = getVeinFromSeed(world, pos1, EnumVein.GEODE, seed);
		}

		int y2 = 30 + rand.nextInt(30);
		int x2 = x + 8 + rand.nextInt(8);
		int z2 = z + 8 + rand.nextInt(8);
		BlockPos pos2 = new BlockPos(x2, y2, z2);
		int rand2 = rand.nextInt(100);
		Biome biome2 = world.getBiome(pos2);
		if (BiomeDictionary.hasType(biome2, BiomeDictionary.Type.BEACH) && rand2 < kiesP) {
			ret[1] = getVeinFromSeed(world, pos2, EnumVein.GUANO, seed);
		} else if (BiomeDictionary.hasType(biome2, BiomeDictionary.Type.MOUNTAIN) && rand2 < kiesP) {
			ret[1] = getVeinFromSeed(world, pos2, EnumVein.KIESLAGER, seed);
		} else if (BiomeDictionary.hasType(biome2, BiomeDictionary.Type.OCEAN) && rand2 < kiesP) {
			if (y2 > 45) {
				ret[1] = getVeinFromSeed(world, pos2, EnumVein.GUANO, seed);
			} else {
				ret[1] = getVeinFromSeed(world, pos2, EnumVein.KIESLAGER, seed);
			}
		} else if (rand2 < veinP) {
			ret[1] = getVeinFromSeed(world, pos2, EnumVein.QUARTZ, seed);
		}

		int y3 = 80 + rand.nextInt(40);
		int x3 = x + 8 + rand.nextInt(8);
		int z3 = z + 8 + rand.nextInt(8);
		BlockPos pos3 = new BlockPos(x3, y3, z3);
		int rand3 = rand.nextInt(100);
		Biome biome3 = world.getBiome(pos3);
		if ((BiomeDictionary.hasType(biome3, BiomeDictionary.Type.SANDY)) && rand3 < sedP) {
			ret[2] = getVeinFromSeed(world, pos3, EnumVein.SAND_SEDIMENT, seed);
		} else if ((BiomeDictionary.hasType(biome3, BiomeDictionary.Type.SAVANNA)
				|| BiomeDictionary.hasType(biome3, BiomeDictionary.Type.JUNGLE)) && rand3 < sedP) {
			ret[2] = getVeinFromSeed(world, pos3, EnumVein.BAUXITE, seed);
		} else if ((BiomeDictionary.hasType(biome3, BiomeDictionary.Type.MOUNTAIN)
				|| BiomeDictionary.hasType(biome3, BiomeDictionary.Type.HILLS)) && rand3 < sedP) {
			ret[2] = getVeinFromSeed(world, pos3, EnumVein.SEDIMENT, seed);
		}

		int y4 = 120 + rand.nextInt(60);
		int x4 = x + 8 + rand.nextInt(8);
		int z4 = z + 8 + rand.nextInt(8);
		BlockPos pos4 = new BlockPos(x4, y4, z4);
		int rand4 = rand.nextInt(100);
		Biome biome4 = world.getBiome(pos4);
		if (rand4 < sedP) {
			ret[3] = getVeinFromSeed(world, pos4, EnumVein.HIGH_SEDIMENT, seed);
		}
		return ret;
	}

	public OreVein getVeinFromSeed(World world, BlockPos pos, EnumVein type, long seed) {
		if (world != null && pos != null && type != null) {
			Random rand = new Random(seed);
			rand.nextInt();
			int round = type.range + rand.nextInt(4);
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

}
