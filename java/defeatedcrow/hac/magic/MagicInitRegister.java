package defeatedcrow.hac.magic;

import net.minecraftforge.fml.common.registry.GameRegistry;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.magic.block.BlockIceCluster;
import defeatedcrow.hac.magic.block.BlockInfernalFlame;
import defeatedcrow.hac.magic.item.ItemMagicDagger;
import defeatedcrow.hac.magic.item.ItemMagicalBadge;
import defeatedcrow.hac.magic.item.ItemMagicalPendant;
import defeatedcrow.hac.magic.item.ItemSilverDagger;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainMaterialRegister;
import defeatedcrow.hac.main.config.ModuleConfig;

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
	}

	static void loadBlocks() {
		MagicInit.clusterIce = new BlockIceCluster(ClimateCore.PACKAGE_BASE + "_cluster_ice");
		MainMaterialRegister.registerBlock(MagicInit.clusterIce, ClimateCore.PACKAGE_BASE + "_cluster_ice");

		MagicInit.infernalFlame = new BlockInfernalFlame(ClimateCore.PACKAGE_BASE + "_infernal_flame");
		MainMaterialRegister.registerBlock(MagicInit.infernalFlame, ClimateCore.PACKAGE_BASE + "_infernal_flame");
	}

	static void loadCreativeTab() {
		MagicInit.pendant.setCreativeTab(ClimateMain.tool);
		MagicInit.badge.setCreativeTab(ClimateMain.tool);

		MagicInit.daggerSilver.setCreativeTab(ClimateMain.tool);
		MagicInit.daggerMagic.setCreativeTab(ClimateMain.tool);

		// MagicInit.clusterIce.setCreativeTab(ClimateMain.tool);
		// MagicInit.infernalFlame.setCreativeTab(ClimateMain.tool);
	}
}
