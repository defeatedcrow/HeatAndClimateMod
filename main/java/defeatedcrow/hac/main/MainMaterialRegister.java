package defeatedcrow.hac.main;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCMaterialReg;
import defeatedcrow.hac.core.base.DCItemBlock;
import defeatedcrow.hac.core.event.DispenseEntityItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInitRegister;
import defeatedcrow.hac.machine.MachineInitRegister;
import defeatedcrow.hac.machine.block.ItemBlockHighTier;
import defeatedcrow.hac.magic.MagicInitRegister;
import defeatedcrow.hac.main.block.FuelItemBlock;
import defeatedcrow.hac.main.block.build.BlockAwning;
import defeatedcrow.hac.main.block.build.BlockBedDC;
import defeatedcrow.hac.main.block.build.BlockBedDCFuton;
import defeatedcrow.hac.main.block.build.BlockBedDCHammock;
import defeatedcrow.hac.main.block.build.BlockBedDCRattan;
import defeatedcrow.hac.main.block.build.BlockBedDCWhite;
import defeatedcrow.hac.main.block.build.BlockBuilding;
import defeatedcrow.hac.main.block.build.BlockCarbideLamp;
import defeatedcrow.hac.main.block.build.BlockChain;
import defeatedcrow.hac.main.block.build.BlockChalcedonyLamp;
import defeatedcrow.hac.main.block.build.BlockChandelier;
import defeatedcrow.hac.main.block.build.BlockClayBricks;
import defeatedcrow.hac.main.block.build.BlockCoolantPackage;
import defeatedcrow.hac.main.block.build.BlockCurtain;
import defeatedcrow.hac.main.block.build.BlockDesiccantPackage;
import defeatedcrow.hac.main.block.build.BlockDoorDC;
import defeatedcrow.hac.main.block.build.BlockFenceBase;
import defeatedcrow.hac.main.block.build.BlockGemBricks;
import defeatedcrow.hac.main.block.build.BlockGlassPlastic;
import defeatedcrow.hac.main.block.build.BlockGlassSelenite;
import defeatedcrow.hac.main.block.build.BlockIronPlate;
import defeatedcrow.hac.main.block.build.BlockLightOrb;
import defeatedcrow.hac.main.block.build.BlockLinoleum;
import defeatedcrow.hac.main.block.build.BlockLowChest;
import defeatedcrow.hac.main.block.build.BlockMCClock_L;
import defeatedcrow.hac.main.block.build.BlockMagnetChest;
import defeatedcrow.hac.main.block.build.BlockMarkingPanel;
import defeatedcrow.hac.main.block.build.BlockMetalChest;
import defeatedcrow.hac.main.block.build.BlockMetalFenceBase;
import defeatedcrow.hac.main.block.build.BlockMetalLadder;
import defeatedcrow.hac.main.block.build.BlockMetalPillar;
import defeatedcrow.hac.main.block.build.BlockOilLamp;
import defeatedcrow.hac.main.block.build.BlockRealtimeClock;
import defeatedcrow.hac.main.block.build.BlockRealtimeClock_L;
import defeatedcrow.hac.main.block.build.BlockScaffold;
import defeatedcrow.hac.main.block.build.BlockSlabChal;
import defeatedcrow.hac.main.block.build.BlockSlabDC;
import defeatedcrow.hac.main.block.build.BlockSlabSlate;
import defeatedcrow.hac.main.block.build.BlockSofaBase;
import defeatedcrow.hac.main.block.build.BlockStairsBase;
import defeatedcrow.hac.main.block.build.BlockTableBase;
import defeatedcrow.hac.main.block.build.BlockVillageChest;
import defeatedcrow.hac.main.block.build.BlockWallLamp;
import defeatedcrow.hac.main.block.build.BlockWallShelf;
import defeatedcrow.hac.main.block.build.ItemBedDC;
import defeatedcrow.hac.main.block.build.ItemDoorDC;
import defeatedcrow.hac.main.block.build.ItemLowChest;
import defeatedcrow.hac.main.block.container.BlockCardboard;
import defeatedcrow.hac.main.block.container.BlockCropBasket;
import defeatedcrow.hac.main.block.container.BlockCropCont;
import defeatedcrow.hac.main.block.container.BlockCropJutebag;
import defeatedcrow.hac.main.block.container.BlockDustBag;
import defeatedcrow.hac.main.block.container.BlockEnemyCont;
import defeatedcrow.hac.main.block.container.BlockLogCont;
import defeatedcrow.hac.main.block.container.BlockMiscCake;
import defeatedcrow.hac.main.block.container.BlockMiscCont;
import defeatedcrow.hac.main.block.device.BlockBellow;
import defeatedcrow.hac.main.block.device.BlockCookingStove;
import defeatedcrow.hac.main.block.device.BlockFirestand;
import defeatedcrow.hac.main.block.device.BlockGeyser;
import defeatedcrow.hac.main.block.device.BlockNormalChamber;
import defeatedcrow.hac.main.block.device.BlockPail;
import defeatedcrow.hac.main.block.device.BlockShitirin;
import defeatedcrow.hac.main.block.device.BlockSink;
import defeatedcrow.hac.main.block.device.BlockStevensonScreen;
import defeatedcrow.hac.main.block.device.BlockThermometer;
import defeatedcrow.hac.main.block.device.BlockWindVane;
import defeatedcrow.hac.main.block.device.ItemBlockShitirin;
import defeatedcrow.hac.main.block.device.ItemPail;
import defeatedcrow.hac.main.block.fluid.DCFluidBlockBase;
import defeatedcrow.hac.main.block.ores.BlockDusts;
import defeatedcrow.hac.main.block.ores.BlockDusts2;
import defeatedcrow.hac.main.block.ores.BlockGem;
import defeatedcrow.hac.main.block.ores.BlockHeatingMetal;
import defeatedcrow.hac.main.block.ores.BlockLayerNew;
import defeatedcrow.hac.main.block.ores.BlockMetalAlloy;
import defeatedcrow.hac.main.block.ores.BlockMetalNew;
import defeatedcrow.hac.main.block.ores.BlockOreNew;
import defeatedcrow.hac.main.block.ores.BlockOres;
import defeatedcrow.hac.main.block.ores.BlockOres2;
import defeatedcrow.hac.main.block.plant.BlockHedge;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.item.entity.ItemCushionGray;
import defeatedcrow.hac.main.item.entity.ItemFlowerPot;
import defeatedcrow.hac.main.item.equip.ItemArmorCape;
import defeatedcrow.hac.main.item.equip.ItemArmorDC;
import defeatedcrow.hac.main.item.equip.ItemArmorDress;
import defeatedcrow.hac.main.item.equip.ItemArmorHat;
import defeatedcrow.hac.main.item.equip.ItemArmorHoodie;
import defeatedcrow.hac.main.item.equip.ItemArmorJacket;
import defeatedcrow.hac.main.item.equip.ItemArmorOvercoat;
import defeatedcrow.hac.main.item.equip.ItemArmorPants;
import defeatedcrow.hac.main.item.equip.ItemArmorShirt;
import defeatedcrow.hac.main.item.equip.ItemArmorSkirt;
import defeatedcrow.hac.main.item.equip.ItemArmorWool;
import defeatedcrow.hac.main.item.food.ItemDCFoods;
import defeatedcrow.hac.main.item.food.ItemFoodMaterials;
import defeatedcrow.hac.main.item.misc.ItemClothN;
import defeatedcrow.hac.main.item.misc.ItemDCIcon;
import defeatedcrow.hac.main.item.misc.ItemFoodDust;
import defeatedcrow.hac.main.item.misc.ItemGearN;
import defeatedcrow.hac.main.item.misc.ItemPetternPaper;
import defeatedcrow.hac.main.item.misc.ItemRepairPutty;
import defeatedcrow.hac.main.item.misc.ItemSilkworm;
import defeatedcrow.hac.main.item.ores.ItemCircuitChal;
import defeatedcrow.hac.main.item.ores.ItemGems;
import defeatedcrow.hac.main.item.ores.ItemIngots;
import defeatedcrow.hac.main.item.ores.ItemMetalMaterials;
import defeatedcrow.hac.main.item.ores.ItemMiscDust;
import defeatedcrow.hac.main.item.ores.ItemOreCrystal;
import defeatedcrow.hac.main.item.ores.ItemOreDusts;
import defeatedcrow.hac.main.item.tool.ItemAlloyYagen;
import defeatedcrow.hac.main.item.tool.ItemAxeDC;
import defeatedcrow.hac.main.item.tool.ItemBullets;
import defeatedcrow.hac.main.item.tool.ItemCrossbowDC;
import defeatedcrow.hac.main.item.tool.ItemCrowDrill;
import defeatedcrow.hac.main.item.tool.ItemDCShield;
import defeatedcrow.hac.main.item.tool.ItemEntityScope;
import defeatedcrow.hac.main.item.tool.ItemHandNet;
import defeatedcrow.hac.main.item.tool.ItemHandSpindle;
import defeatedcrow.hac.main.item.tool.ItemHoeDC;
import defeatedcrow.hac.main.item.tool.ItemMusketDC;
import defeatedcrow.hac.main.item.tool.ItemPickaxeDC;
import defeatedcrow.hac.main.item.tool.ItemRakeEarth;
import defeatedcrow.hac.main.item.tool.ItemScytheDC;
import defeatedcrow.hac.main.item.tool.ItemSpadeDC;
import defeatedcrow.hac.main.item.tool.ItemSpadeEarth;
import defeatedcrow.hac.main.item.tool.ItemStoneYagen;
import defeatedcrow.hac.main.item.tool.ItemSwordDC;
import defeatedcrow.hac.main.item.tool.ItemThermalScope;
import defeatedcrow.hac.main.item.tool.ItemThrowingArrow;
import defeatedcrow.hac.main.item.tool.ItemTinderDC;
import defeatedcrow.hac.main.item.tool.ItemWrench;
import defeatedcrow.hac.main.util.DCArmorMaterial;
import defeatedcrow.hac.main.util.DCMaterialEnum;
import defeatedcrow.hac.main.util.DCToolMaterial;
import defeatedcrow.hac.plugin.DCIntegrationCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class MainMaterialRegister {
	private MainMaterialRegister() {}

	public static void load() {

		registerMaterialEnum();

		registerMain();
		registerModuleEquip();
		registerModuleBuilding();
		registerModuleContainer();

		registerHarvestLevel();
		registerIntegration();

		registerFluids();

		FoodInitRegister.load();
		MachineInitRegister.load();
		MagicInitRegister.load();

		registerDispense();
	}

	static void registerMain() {
		// old
		if (!ModuleConfig.delete_old) {
			MainInit.ores = new BlockOres(Material.IRON, ClimateCore.PACKAGE_BASE + "_ore_stone", 15);
			DCMaterialReg.registerBlock(MainInit.ores, ClimateCore.PACKAGE_BASE + "_ore_stone", ClimateMain.MOD_ID);

			MainInit.ores_2 = new BlockOres2(Material.IRON, ClimateCore.PACKAGE_BASE + "_ore2_stone", 12);
			DCMaterialReg.registerBlock(MainInit.ores_2, ClimateCore.PACKAGE_BASE + "_ore2_stone", ClimateMain.MOD_ID);
		}

		// blocks
		MainInit.oreNew = new BlockOreNew(Material.IRON, ClimateCore.PACKAGE_BASE + "_ore_stone_new", 14);
		DCMaterialReg.registerBlock(MainInit.oreNew, ClimateCore.PACKAGE_BASE + "_ore_stone_new", ClimateMain.MOD_ID);

		MainInit.layerNew = new BlockLayerNew(Material.IRON, ClimateCore.PACKAGE_BASE + "_layer_stone_new", 7);
		DCMaterialReg
				.registerBlock(MainInit.layerNew, ClimateCore.PACKAGE_BASE + "_layer_stone_new", ClimateMain.MOD_ID);

		MainInit.metalBlockNew = new BlockMetalNew(Material.IRON, ClimateCore.PACKAGE_BASE + "_metal_new", 7);
		DCMaterialReg
				.registerBlock(MainInit.metalBlockNew, ClimateCore.PACKAGE_BASE + "_ore_metal_new", ClimateMain.MOD_ID);

		MainInit.metalBlockAlloy = new BlockMetalAlloy(Material.IRON, ClimateCore.PACKAGE_BASE + "_metal_alloy", 9);
		DCMaterialReg
				.registerBlock(MainInit.metalBlockAlloy, ClimateCore.PACKAGE_BASE + "_ore_metal_alloy", ClimateMain.MOD_ID);

		MainInit.dustBlock = new BlockDusts(Material.GROUND, ClimateCore.PACKAGE_BASE + "_dustblock", 15);
		DCMaterialReg
				.registerBlock(MainInit.dustBlock, ClimateCore.PACKAGE_BASE + "_ore_dustblock", ClimateMain.MOD_ID);

		MainInit.dustBlock_2 = new BlockDusts2(Material.GROUND, ClimateCore.PACKAGE_BASE + "_dustblock2", 3);
		DCMaterialReg
				.registerBlock(MainInit.dustBlock_2, ClimateCore.PACKAGE_BASE + "_ore_dustblock2", ClimateMain.MOD_ID);

		MainInit.heatedMetalBlock = new BlockHeatingMetal(Material.GROUND, ClimateCore.PACKAGE_BASE + "_heatingmetal",
				14);
		DCMaterialReg
				.registerBlock(MainInit.heatedMetalBlock, ClimateCore.PACKAGE_BASE + "_ore_heatingmetal", ClimateMain.MOD_ID);

		MainInit.gemBlock = new BlockGem(Material.ROCK, ClimateCore.PACKAGE_BASE + "_gemblock", 12);
		DCMaterialReg.registerBlock(MainInit.gemBlock, ClimateCore.PACKAGE_BASE + "_ore_gemblock", ClimateMain.MOD_ID);

		MainInit.markingPanel = new BlockMarkingPanel(ClimateCore.PACKAGE_BASE + "_build_markingpanel");
		DCMaterialReg
				.registerBlock(MainInit.markingPanel, ClimateCore.PACKAGE_BASE + "_build_markingpanel", ClimateMain.MOD_ID);

		MainInit.lightOrb = new BlockLightOrb(ClimateCore.PACKAGE_BASE + "_build_lightorb");
		DCMaterialReg
				.registerBlock(MainInit.lightOrb, ClimateCore.PACKAGE_BASE + "_build_lightorb", ClimateMain.MOD_ID);

		MainInit.firestand = new BlockFirestand(ClimateCore.PACKAGE_BASE + "_device_firestand");
		DCMaterialReg
				.registerBlock(MainInit.firestand, ClimateCore.PACKAGE_BASE + "_device_firestand", ClimateMain.MOD_ID);

		MainInit.chamber = new BlockNormalChamber(Material.IRON, ClimateCore.PACKAGE_BASE + "_device_chamber", 3);
		MainInit.chamber.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_chamber");
		ForgeRegistries.BLOCKS.register(MainInit.chamber);
		ForgeRegistries.ITEMS.register(new DCItemBlock(MainInit.chamber));

		MainInit.shitirin = new BlockShitirin(Material.CLAY, ClimateCore.PACKAGE_BASE + "_device_shitirin", 3);
		MainInit.shitirin.setRegistryName(ClimateCore.PACKAGE_BASE + "_device_shitirin");
		ForgeRegistries.BLOCKS.register(MainInit.shitirin);
		ForgeRegistries.ITEMS.register(new ItemBlockShitirin(MainInit.shitirin));

		MainInit.fuelStove = new BlockCookingStove(Material.IRON, ClimateCore.PACKAGE_BASE + "_device_fuelstove", 3);
		MainInit.fuelStove.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_device_fuelstove");
		ForgeRegistries.BLOCKS.register(MainInit.fuelStove);
		ForgeRegistries.ITEMS.register(new DCItemBlock(MainInit.fuelStove));

		MainInit.thermometer = new BlockThermometer(ClimateCore.PACKAGE_BASE + "_device_thermometer");
		DCMaterialReg
				.registerBlock(MainInit.thermometer, ClimateCore.PACKAGE_BASE + "_device_thermometer", ClimateMain.MOD_ID);

		MainInit.windvane = new BlockWindVane(ClimateCore.PACKAGE_BASE + "_device_windvane");
		DCMaterialReg
				.registerBlock(MainInit.windvane, ClimateCore.PACKAGE_BASE + "_device_windvane", ClimateMain.MOD_ID);

		MainInit.stevenson_screen = new BlockStevensonScreen(ClimateCore.PACKAGE_BASE + "_device_stevenson_screen");
		DCMaterialReg
				.registerBlock(MainInit.stevenson_screen, ClimateCore.PACKAGE_BASE + "_device_stevenson_screen", ClimateMain.MOD_ID);

		MainInit.bellow = new BlockBellow(ClimateCore.PACKAGE_BASE + "_device_bellow");
		MainInit.bellow.setRegistryName(ClimateCore.PACKAGE_BASE + "_device_bellow");
		ForgeRegistries.BLOCKS.register(MainInit.bellow);
		ForgeRegistries.ITEMS.register(new ItemBlockHighTier(MainInit.bellow, 1));

		MainInit.pail = new BlockPail(ClimateCore.PACKAGE_BASE + "_device_pail");
		MainInit.pail.setRegistryName(ClimateCore.PACKAGE_BASE + "_device_pail");
		ForgeRegistries.BLOCKS.register(MainInit.pail);
		ForgeRegistries.ITEMS.register(new ItemPail(MainInit.pail));

		MainInit.geyser = new BlockGeyser(ClimateCore.PACKAGE_BASE + "_device_geyser_stone");
		DCMaterialReg
				.registerBlock(MainInit.geyser, ClimateCore.PACKAGE_BASE + "_device_geyser_stone", ClimateMain.MOD_ID);

		// items

		// title
		MainInit.iconItem = new ItemDCIcon().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_icons");
		DCMaterialReg.registerItem(MainInit.iconItem, ClimateCore.PACKAGE_BASE + "_icons", ClimateMain.MOD_ID);

		// ores
		MainInit.oreItem = new ItemOreCrystal(14).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_oreitem");
		DCMaterialReg.registerItem(MainInit.oreItem, ClimateCore.PACKAGE_BASE + "_oreitem", ClimateMain.MOD_ID);

		MainInit.oreIngot = new ItemIngots(18).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_ingot");
		DCMaterialReg.registerItem(MainInit.oreIngot, ClimateCore.PACKAGE_BASE + "_ingot", ClimateMain.MOD_ID);

		MainInit.oreDust = new ItemOreDusts(15).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_oredust");
		DCMaterialReg.registerItem(MainInit.oreDust, ClimateCore.PACKAGE_BASE + "_oredust", ClimateMain.MOD_ID);

		MainInit.gems = new ItemGems(24).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_gem");
		DCMaterialReg.registerItem(MainInit.gems, ClimateCore.PACKAGE_BASE + "_gem", ClimateMain.MOD_ID);

		MainInit.miscDust = new ItemMiscDust(13).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_miscdust");
		DCMaterialReg.registerItem(MainInit.miscDust, ClimateCore.PACKAGE_BASE + "_miscdust", ClimateMain.MOD_ID);

		MainInit.foodDust = new ItemFoodDust(7).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fooddust");
		DCMaterialReg.registerItem(MainInit.foodDust, ClimateCore.PACKAGE_BASE + "_fooddust", ClimateMain.MOD_ID);

		// tools
		MainInit.gears = new ItemGearN(4).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_spindle");
		DCMaterialReg.registerItem(MainInit.gears, ClimateCore.PACKAGE_BASE + "_spindle", ClimateMain.MOD_ID);

		MainInit.clothes = new ItemClothN(11).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_misc");
		DCMaterialReg.registerItem(MainInit.clothes, ClimateCore.PACKAGE_BASE + "_misc", ClimateMain.MOD_ID);

		MainInit.patternPaper = new ItemPetternPaper().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_pattern_paper");
		DCMaterialReg
				.registerItem(MainInit.patternPaper, ClimateCore.PACKAGE_BASE + "_pattern_paper", ClimateMain.MOD_ID);

		MainInit.silkworm = new ItemSilkworm(3).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_silkworm");
		DCMaterialReg.registerItem(MainInit.silkworm, ClimateCore.PACKAGE_BASE + "_silkworm", ClimateMain.MOD_ID);

		MainInit.stoneYagen = new ItemStoneYagen().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_yagen_stone");
		DCMaterialReg.registerItem(MainInit.stoneYagen, ClimateCore.PACKAGE_BASE + "_yagen_stone", ClimateMain.MOD_ID);

		MainInit.brassYagen = new ItemAlloyYagen().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_yagen_brass");
		DCMaterialReg.registerItem(MainInit.brassYagen, ClimateCore.PACKAGE_BASE + "_yagen_brass", ClimateMain.MOD_ID);

		MainInit.handSpindle = new ItemHandSpindle().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_handspindle");
		DCMaterialReg.registerItem(MainInit.handSpindle, ClimateCore.PACKAGE_BASE + "_handspindle", ClimateMain.MOD_ID);

		MainInit.crowDrill = new ItemCrowDrill().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_creative_drill");
		DCMaterialReg
				.registerItem(MainInit.crowDrill, ClimateCore.PACKAGE_BASE + "_creative_drill", ClimateMain.MOD_ID);

		MainInit.scope = new ItemThermalScope().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_thermal_scope");
		DCMaterialReg.registerItem(MainInit.scope, ClimateCore.PACKAGE_BASE + "_thermal_scope", ClimateMain.MOD_ID);

		MainInit.entityScope = new ItemEntityScope().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_entity_scope");
		DCMaterialReg
				.registerItem(MainInit.entityScope, ClimateCore.PACKAGE_BASE + "_entity_scope", ClimateMain.MOD_ID);

		MainInit.foodMaterials = new ItemFoodMaterials(3)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_materials");
		DCMaterialReg
				.registerItem(MainInit.foodMaterials, ClimateCore.PACKAGE_BASE + "_food_materials", ClimateMain.MOD_ID);

		MainInit.bakedApple = new ItemDCFoods(7, false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_itemfood");
		DCMaterialReg
				.registerItem(MainInit.bakedApple, ClimateCore.PACKAGE_BASE + "_food_itemfood", ClimateMain.MOD_ID);

		MainInit.wrench = new ItemWrench(DCToolMaterial.getToolMaterial(0))
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_wrench_brass");
		DCMaterialReg.registerItem(MainInit.wrench, ClimateCore.PACKAGE_BASE + "_wrench", ClimateMain.MOD_ID);

		MainInit.tinder = new ItemTinderDC().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_tinder");
		DCMaterialReg.registerItem(MainInit.tinder, ClimateCore.PACKAGE_BASE + "_tinder", ClimateMain.MOD_ID);
	}

	static void registerModuleBuilding() {
		MainInit.bricks = new BlockGemBricks(Material.ROCK, ClimateCore.PACKAGE_BASE + "_build_bricks");
		DCMaterialReg.registerBlock(MainInit.bricks, ClimateCore.PACKAGE_BASE + "_build_bricks", ClimateMain.MOD_ID);

		MainInit.builds = new BlockBuilding(Material.CLAY, ClimateCore.PACKAGE_BASE + "_build_build");
		DCMaterialReg.registerBlock(MainInit.builds, ClimateCore.PACKAGE_BASE + "_build_build", ClimateMain.MOD_ID);

		MainInit.clayBricks = new BlockClayBricks(ClimateCore.PACKAGE_BASE + "_build_claybrick");
		DCMaterialReg
				.registerBlock(MainInit.clayBricks, ClimateCore.PACKAGE_BASE + "_build_claybrick", ClimateMain.MOD_ID);
		ClimateMain.proxy.regTEJson(MainInit.clayBricks, "dcs_climate", "dcs_build_claybrick", "build");

		MainInit.selenite = new BlockGlassSelenite(ClimateCore.PACKAGE_BASE + "_build_selenite", 3);
		DCMaterialReg
				.registerBlock(MainInit.selenite, ClimateCore.PACKAGE_BASE + "_build_selenite", ClimateMain.MOD_ID);

		MainInit.linoleum = new BlockLinoleum(Material.WOOD, ClimateCore.PACKAGE_BASE + "_build_linoleum");
		DCMaterialReg
				.registerBlock(MainInit.linoleum, ClimateCore.PACKAGE_BASE + "_build_linoleum", ClimateMain.MOD_ID);

		MainInit.syntheticBlock = new BlockGlassPlastic(ClimateCore.PACKAGE_BASE + "_build_synthetic_glass", 15);
		DCMaterialReg
				.registerBlock(MainInit.syntheticBlock, ClimateCore.PACKAGE_BASE + "_build_synthetic_glass", ClimateMain.MOD_ID);

		MainInit.stairsGlass = new BlockStairsBase(MainInit.selenite.getDefaultState(), "build/glass_stairs", true,
				false).setUnlocalizedName("dcs_stairs_glass");
		DCMaterialReg
				.registerBlock(MainInit.stairsGlass, ClimateCore.PACKAGE_BASE + "_stairs_glass", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.stairsGlass), "dcs_climate", "dcs_stairs_glass", "build", 15, false);

		MainInit.stairsGypsum = new BlockStairsBase(MainInit.layerNew.getDefaultState(), "ores/gemblock_gypsum", false,
				false).setUnlocalizedName("dcs_stairs_gypsum");
		DCMaterialReg
				.registerBlock(MainInit.stairsGypsum, ClimateCore.PACKAGE_BASE + "_stairs_gypsum", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.stairsGypsum), "dcs_climate", "dcs_stairs_gypsum", "build", 15, false);

		MainInit.stairsMarble = new BlockStairsBase(MainInit.layerNew.getDefaultState(), "ores/gemblock_marble", false,
				false).setUnlocalizedName("dcs_stairs_marble");
		DCMaterialReg
				.registerBlock(MainInit.stairsMarble, ClimateCore.PACKAGE_BASE + "_stairs_marble", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.stairsMarble), "dcs_climate", "dcs_stairs_marble", "build", 15, false);

		MainInit.stairsSerpentine = new BlockStairsBase(MainInit.layerNew.getDefaultState(), "ores/gemblock_serpentine",
				false, false).setUnlocalizedName("dcs_stairs_serpentine");
		DCMaterialReg
				.registerBlock(MainInit.stairsSerpentine, ClimateCore.PACKAGE_BASE + "_stairs_serpentine", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.stairsSerpentine), "dcs_climate", "dcs_stairs_serpentine", "build", 15, false);

		MainInit.stairsBedrock = new BlockStairsBase(MainInit.layerNew.getDefaultState(), "ores/gemblock_bedrock",
				false, false).setBedRock().setUnlocalizedName("dcs_stairs_bedrock");
		DCMaterialReg
				.registerBlock(MainInit.stairsBedrock, ClimateCore.PACKAGE_BASE + "_stairs_bedrock", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.stairsBedrock), "dcs_climate", "dcs_stairs_bedrock", "build", 15, false);

		MainInit.stairsDirtbrick = new BlockStairsBase(MainInit.layerNew.getDefaultState(), "build/build_dirtbrick",
				false, false).setUnlocalizedName("dcs_stairs_dirtbrick");
		DCMaterialReg
				.registerBlock(MainInit.stairsDirtbrick, ClimateCore.PACKAGE_BASE + "_stairs_dirtbrick", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.stairsDirtbrick), "dcs_climate", "dcs_stairs_dirtbrick", "build", 15, false);

		MainInit.halfSlab = new BlockSlabDC();
		DCMaterialReg.registerBlock(MainInit.halfSlab, ClimateCore.PACKAGE_BASE + "_build_slab", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.halfSlab), "dcs_climate", "dcs_build_slab", "build", 5, true);

		MainInit.halfSlab2 = new BlockSlabChal();
		DCMaterialReg
				.registerBlock(MainInit.halfSlab2, ClimateCore.PACKAGE_BASE + "_build_slab_chal", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.halfSlab2), "dcs_climate", "dcs_build_slab_chal", "build", 6, true);

		MainInit.halfSlab3 = new BlockSlabSlate();
		DCMaterialReg
				.registerBlock(MainInit.halfSlab3, ClimateCore.PACKAGE_BASE + "_build_slab_slate", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.halfSlab3), "dcs_climate", "dcs_build_slab_slate", "build", 3, true);

		MainInit.fenceGypsum = new BlockFenceBase("dcs_fence_gypsum").setUnlocalizedName("dcs_fence_gypsum");
		DCMaterialReg
				.registerBlock(MainInit.fenceGypsum, ClimateCore.PACKAGE_BASE + "_fence_gypsum", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.fenceGypsum), "dcs_climate", "dcs_fence_gypsum", "build", 15, false);

		MainInit.fenceMarble = new BlockFenceBase("dcs_fence_marble").setUnlocalizedName("dcs_fence_marble");
		DCMaterialReg
				.registerBlock(MainInit.fenceMarble, ClimateCore.PACKAGE_BASE + "_fence_marble", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.fenceMarble), "dcs_climate", "dcs_fence_marble", "build", 15, false);

		MainInit.fenceSerpentine = new BlockFenceBase("dcs_fence_serpentine")
				.setUnlocalizedName("dcs_fence_serpentine");
		DCMaterialReg
				.registerBlock(MainInit.fenceSerpentine, ClimateCore.PACKAGE_BASE + "_fence_serpentine", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.fenceSerpentine), "dcs_climate", "dcs_fence_serpentine", "build", 15, false);

		MainInit.fenceBedrock = new BlockFenceBase("dcs_fence_bedrock").setBedRock()
				.setUnlocalizedName("dcs_fence_bedrock");
		DCMaterialReg
				.registerBlock(MainInit.fenceBedrock, ClimateCore.PACKAGE_BASE + "_fence_bedrock", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.fenceBedrock), "dcs_climate", "dcs_fence_bedrock", "build", 15, false);

		MainInit.roofSlate = new BlockStairsBase(MainInit.builds.getDefaultState(), "ores/gemblock_bedrock", false,
				true).setUnlocalizedName("dcs_roof_slate");
		DCMaterialReg.registerBlock(MainInit.roofSlate, ClimateCore.PACKAGE_BASE + "_roof_slate", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.roofSlate), "dcs_climate", "dcs_roof_slate", "build", 15, false);

		MainInit.roofSlateRed = new BlockStairsBase(MainInit.builds.getDefaultState(), "ores/gemblock_bedrock", false,
				true).setUnlocalizedName("dcs_roof_slate_red");
		DCMaterialReg
				.registerBlock(MainInit.roofSlateRed, ClimateCore.PACKAGE_BASE + "_roof_slate_red", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.roofSlateRed), "dcs_climate", "dcs_roof_slate_red", "build", 15, false);

		MainInit.roofSlateGreen = new BlockStairsBase(MainInit.builds.getDefaultState(), "ores/gemblock_bedrock", false,
				true).setUnlocalizedName("dcs_roof_slate_green");
		DCMaterialReg
				.registerBlock(MainInit.roofSlateGreen, ClimateCore.PACKAGE_BASE + "_roof_slate_green", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.roofSlateGreen), "dcs_climate", "dcs_roof_slate_green", "build", 15, false);

		MainInit.roofSlateBrown = new BlockStairsBase(MainInit.builds.getDefaultState(), "ores/gemblock_bedrock", false,
				true).setUnlocalizedName("dcs_roof_slate_brown");
		DCMaterialReg
				.registerBlock(MainInit.roofSlateBrown, ClimateCore.PACKAGE_BASE + "_roof_slate_brown", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.roofSlateBrown), "dcs_climate", "dcs_roof_slate_brown", "build", 15, false);

		MainInit.plate = new BlockIronPlate(ClimateCore.PACKAGE_BASE + "_build_plate", 1);
		DCMaterialReg.registerBlock(MainInit.plate, ClimateCore.PACKAGE_BASE + "_build_plate", ClimateMain.MOD_ID);
		ClimateMain.proxy.regBlockJson(Item
				.getItemFromBlock(MainInit.plate), "dcs_climate", "dcs_build_plate", "build", 1, false);

		MainInit.chalLamp = new BlockChalcedonyLamp(ClimateCore.PACKAGE_BASE + "_build_challamp", 15);
		DCMaterialReg
				.registerBlock(MainInit.chalLamp, ClimateCore.PACKAGE_BASE + "_build_challamp", ClimateMain.MOD_ID);

		MainInit.wallLamp = new BlockWallLamp(ClimateCore.PACKAGE_BASE + "_build_walllamp");
		DCMaterialReg
				.registerBlock(MainInit.wallLamp, ClimateCore.PACKAGE_BASE + "_build_walllamp", ClimateMain.MOD_ID);

		MainInit.oilLamp = new BlockOilLamp(ClimateCore.PACKAGE_BASE + "_build_oillamp");
		DCMaterialReg.registerBlock(MainInit.oilLamp, ClimateCore.PACKAGE_BASE + "_build_oillamp", ClimateMain.MOD_ID);

		MainInit.desiccant = new BlockDesiccantPackage(ClimateCore.PACKAGE_BASE + "_desiccant");
		DCMaterialReg.registerBlock(MainInit.desiccant, ClimateCore.PACKAGE_BASE + "_desiccant", ClimateMain.MOD_ID);

		MainInit.freezepack = new BlockCoolantPackage(ClimateCore.PACKAGE_BASE + "_coolant");
		DCMaterialReg.registerBlock(MainInit.freezepack, ClimateCore.PACKAGE_BASE + "_coolant", ClimateMain.MOD_ID);

		MainInit.scaffold = new BlockScaffold(Material.WOOD, ClimateCore.PACKAGE_BASE + "_build_scaffold");
		DCMaterialReg
				.registerBlock(MainInit.scaffold, ClimateCore.PACKAGE_BASE + "_build_scaffold", ClimateMain.MOD_ID);

		if (ModuleConfig.build_advanced) {

			MainInit.pillarSteel = new BlockMetalPillar("dcs_pillar_steel").setUnlocalizedName("dcs_pillar_steel");
			DCMaterialReg
					.registerBlock(MainInit.pillarSteel, ClimateCore.PACKAGE_BASE + "_pillar_steel", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.pillarSteel), "dcs_climate", "dcs_pillar_steel", "build", 15, false);

			MainInit.fenceSteel = new BlockFenceBase("dcs_fence_steel").setUnlocalizedName("dcs_fence_steel");
			DCMaterialReg
					.registerBlock(MainInit.fenceSteel, ClimateCore.PACKAGE_BASE + "_fence_steel", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.fenceSteel), "dcs_climate", "dcs_fence_steel", "build", 15, false);

			MainInit.fenceNetSteel = new BlockMetalFenceBase("dcs_fence_net_steel", false)
					.setUnlocalizedName("dcs_fence_net_steel");
			DCMaterialReg
					.registerBlock(MainInit.fenceNetSteel, ClimateCore.PACKAGE_BASE + "_fence_net_steel", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.fenceNetSteel), "dcs_climate", "dcs_fence_net_steel", "build", 3, false);

			MainInit.fenceLadderSteel = new BlockMetalLadder("dcs_fence_ladder_steel")
					.setUnlocalizedName("dcs_fence_ladder_steel");
			DCMaterialReg
					.registerBlock(MainInit.fenceLadderSteel, ClimateCore.PACKAGE_BASE + "_fence_ladder_steel", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.fenceLadderSteel), "dcs_climate", "dcs_fence_ladder_steel", "build", 3, false);

			MainInit.fenceAluminium = new BlockMetalFenceBase("dcs_fence_aluminium", false)
					.setUnlocalizedName("dcs_fence_aluminium");
			DCMaterialReg
					.registerBlock(MainInit.fenceAluminium, ClimateCore.PACKAGE_BASE + "_fence_aluminium", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.fenceAluminium), "dcs_climate", "dcs_fence_aluminium", "build", 3, false);

			MainInit.fenceNet = new BlockMetalFenceBase("dcs_fence_net", false).setUnlocalizedName("dcs_fence_net");
			DCMaterialReg.registerBlock(MainInit.fenceNet, ClimateCore.PACKAGE_BASE + "_fence_net", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.fenceNet), "dcs_climate", "dcs_fence_net", "build", 3, false);

			MainInit.fenceGlass = new BlockMetalFenceBase("dcs_fence_glass", true)
					.setUnlocalizedName("dcs_fence_glass");
			DCMaterialReg
					.registerBlock(MainInit.fenceGlass, ClimateCore.PACKAGE_BASE + "_fence_glass", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.fenceGlass), "dcs_climate", "dcs_fence_glass", "build", 3, false);

			MainInit.fenceRattan = new BlockMetalFenceBase("dcs_fence_rattan", false)
					.setUnlocalizedName("dcs_fence_rattan");
			DCMaterialReg
					.registerBlock(MainInit.fenceRattan, ClimateCore.PACKAGE_BASE + "_fence_rattan", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.fenceRattan), "dcs_climate", "dcs_fence_rattan", "build", 3, false);

			MainInit.fenceLadder = new BlockMetalLadder("dcs_fence_ladder").setUnlocalizedName("dcs_fence_ladder");
			DCMaterialReg
					.registerBlock(MainInit.fenceLadder, ClimateCore.PACKAGE_BASE + "_fence_ladder", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.fenceLadder), "dcs_climate", "dcs_fence_ladder", "build", 3, false);

			MainInit.awning = new BlockAwning("dcs_build_awning").setUnlocalizedName("dcs_build_awning");
			DCMaterialReg
					.registerBlock(MainInit.awning, ClimateCore.PACKAGE_BASE + "_build_awning", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.awning), "dcs_climate", "dcs_build_awning", "build", 3, false);

			MainInit.chain = new BlockChain(Material.CLAY, "dcs_build_chain").setUnlocalizedName("dcs_build_chain");
			DCMaterialReg.registerBlock(MainInit.chain, ClimateCore.PACKAGE_BASE + "_build_chain", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.chain), "dcs_climate", "dcs_build_chain", "build", 1, true);

			MainInit.tableMarble = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_table_marble", false);
			DCMaterialReg
					.registerBlock(MainInit.tableMarble, ClimateCore.PACKAGE_BASE + "_table_marble", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.tableMarble), "dcs_climate", "dcs_table_marble", "build", 0, false);

			MainInit.tableGypsum = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_table_gypsum", false);
			DCMaterialReg
					.registerBlock(MainInit.tableGypsum, ClimateCore.PACKAGE_BASE + "_table_gypsum", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.tableGypsum), "dcs_climate", "dcs_table_gypsum", "build", 0, false);

			MainInit.tableWood = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_table_wood", false);
			DCMaterialReg
					.registerBlock(MainInit.tableWood, ClimateCore.PACKAGE_BASE + "_table_wood", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.tableWood), "dcs_climate", "dcs_table_wood", "build", 0, false);

			MainInit.tableDark = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_table_darkwood", false);
			DCMaterialReg
					.registerBlock(MainInit.tableDark, ClimateCore.PACKAGE_BASE + "_table_darkwood", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.tableDark), "dcs_climate", "dcs_table_darkwood", "build", 0, false);

			MainInit.squaretableWood = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_squaretable_wood", false);
			DCMaterialReg
					.registerBlock(MainInit.squaretableWood, ClimateCore.PACKAGE_BASE + "_squaretable_wood", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.squaretableWood), "dcs_climate", "dcs_squaretable_wood", "build", 0, false);

			MainInit.squaretableMarble = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_squaretable_marble", false);
			DCMaterialReg
					.registerBlock(MainInit.squaretableMarble, ClimateCore.PACKAGE_BASE + "_squaretable_marble", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.squaretableMarble), "dcs_climate", "dcs_squaretable_marble", "build", 0, false);

			MainInit.squaretableChecker = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_squaretable_checker", false);
			DCMaterialReg
					.registerBlock(MainInit.squaretableChecker, ClimateCore.PACKAGE_BASE + "_squaretable_checker", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.squaretableChecker), "dcs_climate", "dcs_squaretable_checker", "build", 0, false);

			MainInit.squaretableBlack = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_squaretable_black", false);
			DCMaterialReg
					.registerBlock(MainInit.squaretableBlack, ClimateCore.PACKAGE_BASE + "_squaretable_black", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.squaretableBlack), "dcs_climate", "dcs_squaretable_black", "build", 0, false);

			MainInit.squaretableRattan = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_squaretable_rattan", false)
					.setHalf();
			DCMaterialReg
					.registerBlock(MainInit.squaretableRattan, ClimateCore.PACKAGE_BASE + "_squaretable_rattan", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.squaretableRattan), "dcs_climate", "dcs_squaretable_rattan", "build", 0, false);

			MainInit.carpetRed = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_carpet_red", true);
			DCMaterialReg
					.registerBlock(MainInit.carpetRed, ClimateCore.PACKAGE_BASE + "_carpet_red", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.carpetRed), "dcs_climate", "dcs_carpet_red", "build", 0, false);

			MainInit.carpetWhite = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_carpet_white", true);
			DCMaterialReg
					.registerBlock(MainInit.carpetWhite, ClimateCore.PACKAGE_BASE + "_carpet_white", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.carpetWhite), "dcs_climate", "dcs_carpet_white", "build", 0, false);

			MainInit.carpetGray = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_carpet_gray", true);
			DCMaterialReg
					.registerBlock(MainInit.carpetGray, ClimateCore.PACKAGE_BASE + "_carpet_gray", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.carpetGray), "dcs_climate", "dcs_carpet_gray", "build", 0, false);

			MainInit.sofaBlack = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_sofa_black");
			DCMaterialReg
					.registerBlock(MainInit.sofaBlack, ClimateCore.PACKAGE_BASE + "_sofa_black", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.sofaBlack), "dcs_climate", "dcs_sofa_black", "build", 0, false);

			MainInit.sofaRed = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_sofa_red");
			DCMaterialReg.registerBlock(MainInit.sofaRed, ClimateCore.PACKAGE_BASE + "_sofa_red", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.sofaRed), "dcs_climate", "dcs_sofa_red", "build", 0, false);

			MainInit.stoolBlack = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_stool_black").setSmallAABB();
			DCMaterialReg
					.registerBlock(MainInit.stoolBlack, ClimateCore.PACKAGE_BASE + "_stool_black", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.stoolBlack), "dcs_climate", "dcs_stool_black", "build", 0, false);

			MainInit.stoolRed = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_stool_red").setSmallAABB();
			DCMaterialReg.registerBlock(MainInit.stoolRed, ClimateCore.PACKAGE_BASE + "_stool_red", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.stoolRed), "dcs_climate", "dcs_stool_red", "build", 0, false);

			MainInit.stoolRattan = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_stool_rattan").setSmallAABB();
			DCMaterialReg
					.registerBlock(MainInit.stoolRattan, ClimateCore.PACKAGE_BASE + "_stool_rattan", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.stoolRattan), "dcs_climate", "dcs_stool_rattan", "build", 0, false);

			MainInit.chairWood = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_chair_wood").setSmallAABB();
			DCMaterialReg
					.registerBlock(MainInit.chairWood, ClimateCore.PACKAGE_BASE + "_chair_wood", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.chairWood), "dcs_climate", "dcs_chair_wood", "build", 0, false);

			MainInit.chairMarble = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_chair_marble").setSmallAABB();
			DCMaterialReg
					.registerBlock(MainInit.chairMarble, ClimateCore.PACKAGE_BASE + "_chair_marble", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.chairMarble), "dcs_climate", "dcs_chair_marble", "build", 0, false);

			MainInit.chairChecker = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_chair_checker").setSmallAABB();
			DCMaterialReg
					.registerBlock(MainInit.chairChecker, ClimateCore.PACKAGE_BASE + "_chair_checker", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.chairChecker), "dcs_climate", "dcs_chair_checker", "build", 0, false);

			MainInit.chairBlack = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_chair_black").setSmallAABB();
			DCMaterialReg
					.registerBlock(MainInit.chairBlack, ClimateCore.PACKAGE_BASE + "_chair_black", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.chairBlack), "dcs_climate", "dcs_chair_black", "build", 0, false);

			MainInit.chairRattan = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_chair_rattan").setSmallAABB();
			DCMaterialReg
					.registerBlock(MainInit.chairRattan, ClimateCore.PACKAGE_BASE + "_chair_rattan", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.chairRattan), "dcs_climate", "dcs_chair_rattan", "build", 0, false);

			MainInit.lampCarbide = new BlockCarbideLamp(ClimateCore.PACKAGE_BASE + "_lamp_carbide_lantern");
			DCMaterialReg
					.registerBlock(MainInit.lampCarbide, ClimateCore.PACKAGE_BASE + "_lamp_carbide_lantern", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.lampCarbide), "dcs_climate", "dcs_lamp_carbide_lantern", "build", 0, false);

			MainInit.lampGas = new BlockCarbideLamp(ClimateCore.PACKAGE_BASE + "_lamp_carbide_glass");
			DCMaterialReg
					.registerBlock(MainInit.lampGas, ClimateCore.PACKAGE_BASE + "_lamp_carbide_glass", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.lampGas), "dcs_climate", "dcs_lamp_carbide_glass", "build", 0, false);

			MainInit.chestMarble = new BlockLowChest(Material.WOOD,
					ClimateCore.PACKAGE_BASE + "_device_lowchest_marble", true);
			registerChestBlock(MainInit.chestMarble, ClimateCore.PACKAGE_BASE + "_device_lowchest_marble", ClimateMain.MOD_ID);
			ClimateMain.proxy.regTEJson(MainInit.chestMarble, "dcs_climate", "dcs_device_lowchest_marble", "device");

			MainInit.chestWood = new BlockLowChest(Material.WOOD, ClimateCore.PACKAGE_BASE + "_device_lowchest_wood",
					true);
			registerChestBlock(MainInit.chestWood, ClimateCore.PACKAGE_BASE + "_device_lowchest_wood", ClimateMain.MOD_ID);
			ClimateMain.proxy.regTEJson(MainInit.chestWood, "dcs_climate", "dcs_device_lowchest_wood", "device");

			MainInit.chestChecker = new BlockLowChest(Material.WOOD,
					ClimateCore.PACKAGE_BASE + "_device_lowchest_checker", true);
			registerChestBlock(MainInit.chestChecker, ClimateCore.PACKAGE_BASE + "_device_lowchest_checker", ClimateMain.MOD_ID);
			ClimateMain.proxy.regTEJson(MainInit.chestChecker, "dcs_climate", "dcs_device_lowchest_checker", "device");

			MainInit.chestBlack = new BlockLowChest(Material.WOOD, ClimateCore.PACKAGE_BASE + "_device_lowchest_black",
					true);
			registerChestBlock(MainInit.chestBlack, ClimateCore.PACKAGE_BASE + "_device_lowchest_black", ClimateMain.MOD_ID);
			ClimateMain.proxy.regTEJson(MainInit.chestBlack, "dcs_climate", "dcs_device_lowchest_black", "device");

			MainInit.chestRattan = new BlockLowChest(Material.WOOD,
					ClimateCore.PACKAGE_BASE + "_device_lowchest_rattan", true);
			registerChestBlock(MainInit.chestRattan, ClimateCore.PACKAGE_BASE + "_device_lowchest_rattan", ClimateMain.MOD_ID);
			ClimateMain.proxy.regTEJson(MainInit.chestRattan, "dcs_climate", "dcs_device_lowchest_rattan", "device");

			MainInit.wallshelfMarble = new BlockWallShelf(Material.WOOD, ClimateCore.PACKAGE_BASE + "_wallshelf_marble",
					true);
			registerChestBlock(MainInit.wallshelfMarble, ClimateCore.PACKAGE_BASE + "_wallshelf_marble", ClimateMain.MOD_ID);
			ClimateMain.proxy.regTEJson(MainInit.wallshelfMarble, "dcs_climate", "dcs_wallshelf_marble", "device");

			MainInit.wallshelfWood = new BlockWallShelf(Material.WOOD, ClimateCore.PACKAGE_BASE + "_wallshelf_wood",
					true);
			registerChestBlock(MainInit.wallshelfWood, ClimateCore.PACKAGE_BASE + "_wallshelf_wood", ClimateMain.MOD_ID);
			ClimateMain.proxy.regTEJson(MainInit.wallshelfWood, "dcs_climate", "dcs_wallshelf_wood", "device");

			MainInit.wallshelfChecker = new BlockWallShelf(Material.WOOD,
					ClimateCore.PACKAGE_BASE + "_wallshelf_checker", true);
			registerChestBlock(MainInit.wallshelfChecker, ClimateCore.PACKAGE_BASE + "_wallshelf_checker", ClimateMain.MOD_ID);
			ClimateMain.proxy.regTEJson(MainInit.wallshelfChecker, "dcs_climate", "dcs_wallshelf_checker", "device");

			MainInit.wallshelfBlack = new BlockWallShelf(Material.WOOD, ClimateCore.PACKAGE_BASE + "_wallshelf_black",
					true);
			registerChestBlock(MainInit.wallshelfBlack, ClimateCore.PACKAGE_BASE + "_wallshelf_black", ClimateMain.MOD_ID);
			ClimateMain.proxy.regTEJson(MainInit.wallshelfBlack, "dcs_climate", "dcs_wallshelf_black", "device");

			MainInit.wallshelfRattan = new BlockWallShelf(Material.WOOD, ClimateCore.PACKAGE_BASE + "_wallshelf_rattan",
					true);
			registerChestBlock(MainInit.wallshelfRattan, ClimateCore.PACKAGE_BASE + "_wallshelf_rattan", ClimateMain.MOD_ID);
			ClimateMain.proxy.regTEJson(MainInit.wallshelfRattan, "dcs_climate", "dcs_wallshelf_rattan", "device");

			MainInit.sinkMetal = new BlockSink(ClimateCore.PACKAGE_BASE + "_device_sink_half", false);
			DCMaterialReg
					.registerBlock(MainInit.sinkMetal, ClimateCore.PACKAGE_BASE + "_device_sink_half", ClimateMain.MOD_ID);
			ClimateMain.proxy.regTEJson(MainInit.sinkMetal, "dcs_climate", "dcs_device_sink_half", "device");

			MainInit.sinkChest = new BlockSink(ClimateCore.PACKAGE_BASE + "_device_sink_full", true);
			DCMaterialReg
					.registerBlock(MainInit.sinkChest, ClimateCore.PACKAGE_BASE + "_device_sink_full", ClimateMain.MOD_ID);
			ClimateMain.proxy.regTEJson(MainInit.sinkChest, "dcs_climate", "dcs_device_sink_full", "device");

			MainInit.hedgeSpring = new BlockHedge("dcs_hedge_spring").setUnlocalizedName("dcs_hedge_spring");
			DCMaterialReg
					.registerBlock(MainInit.hedgeSpring, ClimateCore.PACKAGE_BASE + "_hedge_spring", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.hedgeSpring), "dcs_climate", "dcs_hedge_spring", "crop", 15, false);

			MainInit.hedgeSummer = new BlockHedge("dcs_hedge_summer").setUnlocalizedName("dcs_hedge_summer");
			DCMaterialReg
					.registerBlock(MainInit.hedgeSummer, ClimateCore.PACKAGE_BASE + "_hedge_summer", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.hedgeSummer), "dcs_climate", "dcs_hedge_summer", "crop", 15, false);

			MainInit.hedgeAutumn = new BlockHedge("dcs_hedge_autumn").setUnlocalizedName("dcs_hedge_autumn");
			DCMaterialReg
					.registerBlock(MainInit.hedgeAutumn, ClimateCore.PACKAGE_BASE + "_hedge_autumn", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.hedgeAutumn), "dcs_climate", "dcs_hedge_autumn", "crop", 15, false);

			MainInit.hedgeWinter = new BlockHedge("dcs_hedge_winter").setUnlocalizedName("dcs_hedge_winter");
			DCMaterialReg
					.registerBlock(MainInit.hedgeWinter, ClimateCore.PACKAGE_BASE + "_hedge_winter", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.hedgeWinter), "dcs_climate", "dcs_hedge_winter", "crop", 15, false);

			MainInit.chandelierGypsum = new BlockChandelier("dcs_build_chandelier")
					.setUnlocalizedName("dcs_build_chandelier");
			DCMaterialReg
					.registerBlock(MainInit.chandelierGypsum, ClimateCore.PACKAGE_BASE + "_build_chandelier", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.chandelierGypsum), "dcs_climate", "dcs_build_chandelier", "build", 2, true);

			MainInit.doorMarble = new BlockDoorDC(ClimateCore.PACKAGE_BASE + "_door_marble", Material.ROCK);
			DCMaterialReg
					.registerBlock(MainInit.doorMarble, ClimateCore.PACKAGE_BASE + "_door_marble", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.doorMarble), "dcs_climate", "dcs_door_marble", "build", 0, true);

			MainInit.itemDoorMarble = new ItemDoorDC(MainInit.doorMarble)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_door_marble_item");
			MainInit.itemDoorMarble.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_door_marble_item");
			ForgeRegistries.ITEMS.register(MainInit.itemDoorMarble);

			MainInit.doorSteel = new BlockDoorDC(ClimateCore.PACKAGE_BASE + "_door_steel", Material.ROCK);
			DCMaterialReg
					.registerBlock(MainInit.doorSteel, ClimateCore.PACKAGE_BASE + "_door_steel", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.doorSteel), "dcs_climate", "dcs_door_steel", "build", 0, true);

			MainInit.itemDoorSteel = new ItemDoorDC(MainInit.doorSteel)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_door_steel_item");
			MainInit.itemDoorSteel.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_door_steel_item");
			ForgeRegistries.ITEMS.register(MainInit.itemDoorSteel);

			MainInit.bed = new BlockBedDC(ClimateCore.PACKAGE_BASE + "_bed");
			DCMaterialReg.registerBlock(MainInit.bed, ClimateCore.PACKAGE_BASE + "_bed", ClimateMain.MOD_ID);

			MainInit.bedWhite = new BlockBedDCWhite(ClimateCore.PACKAGE_BASE + "_bed_white");
			DCMaterialReg.registerBlock(MainInit.bedWhite, ClimateCore.PACKAGE_BASE + "_bed_white", ClimateMain.MOD_ID);

			MainInit.bedRattan = new BlockBedDCRattan(ClimateCore.PACKAGE_BASE + "_bed_rattan");
			DCMaterialReg
					.registerBlock(MainInit.bedRattan, ClimateCore.PACKAGE_BASE + "_bed_rattan", ClimateMain.MOD_ID);

			MainInit.bedFuton = new BlockBedDCFuton(ClimateCore.PACKAGE_BASE + "_bed_futon");
			DCMaterialReg.registerBlock(MainInit.bedFuton, ClimateCore.PACKAGE_BASE + "_bed_futon", ClimateMain.MOD_ID);

			MainInit.bedHammock = new BlockBedDCHammock(ClimateCore.PACKAGE_BASE + "_bed_hammock");
			DCMaterialReg
					.registerBlock(MainInit.bedHammock, ClimateCore.PACKAGE_BASE + "_bed_hammock", ClimateMain.MOD_ID);

			MainInit.itemBed = new ItemBedDC((BlockBedDC) MainInit.bed)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_bed_item");
			MainInit.itemBed.setRegistryName(ClimateMain.MOD_ID, ClimateCore.PACKAGE_BASE + "_bed_item");
			ForgeRegistries.ITEMS.register(MainInit.itemBed);

			MainInit.realtimeClock = new BlockRealtimeClock(Material.GROUND,
					ClimateCore.PACKAGE_BASE + "_device_realtimeclock");
			DCMaterialReg
					.registerBlock(MainInit.realtimeClock, ClimateCore.PACKAGE_BASE + "_device_realtimeclock", ClimateMain.MOD_ID);

			MainInit.realtimeClock_L = new BlockRealtimeClock_L(Material.GROUND,
					ClimateCore.PACKAGE_BASE + "_device_realtimeclock_l");
			DCMaterialReg
					.registerBlock(MainInit.realtimeClock_L, ClimateCore.PACKAGE_BASE + "_device_realtimeclock_l", ClimateMain.MOD_ID);

			MainInit.mcClock_L = new BlockMCClock_L(Material.GROUND, ClimateCore.PACKAGE_BASE + "_device_mcclock_l");
			DCMaterialReg
					.registerBlock(MainInit.mcClock_L, ClimateCore.PACKAGE_BASE + "_device_mcclock_l", ClimateMain.MOD_ID);

			MainInit.curtainWhite = new BlockCurtain("dcs_build_curtain").setUnlocalizedName("dcs_build_curtain");
			DCMaterialReg
					.registerBlock(MainInit.curtainWhite, ClimateCore.PACKAGE_BASE + "_build_curtain", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.curtainWhite), "dcs_climate", "dcs_build_curtain", "build", 0, true);

			MainInit.curtainGray = new BlockCurtain("dcs_build_curtain_gray")
					.setUnlocalizedName("dcs_build_curtain_gray");
			DCMaterialReg
					.registerBlock(MainInit.curtainGray, ClimateCore.PACKAGE_BASE + "_build_curtain_gray", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.curtainGray), "dcs_climate", "dcs_build_curtain_gray", "build", 0, true);

			MainInit.curtainRed = new BlockCurtain("dcs_build_curtain_red").setUnlocalizedName("dcs_build_curtain_red");
			DCMaterialReg
					.registerBlock(MainInit.curtainRed, ClimateCore.PACKAGE_BASE + "_build_curtain_red", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.curtainRed), "dcs_climate", "dcs_build_curtain_red", "build", 0, true);

			MainInit.curtainBlue = new BlockCurtain("dcs_build_curtain_blue")
					.setUnlocalizedName("dcs_build_curtain_blue");
			DCMaterialReg
					.registerBlock(MainInit.curtainBlue, ClimateCore.PACKAGE_BASE + "_build_curtain_blue", ClimateMain.MOD_ID);
			ClimateMain.proxy.regBlockJson(Item
					.getItemFromBlock(MainInit.curtainBlue), "dcs_climate", "dcs_build_curtain_blue", "build", 0, true);

			MainInit.chestMetal = new BlockMetalChest(Material.IRON, ClimateCore.PACKAGE_BASE + "_device_chest_metal");
			registerChestBlock(MainInit.chestMetal, ClimateCore.PACKAGE_BASE + "_device_chest_metal", ClimateMain.MOD_ID);

			MainInit.chestMagnet = new BlockMagnetChest(Material.IRON,
					ClimateCore.PACKAGE_BASE + "_device_chest_magnet");
			registerChestBlock(MainInit.chestMagnet, ClimateCore.PACKAGE_BASE + "_device_chest_magnet", ClimateMain.MOD_ID);

			MainInit.chestVillage = new BlockVillageChest(Material.IRON,
					ClimateCore.PACKAGE_BASE + "_device_chest_village");
			registerChestBlock(MainInit.chestVillage, ClimateCore.PACKAGE_BASE + "_device_chest_village", ClimateMain.MOD_ID);

			// items
			MainInit.flowerPot = new ItemFlowerPot().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_flowerpot");
			DCMaterialReg.registerItem(MainInit.flowerPot, ClimateCore.PACKAGE_BASE + "_flowerpot", ClimateMain.MOD_ID);

			MainInit.cushionGray = new ItemCushionGray().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_big_cushion");
			DCMaterialReg
					.registerItem(MainInit.cushionGray, ClimateCore.PACKAGE_BASE + "_big_cushion", ClimateMain.MOD_ID);

		}
	}

	static void registerModuleEquip() {
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
				"mangalloy" };
			for (int j = 0; j < 10; j++) {
				// DCLogger.debugLog(j + "/" + DCToolMaterial.getToolMaterial(j).toString());
				MainInit.dcAxe[j] = new ItemAxeDC(DCToolMaterial.getToolMaterial(j), name[j])
						.setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_axe_" + name[j]);
				DCMaterialReg
						.registerItem(MainInit.dcAxe[j], ClimateCore.PACKAGE_BASE + "_axe_" + name[j], ClimateMain.MOD_ID);
			}

			for (int j = 0; j < 10; j++) {
				MainInit.dcPickaxe[j] = new ItemPickaxeDC(DCToolMaterial.getToolMaterial(j), name[j])
						.setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_pickaxe_" + name[j]);
				DCMaterialReg
						.registerItem(MainInit.dcPickaxe[j], ClimateCore.PACKAGE_BASE + "_pickaxe_" + name[j], ClimateMain.MOD_ID);
			}

			for (int j = 0; j < 10; j++) {
				MainInit.dcSpade[j] = new ItemSpadeDC(DCToolMaterial.getToolMaterial(j), name[j])
						.setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_spade_" + name[j]);
				DCMaterialReg
						.registerItem(MainInit.dcSpade[j], ClimateCore.PACKAGE_BASE + "_spade_" + name[j], ClimateMain.MOD_ID);
			}

			for (int j = 0; j < 4; j++) {
				MainInit.dcSword[j] = new ItemSwordDC(DCToolMaterial.getToolMaterial(j), name[j], false)
						.setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_sword_" + name[j]);
				DCMaterialReg
						.registerItem(MainInit.dcSword[j], ClimateCore.PACKAGE_BASE + "_sword_" + name[j], ClimateMain.MOD_ID);
			}
			for (int j = 4; j < 6; j++) {
				MainInit.dcSword[j] = new ItemSwordDC(DCToolMaterial.getToolMaterial(j), name[j], true)
						.setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_sword_" + name[j]);
				DCMaterialReg
						.registerItem(MainInit.dcSword[j], ClimateCore.PACKAGE_BASE + "_sword_" + name[j], ClimateMain.MOD_ID);
			}

			MainInit.dcSword[6] = new ItemSwordDC(DCToolMaterial.getToolMaterial(6), name[6], false)
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_sword_" + name[6]);
			DCMaterialReg
					.registerItem(MainInit.dcSword[6], ClimateCore.PACKAGE_BASE + "_sword_" + name[6], ClimateMain.MOD_ID);

			MainInit.dcSword[7] = new ItemSwordDC(DCToolMaterial.getToolMaterial(7), name[7], true)
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_sword_" + name[7]);
			DCMaterialReg
					.registerItem(MainInit.dcSword[7], ClimateCore.PACKAGE_BASE + "_sword_" + name[7], ClimateMain.MOD_ID);

			MainInit.dcSword[8] = new ItemSwordDC(DCToolMaterial.getToolMaterial(8), name[8], true).setToolsteel()
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_sword_" + name[8]);
			DCMaterialReg
					.registerItem(MainInit.dcSword[8], ClimateCore.PACKAGE_BASE + "_sword_" + name[8], ClimateMain.MOD_ID);

			MainInit.dcScythe[0] = new ItemScytheDC(DCToolMaterial.getToolMaterial(0), "brass")
					.setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_scythe_brass");
			DCMaterialReg
					.registerItem(MainInit.dcScythe[0], ClimateCore.PACKAGE_BASE + "_scythe_brass", ClimateMain.MOD_ID);

			MainInit.dcScythe[1] = new ItemScytheDC(DCToolMaterial.getToolMaterial(1), "steel")
					.setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_scythe_steel");
			DCMaterialReg
					.registerItem(MainInit.dcScythe[1], ClimateCore.PACKAGE_BASE + "_scythe_steel", ClimateMain.MOD_ID);

			MainInit.dcScythe[2] = new ItemScytheDC(DCToolMaterial.getToolMaterial(4), "chalcedony")
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_scythe_chalcedony");
			DCMaterialReg
					.registerItem(MainInit.dcScythe[2], ClimateCore.PACKAGE_BASE + "_scythe_chalcedony", ClimateMain.MOD_ID);

			MainInit.dcScythe[3] = new ItemScytheDC(DCToolMaterial.getToolMaterial(7), "garnet")
					.setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_scythe_garnet");
			DCMaterialReg
					.registerItem(MainInit.dcScythe[3], ClimateCore.PACKAGE_BASE + "_scythe_garnet", ClimateMain.MOD_ID);

			MainInit.dcScythe[4] = new ItemScytheDC(ToolMaterial.STONE, "stone").setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_scythe_stone");
			DCMaterialReg
					.registerItem(MainInit.dcScythe[4], ClimateCore.PACKAGE_BASE + "_scythe_stone", ClimateMain.MOD_ID);

			MainInit.dcScythe[5] = new ItemScytheDC(DCToolMaterial.getToolMaterial(8), "toolsteel")
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_scythe_toolsteel");
			DCMaterialReg
					.registerItem(MainInit.dcScythe[5], ClimateCore.PACKAGE_BASE + "_scythe_toolsteel", ClimateMain.MOD_ID);

			MainInit.earthSpade = new ItemSpadeEarth().setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_spade_earth");
			DCMaterialReg
					.registerItem(MainInit.earthSpade, ClimateCore.PACKAGE_BASE + "_spade_earth", ClimateMain.MOD_ID);

			MainInit.earthRake = new ItemRakeEarth().setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_rake_earth");
			DCMaterialReg
					.registerItem(MainInit.earthRake, ClimateCore.PACKAGE_BASE + "_rake_earth", ClimateMain.MOD_ID);

			MainInit.toolsteelRake = new ItemHoeDC(DCToolMaterial.getToolMaterial(8), "toolsteel")
					.setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_rake_toolsteel");
			DCMaterialReg
					.registerItem(MainInit.toolsteelRake, ClimateCore.PACKAGE_BASE + "_rake_toolsteel", ClimateMain.MOD_ID);

			MainInit.shieldSynthetic = new ItemDCShield(DCToolMaterial.getToolMaterial(5), "synthetic")
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_shield_synthetic");
			DCMaterialReg
					.registerItem(MainInit.shieldSynthetic, ClimateCore.PACKAGE_BASE + "_shield_synthetic", ClimateMain.MOD_ID);

			MainInit.shieldTitanium = new ItemDCShield(DCToolMaterial.getToolMaterial(6), "titanium")
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_shield_titanium");
			DCMaterialReg
					.registerItem(MainInit.shieldTitanium, ClimateCore.PACKAGE_BASE + "_shield_titanium", ClimateMain.MOD_ID);

			MainInit.handNet = new ItemHandNet().setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_handnet");
			DCMaterialReg.registerItem(MainInit.handNet, ClimateCore.PACKAGE_BASE + "_handnet", ClimateMain.MOD_ID);

		}

		if (ModuleConfig.weapon_advanced) {
			MainInit.crossbow = new ItemCrossbowDC().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_crossbow");
			DCMaterialReg.registerItem(MainInit.crossbow, ClimateCore.PACKAGE_BASE + "_crossbow", ClimateMain.MOD_ID);

			MainInit.gun = new ItemMusketDC().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_musket");
			DCMaterialReg.registerItem(MainInit.gun, ClimateCore.PACKAGE_BASE + "_musket", ClimateMain.MOD_ID);

			MainInit.cartridge = new ItemBullets(7).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_cartridge");
			DCMaterialReg.registerItem(MainInit.cartridge, ClimateCore.PACKAGE_BASE + "_cartridge", ClimateMain.MOD_ID);

			MainInit.throwingArrow = new ItemThrowingArrow()
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_throwing_arrow");
			DCMaterialReg
					.registerItem(MainInit.throwingArrow, ClimateCore.PACKAGE_BASE + "_throwing_arrow", ClimateMain.MOD_ID);
		}

		if (ModuleConfig.tool) {
			String[] type = { "met", "plate", "leggins", "boots" };
			for (int i = 0; i < 4; i++) {
				EntityEquipmentSlot slot = DCUtil.SLOTS[i];
				MainInit.brassArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_BRASS, DCMaterialEnum.BRASS, slot, "brass")
						.setCreativeTab(ClimateMain.cloth)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_brass");
				DCMaterialReg
						.registerItem(MainInit.brassArmor[i], ClimateCore.PACKAGE_BASE + "_" + type[i] + "_brass", ClimateMain.MOD_ID);

				MainInit.steelArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_STEEL, DCMaterialEnum.STEEL, slot, "steel")
						.setCreativeTab(ClimateMain.cloth)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_steel");
				DCMaterialReg
						.registerItem(MainInit.steelArmor[i], ClimateCore.PACKAGE_BASE + "_" + type[i] + "_steel", ClimateMain.MOD_ID);

				MainInit.chalcArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_CHALCEDONY, DCMaterialEnum.CHALCEDONY, slot,
						"chalcedony").setCreativeTab(ClimateMain.cloth)
								.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_chalcedony");
				DCMaterialReg
						.registerItem(MainInit.chalcArmor[i], ClimateCore.PACKAGE_BASE + "_" + type[i] + "_chalcedony", ClimateMain.MOD_ID);

				MainInit.sapphireArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_SAPPHIRE, DCMaterialEnum.SAPPHIRE, slot,
						"sapphire").setCreativeTab(ClimateMain.cloth)
								.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_sapphire");
				DCMaterialReg
						.registerItem(MainInit.sapphireArmor[i], ClimateCore.PACKAGE_BASE + "_" + type[i] + "_sapphire", ClimateMain.MOD_ID);

				MainInit.titaniumArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_TITANIUM, DCMaterialEnum.TITANIUM, slot,
						"titanium").setCreativeTab(ClimateMain.cloth)
								.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_titanium");
				DCMaterialReg
						.registerItem(MainInit.titaniumArmor[i], ClimateCore.PACKAGE_BASE + "_" + type[i] + "_titanium", ClimateMain.MOD_ID);

				MainInit.silverArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_SILVER, DCMaterialEnum.SILVER, slot,
						"silver").setCreativeTab(ClimateMain.cloth)
								.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_silver");
				DCMaterialReg
						.registerItem(MainInit.silverArmor[i], ClimateCore.PACKAGE_BASE + "_" + type[i] + "_silver", ClimateMain.MOD_ID);
			}

		}

		// clothes
		MainInit.linenUnder = new ItemArmorDC(DCArmorMaterial.DC_LINEN, DCMaterialEnum.LINEN, EntityEquipmentSlot.LEGS,
				"leggins_linen").setColorList(EnumDyeColor.BROWN, EnumDyeColor.BLACK).setCreativeTab(ClimateMain.cloth)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_linen");
		DCMaterialReg
				.registerItem(MainInit.linenUnder, ClimateCore.PACKAGE_BASE + "_leggins_linen", ClimateMain.MOD_ID);

		MainInit.linenCoat = new ItemArmorDC(DCArmorMaterial.DC_LINEN, DCMaterialEnum.LINEN, EntityEquipmentSlot.CHEST,
				"coat_linen").setCreativeTab(ClimateMain.cloth)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_coat_linen");
		DCMaterialReg.registerItem(MainInit.linenCoat, ClimateCore.PACKAGE_BASE + "_coat_linen", ClimateMain.MOD_ID);

		if (ModuleConfig.clothes_advanced) {

			MainInit.linenShirt = new ItemArmorShirt(DCArmorMaterial.DC_LINEN, DCMaterialEnum.LINEN,
					EntityEquipmentSlot.LEGS, "shirt_linen").setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_shirt_linen");
			DCMaterialReg
					.registerItem(MainInit.linenShirt, ClimateCore.PACKAGE_BASE + "_shirt_linen", ClimateMain.MOD_ID);

			MainInit.linenJacket = new ItemArmorJacket(DCArmorMaterial.DC_LINEN, DCMaterialEnum.LINEN,
					EntityEquipmentSlot.CHEST, "jacket_linen").setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_jacket_linen");
			DCMaterialReg
					.registerItem(MainInit.linenJacket, ClimateCore.PACKAGE_BASE + "_jacket_linen", ClimateMain.MOD_ID);

			MainInit.linenBottom = new ItemArmorPants(DCArmorMaterial.DC_LINEN, DCMaterialEnum.LINEN,
					EntityEquipmentSlot.FEET, "pants_linen")
							.setColorList(EnumDyeColor.ORANGE, EnumDyeColor.GRAY, EnumDyeColor.SILVER, EnumDyeColor.GREEN, EnumDyeColor.BLACK)
							.setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_pants_linen");
			DCMaterialReg
					.registerItem(MainInit.linenBottom, ClimateCore.PACKAGE_BASE + "_pants_linen", ClimateMain.MOD_ID);

			MainInit.flowerSkirt = new ItemArmorSkirt(DCArmorMaterial.DC_LINEN, DCMaterialEnum.LINEN,
					EntityEquipmentSlot.FEET, "skirt_linen").setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_skirt_linen");
			DCMaterialReg
					.registerItem(MainInit.flowerSkirt, ClimateCore.PACKAGE_BASE + "_skirt_linen", ClimateMain.MOD_ID);

		}

		// cotton
		MainInit.clothUnder = new ItemArmorDC(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH, EntityEquipmentSlot.LEGS,
				"leggins_cloth").setColorList(EnumDyeColor.BLUE, EnumDyeColor.GREEN).setCreativeTab(ClimateMain.cloth)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_cloth");
		DCMaterialReg
				.registerItem(MainInit.clothUnder, ClimateCore.PACKAGE_BASE + "_leggins_cloth", ClimateMain.MOD_ID);

		MainInit.clothCoat = new ItemArmorOvercoat(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH,
				EntityEquipmentSlot.CHEST, "trench").setCreativeTab(ClimateMain.cloth)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_coat_trench");
		DCMaterialReg.registerItem(MainInit.clothCoat, ClimateCore.PACKAGE_BASE + "_coat_trench", ClimateMain.MOD_ID);

		if (ModuleConfig.clothes_advanced) {

			MainInit.clothShirt = new ItemArmorShirt(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH,
					EntityEquipmentSlot.LEGS, "shirt_cloth")
							.setColorList(EnumDyeColor.LIGHT_BLUE, EnumDyeColor.PINK, EnumDyeColor.PURPLE, EnumDyeColor.RED, EnumDyeColor.BLACK)
							.setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_shirt_cloth");
			DCMaterialReg
					.registerItem(MainInit.clothShirt, ClimateCore.PACKAGE_BASE + "_shirt_cloth", ClimateMain.MOD_ID);

			MainInit.clothJacket = new ItemArmorJacket(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH,
					EntityEquipmentSlot.CHEST, "jacket_cloth")
							.setColorList(EnumDyeColor.GRAY, EnumDyeColor.BLUE, EnumDyeColor.BLACK)
							.setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_jacket_cloth");
			DCMaterialReg
					.registerItem(MainInit.clothJacket, ClimateCore.PACKAGE_BASE + "_jacket_cloth", ClimateMain.MOD_ID);

			MainInit.hoodie = new ItemArmorHoodie(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH,
					EntityEquipmentSlot.CHEST, "hoodie")
							.setColorList(EnumDyeColor.GRAY, EnumDyeColor.RED, EnumDyeColor.BLACK)
							.setCreativeTab(ClimateMain.cloth).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_hoodie");
			DCMaterialReg.registerItem(MainInit.hoodie, ClimateCore.PACKAGE_BASE + "_hoodie_white", ClimateMain.MOD_ID);

			MainInit.clothBottom = new ItemArmorPants(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH,
					EntityEquipmentSlot.FEET, "pants_cloth").setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_pants_cloth");
			DCMaterialReg
					.registerItem(MainInit.clothBottom, ClimateCore.PACKAGE_BASE + "_pants_cloth", ClimateMain.MOD_ID);

			MainInit.clothSkirt = new ItemArmorSkirt(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH,
					EntityEquipmentSlot.FEET, "skirt_cloth")
							.setColorList(EnumDyeColor.ORANGE, EnumDyeColor.CYAN, EnumDyeColor.PURPLE, EnumDyeColor.BLUE, EnumDyeColor.BROWN, EnumDyeColor.RED, EnumDyeColor.BLACK)
							.setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_skirt_cloth");
			DCMaterialReg
					.registerItem(MainInit.clothSkirt, ClimateCore.PACKAGE_BASE + "_skirt_cloth", ClimateMain.MOD_ID);

			// worker
			MainInit.workerSuit = new ItemArmorDC(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH,
					EntityEquipmentSlot.LEGS, "leggins_worker").setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_worker");
			DCMaterialReg
					.registerItem(MainInit.workerSuit, ClimateCore.PACKAGE_BASE + "_leggins_worker", ClimateMain.MOD_ID);

		}

		MainInit.cottonHat = new ItemArmorHat(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH, EntityEquipmentSlot.HEAD,
				"cotton").setCreativeTab(ClimateMain.cloth)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_hat_cotton");
		DCMaterialReg.registerItem(MainInit.cottonHat, ClimateCore.PACKAGE_BASE + "_hat_cotton", ClimateMain.MOD_ID);

		// silk
		MainInit.blackSuit = new ItemArmorDC(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH, EntityEquipmentSlot.LEGS,
				"leggins_suit")
						.setColorList(EnumDyeColor.YELLOW, EnumDyeColor.SILVER, EnumDyeColor.PURPLE, EnumDyeColor.BLUE, EnumDyeColor.BLACK)
						.setCreativeTab(ClimateMain.cloth)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_suit");
		DCMaterialReg.registerItem(MainInit.blackSuit, ClimateCore.PACKAGE_BASE + "_leggins_suit", ClimateMain.MOD_ID);

		MainInit.blackCoat = new ItemArmorOvercoat(DCArmorMaterial.DC_SYNTHETIC, DCMaterialEnum.SYNTHETIC,
				EntityEquipmentSlot.CHEST, "black").setShort().setCreativeTab(ClimateMain.cloth)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_coat_black");
		DCMaterialReg.registerItem(MainInit.blackCoat, ClimateCore.PACKAGE_BASE + "_coat_black", ClimateMain.MOD_ID);

		if (ModuleConfig.clothes_advanced) {
			// dress
			MainInit.silkDress = new ItemArmorDress(DCArmorMaterial.DC_SYNTHETIC, DCMaterialEnum.SYNTHETIC,
					EntityEquipmentSlot.LEGS, "dress_silk").setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_dress_silk");
			DCMaterialReg
					.registerItem(MainInit.silkDress, ClimateCore.PACKAGE_BASE + "_dress_silk", ClimateMain.MOD_ID);

			MainInit.silkCape = new ItemArmorCape(DCArmorMaterial.DC_SYNTHETIC, DCMaterialEnum.SYNTHETIC,
					EntityEquipmentSlot.HEAD, "cape_silk")
							.setColorList(EnumDyeColor.YELLOW, EnumDyeColor.RED, EnumDyeColor.BLACK)
							.setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_cape_silk");
			DCMaterialReg.registerItem(MainInit.silkCape, ClimateCore.PACKAGE_BASE + "_cape_silk", ClimateMain.MOD_ID);

			// wool
			MainInit.peaCoat = new ItemArmorHoodie(DCArmorMaterial.DC_WOOL, DCMaterialEnum.WOOL,
					EntityEquipmentSlot.CHEST, "hoodie_pea").setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_peacoat_black");
			DCMaterialReg.registerItem(MainInit.peaCoat, ClimateCore.PACKAGE_BASE + "_hoodie_pea", ClimateMain.MOD_ID);

			MainInit.modsCoat = new ItemArmorHoodie(DCArmorMaterial.DC_WOOL, DCMaterialEnum.WOOL,
					EntityEquipmentSlot.CHEST, "hoodie_mods").setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_hoodie_mods");
			DCMaterialReg
					.registerItem(MainInit.modsCoat, ClimateCore.PACKAGE_BASE + "_hoodie_mods", ClimateMain.MOD_ID);

			MainInit.woolJacket = new ItemArmorJacket(DCArmorMaterial.DC_WOOL, DCMaterialEnum.WOOL,
					EntityEquipmentSlot.CHEST, "jacket_wool")
							.setColorList(EnumDyeColor.ORANGE, EnumDyeColor.LIGHT_BLUE, EnumDyeColor.CYAN, EnumDyeColor.RED)
							.setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_jacket_wool");
			DCMaterialReg
					.registerItem(MainInit.woolJacket, ClimateCore.PACKAGE_BASE + "_jacket_wool", ClimateMain.MOD_ID);

			MainInit.furCape = new ItemArmorCape(DCArmorMaterial.DC_WOOL, DCMaterialEnum.WOOL, EntityEquipmentSlot.HEAD,
					"cape_fur").setColorList(EnumDyeColor.BLUE, EnumDyeColor.BROWN, EnumDyeColor.BLACK)
							.setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_cape_fur");
			DCMaterialReg.registerItem(MainInit.furCape, ClimateCore.PACKAGE_BASE + "_cape_fur", ClimateMain.MOD_ID);

		}

		MainInit.woolWear = new ItemArmorWool(DCArmorMaterial.DC_WOOL, DCMaterialEnum.WOOL, EntityEquipmentSlot.HEAD,
				"wool").setCreativeTab(ClimateMain.cloth).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_wear_wool");
		DCMaterialReg.registerItem(MainInit.woolWear, ClimateCore.PACKAGE_BASE + "_wear_wool", ClimateMain.MOD_ID);

		MainInit.furWear = new ItemArmorWool(DCArmorMaterial.DC_WOOL, DCMaterialEnum.WOOL, EntityEquipmentSlot.HEAD,
				"fur").setCreativeTab(ClimateMain.cloth).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_wear_fur");
		DCMaterialReg.registerItem(MainInit.furWear, ClimateCore.PACKAGE_BASE + "_wear_fur", ClimateMain.MOD_ID);

		MainInit.woolBoots = new ItemArmorWool(DCArmorMaterial.DC_WOOL, DCMaterialEnum.WOOL, EntityEquipmentSlot.FEET,
				"boots").setCreativeTab(ClimateMain.cloth).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_boots_wool");
		DCMaterialReg.registerItem(MainInit.woolBoots, ClimateCore.PACKAGE_BASE + "_boots_wool", ClimateMain.MOD_ID);

		// synthetic
		if (ModuleConfig.clothes_advanced && ModuleConfig.machine) {

			MainInit.trackSuit = new ItemArmorDC(DCArmorMaterial.DC_SYNTHETIC, DCMaterialEnum.SYNTHETIC,
					EntityEquipmentSlot.LEGS, "leggins_tracksuit")
							.setColorList(EnumDyeColor.PINK, EnumDyeColor.BLUE, EnumDyeColor.RED)
							.setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_track");
			DCMaterialReg
					.registerItem(MainInit.trackSuit, ClimateCore.PACKAGE_BASE + "_leggins_track", ClimateMain.MOD_ID);

			MainInit.combatDress = new ItemArmorDC(DCArmorMaterial.DC_SYNTHETIC, DCMaterialEnum.SYNTHETIC,
					EntityEquipmentSlot.LEGS, "leggins_combatdress")
							.setColorList(EnumDyeColor.ORANGE, EnumDyeColor.BLUE).setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_combatdress");
			DCMaterialReg
					.registerItem(MainInit.combatDress, ClimateCore.PACKAGE_BASE + "_leggins_combatdress", ClimateMain.MOD_ID);

		}

		// magic

		if (ModuleConfig.clothes_advanced && ModuleConfig.magic) {

			MainInit.magicUnder = new ItemArmorDC(DCArmorMaterial.DC_SYNTHETIC, DCMaterialEnum.SYNTHETIC,
					EntityEquipmentSlot.LEGS, "leggins_magic").setColorList(EnumDyeColor.BROWN, EnumDyeColor.BLACK)
							.setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_magic");
			DCMaterialReg
					.registerItem(MainInit.magicUnder, ClimateCore.PACKAGE_BASE + "_leggins_magic", ClimateMain.MOD_ID);

			MainInit.magicCoat = new ItemArmorOvercoat(DCArmorMaterial.DC_SYNTHETIC, DCMaterialEnum.SYNTHETIC,
					EntityEquipmentSlot.CHEST, "magic").setColorList(EnumDyeColor.BROWN, EnumDyeColor.BLACK)
							.setCreativeTab(ClimateMain.cloth)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_coat_magic");
			DCMaterialReg
					.registerItem(MainInit.magicCoat, ClimateCore.PACKAGE_BASE + "_coat_magic", ClimateMain.MOD_ID);

		}

		// other
		MainInit.leatherHat = new ItemArmorHat(ArmorMaterial.LEATHER, DCMaterialEnum.LINEN, EntityEquipmentSlot.HEAD,
				"leather").setCreativeTab(ClimateMain.cloth)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_hat_leather");
		DCMaterialReg.registerItem(MainInit.leatherHat, ClimateCore.PACKAGE_BASE + "_hat_leather", ClimateMain.MOD_ID);

	}

	static void registerModuleContainer() {
		MainInit.logCont = new BlockLogCont(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_log", 6);
		int[] f = { 1600, 1600, 1600, 1600, 1600, 1600, 14400 };
		registerBlock(MainInit.logCont, ClimateCore.PACKAGE_BASE + "_cont_log", ClimateMain.MOD_ID, f);
		ClimateMain.proxy.addSidedBlock(MainInit.logCont, "cont_log", 6);

		MainInit.cropCont = new BlockCropCont(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_crop", 11);
		DCMaterialReg.registerBlock(MainInit.cropCont, ClimateCore.PACKAGE_BASE + "_cont_crop", ClimateMain.MOD_ID);
		ClimateMain.proxy.addSidedBlock(MainInit.cropCont, "cont_crop", 11);

		MainInit.dropCont = new BlockEnemyCont(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_metal", 5);
		DCMaterialReg.registerBlock(MainInit.dropCont, ClimateCore.PACKAGE_BASE + "_cont_metal", ClimateMain.MOD_ID);
		ClimateMain.proxy.addSidedBlock(MainInit.dropCont, "cont_metal", 5);

		MainInit.miscCont = new BlockMiscCont(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_misc", 4);
		DCMaterialReg.registerBlock(MainInit.miscCont, ClimateCore.PACKAGE_BASE + "_cont_misc", ClimateMain.MOD_ID);
		ClimateMain.proxy.addSidedBlock(MainInit.miscCont, "cont_misc", 4);

		MainInit.cardboard = new BlockCardboard(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_cardboard", 5);
		DCMaterialReg
				.registerBlock(MainInit.cardboard, ClimateCore.PACKAGE_BASE + "_cont_cardboard", ClimateMain.MOD_ID);
		ClimateMain.proxy.addSidedBlock(MainInit.cardboard, "cont_cardboard", 5);

		MainInit.dustBags = new BlockDustBag(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_bags", 7);
		DCMaterialReg.registerBlock(MainInit.dustBags, ClimateCore.PACKAGE_BASE + "_cont_bags", ClimateMain.MOD_ID);
		ClimateMain.proxy.addSidedBlock(MainInit.dustBags, "cont_bags", 7);

		MainInit.cropBasket = new BlockCropBasket(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_basket", 14);
		DCMaterialReg.registerBlock(MainInit.cropBasket, ClimateCore.PACKAGE_BASE + "_cont_basket", ClimateMain.MOD_ID);
		ClimateMain.proxy.addSidedBlock(MainInit.cropBasket, "cont_basket", 14);

		MainInit.cropJute = new BlockCropJutebag(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_jutebag", 5);
		DCMaterialReg.registerBlock(MainInit.cropJute, ClimateCore.PACKAGE_BASE + "_cont_jutebag", ClimateMain.MOD_ID);
		ClimateMain.proxy.addSidedBlock(MainInit.cropJute, "cont_jutebag", 5);

		MainInit.dustCake = new BlockMiscCake(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_cake", 5);
		DCMaterialReg.registerBlock(MainInit.dustCake, ClimateCore.PACKAGE_BASE + "_cont_cake", ClimateMain.MOD_ID);
		ClimateMain.proxy.addSidedBlock(MainInit.dustCake, "cont_cake", 5);

	}

	public static void registerFluids() {
		// fluid
		MainInit.oil = new Fluid("dcs.seed_oil", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/seedoil_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/seedoil_still")).setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".seed_oil");
		FluidRegistry.registerFluid(MainInit.oil);
		MainInit.oilBlock = new DCFluidBlockBase(MainInit.oil, "seedoil_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_oil");
		DCMaterialReg
				.registerBlock(MainInit.oilBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_oil", ClimateMain.MOD_ID);
		MainInit.oil.setBlock(MainInit.oilBlock);

		MainInit.greenTea = new Fluid("dcs.green_tea", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/greentea_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/greentea_still")).setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".green_tea")
								.setTemperature(350);
		FluidRegistry.registerFluid(MainInit.greenTea);
		MainInit.greenTeaBlock = new DCFluidBlockBase(MainInit.greenTea, "greentea_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_greentea");
		DCMaterialReg
				.registerBlock(MainInit.greenTeaBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_greentea", ClimateMain.MOD_ID);
		MainInit.greenTea.setBlock(MainInit.greenTeaBlock);

		MainInit.blackTea = new Fluid("dcs.black_tea", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/blacktea_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/blacktea_still")).setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".black_tea")
								.setTemperature(350);
		FluidRegistry.registerFluid(MainInit.blackTea);
		MainInit.blackTeaBlock = new DCFluidBlockBase(MainInit.blackTea, "blacktea_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_blacktea");
		DCMaterialReg
				.registerBlock(MainInit.blackTeaBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_blacktea", ClimateMain.MOD_ID);
		MainInit.blackTea.setBlock(MainInit.blackTeaBlock);

		MainInit.coffee = new Fluid("dcs.black_coffee", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/coffee_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/coffee_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".black_coffee").setTemperature(350);
		FluidRegistry.registerFluid(MainInit.coffee);
		MainInit.coffeeBlock = new DCFluidBlockBase(MainInit.coffee, "coffee_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_coffee");
		DCMaterialReg
				.registerBlock(MainInit.coffeeBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_coffee", ClimateMain.MOD_ID);
		MainInit.coffee.setBlock(MainInit.coffeeBlock);

		MainInit.cream = new Fluid("dcs.milk_cream", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/cream_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/cream_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".milk_cream");
		FluidRegistry.registerFluid(MainInit.cream);
		MainInit.creamBlock = new DCFluidBlockBase(MainInit.cream, "cream_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_cream");
		DCMaterialReg
				.registerBlock(MainInit.creamBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_cream", ClimateMain.MOD_ID);
		MainInit.cream.setBlock(MainInit.creamBlock);

		MainInit.tomatoJuice = new Fluid("dcs.vegetable_juice", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/vegetable_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/vegetable_still"))
								.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".vegetable_juice");
		FluidRegistry.registerFluid(MainInit.tomatoJuice);
		MainInit.tomatoBlock = new DCFluidBlockBase(MainInit.tomatoJuice, "vegetable_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_vegetable");
		DCMaterialReg
				.registerBlock(MainInit.tomatoBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_vegetable", ClimateMain.MOD_ID);
		MainInit.tomatoJuice.setBlock(MainInit.tomatoBlock);

		MainInit.stock = new Fluid("dcs.stock", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/stock_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/stock_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".stock").setTemperature(350);
		FluidRegistry.registerFluid(MainInit.stock);
		MainInit.stockBlock = new DCFluidBlockBase(MainInit.stock, "stock_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_stock");
		DCMaterialReg
				.registerBlock(MainInit.stockBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_stock", ClimateMain.MOD_ID);
		MainInit.stock.setBlock(MainInit.stockBlock);

		MainInit.lemon = new Fluid("dcs.lemonade", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/lemon_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/lemon_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".lemonade");
		FluidRegistry.registerFluid(MainInit.lemon);
		MainInit.lemonBlock = new DCFluidBlockBase(MainInit.lemon, "lemon_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_lemonade");
		DCMaterialReg
				.registerBlock(MainInit.lemonBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_lemonade", ClimateMain.MOD_ID);
		MainInit.lemon.setBlock(MainInit.lemonBlock);

		MainInit.blackLiquor = new Fluid("dcs.black_liquor", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/black_liquor_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/black_liquor_still"))
								.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".black_liquor");
		FluidRegistry.registerFluid(MainInit.blackLiquor);
		MainInit.blackLiquorBlock = new DCFluidBlockBase(MainInit.blackLiquor, "black_liquor_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_black_liquor");
		DCMaterialReg
				.registerBlock(MainInit.blackLiquorBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_black_liquor", ClimateMain.MOD_ID);
		MainInit.blackLiquor.setBlock(MainInit.blackLiquorBlock);

		MainInit.hotSpring = new Fluid("dcs.hotspring", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/hotspring_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/hotspring_still")).setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".hotspring")
								.setTemperature(350);
		FluidRegistry.registerFluid(MainInit.hotSpring);
		MainInit.hotSpringBlock = new DCFluidBlockBase(MainInit.hotSpring, "hotspring_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_hotspring");
		DCMaterialReg
				.registerBlock(MainInit.hotSpringBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_hotspring", ClimateMain.MOD_ID);
		MainInit.hotSpring.setBlock(MainInit.hotSpringBlock);

		MainInit.mazai = new Fluid("dcs.mazai", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/mazai_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/mazai_still")) {
			@Override
			public boolean doesVaporize(FluidStack fluid) {
				return false;
			}
		}.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".mazai").setLuminosity(15).setViscosity(1200).setDensity(1200)
				.setTemperature(270);
		FluidRegistry.registerFluid(MainInit.mazai);
		MainInit.mazaiBlock = new DCFluidBlockBase(MainInit.mazai, "mazai_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_mazai");
		DCMaterialReg
				.registerBlock(MainInit.mazaiBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_mazai", ClimateMain.MOD_ID);
		MainInit.mazai.setBlock(MainInit.mazaiBlock);

		MainInit.soyMilk = new Fluid("dcs.soy_milk", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/soy_milk_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/soy_milk_still")).setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".soy_milk");
		FluidRegistry.registerFluid(MainInit.soyMilk);
		MainInit.soyMilkBlock = new DCFluidBlockBase(MainInit.soyMilk, "soy_milk_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_soy_milk");
		DCMaterialReg
				.registerBlock(MainInit.soyMilkBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_soy_milk", ClimateMain.MOD_ID);
		MainInit.soyMilk.setBlock(MainInit.soyMilkBlock);

		MainInit.hydrogen = new Fluid("dcs.hydrogen", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/hydrogen_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/hydrogen_still")).setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".hydrogen")
								.setDensity(-1000).setViscosity(300).setGaseous(true);
		FluidRegistry.registerFluid(MainInit.hydrogen);
		MainInit.hydrogenBlock = new DCFluidBlockBase(MainInit.hydrogen, "hydrogen_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_hydrogen");
		DCMaterialReg
				.registerBlock(MainInit.hydrogenBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_hydrogen", ClimateMain.MOD_ID);
		MainInit.hydrogen.setBlock(MainInit.hydrogenBlock);

		MainInit.ammonia = new Fluid("dcs.ammonia", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/ammonia_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/ammonia_still")).setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".ammonia")
								.setDensity(-1000).setViscosity(300).setGaseous(true);
		FluidRegistry.registerFluid(MainInit.ammonia);
		MainInit.ammoniaBlock = new DCFluidBlockBase(MainInit.ammonia, "ammonia_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_ammonia");
		DCMaterialReg
				.registerBlock(MainInit.ammoniaBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_ammonia", ClimateMain.MOD_ID);
		MainInit.ammonia.setBlock(MainInit.ammoniaBlock);

		MainInit.nitricAcid = new Fluid("dcs.nitric_acid", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/nitric_acid_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/nitric_acid_still")).setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".nitric_acid")
								.setDensity(1200).setViscosity(1200);
		FluidRegistry.registerFluid(MainInit.nitricAcid);
		MainInit.nitricAcidBlock = new DCFluidBlockBase(MainInit.nitricAcid, "nitric_acid_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_nitric_acid");
		DCMaterialReg
				.registerBlock(MainInit.nitricAcidBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_nitric_acid", ClimateMain.MOD_ID);
		MainInit.nitricAcid.setBlock(MainInit.nitricAcidBlock);

		MainInit.sulfuricAcid = new Fluid("dcs.sulfuric_acid", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/sulfuric_acid_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/sulfuric_acid_still"))
								.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".sulfuric_acid").setDensity(1200)
								.setViscosity(1200);
		FluidRegistry.registerFluid(MainInit.sulfuricAcid);
		MainInit.sulfuricAcidBlock = new DCFluidBlockBase(MainInit.sulfuricAcid, "sulfuric_acid_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_sulfuric_acid");
		DCMaterialReg
				.registerBlock(MainInit.sulfuricAcidBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_sulfuric_acid", ClimateMain.MOD_ID);
		MainInit.sulfuricAcid.setBlock(MainInit.sulfuricAcidBlock);

		MainInit.fuelGas = new Fluid("dcs.fuel_gas", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/fuel_gas_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/fuel_gas_still")).setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".fuel_gas")
								.setDensity(-500).setViscosity(300).setGaseous(true);
		FluidRegistry.registerFluid(MainInit.fuelGas);
		MainInit.fuelGasBlock = new DCFluidBlockBase(MainInit.fuelGas, "fuel_gas_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_gas");
		DCMaterialReg
				.registerBlock(MainInit.fuelGasBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_gas", ClimateMain.MOD_ID);
		MainInit.fuelGas.setBlock(MainInit.fuelGasBlock);

		MainInit.fuelOil = new Fluid("dcs.fuel_oil", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/fuel_oil_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/fuel_oil_still")).setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".fuel_oil")
								.setDensity(800).setViscosity(1500);
		FluidRegistry.registerFluid(MainInit.fuelOil);
		MainInit.fuelOilBlock = new DCFluidBlockBase(MainInit.fuelOil, "fuel_oil_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_oil");
		DCMaterialReg
				.registerBlock(MainInit.fuelOilBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_fuel_oil", ClimateMain.MOD_ID);
		MainInit.fuelOil.setBlock(MainInit.fuelOilBlock);

		MainInit.nitrogen = new Fluid("dcs.nitrogen", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/nitrogen_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/nitrogen_still")).setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".nitrogen")
								.setDensity(1100).setViscosity(1100).setTemperature(77);
		FluidRegistry.registerFluid(MainInit.nitrogen);
		MainInit.nitrogenBlock = new DCFluidBlockBase(MainInit.nitrogen, "nitrogen_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_nitrogen");
		DCMaterialReg
				.registerBlock(MainInit.nitrogenBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_nitrogen", ClimateMain.MOD_ID);
		MainInit.nitrogen.setBlock(MainInit.nitrogenBlock);

		MainInit.ethanol = new Fluid("dcs.ethanol", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/ethanol_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/ethanol_still")).setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".ethanol");
		FluidRegistry.registerFluid(MainInit.ethanol);
		MainInit.ethanolBlock = new DCFluidBlockBase(MainInit.ethanol, "ethanol_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_ethanol");
		DCMaterialReg
				.registerBlock(MainInit.ethanolBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_ethanol", ClimateMain.MOD_ID);
		MainInit.ethanol.setBlock(MainInit.ethanolBlock);

		MainInit.milk = new Fluid("dcs.raw_milk", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/raw_milk_still"), new ResourceLocation(ClimateCore.PACKAGE_ID,
						"blocks/fluid/raw_milk_still")).setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".raw_milk");
		FluidRegistry.registerFluid(MainInit.milk);
		MainInit.milkBlock = new DCFluidBlockBase(MainInit.milk, "raw_milk_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_raw_milk");
		DCMaterialReg
				.registerBlock(MainInit.milkBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_raw_milk", ClimateMain.MOD_ID);
		MainInit.milk.setBlock(MainInit.milkBlock);

		MainInit.steam = new Fluid("dcs.steam", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/steam_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/steam_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".steam").setDensity(-500).setViscosity(300)
						.setGaseous(true).setTemperature(390);
		FluidRegistry.registerFluid(MainInit.steam);
		MainInit.steamBlock = new DCFluidBlockBase(MainInit.steam, "steam_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_steam");
		DCMaterialReg
				.registerBlock(MainInit.steamBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_steam", ClimateMain.MOD_ID);
		MainInit.steam.setBlock(MainInit.steamBlock);

		MainInit.oxygen = new Fluid("dcs.oxygen", new ResourceLocation(ClimateCore.PACKAGE_ID,
				"blocks/fluid/oxygen_still"), new ResourceLocation(ClimateCore.PACKAGE_ID, "blocks/fluid/oxygen_still"))
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + ".oxygen").setDensity(-500).setViscosity(300)
						.setGaseous(true);
		FluidRegistry.registerFluid(MainInit.oxygen);
		MainInit.oxygenBlock = new DCFluidBlockBase(MainInit.oxygen, "oxygen_still")
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_fluidblock_oxygen");
		DCMaterialReg
				.registerBlock(MainInit.oxygenBlock, ClimateCore.PACKAGE_BASE + "_fluidblock_oxygen", ClimateMain.MOD_ID);
		MainInit.oxygen.setBlock(MainInit.oxygenBlock);

		// bucket
		FluidRegistry.addBucketForFluid(MainInit.oil);
		FluidRegistry.addBucketForFluid(MainInit.greenTea);
		FluidRegistry.addBucketForFluid(MainInit.blackTea);
		FluidRegistry.addBucketForFluid(MainInit.coffee);
		FluidRegistry.addBucketForFluid(MainInit.cream);
		FluidRegistry.addBucketForFluid(MainInit.tomatoJuice);
		FluidRegistry.addBucketForFluid(MainInit.blackLiquor);
		FluidRegistry.addBucketForFluid(MainInit.hotSpring);
		FluidRegistry.addBucketForFluid(MainInit.stock);
		FluidRegistry.addBucketForFluid(MainInit.lemon);
		FluidRegistry.addBucketForFluid(MainInit.mazai);
		FluidRegistry.addBucketForFluid(MainInit.soyMilk);
		FluidRegistry.addBucketForFluid(MainInit.hydrogen);
		FluidRegistry.addBucketForFluid(MainInit.ammonia);
		FluidRegistry.addBucketForFluid(MainInit.nitricAcid);
		FluidRegistry.addBucketForFluid(MainInit.sulfuricAcid);
		FluidRegistry.addBucketForFluid(MainInit.fuelGas);
		FluidRegistry.addBucketForFluid(MainInit.fuelOil);
		FluidRegistry.addBucketForFluid(MainInit.nitrogen);
		FluidRegistry.addBucketForFluid(MainInit.ethanol);
		FluidRegistry.addBucketForFluid(MainInit.milk);
		FluidRegistry.addBucketForFluid(MainInit.steam);
		FluidRegistry.addBucketForFluid(MainInit.oxygen);

		// heat tier
		ClimateAPI.registerBlock.registerHeatBlock(MainInit.coffeeBlock, 0, DCHeatTier.HOT);
		ClimateAPI.registerBlock.registerHumBlock(MainInit.coffeeBlock, 0, DCHumidity.UNDERWATER);
		ClimateAPI.registerBlock.registerHeatBlock(MainInit.blackTeaBlock, 0, DCHeatTier.HOT);
		ClimateAPI.registerBlock.registerHumBlock(MainInit.blackTeaBlock, 0, DCHumidity.UNDERWATER);
		ClimateAPI.registerBlock.registerHeatBlock(MainInit.greenTeaBlock, 0, DCHeatTier.HOT);
		ClimateAPI.registerBlock.registerHumBlock(MainInit.greenTeaBlock, 0, DCHumidity.UNDERWATER);
		ClimateAPI.registerBlock.registerHeatBlock(MainInit.hotSpringBlock, 0, DCHeatTier.HOT);
		ClimateAPI.registerBlock.registerHumBlock(MainInit.hotSpringBlock, 0, DCHumidity.UNDERWATER);
		ClimateAPI.registerBlock.registerHeatBlock(MainInit.stockBlock, 0, DCHeatTier.HOT);
		ClimateAPI.registerBlock.registerHumBlock(MainInit.stockBlock, 0, DCHumidity.UNDERWATER);
		ClimateAPI.registerBlock.registerHumBlock(MainInit.sulfuricAcidBlock, 0, DCHumidity.DRY);
		ClimateAPI.registerBlock.registerHumBlock(MainInit.fuelOilBlock, 0, DCHumidity.NORMAL);
		ClimateAPI.registerBlock.registerHumBlock(MainInit.fuelGasBlock, 0, DCHumidity.NORMAL);
		ClimateAPI.registerBlock.registerHeatBlock(MainInit.nitrogenBlock, 0, DCHeatTier.CRYOGENIC);
		ClimateAPI.registerBlock.registerHumBlock(MainInit.nitrogenBlock, 0, DCHumidity.NORMAL);
		ClimateAPI.registerBlock.registerHeatBlock(MainInit.steamBlock, 0, DCHeatTier.BOIL);
		ClimateAPI.registerBlock.registerHumBlock(MainInit.steamBlock, 0, DCHumidity.WET);
		ClimateAPI.registerBlock.registerAirBlock(MainInit.oxygenBlock, 0, DCAirflow.FLOW);
		ClimateAPI.registerBlock.registerHumBlock(MainInit.oxygenBlock, 0, DCHumidity.NORMAL);

	}

	static void registerIntegration() {

		if (DCIntegrationCore.loadedForestry) {
			MainInit.circuit = new ItemCircuitChal().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_circuit");
			DCMaterialReg.registerItem(MainInit.circuit, ClimateCore.PACKAGE_BASE + "_circuit", ClimateMain.MOD_ID);
		}
		if (DCIntegrationCore.loadedMekanism) {
			MainInit.metalMaterials = new ItemMetalMaterials().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_metal");
			DCMaterialReg
					.registerItem(MainInit.metalMaterials, ClimateCore.PACKAGE_BASE + "_metal", ClimateMain.MOD_ID);
		}

		// 自己連携
		ClimateAPI.registerBlock.registerHumBlock(MainInit.desiccant, 0, DCHumidity.DRY);
		ClimateAPI.registerBlock.registerHeatBlock(MainInit.freezepack, 0, DCHeatTier.COLD);
	}

	static void registerDispense() {
		if (ModuleConfig.build_advanced) {
			DispenseEntityItem.getInstance().dispenceList.add(MainInit.cushionGray);
		}
	}

	static void registerMaterialEnum() {
		MainInit.repairPutty = new ItemRepairPutty().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_repair");
		DCMaterialReg.registerItem(MainInit.repairPutty, ClimateCore.PACKAGE_BASE + "_repair", ClimateMain.MOD_ID);

		DCArmorMaterial.load();
		DCToolMaterial.load();
	}

	public static void registerBlock(Block block, String name, String modid, int[] fuel) {
		Block reg = block.setRegistryName(modid, name);
		ForgeRegistries.BLOCKS.register(reg);
		ForgeRegistries.ITEMS.register(new FuelItemBlock(reg, fuel));
	}

	public static void registerChestBlock(Block block, String name, String modid) {
		Block reg = block.setRegistryName(modid, name);
		ForgeRegistries.BLOCKS.register(reg);
		ForgeRegistries.ITEMS.register(new ItemLowChest(reg));
	}

	private static void registerHarvestLevel() {
		MainInit.oreNew.setHarvestLevel("pickaxe", 0);
		MainInit.layerNew.setHarvestLevel("pickaxe", 0);
		MainInit.builds.setHarvestLevel("pickaxe", 0);
	}

}
