package defeatedcrow.hac.core.worldgen.vein;

import java.util.List;
import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;

public class OreGenPos {
	public static final OreGenPos INSTANCE = new OreGenPos();

	public OreVein[] getVeins(ChunkPos chunk, WorldGenLevel world) {
		long seed = world.getSeed() + chunk.x + chunk.z * 31;
		Random rand = new Random(seed);
		rand.nextInt();
		rand.nextInt(100);
		rand.nextInt(20);
		rand.nextInt(30);
		rand.nextInt(40);
		rand.nextInt(60);
		rand.nextInt(8);

		// 浅層、深層で2個生成する
		OreVein[] ret = new OreVein[4];

		int y1 = 64;
		int x1 = chunk.getMinBlockX() + 2 + rand.nextInt(12);
		int z1 = chunk.getMinBlockZ() + 2 + rand.nextInt(12);
		BlockPos pos1 = new BlockPos(x1, y1, z1);
		Holder<Biome> biome1 = world.getBiome(pos1);

		List<VeinTable> table1 = VeinTableRegister.INSTANCE.getMachtTable(biome1, false);
		if (!table1.isEmpty()) {
			VeinTable get = table1.get(rand.nextInt(table1.size()));
			if (rand.nextInt(100) < get.generateProbability) {
				ret[0] = getVeinFromSeed(world, pos1, get, seed);
			}
		}

		int y2 = 64;
		int x2 = chunk.getMinBlockX() + 2 + rand.nextInt(12);
		int z2 = chunk.getMinBlockZ() + 2 + rand.nextInt(12);
		BlockPos pos2 = new BlockPos(x2, y2, z2);
		Holder<Biome> biome2 = world.getBiome(pos2);

		List<VeinTable> table2 = VeinTableRegister.INSTANCE.getMachtTable(biome2, false);
		if (!table2.isEmpty()) {
			VeinTable get = table2.get(rand.nextInt(table2.size()));
			if (rand.nextInt(100) < get.generateProbability) {
				ret[1] = getVeinFromSeed(world, pos2, get, seed);
			}
		}

		int y3 = 64;
		int x3 = chunk.getMinBlockX() + 2 + rand.nextInt(12);
		int z3 = chunk.getMinBlockZ() + 2 + rand.nextInt(12);
		BlockPos pos3 = new BlockPos(x3, y3, z3);
		Holder<Biome> biome3 = world.getBiome(pos3);

		List<VeinTable> table3 = VeinTableRegister.INSTANCE.getMachtTable(biome3, true);
		if (!table3.isEmpty()) {
			VeinTable get = table3.get(rand.nextInt(table3.size()));
			if (rand.nextInt(100) < get.generateProbability) {
				ret[2] = getVeinFromSeed(world, pos3, get, seed);
			}
		}

		return ret;
	}

	public OreVein getVeinFromSeed(WorldGenLevel world, BlockPos pos, VeinTable vein, long seed) {
		if (world != null && pos != null && vein != null) {
			Random rand = new Random(seed + pos.getX() + pos.getZ());
			rand.nextInt();
			int round = vein.radius + rand.nextInt(3);
			int alt = vein.altitude + rand.nextInt(20) - rand.nextInt(20);
			int[] set = new int[round];
			for (int i = 0; i < round; i++) {
				set[i] = rand.nextInt(vein.getWeightCount());
			}
			OreVein ret = new OreVein(vein, new BlockPos(pos.getX(), alt, pos.getZ()), round, alt, set);
			return ret;
		}
		return null;
	}

	public class OreVein {
		public final VeinTable table;
		public final BlockPos pos;
		public final int round;
		public final int altitude;
		public final int[] rands;

		public OreVein(VeinTable tableIn, BlockPos p, int r, int a, int[] s) {
			table = tableIn;
			pos = p;
			round = r;
			altitude = a;
			rands = s;
		}
	}
}
