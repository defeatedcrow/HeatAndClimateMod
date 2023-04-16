package defeatedcrow.hac.core.worldgen.vein;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;

public class VeinTableRegister {

	private VeinTableRegister() {};

	public static VeinTableRegister INSTANCE = new VeinTableRegister();
	public static List<VeinTable> list = Lists.newArrayList();

	private static void addTable(VeinTable tableIn) {
		for (VeinTable table : INSTANCE.list) {
			EnumVein vein = table.getVeinType();
			if (tableIn.getVeinType() == vein) {
				return;
			}
		}
		list.add(tableIn);
	}

	public Optional<VeinTable> getTable(String vein) {
		for (VeinTable v : list) {
			if (v.veinName.equalsIgnoreCase(vein)) {
				return Optional.of(v);
			}
		}
		return Optional.empty();
	}

	public static List<VeinTable> getMachtTable(Holder<Biome> biome, boolean deep) {
		// biome.getTagKeys().forEach(tag -> DCLogger.debugInfoLog("cheking biome " + tag.location().toString()));
		List<VeinTable> list = Lists.newArrayList();
		for (VeinTable table : INSTANCE.list) {
			EnumVein vein = table.getVeinType();
			if (vein.is(biome) && table.isDeep == deep) {
				list.add(table);
				// DCLogger.debugInfoLog("tag match:" + table.veinName);
			}
		}
		return list;
	}

