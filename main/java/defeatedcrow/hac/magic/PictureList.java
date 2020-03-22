package defeatedcrow.hac.magic;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.WeakHashMap;

import defeatedcrow.hac.api.magic.MagicColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public class PictureList {

	public final static PictureList INSTANCE = new PictureList();

	public static final WeakHashMap<BlockPos, MagicColor> colorMap = new WeakHashMap<>();

	public static boolean hasColor(ChunkPos chunk, MagicColor color) {
		for (Entry<BlockPos, MagicColor> e : INSTANCE.colorMap.entrySet()) {
			if (color == e.getValue()) {
				ChunkPos c = new ChunkPos(e.getKey());
				if (Math.abs(chunk.x - c.x) < 3 && Math.abs(chunk.z - c.z) < 3) {
					return true;
				}
			}
		}
		return false;
	}

	public static void checkList(World world) {
		Iterator<BlockPos> itr = INSTANCE.colorMap.keySet().iterator();
		while (itr.hasNext()) {
			BlockPos p = itr.next();
			if (world.getTileEntity(p) == null) {
				itr.remove();
			}
		}
	}

}
