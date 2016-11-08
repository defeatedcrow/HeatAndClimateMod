package defeatedcrow.hac.magic;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.magic.block.BlockElestial;
import defeatedcrow.hac.magic.block.BlockIceCluster;
import defeatedcrow.hac.magic.block.BlockInfernalFlame;
import defeatedcrow.hac.magic.block.BlockMaceBird;
import defeatedcrow.hac.magic.block.BlockMaceIce;
import defeatedcrow.hac.magic.block.BlockMaceLight;
import defeatedcrow.hac.magic.block.BlockMaceMoon;
import defeatedcrow.hac.magic.block.BlockMaceOcean;
import defeatedcrow.hac.magic.block.ItemBlockMaceBird;
import defeatedcrow.hac.magic.block.ItemBlockMaceIce;
import defeatedcrow.hac.magic.block.ItemBlockMaceLight;
import defeatedcrow.hac.magic.block.ItemBlockMaceMoon;
import defeatedcrow.hac.magic.block.ItemBlockMaceOcean;
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

	private MagicInitRegister() {
	}

	public static void load() {
		loadBlocks();
		loadItems();

		if (ModuleConfig.magic)
			loadCreativeTab();
	}

	static void loadItems() {
		MagicInit.pendant = new ItemMagicalPendant(10).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_jewel_pendant");
		GameRegistry.register(MagicInit.pendant.setRegistryName(ClimateCore.PACKAGE_BASE + "_jewel_pendant"));

		MagicInit.badge = new ItemMagicalBadge(10).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_jewel_badge");
		GameRegistry.register(MagicInit.badge.setRegistryName(ClimateCore.PACKAGE_BASE + "_jewel_badge"));

		MagicInit.daggerSilver = new ItemSilverDagger().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_dagger_silver");
		GameRegistry.register(MagicInit.daggerSilver.setRegistryName(ClimateCore.PACKAGE_BASE + "_dagger_silver"));

		MagicInit.daggerMagic = new ItemMagicDagger().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_dagger_magic");
		GameRegistry.register(MagicInit.daggerMagic.setRegistryName(ClimateCore.PACKAGE_BASE + "_dagger_magic"));

		MagicInit.macehandle = new ItemMaceHandle(0).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_mace_handle");
		GameRegistry.register(MagicInit.macehandle.setRegistryName(ClimateCore.PACKAGE_BASE + "_mace_handle"));

		MagicInit.maceStarItem = new ItemMaceCore(4).setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_macecore");
		GameRegistry.register(MagicInit.maceStarItem.setRegistryName(ClimateCore.PACKAGE_BASE + "_macecore"));
	}

	static void loadBlocks() {
		MagicInit.clusterIce = new BlockIceCluster(ClimateCore.PACKAGE_BASE + "_cluster_ice");
		MainMaterialRegister.registerBlock(MagicInit.clusterIce, ClimateCore.PACKAGE_BASE + "_cluster_ice");

		MagicInit.infernalFlame = new BlockInfernalFlame(ClimateCore.PACKAGE_BASE + "_infernal_flame");
		MainMaterialRegister.registerBlock(MagicInit.infernalFlame, ClimateCore.PACKAGE_BASE + "_infernal_flame");

		MagicInit.elestial = new BlockElestial(Material.IRON, ClimateCore.PACKAGE_BASE + "_ore_elestial");
		MainMaterialRegister.registerBlock(MagicInit.elestial, ClimateCore.PACKAGE_BASE + "_ore_elestial");

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

		MagicInit.elestial.setCreativeTab(ClimateCore.climate);

		// MagicInit.clusterIce.setCreativeTab(ClimateMain.tool);
		// MagicInit.infernalFlame.setCreativeTab(ClimateMain.tool);
	}
}
