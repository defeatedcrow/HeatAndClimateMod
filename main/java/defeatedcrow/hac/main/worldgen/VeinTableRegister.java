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
		VeinTable red = new VeinTable(EnumVein.RED, new OreSetDC(100, new BlockSet(MainInit.layerNew, 1)));
		red.addOreToTable1(new OreSetDC(80, new BlockSet(MainInit.oreNew, 0), new BlockSet(MainInit.oreNew, 5),
				5), new OreSetDC(20, new BlockSet(MainInit.layerNew, 3)), new OreSetDC(40, new BlockSet(
						MainInit.layerNew, 1)), new OreSetDC(5, new BlockSet(MainInit.oreNew, 5)));
		list.add(red);

		VeinTable red_s = new VeinTable(EnumVein.HIGH_RED, new OreSetDC(100, new BlockSet(MainInit.layerNew, 1)));
		red_s.addOreToTable1(new OreSetDC(60, new BlockSet(MainInit.oreNew, 0), new BlockSet(MainInit.oreNew, 5),
				5), new OreSetDC(20, new BlockSet(MainInit.layerNew, 3)), new OreSetDC(50, new BlockSet(
						MainInit.layerNew, 1)), new OreSetDC(10, new BlockSet(MainInit.oreNew, 5)));
		list.add(red_s);

		VeinTable green = new VeinTable(EnumVein.GREEN, new OreSetDC(100, new BlockSet(MainInit.layerNew, 6)));
		green.addOreToTable1(new OreSetDC(60, new BlockSet(MainInit.oreNew, 1), new BlockSet(MainInit.oreNew, 6),
				5), new OreSetDC(20, new BlockSet(MainInit.layerNew, 6), new BlockSet(Blocks.EMERALD_ORE, 0),
						5), new OreSetDC(10, new BlockSet(MainInit.oreNew, 6)));
		list.add(green);

		VeinTable blue = new VeinTable(EnumVein.BLUE, new OreSetDC(100, new BlockSet(MainInit.layerNew, 0)));
		blue.addOreToTable1(new OreSetDC(60, new BlockSet(MainInit.oreNew, 2), new BlockSet(MainInit.oreNew, 7),
				5), new OreSetDC(40, new BlockSet(MainInit.layerNew, 0)), new OreSetDC(20, new BlockSet(Blocks.CLAY,
						0)), new OreSetDC(10, new BlockSet(MainInit.oreNew, 7)));
		list.add(blue);

		VeinTable white = new VeinTable(EnumVein.WHITE, new OreSetDC(100, new BlockSet(MainInit.gemBlock, 6)));
		white.addOreToTable1(new OreSetDC(80, new BlockSet(MainInit.oreNew, 3), new BlockSet(MainInit.oreNew, 8),
				5), new OreSetDC(40, new BlockSet(MainInit.gemBlock, 6)), new OreSetDC(20, new BlockSet(
						MainInit.layerNew, 5)), new OreSetDC(10, new BlockSet(MainInit.oreNew, 8)));
		list.add(white);

		VeinTable black = new VeinTable(EnumVein.BLACK, new OreSetDC(100, new BlockSet(Blocks.STONE, 0)));
		black.addOreToTable1(new OreSetDC(60, new BlockSet(MainInit.oreNew, 4), new BlockSet(MainInit.oreNew, 9),
				5), new OreSetDC(30, new BlockSet(Blocks.REDSTONE_ORE, 0)), new OreSetDC(30, new BlockSet(
						MainInit.layerNew, 4)), new OreSetDC(20, new BlockSet(MainInit.oreNew, 9)));
		list.add(black);

		VeinTable guano = new VeinTable(EnumVein.GUANO, new OreSetDC(100, new BlockSet(Blocks.GRAVEL, 0)));
		guano.addOreToTable1(new OreSetDC(100, new BlockSet(MainInit.layerNew, 2), new BlockSet(Blocks.GRAVEL, 0), 30));
		list.add(guano);

		VeinTable skarn = new VeinTable(EnumVein.SKARN, new OreSetDC(100, new BlockSet(MainInit.gemBlock, 6)));
		skarn.addOreToTable1(new OreSetDC(30, new BlockSet(MainInit.oreNew, 0), new BlockSet(MainInit.oreNew, 5),
				10), new OreSetDC(30, new BlockSet(MainInit.oreNew, 1), new BlockSet(MainInit.oreNew, 6),
						10), new OreSetDC(30, new BlockSet(MainInit.oreNew, 2), new BlockSet(MainInit.oreNew, 7),
								10), new OreSetDC(20, new BlockSet(MainInit.gemBlock, 6)));
		list.add(skarn);

		VeinTable skarn2 = new VeinTable(EnumVein.SKARN_UNDER, new OreSetDC(100, new BlockSet(Blocks.STONE, 1)));
		skarn2.addOreToTable1(new OreSetDC(30, new BlockSet(Blocks.STONE, 1), new BlockSet(Blocks.REDSTONE_ORE, 0),
				10), new OreSetDC(30, new BlockSet(MainInit.oreNew, 3), new BlockSet(MainInit.oreNew, 8),
						10), new OreSetDC(40, new BlockSet(MainInit.oreNew, 4), new BlockSet(MainInit.oreNew, 9), 10));
		list.add(skarn2);

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
