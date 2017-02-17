package defeatedcrow.hac.main;

import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCItemBlock;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInitRegister;
import defeatedcrow.hac.machine.MachineInitRegister;
import defeatedcrow.hac.machine.block.ItemBlockHighTier;
import defeatedcrow.hac.magic.MagicInitRegister;
import defeatedcrow.hac.main.block.build.BlockBuilding;
import defeatedcrow.hac.main.block.build.BlockChalcedonyLamp;
import defeatedcrow.hac.main.block.build.BlockGemBricks;
import defeatedcrow.hac.main.block.build.BlockGlassSelenite;
import defeatedcrow.hac.main.block.build.BlockIronPlate;
import defeatedcrow.hac.main.block.build.BlockLowChest;
import defeatedcrow.hac.main.block.build.BlockMagnetChest;
import defeatedcrow.hac.main.block.build.BlockMarkingPanel;
import defeatedcrow.hac.main.block.build.BlockMetalChest;
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
import defeatedcrow.hac.main.block.device.BlockBellow;
import defeatedcrow.hac.main.block.device.BlockCookingStove;
import defeatedcrow.hac.main.block.device.BlockNormalChamber;
import defeatedcrow.hac.main.block.device.BlockShitirin;
import defeatedcrow.hac.main.block.device.BlockSink;
import defeatedcrow.hac.main.block.device.BlockStevensonScreen;
import defeatedcrow.hac.main.block.device.ItemBlockShitirin;
import defeatedcrow.hac.main.block.ores.BlockDusts;
import defeatedcrow.hac.main.block.ores.BlockGem;
import defeatedcrow.hac.main.block.ores.BlockMetal;
import defeatedcrow.hac.main.block.ores.BlockOres;
import defeatedcrow.hac.main.block.ores.BlockOres2;
import defeatedcrow.hac.main.item.equip.ItemArmorDC;
import defeatedcrow.hac.main.item.equip.ItemArmorHat;
import defeatedcrow.hac.main.item.equip.ItemArmorThinDC;
import defeatedcrow.hac.main.item.food.ItemDCFoods;
import defeatedcrow.hac.main.item.food.ItemFoodMaterials;
import defeatedcrow.hac.main.item.misc.ItemMiscs;
import defeatedcrow.hac.main.item.misc.ItemRepairPutty;
import defeatedcrow.hac.main.item.ores.ItemGems;
import defeatedcrow.hac.main.item.ores.ItemIngots;
import defeatedcrow.hac.main.item.ores.ItemMiscDust;
import defeatedcrow.hac.main.item.ores.ItemOreDusts;
import defeatedcrow.hac.main.item.tool.ItemArroyYagen;
import defeatedcrow.hac.main.item.tool.ItemAxeDC;
import defeatedcrow.hac.main.item.tool.ItemCrowDrill;
import defeatedcrow.hac.main.item.tool.ItemPickaxeDC;
import defeatedcrow.hac.main.item.tool.ItemScytheDC;
import defeatedcrow.hac.main.item.tool.ItemSpadeDC;
import defeatedcrow.hac.main.item.tool.ItemStoneYagen;
import defeatedcrow.hac.main.item.tool.ItemSwordDC;
import defeatedcrow.hac.main.item.tool.ItemWrench;
import defeatedcrow.hac.main.util.DCArmorMaterial;
import defeatedcrow.hac.main.util.DCMaterial;
import defeatedcrow.hac.main.util.DCToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MainMaterialRegister {
	private MainMaterialRegister() {}

	public static void load() {

		registerBlock();
		regJsonBlock();
		regDeviceBlock();
		registerSidedBlock();
		registerItems();
		registerMaterialEnum();
		registerEquip();
		registerFood();
		registerHarvestLevel();

		FoodInitRegister.load();
		MachineInitRegister.load();
		MagicInitRegister.load();
	}

	static void registerBlock() {
		MainInit.ores = new BlockOres(Material.IRON, ClimateCore.PACKAGE_BASE + "_ore_stone", 15);
		registerBlock(MainInit.ores, ClimateCore.PACKAGE_BASE + "_ore_stone");

		MainInit.ores_2 = new BlockOres2(Material.IRON, ClimateCore.PACKAGE_BASE + "_ore2_stone", 7);
		registerBlock(MainInit.ores_2, ClimateCore.PACKAGE_BASE + "_ore2_stone");

		MainInit.metalBlock = new BlockMetal(Material.IRON, ClimateCore.PACKAGE_BASE + "_metal", 10);
		registerBlock(MainInit.metalBlock, ClimateCore.PACKAGE_BASE + "_ore_metalblock");

		MainInit.dustBlock = new BlockDusts(Material.GROUND, ClimateCore.PACKAGE_BASE + "_dustblock", 11);
		registerBlock(MainInit.dustBlock, ClimateCore.PACKAGE_BASE + "_ore_dustblock");

		MainInit.gemBlock = new BlockGem(Material.ROCK, ClimateCore.PACKAGE_BASE + "_gemblock", 12);
		registerBlock(MainInit.gemBlock, ClimateCore.PACKAGE_BASE + "_ore_gemblock");

		MainInit.bricks = new BlockGemBricks(Material.ROCK, ClimateCore.PACKAGE_BASE + "_build_bricks");
		registerBlock(MainInit.bricks, ClimateCore.PACKAGE_BASE + "_build_bricks");

		MainInit.builds = new BlockBuilding(Material.ROCK, ClimateCore.PACKAGE_BASE + "_build_build");
		registerBlock(MainInit.builds, ClimateCore.PACKAGE_BASE + "_build_build");

		MainInit.selenite = new BlockGlassSelenite(ClimateCore.PACKAGE_BASE + "_build_selenite", 2);
		registerBlock(MainInit.selenite, ClimateCore.PACKAGE_BASE + "_build_selenite");

		MainInit.chalLamp = new BlockChalcedonyLamp(ClimateCore.PACKAGE_BASE + "_build_challamp", 15);
		registerBlock(MainInit.chalLamp, ClimateCore.PACKAGE_BASE + "_build_challamp");

		MainInit.wallLamp = new BlockWallLamp(ClimateCore.PACKAGE_BASE + "_build_walllamp");
		registerBlock(MainInit.wallLamp, ClimateCore.PACKAGE_BASE + "_build_walllamp");

		MainInit.markingPanel = new BlockMarkingPanel(ClimateCore.PACKAGE_BASE + "_build_markingpanel");
		registerBlock(MainInit.markingPanel, ClimateCore.PACKAGE_BASE + "_build_markingpanel");
	}

	static void registerSidedBlock() {
		MainInit.logCont = new BlockLogCont(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_log", 6);
		registerBlock(MainInit.logCont, ClimateCore.PACKAGE_BASE + "_cont_log");
		ClimateMain.proxy.addSidedBlock(MainInit.logCont, "cont_log", 6);

		MainInit.cropCont = new BlockCropCont(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_crop", 10);
		registerBlock(MainInit.cropCont, ClimateCore.PACKAGE_BASE + "_cont_crop");
		ClimateMain.proxy.addTBBlock(MainInit.cropCont, "cont_crop", 10);

		MainInit.dropCont = new BlockEnemyCont(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_metal", 4);
		registerBlock(MainInit.dropCont, ClimateCore.PACKAGE_BASE + "_cont_metal");
		ClimateMain.proxy.addTBBlock(MainInit.dropCont, "cont_metal", 4);

		MainInit.miscCont = new BlockMiscCont(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_misc", 3);
		registerBlock(MainInit.miscCont, ClimateCore.PACKAGE_BASE + "_cont_misc");
		ClimateMain.proxy.addTBBlock(MainInit.miscCont, "cont_misc", 3);

		MainInit.cardboard = new BlockCardboard(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_cardboard", 5);
		registerBlock(MainInit.cardboard, ClimateCore.PACKAGE_BASE + "_cont_cardboard");
		ClimateMain.proxy.addSidedBlock(MainInit.cardboard, "cont_cardboard", 5);

		MainInit.dustBags = new BlockDustBag(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_bags", 3);
		registerBlock(MainInit.dustBags, ClimateCore.PACKAGE_BASE + "_cont_bags");
		ClimateMain.proxy.addSidedBlock(MainInit.dustBags, "cont_bags", 3);

		MainInit.cropBasket = new BlockCropBasket(Material.CLAY, ClimateCore.PACKAGE_BASE + "_cont_basket", 8);
		registerBlock(MainInit.cropBasket, ClimateCore.PACKAGE_BASE + "_cont_basket");
		ClimateMain.proxy.addTBBlock(MainInit.cropBasket, "cont_basket", 8);
	}

	static void regJsonBlock() {
		MainInit.stairsGlass = new BlockStairsBase(MainInit.selenite.getDefaultState(), "build/glass_stairs", true)
				.setUnlocalizedName("dcs_stairs_glass");
		registerBlock(MainInit.stairsGlass, ClimateCore.PACKAGE_BASE + "_stairs_glass");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stairsGlass), "dcs_climate", "dcs_stairs_glass",
				"build", 15, false);

		MainInit.stairsGypsum = new BlockStairsBase(MainInit.ores.getDefaultState(), "ores/gemblock_gypsum", false)
				.setUnlocalizedName("dcs_stairs_gypsum");
		registerBlock(MainInit.stairsGypsum, ClimateCore.PACKAGE_BASE + "_stairs_gypsum");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stairsGypsum), "dcs_climate", "dcs_stairs_gypsum",
				"build", 15, false);

		MainInit.stairsMarble = new BlockStairsBase(MainInit.ores.getDefaultState(), "ores/gemblock_marble", false)
				.setUnlocalizedName("dcs_stairs_marble");
		registerBlock(MainInit.stairsMarble, ClimateCore.PACKAGE_BASE + "_stairs_marble");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stairsMarble), "dcs_climate", "dcs_stairs_marble",
				"build", 15, false);

		MainInit.stairsSerpentine = new BlockStairsBase(MainInit.ores.getDefaultState(), "ores/gemblock_serpentine",
				false).setUnlocalizedName("dcs_stairs_serpentine");
		registerBlock(MainInit.stairsSerpentine, ClimateCore.PACKAGE_BASE + "_stairs_serpentine");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stairsSerpentine), "dcs_climate",
				"dcs_stairs_serpentine", "build", 15, false);

		MainInit.stairsBedrock = new BlockStairsBase(MainInit.ores.getDefaultState(), "ores/gemblock_bedrock", false)
				.setUnlocalizedName("dcs_stairs_bedrock");
		registerBlock(MainInit.stairsBedrock, ClimateCore.PACKAGE_BASE + "_stairs_bedrock");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stairsBedrock), "dcs_climate",
				"dcs_stairs_bedrock", "build", 15, false);

		MainInit.halfSlab = new BlockSlabDC();
		registerBlock(MainInit.halfSlab, ClimateCore.PACKAGE_BASE + "_build_slab");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.halfSlab), "dcs_climate", "dcs_build_slab",
				"build", 4, true);

		MainInit.tableMarble = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_table_marble", false);
		registerBlock(MainInit.tableMarble, ClimateCore.PACKAGE_BASE + "_table_marble");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.tableMarble), "dcs_climate", "dcs_table_marble",
				"build", 0, false);

		MainInit.tableGypsum = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_table_gypsum", false);
		registerBlock(MainInit.tableGypsum, ClimateCore.PACKAGE_BASE + "_table_gypsum");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.tableGypsum), "dcs_climate", "dcs_table_gypsum",
				"build", 0, false);

		MainInit.tableWood = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_table_wood", false);
		registerBlock(MainInit.tableWood, ClimateCore.PACKAGE_BASE + "_table_wood");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.tableWood), "dcs_climate", "dcs_table_wood",
				"build", 0, false);

		MainInit.tableDark = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_table_darkwood", false);
		registerBlock(MainInit.tableDark, ClimateCore.PACKAGE_BASE + "_table_darkwood");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.tableDark), "dcs_climate", "dcs_table_darkwood",
				"build", 0, false);

		MainInit.carpetRed = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_carpet_red", true);
		registerBlock(MainInit.carpetRed, ClimateCore.PACKAGE_BASE + "_carpet_red");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.carpetRed), "dcs_climate", "dcs_carpet_red",
				"build", 0, false);

		MainInit.carpetWhite = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_carpet_white", true);
		registerBlock(MainInit.carpetWhite, ClimateCore.PACKAGE_BASE + "_carpet_white");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.carpetWhite), "dcs_climate", "dcs_carpet_white",
				"build", 0, false);

		MainInit.carpetGray = new BlockTableBase(ClimateCore.PACKAGE_BASE + "_carpet_gray", true);
		registerBlock(MainInit.carpetGray, ClimateCore.PACKAGE_BASE + "_carpet_gray");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.carpetGray), "dcs_climate", "dcs_carpet_gray",
				"build", 0, false);

		MainInit.sofaBlack = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_sofa_black");
		registerBlock(MainInit.sofaBlack, ClimateCore.PACKAGE_BASE + "_sofa_black");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.sofaBlack), "dcs_climate", "dcs_sofa_black",
				"build", 0, false);

		MainInit.sofaRed = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_sofa_red");
		registerBlock(MainInit.sofaRed, ClimateCore.PACKAGE_BASE + "_sofa_red");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.sofaRed), "dcs_climate", "dcs_sofa_red", "build",
				0, false);

		MainInit.stoolBlack = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_stool_black").setSmallAABB();
		registerBlock(MainInit.stoolBlack, ClimateCore.PACKAGE_BASE + "_stool_black");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stoolBlack), "dcs_climate", "dcs_stool_black",
				"build", 0, false);

		MainInit.stoolRed = new BlockSofaBase(ClimateCore.PACKAGE_BASE + "_stool_red").setSmallAABB();
		registerBlock(MainInit.stoolRed, ClimateCore.PACKAGE_BASE + "_stool_red");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stoolRed), "dcs_climate", "dcs_stool_red",
				"build", 0, false);

		MainInit.plate = new BlockIronPlate(ClimateCore.PACKAGE_BASE + "_build_plate", 1);
		registerBlock(MainInit.plate, ClimateCore.PACKAGE_BASE + "_build_plate");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.plate), "dcs_climate", "dcs_build_plate", "build",
				1, false);

		MainInit.chestMarble = new BlockLowChest(Material.ROCK, ClimateCore.PACKAGE_BASE + "_device_lowchest_marble",
				true);
		registerBlock(MainInit.chestMarble, ClimateCore.PACKAGE_BASE + "_device_lowchest_marble");
		ClimateMain.proxy.regTEJson(MainInit.chestMarble, "dcs_climate", "dcs_device_lowchest_marble", "device");

		MainInit.chestWood = new BlockLowChest(Material.ROCK, ClimateCore.PACKAGE_BASE + "_device_lowchest_wood", true);
		registerBlock(MainInit.chestWood, ClimateCore.PACKAGE_BASE + "_device_lowchest_wood");
		ClimateMain.proxy.regTEJson(MainInit.chestWood, "dcs_climate", "dcs_device_lowchest_wood", "device");

		MainInit.sinkMetal = new BlockSink(ClimateCore.PACKAGE_BASE + "_device_sink_half", false);
		registerBlock(MainInit.sinkMetal, ClimateCore.PACKAGE_BASE + "_device_sink_half");
		ClimateMain.proxy.regTEJson(MainInit.sinkMetal, "dcs_climate", "dcs_device_sink_half", "device");

		MainInit.sinkChest = new BlockSink(ClimateCore.PACKAGE_BASE + "_device_sink_full", true);
		registerBlock(MainInit.sinkChest, ClimateCore.PACKAGE_BASE + "_device_sink_full");
		ClimateMain.proxy.regTEJson(MainInit.sinkChest, "dcs_climate", "dcs_device_sink_full", "device");
	}

	static void regDeviceBlock() {
		MainInit.chamber = new BlockNormalChamber(Material.IRON, ClimateCore.PACKAGE_BASE + "_device_chamber", 1);
		MainInit.chamber.setRegistryName(ClimateCore.PACKAGE_BASE + "_device_chamber");
		GameRegistry.register(MainInit.chamber);
		GameRegistry.register(new DCItemBlock(MainInit.chamber) {
			@Override
			public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
				tooltip.add(TextFormatting.BOLD.toString() + "Tier 1");
			}
		});

		MainInit.shitirin = new BlockShitirin(Material.CLAY, ClimateCore.PACKAGE_BASE + "_device_shitirin", 1);
		MainInit.shitirin.setRegistryName(ClimateCore.PACKAGE_BASE + "_device_shitirin");
		GameRegistry.register(MainInit.shitirin);
		GameRegistry.register(new ItemBlockShitirin(MainInit.shitirin));

		MainInit.fuelStove = new BlockCookingStove(Material.IRON, ClimateCore.PACKAGE_BASE + "_device_fuelstove", 3);
		MainInit.fuelStove.setRegistryName(ClimateCore.PACKAGE_BASE + "_device_fuelstove");
		GameRegistry.register(MainInit.fuelStove);
		GameRegistry.register(new DCItemBlock(MainInit.fuelStove) {
			@Override
			public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
				tooltip.add(TextFormatting.BOLD.toString() + "Tier 2");
			}
		});

		MainInit.stevenson_screen = new BlockStevensonScreen(ClimateCore.PACKAGE_BASE + "_device_stevenson_screen");
		registerBlock(MainInit.stevenson_screen, ClimateCore.PACKAGE_BASE + "_device_stevenson_screen");

		MainInit.chestMetal = new BlockMetalChest(Material.IRON, ClimateCore.PACKAGE_BASE + "_device_chest_metal");
		registerBlock(MainInit.chestMetal, ClimateCore.PACKAGE_BASE + "_device_chest_metal");

		MainInit.chestMagnet = new BlockMagnetChest(Material.IRON, ClimateCore.PACKAGE_BASE + "_device_chest_magnet");
		registerBlock(MainInit.chestMagnet, ClimateCore.PACKAGE_BASE + "_device_chest_magnet");

		MainInit.chestVillage = new BlockVillageChest(Material.IRON,
				ClimateCore.PACKAGE_BASE + "_device_chest_village");
		registerBlock(MainInit.chestVillage, ClimateCore.PACKAGE_BASE + "_device_chest_village");

		MainInit.bellow = new BlockBellow(ClimateCore.PACKAGE_BASE + "_device_bellow");
		MainInit.bellow.setRegistryName(ClimateCore.PACKAGE_BASE + "_device_bellow");
		GameRegistry.register(MainInit.bellow);
		GameRegistry.register(new ItemBlockHighTier(MainInit.bellow, 1));
	}

	static void registerItems() {
		// ores
		MainInit.oreIngot = new ItemIngots(10).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_ingot");
		GameRegistry.register(MainInit.oreIngot.setRegistryName(ClimateCore.PACKAGE_BASE + "_ingot"));

		MainInit.oreDust = new ItemOreDusts(9).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_oredust");
		GameRegistry.register(MainInit.oreDust.setRegistryName(ClimateCore.PACKAGE_BASE + "_oredust"));

		MainInit.gems = new ItemGems(14).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_gem");
		GameRegistry.register(MainInit.gems.setRegistryName(ClimateCore.PACKAGE_BASE + "_gem"));

		MainInit.miscDust = new ItemMiscDust(8).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_miscdust");
		GameRegistry.register(MainInit.miscDust.setRegistryName(ClimateCore.PACKAGE_BASE + "_miscdust"));

		// tools
		MainInit.materials = new ItemMiscs(7).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_material");
		GameRegistry.register(MainInit.materials.setRegistryName(ClimateCore.PACKAGE_BASE + "_material"));

		MainInit.stoneYagen = new ItemStoneYagen().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_yagen_stone");
		GameRegistry.register(MainInit.stoneYagen.setRegistryName(ClimateCore.PACKAGE_BASE + "_yagen_stone"));

		MainInit.brassYagen = new ItemArroyYagen().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_yagen_brass");
		GameRegistry.register(MainInit.brassYagen.setRegistryName(ClimateCore.PACKAGE_BASE + "_yagen_brass"));

		MainInit.crowDrill = new ItemCrowDrill().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_creative_drill");
		GameRegistry.register(MainInit.crowDrill.setRegistryName(ClimateCore.PACKAGE_BASE + "_creative_drill"));

	}

	static void registerFood() {
		MainInit.foodMaterials = new ItemFoodMaterials(2)
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_materials");
		GameRegistry.register(MainInit.foodMaterials.setRegistryName(ClimateCore.PACKAGE_BASE + "_food_materials"));

		MainInit.bakedApple = new ItemDCFoods(2, false).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_food_itemfood");
		GameRegistry.register(MainInit.bakedApple.setRegistryName(ClimateCore.PACKAGE_BASE + "_food_itemfood"));
	}

	static void registerEquip() {
		String[] name = {
				"brass",
				"steel",
				"silver",
				"nickelsilver",
				"chalcedony",
				"sapphire"
		};
		for (int j = 0; j < 6; j++) {
			DCLogger.debugLog(j + "/" + DCToolMaterial.getToolMaterial(j).toString());
			MainInit.dcAxe[j] = new ItemAxeDC(DCToolMaterial.getToolMaterial(j), name[j])
					.setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_axe_" + name[j]);
			GameRegistry.register(MainInit.dcAxe[j].setRegistryName(ClimateCore.PACKAGE_BASE + "_axe_" + name[j]));
		}

		for (int j = 0; j < 6; j++) {
			MainInit.dcPickaxe[j] = new ItemPickaxeDC(DCToolMaterial.getToolMaterial(j), name[j])
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_pickaxe_" + name[j]);
			GameRegistry
					.register(MainInit.dcPickaxe[j].setRegistryName(ClimateCore.PACKAGE_BASE + "_pickaxe_" + name[j]));
		}

		for (int j = 0; j < 6; j++) {
			MainInit.dcSpade[j] = new ItemSpadeDC(DCToolMaterial.getToolMaterial(j), name[j])
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_spade_" + name[j]);
			GameRegistry.register(MainInit.dcSpade[j].setRegistryName(ClimateCore.PACKAGE_BASE + "_spade_" + name[j]));
		}

		for (int j = 0; j < 4; j++) {
			MainInit.dcSword[j] = new ItemSwordDC(DCToolMaterial.getToolMaterial(j), name[j], false)
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_sword_" + name[j]);
			GameRegistry.register(MainInit.dcSword[j].setRegistryName(ClimateCore.PACKAGE_BASE + "_sword_" + name[j]));
		}
		for (int j = 4; j < 6; j++) {
			MainInit.dcSword[j] = new ItemSwordDC(DCToolMaterial.getToolMaterial(j), name[j], true)
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_sword_" + name[j]);
			GameRegistry.register(MainInit.dcSword[j].setRegistryName(ClimateCore.PACKAGE_BASE + "_sword_" + name[j]));
		}

		MainInit.dcScythe[0] = new ItemScytheDC(DCToolMaterial.getToolMaterial(0), "brass")
				.setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_scythe_brass");
		GameRegistry.register(MainInit.dcScythe[0].setRegistryName(ClimateCore.PACKAGE_BASE + "_scythe_brass"));

		MainInit.dcScythe[1] = new ItemScytheDC(DCToolMaterial.getToolMaterial(1), "steel")
				.setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_scythe_steel");
		GameRegistry.register(MainInit.dcScythe[1].setRegistryName(ClimateCore.PACKAGE_BASE + "_scythe_steel"));

		MainInit.dcScythe[2] = new ItemScytheDC(DCToolMaterial.getToolMaterial(4), "chalcedony")
				.setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_scythe_chalcedony");
		GameRegistry.register(MainInit.dcScythe[2].setRegistryName(ClimateCore.PACKAGE_BASE + "_scythe_chalcedony"));

		String[] type = {
				"met",
				"plate",
				"leggins",
				"boots"
		};
		for (int i = 0; i < 4; i++) {
			EntityEquipmentSlot slot = DCUtil.SLOTS[i];
			MainInit.brassArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_BRASS, DCMaterial.BRASS, slot, "brass")
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_brass");
			GameRegistry.register(
					MainInit.brassArmor[i].setRegistryName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_brass"));

			MainInit.steelArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_STEEL, DCMaterial.STEEL, slot, "steel")
					.setCreativeTab(ClimateMain.tool)
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_steel");
			GameRegistry.register(
					MainInit.steelArmor[i].setRegistryName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_steel"));

			MainInit.chalcArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_CHALCEDONY, DCMaterial.CHALCEDONY, slot,
					"chalcedony").setCreativeTab(ClimateMain.tool)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_chalcedony");
			GameRegistry.register(
					MainInit.chalcArmor[i].setRegistryName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_chalcedony"));

			MainInit.sapphireArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_SAPPHIRE, DCMaterial.SAPPHIRE, slot,
					"sapphire").setCreativeTab(ClimateMain.tool)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_sapphire");
			GameRegistry.register(
					MainInit.sapphireArmor[i].setRegistryName(ClimateCore.PACKAGE_BASE + "_" + type[i] + "_sapphire"));
		}

		MainInit.linenUnder = new ItemArmorThinDC(DCArmorMaterial.DC_LINEN, DCMaterial.LINEN, EntityEquipmentSlot.LEGS,
				"linen").setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_linen");
		GameRegistry.register(MainInit.linenUnder.setRegistryName(ClimateCore.PACKAGE_BASE + "_leggins_linen"));

		MainInit.linenCourt = new ItemArmorThinDC(DCArmorMaterial.DC_LINEN, DCMaterial.LINEN, EntityEquipmentSlot.CHEST,
				"linen").setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_coat_linen");
		GameRegistry.register(MainInit.linenCourt.setRegistryName(ClimateCore.PACKAGE_BASE + "_coat_linen"));

		MainInit.clothUnder = new ItemArmorThinDC(DCArmorMaterial.DC_CLOTH, DCMaterial.LINEN, EntityEquipmentSlot.LEGS,
				"cloth").setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_cloth");
		GameRegistry.register(MainInit.clothUnder.setRegistryName(ClimateCore.PACKAGE_BASE + "_leggins_cloth"));

		MainInit.workerSuit = new ItemArmorThinDC(DCArmorMaterial.DC_CLOTH, DCMaterial.LINEN, EntityEquipmentSlot.LEGS,
				"worker").setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_worker");
		GameRegistry.register(MainInit.workerSuit.setRegistryName(ClimateCore.PACKAGE_BASE + "_leggins_worker"));

		MainInit.blackSuit = new ItemArmorThinDC(DCArmorMaterial.DC_CLOTH, DCMaterial.LINEN, EntityEquipmentSlot.LEGS,
				"suit").setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_suit");
		GameRegistry.register(MainInit.blackSuit.setRegistryName(ClimateCore.PACKAGE_BASE + "_leggins_suit"));

		MainInit.leatherHat = new ItemArmorHat(ArmorMaterial.LEATHER, DCMaterial.LINEN, EntityEquipmentSlot.HEAD,
				"leather").setCreativeTab(ClimateMain.tool)
						.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_hat_leather");
		GameRegistry.register(MainInit.leatherHat.setRegistryName(ClimateCore.PACKAGE_BASE + "_hat_leather"));

		MainInit.cottonHat = new ItemArmorHat(DCArmorMaterial.DC_CLOTH, DCMaterial.CLOTH, EntityEquipmentSlot.HEAD,
				"cotton").setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_hat_cotton");
		GameRegistry.register(MainInit.cottonHat.setRegistryName(ClimateCore.PACKAGE_BASE + "_hat_cotton"));

		MainInit.wrench = new ItemWrench(DCToolMaterial.getToolMaterial(0))
				.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_wrench_brass");
		GameRegistry.register(MainInit.wrench.setRegistryName(ClimateCore.PACKAGE_BASE + "_wrench"));

	}

	static void registerMaterialEnum() {
		MainInit.repairPutty = new ItemRepairPutty().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_repair");
		GameRegistry.register(MainInit.repairPutty.setRegistryName(ClimateCore.PACKAGE_BASE + "_repair"));

		DCArmorMaterial.load();
		DCToolMaterial.load();
	}

	private static void registerHarvestLevel() {
		MainInit.ores.setHarvestLevel("pickaxe", 0);
	}

	public static void registerBlock(Block block, String name) {
		Block reg = block.setRegistryName(name);
		GameRegistry.register(reg);
		GameRegistry.register(new DCItemBlock(reg));
	}

}
