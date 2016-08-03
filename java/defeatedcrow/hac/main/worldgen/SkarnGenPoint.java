package defeatedcrow.hac.main.worldgen;

import net.minecraft.util.math.BlockPos;
import defeatedcrow.hac.core.DCLogger;

// ゲーム外に保存できないので保留中
public class SkarnGenPoint {

	public static final SkarnGenPoint INSTANCE = new SkarnGenPoint();

	private static BlockPos[] genPos = new BlockPos[10];

	public static int count = 0;
	public static int current = 0;

	private SkarnGenPoint() {
	}

	protected static void addPos(BlockPos pos) {
		if (current < 0 || current > 9) {
			current = 0;
		}
		genPos[current] = pos;

		current++;
		count++;
		DCLogger.debugLog("skarn! No." + count + ": " + pos.getX() + ", " + pos.getZ());
	}

}
