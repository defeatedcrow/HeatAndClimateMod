package defeatedcrow.hac.main.util;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class DCCoordinate {

	public final int x;
	public final int z;
	public final int dim;

	public DCCoordinate(int i, int j, int d) {
		x = i;
		z = j;
		dim = d;
	}

	public Chunk getChunk(World world) {
		return world.getChunkFromChunkCoords(x, z);
	}

	public boolean sameCood(int i, int j, int d) {
		return i == x && j == z && dim == d;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof DCCoordinate) {
			DCCoordinate p = (DCCoordinate) obj;
			return p.x == x && p.z == z && p.dim == dim;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int i = x * 13 + z * 953;
		return i;
	}

}
