package defeatedcrow.hac.main;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCItemBlock;
import defeatedcrow.hac.magic.item.ItemMagicalPendant;
import defeatedcrow.hac.main.block.build.BlockGlassSelenite;
import defeatedcrow.hac.main.block.build.BlockSlabDC;
import defeatedcrow.hac.main.block.build.BlockStairsBase;
import defeatedcrow.hac.main.block.container.BlockCropCont;
import defeatedcrow.hac.main.block.container.BlockEnemyCont;
import defeatedcrow.hac.main.block.container.BlockLogCont;
import defeatedcrow.hac.main.block.container.BlockMiscCont;
import defeatedcrow.hac.main.block.device.BlockNormalChamber;
import defeatedcrow.hac.main.block.ores.BlockDusts;
import defeatedcrow.hac.main.block.ores.BlockGem;
import defeatedcrow.hac.main.block.ores.BlockMetal;
import defeatedcrow.hac.main.block.ores.BlockOres;
import defeatedcrow.hac.main.item.equip.ItemArmorDC;
import defeatedcrow.hac.main.item.equip.ItemArmorThinDC;
import defeatedcrow.hac.main.item.food.ItemDCFoods;
import defeatedcrow.hac.main.item.misc.ItemMiscs;
import defeatedcrow.hac.main.item.misc.ItemRepairPutty;
import defeatedcrow.hac.main.item.ores.ItemGems;
import defeatedcrow.hac.main.item.ores.ItemIngots;
import defeatedcrow.hac.main.item.ores.ItemMiscDust;
import defeatedcrow.hac.main.item.ores.ItemOreDusts;
import defeatedcrow.hac.main.item.tool.ItemAxeDC;
import defeatedcrow.hac.main.item.tool.ItemCrowDrill;
import defeatedcrow.hac.main.item.tool.ItemPickaxeDC;
import defeatedcrow.hac.main.item.tool.ItemSpadeDC;
import defeatedcrow.hac.main.item.tool.ItemStoneYagen;
import defeatedcrow.hac.main.item.tool.ItemSwordDC;
import defeatedcrow.hac.main.util.DCArmorMaterial;
import defeatedcrow.hac.main.util.DCMaterial;
import defeatedcrow.hac.main.util.DCToolMaterial;

public class MainMaterialRegister {
	private MainMaterialRegister() {
	}

