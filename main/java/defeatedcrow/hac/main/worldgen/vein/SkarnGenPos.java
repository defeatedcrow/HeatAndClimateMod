package defeatedcrow.hac.main.worldgen.vein;

import java.util.Random;

import defeatedcrow.hac.main.config.WorldGenConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

/**
 * スカルンの生成をキャラバン方式にする
 */
public class SkarnGenPos {

	public static Chunk chunk = null;

	public static boolean isDupe(int cx, int cz, World world) {
		if (!WorldGenConfig.skarnDupeCheck)
			return false;
		/* チャンクを16x16区画に分け、その範囲内で生成は1個までとする */
		int ccx = cx >> 4;
		int ccz = cz >> 4;
		ccx *= 16;
		ccz *= 16;
		int count = 0;
		for (int x = ccx; x < ccx + 16; x++) {
			for (int z = ccz; ccz + z < 16; z++) {
				if (x == cx && z == cz)
					continue;
				if (canGeneratePos(x, z, world)) {
					if (x >= cx || z >= cz) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean hasCenterPoint(int cx, int cz, World world) {
		BlockPos ret = null;
		int count = 100;
		for (int x = -2; x < 3; x++) {
			for (int z = -2; z < 3; z++) {
				if (canGeneratePos(cx + x, cz + z, world)) {
					return true;
				}
			}
		}
		return false;
	}

	public static BlockPos getNearestPoint(int cx, int cz, World world) {
		BlockPos ret = null;
		int count = 100;
		for (int x = -2; x < 3; x++) {
			for (int z = -2; z < 3; z++) {
				if (canGeneratePos(cx + x, cz + z, world)) {
					int i = x * x + z * z;
					if (i < count) {
						count = i;
						int rx = cx + x;
						int rz = cz + z;
						ret = new BlockPos(rx * 16 + 8, 40, rz * 16 + 8);
					}
				}
			}
		}
		if (ret != null)
			return ret;
		return null;
	}

	public static int getGenHeight(int cx, int cz, World world, BlockPos pos) {
		int h = -1;
		int count = 100;
		for (int x = -2; x < 3; x++) {
			for (int z = -2; z < 3; z++) {
				if (canGeneratePos(cx + x, cz + z, world)) {
					int i = x * x + z * z;
					if (i < count) {
						count = i;
						int rx = cx + x;
						int rz = cz + z;
						int px = rx * 16 + 8 - pos.getX();
						int pz = rz * 16 + 8 - pos.getZ();
						double d = MathHelper.sqrt(px * px + pz * pz);
						h = 40 - MathHelper.floor(d);
					}
				}
			}
		}
		if (h > 0)
			return h;
		return -1;
	}

	public static int getGenHeight(World world, BlockPos pos, BlockPos from) {
		int h = -1;
		if (pos != null && from != null) {
			int px = from.getX() - pos.getX();
			int pz = from.getZ() - pos.getZ();
			double d = MathHelper.sqrt(px * px + pz * pz);
			h = from.getY() - MathHelper.floor(d);
		}
		if (h > 0)
			return h;
		return -1;
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
		long seed = world.getSeed() + cx + cz * 17;
		Random rand = new Random(seed);
		rand.nextInt(10000);
		int r = rand.nextInt(10000);
		if (r > 0 && r < WorldGenConfig.skarnGen) {
			return true;
		}
		return false;
	}

}
