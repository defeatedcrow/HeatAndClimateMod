package defeatedcrow.hac.main.worldgen;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.orevein.EnumVein;
import defeatedcrow.hac.main.api.orevein.IVeinTable;
import defeatedcrow.hac.main.api.orevein.IVeinTableRegister;
import defeatedcrow.hac.main.api.orevein.VeinTable;
import defeatedcrow.hac.main.api.orevein.VeinTableRegisterEvent;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;

public class VeinTableRegister implements IVeinTableRegister {

	private VeinTableRegister() {};

	public static VeinTableRegister INSTANCE = new VeinTableRegister();
	public static List<VeinTable> list = new ArrayList<VeinTable>();

	public void registerVeins() {
		VeinTable h_sed = new VeinTable(EnumVein.HIGH_SEDIMENT, new OreSetDC(100, new BlockSet(MainInit.ores, 0)),
				new OreSetDC(100, new BlockSet(MainInit.ores, 1)));
		h_sed.addOreToTable1(new OreSetDC(50, new BlockSet(MainInit.ores, 0)), new OreSetDC(30, new BlockSet(
				MainInit.ores, 1)), new OreSetDC(30, new BlockSet(Blocks.COAL_ORE, 0)), new OreSetDC(20, new BlockSet(
						Blocks.STONE, 3), new BlockSet(MainInit.ores, 2), 40));
		h_sed.addOreToTable2(new OreSetDC(50, new BlockSet(MainInit.ores, 0)), new OreSetDC(30, new BlockSet(
				MainInit.ores, 1)), new OreSetDC(30, new BlockSet(Blocks.COAL_ORE, 0)), new OreSetDC(20, new BlockSet(
						Blocks.STONE, 3), new BlockSet(MainInit.ores, 2), 40));
		list.add(h_sed);

		VeinTable sed = new VeinTable(EnumVein.SEDIMENT, new OreSetDC(100, new BlockSet(MainInit.ores, 0)),
				new OreSetDC(100, new BlockSet(MainInit.ores, 1)));
		sed.addOreToTable1(new OreSetDC(30, new BlockSet(MainInit.ores, 1)), new OreSetDC(50, new BlockSet(
				MainInit.ores, 0)), new OreSetDC(30, new BlockSet(Blocks.COAL_ORE, 0)), new OreSetDC(20, new BlockSet(
						Blocks.STONE, 3), new BlockSet(MainInit.ores, 2), 40));
		sed.addOreToTable2(new OreSetDC(30, new BlockSet(MainInit.ores, 1)), new OreSetDC(50, new BlockSet(
				MainInit.ores, 0)), new OreSetDC(30, new BlockSet(Blocks.COAL_ORE, 0)), new OreSetDC(20, new BlockSet(
						Blocks.STONE, 3), new BlockSet(MainInit.ores, 2), 40));
		list.add(sed);

		VeinTable sand = new VeinTable(EnumVein.SAND_SEDIMENT, new OreSetDC(100, new BlockSet(MainInit.ores_2, 0),
				SAND), new OreSetDC(100, new BlockSet(MainInit.ores_2, 2), SAND));
		sand.addOreToTable1(new OreSetDC(30, new BlockSet(MainInit.ores_2, 1), SAND), new OreSetDC(30, new BlockSet(
				MainInit.ores_2, 10), SAND), new OreSetDC(30, new BlockSet(MainInit.ores_2, 2), SAND), new OreSetDC(30,
						new BlockSet(MainInit.ores_2, 0), SAND));
		sand.addOreToTable2(new OreSetDC(30, new BlockSet(MainInit.ores_2, 1)), new OreSetDC(30, new BlockSet(
				MainInit.ores_2, 10), SAND), new OreSetDC(30, new BlockSet(MainInit.ores_2, 0), SAND), new OreSetDC(30,
						new BlockSet(MainInit.ores_2, 2), SAND));
		list.add(sand);

		VeinTable bx = new VeinTable(EnumVein.BAUXITE, new OreSetDC(100, new BlockSet(MainInit.ores_2, 10), STONE3),
				new OreSetDC(100, new BlockSet(MainInit.ores_2, 10), STONE3));
		bx.addOreToTable1(new OreSetDC(30, new BlockSet(Blocks.STONE, 1), STONE3), new OreSetDC(70, new BlockSet(
				MainInit.ores_2, 10), STONE3));
		bx.addOreToTable2(new OreSetDC(30, new BlockSet(Blocks.STONE, 1), STONE3), new OreSetDC(70, new BlockSet(
				MainInit.ores_2, 10), STONE3));
		list.add(bx);

		VeinTable guano = new VeinTable(EnumVein.GUANO, new OreSetDC(100, new BlockSet(MainInit.ores_2, 12)),
				new OreSetDC(100, new BlockSet(MainInit.ores_2, 12)));
		guano.addOreToTable1(new OreSetDC(100, new BlockSet(MainInit.ores_2, 12), new BlockSet(Blocks.GRAVEL, 0), 20));
		guano.addOreToTable2(new OreSetDC(100, new BlockSet(MainInit.ores_2, 12), new BlockSet(Blocks.GRAVEL, 0), 20));
		list.add(guano);

		VeinTable ks = new VeinTable(EnumVein.KIESLAGER, new OreSetDC(100, new BlockSet(Blocks.STONE, 5), new BlockSet(
				MainInit.ores, 6), 50), new OreSetDC(100, new BlockSet(MainInit.ores, 6)));
		ks.addOreToTable1(new OreSetDC(40, new BlockSet(MainInit.ores, 6), new BlockSet(MainInit.ores, 7),
				20), new OreSetDC(30, new BlockSet(MainInit.ores, 8), new BlockSet(MainInit.ores_2, 9),
						5), new OreSetDC(20, new BlockSet(MainInit.ores, 4), new BlockSet(MainInit.ores, 7),
								30), new OreSetDC(10, new BlockSet(MainInit.ores_2, 4)));
		ks.addOreToTable2(new OreSetDC(40, new BlockSet(MainInit.ores, 6), new BlockSet(MainInit.ores, 7),
				20), new OreSetDC(30, new BlockSet(MainInit.ores, 8), new BlockSet(MainInit.ores_2, 9),
						5), new OreSetDC(20, new BlockSet(MainInit.ores, 4), new BlockSet(MainInit.ores_2, 4),
								20), new OreSetDC(10, new BlockSet(MainInit.ores, 7)));
		list.add(ks);

		VeinTable qt = new VeinTable(EnumVein.QUARTZ, new OreSetDC(100, new BlockSet(Blocks.STONE, 1), false),
				new OreSetDC(100, new BlockSet(MainInit.ores, 9), false));
		qt.addOreToTable1(new OreSetDC(30, new BlockSet(MainInit.ores, 8), new BlockSet(MainInit.ores_2, 9), 5,
				CHAL), new OreSetDC(30, new BlockSet(MainInit.ores, 15), CHAL), new OreSetDC(10, new BlockSet(
						MainInit.ores, 10), CHAL), new OreSetDC(20, new BlockSet(MainInit.ores, 12),
								CHAL), new OreSetDC(10, new BlockSet(MainInit.ores, 11), CHAL));
		qt.addOreToTable2(new OreSetDC(30, new BlockSet(MainInit.ores, 8), new BlockSet(MainInit.ores_2, 9), 5,
				CHAL), new OreSetDC(30, new BlockSet(MainInit.ores, 15), CHAL), new OreSetDC(10, new BlockSet(
						MainInit.ores, 10), CHAL), new OreSetDC(20, new BlockSet(MainInit.ores, 12),
								CHAL), new OreSetDC(10, new BlockSet(MainInit.ores, 11), CHAL));
		list.add(qt);

		VeinTable go = new VeinTable(EnumVein.GEODE, new OreSetDC(100, new BlockSet(Blocks.STONE, 1), false),
				new OreSetDC(100, new BlockSet(MainInit.ores, 9), false));
		go.addOreToTable1(new OreSetDC(20, new BlockSet(MainInit.ores, 9), false), new OreSetDC(10, new BlockSet(
				MainInit.ores, 13), CHAL), new OreSetDC(10, new BlockSet(MainInit.ores, 14), CHAL), new OreSetDC(10,
						new BlockSet(MainInit.ores, 10), CHAL), new OreSetDC(20, new BlockSet(MainInit.ores, 12),
								CHAL), new OreSetDC(10, new BlockSet(MainInit.ores_2, 5), CHAL), new OreSetDC(10,
										new BlockSet(MainInit.ores_2, 11), CHAL), new OreSetDC(10, new BlockSet(
												Blocks.AIR, 0), false));
		go.addOreToTable2(new OreSetDC(20, new BlockSet(MainInit.ores, 9), false), new OreSetDC(10, new BlockSet(
				MainInit.ores, 13), CHAL), new OreSetDC(10, new BlockSet(MainInit.ores, 14), CHAL), new OreSetDC(10,
						new BlockSet(MainInit.ores, 10), CHAL), new OreSetDC(20, new BlockSet(MainInit.ores, 12),
								CHAL), new OreSetDC(10, new BlockSet(MainInit.ores_2, 5), CHAL), new OreSetDC(10,
										new BlockSet(MainInit.ores_2, 11), CHAL), new OreSetDC(10, new BlockSet(
												Blocks.AIR, 0), false));
		list.add(go);

		VeinTable lv = new VeinTable(EnumVein.UNDERLAVA, new OreSetDC(100, new BlockSet(MainInit.ores, 5)),
				new OreSetDC(100, new BlockSet(MainInit.ores_2, 6)));
		lv.addOreToTable1(new OreSetDC(40, new BlockSet(MainInit.ores, 5)), new OreSetDC(30, new BlockSet(
				MainInit.ores_2, 8)), new OreSetDC(30, new BlockSet(MainInit.ores_2, 6)));
		lv.addOreToTable2(new OreSetDC(30, new BlockSet(MainInit.ores, 5)), new OreSetDC(20, new BlockSet(
				MainInit.ores_2, 3)), new OreSetDC(30, new BlockSet(MainInit.ores_2, 6)), new OreSetDC(20, new BlockSet(
						MainInit.ores_2, 7)));
		list.add(lv);

		VeinTable sk = new VeinTable(EnumVein.SKARN, new OreSetDC(100, new BlockSet(MainInit.ores_2, 0), false),
				new OreSetDC(100, new BlockSet(MainInit.gemBlock, 6), false));
		sk.addOreToTable1(new OreSetDC(30, new BlockSet(MainInit.ores, 6)), new OreSetDC(30, new BlockSet(MainInit.ores,
				8), new BlockSet(MainInit.ores_2, 9), 5), new OreSetDC(20, new BlockSet(MainInit.ores,
						4)), new OreSetDC(20, new BlockSet(MainInit.ores_2, 4)));
		sk.addOreToTable2(new OreSetDC(30, new BlockSet(MainInit.ores, 6)), new OreSetDC(30, new BlockSet(MainInit.ores,
				8), new BlockSet(MainInit.ores_2, 9), 5), new OreSetDC(20, new BlockSet(Blocks.STONE, 3)), new OreSetDC(
						30, new BlockSet(MainInit.gemBlock, 6)));
		list.add(sk);

		VeinTable sku = new VeinTable(EnumVein.SKARN_UNDER, new OreSetDC(100, new BlockSet(MainInit.ores_2, 0), false),
				new OreSetDC(100, new BlockSet(MainInit.gemBlock, 6), false));
		sku.addOreToTable1(new OreSetDC(50, new BlockSet(MainInit.ores, 5)), new OreSetDC(40, new BlockSet(
				Blocks.GOLD_ORE, 0)), new OreSetDC(10, new BlockSet(Blocks.STONE, 5)));
		sku.addOreToTable2(new OreSetDC(50, new BlockSet(MainInit.ores, 5)), new OreSetDC(40, new BlockSet(
				Blocks.GOLD_ORE, 0)), new OreSetDC(10, new BlockSet(Blocks.STONE, 5)));
		list.add(sku);

		// event
		VeinTableRegisterEvent event = new VeinTableRegisterEvent(INSTANCE);
		MinecraftForge.EVENT_BUS.post(event);
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

	private static final BlockSet AIR = new BlockSet(Blocks.AIR, 0);

	private static final BlockSet STONE = new BlockSet(Blocks.STONE, 0);
	private static final BlockSet STONE1 = new BlockSet(Blocks.STONE, 1);
	private static final BlockSet STONE2 = new BlockSet(Blocks.STONE, 3);
	private static final BlockSet STONE3 = new BlockSet(Blocks.STONE, 5);

	private static final BlockSet SAND = new BlockSet(Blocks.SANDSTONE, 0);
	private static final BlockSet CHAL = new BlockSet(MainInit.ores, 9);

}
