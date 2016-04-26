package defeatedcrow.hac.main;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItemBlock;
import defeatedcrow.hac.main.block.build.BlockGlassSelenite;
import defeatedcrow.hac.main.block.build.BlockSlabDC;
import defeatedcrow.hac.main.block.build.BlockStairsBase;
import defeatedcrow.hac.main.block.container.BlockCropCont;
import defeatedcrow.hac.main.block.container.BlockEnemyCont;
import defeatedcrow.hac.main.block.container.BlockLogCont;
import defeatedcrow.hac.main.block.container.BlockMiscCont;
import defeatedcrow.hac.main.block.ores.BlockDusts;
import defeatedcrow.hac.main.block.ores.BlockGem;
import defeatedcrow.hac.main.block.ores.BlockMetal;
import defeatedcrow.hac.main.block.ores.BlockOres;
import defeatedcrow.hac.main.item.food.ItemDCFoods;
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
		regJsonBlock();
		registerItem();
		registerFood();
		registerHarvestLevel();
	}

	static void registerBlock() {
		MainInit.ores = new BlockOres(Material.iron, ClimateCore.PACKAGE_BASE + "_ore_stone", 15);
		GameRegistry.registerBlock(MainInit.ores, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_ore_stone");

		MainInit.metalBlock = new BlockMetal(Material.iron, ClimateCore.PACKAGE_BASE + "_metal", 7);
		GameRegistry.registerBlock(MainInit.metalBlock, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_ore_metalblock");

		MainInit.dustBlock = new BlockDusts(Material.ground, ClimateCore.PACKAGE_BASE + "_dustblock", 7);
		GameRegistry.registerBlock(MainInit.dustBlock, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_ore_dustblock");

		MainInit.gemBlock = new BlockGem(Material.rock, ClimateCore.PACKAGE_BASE + "_gemblock", 4);
		GameRegistry.registerBlock(MainInit.gemBlock, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_ore_gemblock");

		MainInit.selenite = new BlockGlassSelenite(ClimateCore.PACKAGE_BASE + "_build_selenite", 2);
		GameRegistry.registerBlock(MainInit.selenite, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_build_selenite");
	}

	static void registerSidedBlock() {
		MainInit.logCont = new BlockLogCont(Material.wood, ClimateCore.PACKAGE_BASE + "_cont_log", 6);
		GameRegistry.registerBlock(MainInit.logCont, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_cont_log");
		ClimateMain.proxy.addSidedBlock(MainInit.logCont);

		MainInit.cropCont = new BlockCropCont(Material.wood, ClimateCore.PACKAGE_BASE + "_cont_crop", 10);
		GameRegistry.registerBlock(MainInit.cropCont, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_cont_crop");
		ClimateMain.proxy.addTBBlock(MainInit.cropCont);

		MainInit.dropCont = new BlockEnemyCont(Material.clay, ClimateCore.PACKAGE_BASE + "_cont_metal", 4);
		GameRegistry.registerBlock(MainInit.dropCont, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_cont_metal");
		ClimateMain.proxy.addTBBlock(MainInit.dropCont);

		MainInit.miscCont = new BlockMiscCont(Material.clay, ClimateCore.PACKAGE_BASE + "_cont_misc", 3);
		GameRegistry.registerBlock(MainInit.miscCont, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_cont_misc");
		ClimateMain.proxy.addTBBlock(MainInit.miscCont);
	}

	static void regJsonBlock() {
		MainInit.stairsGlass = new BlockStairsBase(MainInit.selenite.getDefaultState(), "build/glass_stairs", true)
				.setUnlocalizedName("dcs_stairs_glass");
		GameRegistry.registerBlock(MainInit.stairsGlass, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_stairs_glass");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stairsGlass), "dcs_climate", "dcs_stairs_glass", "build", 15, false);

		MainInit.stairsGypsum = new BlockStairsBase(MainInit.ores.getDefaultState(), "ores/gemblock_gypsum", false)
				.setUnlocalizedName("dcs_stairs_gypsum");
		GameRegistry.registerBlock(MainInit.stairsGypsum, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_stairs_gypsum");
		ClimateMain.proxy
				.regBlockJson(Item.getItemFromBlock(MainInit.stairsGypsum), "dcs_climate", "dcs_stairs_gypsum", "build", 15, false);

		MainInit.halfSlab = new BlockSlabDC();
		GameRegistry.registerBlock(MainInit.halfSlab, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_build_slab");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.halfSlab), "dcs_climate", "dcs_build_slab", "build", 2, true);
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

	static void registerFood() {
		MainInit.bakedApple = new ItemDCFoods(0, false).setCreativeTab(ClimateCore.climate).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_food_itemfood");
		GameRegistry.registerItem(MainInit.bakedApple, ClimateCore.PACKAGE_BASE + "_food_itemfood");
	}

	private static void registerHarvestLevel() {
		MainInit.ores.setHarvestLevel("pickaxe", 0);
	}
}
