package defeatedcrow.hac.magic;

import defeatedcrow.hac.core.ClimateCore;
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
import defeatedcrow.hac.magic.item.ItemEXPGem;
import defeatedcrow.hac.magic.item.ItemMaceCore;
import defeatedcrow.hac.magic.item.ItemMaceHandle;
import defeatedcrow.hac.magic.item.ItemMagicDagger;
import defeatedcrow.hac.magic.item.ItemMagicalBadge;
import defeatedcrow.hac.magic.item.ItemMagicalPendant;
import defeatedcrow.hac.magic.item.ItemSilverDagger;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainMaterialRegister;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
		MagicInit.pendant = new ItemMagicalPendant(16).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_jewel_pendant");
		GameRegistry.register(MagicInit.pendant.setRegistryName(ClimateCore.PACKAGE_BASE + "_jewel_pendant"));

		MagicInit.badge = new ItemMagicalBadge(16).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_jewel_badge");
		GameRegistry.register(MagicInit.badge.setRegistryName(ClimateCore.PACKAGE_BASE + "_jewel_badge"));

		MagicInit.daggerSilver = new ItemSilverDagger().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_dagger_silver");
		GameRegistry.register(MagicInit.daggerSilver.setRegistryName(ClimateCore.PACKAGE_BASE + "_dagger_silver"));

		MagicInit.daggerMagic = new ItemMagicDagger().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_dagger_magic");
		GameRegistry.register(MagicInit.daggerMagic.setRegistryName(ClimateCore.PACKAGE_BASE + "_dagger_magic"));

		MagicInit.macehandle = new ItemMaceHandle(0).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_mace_handle");
		GameRegistry.register(MagicInit.macehandle.setRegistryName(ClimateCore.PACKAGE_BASE + "_mace_handle"));

		MagicInit.maceStarItem = new ItemMaceCore(7).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_macecore");
		GameRegistry.register(MagicInit.maceStarItem.setRegistryName(ClimateCore.PACKAGE_BASE + "_macecore"));

		MagicInit.expGem = new ItemEXPGem().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_expgem");
		GameRegistry.register(MagicInit.expGem.setRegistryName(ClimateCore.PACKAGE_BASE + "_expgem"));
	}

	static void loadBlocks() {
		MagicInit.clusterIce = new BlockIceCluster(ClimateCore.PACKAGE_BASE + "_cluster_ice");
		MainMaterialRegister.registerBlock(MagicInit.clusterIce, ClimateCore.PACKAGE_BASE + "_cluster_ice");

		MagicInit.infernalFlame = new BlockInfernalFlame(ClimateCore.PACKAGE_BASE + "_infernal_flame");
		MainMaterialRegister.registerBlock(MagicInit.infernalFlame, ClimateCore.PACKAGE_BASE + "_infernal_flame");

		MagicInit.elestial = new BlockElestial(Material.GLASS, ClimateCore.PACKAGE_BASE + "_ore_elestial");
		MainMaterialRegister.registerBlock(MagicInit.elestial, ClimateCore.PACKAGE_BASE + "_ore_elestial");

		MagicInit.lotusCandle = new BlockLotusCandle(ClimateCore.PACKAGE_BASE + "_lotus_candle_white", false);
		MainMaterialRegister.registerBlock(MagicInit.lotusCandle, ClimateCore.PACKAGE_BASE + "_lotus_candle_white");

		MagicInit.lotusCandleBlack = new BlockLotusCandle(ClimateCore.PACKAGE_BASE + "_lotus_candle_black", true);
		MainMaterialRegister.registerBlock(MagicInit.lotusCandleBlack,
				ClimateCore.PACKAGE_BASE + "_lotus_candle_black");

		MagicInit.biomeOrb = new BlockBiomeGlass(ClimateCore.PACKAGE_BASE + "_device_biomeglass");
		MainMaterialRegister.registerBlock(MagicInit.biomeOrb, ClimateCore.PACKAGE_BASE + "_device_biomeglass");

		MagicInit.maceSun = new BlockMaceLight(ClimateCore.PACKAGE_BASE + "_magicmace_light");
		MagicInit.maceSun.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_light");
		GameRegistry.register(MagicInit.maceSun);
		GameRegistry.register(new ItemBlockMaceLight(MagicInit.maceSun));

		MagicInit.maceMoon = new BlockMaceMoon(ClimateCore.PACKAGE_BASE + "_magicmace_moon");
		MagicInit.maceMoon.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_moon");
		GameRegistry.register(MagicInit.maceMoon);
		GameRegistry.register(new ItemBlockMaceMoon(MagicInit.maceMoon));

		MagicInit.maceBird = new BlockMaceBird(ClimateCore.PACKAGE_BASE + "_magicmace_bird");
		MagicInit.maceBird.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_bird");
		GameRegistry.register(MagicInit.maceBird);
		GameRegistry.register(new ItemBlockMaceBird(MagicInit.maceBird));

		MagicInit.maceIce = new BlockMaceIce(ClimateCore.PACKAGE_BASE + "_magicmace_ice");
		MagicInit.maceIce.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_ice");
		GameRegistry.register(MagicInit.maceIce);
		GameRegistry.register(new ItemBlockMaceIce(MagicInit.maceIce));

		MagicInit.maceOcean = new BlockMaceOcean(ClimateCore.PACKAGE_BASE + "_magicmace_ocean");
		MagicInit.maceOcean.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_ocean");
		GameRegistry.register(MagicInit.maceOcean);
		GameRegistry.register(new ItemBlockMaceOcean(MagicInit.maceOcean));

		MagicInit.maceBurn = new BlockMaceBurn(ClimateCore.PACKAGE_BASE + "_magicmace_burn");
		MagicInit.maceBurn.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_burn");
		GameRegistry.register(MagicInit.maceBurn);
		GameRegistry.register(new ItemBlockMaceBurn(MagicInit.maceBurn));

		MagicInit.maceFlower = new BlockMaceFlower(ClimateCore.PACKAGE_BASE + "_magicmace_flower");
		MagicInit.maceFlower.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_flower");
		GameRegistry.register(MagicInit.maceFlower);
		GameRegistry.register(new ItemBlockMaceFlower(MagicInit.maceFlower));

		MagicInit.maceGlory = new BlockMaceGlory(ClimateCore.PACKAGE_BASE + "_magicmace_glory");
		MagicInit.maceGlory.setRegistryName(ClimateCore.PACKAGE_BASE + "_magicmace_glory");
		GameRegistry.register(MagicInit.maceGlory);
		GameRegistry.register(new ItemBlockMaceGlory(MagicInit.maceGlory));

		MagicInit.timeCage = new BlockTimeCage(ClimateCore.PACKAGE_BASE + "_time_cage");
		MainMaterialRegister.registerBlock(MagicInit.timeCage, ClimateCore.PACKAGE_BASE + "_time_cage");
	}

	static void loadCreativeTab() {
		MagicInit.pendant.setCreativeTab(ClimateMain.tool);
		MagicInit.badge.setCreativeTab(ClimateMain.tool);

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
