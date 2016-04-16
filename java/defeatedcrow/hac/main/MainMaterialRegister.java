package defeatedcrow.hac.main;

import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItemBlock;
import defeatedcrow.hac.main.block.container.BlockLogCont;
import defeatedcrow.hac.main.block.ores.BlockDusts;
import defeatedcrow.hac.main.block.ores.BlockGem;
import defeatedcrow.hac.main.block.ores.BlockMetal;
import defeatedcrow.hac.main.block.ores.BlockOres;
import defeatedcrow.hac.main.item.misc.ItemMiscs;
import defeatedcrow.hac.main.item.ores.ItemGems;
import defeatedcrow.hac.main.item.ores.ItemIngots;
import defeatedcrow.hac.main.item.ores.ItemMiscDust;
import defeatedcrow.hac.main.item.ores.ItemOreDusts;
import defeatedcrow.hac.main.item.tool.ItemCrowDrill;
import defeatedcrow.hac.main.item.tool.ItemStoneYagen;

public class MainMaterialRegister {
	private MainMaterialRegister() {
	}

	public static void load() {
		registerBlock();
		registerSidedBlock();
		registerItem();
		registerHarvestLevel();
	}

	static void registerBlock() {
		MainInit.ores = new BlockOres(Material.iron, ClimateCore.PACKAGE_BASE + "_ore_stone", 15);
		GameRegistry.registerBlock(MainInit.ores, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_ore_stone");

		MainInit.metalBlock = new BlockMetal(Material.iron, ClimateCore.PACKAGE_BASE + "_metal", 7);
		GameRegistry.registerBlock(MainInit.metalBlock, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_ore_metalblock");

		MainInit.dustBlock = new BlockDusts(Material.ground, ClimateCore.PACKAGE_BASE + "_dustblock", 7);
		GameRegistry.registerBlock(MainInit.dustBlock, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_ore_dustblock");

		MainInit.gemBlock = new BlockGem(Material.iron, ClimateCore.PACKAGE_BASE + "_gemblock", 4);
		GameRegistry.registerBlock(MainInit.gemBlock, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_ore_gemblock");
	}

	static void registerSidedBlock() {
		MainInit.logCont = new BlockLogCont(Material.wood, ClimateCore.PACKAGE_BASE + "_cont_log", 6);
		GameRegistry.registerBlock(MainInit.logCont, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_cont_log");
		ClimateMain.proxy.addSidedBlock(MainInit.logCont);
	}

	static void registerItem() {
		// ores
		MainInit.oreIngot = new ItemIngots(7).setCreativeTab(ClimateCore.climate).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_ingot");
		GameRegistry.registerItem(MainInit.oreIngot, ClimateCore.PACKAGE_BASE + "_ingot");

		MainInit.oreDust = new ItemOreDusts(7).setCreativeTab(ClimateCore.climate)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_oredust");
		GameRegistry.registerItem(MainInit.oreDust, ClimateCore.PACKAGE_BASE + "_oredust");

		MainInit.gems = new ItemGems(4).setCreativeTab(ClimateCore.climate).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_gem");
		GameRegistry.registerItem(MainInit.gems, ClimateCore.PACKAGE_BASE + "_gem");

		MainInit.miscDust = new ItemMiscDust(5).setCreativeTab(ClimateCore.climate).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_miscdust");
		GameRegistry.registerItem(MainInit.miscDust, ClimateCore.PACKAGE_BASE + "_miscdust");

		// tools
		MainInit.materials = new ItemMiscs(4).setCreativeTab(ClimateCore.climate)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_material");
		GameRegistry.registerItem(MainInit.materials, ClimateCore.PACKAGE_BASE + "_material");

		MainInit.stoneYagen = new ItemStoneYagen().setCreativeTab(ClimateCore.climate).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_yagen_stone");
		GameRegistry.registerItem(MainInit.stoneYagen, ClimateCore.PACKAGE_BASE + "_yagen_stone");

		MainInit.crowDrill = new ItemCrowDrill().setCreativeTab(ClimateCore.climate).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_creative_drill");
		GameRegistry.registerItem(MainInit.crowDrill, ClimateCore.PACKAGE_BASE + "_creative_drill");
	}

	private static void registerHarvestLevel() {
		MainInit.ores.setHarvestLevel("pickaxe", 0);
	}
}
