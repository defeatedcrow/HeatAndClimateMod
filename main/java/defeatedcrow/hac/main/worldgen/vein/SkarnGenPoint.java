package defeatedcrow.hac.main.worldgen.vein;

import defeatedcrow.hac.core.DCLogger;

// ゲーム外に保存できないので保留中
@Deprecated
public class SkarnGenPoint {

	public static final SkarnGenPoint INSTANCE = new SkarnGenPoint();

	private static ChunkCoord[] genPos = new ChunkCoord[5];

	public static int count = 0;
	public static int current = 0;

	private SkarnGenPoint() {}

	protected static void addPos(int x, int z) {
		ChunkCoord c = INSTANCE.new ChunkCoord(x, z);
		if (current < 0 || current >= genPos.length) {
			current = 0;
		}
		genPos[current] = c;

		current++;
		count++;
		DCLogger.debugLog("skarn! No." + count + ": " + c.x + ", " + c.z);
	}

	protected static boolean hasPos(int x, int z) {
		ChunkCoord c = INSTANCE.new ChunkCoord(x, z);
		boolean ret = false;
		for (ChunkCoord check : genPos) {
			if (check != null && c.equals(check)) {
				ret = true;
			}
		}
		return ret;
	}

	public class ChunkCoord {
		final int x;
		final int z;
		final int hash;

		ChunkCoord(int x2, int z2) {
			x = x2;
			z = z2;
			hash = x + z * 3001;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj instanceof ChunkCoord) {
				ChunkCoord coord = (ChunkCoord) obj;
				return this.x != coord.x ? false : this.z == coord.z;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return hash;
		}

	}

}
