package defeatedcrow.hac.main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCItemBlock;
import defeatedcrow.hac.magic.item.ItemMagicalPendant;
import defeatedcrow.hac.main.block.build.BlockGlassSelenite;
import defeatedcrow.hac.main.block.build.BlockSlabDC;
import defeatedcrow.hac.main.block.build.BlockStairsBase;
import defeatedcrow.hac.main.block.container.BlockCardboard;
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
import defeatedcrow.hac.main.item.tool.ItemArroyYagen;
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
		register();
		registerMaterialEnum();
		registerEquip();
		registerFood();
		registerHarvestLevel();
	}

	static void registerBlock() {
		MainInit.ores = new BlockOres(Material.IRON, ClimateCore.PACKAGE_BASE + "_ore_stone", 15);
		registerBlock(MainInit.ores, ClimateCore.PACKAGE_BASE + "_ore_stone");

		MainInit.metalBlock = new BlockMetal(Material.IRON, ClimateCore.PACKAGE_BASE + "_metal", 7);
		registerBlock(MainInit.metalBlock, ClimateCore.PACKAGE_BASE + "_ore_metalblock");

		MainInit.dustBlock = new BlockDusts(Material.GROUND, ClimateCore.PACKAGE_BASE + "_dustblock", 7);
		registerBlock(MainInit.dustBlock, ClimateCore.PACKAGE_BASE + "_ore_dustblock");

		MainInit.gemBlock = new BlockGem(Material.ROCK, ClimateCore.PACKAGE_BASE + "_gemblock", 4);
		registerBlock(MainInit.gemBlock, ClimateCore.PACKAGE_BASE + "_ore_gemblock");

		MainInit.selenite = new BlockGlassSelenite(ClimateCore.PACKAGE_BASE + "_build_selenite", 2);
		registerBlock(MainInit.selenite, ClimateCore.PACKAGE_BASE + "_build_selenite");
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
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.stairsGypsum), "dcs_climate",
				"dcs_stairs_gypsum", "build", 15, false);

		MainInit.halfSlab = new BlockSlabDC();
		registerBlock(MainInit.halfSlab, ClimateCore.PACKAGE_BASE + "_build_slab");
		ClimateMain.proxy.regBlockJson(Item.getItemFromBlock(MainInit.halfSlab), "dcs_climate", "dcs_build_slab",
				"build", 2, true);
	}

	static void regDeviceBlock() {
		MainInit.chamber = new BlockNormalChamber(Material.IRON, ClimateCore.PACKAGE_BASE + "_device_chamber", 1);
		registerBlock(MainInit.chamber, ClimateCore.PACKAGE_BASE + "_device_chamber");
	}

	static void register() {
		// ores
		MainInit.oreIngot = new ItemIngots(7).setCreativeTab(ClimateCore.climate).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_ingot");
		GameRegistry.register(MainInit.oreIngot.setRegistryName(ClimateCore.PACKAGE_BASE + "_ingot"));

		MainInit.oreDust = new ItemOreDusts(7).setCreativeTab(ClimateCore.climate).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_oredust");
		GameRegistry.register(MainInit.oreDust.setRegistryName(ClimateCore.PACKAGE_BASE + "_oredust"));

		MainInit.gems = new ItemGems(7).setCreativeTab(ClimateCore.climate).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_gem");
		GameRegistry.register(MainInit.gems.setRegistryName(ClimateCore.PACKAGE_BASE + "_gem"));

		MainInit.miscDust = new ItemMiscDust(5).setCreativeTab(ClimateCore.climate).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_miscdust");
		GameRegistry.register(MainInit.miscDust.setRegistryName(ClimateCore.PACKAGE_BASE + "_miscdust"));

		// tools
		MainInit.materials = new ItemMiscs(5).setCreativeTab(ClimateCore.climate).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_material");
		GameRegistry.register(MainInit.materials.setRegistryName(ClimateCore.PACKAGE_BASE + "_material"));

		MainInit.stoneYagen = new ItemStoneYagen().setCreativeTab(ClimateCore.climate).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_yagen_stone");
		GameRegistry.register(MainInit.stoneYagen.setRegistryName(ClimateCore.PACKAGE_BASE + "_yagen_stone"));

		MainInit.brassYagen = new ItemArroyYagen().setCreativeTab(ClimateCore.climate).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_yagen_brass");
		GameRegistry.register(MainInit.brassYagen.setRegistryName(ClimateCore.PACKAGE_BASE + "_yagen_brass"));

		MainInit.crowDrill = new ItemCrowDrill().setCreativeTab(ClimateCore.climate).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_creative_drill");
		GameRegistry.register(MainInit.crowDrill.setRegistryName(ClimateCore.PACKAGE_BASE + "_creative_drill"));

	}

	static void registerFood() {
		MainInit.bakedApple = new ItemDCFoods(0, false).setCreativeTab(ClimateCore.climate).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_food_itemfood");
		GameRegistry.register(MainInit.bakedApple.setRegistryName(ClimateCore.PACKAGE_BASE + "_food_itemfood"));
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
			MainInit.dcAxe[j] = new ItemAxeDC(DCToolMaterial.getToolMaterial(j), name[j]).setCreativeTab(
					ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_axe_" + name[j]);
			GameRegistry.register(MainInit.dcAxe[j].setRegistryName(ClimateCore.PACKAGE_BASE + "_axe_" + name[j]));
		}

		for (int j = 0; j < 6; j++) {
			MainInit.dcPickaxe[j] = new ItemPickaxeDC(DCToolMaterial.getToolMaterial(j), name[j]).setCreativeTab(
					ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_pickaxe_" + name[j]);
			GameRegistry.register(MainInit.dcPickaxe[j].setRegistryName(ClimateCore.PACKAGE_BASE + "_pickaxe_"
					+ name[j]));
		}

		for (int j = 0; j < 6; j++) {
			MainInit.dcSpade[j] = new ItemSpadeDC(DCToolMaterial.getToolMaterial(j), name[j]).setCreativeTab(
					ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_spade_" + name[j]);
			GameRegistry.register(MainInit.dcSpade[j].setRegistryName(ClimateCore.PACKAGE_BASE + "_spade_" + name[j]));
		}

		for (int j = 0; j < 6; j++) {
			MainInit.dcSword[j] = new ItemSwordDC(DCToolMaterial.getToolMaterial(j), name[j]).setCreativeTab(
					ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_sword_" + name[j]);
			GameRegistry.register(MainInit.dcSword[j].setRegistryName(ClimateCore.PACKAGE_BASE + "_sword_" + name[j]));
		}

		String[] type = {
				"met",
				"plate",
				"leggins",
				"boots" };
		for (int i = 0; i < 4; i++) {
			EntityEquipmentSlot slot = EntityEquipmentSlot.values()[i + 2];
			MainInit.brassArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_BRASS, DCMaterial.BRASS, slot, "brass")
					.setCreativeTab(ClimateMain.tool).setUnlocalizedName(
							ClimateCore.PACKAGE_BASE + "_" + type[i] + "_brass");
			GameRegistry.register(MainInit.brassArmor[i].setRegistryName(ClimateCore.PACKAGE_BASE + "_" + type[i]
					+ "_brass"));

			MainInit.steelArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_STEEL, DCMaterial.STEEL, slot, "steel")
					.setCreativeTab(ClimateMain.tool).setUnlocalizedName(
							ClimateCore.PACKAGE_BASE + "_" + type[i] + "_steel");
			GameRegistry.register(MainInit.steelArmor[i].setRegistryName(ClimateCore.PACKAGE_BASE + "_" + type[i]
					+ "_steel"));

			MainInit.chalcArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_CHALCEDONY, DCMaterial.CHALCEDONY, slot,
					"chalcedony").setCreativeTab(ClimateMain.tool).setUnlocalizedName(
					ClimateCore.PACKAGE_BASE + "_" + type[i] + "_chalcedony");
			GameRegistry.register(MainInit.chalcArmor[i].setRegistryName(ClimateCore.PACKAGE_BASE + "_" + type[i]
					+ "_chalcedony"));

			MainInit.sapphireArmor[i] = new ItemArmorDC(DCArmorMaterial.DC_SAPPHIRE, DCMaterial.SAPPHIRE, slot,
					"sapphire").setCreativeTab(ClimateMain.tool).setUnlocalizedName(
					ClimateCore.PACKAGE_BASE + "_" + type[i] + "_sapphire");
			GameRegistry.register(MainInit.sapphireArmor[i].setRegistryName(ClimateCore.PACKAGE_BASE + "_" + type[i]
					+ "_sapphire"));
		}

		MainInit.linenUnder = new ItemArmorThinDC(DCArmorMaterial.DC_LINEN, DCMaterial.LINEN, EntityEquipmentSlot.LEGS,
				"linen").setCreativeTab(ClimateMain.tool).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_leggins_linen");
		GameRegistry.register(MainInit.linenUnder.setRegistryName(ClimateCore.PACKAGE_BASE + "_leggins_linen"));

		MainInit.linenCourt = new ItemArmorThinDC(DCArmorMaterial.DC_LINEN, DCMaterial.LINEN,
				EntityEquipmentSlot.CHEST, "linen").setCreativeTab(ClimateMain.tool).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_coat_linen");
		GameRegistry.register(MainInit.linenCourt.setRegistryName(ClimateCore.PACKAGE_BASE + "_coat_linen"));

		MainInit.clothUnder = new ItemArmorThinDC(DCArmorMaterial.DC_CLOTH, DCMaterial.LINEN, EntityEquipmentSlot.LEGS,
				"cloth").setCreativeTab(ClimateMain.tool).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_leggins_cloth");
		GameRegistry.register(MainInit.clothUnder.setRegistryName(ClimateCore.PACKAGE_BASE + "_leggins_cloth"));

		MainInit.workerSuit = new ItemArmorThinDC(DCArmorMaterial.DC_CLOTH, DCMaterial.LINEN, EntityEquipmentSlot.LEGS,
				"worker").setCreativeTab(ClimateMain.tool).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_leggins_worker");
		GameRegistry.register(MainInit.workerSuit.setRegistryName(ClimateCore.PACKAGE_BASE + "_leggins_worker"));

		MainInit.blackSuit = new ItemArmorThinDC(DCArmorMaterial.DC_CLOTH, DCMaterial.LINEN, EntityEquipmentSlot.LEGS,
				"suit").setCreativeTab(ClimateMain.tool).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_leggins_suit");
		GameRegistry.register(MainInit.blackSuit.setRegistryName(ClimateCore.PACKAGE_BASE + "_leggins_suit"));

		MainInit.pendant = new ItemMagicalPendant(9).setCreativeTab(ClimateMain.tool).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_jewel_pendant");
		GameRegistry.register(MainInit.pendant.setRegistryName(ClimateCore.PACKAGE_BASE + "_jewel_pendant"));
	}

	static void registerMaterialEnum() {
		MainInit.repairPatty = new ItemRepairPutty().setCreativeTab(ClimateMain.tool).setUnlocalizedName(
				ClimateCore.PACKAGE_BASE + "_repair");
		GameRegistry.register(MainInit.repairPatty.setRegistryName(ClimateCore.PACKAGE_BASE + "_repair"));

		DCArmorMaterial.load();
		DCToolMaterial.load();
	}

	private static void registerHarvestLevel() {
		MainInit.ores.setHarvestLevel("pickaxe", 0);
	}

	static void registerBlock(Block block, String name) {
		Block reg = block.setRegistryName(name);
		GameRegistry.register(reg);
		GameRegistry.register(new DCItemBlock(reg));
	}
}
