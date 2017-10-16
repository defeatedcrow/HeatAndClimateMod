package defeatedcrow.hac.main;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.DCMaterial;
import defeatedcrow.hac.core.base.DCItemBlock;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInitRegister;
import defeatedcrow.hac.machine.MachineInitRegister;
import defeatedcrow.hac.machine.block.ItemBlockHighTier;
import defeatedcrow.hac.magic.MagicInitRegister;
import defeatedcrow.hac.main.block.build.BlockAwning;
import defeatedcrow.hac.main.block.build.BlockBuilding;
import defeatedcrow.hac.main.block.build.BlockCarbideLamp;
import defeatedcrow.hac.main.block.build.BlockChalcedonyLamp;
import defeatedcrow.hac.main.block.build.BlockClayBricks;
import defeatedcrow.hac.main.block.build.BlockCoolantPackage;
import defeatedcrow.hac.main.block.build.BlockDesiccantPackage;
import defeatedcrow.hac.main.block.build.BlockFenceBase;
import defeatedcrow.hac.main.block.build.BlockGemBricks;
import defeatedcrow.hac.main.block.build.BlockGlassPlastic;
import defeatedcrow.hac.main.block.build.BlockGlassSelenite;
import defeatedcrow.hac.main.block.build.BlockIronPlate;
import defeatedcrow.hac.main.block.build.BlockLightOrb;
import defeatedcrow.hac.main.block.build.BlockLowChest;
import defeatedcrow.hac.main.block.build.BlockMagnetChest;
import defeatedcrow.hac.main.block.build.BlockMarkingPanel;
import defeatedcrow.hac.main.block.build.BlockMetalChest;
import defeatedcrow.hac.main.block.build.BlockMetalFenceBase;
import defeatedcrow.hac.main.block.build.BlockMetalLadder;
import defeatedcrow.hac.main.block.build.BlockMetalPillar;
import defeatedcrow.hac.main.block.build.BlockSlabChal;
import defeatedcrow.hac.main.block.build.BlockSlabDC;
import defeatedcrow.hac.main.block.build.BlockSofaBase;
import defeatedcrow.hac.main.block.build.BlockStairsBase;
import defeatedcrow.hac.main.block.build.BlockTableBase;
import defeatedcrow.hac.main.block.build.BlockVillageChest;
import defeatedcrow.hac.main.block.build.BlockWallLamp;
import defeatedcrow.hac.main.block.container.BlockCardboard;
import defeatedcrow.hac.main.block.container.BlockCropBasket;
import defeatedcrow.hac.main.block.container.BlockCropCont;
import defeatedcrow.hac.main.block.container.BlockDustBag;
import defeatedcrow.hac.main.block.container.BlockEnemyCont;
import defeatedcrow.hac.main.block.container.BlockLogCont;
import defeatedcrow.hac.main.block.container.BlockMiscCont;
import defeatedcrow.hac.main.block.device.BlockAcvShield;
import defeatedcrow.hac.main.block.device.BlockBellow;
import defeatedcrow.hac.main.block.device.BlockCookingStove;
import defeatedcrow.hac.main.block.device.BlockNormalChamber;
import defeatedcrow.hac.main.block.device.BlockShitirin;
import defeatedcrow.hac.main.block.device.BlockSink;
import defeatedcrow.hac.main.block.device.BlockStevensonScreen;
import defeatedcrow.hac.main.block.device.BlockThermometer;
import defeatedcrow.hac.main.block.device.BlockWindVane;
import defeatedcrow.hac.main.block.device.ItemBlockShield;
import defeatedcrow.hac.main.block.device.ItemBlockShitirin;
import defeatedcrow.hac.main.block.ores.BlockDusts;
import defeatedcrow.hac.main.block.ores.BlockGem;
import defeatedcrow.hac.main.block.ores.BlockMetal;
import defeatedcrow.hac.main.block.ores.BlockOres;
import defeatedcrow.hac.main.block.ores.BlockOres2;
import defeatedcrow.hac.main.block.plant.BlockHedge;
import defeatedcrow.hac.main.item.entity.ItemFlowerPot;
import defeatedcrow.hac.main.item.equip.ItemArmorDC;
import defeatedcrow.hac.main.item.equip.ItemArmorHat;
import defeatedcrow.hac.main.item.equip.ItemArmorHoodie;
import defeatedcrow.hac.main.item.equip.ItemArmorThinDC;
import defeatedcrow.hac.main.item.food.ItemDCFoods;
import defeatedcrow.hac.main.item.food.ItemFoodMaterials;
import defeatedcrow.hac.main.item.misc.ItemMiscs;
import defeatedcrow.hac.main.item.misc.ItemRepairPutty;
import defeatedcrow.hac.main.item.ores.ItemCircuitChal;
import defeatedcrow.hac.main.item.ores.ItemGems;
import defeatedcrow.hac.main.item.ores.ItemIngots;
import defeatedcrow.hac.main.item.ores.ItemMetalMaterials;
import defeatedcrow.hac.main.item.ores.ItemMiscDust;
import defeatedcrow.hac.main.item.ores.ItemOreDusts;
import defeatedcrow.hac.main.item.tool.ItemAlloyYagen;
import defeatedcrow.hac.main.item.tool.ItemAxeDC;
import defeatedcrow.hac.main.item.tool.ItemBullets;
import defeatedcrow.hac.main.item.tool.ItemCrossbowDC;
import defeatedcrow.hac.main.item.tool.ItemCrowDrill;
import defeatedcrow.hac.main.item.tool.ItemEntityScope;
import defeatedcrow.hac.main.item.tool.ItemMusketDC;
import defeatedcrow.hac.main.item.tool.ItemPickaxeDC;
import defeatedcrow.hac.main.item.tool.ItemScytheDC;
import defeatedcrow.hac.main.item.tool.ItemSpadeDC;
import defeatedcrow.hac.main.item.tool.ItemStoneYagen;
import defeatedcrow.hac.main.item.tool.ItemSwordDC;
import defeatedcrow.hac.main.item.tool.ItemThermalScope;
import defeatedcrow.hac.main.item.tool.ItemWrench;
import defeatedcrow.hac.main.util.DCArmorMaterial;
import defeatedcrow.hac.main.util.DCMaterialEnum;
import defeatedcrow.hac.main.util.DCToolMaterial;
import defeatedcrow.hac.plugin.DCIntegrationCore;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class MainMaterialRegister {
	private MainMaterialRegister() {}

	public static void load() {

		registerBlocks();
		regJsonBlock();
		regDeviceBlock();
		registerSidedBlock();
		registerItems();
		registerMaterialEnum();
		registerEquip();
		registerFood();
		registerHarvestLevel();
		registerIntegration();

		FoodInitRegister.load();
		MachineInitRegister.load();
		MagicInitRegister.load();
	}

	static void registerBlocks() {
		MainInit.ores = new BlockOres(Material.IRON, ClimateCore.PACKAGE_BASE + "_ore_stone", 15);
		DCMaterial.registerBlock(MainInit.ores, ClimateCore.PACKAGE_BASE + "_ore_stone", ClimateMain.MOD_ID);

		MainInit.ores_2 = new BlockOres2(Material.IRON, ClimateCore.PACKAGE_BASE + "_ore2_stone", 12);
		DCMaterial.registerBlock(MainInit.ores_2, ClimateCore.PACKAGE_BASE + "_ore2_stone", ClimateMain.MOD_ID);

		MainInit.metalBlock = new BlockMetal(Material.IRON, ClimateCore.PACKAGE_BASE + "_metal", 15);
		DCMaterial.registerBlock(MainInit.metalBlock, ClimateCore.PACKAGE_BASE + "_ore_metalblock", ClimateMain.MOD_ID);

		MainInit.dustBlock = new BlockDusts(Material.GROUND, ClimateCore.PACKAGE_BASE + "_dustblock", 15);
		DCMaterial.registerBlock(MainInit.dustBlock, ClimateCore.PACKAGE_BASE + "_ore_dustblock", ClimateMain.MOD_ID);

		MainInit.gemBlock = new BlockGem(Material.ROCK, ClimateCore.PACKAGE_BASE + "_gemblock", 12);
		DCMaterial.registerBlock(MainInit.gemBlock, ClimateCore.PACKAGE_BASE + "_ore_gemblock", ClimateMain.MOD_ID);

		MainInit.bricks = new BlockGemBricks(Material.ROCK, ClimateCore.PACKAGE_BASE + "_build_bricks");
		DCMaterial.registerBlock(MainInit.bricks, ClimateCore.PACKAGE_BASE + "_build_bricks", ClimateMain.MOD_ID);

		MainInit.builds = new BlockBuilding(Material.ROCK, ClimateCore.PACKAGE_BASE + "_build_build");
		DCMaterial.registerBlock(MainInit.builds, ClimateCore.PACKAGE_BASE + "_build_build", ClimateMain.MOD_ID);

		MainInit.selenite = new BlockGlassSelenite(ClimateCore.PACKAGE_BASE + "_build_selenite", 2);
		DCMaterial.registerBlock(MainInit.selenite, ClimateCore.PACKAGE_BASE + "_build_selenite", ClimateMain.MOD_ID);

		MainInit.chalLamp = new BlockChalcedonyLamp(ClimateCore.PACKAGE_BASE + "_build_challamp", 15);
		DCMaterial.registerBlock(MainInit.chalLamp, ClimateCore.PACKAGE_BASE + "_build_challamp", ClimateMain.MOD_ID);

		MainInit.wallLamp = new BlockWallLamp(ClimateCore.PACKAGE_BASE + "_build_walllamp");
		DCMaterial.registerBlock(MainInit.wallLamp, ClimateCore.PACKAGE_BASE + "_build_walllamp", ClimateMain.MOD_ID);

		MainInit.markingPanel = new BlockMarkingPanel(ClimateCore.PACKAGE_BASE + "_build_markingpanel");
		DCMaterial.registerBlock(MainInit.markingPanel, ClimateCore.PACKAGE_BASE + "_build_markingpanel",
				ClimateMain.MOD_ID);

		MainInit.lightOrb = new BlockLightOrb(ClimateCore.PACKAGE_BASE + "_build_lightorb");
		DCMaterial.registerBlock(MainInit.lightOrb, ClimateCore.PACKAGE_BASE + "_build_lightorb", ClimateMain.MOD_ID);

		MainInit.syntheticBlock = new BlockGlassPlastic(ClimateCore.PACKAGE_BASE + "_build_synthetic_glass", 15);
		DCMaterial.registerBlock(MainInit.syntheticBlock, ClimateCore.PACKAGE_BASE + "_build_synthetic_glass",
				ClimateMain.MOD_ID);

		MainInit.desiccant = new BlockDesiccantPackage(ClimateCore.PACKAGE_BASE + "_desiccant");
		DCMaterial.registerBlock(MainInit.desiccant, ClimateCore.PACKAGE_BASE + "_desiccant", ClimateMain.MOD_ID);

		MainInit.freezepack = new BlockCoolantPackage(ClimateCore.PACKAGE_BASE + "_coolant");
		DCMaterial.registerBlock(MainInit.freezepack, ClimateCore.PACKAGE_BASE + "_coolant", ClimateMain.MOD_ID);
	}

	static void registerSidedBlock() {
		MainInit.logCont = new BlockLogCont(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_log", 6);
		DCMaterial.registerBlock(MainInit.logCont, ClimateCore.PACKAGE_BASE + "_cont_log", ClimateMain.MOD_ID);
		ClimateMain.proxy.addSidedBlock(MainInit.logCont, "cont_log", 6);

		MainInit.cropCont = new BlockCropCont(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_crop", 10);
		DCMaterial.registerBlock(MainInit.cropCont, ClimateCore.PACKAGE_BASE + "_cont_crop", ClimateMain.MOD_ID);
		ClimateMain.proxy.addTBBlock(MainInit.cropCont, "cont_crop", 10);

		MainInit.dropCont = new BlockEnemyCont(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_metal", 4);
		DCMaterial.registerBlock(MainInit.dropCont, ClimateCore.PACKAGE_BASE + "_cont_metal", ClimateMain.MOD_ID);
		ClimateMain.proxy.addTBBlock(MainInit.dropCont, "cont_metal", 4);

		MainInit.miscCont = new BlockMiscCont(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_misc", 3);
		DCMaterial.registerBlock(MainInit.miscCont, ClimateCore.PACKAGE_BASE + "_cont_misc", ClimateMain.MOD_ID);
		ClimateMain.proxy.addTBBlock(MainInit.miscCont, "cont_misc", 3);

		MainInit.cardboard = new BlockCardboard(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_cardboard", 5);
		DCMaterial.registerBlock(MainInit.cardboard, ClimateCore.PACKAGE_BASE + "_cont_cardboard", ClimateMain.MOD_ID);
		ClimateMain.proxy.addSidedBlock(MainInit.cardboard, "cont_cardboard", 5);

		MainInit.dustBags = new BlockDustBag(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_bags", 3);
		DCMaterial.registerBlock(MainInit.dustBags, ClimateCore.PACKAGE_BASE + "_cont_bags", ClimateMain.MOD_ID);
		ClimateMain.proxy.addSidedBlock(MainInit.dustBags, "cont_bags", 3);

		MainInit.cropBasket = new BlockCropBasket(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_basket", 8);
		DCMaterial.registerBlock(MainInit.cropBasket, ClimateCore.PACKAGE_BASE + "_cont_basket", ClimateMain.MOD_ID);
		ClimateMain.proxy.addTBBlock(MainInit.cropBasket, "cont_basket", 8);
	}

	static void regJsonBlock() {
		MainInit.stairsGlass = new BlockStairsBase(MainInit.selenite.getDefaultState(), "build/glass_stairs", true)
				.setUnlocalizedName("dcs_stairs_glass");
		DCMaterial.registerBlock(MainInit.stairsGlass, ClimateCore.PACKAGE_BASE + "_stairs_glass", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stairsGlass), "dcs_climate", "dcs_stairs_glass",
				"build", 15, false);

		MainInit.stairsGypsum = new BlockStairsBase(MainInit.ores.getDefaultState(), "ores/gemblock_gypsum", false)
				.setUnlocalizedName("dcs_stairs_gypsum");
		DCMaterial.registerBlock(MainInit.stairsGypsum, ClimateCore.PACKAGE_BASE + "_stairs_gypsum",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stairsGypsum), "dcs_climate", "dcs_stairs_gypsum",
				"build", 15, false);

		MainInit.stairsMarble = new BlockStairsBase(MainInit.ores.getDefaultState(), "ores/gemblock_marble", false)
				.setUnlocalizedName("dcs_stairs_marble");
		DCMaterial.registerBlock(MainInit.stairsMarble, ClimateCore.PACKAGE_BASE + "_stairs_marble",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stairsMarble), "dcs_climate", "dcs_stairs_marble",
				"build", 15, false);

		MainInit.stairsSerpentine = new BlockStairsBase(MainInit.ores.getDefaultState(), "ores/gemblock_serpentine",
				false).setUnlocalizedName("dcs_stairs_serpentine");
		DCMaterial.registerBlock(MainInit.stairsSerpentine, ClimateCore.PACKAGE_BASE + "_stairs_serpentine",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stairsSerpentine), "dcs_climate",
				"dcs_stairs_serpentine", "build", 15, false);

		MainInit.stairsBedrock = new BlockStairsBase(MainInit.ores.getDefaultState(), "ores/gemblock_bedrock", false)
				.setUnlocalizedName("dcs_stairs_bedrock");
		DCMaterial.registerBlock(MainInit.stairsBedrock, ClimateCore.PACKAGE_BASE + "_stairs_bedrock",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stairsBedrock), "dcs_climate",
				"dcs_stairs_bedrock", "build", 15, false);

		MainInit.halfSlab = new BlockSlabDC();
		DCMaterial.registerBlock(MainInit.halfSlab, ClimateCore.PACKAGE_BASE + "_build_slab", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.halfSlab), "dcs_climate", "dcs_build_slab",
				"build", 4, true);

		MainInit.halfSlab2 = new BlockSlabChal();
		DCMaterial.registerBlock(MainInit.halfSlab2, ClimateCore.PACKAGE_BASE + "_build_slab_chal", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.halfSlab2), "dcs_climate", "dcs_build_slab_chal",
				"build", 6, true);

		MainInit.fenceGypsum = new BlockFenceBase("dcs_fence_gypsum").setUnlocalizedName("dcs_fence_gypsum");
		DCMaterial.registerBlock(MainInit.fenceGypsum, ClimateCore.PACKAGE_BASE + "_fence_gypsum", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.fenceGypsum), "dcs_climate", "dcs_fence_gypsum",
				"build", 15, false);

		MainInit.fenceMarble = new BlockFenceBase("dcs_fence_marble").setUnlocalizedName("dcs_fence_marble");
		DCMaterial.registerBlock(MainInit.fenceMarble, ClimateCore.PACKAGE_BASE + "_fence_marble", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.fenceMarble), "dcs_climate", "dcs_fence_marble",
				"build", 15, false);

		MainInit.fenceSerpentine = new BlockFenceBase("dcs_fence_serpentine")
				.setUnlocalizedName("dcs_fence_serpentine");
		DCMaterial.registerBlock(MainInit.fenceSerpentine, ClimateCore.PACKAGE_BASE + "_fence_serpentine",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.fenceSerpentine), "dcs_climate",
				"dcs_fence_serpentine", "build", 15, false);

		MainInit.fenceBedrock = new BlockFenceBase("dcs_fence_bedrock").setUnlocalizedName("dcs_fence_bedrock");
		DCMaterial.registerBlock(MainInit.fenceBedrock, ClimateCore.PACKAGE_BASE + "_fence_bedrock",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.fenceBedrock), "dcs_climate", "dcs_fence_bedrock",
				"build", 15, false);

		MainInit.pillarSteel = new BlockMetalPillar("dcs_pillar_steel").setUnlocalizedName("dcs_pillar_steel");
		DCMaterial.registerBlock(MainInit.pillarSteel, ClimateCore.PACKAGE_BASE + "_pillar_steel", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.pillarSteel), "dcs_climate", "dcs_pillar_steel",
				"build", 15, false);

		MainInit.fenceSteel = new BlockFenceBase("dcs_fence_steel").setUnlocalizedName("dcs_fence_steel");
		DCMaterial.registerBlock(MainInit.fenceSteel, ClimateCore.PACKAGE_BASE + "_fence_steel", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.fenceSteel), "dcs_climate", "dcs_fence_steel",
				"build", 15, false);

		MainInit.fenceNetSteel = new BlockMetalFenceBase("dcs_fence_net_steel", false)
				.setUnlocalizedName("dcs_fence_net_steel");
		DCMaterial.registerBlock(MainInit.fenceNetSteel, ClimateCore.PACKAGE_BASE + "_fence_net_steel",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.fenceNetSteel), "dcs_climate",
				"dcs_fence_net_steel", "build", 3, false);

		MainInit.fenceLadderSteel = new BlockMetalLadder("dcs_fence_ladder_steel")
				.setUnlocalizedName("dcs_fence_ladder_steel");
		DCMaterial.registerBlock(MainInit.fenceLadderSteel, ClimateCore.PACKAGE_BASE + "_fence_ladder_steel",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.fenceLadderSteel), "dcs_climate",
				"dcs_fence_ladder_steel", "build", 3, false);

		MainInit.fenceAluminium = new BlockMetalFenceBase("dcs_fence_aluminium", false)
				.setUnlocalizedName("dcs_fence_aluminium");
		DCMaterial.registerBlock(MainInit.fenceAluminium, ClimateCore.PACKAGE_BASE + "_fence_aluminium",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.fenceAluminium), "dcs_climate",
				"dcs_fence_aluminium", "build", 3, false);

		MainInit.fenceNet = new BlockMetalFenceBase("dcs_fence_net", false).setUnlocalizedName("dcs_fence_net");
		DCMaterial.registerBlock(MainInit.fenceNet, ClimateCore.PACKAGE_BASE + "_fence_net", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.fenceNet), "dcs_climate", "dcs_fence_net",
				"build", 3, false);

		MainInit.fenceGlass = new BlockMetalFenceBase("dcs_fence_glass", true).setUnlocalizedName("dcs_fence_glass");
		DCMaterial.registerBlock(MainInit.fenceGlass, ClimateCore.PACKAGE_BASE + "_fence_glass", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.fenceGlass), "dcs_climate", "dcs_fence_glass",
				"build", 3, false);

		MainInit.fenceLadder = new BlockMetalLadder("dcs_fence_ladder").setUnlocalizedName("dcs_fence_ladder");
		DCMaterial.registerBlock(MainInit.fenceLadder, ClimateCore.PACKAGE_BASE + "_fence_ladder", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.fenceLadder), "dcs_climate", "dcs_fence_ladder",
				"build", 3, false);

		MainInit.awning = new BlockAwning("dcs_build_awning").setUnlocalizedName("dcs_build_awning");
		DCMaterial.registerBlock(MainInit.awning, ClimateCore.PACKAGE_BASE + "_build_awning", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.awning), "dcs_climate", "dcs_build_awning",
				"build", 3, false);

		MainInit.tableMarble = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_table_marble", false);
		DCMaterial.registerBlock(MainInit.tableMarble, ClimateCore.PACKAGE_BASE + "_table_marble", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.tableMarble), "dcs_climate", "dcs_table_marble",
				"build", 0, false);

		MainInit.tableGypsum = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_table_gypsum", false);
		DCMaterial.registerBlock(MainInit.tableGypsum, ClimateCore.PACKAGE_BASE + "_table_gypsum", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.tableGypsum), "dcs_climate", "dcs_table_gypsum",
				"build", 0, false);

		MainInit.tableWood = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_table_wood", false);
		DCMaterial.registerBlock(MainInit.tableWood, ClimateCore.PACKAGE_BASE + "_table_wood", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.tableWood), "dcs_climate", "dcs_table_wood",
				"build", 0, false);

		MainInit.tableDark = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_table_darkwood", false);
		DCMaterial.registerBlock(MainInit.tableDark, ClimateCore.PACKAGE_BASE + "_table_darkwood", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.tableDark), "dcs_climate", "dcs_table_darkwood",
				"build", 0, false);

		MainInit.carpetRed = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_carpet_red", true);
		DCMaterial.registerBlock(MainInit.carpetRed, ClimateCore.PACKAGE_BASE + "_carpet_red", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.carpetRed), "dcs_climate", "dcs_carpet_red",
				"build", 0, false);

		MainInit.carpetWhite = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_carpet_white", true);
		DCMaterial.registerBlock(MainInit.carpetWhite, ClimateCore.PACKAGE_BASE + "_carpet_white", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.carpetWhite), "dcs_climate", "dcs_carpet_white",
				"build", 0, false);

		MainInit.carpetGray = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_carpet_gray", true);
		DCMaterial.registerBlock(MainInit.carpetGray, ClimateCore.PACKAGE_BASE + "_carpet_gray", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.carpetGray), "dcs_climate", "dcs_carpet_gray",
				"build", 0, false);

		MainInit.sofaBlack = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_sofa_black");
		DCMaterial.registerBlock(MainInit.sofaBlack, ClimateCore.PACKAGE_BASE + "_sofa_black", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.sofaBlack), "dcs_climate", "dcs_sofa_black",
				"build", 0, false);

		MainInit.sofaRed = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_sofa_red");
		DCMaterial.registerBlock(MainInit.sofaRed, ClimateCore.PACKAGE_BASE + "_sofa_red", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.sofaRed), "dcs_climate", "dcs_sofa_red", "build",
				0, false);

		MainInit.stoolBlack = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_stool_black").setSmallAABB();
		DCMaterial.registerBlock(MainInit.stoolBlack, ClimateCore.PACKAGE_BASE + "_stool_black", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stoolBlack), "dcs_climate", "dcs_stool_black",
				"build", 0, false);

		MainInit.stoolRed = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_stool_red").setSmallAABB();
		DCMaterial.registerBlock(MainInit.stoolRed, ClimateCore.PACKAGE_BASE + "_stool_red", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stoolRed), "dcs_climate", "dcs_stool_red",
				"build", 0, false);

		MainInit.plate = new BlockIronPlate(ClimateCore.PACKAGE_BASE + "_build_plate", 1);
		DCMaterial.registerBlock(MainInit.plate, ClimateCore.PACKAGE_BASE + "_build_plate", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.plate), "dcs_climate", "dcs_build_plate", "build",
				1, false);

		MainInit.lampCarbide = new BlockCarbideLamp(ClimateCore.PACKAGE_BASE + "_lamp_carbide_lantern");
		DCMaterial.registerBlock(MainInit.lampCarbide, ClimateCore.PACKAGE_BASE + "_lamp_carbide_lantern",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.lampCarbide), "dcs_climate",
				"dcs_lamp_carbide_lantern", "build", 0, false);

		MainInit.lampGas = new BlockCarbideLamp(ClimateCore.PACKAGE_BASE + "_lamp_carbide_glass");
		DCMaterial.registerBlock(MainInit.lampGas, ClimateCore.PACKAGE_BASE + "_lamp_carbide_glass",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.lampGas), "dcs_climate", "dcs_lamp_carbide_glass",
				"build", 0, false);

		MainInit.chestMarble = new BlockLowChest(Material.ROCK, ClimateCore.PACKAGE_BASE + "_device_lowchest_marble",
				true);
		DCMaterial.registerBlock(MainInit.chestMarble, ClimateCore.PACKAGE_BASE + "_device_lowchest_marble",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regTEJson(MainInit.chestMarble, "dcs_climate", "dcs_device_lowchest_marble", "device");

		MainInit.chestWood = new BlockLowChest(Material.ROCK, ClimateCore.PACKAGE_BASE + "_device_lowchest_wood", true);
		DCMaterial.registerBlock(MainInit.chestWood, ClimateCore.PACKAGE_BASE + "_device_lowchest_wood",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regTEJson(MainInit.chestWood, "dcs_climate", "dcs_device_lowchest_wood", "device");

		MainInit.sinkMetal = new BlockSink(ClimateCore.PACKAGE_BASE + "_device_sink_half", false);
		DCMaterial.registerBlock(MainInit.sinkMetal, ClimateCore.PACKAGE_BASE + "_device_sink_half",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regTEJson(MainInit.sinkMetal, "dcs_climate", "dcs_device_sink_half", "device");

		MainInit.sinkChest = new BlockSink(ClimateCore.PACKAGE_BASE + "_device_sink_full", true);
		DCMaterial.registerBlock(MainInit.sinkChest, ClimateCore.PACKAGE_BASE + "_device_sink_full",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regTEJson(MainInit.sinkChest, "dcs_climate", "dcs_device_sink_full", "device");

		MainInit.clayBricks = new BlockClayBricks(ClimateCore.PACKAGE_BASE + "_build_claybrick");
		DCMaterial.registerBlock(MainInit.clayBricks, ClimateCore.PACKAGE_BASE + "_build_claybrick",
				ClimateMain.MOD_ID);
		ClimateMain.proxy.regTEJson(MainInit.clayBricks, "dcs_climate", "dcs_build_claybrick", "build");

		MainInit.hedgeSpring = new BlockHedge("dcs_hedge_spring").setUnlocalizedName("dcs_hedge_spring");
		DCMaterial.registerBlock(MainInit.hedgeSpring, ClimateCore.PACKAGE_BASE + "_hedge_spring", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.hedgeSpring), "dcs_climate", "dcs_hedge_spring",
				"crop", 15, false);

		MainInit.hedgeSummer = new BlockHedge("dcs_hedge_summer").setUnlocalizedName("dcs_hedge_summer");
		DCMaterial.registerBlock(MainInit.hedgeSummer, ClimateCore.PACKAGE_BASE + "_hedge_summer", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.hedgeSummer), "dcs_climate", "dcs_hedge_summer",
				"crop", 15, false);

		MainInit.hedgeAutumn = new BlockHedge("dcs_hedge_autumn").setUnlocalizedName("dcs_hedge_autumn");
		DCMaterial.registerBlock(MainInit.hedgeAutumn, ClimateCore.PACKAGE_BASE + "_hedge_autumn", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.hedgeAutumn), "dcs_climate", "dcs_hedge_autumn",
				"crop", 15, false);

		MainInit.hedgeWinter = new BlockHedge("dcs_hedge_winter").setUnlocalizedName("dcs_hedge_winter");
		DCMaterial.registerBlock(MainInit.hedgeWinter, ClimateCore.PACKAGE_BASE + "_hedge_winter", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.hedgeWinter), "dcs_climate", "dcs_hedge_winter",
				"crop", 15, false);
	}

	static void regDeviceBlock() {
		MainInit.chamber = new BlockNormalChamber(Material.IRON, ClimateCore.PACKAGE_BASE + "_device_chamber", 1);
		MainInit.chamber.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_chamber");
		ForgeRegistries.BLOCKS.register(MainInit.chamber);
		ForgeRegistries.ITEMS.register(new DCItemBlock(MainInit.chamber) {
			@Override
			public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip,
					ITooltipFlag flag) {
				tooltip.add(TextFormatting.BOLD.toString() + "Tier 1");
			}
		});

		MainInit.shitirin = new BlockShitirin(Material.CLAY, ClimateCore.PACKAGE_BASE + "_device_shitirin", 1);
		MainInit.shitirin.setRegistryName(ClimateCore.PACKAGE_BASE + "_device_shitirin");
		ForgeRegistries.BLOCKS.register(MainInit.shitirin);
		ForgeRegistries.ITEMS.register(new ItemBlockShitirin(MainInit.shitirin));

		MainInit.fuelStove = new BlockCookingStove(Material.IRON, ClimateCore.PACKAGE_BASE + "_device_fuelstove", 3);
		MainInit.fuelStove.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_fuelstove");
		ForgeRegistries.BLOCKS.register(MainInit.fuelStove);
		ForgeRegistries.ITEMS.register(new DCItemBlock(MainInit.fuelStove) {
			@Override
			public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip,
					ITooltipFlag flag) {
				tooltip.add(TextFormatting.BOLD.toString() + "Tier 2");
			}
		});

		MainInit.thermometer = new BlockThermometer(ClimateCore.PACKAGE_BASE + "_device_thermometer");
		DCMaterial.registerBlock(MainInit.thermometer, ClimateCore.PACKAGE_BASE + "_device_thermometer",
				ClimateMain.MOD_ID);

		MainInit.windvane = new BlockWindVane(ClimateCore.PACKAGE_BASE + "_device_windvane");
		DCMaterial.registerBlock(MainInit.windvane, ClimateCore.PACKAGE_BASE + "_device_windvane", ClimateMain.MOD_ID);

		MainInit.stevenson_screen = new BlockStevensonScreen(ClimateCore.PACKAGE_BASE + "_device_stevenson_screen");
		DCMaterial.registerBlock(MainInit.stevenson_screen, ClimateCore.PACKAGE_BASE + "_device_stevenson_screen",
				ClimateMain.MOD_ID);

		MainInit.chestMetal = new BlockMetalChest(Material.IRON, ClimateCore.PACKAGE_BASE + "_device_chest_metal");
		DCMaterial.registerBlock(MainInit.chestMetal, ClimateCore.PACKAGE_BASE + "_device_chest_metal",
				ClimateMain.MOD_ID);

		MainInit.chestMagnet = new BlockMagnetChest(Material.IRON, ClimateCore.PACKAGE_BASE + "_device_chest_magnet");
		DCMaterial.registerBlock(MainInit.chestMagnet, ClimateCore.PACKAGE_BASE + "_device_chest_magnet",
				ClimateMain.MOD_ID);

		MainInit.chestVillage = new BlockVillageChest(Material.IRON,
				ClimateCore.PACKAGE_BASE + "_device_chest_village");
		DCMaterial.registerBlock(MainInit.chestVillage, ClimateCore.PACKAGE_BASE + "_device_chest_village",
				ClimateMain.MOD_ID);

		MainInit.bellow = new BlockBellow(ClimateCore.PACKAGE_BASE + "_device_bellow");
		MainInit.bellow.setRegistryName(ClimateCore.PACKAGE_BASE + "_device_bellow");
		ForgeRegistries.BLOCKS.register(MainInit.bellow);
		ForgeRegistries.ITEMS.register(new ItemBlockHighTier(MainInit.bellow, 1));

		MainInit.achievementShield = new BlockAcvShield(Material.GROUND, "dcs_build_shield", 2)
				.setUnlocalizedName("dcs_build_shield");
		MainInit.achievementShield.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_build_shield");
		ForgeRegistries.BLOCKS.register(MainInit.achievementShield);
		ForgeRegistries.ITEMS.register(new ItemBlockShield(MainInit.achievementShield));
	}

	static void registerItems() {
		// ores
		MainInit.oreIngot = new ItemIngots(15).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_ingot");
		DCMaterial.registerItem(MainInit.oreIngot, ClimateCore.PACKAGE_BASE + "_ingot", ClimateMain.MOD_ID);

		MainInit.oreDust = new ItemOreDusts(13).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_oredust");
		DCMaterial.registerItem(MainInit.oreDust, ClimateCore.PACKAGE_BASE + "_oredust", ClimateMain.MOD_ID);

		MainInit.gems = new ItemGems(18).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_gem");
		DCMaterial.registerItem(MainInit.gems, ClimateCore.PACKAGE_BASE + "_gem", ClimateMain.MOD_ID);

		MainInit.miscDust = new ItemMiscDust(10).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_miscdust");
		DCMaterial.registerItem(MainInit.miscDust, ClimateCore.PACKAGE_BASE + "_miscdust", ClimateMain.MOD_ID);

		// tools
		MainInit.materials = new ItemMiscs(7).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_material");
		DCMaterial.registerItem(MainInit.materials, ClimateCore.PACKAGE_BASE + "_material", ClimateMain.MOD_ID);

		MainInit.stoneYagen = new ItemStoneYagen().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_yagen_stone");
		DCMaterial.registerItem(MainInit.stoneYagen, ClimateCore.PACKAGE_BASE + "_yagen_stone", ClimateMain.MOD_ID);

		MainInit.brassYagen = new ItemAlloyYagen().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_yagen_brass");
		DCMaterial.registerItem(MainInit.brassYagen, ClimateCore.PACKAGE_BASE + "_yagen_brass", ClimateMain.MOD_ID);

		MainInit.crowDrill = new ItemCrowDrill().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_creative_drill");
		DCMaterial.registerItem(MainInit.crowDrill, ClimateCore.PACKAGE_BASE + "_creative_drill", ClimateMain.MOD_ID);

		MainInit.flowerPot = new ItemFlowerPot().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_flowerpot");
		DCMaterial.registerItem(MainInit.flowerPot, ClimateCore.PACKAGE_BASE + "_flowerpot", ClimateMain.MOD_ID);

		MainInit.scope = new ItemThermalScope().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_thermal_scope");
		DCMaterial.registerItem(MainInit.scope, ClimateCore.PACKAGE_BASE + "_thermal_scope", ClimateMain.MOD_ID);

		MainInit.entityScope = new ItemEntityScope().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_entity_scope");
		DCMaterial.registerItem(MainInit.entityScope, ClimateCore.PACKAGE_BASE + "_entity_scope", ClimateMain.MOD_ID);
	}

	static void registerFood() {
		MainInit.foodMaterials = new ItemFoodMaterials(2)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_materials");
		DCMaterial.registerItem(MainInit.foodMaterials, ClimateCore.PACKAGE_BASE + "_food_materials",
				ClimateMain.MOD_ID);

		MainInit.bakedApple = new ItemDCFoods(4, false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_itemfood");
		DCMaterial.registerItem(MainInit.bakedApple, ClimateCore.PACKAGE_BASE + "_food_itemfood", ClimateMain.MOD_ID);
	}

	static void registerEquip() {
		String[] name = {
				"brass", "steel", "silver", "nickelsilver", "chalcedony", "sapphire", "titanium"
		};
		for (int j = 0; j < 7; j++) {
			DCLogger.debugLog(j + "/" + DCToolMaterial.getToolMaterial(j).toString());
			MainInit.dcAxe[j] = new ItemAxeDC(DCToolMaterial.getToolMaterial(j), name[j])
					.setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_axe_" + name[j]);
			DCMaterial.registerItem(MainInit.dcAxe[j], ClimateCore.PACKAGE_BASE + "_axe_" + name[j],
					ClimateMain.MOD_ID);
		}

		for (int j = 0; j < 7; j++) {
			MainInit.dcPickaxe[j] = new ItemPickaxeDC(DCToolMaterial.getToolMaterial(j), name[j])
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_pickaxe_" + name[j]);
			DCMaterial.registerItem(MainInit.dcPickaxe[j], ClimateCore.PACKAGE_BASE + "_pickaxe_" + name[j],
					ClimateMain.MOD_ID);
		}

		for (int j = 0; j < 7; j++) {
			MainInit.dcSpade[j] = new ItemSpadeDC(DCToolMaterial.getToolMaterial(j), name[j])
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_spade_" + name[j]);
			DCMaterial.registerItem(MainInit.dcSpade[j], ClimateCore.PACKAGE_BASE + "_spade_" + name[j],
					ClimateMain.MOD_ID);
		}

		for (int j = 0; j < 4; j++) {
			MainInit.dcSword[j] = new ItemSwordDC(DCToolMaterial.getToolMaterial(j), name[j], false)
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_sword_" + name[j]);
			DCMaterial.registerItem(MainInit.dcSword[j], ClimateCore.PACKAGE_BASE + "_sword_" + name[j],
					ClimateMain.MOD_ID);
		}
		for (int j = 4; j < 6; j++) {
			MainInit.dcSword[j] = new ItemSwordDC(DCToolMaterial.getToolMaterial(j), name[j], true)
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_sword_" + name[j]);
			DCMaterial.registerItem(MainInit.dcSword[j], ClimateCore.PACKAGE_BASE + "_sword_" + name[j],
					ClimateMain.MOD_ID);
		}

		MainInit.dcSword[6] = new ItemSwordDC(DCToolMaterial.getToolMaterial(6), name[6], false)
				.setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_sword_" + name[6]);
		DCMaterial.registerItem(MainInit.dcSword[6], ClimateCore.PACKAGE_BASE + "_sword_" + name[6],
				ClimateMain.MOD_ID);

		MainInit.dcScythe[0] = new ItemScytheDC(DCToolMaterial.getToolMaterial(0), "brass")
				.setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_scythe_brass");
		DCMaterial.registerItem(MainInit.dcScythe[0], ClimateCore.PACKAGE_BASE + "_scythe_brass", ClimateMain.MOD_ID);

		MainInit.dcScythe[1] = new ItemScytheDC(DCToolMaterial.getToolMaterial(1), "steel")
				.setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_scythe_steel");
		DCMaterial.registerItem(MainInit.dcScythe[1], ClimateCore.PACKAGE_BASE + "_scythe_steel", ClimateMain.MOD_ID);

		MainInit.dcScythe[2] = new ItemScytheDC(DCToolMaterial.getToolMaterial(4), "chalcedony")
				.setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_scythe_chalcedony");
		DCMaterial.registerItem(MainInit.dcScythe[2], ClimateCore.PACKAGE_BASE + "_scythe_chalcedony",
				ClimateMain.MOD_ID);

		MainInit.crossbow = new ItemCrossbowDC().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_crossbow");
		DCMaterial.registerItem(MainInit.crossbow, ClimateCore.PACKAGE_BASE + "_crossbow", ClimateMain.MOD_ID);

		MainInit.gun = new ItemMusketDC().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_musket");
		DCMaterial.registerItem(MainInit.gun, ClimateCore.PACKAGE_BASE + "_musket", ClimateMain.MOD_ID);

		MainInit.cartridge = new ItemBullets(4).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_cartridge");
		DCMaterial.registerItem(MainInit.cartridge, ClimateCore.PACKAGE_BASE + "_cartridge", ClimateMain.MOD_ID);

		String[] type = {
				"met", "plate", "leggins", "boots"
		};
		for (int i = 0; i < 4; i++) {
			EntityEquipmentSlot slot = DCUtil.SLOTS[i];
			MainInit.brassArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_BRASS, DCMaterialEnum.BRASS, slot, "brass")
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_brass");
			DCMaterial.registerItem(MainInit.brassArmor[i], ClimateCore.PACKAGE_BASE + "_" + type[i] + "_brass",
					ClimateMain.MOD_ID);

			MainInit.steelArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_STEEL, DCMaterialEnum.STEEL, slot, "steel")
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_steel");
			DCMaterial.registerItem(MainInit.steelArmor[i], ClimateCore.PACKAGE_BASE + "_" + type[i] + "_steel",
					ClimateMain.MOD_ID);

			MainInit.chalcArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_CHALCEDONY, DCMaterialEnum.CHALCEDONY, slot,
					"chalcedony").setCreativeTab(ClimateMain.tool)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_chalcedony");
			DCMaterial.registerItem(MainInit.chalcArmor[i], ClimateCore.PACKAGE_BASE + "_" + type[i] + "_chalcedony",
					ClimateMain.MOD_ID);

			MainInit.sapphireArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_SAPPHIRE, DCMaterialEnum.SAPPHIRE, slot,
					"sapphire").setCreativeTab(ClimateMain.tool)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_sapphire");
			DCMaterial.registerItem(MainInit.sapphireArmor[i], ClimateCore.PACKAGE_BASE + "_" + type[i] + "_sapphire",
					ClimateMain.MOD_ID);

			MainInit.titaniumArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_TITANIUM, DCMaterialEnum.TITANIUM, slot,
					"titanium").setCreativeTab(ClimateMain.tool)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_titanium");
			DCMaterial.registerItem(MainInit.titaniumArmor[i], ClimateCore.PACKAGE_BASE + "_" + type[i] + "_titanium",
					ClimateMain.MOD_ID);
		}

		MainInit.linenUnder = new ItemArmorThinDC(DCArmorMaterial.DC_LINEN, DCMaterialEnum.LINEN,
				EntityEquipmentSlot.LEGS, "linen").setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_linen");
		DCMaterial.registerItem(MainInit.linenUnder, ClimateCore.PACKAGE_BASE + "_leggins_linen", ClimateMain.MOD_ID);

		MainInit.linenCourt = new ItemArmorThinDC(DCArmorMaterial.DC_LINEN, DCMaterialEnum.LINEN,
				EntityEquipmentSlot.CHEST, "linen").setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_coat_linen");
		DCMaterial.registerItem(MainInit.linenCourt, ClimateCore.PACKAGE_BASE + "_coat_linen", ClimateMain.MOD_ID);

		MainInit.clothUnder = new ItemArmorThinDC(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH,
				EntityEquipmentSlot.LEGS, "cloth").setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_cloth");
		DCMaterial.registerItem(MainInit.clothUnder, ClimateCore.PACKAGE_BASE + "_leggins_cloth", ClimateMain.MOD_ID);

		MainInit.workerSuit = new ItemArmorThinDC(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH,
				EntityEquipmentSlot.LEGS, "worker").setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_worker");
		DCMaterial.registerItem(MainInit.workerSuit, ClimateCore.PACKAGE_BASE + "_leggins_worker", ClimateMain.MOD_ID);

		MainInit.blackSuit = new ItemArmorThinDC(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH,
				EntityEquipmentSlot.LEGS, "suit").setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_suit");
		DCMaterial.registerItem(MainInit.blackSuit, ClimateCore.PACKAGE_BASE + "_leggins_suit", ClimateMain.MOD_ID);

		MainInit.trackSuit = new ItemArmorThinDC(DCArmorMaterial.DC_SYNTHETIC, DCMaterialEnum.SYNTHETIC,
				EntityEquipmentSlot.LEGS, "tracksuit").setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_track");
		DCMaterial.registerItem(MainInit.trackSuit, ClimateCore.PACKAGE_BASE + "_leggins_track", ClimateMain.MOD_ID);

		MainInit.combatDress = new ItemArmorThinDC(DCArmorMaterial.DC_SYNTHETIC, DCMaterialEnum.SYNTHETIC,
				EntityEquipmentSlot.LEGS, "combatdress").setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_combatdress");
		DCMaterial.registerItem(MainInit.combatDress, ClimateCore.PACKAGE_BASE + "_leggins_combatdress",
				ClimateMain.MOD_ID);

		MainInit.hoodie = new ItemArmorHoodie(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH, EntityEquipmentSlot.CHEST,
				"white").setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_hoodie_white");
		DCMaterial.registerItem(MainInit.hoodie, ClimateCore.PACKAGE_BASE + "_hoodie_white", ClimateMain.MOD_ID);

		MainInit.leatherHat = new ItemArmorHat(ArmorMaterial.LEATHER, DCMaterialEnum.LINEN, EntityEquipmentSlot.HEAD,
				"leather").setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_hat_leather");
		DCMaterial.registerItem(MainInit.leatherHat, ClimateCore.PACKAGE_BASE + "_hat_leather", ClimateMain.MOD_ID);

		MainInit.cottonHat = new ItemArmorHat(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH, EntityEquipmentSlot.HEAD,
				"cotton").setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_hat_cotton");
		DCMaterial.registerItem(MainInit.cottonHat, ClimateCore.PACKAGE_BASE + "_hat_cotton", ClimateMain.MOD_ID);

		MainInit.wrench = new ItemWrench(DCToolMaterial.getToolMaterial(0))
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_wrench_brass");
		DCMaterial.registerItem(MainInit.wrench, ClimateCore.PACKAGE_BASE + "_wrench", ClimateMain.MOD_ID);

	}

	static void registerIntegration() {

		if (DCIntegrationCore.loadedForestry) {
			MainInit.circuit = new ItemCircuitChal().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_circuit");
			DCMaterial.registerItem(MainInit.circuit, ClimateCore.PACKAGE_BASE + "_circuit", ClimateMain.MOD_ID);
		}
		if (DCIntegrationCore.loadedMekanism) {
			MainInit.metalMaterials = new ItemMetalMaterials().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_metal");
			DCMaterial.registerItem(MainInit.metalMaterials, ClimateCore.PACKAGE_BASE + "_metal", ClimateMain.MOD_ID);
		}
	}

	static void registerMaterialEnum() {
		MainInit.repairPutty = new ItemRepairPutty().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_repair");
		DCMaterial.registerItem(MainInit.repairPutty, ClimateCore.PACKAGE_BASE + "_repair", ClimateMain.MOD_ID);

		DCArmorMaterial.load();
		DCToolMaterial.load();
	}

	private static void registerHarvestLevel() {
		MainInit.ores.setHarvestLevel("pickaxe", 0);
		MainInit.ores_2.setHarvestLevel("pickaxe", 0);
	}

}
