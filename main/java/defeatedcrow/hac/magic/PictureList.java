package defeatedcrow.hac.magic;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.MagicColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public class PictureList {

	public final static PictureList INSTANCE = new PictureList();

	public static final ConcurrentHashMap<BlockPos, MagicColor> colorMap = new ConcurrentHashMap<>();

	public static boolean hasColor(ChunkPos chunk, MagicColor color) {
		Map<BlockPos, MagicColor> map = Collections.synchronizedMap(colorMap);
		synchronized (map) {
			for (Entry<BlockPos, MagicColor> e : map.entrySet()) {
				if (color == e.getValue()) {
					ChunkPos c = new ChunkPos(e.getKey());
					if (Math.abs(chunk.x - c.x) < 3 && Math.abs(chunk.z - c.z) < 3) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static BlockPos getPos(ChunkPos chunk, MagicColor color) {
		Map<BlockPos, MagicColor> map = Collections.synchronizedMap(colorMap);
		synchronized (map) {
			for (Entry<BlockPos, MagicColor> e : map.entrySet()) {
				if (color == e.getValue()) {
					ChunkPos c = new ChunkPos(e.getKey());
					if (Math.abs(chunk.x - c.x) < 3 && Math.abs(chunk.z - c.z) < 3) {
						return new BlockPos(e.getKey());
					}
				}
			}
		}
		return null;
	}

	public static void checkList(World world) {
		Iterator<BlockPos> itr = INSTANCE.colorMap.keySet().iterator();
		List<BlockPos> remove = Lists.newArrayList();
		while (itr.hasNext()) {
			BlockPos p = itr.next();
			if (!world.isBlockLoaded(p) || world.getTileEntity(p) == null) {
				itr.remove();
			}
		}
	}

}
