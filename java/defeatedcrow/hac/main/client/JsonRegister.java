package defeatedcrow.hac.main.client;

import defeatedcrow.hac.api.placeable.ISidedTexture;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.client.JsonRegisterHelper;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.plugin.DCIntegrationCore;
import net.minecraft.item.Item;

public class JsonRegister {

	protected static final JsonRegisterHelper MAIN_INSTANCE = new JsonRegisterHelper(
			"E:\\modding\\1.12.1\\hac_main\\src\\main\\resources");

	public static void load() {
		regItems();
		regBlocks();
		regTools();
	}

	static void regItems() {
		MAIN_INSTANCE.regSimpleItem(MainInit.oreIngot, ClimateCore.PACKAGE_ID, "dcs_ore_ingot", "ores", 15);
		MAIN_INSTANCE.regSimpleItem(MainInit.oreDust, ClimateCore.PACKAGE_ID, "dcs_ore_dust", "ores", 13);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems, ClimateCore.PACKAGE_ID, "dcs_ore_gem", "ores", 18);
		MAIN_INSTANCE.regSimpleItem(MainInit.miscDust, ClimateCore.PACKAGE_ID, "dcs_misc_dust", "ores", 11);
		MAIN_INSTANCE.regSimpleItem(MainInit.stoneYagen, ClimateCore.PACKAGE_ID, "dcs_stone_yagen", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.brassYagen, ClimateCore.PACKAGE_ID, "dcs_brass_yagen", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.materials, ClimateCore.PACKAGE_ID, "dcs_material", "tool", 9);
		MAIN_INSTANCE.regSimpleItem(MainInit.crowDrill, ClimateCore.PACKAGE_ID, "dcs_crow_drill", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.wrench, ClimateCore.PACKAGE_ID, "dcs_wrench", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.bakedApple, ClimateCore.PACKAGE_ID, "dcs_baked_apple", "food", 4);
		MAIN_INSTANCE.regSimpleItem(MainInit.repairPutty, ClimateCore.PACKAGE_ID, "dcs_repair_putty", "tool", 2);
		MAIN_INSTANCE.regSimpleItem(MainInit.foodMaterials, ClimateCore.PACKAGE_ID, "dcs_food_materials", "food", 2);
		MAIN_INSTANCE.regSimpleItem(MainInit.flowerPot, ClimateCore.PACKAGE_ID, "dcs_flowerpot_white", "build", 1);
		MAIN_INSTANCE.regSimpleItem(MainInit.scope, ClimateCore.PACKAGE_ID, "dcs_thermal_scope", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.entityScope, ClimateCore.PACKAGE_ID, "dcs_entity_scope", "tool", 0);