	public static void initRegistry() {
		VeinTable white = new VeinTable(EnumVein.WHITE.toString(), 60, 40, 4, false, DCUtil.getBlockRegName(Blocks.BASALT))
			.setData(new OreSet(50, CoreInit.STONE_QUARTZ.get(), CoreInit.ORE_CHALCEDONY.get(), 5)).setData(new OreSet(30, CoreInit.ORE_WHITE.get()))
			.setData(new OreSet(20, Blocks.BASALT));
		addTable(white);

		VeinTable blue = new VeinTable(EnumVein.BLUE.toString(), 60, 20, 4, false, DCUtil.getBlockRegName(Blocks.CALCITE))
			.setData(new OreSet(50, Blocks.CALCITE, CoreInit.ORE_FLUORITE.get(), 5)).setData(new OreSet(50, CoreInit.ORE_BLUE.get()));
		addTable(blue);

		VeinTable black = new VeinTable(EnumVein.BLACK.toString(), 60, 40, 4, false, DCUtil.getBlockRegName(Blocks.CLAY))
			.setData(new OreSet(50, CoreInit.STONE_MUD.get(), CoreInit.ORE_JET.get(), 5)).setData(new OreSet(30, CoreInit.ORE_BLACK.get()))
			.setData(new OreSet(20, Blocks.COAL_ORE));
		addTable(black);

		VeinTable red = new VeinTable(EnumVein.RED.toString(), 60, 20, 4, false, DCUtil.getBlockRegName(Blocks.RED_SANDSTONE))
			.setData(new OreSet(60, CoreInit.STONE_GYPSUM.get(), CoreInit.ORE_DESERTROSE.get(), 5)).setData(new OreSet(40, CoreInit.ORE_RED.get()));
		addTable(red);

		VeinTable green = new VeinTable(EnumVein.GREEN.toString(), 60, 20, 4, false, DCUtil.getBlockRegName(Blocks.DIORITE))
			.setData(new OreSet(60, CoreInit.STONE_SERPENTINE.get(), CoreInit.ORE_SERPENTINE.get(), 5)).setData(new OreSet(40, CoreInit.ORE_GREEN.get()));
		addTable(green);

		VeinTable white_blue = new VeinTable(EnumVein.WHITE_BLUE.toString(), 30, -30, 8, true, DCUtil.getBlockRegName(Blocks.CALCITE))
			.setData(new OreSet(40, CoreInit.STONE_SKARN.get(), CoreInit.ORE_LARIMAR.get(), 10, CoreInit.ORE_AQUAMARINE.get(), 2))
			.setData(new OreSet(10, CoreInit.ORE_WHITE.get())).setData(new OreSet(10, CoreInit.ORE_BLUE.get()))
			.setData(new OreSet(15, CoreInit.ORE_WHITE_DEEP.get())).setData(new OreSet(15, CoreInit.ORE_BLUE_DEEP.get()))
			.setData(new OreSet(10, Blocks.CALCITE));
		addTable(white_blue);

		VeinTable blue_black = new VeinTable(EnumVein.BLUE_BLACK.toString(), 30, -30, 8, true, DCUtil.getBlockRegName(CoreInit.STONE_MUD.get()))
			.setData(new OreSet(40, CoreInit.STONE_HORNFELS.get(), CoreInit.ORE_IOLITE.get(), 10, CoreInit.ORE_OPAL.get(), 2))
			.setData(new OreSet(10, CoreInit.ORE_BLACK.get())).setData(new OreSet(10, CoreInit.ORE_BLUE.get()))
			.setData(new OreSet(15, CoreInit.ORE_BLACK_DEEP.get())).setData(new OreSet(15, CoreInit.ORE_BLUE_DEEP.get()))
			.setData(new OreSet(10, CoreInit.STONE_MUD.get(), CoreInit.ORE_JET.get(), 5));
		addTable(blue_black);

		VeinTable black_red = new VeinTable(EnumVein.BLACK_RED.toString(), 30, -30, 8, true, DCUtil.getBlockRegName(CoreInit.STONE_GRANITE.get()))
			.setData(new OreSet(40, CoreInit.STONE_MARBLE.get(), CoreInit.ORE_ROSINCA.get(), 10, CoreInit.ORE_SPINEL.get(), 2))
			.setData(new OreSet(10, CoreInit.ORE_BLACK.get())).setData(new OreSet(10, CoreInit.ORE_RED.get()))
			.setData(new OreSet(15, CoreInit.ORE_BLACK_DEEP.get())).setData(new OreSet(15, CoreInit.ORE_RED_DEEP.get()))
			.setData(new OreSet(10, CoreInit.STONE_GRANITE.get()));
		addTable(black_red);

		VeinTable red_green = new VeinTable(EnumVein.RED_GREEN.toString(), 30, -30, 8, true, DCUtil.getBlockRegName(Blocks.TUFF))
			.setData(new OreSet(40, CoreInit.STONE_SCHIST_BLUE.get(), CoreInit.ORE_AMAZONITE.get(), 10, CoreInit.ORE_JADEITE.get(), 2))
			.setData(new OreSet(10, CoreInit.ORE_RED.get())).setData(new OreSet(10, CoreInit.ORE_GREEN.get()))
			.setData(new OreSet(15, CoreInit.ORE_RED_DEEP.get())).setData(new OreSet(15, CoreInit.ORE_GREEN_DEEP.get()))
			.setData(new OreSet(10, Blocks.TUFF));
		addTable(red_green);

		VeinTable green_white = new VeinTable(EnumVein.GREEN_WHITE.toString(), 30, -30, 8, true, DCUtil.getBlockRegName(Blocks.GRANITE))
			.setData(new OreSet(40, CoreInit.STONE_GREISEN.get(), CoreInit.ORE_HELIODOR.get(), 10, CoreInit.ORE_TOPAZ.get(), 2))
			.setData(new OreSet(10, CoreInit.ORE_WHITE.get())).setData(new OreSet(10, CoreInit.ORE_GREEN.get()))
			.setData(new OreSet(15, CoreInit.ORE_WHITE_DEEP.get())).setData(new OreSet(15, CoreInit.ORE_GREEN_DEEP.get()))
			.setData(new OreSet(10, Blocks.GRANITE));
		addTable(green_white);

		VeinTable nether = new VeinTable(EnumVein.NETHER.toString(), 30, 60, 8, false, DCUtil.getBlockRegName(Blocks.NETHERRACK))
			.setData(new OreSet(40, CoreInit.STONE_SCHIST_NETHER.get(), CoreInit.ORE_DRAGONSEYE.get(), 3))
			.setData(new OreSet(20, CoreInit.ORE_RED.get(), CoreInit.ORE_RED_DEEP.get(), 20))
			.setData(new OreSet(20, CoreInit.ORE_BLACK.get(), CoreInit.ORE_BLACK_DEEP.get(), 20))
			.setData(new OreSet(20, Blocks.NETHERRACK, CoreInit.STONE_SULFUR.get(), 40));
		addTable(nether);
	}
}
