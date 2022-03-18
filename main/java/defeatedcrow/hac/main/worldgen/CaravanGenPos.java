package defeatedcrow.hac.main.worldgen;

import java.util.Random;

import defeatedcrow.hac.main.config.WorldGenConfig;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

/**
 * キャラバンサライの生成座標
 */
public class CaravanGenPos {

	public static Chunk chunk = null;

	public static int isDupe(int cx, int cz, World world) {
		int count = 0;
		for (int x = -8; x < 9; x++) {
			for (int z = -8; z < 9; z++) {
				if (x == 0 && z == 0)
					continue;
				if (canGeneratePos(cx + x, cz + z, world)) {
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

	public static boolean isSuitableChunk(int cx, int cz, World world) {
		if (world.isRemote)
			return false;

		int genDim1 = world.provider.getDimension();
		if (genDim1 != 0)
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
		int[] r = new int[5];
		r[0] = rand.nextInt(4);
		r[1] = rand.nextInt(4);
		r[2] = rand.nextInt(4);
		r[3] = rand.nextInt(4);
		r[4] = rand.nextInt(4);
		return r;
	}

}
