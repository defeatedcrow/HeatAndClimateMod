package defeatedcrow.hac.main.client;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.client.JsonRegisterHelper;
import defeatedcrow.hac.main.MainInit;

public class JsonRegister {

	protected static final JsonRegisterHelper MAIN_INSTANCE = new JsonRegisterHelper(
			"E:\\modding\\1.10.2\\hac_main\\src\\main\\resources");

	public static void load() {
		regItems();
		regBlocks();
		regTools();
	}

	static void regItems() {
		MAIN_INSTANCE.regSimpleItem(MainInit.oreIngot, ClimateCore.PACKAGE_ID, "dcs_ore_ingot", "ores", 10);
		MAIN_INSTANCE.regSimpleItem(MainInit.oreDust, ClimateCore.PACKAGE_ID, "dcs_ore_dust", "ores", 9);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems, ClimateCore.PACKAGE_ID, "dcs_ore_gem", "ores", 14);
		MAIN_INSTANCE.regSimpleItem(MainInit.miscDust, ClimateCore.PACKAGE_ID, "dcs_misc_dust", "ores", 8);
		MAIN_INSTANCE.regSimpleItem(MainInit.stoneYagen, ClimateCore.PACKAGE_ID, "dcs_stone_yagen", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.brassYagen, ClimateCore.PACKAGE_ID, "dcs_brass_yagen", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.materials, ClimateCore.PACKAGE_ID, "dcs_material", "tool", 7);
		MAIN_INSTANCE.regSimpleItem(MainInit.crowDrill, ClimateCore.PACKAGE_ID, "dcs_crow_drill", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.wrench, ClimateCore.PACKAGE_ID, "dcs_wrench", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.bakedApple, ClimateCore.PACKAGE_ID, "dcs_baked_apple", "food", 3);
		MAIN_INSTANCE.regSimpleItem(MainInit.repairPutty, ClimateCore.PACKAGE_ID, "dcs_repair_putty", "tool", 1);
		MAIN_INSTANCE.regSimpleItem(MainInit.foodMaterials, ClimateCore.PACKAGE_ID, "dcs_food_materials", "food", 2);

	}

	static void regBlocks() {
		regCube((ITexturePath) MainInit.dustBlock, ClimateCore.PACKAGE_ID, "dcs_ore_dustblock", "ores", 15);
		regCube((ITexturePath) MainInit.metalBlock, ClimateCore.PACKAGE_ID, "dcs_ore_metalblock", "ores", 15);
		regCube((ITexturePath) MainInit.gemBlock, ClimateCore.PACKAGE_ID, "dcs_ore_gemblock", "ores", 15);
		regCube((ITexturePath) MainInit.selenite, ClimateCore.PACKAGE_ID, "dcs_build_selenite", "build", 2);
		regCube((ITexturePath) MainInit.bricks, ClimateCore.PACKAGE_ID, "dcs_build_bricks", "build", 2);
		regCube((ITexturePath) MainInit.builds, ClimateCore.PACKAGE_ID, "dcs_build_build", "build", 4);

		MAIN_INSTANCE.regSimpleBlock(MainInit.ores, ClimateCore.PACKAGE_ID, "dcs_ore_stone", "ores", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.ores_2, ClimateCore.PACKAGE_ID, "dcs_ore2_stone", "ores", 7);
		MAIN_INSTANCE.regSimpleBlock(MainInit.dustBlock, ClimateCore.PACKAGE_ID, "dcs_ore_dustblock", "ores", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.metalBlock, ClimateCore.PACKAGE_ID, "dcs_ore_metalblock", "ores", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.gemBlock, ClimateCore.PACKAGE_ID, "dcs_ore_gemblock", "ores", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.selenite, ClimateCore.PACKAGE_ID, "dcs_build_selenite", "build", 2);
		MAIN_INSTANCE.regSimpleBlock(MainInit.chalLamp, ClimateCore.PACKAGE_ID, "dcs_build_challamp", "build", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.bricks, ClimateCore.PACKAGE_ID, "dcs_build_bricks", "build", 2);
		MAIN_INSTANCE.regSimpleBlock(MainInit.builds, ClimateCore.PACKAGE_ID, "dcs_build_build", "build", 4);
		MAIN_INSTANCE.regSimpleBlock(MainInit.wallLamp, ClimateCore.PACKAGE_ID, "dcs_build_walllamp", "build", 3);
		MAIN_INSTANCE.regSimpleBlock(MainInit.plate, ClimateCore.PACKAGE_ID, "dcs_build_plate", "build", 1);
		MAIN_INSTANCE.regSimpleBlock(MainInit.markingPanel, ClimateCore.PACKAGE_ID, "dcs_build_markingpanel", "build",
				0);
		MAIN_INSTANCE.regSimpleBlock(MainInit.sinkMetal, ClimateCore.PACKAGE_ID, "dcs_device_sink_half", "device", 0);
		MAIN_INSTANCE.regSimpleBlock(MainInit.sinkChest, ClimateCore.PACKAGE_ID, "dcs_device_sink_full", "device", 0);

		MAIN_INSTANCE.regTEBlock(MainInit.chamber, ClimateCore.PACKAGE_ID, "dcs_device_chamber", "machine", 0);
		MAIN_INSTANCE.regTEBlock(MainInit.shitirin, ClimateCore.PACKAGE_ID, "dcs_device_shitirin", "machine", 0);
		MAIN_INSTANCE.regTEBlock(MainInit.fuelStove, ClimateCore.PACKAGE_ID, "dcs_device_fuelstove", "machine", 0);
		MAIN_INSTANCE.regTEBlock(MainInit.chestMetal, ClimateCore.PACKAGE_ID, "dcs_device_chest_metal", "device", 0);
		MAIN_INSTANCE.regTEBlock(MainInit.chestMagnet, ClimateCore.PACKAGE_ID, "dcs_device_chest_magnet", "device", 0);
		MAIN_INSTANCE.regTEBlock(MainInit.chestVillage, ClimateCore.PACKAGE_ID, "dcs_device_chest_village", "device",
				0);
		MAIN_INSTANCE.regTETorqueBlock(MainInit.bellow, ClimateCore.PACKAGE_ID, "dcs_device_bellow", "device", 0);
		MAIN_INSTANCE.regTEBlock(MainInit.thermometer, ClimateCore.PACKAGE_ID, "dcs_device_thermometer", "device", 0);
		MAIN_INSTANCE.regTEBlock(MainInit.windvane, ClimateCore.PACKAGE_ID, "dcs_device_windvane", "device", 0);
		MAIN_INSTANCE.regTEBlock(MainInit.stevenson_screen, ClimateCore.PACKAGE_ID, "dcs_device_stevenson_screen",
				"machine", 0);
	}

	static void regTools() {

		String[] name = {
				"brass",
				"steel",
				"silver",
				"nickelsilver",
				"chalcedony",
				"sapphire"
		};
		for (int j = 0; j < name.length; j++) {
			MAIN_INSTANCE.regSimpleItem(MainInit.dcAxe[j], ClimateCore.PACKAGE_ID, "dcs_axe_" + name[j], "equip", 0);
			MAIN_INSTANCE.regSimpleItem(MainInit.dcPickaxe[j], ClimateCore.PACKAGE_ID, "dcs_pickaxe_" + name[j],
					"equip", 0);
			MAIN_INSTANCE.regSimpleItem(MainInit.dcSpade[j], ClimateCore.PACKAGE_ID, "dcs_spade_" + name[j], "equip",
					0);
			MAIN_INSTANCE.regSimpleItem(MainInit.dcSword[j], ClimateCore.PACKAGE_ID, "dcs_sword_" + name[j], "equip",
					0);
		}
		String[] type = {
				"met",
				"plate",
				"leggins",
				"boots"
		};
		for (int i = 0; i < 4; i++) {
			MAIN_INSTANCE.regSimpleItem(MainInit.brassArmor[i], ClimateCore.PACKAGE_ID, "dcs_" + type[i] + "_brass",
					"equip", 0);
			MAIN_INSTANCE.regSimpleItem(MainInit.steelArmor[i], ClimateCore.PACKAGE_ID, "dcs_" + type[i] + "_steel",
					"equip", 0);
			MAIN_INSTANCE.regSimpleItem(MainInit.chalcArmor[i], ClimateCore.PACKAGE_ID,
					"dcs_" + type[i] + "_chalcedony", "equip", 0);
			MAIN_INSTANCE.regSimpleItem(MainInit.sapphireArmor[i], ClimateCore.PACKAGE_ID,
					"dcs_" + type[i] + "_sapphire", "equip", 0);
		}

		String[] name2 = {
				"brass",
				"steel",
				"chalcedony"
		};
		for (int j = 0; j < name2.length; j++) {
			MAIN_INSTANCE.regSimpleItem(MainInit.dcScythe[j], ClimateCore.PACKAGE_ID, "dcs_scythe_" + name[j], "equip",
					0);
		}

		MAIN_INSTANCE.regSimpleItem(MainInit.linenUnder, ClimateCore.PACKAGE_ID, "dcs_leggins_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.linenCourt, ClimateCore.PACKAGE_ID, "dcs_plate_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothUnder, ClimateCore.PACKAGE_ID, "dcs_leggins_cloth", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.workerSuit, ClimateCore.PACKAGE_ID, "dcs_leggins_worker", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.blackSuit, ClimateCore.PACKAGE_ID, "dcs_leggins_black", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.leatherHat, ClimateCore.PACKAGE_ID, "dcs_hat_leather", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.cottonHat, ClimateCore.PACKAGE_ID, "dcs_hat_cotton", "equip", 0);
	}

	static void regCube(ITexturePath block, String domein, String name, String dir, int meta) {
		if (MAIN_INSTANCE.active) {
			for (int i = 0; i <= meta; i++) {
				MAIN_INSTANCE.checkAndBuildJsonCube(block, domein, name, dir, i, true);
			}
		}
	}

}
