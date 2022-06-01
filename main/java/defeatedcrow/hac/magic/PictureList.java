package defeatedcrow.hac.magic;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.main.api.DimCoord;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public class PictureList {

	public final static PictureList INSTANCE = new PictureList();

	public static final ConcurrentHashMap<DimCoord, MagicColor> colorMap = new ConcurrentHashMap<>();

	public static boolean hasColor(int dim, ChunkPos chunk, MagicColor color) {
		Map<DimCoord, MagicColor> map = Collections.synchronizedMap(colorMap);
		synchronized (map) {
			for (Entry<DimCoord, MagicColor> e : map.entrySet()) {
				if (dim == e.getKey().getDim() && color == e.getValue()) {
					ChunkPos c = new ChunkPos(e.getKey().getPos());
					if (Math.abs(chunk.x - c.x) < 3 && Math.abs(chunk.z - c.z) < 3) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static BlockPos getPos(int dim, ChunkPos chunk, MagicColor color) {
		Map<DimCoord, MagicColor> map = Collections.synchronizedMap(colorMap);
		synchronized (map) {
			for (Entry<DimCoord, MagicColor> e : map.entrySet()) {
				if (dim == e.getKey().getDim() && color == e.getValue()) {
					ChunkPos c = new ChunkPos(e.getKey().getPos());
					if (Math.abs(chunk.x - c.x) < 3 && Math.abs(chunk.z - c.z) < 3) {
						return new BlockPos(e.getKey().getPos());
					}
				}
			}
		}
		return null;
	}

	public static void checkList(World world) {
		Iterator<DimCoord> itr = INSTANCE.colorMap.keySet().iterator();
		List<DimCoord> remove = Lists.newArrayList();
		while (itr.hasNext()) {
			DimCoord c = itr.next();
			int dim = c.getDim();
			BlockPos p = c.getPos();
			if (world.provider.getDimension() == dim) {
				if (!world.isBlockLoaded(p) || world.getTileEntity(p) == null) {
					itr.remove();
				}
			}
		}
	}

}
