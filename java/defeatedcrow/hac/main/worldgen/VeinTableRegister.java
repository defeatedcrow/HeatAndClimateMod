package defeatedcrow.hac.main.worldgen;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.orevein.EnumVein;
import defeatedcrow.hac.main.api.orevein.IVeinTable;
import defeatedcrow.hac.main.api.orevein.IVeinTableRegister;
import defeatedcrow.hac.main.api.orevein.OreSet;
import net.minecraft.init.Blocks;

public class VeinTableRegister implements IVeinTableRegister {

	private VeinTableRegister() {};

	public static final VeinTableRegister INSTANCE = new VeinTableRegister();
	public static final List<VeinTable> list = new ArrayList<VeinTable>();

	public void registerVeins() {
		VeinTable sed = new VeinTable(EnumVein.SEDIMENT, new OreSet(100, new BlockSet(MainInit.ores, 0)),
				new OreSet(100, new BlockSet(MainInit.ores, 1)));
		sed.addOreToTable1(new OreSet(40, new BlockSet(MainInit.ores, 1)),
				new OreSet(40, new BlockSet(MainInit.ores, 0)), new OreSet(30, new BlockSet(Blocks.COAL_ORE, 0)),
				new OreSet(20, new BlockSet(Blocks.STONE, 3), new BlockSet(MainInit.ores, 2), 40));
		list.add(sed);

		VeinTable sand = new VeinTable(EnumVein.SAND_SEDIMENT, new OreSet(100, new BlockSet(MainInit.ores_2, 0)),
				new OreSet(100, new BlockSet(MainInit.ores_2, 0)));
		sand.addOreToTable1(new OreSet(40, new BlockSet(MainInit.ores_2, 1)),
				new OreSet(30, new BlockSet(MainInit.ores_2, 2)), new OreSet(30, new BlockSet(MainInit.ores_2, 0)));
		sand.addOreToTable2(new OreSet(40, new BlockSet(MainInit.ores_2, 1)),
				new OreSet(30, new BlockSet(MainInit.ores_2, 10)), new OreSet(30, new BlockSet(MainInit.ores_2, 0)));
		list.add(sand);

		VeinTable bx = new VeinTable(EnumVein.BAUXITE, new OreSet(100, new BlockSet(Blocks.STONE, 1)),
				new OreSet(100, new BlockSet(MainInit.ores_2, 10)));
		bx.addOreToTable1(new OreSet(40, new BlockSet(Blocks.STONE, 1)),
				new OreSet(60, new BlockSet(MainInit.ores_2, 10)));
		list.add(bx);

		VeinTable guano = new VeinTable(EnumVein.GUANO, new OreSet(100, new BlockSet(MainInit.ores_2, 12)),
				new OreSet(100, new BlockSet(MainInit.ores_2, 12)));
		guano.addOreToTable1(new OreSet(100, new BlockSet(MainInit.ores_2, 12)));
		list.add(guano);

		VeinTable ks = new VeinTable(EnumVein.KIESLAGER,
				new OreSet(100, new BlockSet(Blocks.STONE, 5), new BlockSet(MainInit.ores, 6), 50),
				new OreSet(100, new BlockSet(MainInit.ores, 6)));
		ks.addOreToTable1(new OreSet(40, new BlockSet(MainInit.ores, 6), new BlockSet(MainInit.ores, 7), 20),
				new OreSet(30, new BlockSet(MainInit.ores, 8), new BlockSet(MainInit.ores_2, 9), 5),
				new OreSet(20, new BlockSet(MainInit.ores, 4), new BlockSet(MainInit.ores, 7), 30),
				new OreSet(10, new BlockSet(MainInit.ores_2, 4)));
		ks.addOreToTable2(new OreSet(40, new BlockSet(MainInit.ores, 6), new BlockSet(MainInit.ores, 7), 20),
				new OreSet(30, new BlockSet(MainInit.ores, 8), new BlockSet(MainInit.ores_2, 9), 5),
				new OreSet(20, new BlockSet(MainInit.ores, 4), new BlockSet(MainInit.ores_2, 4), 20),
				new OreSet(10, new BlockSet(MainInit.ores, 7)));
		list.add(ks);

		VeinTable qt = new VeinTable(EnumVein.QUARTZ, new OreSet(100, new BlockSet(Blocks.STONE, 1)),
				new OreSet(100, new BlockSet(MainInit.ores, 9)));
		qt.addOreToTable1(new OreSet(30, new BlockSet(MainInit.ores, 8), new BlockSet(MainInit.ores_2, 9), 5),
				new OreSet(30, new BlockSet(MainInit.ores, 15)), new OreSet(10, new BlockSet(MainInit.ores, 10)),
				new OreSet(20, new BlockSet(MainInit.ores, 12)), new OreSet(10, new BlockSet(MainInit.ores, 11)));
		list.add(qt);

		VeinTable go = new VeinTable(EnumVein.GEODE, new OreSet(100, new BlockSet(Blocks.STONE, 1)),
				new OreSet(100, new BlockSet(MainInit.ores, 9)));
		go.addOreToTable1(new OreSet(20, new BlockSet(MainInit.ores, 9)),
				new OreSet(10, new BlockSet(MainInit.ores, 13)), new OreSet(10, new BlockSet(MainInit.ores, 14)),
				new OreSet(10, new BlockSet(MainInit.ores, 10)), new OreSet(20, new BlockSet(MainInit.ores, 12)),
				new OreSet(10, new BlockSet(MainInit.ores_2, 5)), new OreSet(10, new BlockSet(MainInit.ores_2, 11)),
				new OreSet(10, new BlockSet(Blocks.AIR, 0)));
		list.add(go);

		VeinTable lv = new VeinTable(EnumVein.UNDERLAVA, new OreSet(100, new BlockSet(MainInit.ores, 5)),
				new OreSet(100, new BlockSet(MainInit.ores_2, 6)));
		lv.addOreToTable1(new OreSet(40, new BlockSet(MainInit.ores, 5)),
				new OreSet(30, new BlockSet(MainInit.ores_2, 8)), new OreSet(30, new BlockSet(MainInit.ores_2, 6)));
		lv.addOreToTable2(new OreSet(30, new BlockSet(MainInit.ores, 5)),
				new OreSet(20, new BlockSet(MainInit.ores_2, 3)), new OreSet(30, new BlockSet(MainInit.ores_2, 6)),
				new OreSet(20, new BlockSet(MainInit.ores_2, 7)));
		list.add(lv);

		VeinTable sk = new VeinTable(EnumVein.SKARN, new OreSet(100, new BlockSet(MainInit.ores_2, 0)),
				new OreSet(100, new BlockSet(MainInit.gemBlock, 6)));
		sk.addOreToTable1(new OreSet(30, new BlockSet(MainInit.ores, 6)),
				new OreSet(30, new BlockSet(MainInit.ores, 8), new BlockSet(MainInit.ores_2, 9), 5),
				new OreSet(20, new BlockSet(MainInit.ores, 4)), new OreSet(20, new BlockSet(MainInit.ores_2, 4)));
		sk.addOreToTable1(new OreSet(30, new BlockSet(MainInit.ores, 6)),
				new OreSet(30, new BlockSet(MainInit.ores, 8), new BlockSet(MainInit.ores_2, 9), 5),
				new OreSet(20, new BlockSet(MainInit.ores, 1)), new OreSet(20, new BlockSet(MainInit.ores_2, 4)));
		list.add(sk);

		VeinTable sku = new VeinTable(EnumVein.SKARN_UNDER, new OreSet(100, new BlockSet(MainInit.ores_2, 0)),
				new OreSet(100, new BlockSet(MainInit.gemBlock, 6)));
		sku.addOreToTable2(new OreSet(50, new BlockSet(MainInit.ores, 5)),
				new OreSet(40, new BlockSet(Blocks.GOLD_ORE, 0)), new OreSet(10, new BlockSet(Blocks.STONE, 5)));
		sku.addOreToTable2(new OreSet(40, new BlockSet(MainInit.ores, 5)),
				new OreSet(40, new BlockSet(Blocks.GOLD_ORE, 0)), new OreSet(20, new BlockSet(Blocks.STONE, 5)));
		list.add(sku);
	}

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

}