	public static void load() {

		registerBlock();
		registerSidedBlock();
		regJsonBlock();
		regDeviceBlock();
		registerItem();
		registerMaterialEnum();
		registerEquip();
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
		MainInit.logCont = new BlockLogCont(Material.clay, ClimateCore.PACKAGE_BASE + "_cont_log", 6);
		GameRegistry.registerBlock(MainInit.logCont, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_cont_log");
		ClimateMain.proxy.addSidedBlock(MainInit.logCont);

		MainInit.cropCont = new BlockCropCont(Material.clay, ClimateCore.PACKAGE_BASE + "_cont_crop", 10);
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

	static void regDeviceBlock() {
		MainInit.chamber = new BlockNormalChamber(Material.iron, ClimateCore.PACKAGE_BASE + "_device_chamber", 1);
		GameRegistry.registerBlock(MainInit.chamber, DCItemBlock.class, ClimateCore.PACKAGE_BASE + "_device_chamber");
	}

	static void registerItem() {
		// ores
		MainInit.oreIngot = new ItemIngots(7).setCreativeTab(ClimateCore.climate).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_ingot");
		GameRegistry.registerItem(MainInit.oreIngot, ClimateCore.PACKAGE_BASE + "_ingot");

		MainInit.oreDust = new ItemOreDusts(7).setCreativeTab(ClimateCore.climate)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_oredust");
		GameRegistry.registerItem(MainInit.oreDust, ClimateCore.PACKAGE_BASE + "_oredust");

		MainInit.gems = new ItemGems(7).setCreativeTab(ClimateCore.climate).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_gem");
		GameRegistry.registerItem(MainInit.gems, ClimateCore.PACKAGE_BASE + "_gem");

		MainInit.miscDust = new ItemMiscDust(5).setCreativeTab(ClimateCore.climate).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_miscdust");
		GameRegistry.registerItem(MainInit.miscDust, ClimateCore.PACKAGE_BASE + "_miscdust");

		// tools
		MainInit.materials = new ItemMiscs(5).setCreativeTab(ClimateCore.climate)
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

	static void registerEquip() {
		String[] name = {
				"brass",
				"steel",
				"silver",
				"nickelsilver",
				"chalcedony",
				"sapphire" };
		for (int j = 0; j < 6; j++) {
			DCLogger.debugLog(j + "/" + DCToolMaterial.getToolMaterial(j).toString());
			MainInit.dcAxe[j] = new ItemAxeDC(DCToolMaterial.getToolMaterial(j), name[j]).setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_axe_" + name[j]);
			GameRegistry.registerItem(MainInit.dcAxe[j], ClimateCore.PACKAGE_BASE + "_axe_" + name[j]);
		}

		for (int j = 0; j < 6; j++) {
			MainInit.dcPickaxe[j] = new ItemPickaxeDC(DCToolMaterial.getToolMaterial(j), name[j]).setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_pickaxe_" + name[j]);
			GameRegistry.registerItem(MainInit.dcPickaxe[j], ClimateCore.PACKAGE_BASE + "_pickaxe_" + name[j]);
		}

		for (int j = 0; j < 6; j++) {
			MainInit.dcSpade[j] = new ItemSpadeDC(DCToolMaterial.getToolMaterial(j), name[j]).setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_spade_" + name[j]);
			GameRegistry.registerItem(MainInit.dcSpade[j], ClimateCore.PACKAGE_BASE + "_spade_" + name[j]);
		}

		for (int j = 0; j < 6; j++) {
			MainInit.dcSword[j] = new ItemSwordDC(DCToolMaterial.getToolMaterial(j), name[j]).setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_sword_" + name[j]);
			GameRegistry.registerItem(MainInit.dcSword[j], ClimateCore.PACKAGE_BASE + "_sword_" + name[j]);
		}

		String[] type = {
				"met",
				"plate",
				"leggins",
				"boots" };
		for (int i = 0; i < 4; i++) {
			MainInit.brassArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_BRASS, DCMaterial.BRASS, i, "brass").setCreativeTab(
					ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_brass");
			GameRegistry.registerItem(MainInit.brassArmor[i], ClimateCore.PACKAGE_BASE + "_" + type[i] + "_brass");

			MainInit.steelArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_STEEL, DCMaterial.STEEL, i, "steel").setCreativeTab(
					ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_steel");
			GameRegistry.registerItem(MainInit.steelArmor[i], ClimateCore.PACKAGE_BASE + "_" + type[i] + "_steel");

			MainInit.chalcArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_CHALCEDONY, DCMaterial.CHALCEDONY, i, "chalcedony").setCreativeTab(
					ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_chalcedony");
			GameRegistry.registerItem(MainInit.chalcArmor[i], ClimateCore.PACKAGE_BASE + "_" + type[i] + "_chalcedony");

			MainInit.sapphireArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_SAPPHIRE, DCMaterial.SAPPHIRE, i, "sapphire").setCreativeTab(
					ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_sapphire");
			GameRegistry.registerItem(MainInit.sapphireArmor[i], ClimateCore.PACKAGE_BASE + "_" + type[i] + "_sapphire");
		}

		MainInit.linenUnder = new ItemArmorThinDC(DCArmorMaterial.DC_LINEN, DCMaterial.LINEN, 2, "linen").setCreativeTab(ClimateMain.tool)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_linen");
		GameRegistry.registerItem(MainInit.linenUnder, ClimateCore.PACKAGE_BASE + "_leggins_linen");

		MainInit.linenCourt = new ItemArmorThinDC(DCArmorMaterial.DC_LINEN, DCMaterial.LINEN, 1, "linen").setCreativeTab(ClimateMain.tool)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_coat_linen");
		GameRegistry.registerItem(MainInit.linenCourt, ClimateCore.PACKAGE_BASE + "_coat_linen");

		MainInit.clothUnder = new ItemArmorThinDC(DCArmorMaterial.DC_CLOTH, DCMaterial.LINEN, 2, "cloth").setCreativeTab(ClimateMain.tool)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_cloth");
		GameRegistry.registerItem(MainInit.clothUnder, ClimateCore.PACKAGE_BASE + "_leggins_cloth");

		MainInit.workerSuit = new ItemArmorThinDC(DCArmorMaterial.DC_CLOTH, DCMaterial.LINEN, 2, "worker").setCreativeTab(ClimateMain.tool)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_worker");
		GameRegistry.registerItem(MainInit.workerSuit, ClimateCore.PACKAGE_BASE + "_leggins_worker");

		MainInit.blackSuit = new ItemArmorThinDC(DCArmorMaterial.DC_CLOTH, DCMaterial.LINEN, 2, "suit").setCreativeTab(ClimateMain.tool)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_suit");
		GameRegistry.registerItem(MainInit.blackSuit, ClimateCore.PACKAGE_BASE + "_leggins_suit");

		MainInit.pendant = new ItemMagicalPendant(9).setCreativeTab(ClimateMain.tool).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_jewel_pendant");
		GameRegistry.registerItem(MainInit.pendant, ClimateCore.PACKAGE_BASE + "_jewel_pendant");
	}

	static void registerMaterialEnum() {
		MainInit.repairPatty = new ItemRepairPutty().setCreativeTab(ClimateMain.tool).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_repair");
		GameRegistry.registerItem(MainInit.repairPatty, ClimateCore.PACKAGE_BASE + "_repair");

		DCArmorMaterial.load();
		DCToolMaterial.load();
	}

	private static void registerHarvestLevel() {
		MainInit.ores.setHarvestLevel("pickaxe", 0);
	}
}
