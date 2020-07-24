package defeatedcrow.hac.main.worldgen.vein;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.main.api.orevein.EnumVein;
import defeatedcrow.hac.main.api.orevein.IVeinTable;
import defeatedcrow.hac.main.api.orevein.IVeinTableRegister;
import defeatedcrow.hac.main.api.orevein.VeinTable;
import net.minecraft.init.Blocks;

public class VeinTableRegister implements IVeinTableRegister {

	private VeinTableRegister() {};

	public static VeinTableRegister INSTANCE = new VeinTableRegister();
	public static List<VeinTable> list = new ArrayList<VeinTable>();

	public VeinTable getTable(EnumVein vein) {
		for (VeinTable v : list) {
			if (v.vein == vein) {
				return v;
			}
		}
		return null;
	}

	@Override
	public IVeinTable getTableFromType(EnumVein type) {
		return getTable(type);
	}

	public static final BlockSet STONE = new BlockSet(Blocks.STONE, 0);
	private static final BlockSet STONE1 = new BlockSet(Blocks.STONE, 1);
	private static final BlockSet STONE2 = new BlockSet(Blocks.STONE, 3);
	private static final BlockSet STONE3 = new BlockSet(Blocks.STONE, 5);

	private static final BlockSet SAND = new BlockSet(Blocks.SANDSTONE, 0);

}
