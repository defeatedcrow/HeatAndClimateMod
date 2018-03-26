package defeatedcrow.hac.magic;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCMaterialReg;
import defeatedcrow.hac.magic.block.BlockBiomeGlass;
import defeatedcrow.hac.magic.block.BlockElestial;
import defeatedcrow.hac.magic.block.BlockIceCluster;
import defeatedcrow.hac.magic.block.BlockInfernalFlame;
import defeatedcrow.hac.magic.block.BlockLotusCandle;
import defeatedcrow.hac.magic.block.BlockMaceBird;
import defeatedcrow.hac.magic.block.BlockMaceBurn;
import defeatedcrow.hac.magic.block.BlockMaceFlower;
import defeatedcrow.hac.magic.block.BlockMaceGlory;
import defeatedcrow.hac.magic.block.BlockMaceIce;
import defeatedcrow.hac.magic.block.BlockMaceLight;
import defeatedcrow.hac.magic.block.BlockMaceMoon;
import defeatedcrow.hac.magic.block.BlockMaceOcean;
import defeatedcrow.hac.magic.block.BlockTimeCage;
import defeatedcrow.hac.magic.block.ItemBlockMaceBird;
import defeatedcrow.hac.magic.block.ItemBlockMaceBurn;
import defeatedcrow.hac.magic.block.ItemBlockMaceFlower;
import defeatedcrow.hac.magic.block.ItemBlockMaceGlory;
import defeatedcrow.hac.magic.block.ItemBlockMaceIce;
import defeatedcrow.hac.magic.block.ItemBlockMaceLight;
import defeatedcrow.hac.magic.block.ItemBlockMaceMoon;
import defeatedcrow.hac.magic.block.ItemBlockMaceOcean;
import defeatedcrow.hac.magic.item.ItemAmulet;
import defeatedcrow.hac.magic.item.ItemArmorGemBoots;
import defeatedcrow.hac.magic.item.ItemEXPGem;
import defeatedcrow.hac.magic.item.ItemMaceCore;
import defeatedcrow.hac.magic.item.ItemMaceHandle;
import defeatedcrow.hac.magic.item.ItemMagicDagger;
import defeatedcrow.hac.magic.item.ItemMagicalBadge;
import defeatedcrow.hac.magic.item.ItemMagicalPendant;
import defeatedcrow.hac.magic.item.ItemSilverDagger;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.DCArmorMaterial;
import defeatedcrow.hac.main.util.DCMaterialEnum;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class MagicInitRegister {

	private MagicInitRegister() {}

	public static void load() {
		loadBlocks();
		loadItems();

		if (ModuleConfig.magic) {
			loadCreativeTab();
		}
	}

	static void loadItems() {
		MagicInit.pendant = new ItemMagicalPendant(19).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_jewel_pendant");
		DCMaterialReg.registerItem(MagicInit.pendant, ClimateCore.PACKAGE_BASE + "_jewel_pendant", ClimateMain.MOD_ID);

		MagicInit.badge = new ItemMagicalBadge(19).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_jewel_badge");
		DCMaterialReg.registerItem(MagicInit.badge, ClimateCore.PACKAGE_BASE + "_jewel_badge", ClimateMain.MOD_ID);

		MagicInit.daggerSilver = new ItemSilverDagger().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_dagger_silver");
		DCMaterialReg.registerItem(MagicInit.daggerSilver, ClimateCore.PACKAGE_BASE + "_dagger_silver",
				ClimateMain.MOD_ID);

		MagicInit.daggerMagic = new ItemMagicDagger().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_dagger_magic");
		DCMaterialReg.registerItem(MagicInit.daggerMagic, ClimateCore.PACKAGE_BASE + "_dagger_magic",
				ClimateMain.MOD_ID);

		MagicInit.macehandle = new ItemMaceHandle(0).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_mace_handle");
		DCMaterialReg.registerItem(MagicInit.macehandle, ClimateCore.PACKAGE_BASE + "_mace_handle", ClimateMain.MOD_ID);

		MagicInit.maceStarItem = new ItemMaceCore(7).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_macecore");
		DCMaterialReg.registerItem(MagicInit.maceStarItem, ClimateCore.PACKAGE_BASE + "_macecore", ClimateMain.MOD_ID);

		MagicInit.expGem = new ItemEXPGem().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_expgem");
		DCMaterialReg.registerItem(MagicInit.expGem, ClimateCore.PACKAGE_BASE + "_expgem", ClimateMain.MOD_ID);

		MagicInit.amulet = new ItemAmulet(4).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_jewel_amulet");
		DCMaterialReg.registerItem(MagicInit.amulet, ClimateCore.PACKAGE_BASE + "_jewel_amulet", ClimateMain.MOD_ID);

		MagicInit.gemBootsBird = new ItemArmorGemBoots(DCArmorMaterial.DC_CHALCEDONY, DCMaterialEnum.CHALCEDONY,
				EntityEquipmentSlot.FEET, "blue").setCreativeTab(ClimateMain.cloth).setUnlocalizedName(
						ClimateCore.PACKAGE_BASE + "_gemboots_blue");
		DCMaterialReg.registerItem(MagicInit.gemBootsBird, ClimateCore.PACKAGE_BASE + "_gemboots_blue",
				ClimateMain.MOD_ID);

		MagicInit.gemBootsFish = new ItemArmorGemBoots(DCArmorMaterial.DC_CHALCEDONY, DCMaterialEnum.CHALCEDONY,
				EntityEquipmentSlot.FEET, "green").setCreativeTab(ClimateMain.cloth).setUnlocalizedName(
						ClimateCore.PACKAGE_BASE + "_gemboots_green");
		DCMaterialReg.registerItem(MagicInit.gemBootsFish, ClimateCore.PACKAGE_BASE + "_gemboots_green",
				ClimateMain.MOD_ID);
	}

	static void loadBlocks() {
		MagicInit.clusterIce = new BlockIceCluster(ClimateCore.PACKAGE_BASE + "_cluster_ice");
		DCMaterialReg.registerBlock(MagicInit.clusterIce, ClimateCore.PACKAGE_BASE + "_cluster_ice",
				ClimateMain.MOD_ID);

		MagicInit.infernalFlame = new BlockInfernalFlame(ClimateCore.PACKAGE_BASE + "_infernal_flame");
		DCMaterialReg.registerBlock(MagicInit.infernalFlame, ClimateCore.PACKAGE_BASE + "_infernal_flame",
				ClimateMain.MOD_ID);

		MagicInit.elestial = new BlockElestial(Material.GLASS, ClimateCore.PACKAGE_BASE + "_ore_elestial");
		DCMaterialReg.registerBlock(MagicInit.elestial, ClimateCore.PACKAGE_BASE + "_ore_elestial", ClimateMain.MOD_ID);

		MagicInit.lotusCandle = new BlockLotusCandle(ClimateCore.PACKAGE_BASE + "_lotus_candle_white", false);
		DCMaterialReg.registerBlock(MagicInit.lotusCandle, ClimateCore.PACKAGE_BASE + "_lotus_candle_white",
				ClimateMain.MOD_ID);

		MagicInit.lotusCandleBlack = new BlockLotusCandle(ClimateCore.PACKAGE_BASE + "_lotus_candle_black", true);
		DCMaterialReg.registerBlock(MagicInit.lotusCandleBlack, ClimateCore.PACKAGE_BASE + "_lotus_candle_black",
				ClimateMain.MOD_ID);

		MagicInit.biomeOrb = new BlockBiomeGlass(ClimateCore.PACKAGE_BASE + "_device_biomeglass");
		DCMaterialReg.registerBlock(MagicInit.biomeOrb, ClimateCore.PACKAGE_BASE + "_device_biomeglass",
				ClimateMain.MOD_ID);

		MagicInit.maceSun = new BlockMaceLight(ClimateCore.PACKAGE_BASE + "_magicmace_light");
		MagicInit.maceSun.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_light");
		ForgeRegistries.BLOCKS.register(MagicInit.maceSun);
		ForgeRegistries.ITEMS.register(new ItemBlockMaceLight(MagicInit.maceSun));

		MagicInit.maceMoon = new BlockMaceMoon(ClimateCore.PACKAGE_BASE + "_magicmace_moon");
		MagicInit.maceMoon.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_moon");
		ForgeRegistries.BLOCKS.register(MagicInit.maceMoon);
		ForgeRegistries.ITEMS.register(new ItemBlockMaceMoon(MagicInit.maceMoon));

		MagicInit.maceBird = new BlockMaceBird(ClimateCore.PACKAGE_BASE + "_magicmace_bird");
		MagicInit.maceBird.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_bird");
		ForgeRegistries.BLOCKS.register(MagicInit.maceBird);
		ForgeRegistries.ITEMS.register(new ItemBlockMaceBird(MagicInit.maceBird));

		MagicInit.maceIce = new BlockMaceIce(ClimateCore.PACKAGE_BASE + "_magicmace_ice");
		MagicInit.maceIce.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_ice");
		ForgeRegistries.BLOCKS.register(MagicInit.maceIce);
		ForgeRegistries.ITEMS.register(new ItemBlockMaceIce(MagicInit.maceIce));

		MagicInit.maceOcean = new BlockMaceOcean(ClimateCore.PACKAGE_BASE + "_magicmace_ocean");
		MagicInit.maceOcean.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_ocean");
		ForgeRegistries.BLOCKS.register(MagicInit.maceOcean);
		ForgeRegistries.ITEMS.register(new ItemBlockMaceOcean(MagicInit.maceOcean));

		MagicInit.maceBurn = new BlockMaceBurn(ClimateCore.PACKAGE_BASE + "_magicmace_burn");
		MagicInit.maceBurn.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_burn");
		ForgeRegistries.BLOCKS.register(MagicInit.maceBurn);
		ForgeRegistries.ITEMS.register(new ItemBlockMaceBurn(MagicInit.maceBurn));

		MagicInit.maceFlower = new BlockMaceFlower(ClimateCore.PACKAGE_BASE + "_magicmace_flower");
		MagicInit.maceFlower.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_flower");
		ForgeRegistries.BLOCKS.register(MagicInit.maceFlower);
		ForgeRegistries.ITEMS.register(new ItemBlockMaceFlower(MagicInit.maceFlower));

		MagicInit.maceGlory = new BlockMaceGlory(ClimateCore.PACKAGE_BASE + "_magicmace_glory");
		MagicInit.maceGlory.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_glory");
		ForgeRegistries.BLOCKS.register(MagicInit.maceGlory);
		ForgeRegistries.ITEMS.register(new ItemBlockMaceGlory(MagicInit.maceGlory));

		MagicInit.timeCage = new BlockTimeCage(ClimateCore.PACKAGE_BASE + "_time_cage");
		DCMaterialReg.registerBlock(MagicInit.timeCage, ClimateCore.PACKAGE_BASE + "_time_cage", ClimateMain.MOD_ID);
	}

	static void loadCreativeTab() {
		MagicInit.pendant.setCreativeTab(ClimateMain.tool);
		MagicInit.badge.setCreativeTab(ClimateMain.tool);
		MagicInit.amulet.setCreativeTab(ClimateMain.tool);

		MagicInit.daggerSilver.setCreativeTab(ClimateMain.tool);
		MagicInit.daggerMagic.setCreativeTab(ClimateMain.tool);

		MagicInit.macehandle.setCreativeTab(ClimateMain.tool);
		MagicInit.maceStarItem.setCreativeTab(ClimateMain.tool);
		MagicInit.maceSun.setCreativeTab(ClimateMain.tool);
		MagicInit.maceMoon.setCreativeTab(ClimateMain.tool);
		MagicInit.maceBird.setCreativeTab(ClimateMain.tool);
		MagicInit.maceIce.setCreativeTab(ClimateMain.tool);
		MagicInit.maceOcean.setCreativeTab(ClimateMain.tool);
		MagicInit.maceBurn.setCreativeTab(ClimateMain.tool);
		MagicInit.maceFlower.setCreativeTab(ClimateMain.tool);
		MagicInit.maceGlory.setCreativeTab(ClimateMain.tool);

		MagicInit.elestial.setCreativeTab(ClimateCore.climate);
		MagicInit.expGem.setCreativeTab(ClimateCore.climate);
		MagicInit.lotusCandle.setCreativeTab(ClimateMain.build);
		MagicInit.lotusCandleBlack.setCreativeTab(ClimateMain.build);

		MagicInit.biomeOrb.setCreativeTab(ClimateMain.build);
		MagicInit.timeCage.setCreativeTab(ClimateMain.build);

		// MagicInit.clusterIce.setCreativeTab(ClimateMain.tool);
		// MagicInit.infernalFlame.setCreativeTab(ClimateMain.tool);
	}
}
