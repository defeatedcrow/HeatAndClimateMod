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
	public static final JsonRegisterHelper MAIN_INSTANCE = new JsonRegisterHelper("F:\\modding\\1.12.1\\hac_main\\src\\main\\resources");

	public static void load() {
		regItems();
		regBlocks();
		regTools();
		regFluids();
	}

	static void regItems() {
		MAIN_INSTANCE.regSimpleItem(MainInit.iconItem, "dcs_climate", "dcs_icons", "misc", 2);
		MAIN_INSTANCE.regSimpleItem(MainInit.oreIngot, "dcs_climate", "dcs_ore_ingot", "ores", 18);
		MAIN_INSTANCE.regSimpleItem(MainInit.oreDust, "dcs_climate", "dcs_ore_dust", "ores", 15);
		MAIN_INSTANCE.regSimpleItem(MainInit.oreItem, "dcs_climate", "dcs_ore_item", "ores", 14);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems, "dcs_climate", "dcs_ore_gem", "ores", 24);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems_red, "dcs_climate", "dcs_ore_gem_red", "ores", 5);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems_green, "dcs_climate", "dcs_ore_gem_green", "ores", 5);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems_blue, "dcs_climate", "dcs_ore_gem_blue", "ores", 5);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems_white, "dcs_climate", "dcs_ore_gem_white", "ores", 5);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems_black, "dcs_climate", "dcs_ore_gem_black", "ores", 5);
		MAIN_INSTANCE.regSimpleItem(MainInit.gems_layer, "dcs_climate", "dcs_ore_gem_layer", "ores", 6);
		MAIN_INSTANCE.regSimpleItem(MainInit.miscDust, "dcs_climate", "dcs_misc_dust", "ores", 13);
		MAIN_INSTANCE.regSimpleItem(MainInit.foodDust, "dcs_climate", "dcs_food_dust", "food", 7);
		MAIN_INSTANCE.regSimpleItem(MainInit.stoneYagen, "dcs_climate", "dcs_stone_yagen", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.brassYagen, "dcs_climate", "dcs_brass_yagen", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.handSpindle, "dcs_climate", "dcs_handspindle", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.gears, "dcs_climate", "dcs_gear", "tool", 4);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothes, "dcs_climate", "dcs_cloth", "tool", 11);
		MAIN_INSTANCE.regSimpleItem(MainInit.patternPaper, "dcs_climate", "dcs_pattern_paper", "tool", 8);
		MAIN_INSTANCE.regSimpleItem(MainInit.silkworm, "dcs_climate", "dcs_silkworm", "tool", 3);
		MAIN_INSTANCE.regSimpleItem(MainInit.crowDrill, "dcs_climate", "dcs_crow_drill", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.wrench, "dcs_climate", "dcs_wrench", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.bakedApple, "dcs_climate", "dcs_baked_apple", "food", 7);
		MAIN_INSTANCE.regSimpleItem(MainInit.repairPutty, "dcs_climate", "dcs_repair_putty", "tool", 2);
		MAIN_INSTANCE.regSimpleItem(MainInit.foodMaterials, "dcs_climate", "dcs_food_materials", "food", 3);
		MAIN_INSTANCE.regSimpleItem(MainInit.flowerPot, "dcs_climate", "dcs_flowerpot_white", "build", 1);
		MAIN_INSTANCE.regSimpleItem(MainInit.desktopAccessories, "dcs_climate", "dcs_desktop_accessories", "build", 8);
		MAIN_INSTANCE.regSimpleItem(MainInit.coatRack, "dcs_climate", "dcs_coat_rack", "build", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.scope, "dcs_climate", "dcs_thermal_scope", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.entityScope, "dcs_climate", "dcs_entity_scope", "tool", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.itemDoorMarble, "dcs_climate", "dcs_door_marble", "build", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.itemDoorGreisen, "dcs_climate", "dcs_door_greisen", "build", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.itemDoorGypsum, "dcs_climate", "dcs_door_gypsum", "build", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.itemDoorSteel, "dcs_climate", "dcs_door_steel", "build", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.cushionGray, "dcs_climate", "dcs_big_cushion", "build", 4);
		MAIN_INSTANCE.regSimpleItem(MainInit.tinder, "dcs_climate", "dcs_tinder", "tool", 2);
		MAIN_INSTANCE.regSimpleItem(MainInit.animalFeed, "dcs_climate", "dcs_animalfeed", "food", 2);
		MAIN_INSTANCE.regSimpleItem(MainInit.colorChanger, "dcs_climate", "dcs_color_changer", "tool", 0);
		if (DCIntegrationCore.loadedForestry) {
			MAIN_INSTANCE.regSimpleItem(MainInit.circuit, "dcs_climate", "dcs_plugin_circuit", "device", 0);
		}
		if (DCIntegrationCore.loadedMekanism) {
			MAIN_INSTANCE.regSimpleItem(MainInit.metalMaterials, "dcs_climate", "dcs_plugin_metal_material", "device", 7);
		}
	}

	static void regBlocks() {
		regCube((ITexturePath) MainInit.dustBlock, "dcs_climate", "dcs_ore_dustblock", "ores", 15);
		regCube((ITexturePath) MainInit.dustBlock_2, "dcs_climate", "dcs_ore2_dustblock", "ores", 3);
		regCube((ITexturePath) MainInit.metalBlockNew, "dcs_climate", "dcs_ore_metal_new", "ores", 7);
		regCube((ITexturePath) MainInit.metalBlockAlloy, "dcs_climate", "dcs_ore_metal_alloy", "ores", 9);
		regCube((ITexturePath) MainInit.heatedMetalBlock, "dcs_climate", "dcs_ore_heatingmetal", "ores", 14);
		regCube((ITexturePath) MainInit.gemBlock, "dcs_climate", "dcs_ore_gemblock", "ores", 15);
		regCube((ITexturePath) MainInit.bricks, "dcs_climate", "dcs_build_bricks", "build", 7);
		regCube((ITexturePath) MainInit.builds, "dcs_climate", "dcs_build_build", "build", 11);
		regCube((ITexturePath) MainInit.syntheticBlock, "dcs_climate", "dcs_build_synthetic_glass", "build", 15);
		regCube((ITexturePath) MainInit.linoleum, "dcs_climate", "dcs_build_linoleum", "build", 15);
		regCube((ITexturePath) MainInit.clayBricks, "dcs_climate", "dcs_build_claybrick", "build", 15);
		regCube((ITexturePath) MainInit.oreNew, "dcs_climate", "dcs_ore_stone_new", "ores", 14);
		regCube((ITexturePath) MainInit.layerNew, "dcs_climate", "dcs_layer_stone_new", "ores", 7);
		regCube((ITexturePath) MainInit.skarnBlock, "dcs_climate", "dcs_layer_skarn", "ores", 2);
		regCube((ITexturePath) MainInit.skarnOre, "dcs_climate", "dcs_layer_skarn_ore", "ores", 8);
		// ores
		MAIN_INSTANCE.regSimpleBlock(MainInit.oreNew, "dcs_climate", "dcs_ore_stone_new", "ores", 14);
		MAIN_INSTANCE.regSimpleBlock(MainInit.layerNew, "dcs_climate", "dcs_layer_stone_new", "ores", 7);
		MAIN_INSTANCE.regSimpleBlock(MainInit.skarnBlock, "dcs_climate", "dcs_layer_skarn", "ores", 2);
		MAIN_INSTANCE.regSimpleBlock(MainInit.skarnOre, "dcs_climate", "dcs_layer_skarn_ore", "ores", 8);
		MAIN_INSTANCE.regSimpleBlock(MainInit.dustBlock, "dcs_climate", "dcs_ore_dustblock", "ores", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.dustBlock_2, "dcs_climate", "dcs_ore2_dustblock", "ores", 3);
		MAIN_INSTANCE.regSimpleBlock(MainInit.metalBlockNew, "dcs_climate", "dcs_ore_metal_new", "ores", 7);
		MAIN_INSTANCE.regSimpleBlock(MainInit.metalBlockAlloy, "dcs_climate", "dcs_ore_metal_alloy", "ores", 9);
		MAIN_INSTANCE.regSimpleBlock(MainInit.heatedMetalBlock, "dcs_climate", "dcs_ore_heatingmetal", "ores", 14);
		MAIN_INSTANCE.regSimpleBlock(MainInit.gemBlock, "dcs_climate", "dcs_ore_gemblock", "ores", 15);
		// building
		MAIN_INSTANCE.regStateAndBlock(MainInit.wallpaper, "dcs_climate", "dcs_build_wallpaper", "build", 15, false);
		MAIN_INSTANCE.regStateAndBlock(MainInit.clayBricks, "dcs_climate", "dcs_build_claybrick", "build", 15, false);
		ClimateMain.proxy.regBlockSlab(MainInit.stairsGlass, "dcs_climate", "dcs_stairs_glass", "build");
		ClimateMain.proxy.regBlockSlab(MainInit.stairsGypsum, "dcs_climate", "dcs_stairs_gypsum", "build");
		ClimateMain.proxy.regBlockSlab(MainInit.stairsMarble, "dcs_climate", "dcs_stairs_marble", "build");
		ClimateMain.proxy.regBlockSlab(MainInit.stairsSerpentine, "dcs_climate", "dcs_stairs_serpentine", "build");
		ClimateMain.proxy.regBlockSlab(MainInit.stairsBedrock, "dcs_climate", "dcs_stairs_bedrock", "build");
		ClimateMain.proxy.regBlockSlab(MainInit.stairsDirtbrick, "dcs_climate", "dcs_stairs_dirtbrick", "build");
		ClimateMain.proxy.regBlockSlab(MainInit.stairsSkarn, "dcs_climate", "dcs_stairs_skarn", "build");
		ClimateMain.proxy.regBlockSlab(MainInit.stairsGreisen, "dcs_climate", "dcs_stairs_greisen", "build");
		ClimateMain.proxy.regBlockSlab(MainInit.roofSlate, "dcs_climate", "dcs_roof_slate", "build");
		ClimateMain.proxy.regBlockSlab(MainInit.roofSlateRed, "dcs_climate", "dcs_roof_slate_red", "build");
		ClimateMain.proxy.regBlockSlab(MainInit.roofSlateGreen, "dcs_climate", "dcs_roof_slate_green", "build");
		ClimateMain.proxy.regBlockSlab(MainInit.roofSlateBrown, "dcs_climate", "dcs_roof_slate_brown", "build");
		ClimateMain.proxy.regBlockSlab(MainInit.roofStraw, "dcs_climate", "dcs_roof_straw", "build");
		MAIN_INSTANCE.regStateAndBlock(MainInit.halfSlab, "dcs_climate", "dcs_build_slab", "build", 7, true);
		MAIN_INSTANCE.regStateAndBlock(MainInit.halfSlab2, "dcs_climate", "dcs_build_slab_chal", "build", 6, true);
		MAIN_INSTANCE.regStateAndBlock(MainInit.halfSlab3, "dcs_climate", "dcs_build_slab_slate", "build", 3, true);
		ClimateMain.proxy.regBlockSlab(MainInit.fenceGypsum, "dcs_climate", "dcs_fence_gypsum", "build");
		ClimateMain.proxy.regBlockSlab(MainInit.fenceMarble, "dcs_climate", "dcs_fence_marble", "build");
		ClimateMain.proxy.regBlockSlab(MainInit.fenceSerpentine, "dcs_climate", "dcs_fence_serpentine", "build");
		ClimateMain.proxy.regBlockSlab(MainInit.fenceBedrock, "dcs_climate", "dcs_fence_bedrock", "build");
		MAIN_INSTANCE.regStateAndBlock(MainInit.strawBlock, "dcs_climate", "dcs_pillar_straw", "build", 0, false);
		MAIN_INSTANCE.regStateAndBlock(MainInit.plate, "dcs_climate", "dcs_build_plate", "build", 1, false);
		MAIN_INSTANCE.regStateAndBlock(MainInit.pressureChal, "dcs_climate", "dcs_build_pressure_chal", "build", 0, false);
		MAIN_INSTANCE.regStateAndBlock(MainInit.pressureOlivine, "dcs_climate", "dcs_build_pressure_olivine", "build", 0, false);
		MAIN_INSTANCE.regSimpleBlock(MainInit.chalLamp, "dcs_climate", "dcs_build_challamp", "build", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.bricks, "dcs_climate", "dcs_build_bricks", "build", 7);
		MAIN_INSTANCE.regSimpleBlock(MainInit.builds, "dcs_climate", "dcs_build_build", "build", 11);
		MAIN_INSTANCE.regSimpleBlock(MainInit.linoleum, "dcs_climate", "dcs_build_linoleum", "build", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.wallLamp, "dcs_climate", "dcs_build_walllamp", "build", 3);
		MAIN_INSTANCE.regSimpleBlock(MainInit.oilLamp, "dcs_climate", "dcs_build_oillamp", "build", 3);
		MAIN_INSTANCE.regSimpleBlock(MainInit.plate, "dcs_climate", "dcs_build_plate", "build", 1);
		MAIN_INSTANCE.regSimpleBlock(MainInit.scaffold, "dcs_climate", "dcs_build_scaffold", "build", 0);
		MAIN_INSTANCE.regStateAndBlock(MainInit.markingPanel, "dcs_climate", "dcs_build_markingpanel", "build", 0, false);
		MAIN_INSTANCE.regStateAndBlock(MainInit.lightOrb, "dcs_climate", "dcs_build_lightorb", "build", 0, true);
		MAIN_INSTANCE.regSimpleBlock(MainInit.syntheticBlock, "dcs_climate", "dcs_build_synthetic_glass", "build", 15);
		MAIN_INSTANCE.regSimpleBlock(MainInit.clayBricks, "dcs_climate", "dcs_build_claybrick", "build", 15);
		MAIN_INSTANCE.regStateAndBlock(MainInit.desiccant, "dcs_climate", "dcs_desiccant", "build", 3, false);
		MAIN_INSTANCE.regStateAndBlock(MainInit.freezepack, "dcs_climate", "dcs_coolant", "build", 3, false);
		// device
		MAIN_INSTANCE.regStateAndBlock(MainInit.firestand, "dcs_climate", "dcs_device_firestand", "device", 0, true);
		MAIN_INSTANCE.regStateAndBlock(MainInit.chamber, "dcs_climate", "dcs_device_chamber", "machine", 0, true);
		MAIN_INSTANCE.regStateAndBlock(MainInit.shitirin, "dcs_climate", "dcs_device_shitirin", "machine", 0, true);
		MAIN_INSTANCE.regStateAndBlock(MainInit.fuelStove, "dcs_climate", "dcs_device_fuelstove", "machine", 0, true);
		MAIN_INSTANCE.regStateAndBlock(MainInit.bellow, "dcs_climate", "dcs_device_bellow", "device", 0, true);
		MAIN_INSTANCE.regStateAndBlock(MainInit.thermometer, "dcs_climate", "dcs_device_thermometer", "device", 0, true);
		MAIN_INSTANCE.regStateAndBlock(MainInit.windvane, "dcs_climate", "dcs_device_windvane", "device", 0, true);
		MAIN_INSTANCE.regStateAndBlock(MainInit.stevenson_screen, "dcs_climate", "dcs_device_stevenson_screen", "machine", 0, true);
		MAIN_INSTANCE.regStateAndBlock(MainInit.pail, "dcs_climate", "dcs_device_pail", "machine", 3, false);
		MAIN_INSTANCE.regSimpleBlock(MainInit.geyser, "dcs_climate", "dcs_device_geyser_stone", "device", 0);
		MAIN_INSTANCE.regSimpleBlock(MainInit.swedishTorch, "dcs_climate", "dcs_device_swedish_torch", "device", 15);
		ModelLoader.setCustomStateMapper(MainInit.roofStraw, (new StateMap.Builder()).ignore(BlockStairs.HALF).build());
		// selenite glass
		MAIN_INSTANCE.regSimpleItem(Item.getItemFromBlock(MainInit.selenite), "dcs_climate", "dcs_build_selenite", "build", 3);
		ModelLoader.setCustomStateMapper(MainInit.selenite, new StateMapperBase() {
			final ModelResourceLocation selenite = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_glass_selenite", "multipart");
			final ModelResourceLocation light = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_glass_light", "multipart");
			final ModelResourceLocation dark = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_glass_dark", "multipart");
			final ModelResourceLocation crystal = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_glass_crystal", "multipart");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				if (state != null) {
					int type = DCState.getInt(state, DCState.TYPE16);
					switch (type) {
					case 0:
						return selenite;
					case 1:
						return light;
					case 2:
						return dark;
					case 3:
						return crystal;
					default:
						return selenite;
					}
				}
				return selenite;
			}
		});
		if (ModuleConfig.build_advanced) {
			MAIN_INSTANCE.regStateAndBlock(MainInit.pillarSteel, "dcs_climate", "dcs_pillar_steel", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.fenceSteel, "dcs_climate", "dcs_fence_steel", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.fenceNetSteel, "dcs_climate", "dcs_fence_net_steel", "build", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.fenceAluminium, "dcs_climate", "dcs_fence_aluminium", "build", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.fenceNet, "dcs_climate", "dcs_fence_net", "build", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.fenceGlass, "dcs_climate", "dcs_fence_glass", "build", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.fenceRattan, "dcs_climate", "dcs_fence_rattan", "build", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.fenceLadder, "dcs_climate", "dcs_fence_ladder", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.fenceLadderSteel, "dcs_climate", "dcs_fence_ladder_steel", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.awningWood, "dcs_climate", "dcs_build_awning", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.awningMetal, "dcs_climate", "dcs_build_awning_metal", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.awningLinen, "dcs_climate", "dcs_build_awning_linen", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.awningCloth, "dcs_climate", "dcs_build_awning_cloth", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chain, "dcs_climate", "dcs_build_chain", "build", 3, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.tableMarble, "dcs_climate", "dcs_table_marble", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.tableGypsum, "dcs_climate", "dcs_table_gypsum", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.tableWood, "dcs_climate", "dcs_table_wood", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.tableDark, "dcs_climate", "dcs_table_darkwood", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.squaretableWood, "dcs_climate", "dcs_squaretable_wood", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.squaretableMarble, "dcs_climate", "dcs_squaretable_marble", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.squaretableSkarn, "dcs_climate", "dcs_squaretable_skarn", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.squaretableGreisen, "dcs_climate", "dcs_squaretable_greisen", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.squaretableChecker, "dcs_climate", "dcs_squaretable_checker", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.squaretableBlack, "dcs_climate", "dcs_squaretable_black", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.squaretableRattan, "dcs_climate", "dcs_squaretable_rattan", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.smallTableWood, "dcs_climate", "dcs_smalltable_wood", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.smallTableStone, "dcs_climate", "dcs_smalltable_stone", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.carpetRed, "dcs_climate", "dcs_carpet_red", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.carpetWhite, "dcs_climate", "dcs_carpet_white", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.carpetGray, "dcs_climate", "dcs_carpet_gray", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.carpetLinen, "dcs_climate", "dcs_carpet_linen", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.carpetTatami, "dcs_climate", "dcs_carpet_tatami", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.sofaBlack, "dcs_climate", "dcs_sofa_black", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.sofaRed, "dcs_climate", "dcs_sofa_red", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.sofaGreen, "dcs_climate", "dcs_sofa_green", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.sofaPink, "dcs_climate", "dcs_sofa_pink", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.sofaBlue, "dcs_climate", "dcs_sofa_blue", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.sofaRattan, "dcs_climate", "dcs_sofa_rattan", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.stoolBlack, "dcs_climate", "dcs_stool_black", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.stoolRed, "dcs_climate", "dcs_stool_red", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.stoolSmall, "dcs_climate", "dcs_smallstool", "build", 7, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.stoolRattan, "dcs_climate", "dcs_stool_rattan", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chairWood, "dcs_climate", "dcs_chair_wood", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chairMarble, "dcs_climate", "dcs_chair_marble", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chairSkarn, "dcs_climate", "dcs_chair_skarn", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chairGreisen, "dcs_climate", "dcs_chair_greisen", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chairChecker, "dcs_climate", "dcs_chair_checker", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chairBlack, "dcs_climate", "dcs_chair_black", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chairRattan, "dcs_climate", "dcs_chair_rattan", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.lampCarbide, "dcs_climate", "dcs_lamp_carbide_lantern", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.lampGas, "dcs_climate", "dcs_lamp_carbide_glass", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chestMarble, "dcs_climate", "dcs_device_lowchest_marble", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chestSkarn, "dcs_climate", "dcs_device_lowchest_skarn", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chestGreisen, "dcs_climate", "dcs_device_lowchest_greisen", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chestWood, "dcs_climate", "dcs_device_lowchest_wood", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chestChecker, "dcs_climate", "dcs_device_lowchest_checker", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chestBlack, "dcs_climate", "dcs_device_lowchest_black", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chestRattan, "dcs_climate", "dcs_device_lowchest_rattan", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.wallshelfMarble, "dcs_climate", "dcs_wallshelf_marble", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.wallshelfSkarn, "dcs_climate", "dcs_wallshelf_skarn", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.wallshelfGreisen, "dcs_climate", "dcs_wallshelf_greisen", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.wallshelfWood, "dcs_climate", "dcs_wallshelf_wood", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.wallshelfChecker, "dcs_climate", "dcs_wallshelf_checker", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.wallshelfBlack, "dcs_climate", "dcs_wallshelf_black", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.wallshelfRattan, "dcs_climate", "dcs_wallshelf_rattan", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.sinkMetal, "dcs_climate", "dcs_device_sink_half", "device", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.sinkChest, "dcs_climate", "dcs_device_sink_full", "device", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.craftingCounter, "dcs_climate", "dcs_device_crafting_counter", "device", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.kitchenHood, "dcs_climate", "dcs_device_kitchen_hood", "device", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chandelierGypsum, "dcs_climate", "dcs_build_chandelier", "build", 15, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.doorMarble, "dcs_climate", "dcs_door_marble", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.doorGreisen, "dcs_climate", "dcs_door_greisen", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.doorGypsum, "dcs_climate", "dcs_door_gypsum", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.doorSteel, "dcs_climate", "dcs_door_steel", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.doorHikido, "dcs_climate", "dcs_door_hikido", "build", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.curtainWhite, "dcs_climate", "dcs_build_curtain", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.curtainGray, "dcs_climate", "dcs_build_curtain_gray", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.curtainRed, "dcs_climate", "dcs_build_curtain_red", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.curtainBlue, "dcs_climate", "dcs_build_curtain_blue", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.windowBlinds, "dcs_climate", "dcs_build_curtain_blinds", "build", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.windowWood, "dcs_climate", "dcs_window_wood", "build", 1, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.displayShelf, "dcs_climate", "dcs_device_display_shelf", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.displayStand, "dcs_climate", "dcs_device_display_stand", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.displayCase, "dcs_climate", "dcs_device_display_case", "device", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.wallDecoration, "dcs_climate", "dcs_build_wall_decoration", "build", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.sinkMetal, "dcs_climate", "dcs_device_sink_half", "device", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.sinkChest, "dcs_climate", "dcs_device_sink_full", "device", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.craftingCounter, "dcs_climate", "dcs_device_crafting_counter", "device", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.kitchenHood, "dcs_climate", "dcs_device_kitchen_hood", "device", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.awningWood, "dcs_climate", "dcs_build_awning", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.awningMetal, "dcs_climate", "dcs_build_awning_metal", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.awningLinen, "dcs_climate", "dcs_build_awning_linen", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.awningCloth, "dcs_climate", "dcs_build_awning_cloth", "build", 0, false);
			MAIN_INSTANCE.regStateAndBlock(MainInit.lampCarbide, "dcs_climate", "dcs_lamp_carbide_lantern", "build", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chestMetal, "dcs_climate", "dcs_device_chest_metal", "device", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chestMagnet, "dcs_climate", "dcs_device_chest_magnet", "device", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chestVillage, "dcs_climate", "dcs_device_chest_village", "device", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.chestHopper, "dcs_climate", "dcs_device_hopper_chest", "device", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.realtimeClock, "dcs_climate", "dcs_device_realtimeclock", "device", 3, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.realtimeClock_L, "dcs_climate", "dcs_device_realtimeclock_l", "device", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.mcClock_L, "dcs_climate", "dcs_device_mcclock_l", "device", 0, true);
			MAIN_INSTANCE.regSimpleBlock(MainInit.flowerVase, "dcs_climate", "dcs_build_vase", "build", 4);
			MAIN_INSTANCE.regSimpleBlock(MainInit.planting, "dcs_climate", "dcs_build_flower_planting", "build", 3);
			MAIN_INSTANCE.regStateAndBlock(MainInit.bed, "dcs_climate", "dcs_bed", "build", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.bedWhite, "dcs_climate", "dcs_bed_white", "build", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.bedRattan, "dcs_climate", "dcs_bed_rattan", "build", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.bedFuton, "dcs_climate", "dcs_bed_futon", "build", 0, true);
			MAIN_INSTANCE.regStateAndBlock(MainInit.bedHammock, "dcs_climate", "dcs_bed_hammock", "build", 0, true);
			MAIN_INSTANCE.regSimpleItem(MainInit.itemBed, "dcs_climate", "dcs_bed_item", "build", 4);
			// hedge
			MAIN_INSTANCE.regSimpleBlock(MainInit.lampGas, "dcs_climate", "dcs_lamp_carbide_glass", "build", 0);
			MAIN_INSTANCE.regSimpleBlock(MainInit.hedgeSpring, "dcs_climate", "dcs_hedge_spring", "crop", 0);
			MAIN_INSTANCE.regSimpleBlock(MainInit.hedgeSummer, "dcs_climate", "dcs_hedge_summer", "crop", 0);
			MAIN_INSTANCE.regSimpleBlock(MainInit.hedgeAutumn, "dcs_climate", "dcs_hedge_autumn", "crop", 0);
			MAIN_INSTANCE.regSimpleBlock(MainInit.hedgeWinter, "dcs_climate", "dcs_hedge_winter", "crop", 0);
			ModelLoader.setCustomStateMapper(MainInit.hedgeSpring, new StateMapperBase() {
				final ModelResourceLocation flower = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_hedge_spring", "multipart");
				final ModelResourceLocation leaves = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_hedge_leaves_spring", "multipart");

				@Override
				protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
					if (state != null && DCState.getBool(state, DCState.FLAG)) {
						return flower;
					}
					return leaves;
				}
			});
			ModelLoader.setCustomStateMapper(MainInit.hedgeSummer, new StateMapperBase() {
				final ModelResourceLocation flower = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_hedge_summer", "multipart");
				final ModelResourceLocation leaves = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_hedge_leaves_summer", "multipart");

				@Override
				protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
					if (state != null && DCState.getBool(state, DCState.FLAG)) {
						return flower;
					}
					return leaves;
				}
			});
			ModelLoader.setCustomStateMapper(MainInit.hedgeAutumn, new StateMapperBase() {
				final ModelResourceLocation flower = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_hedge_autumn", "multipart");
				final ModelResourceLocation leaves = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_hedge_leaves_autumn", "multipart");

				@Override
				protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
					if (state != null && DCState.getBool(state, DCState.FLAG)) {
						return flower;
					}
					return leaves;
				}
			});
			ModelLoader.setCustomStateMapper(MainInit.hedgeWinter, new StateMapperBase() {
				final ModelResourceLocation flower = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_hedge_winter", "multipart");
				final ModelResourceLocation leaves = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_hedge_leaves_winter", "multipart");

				@Override
				protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
					if (state != null && DCState.getBool(state, DCState.FLAG)) {
						return flower;
					}
					return leaves;
				}
			});
		}
		if (!ModuleConfig.delete_old) {
			MAIN_INSTANCE.regSimpleBlock(MainInit.ores, "dcs_climate", "dcs_ore_stone", "ores", 15);
			MAIN_INSTANCE.regSimpleBlock(MainInit.ores_2, "dcs_climate", "dcs_ore2_stone", "ores", 12);
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
				MAIN_INSTANCE.regSimpleItem(MainInit.dcAxe[j], "dcs_climate", "dcs_axe_" + name[j], "equip", 0);
				MAIN_INSTANCE.regSimpleItem(MainInit.dcPickaxe[j], "dcs_climate", "dcs_pickaxe_" + name[j], "equip", 0);
				MAIN_INSTANCE.regSimpleItem(MainInit.dcSpade[j], "dcs_climate", "dcs_spade_" + name[j], "equip", 0);
				if (j < 9)
					MAIN_INSTANCE.regSimpleItem(MainInit.dcSword[j], "dcs_climate", "dcs_sword_" + name[j], "equip", 0);
			}
			String[] type = {
					"met",
					"plate",
					"leggins",
					"boots"
			};
			for (int i = 0; i < 4; i++) {
				MAIN_INSTANCE.regSimpleItem(MainInit.brassArmor[i], "dcs_climate", "dcs_" + type[i] + "_brass", "equip", 0);
				MAIN_INSTANCE.regSimpleItem(MainInit.steelArmor[i], "dcs_climate", "dcs_" + type[i] + "_steel", "equip", 0);
				MAIN_INSTANCE.regSimpleItem(MainInit.chalcArmor[i], "dcs_climate", "dcs_" + type[i] + "_chalcedony", "equip", 0);
				MAIN_INSTANCE.regSimpleItem(MainInit.sapphireArmor[i], "dcs_climate", "dcs_" + type[i] + "_sapphire", "equip", 0);
				MAIN_INSTANCE.regSimpleItem(MainInit.titaniumArmor[i], "dcs_climate", "dcs_" + type[i] + "_titanium", "equip", 0);
				MAIN_INSTANCE.regSimpleItem(MainInit.silverArmor[i], "dcs_climate", "dcs_" + type[i] + "_silver", "equip", 0);
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
				MAIN_INSTANCE.regSimpleItem(MainInit.dcScythe[j], "dcs_climate", "dcs_scythe_" + name[j], "equip", 0);
			}
			MAIN_INSTANCE.regSimpleItem(MainInit.earthSpade, "dcs_climate", "dcs_spade_earth", "equip", 0);
			MAIN_INSTANCE.regSimpleItem(MainInit.earthRake, "dcs_climate", "dcs_rake_earth", "equip", 0);
			MAIN_INSTANCE.regSimpleItem(MainInit.toolsteelRake, "dcs_climate", "dcs_rake_toolsteel", "equip", 0);
			MAIN_INSTANCE.regSimpleItem(MainInit.shieldSynthetic, "dcs_climate", "dcs_shield_synthetic", "equip", 0);
			MAIN_INSTANCE.regSimpleItem(MainInit.shieldTitanium, "dcs_climate", "dcs_shield_titanium", "equip", 0);
			MAIN_INSTANCE.regSimpleItem(MainInit.handNet, "dcs_climate", "dcs_handnet", "equip", 0);
		}
		MAIN_INSTANCE.regSimpleItem(MainInit.crossbow, "dcs_climate", "dcs_crossbow_iron", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.gun, "dcs_climate", "dcs_musket_steel", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.cartridge, "dcs_climate", "dcs_cartridge", "equip", 7);
		MAIN_INSTANCE.regSimpleItem(MainInit.throwingArrow, "dcs_climate", "dcs_throwing_arrow", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.linenUnder, "dcs_climate", "dcs_leggins_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.linenCoat, "dcs_climate", "dcs_plate_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothUnder, "dcs_climate", "dcs_leggins_cloth", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.workerSuit, "dcs_climate", "dcs_leggins_worker", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.blackSuit, "dcs_climate", "dcs_leggins_black", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.trackSuit, "dcs_climate", "dcs_leggins_tracksuit", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.combatDress, "dcs_climate", "dcs_leggins_combatdress", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.hoodie, "dcs_climate", "dcs_plate_hoodie", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.leatherHat, "dcs_climate", "dcs_hat_leather", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.cottonHat, "dcs_climate", "dcs_hat_cotton", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.woolWear, "dcs_climate", "dcs_wear_wool", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.furWear, "dcs_climate", "dcs_wear_fue", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.woolBoots, "dcs_climate", "dcs_boots_wool", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.peaCoat, "dcs_climate", "dcs_plate_peacoat", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.blackCoat, "dcs_climate", "dcs_plate_silk", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothCoat, "dcs_climate", "dcs_plate_cloth", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.magicUnder, "dcs_climate", "dcs_leggins_magic", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.magicCoat, "dcs_climate", "dcs_plate_magic", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.linenJacket, "dcs_climate", "dcs_plate_jacket_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothJacket, "dcs_climate", "dcs_plate_jacket_cloth", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.woolJacket, "dcs_climate", "dcs_plate_jacket_wool", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.modsCoat, "dcs_climate", "dcs_plate_mods", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.linenShirt, "dcs_climate", "dcs_leggins_shirt_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothShirt, "dcs_climate", "dcs_leggins_shirt_cloth", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.linenBottom, "dcs_climate", "dcs_boots_pants_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothBottom, "dcs_climate", "dcs_boots_pants_cloth", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.flowerSkirt, "dcs_climate", "dcs_boots_skirt_linen", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.clothSkirt, "dcs_climate", "dcs_boots_skirt_cloth", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.silkSkirt, "dcs_climate", "dcs_boots_skirt_silk", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.silkDress, "dcs_climate", "dcs_leggins_dress_silk", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.silkCape, "dcs_climate", "dcs_met_cape_silk", "equip", 0);
		MAIN_INSTANCE.regSimpleItem(MainInit.furCape, "dcs_climate", "dcs_met_cape_fur", "equip", 0);
	}

	static void regFluids() {
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.oilBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel_Oil = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_oil", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel_Oil;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.oilBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel_Oil = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_oil", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel_Oil;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.greenTeaBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_greentea", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.greenTeaBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_greentea", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.blackTeaBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_blacktea", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.blackTeaBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_blacktea", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.coffeeBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_coffee", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.coffeeBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_coffee", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.creamBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_cream", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.creamBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_cream", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.tomatoBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_vegetable", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.tomatoBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_vegetable", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.blackLiquorBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_black_liquor", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.blackLiquorBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_black_liquor", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.hotSpringBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_hotspring", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.hotSpringBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_hotspring", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.stockBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_stock", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.stockBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_stock", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.lemonBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_lemonade", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.lemonBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_lemonade", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.mazaiBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_mazai", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.mazaiBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_mazai", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.soyMilkBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_soy_milk", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.soyMilkBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_soy_milk", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.hydrogenBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_hydrogen", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.hydrogenBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_hydrogen", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.ammoniaBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_ammonia", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.ammoniaBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_ammonia", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.fuelGasBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_gas", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.fuelGasBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_gas", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.fuelOilBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_oil", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.fuelOilBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_oil", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.nitricAcidBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_nitric_acid", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.nitricAcidBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_nitric_acid", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.sulfuricAcidBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_sulfuric_acid", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.sulfuricAcidBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_sulfuric_acid", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.nitrogenBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_nitrogen", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.nitrogenBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_nitrogen", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.ethanolBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_ethanol", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.ethanolBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_ethanol", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.milkBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_raw_milk", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.milkBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_raw_milk", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.steamBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_steam", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.steamBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_steam", "fluid");

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(MainInit.oxygenBlock), new ItemMeshDefinition() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_oxygen", "fluid");

			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return fluidModel;
			}
		});
		ModelLoader.setCustomStateMapper(MainInit.oxygenBlock, new StateMapperBase() {
			final ModelResourceLocation fluidModel = new ModelResourceLocation(ClimateMain.MOD_ID + ":" + ClimateCore.PACKAGE_BASE + "_fluidblock_oxygen", "fluid");

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
