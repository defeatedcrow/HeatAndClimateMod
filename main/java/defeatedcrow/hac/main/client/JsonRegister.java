package defeatedcrow.hac.main.client;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.placeable.ISidedTexture;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.client.JsonRegisterHelper;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.plugin.DCIntegrationCore;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class JsonRegister {

	protected static final JsonRegisterHelper MAIN_INSTANCE = new JsonRegisterHelper(
			"F:\\modding\\1.12.1\\hac_main\\src\\main\\resources");

	public static void load() {
		regItems();
		regBlocks();
		regTools();
		regFluids();
	}

	static void regItems() {
		MAIN_INSTANCE.regSimpleItem(MainInit.iconItem, ClimateCore.PACKAGE_ID, "dcs_icons", "misc", 2);
		MAIN_INSTANCE.regSimpleItem(MainInit.oreIngot, ClimateCore.PACKAGE_ID, "dcs_ore_ingot", "ores", 18);
		MAIN_INSTANCE.regSimpleItem(MainInit.oreDust, ClimateCore.PACKAGE_ID, "dcs_ore_dust", "ores", 15);
		MAIN_INSTANCE.regSimpleItem(MainInit.oreItem, ClimateCore.PACKAGE_ID, "dcs_ore_item", "ores", 14);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems, ClimateCore.PACKAGE_ID, "dcs_ore_gem", "ores", 24);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems_red, ClimateCore.PACKAGE_ID, "dcs_ore_gem_red", "ores", 5);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems_green, ClimateCore.PACKAGE_ID, "dcs_ore_gem_green", "ores", 5);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems_blue, ClimateCore.PACKAGE_ID, "dcs_ore_gem_blue", "ores", 5);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems_white, ClimateCore.PACKAGE_ID, "dcs_ore_gem_white", "ores", 5);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems_black, ClimateCore.PACKAGE_ID, "dcs_ore_gem_black", "ores", 5);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems_layer, ClimateCore.PACKAGE_ID, "dcs_ore_gem_layer", "ores", 6);
		MAIN_INSTANCE.regSimpleItem(MainInit.miscDust, ClimateCore.PACKAGE_ID, "dcs_misc_dust", "ores", 13);
		MAIN_INSTANCE.regSimpleItem(MainInit.foodDust, ClimateCore.PACKAGE_ID, "dcs_food_dust", "food", 7);
		MAIN_INSTANCE.regSimpleItem(MainInit.stoneYagen, ClimateCore.PACKAGE_ID, "dcs_stone_yagen", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.brassYagen, ClimateCore.PACKAGE_ID, "dcs_brass_yagen", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.handSpindle, ClimateCore.PACKAGE_ID, "dcs_handspindle", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.gears, ClimateCore.PACKAGE_ID, "dcs_gear", "tool", 4);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothes, ClimateCore.PACKAGE_ID, "dcs_cloth", "tool", 11);
		MAIN_INSTANCE.regSimpleItem(MainInit.patternPaper, ClimateCore.PACKAGE_ID, "dcs_pattern_paper", "tool", 8);
		MAIN_INSTANCE.regSimpleItem(MainInit.silkworm, ClimateCore.PACKAGE_ID, "dcs_silkworm", "tool", 3);
		MAIN_INSTANCE.regSimpleItem(MainInit.crowDrill, ClimateCore.PACKAGE_ID, "dcs_crow_drill", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.wrench, ClimateCore.PACKAGE_ID, "dcs_wrench", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.bakedApple, ClimateCore.PACKAGE_ID, "dcs_baked_apple", "food", 7);
		MAIN_INSTANCE.regSimpleItem(MainInit.repairPutty, ClimateCore.PACKAGE_ID, "dcs_repair_putty", "tool", 2);
		MAIN_INSTANCE.regSimpleItem(MainInit.foodMaterials, ClimateCore.PACKAGE_ID, "dcs_food_materials", "food", 3);
		MAIN_INSTANCE.regSimpleItem(MainInit.flowerPot, ClimateCore.PACKAGE_ID, "dcs_flowerpot_white", "build", 1);
		MAIN_INSTANCE.regSimpleItem(MainInit.scope, ClimateCore.PACKAGE_ID, "dcs_thermal_scope", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.entityScope, ClimateCore.PACKAGE_ID, "dcs_entity_scope", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.itemDoorMarble, ClimateCore.PACKAGE_ID, "dcs_door_marble", "build", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.itemDoorGreisen, ClimateCore.PACKAGE_ID, "dcs_door_greisen", "build", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.itemDoorGypsum, ClimateCore.PACKAGE_ID, "dcs_door_gypsum", "build", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.itemDoorSteel, ClimateCore.PACKAGE_ID, "dcs_door_steel", "build", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.cushionGray, ClimateCore.PACKAGE_ID, "dcs_big_cushion", "build", 4);
		MAIN_INSTANCE.regSimpleItem(MainInit.tinder, ClimateCore.PACKAGE_ID, "dcs_tinder", "tool", 2);
		MAIN_INSTANCE.regSimpleItem(MainInit.animalFeed, ClimateCore.PACKAGE_ID, "dcs_animalfeed", "food", 2);
		MAIN_INSTANCE.regSimpleItem(MainInit.colorChanger, ClimateCore.PACKAGE_ID, "dcs_color_changer", "tool", 0);

		if (DCIntegrationCore.loadedForestry) {
			MAIN_INSTANCE.regSimpleItem(MainInit.circuit, ClimateCore.PACKAGE_ID, "dcs_plugin_circuit", "device", 0);
		}
		if (DCIntegrationCore.loadedMekanism) {
			MAIN_INSTANCE
					.regSimpleItem(MainInit.metalMaterials, ClimateCore.PACKAGE_ID, "dcs_plugin_metal_material", "device", 7);
		}

	}

	static void regBlocks() {
		regCube((ITexturePath) MainInit.dustBlock, ClimateCore.PACKAGE_ID, "dcs_ore_dustblock", "ores", 15);
		regCube((ITexturePath) MainInit.dustBlock_2, ClimateCore.PACKAGE_ID, "dcs_ore2_dustblock", "ores", 3);
		regCube((ITexturePath) MainInit.metalBlockNew, ClimateCore.PACKAGE_ID, "dcs_ore_metal_new", "ores", 7);
		regCube((ITexturePath) MainInit.metalBlockAlloy, ClimateCore.PACKAGE_ID, "dcs_ore_metal_alloy", "ores", 9);
		regCube((ITexturePath) MainInit.heatedMetalBlock, ClimateCore.PACKAGE_ID, "dcs_ore_heatingmetal", "ores", 14);
		regCube((ITexturePath) MainInit.gemBlock, ClimateCore.PACKAGE_ID, "dcs_ore_gemblock", "ores", 15);
		regCube((ITexturePath) MainInit.selenite, ClimateCore.PACKAGE_ID, "dcs_build_selenite", "build", 3);
		regCube((ITexturePath) MainInit.bricks, ClimateCore.PACKAGE_ID, "dcs_build_bricks", "build", 7);
		regCube((ITexturePath) MainInit.builds, ClimateCore.PACKAGE_ID, "dcs_build_build", "build", 11);
		regCube((ITexturePath) MainInit.syntheticBlock, ClimateCore.PACKAGE_ID, "dcs_build_synthetic_glass", "build", 15);
		regCube((ITexturePath) MainInit.linoleum, ClimateCore.PACKAGE_ID, "dcs_build_linoleum", "build", 15);
		regCube((ITexturePath) MainInit.clayBricks, ClimateCore.PACKAGE_ID, "dcs_build_claybrick", "build", 15);
		regCube((ITexturePath) MainInit.oreNew, ClimateCore.PACKAGE_ID, "dcs_ore_stone_new", "ores", 14);
		regCube((ITexturePath) MainInit.layerNew, ClimateCore.PACKAGE_ID, "dcs_layer_stone_new", "ores", 7);
		regCube((ITexturePath) MainInit.skarnBlock, ClimateCore.PACKAGE_ID, "dcs_layer_skarn", "ores", 2);
		regCube((ITexturePath) MainInit.skarnOre, ClimateCore.PACKAGE_ID, "dcs_layer_skarn_ore", "ores", 8);

		MAIN_INSTANCE.regSimpleBlock(MainInit.oreNew, ClimateCore.PACKAGE_ID, "dcs_ore_stone_new", "ores", 14);
		MAIN_INSTANCE.regSimpleBlock(MainInit.layerNew, ClimateCore.PACKAGE_ID, "dcs_layer_stone_new", "ores", 7);
		MAIN_INSTANCE.regSimpleBlock(MainInit.skarnBlock, ClimateCore.PACKAGE_ID, "dcs_layer_skarn", "ores", 2);
		MAIN_INSTANCE.regSimpleBlock(MainInit.skarnOre, ClimateCore.PACKAGE_ID, "dcs_layer_skarn_ore", "ores", 8);
		MAIN_INSTANCE.regSimpleBlock(MainInit.dustBlock, ClimateCore.PACKAGE_ID, "dcs_ore_dustblock", "ores", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.dustBlock_2, ClimateCore.PACKAGE_ID, "dcs_ore2_dustblock", "ores", 3);
		MAIN_INSTANCE.regSimpleBlock(MainInit.metalBlockNew, ClimateCore.PACKAGE_ID, "dcs_ore_metal_new", "ores", 7);
		MAIN_INSTANCE
				.regSimpleBlock(MainInit.metalBlockAlloy, ClimateCore.PACKAGE_ID, "dcs_ore_metal_alloy", "ores", 9);
		MAIN_INSTANCE
				.regSimpleBlock(MainInit.heatedMetalBlock, ClimateCore.PACKAGE_ID, "dcs_ore_heatingmetal", "ores", 14);
		MAIN_INSTANCE.regSimpleBlock(MainInit.gemBlock, ClimateCore.PACKAGE_ID, "dcs_ore_gemblock", "ores", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.selenite, ClimateCore.PACKAGE_ID, "dcs_build_selenite", "build", 3);
		MAIN_INSTANCE.regSimpleBlock(MainInit.chalLamp, ClimateCore.PACKAGE_ID, "dcs_build_challamp", "build", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.bricks, ClimateCore.PACKAGE_ID, "dcs_build_bricks", "build", 7);
		MAIN_INSTANCE.regSimpleBlock(MainInit.builds, ClimateCore.PACKAGE_ID, "dcs_build_build", "build", 11);
		MAIN_INSTANCE.regSimpleBlock(MainInit.linoleum, ClimateCore.PACKAGE_ID, "dcs_build_linoleum", "build", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.wallLamp, ClimateCore.PACKAGE_ID, "dcs_build_walllamp", "build", 3);
		MAIN_INSTANCE.regSimpleBlock(MainInit.oilLamp, ClimateCore.PACKAGE_ID, "dcs_build_oillamp", "build", 3);

		MAIN_INSTANCE.regSimpleBlock(MainInit.plate, ClimateCore.PACKAGE_ID, "dcs_build_plate", "build", 1);
		MAIN_INSTANCE.regSimpleBlock(MainInit.scaffold, ClimateCore.PACKAGE_ID, "dcs_build_scaffold", "build", 0);

		MAIN_INSTANCE
				.regSimpleBlock(MainInit.markingPanel, ClimateCore.PACKAGE_ID, "dcs_build_markingpanel", "build", 0);
		MAIN_INSTANCE.regSimpleBlock(MainInit.lightOrb, ClimateCore.PACKAGE_ID, "dcs_build_lightorb", "build", 0);

		MAIN_INSTANCE
				.regSimpleBlock(MainInit.syntheticBlock, ClimateCore.PACKAGE_ID, "dcs_build_synthetic_glass", "build", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.clayBricks, ClimateCore.PACKAGE_ID, "dcs_build_claybrick", "build", 15);

		MAIN_INSTANCE.regSimpleItem(Item
				.getItemFromBlock(MainInit.desiccant), ClimateCore.PACKAGE_ID, "dcs_desiccant", "build", 3);
		MAIN_INSTANCE.regSimpleItem(Item
				.getItemFromBlock(MainInit.freezepack), ClimateCore.PACKAGE_ID, "dcs_coolant", "build", 3);

		MAIN_INSTANCE.regTEBlock(MainInit.firestand, ClimateCore.PACKAGE_ID, "dcs_device_firestand", "device", 0, true);
		MAIN_INSTANCE.regTEBlock(MainInit.chamber, ClimateCore.PACKAGE_ID, "dcs_device_chamber", "machine", 0, true);
		MAIN_INSTANCE.regTEBlock(MainInit.shitirin, ClimateCore.PACKAGE_ID, "dcs_device_shitirin", "machine", 0, true);
		MAIN_INSTANCE
				.regTEBlock(MainInit.fuelStove, ClimateCore.PACKAGE_ID, "dcs_device_fuelstove", "machine", 0, true);

		MAIN_INSTANCE.regTETorqueBlock(MainInit.bellow, ClimateCore.PACKAGE_ID, "dcs_device_bellow", "device", 0, true);
		MAIN_INSTANCE
				.regTEBlock(MainInit.thermometer, ClimateCore.PACKAGE_ID, "dcs_device_thermometer", "device", 0, true);
		MAIN_INSTANCE.regTEBlock(MainInit.windvane, ClimateCore.PACKAGE_ID, "dcs_device_windvane", "device", 0, true);
		MAIN_INSTANCE
				.regTEBlock(MainInit.stevenson_screen, ClimateCore.PACKAGE_ID, "dcs_device_stevenson_screen", "machine", 0, true);
		MAIN_INSTANCE.regTEBlock(MainInit.pail, ClimateCore.PACKAGE_ID, "dcs_device_pail", "machine", 0, false);
		MAIN_INSTANCE.regSimpleBlock(MainInit.geyser, ClimateCore.PACKAGE_ID, "dcs_device_geyser_stone", "device", 0);
		MAIN_INSTANCE
				.regSimpleBlock(MainInit.swedishTorch, ClimateCore.PACKAGE_ID, "dcs_device_swedish_torch", "device", 15);

		ModelLoader.setCustomStateMapper(MainInit.roofStraw, (new StateMap.Builder()).ignore(BlockStairs.HALF)
				.build());
		ModelLoader.setCustomStateMapper(MainInit.windowBlinds, (new StateMap.Builder()).ignore(DCState.DOUBLE)
				.ignore(DCState.POWERED)
				.build());

		// door
		if (ModuleConfig.build_advanced) {
			MAIN_INSTANCE
					.regSimpleBlock(MainInit.sinkMetal, ClimateCore.PACKAGE_ID, "dcs_device_sink_half", "device", 0);
			MAIN_INSTANCE
					.regSimpleBlock(MainInit.sinkChest, ClimateCore.PACKAGE_ID, "dcs_device_sink_full", "device", 0);
			MAIN_INSTANCE
					.regSimpleBlock(MainInit.craftingCounter, ClimateCore.PACKAGE_ID, "dcs_device_crafting_counter", "device", 0);
			MAIN_INSTANCE
					.regSimpleBlock(MainInit.kitchenHood, ClimateCore.PACKAGE_ID, "dcs_device_kitchen_hood", "device", 0);
			MAIN_INSTANCE.regSimpleBlock(MainInit.awning, ClimateCore.PACKAGE_ID, "dcs_build_awning", "build", 3);
			MAIN_INSTANCE
					.regSimpleBlock(MainInit.lampCarbide, ClimateCore.PACKAGE_ID, "dcs_lamp_carbide_lantern", "build", 0);
			MAIN_INSTANCE
					.regSimpleBlock(MainInit.lampGas, ClimateCore.PACKAGE_ID, "dcs_lamp_carbide_glass", "build", 0);
			MAIN_INSTANCE.regSimpleBlock(MainInit.hedgeSpring, ClimateCore.PACKAGE_ID, "dcs_hedge_spring", "crop", 0);
			MAIN_INSTANCE.regSimpleBlock(MainInit.hedgeSummer, ClimateCore.PACKAGE_ID, "dcs_hedge_summer", "crop", 0);
			MAIN_INSTANCE.regSimpleBlock(MainInit.hedgeAutumn, ClimateCore.PACKAGE_ID, "dcs_hedge_autumn", "crop", 0);
			MAIN_INSTANCE.regSimpleBlock(MainInit.hedgeWinter, ClimateCore.PACKAGE_ID, "dcs_hedge_winter", "crop", 0);

			ModelLoader.setCustomStateMapper(MainInit.doorMarble, (new StateMap.Builder()).ignore(BlockDoor.POWERED)
					.build());
			ModelLoader.setCustomStateMapper(MainInit.doorGreisen, (new StateMap.Builder()).ignore(BlockDoor.POWERED)
					.build());
			ModelLoader.setCustomStateMapper(MainInit.doorGypsum, (new StateMap.Builder()).ignore(BlockDoor.POWERED)
					.build());
			ModelLoader.setCustomStateMapper(MainInit.doorSteel, (new StateMap.Builder()).ignore(BlockDoor.POWERED)
					.build());

			MAIN_INSTANCE
					.regTEBlock(MainInit.chestMetal, ClimateCore.PACKAGE_ID, "dcs_device_chest_metal", "device", 0, true);
			MAIN_INSTANCE
					.regTEBlock(MainInit.chestMagnet, ClimateCore.PACKAGE_ID, "dcs_device_chest_magnet", "device", 0, true);
			MAIN_INSTANCE
					.regTEBlock(MainInit.chestVillage, ClimateCore.PACKAGE_ID, "dcs_device_chest_village", "device", 0, true);

			MAIN_INSTANCE
					.regTEBlock(MainInit.realtimeClock, ClimateCore.PACKAGE_ID, "dcs_device_realtimeclock", "device", 3, true);

			ModelLoader.setCustomStateMapper(MainInit.realtimeClock_L, (new StateMap.Builder()).ignore(DCState.FACING)
					.build());
			MAIN_INSTANCE.regSimpleItem(Item
					.getItemFromBlock(MainInit.realtimeClock_L), ClimateCore.PACKAGE_ID, "dcs_device_realtimeclock_l", "device", 0);

			ModelLoader.setCustomStateMapper(MainInit.mcClock_L, (new StateMap.Builder()).ignore(DCState.FACING)
					.build());
			MAIN_INSTANCE.regSimpleItem(Item
					.getItemFromBlock(MainInit.mcClock_L), ClimateCore.PACKAGE_ID, "dcs_device_mcclock_l", "device", 0);
			MAIN_INSTANCE.regSimpleBlock(MainInit.flowerVase, ClimateCore.PACKAGE_ID, "dcs_build_vase", "build", 4);
			MAIN_INSTANCE
					.regSimpleBlock(MainInit.planting, ClimateCore.PACKAGE_ID, "dcs_build_flower_planting", "build", 3);

			ModelLoader.setCustomStateMapper(MainInit.bed, (new StateMap.Builder()).ignore(BlockBed.PART)
					.ignore(BlockBed.OCCUPIED).ignore(BlockBed.FACING).build());
			ModelLoader.setCustomStateMapper(MainInit.bedWhite, (new StateMap.Builder()).ignore(BlockBed.PART)
					.ignore(BlockBed.OCCUPIED).ignore(BlockBed.FACING).build());
			ModelLoader.setCustomStateMapper(MainInit.bedRattan, (new StateMap.Builder()).ignore(BlockBed.PART)
					.ignore(BlockBed.OCCUPIED).ignore(BlockBed.FACING).build());
			ModelLoader.setCustomStateMapper(MainInit.bedFuton, (new StateMap.Builder()).ignore(BlockBed.PART)
					.ignore(BlockBed.OCCUPIED).ignore(BlockBed.FACING).build());
			ModelLoader.setCustomStateMapper(MainInit.bedHammock, (new StateMap.Builder()).ignore(BlockBed.PART)
					.ignore(BlockBed.OCCUPIED).ignore(BlockBed.FACING).build());
			MAIN_INSTANCE.regSimpleBlock(MainInit.bed, ClimateCore.PACKAGE_ID, "dcs_bed", "build", 0);
			MAIN_INSTANCE.regSimpleBlock(MainInit.bedWhite, ClimateCore.PACKAGE_ID, "dcs_bed_white", "build", 0);
			MAIN_INSTANCE.regSimpleBlock(MainInit.bedRattan, ClimateCore.PACKAGE_ID, "dcs_bed_rattan", "build", 0);
			MAIN_INSTANCE.regSimpleBlock(MainInit.bedFuton, ClimateCore.PACKAGE_ID, "dcs_bed_futon", "build", 0);
			MAIN_INSTANCE.regSimpleBlock(MainInit.bedHammock, ClimateCore.PACKAGE_ID, "dcs_bed_hammock", "build", 0);
			MAIN_INSTANCE.regSimpleItem(MainInit.itemBed, ClimateCore.PACKAGE_ID, "dcs_bed_item", "build", 4);

			ModelLoader.setCustomStateMapper(MainInit.doorHikido, (new StateMap.Builder()).ignore(DCState.FLAG)
					.ignore(DCState.POWERED).build());
			MAIN_INSTANCE.regSimpleBlock(MainInit.doorHikido, ClimateCore.PACKAGE_ID, "dcs_door_hikido", "build", 0);
		}

		if (!ModuleConfig.delete_old) {
			MAIN_INSTANCE.regSimpleBlock(MainInit.ores, ClimateCore.PACKAGE_ID, "dcs_ore_stone", "ores", 15);
			MAIN_INSTANCE.regSimpleBlock(MainInit.ores_2, ClimateCore.PACKAGE_ID, "dcs_ore2_stone", "ores", 12);
		}
	}

	static void regTools() {

		if (ModuleConfig.tool) {
			String[] name = {
					"brass",
					"steel",
					"silver",
					"nickelsilver",
					"chalcedony",
					"sapphire",
					"titanium",
					"garnet",
					"toolsteel",
					"mangalloy"
			};
			for (int j = 0; j < name.length; j++) {
				MAIN_INSTANCE
						.regSimpleItem(MainInit.dcAxe[j], ClimateCore.PACKAGE_ID, "dcs_axe_" + name[j], "equip", 0);
				MAIN_INSTANCE
						.regSimpleItem(MainInit.dcPickaxe[j], ClimateCore.PACKAGE_ID, "dcs_pickaxe_" + name[j], "equip", 0);
				MAIN_INSTANCE
						.regSimpleItem(MainInit.dcSpade[j], ClimateCore.PACKAGE_ID, "dcs_spade_" + name[j], "equip", 0);
				if (j < 9)
					MAIN_INSTANCE
							.regSimpleItem(MainInit.dcSword[j], ClimateCore.PACKAGE_ID, "dcs_sword_" + name[j], "equip", 0);
			}
			String[] type = {
					"met",
					"plate",
					"leggins",
					"boots"
			};
			for (int i = 0; i < 4; i++) {
				MAIN_INSTANCE
						.regSimpleItem(MainInit.brassArmor[i], ClimateCore.PACKAGE_ID, "dcs_" + type[i] + "_brass", "equip", 0);
				MAIN_INSTANCE
						.regSimpleItem(MainInit.steelArmor[i], ClimateCore.PACKAGE_ID, "dcs_" + type[i] + "_steel", "equip", 0);
				MAIN_INSTANCE
						.regSimpleItem(MainInit.chalcArmor[i], ClimateCore.PACKAGE_ID, "dcs_" + type[i] + "_chalcedony", "equip", 0);
				MAIN_INSTANCE
						.regSimpleItem(MainInit.sapphireArmor[i], ClimateCore.PACKAGE_ID, "dcs_" + type[i] + "_sapphire", "equip", 0);
				MAIN_INSTANCE
						.regSimpleItem(MainInit.titaniumArmor[i], ClimateCore.PACKAGE_ID, "dcs_" + type[i] + "_titanium", "equip", 0);
				MAIN_INSTANCE
						.regSimpleItem(MainInit.silverArmor[i], ClimateCore.PACKAGE_ID, "dcs_" + type[i] + "_silver", "equip", 0);
			}

			String[] name2 = {
					"brass",
					"steel",
					"chalcedony",
					"garnet",
					"stone",
					"toolsteel"
			};
			for (int j = 0; j < name2.length; j++) {
				MAIN_INSTANCE
						.regSimpleItem(MainInit.dcScythe[j], ClimateCore.PACKAGE_ID, "dcs_scythe_" + name[j], "equip", 0);
			}

			MAIN_INSTANCE.regSimpleItem(MainInit.earthSpade, ClimateCore.PACKAGE_ID, "dcs_spade_earth", "equip", 0);
			MAIN_INSTANCE.regSimpleItem(MainInit.earthRake, ClimateCore.PACKAGE_ID, "dcs_rake_earth", "equip", 0);
			MAIN_INSTANCE
					.regSimpleItem(MainInit.toolsteelRake, ClimateCore.PACKAGE_ID, "dcs_rake_toolsteel", "equip", 0);
			MAIN_INSTANCE
					.regSimpleItem(MainInit.shieldSynthetic, ClimateCore.PACKAGE_ID, "dcs_shield_synthetic", "equip", 0);
			MAIN_INSTANCE
					.regSimpleItem(MainInit.shieldTitanium, ClimateCore.PACKAGE_ID, "dcs_shield_titanium", "equip", 0);
			MAIN_INSTANCE.regSimpleItem(MainInit.handNet, ClimateCore.PACKAGE_ID, "dcs_handnet", "equip", 0);

		}

		MAIN_INSTANCE.regSimpleItem(MainInit.crossbow, ClimateCore.PACKAGE_ID, "dcs_crossbow_iron", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.gun, ClimateCore.PACKAGE_ID, "dcs_musket_steel", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.cartridge, ClimateCore.PACKAGE_ID, "dcs_cartridge", "equip", 7);
		MAIN_INSTANCE.regSimpleItem(MainInit.throwingArrow, ClimateCore.PACKAGE_ID, "dcs_throwing_arrow", "equip", 0);

		MAIN_INSTANCE.regSimpleItem(MainInit.linenUnder, ClimateCore.PACKAGE_ID, "dcs_leggins_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.linenCoat, ClimateCore.PACKAGE_ID, "dcs_plate_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothUnder, ClimateCore.PACKAGE_ID, "dcs_leggins_cloth", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.workerSuit, ClimateCore.PACKAGE_ID, "dcs_leggins_worker", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.blackSuit, ClimateCore.PACKAGE_ID, "dcs_leggins_black", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.trackSuit, ClimateCore.PACKAGE_ID, "dcs_leggins_tracksuit", "equip", 0);
		MAIN_INSTANCE
				.regSimpleItem(MainInit.combatDress, ClimateCore.PACKAGE_ID, "dcs_leggins_combatdress", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.hoodie, ClimateCore.PACKAGE_ID, "dcs_plate_hoodie", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.leatherHat, ClimateCore.PACKAGE_ID, "dcs_hat_leather", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.cottonHat, ClimateCore.PACKAGE_ID, "dcs_hat_cotton", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.woolWear, ClimateCore.PACKAGE_ID, "dcs_wear_wool", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.furWear, ClimateCore.PACKAGE_ID, "dcs_wear_fue", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.woolBoots, ClimateCore.PACKAGE_ID, "dcs_boots_wool", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.peaCoat, ClimateCore.PACKAGE_ID, "dcs_plate_peacoat", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.blackCoat, ClimateCore.PACKAGE_ID, "dcs_plate_silk", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothCoat, ClimateCore.PACKAGE_ID, "dcs_plate_cloth", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.magicUnder, ClimateCore.PACKAGE_ID, "dcs_leggins_magic", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.magicCoat, ClimateCore.PACKAGE_ID, "dcs_plate_magic", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.linenJacket, ClimateCore.PACKAGE_ID, "dcs_plate_jacket_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothJacket, ClimateCore.PACKAGE_ID, "dcs_plate_jacket_cloth", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.woolJacket, ClimateCore.PACKAGE_ID, "dcs_plate_jacket_wool", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.modsCoat, ClimateCore.PACKAGE_ID, "dcs_plate_mods", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.linenShirt, ClimateCore.PACKAGE_ID, "dcs_leggins_shirt_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothShirt, ClimateCore.PACKAGE_ID, "dcs_leggins_shirt_cloth", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.linenBottom, ClimateCore.PACKAGE_ID, "dcs_boots_pants_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothBottom, ClimateCore.PACKAGE_ID, "dcs_boots_pants_cloth", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.flowerSkirt, ClimateCore.PACKAGE_ID, "dcs_boots_skirt_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothSkirt, ClimateCore.PACKAGE_ID, "dcs_boots_skirt_cloth", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.silkSkirt, ClimateCore.PACKAGE_ID, "dcs_boots_skirt_silk", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.silkDress, ClimateCore.PACKAGE_ID, "dcs_leggins_dress_silk", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.silkCape, ClimateCore.PACKAGE_ID, "dcs_met_cape_silk", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.furCape, ClimateCore.PACKAGE_ID, "dcs_met_cape_fur", "equip", 0);
	}

	static void regFluids() {
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.oilBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel_Oil = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_oil", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel_Oil;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.oilBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel_Oil = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_oil", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel_Oil;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.greenTeaBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_greentea", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.greenTeaBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_greentea", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.blackTeaBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_blacktea", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.blackTeaBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_blacktea", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.coffeeBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_coffee", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.coffeeBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_coffee", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.creamBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_cream", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.creamBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_cream", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.tomatoBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_vegetable", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.tomatoBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_vegetable", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.blackLiquorBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_black_liquor", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.blackLiquorBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_black_liquor", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.hotSpringBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_hotspring", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.hotSpringBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_hotspring", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.stockBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_stock", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.stockBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_stock", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.lemonBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_lemonade", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.lemonBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_lemonade", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.mazaiBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_mazai", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.mazaiBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_mazai", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.soyMilkBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_soy_milk", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.soyMilkBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_soy_milk", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.hydrogenBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_hydrogen", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.hydrogenBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_hydrogen", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.ammoniaBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_ammonia", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.ammoniaBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_ammonia", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.fuelGasBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_gas", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.fuelGasBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_gas", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.fuelOilBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_oil", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.fuelOilBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_oil", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.nitricAcidBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_nitric_acid", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.nitricAcidBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_nitric_acid", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item
				.getItemFromBlock(MainInit.sulfuricAcidBlock), new ItemMeshDefinition() {
					final ModelResourceLocation fluidModel = new ModelResourceLocation(
							ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_sulfuric_acid", "fluid");

					@Override
					public ModelResourceLocation getModelLocation(ItemStack stack) {
						return fluidModel;
					}
				});
		ModelLoader.setCustomStateMapper(MainInit.sulfuricAcidBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_sulfuric_acid", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.nitrogenBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_nitrogen", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.nitrogenBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_nitrogen", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.ethanolBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_ethanol", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.ethanolBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_ethanol", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.milkBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_raw_milk", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.milkBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_raw_milk", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.steamBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_steam", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.steamBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_steam", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.oxygenBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_oxygen", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.oxygenBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(
					ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_oxygen", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
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