		if (DCIntegrationCore.loadedForestry) {
			MAIN_INSTANCE.regSimpleItem(MainInit.circuit, ClimateCore.PACKAGE_ID, "dcs_plugin_circuit", "device", 0);
		}
		if (DCIntegrationCore.loadedMekanism) {
			MAIN_INSTANCE.regSimpleItem(MainInit.metalMaterials, ClimateCore.PACKAGE_ID, "dcs_plugin_metal_material",
					"device", 7);
		}

	}

	static void regBlocks() {
		regCube((ITexturePath) MainInit.dustBlock, ClimateCore.PACKAGE_ID, "dcs_ore_dustblock", "ores", 15);
		regCube((ITexturePath) MainInit.metalBlock, ClimateCore.PACKAGE_ID, "dcs_ore_metalblock", "ores", 15);
		regCube((ITexturePath) MainInit.gemBlock, ClimateCore.PACKAGE_ID, "dcs_ore_gemblock", "ores", 15);
		regCube((ITexturePath) MainInit.selenite, ClimateCore.PACKAGE_ID, "dcs_build_selenite", "build", 2);
		regCube((ITexturePath) MainInit.bricks, ClimateCore.PACKAGE_ID, "dcs_build_bricks", "build", 2);
		regCube((ITexturePath) MainInit.builds, ClimateCore.PACKAGE_ID, "dcs_build_build", "build", 5);
		regCube((ITexturePath) MainInit.syntheticBlock, ClimateCore.PACKAGE_ID, "dcs_build_synthetic_glass", "build",
				15);

		MAIN_INSTANCE.regSimpleBlock(MainInit.ores, ClimateCore.PACKAGE_ID, "dcs_ore_stone", "ores", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.ores_2, ClimateCore.PACKAGE_ID, "dcs_ore2_stone", "ores", 12);
		MAIN_INSTANCE.regSimpleBlock(MainInit.dustBlock, ClimateCore.PACKAGE_ID, "dcs_ore_dustblock", "ores", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.metalBlock, ClimateCore.PACKAGE_ID, "dcs_ore_metalblock", "ores", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.gemBlock, ClimateCore.PACKAGE_ID, "dcs_ore_gemblock", "ores", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.selenite, ClimateCore.PACKAGE_ID, "dcs_build_selenite", "build", 2);
		MAIN_INSTANCE.regSimpleBlock(MainInit.chalLamp, ClimateCore.PACKAGE_ID, "dcs_build_challamp", "build", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.bricks, ClimateCore.PACKAGE_ID, "dcs_build_bricks", "build", 2);
		MAIN_INSTANCE.regSimpleBlock(MainInit.builds, ClimateCore.PACKAGE_ID, "dcs_build_build", "build", 5);
		MAIN_INSTANCE.regSimpleBlock(MainInit.wallLamp, ClimateCore.PACKAGE_ID, "dcs_build_walllamp", "build", 3);
		MAIN_INSTANCE.regSimpleBlock(MainInit.awning, ClimateCore.PACKAGE_ID, "dcs_build_awning", "build", 3);
		MAIN_INSTANCE.regSimpleBlock(MainInit.plate, ClimateCore.PACKAGE_ID, "dcs_build_plate", "build", 1);
		MAIN_INSTANCE.regSimpleBlock(MainInit.lampCarbide, ClimateCore.PACKAGE_ID, "dcs_lamp_carbide_lantern", "build",
				0);
		MAIN_INSTANCE.regSimpleBlock(MainInit.lampGas, ClimateCore.PACKAGE_ID, "dcs_lamp_carbide_glass", "build", 0);
		MAIN_INSTANCE.regSimpleBlock(MainInit.markingPanel, ClimateCore.PACKAGE_ID, "dcs_build_markingpanel", "build",
				0);
		MAIN_INSTANCE.regSimpleBlock(MainInit.lightOrb, ClimateCore.PACKAGE_ID, "dcs_build_lightorb", "build", 0);
		MAIN_INSTANCE.regSimpleBlock(MainInit.sinkMetal, ClimateCore.PACKAGE_ID, "dcs_device_sink_half", "device", 0);
		MAIN_INSTANCE.regSimpleBlock(MainInit.sinkChest, ClimateCore.PACKAGE_ID, "dcs_device_sink_full", "device", 0);
		MAIN_INSTANCE.regSimpleBlock(MainInit.syntheticBlock, ClimateCore.PACKAGE_ID, "dcs_build_synthetic_glass",
				"build", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.clayBricks, ClimateCore.PACKAGE_ID, "dcs_build_claybrick", "build", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.achievementShield, ClimateCore.PACKAGE_ID, "dcs_build_shield", "build",
				2);

		MAIN_INSTANCE.regSimpleItem(Item.getItemFromBlock(MainInit.desiccant), ClimateCore.PACKAGE_ID, "dcs_desiccant",
				"build", 3);
		MAIN_INSTANCE.regSimpleItem(Item.getItemFromBlock(MainInit.freezepack), ClimateCore.PACKAGE_ID, "dcs_coolant",
				"build", 3);
		MAIN_INSTANCE.regSimpleBlock(MainInit.hedgeSpring, ClimateCore.PACKAGE_ID, "dcs_hedge_spring", "crop", 0);
		MAIN_INSTANCE.regSimpleBlock(MainInit.hedgeSummer, ClimateCore.PACKAGE_ID, "dcs_hedge_summer", "crop", 0);
		MAIN_INSTANCE.regSimpleBlock(MainInit.hedgeAutumn, ClimateCore.PACKAGE_ID, "dcs_hedge_autumn", "crop", 0);
		MAIN_INSTANCE.regSimpleBlock(MainInit.hedgeWinter, ClimateCore.PACKAGE_ID, "dcs_hedge_winter", "crop", 0);

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
				"brass", "steel", "silver", "nickelsilver", "chalcedony", "sapphire", "titanium"
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
				"met", "plate", "leggins", "boots"
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
			MAIN_INSTANCE.regSimpleItem(MainInit.titaniumArmor[i], ClimateCore.PACKAGE_ID,
					"dcs_" + type[i] + "_titanium", "equip", 0);
		}

		String[] name2 = {
				"brass", "steel", "chalcedony"
		};
		for (int j = 0; j < name2.length; j++) {
			MAIN_INSTANCE.regSimpleItem(MainInit.dcScythe[j], ClimateCore.PACKAGE_ID, "dcs_scythe_" + name[j], "equip",
					0);
		}

		MAIN_INSTANCE.regSimpleItem(MainInit.crossbow, ClimateCore.PACKAGE_ID, "dcs_crossbow_iron", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.gun, ClimateCore.PACKAGE_ID, "dcs_musket_steel", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.cartridge, ClimateCore.PACKAGE_ID, "dcs_cartridge", "equip", 4);

		MAIN_INSTANCE.regSimpleItem(MainInit.linenUnder, ClimateCore.PACKAGE_ID, "dcs_leggins_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.linenCourt, ClimateCore.PACKAGE_ID, "dcs_plate_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothUnder, ClimateCore.PACKAGE_ID, "dcs_leggins_cloth", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.workerSuit, ClimateCore.PACKAGE_ID, "dcs_leggins_worker", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.blackSuit, ClimateCore.PACKAGE_ID, "dcs_leggins_black", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.trackSuit, ClimateCore.PACKAGE_ID, "dcs_leggins_tracksuit", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.combatDress, ClimateCore.PACKAGE_ID, "dcs_leggins_combatdress", "equip",
				0);
		MAIN_INSTANCE.regSimpleItem(MainInit.hoodie, ClimateCore.PACKAGE_ID, "dcs_plate_hoodie", "equip", 0);
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

	static void regSidedCube(ISidedTexture block, String domein, String name, String dir, int meta) {
		if (MAIN_INSTANCE.active) {
			for (int i = 0; i <= meta; i++) {
				MAIN_INSTANCE.checkAndBuildSidedCube(block, domein, name, dir, i, true);
			}
		}
	}

	static void regCross(ISidedTexture block, String domein, String name, String dir, int meta) {
		if (MAIN_INSTANCE.active) {
			for (int i = 0; i <= meta; i++) {
				MAIN_INSTANCE.checkAndBuildJsonCross(block, domein, name, dir, i, true);
			}
		}
	}

}
