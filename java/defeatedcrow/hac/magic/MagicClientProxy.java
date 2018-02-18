package defeatedcrow.hac.magic;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.client.JsonRegisterHelper;
import defeatedcrow.hac.magic.block.TileIceCluster;
import defeatedcrow.hac.magic.block.TileInfernalFlame;
import defeatedcrow.hac.magic.block.TileLotusCandle;
import defeatedcrow.hac.magic.block.TileLotusCandleBlack;
import defeatedcrow.hac.magic.block.TileMaceBird;
import defeatedcrow.hac.magic.block.TileMaceBurn;
import defeatedcrow.hac.magic.block.TileMaceFlower;
import defeatedcrow.hac.magic.block.TileMaceGlory;
import defeatedcrow.hac.magic.block.TileMaceIce;
import defeatedcrow.hac.magic.block.TileMaceLight;
import defeatedcrow.hac.magic.block.TileMaceMoon;
import defeatedcrow.hac.magic.block.TileMaceOcean;
import defeatedcrow.hac.magic.block.TileTimeCage;
import defeatedcrow.hac.magic.client.HealCircleRenderer;
import defeatedcrow.hac.magic.client.MagicCircleRenderer;
import defeatedcrow.hac.magic.client.MagicDaggerRenderer;
import defeatedcrow.hac.magic.client.MagicIceSpitRenderer;
import defeatedcrow.hac.magic.client.MagicLightSpitRenderer;
import defeatedcrow.hac.magic.client.MagicWhiteSpitRenderer;
import defeatedcrow.hac.magic.client.ProjCircleRenderer;
import defeatedcrow.hac.magic.client.TESRIceCluster;
import defeatedcrow.hac.magic.client.TESRInfernalFlame;
import defeatedcrow.hac.magic.client.TESRLotusCandle;
import defeatedcrow.hac.magic.client.TESRMace;
import defeatedcrow.hac.magic.client.TESRMaceBird;
import defeatedcrow.hac.magic.client.TESRMaceBurn;
import defeatedcrow.hac.magic.client.TESRMaceFlower;
import defeatedcrow.hac.magic.client.TESRMaceGlory;
import defeatedcrow.hac.magic.client.TESRMaceIce;
import defeatedcrow.hac.magic.client.TESRMaceMoon;
import defeatedcrow.hac.magic.client.TESRMaceOcean;
import defeatedcrow.hac.magic.client.TESRTimeCage;
import defeatedcrow.hac.magic.proj.EntityHealBarrier;
import defeatedcrow.hac.magic.proj.EntityMobBarrier;
import defeatedcrow.hac.magic.proj.EntityProjBarrier;
import defeatedcrow.hac.magic.proj.EntityProjChalB;
import defeatedcrow.hac.magic.proj.EntityProjChalR;
import defeatedcrow.hac.magic.proj.EntityProjChalW;
import defeatedcrow.hac.magic.proj.EntityProjClmD;
import defeatedcrow.hac.magic.proj.EntityProjClmM;
import defeatedcrow.hac.magic.proj.EntityProjCryC;
import defeatedcrow.hac.magic.proj.EntityProjCryL;
import defeatedcrow.hac.magic.proj.EntityProjCryM;
import defeatedcrow.hac.magic.proj.EntityProjIceSpit;
import defeatedcrow.hac.magic.proj.EntityProjLapC;
import defeatedcrow.hac.magic.proj.EntityProjLapM;
import defeatedcrow.hac.magic.proj.EntityProjLapS;
import defeatedcrow.hac.magic.proj.EntityProjLightSpit;
import defeatedcrow.hac.magic.proj.EntityProjSapB;
import defeatedcrow.hac.magic.proj.EntityProjSapR;
import defeatedcrow.hac.magic.proj.EntityProjSapW;
import defeatedcrow.hac.magic.proj.EntityProjSchB;
import defeatedcrow.hac.magic.proj.EntityProjSchC;
import defeatedcrow.hac.magic.proj.EntityProjSilver;
import defeatedcrow.hac.magic.proj.EntityProjWhiteSpit;
import defeatedcrow.hac.main.client.ClientMainProxy;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MagicClientProxy {

	public static void loadEntity() {
		ClientMainProxy.registRender(EntityProjSilver.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjChalW.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjChalB.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjChalR.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjSapW.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjSapB.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjSapR.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjCryM.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjCryC.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjCryL.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjLapC.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjLapS.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjLapM.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjSchB.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjSchC.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjClmD.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjClmM.class, MagicDaggerRenderer.class);
		ClientMainProxy.registRender(EntityProjLightSpit.class, MagicLightSpitRenderer.class);
		ClientMainProxy.registRender(EntityProjIceSpit.class, MagicIceSpitRenderer.class);
		ClientMainProxy.registRender(EntityProjWhiteSpit.class, MagicWhiteSpitRenderer.class);

		ClientMainProxy.registRender(EntityMobBarrier.class, MagicCircleRenderer.class);
		ClientMainProxy.registRender(EntityProjBarrier.class, ProjCircleRenderer.class);
		ClientMainProxy.registRender(EntityHealBarrier.class, HealCircleRenderer.class);
	}

	public static void loadTE() {
		ClientRegistry.registerTileEntity(TileIceCluster.class, "dcs_te_cluster_ice", new TESRIceCluster());
		ClientRegistry.registerTileEntity(TileInfernalFlame.class, "dcs_te_infernal_flame", new TESRInfernalFlame());
		ClientRegistry.registerTileEntity(TileLotusCandle.class, "dcs_te_lotus_candle", new TESRLotusCandle());
		ClientRegistry.registerTileEntity(TileLotusCandleBlack.class, "dcs_te_lotus_candle_black",
				new TESRLotusCandle());

		ClientRegistry.registerTileEntity(TileMaceLight.class, "dcs_te_magicmace_light", new TESRMace());
		ClientRegistry.registerTileEntity(TileMaceMoon.class, "dcs_te_magicmace_moon", new TESRMaceMoon().setNoFrame());
		ClientRegistry.registerTileEntity(TileMaceBird.class, "dcs_te_magicmace_bird", new TESRMaceBird());
		ClientRegistry.registerTileEntity(TileMaceIce.class, "dcs_te_magicmace_ice", new TESRMaceIce());
		ClientRegistry.registerTileEntity(TileMaceOcean.class, "dcs_te_magicmace_ocean", new TESRMaceOcean());
		ClientRegistry.registerTileEntity(TileMaceBurn.class, "dcs_te_magicmace_burn", new TESRMaceBurn());
		ClientRegistry.registerTileEntity(TileMaceFlower.class, "dcs_te_magicmace_flower",
				new TESRMaceFlower().setNoFrame());
		ClientRegistry.registerTileEntity(TileMaceGlory.class, "dcs_te_magicmace_glory",
				new TESRMaceGlory().setNoFrame());

		ClientRegistry.registerTileEntity(TileTimeCage.class, "dcs_te_time_cage", new TESRTimeCage());
	}

	public static void regJson(JsonRegisterHelper instance) {
		// item

		instance.regSimpleItem(MagicInit.pendant, ClimateCore.PACKAGE_ID, "dcs_jewel_pendant", "equip", 19);
		instance.regSimpleItem(MagicInit.badge, ClimateCore.PACKAGE_ID, "dcs_jewel_badge", "equip", 19);
		instance.regSimpleItem(MagicInit.daggerSilver, ClimateCore.PACKAGE_ID, "dcs_dagger_silver", "magic", 0);
		instance.regSimpleItem(MagicInit.daggerMagic, ClimateCore.PACKAGE_ID, "dcs_dagger_magic", "magic", 15);
		instance.regSimpleItem(MagicInit.macehandle, ClimateCore.PACKAGE_ID, "dcs_mace_handle", "magic", 0);
		instance.regSimpleItem(MagicInit.maceStarItem, ClimateCore.PACKAGE_ID, "dcs_macecore", "magic", 7);
		instance.regSimpleItem(MagicInit.expGem, ClimateCore.PACKAGE_ID, "dcs_expgem", "magic", 0);
		instance.regSimpleItem(MagicInit.amulet, ClimateCore.PACKAGE_ID, "dcs_jewel_amulet", "equip", 4);

		// block
		instance.regTEBlock(MagicInit.clusterIce, ClimateCore.PACKAGE_ID, "dcs_cluster_ice", "magic", 0);
		instance.regTEBlock(MagicInit.infernalFlame, ClimateCore.PACKAGE_ID, "dcs_infernal_flame", "magic", 0);
		instance.regSimpleBlock(MagicInit.elestial, ClimateCore.PACKAGE_ID, "dcs_ore_elestial", "ores", 0);
		instance.regTEBlock(MagicInit.lotusCandle, ClimateCore.PACKAGE_ID, "dcs_lotus_candle_white", "magic", 0);
		instance.regTEBlock(MagicInit.lotusCandleBlack, ClimateCore.PACKAGE_ID, "dcs_lotus_candle_black", "magic", 0);
		instance.regSimpleBlock(MagicInit.biomeOrb, ClimateCore.PACKAGE_ID, "dcs_magic_biomeglass", "magic", 3);
		instance.regTEBlock(MagicInit.timeCage, ClimateCore.PACKAGE_ID, "dcs_time_cage", "magic", 0);

		instance.regTEBlock(MagicInit.maceSun, ClimateCore.PACKAGE_ID, "dcs_magicmace_light", "magic", 0);
		instance.regTEBlock(MagicInit.maceMoon, ClimateCore.PACKAGE_ID, "dcs_magicmace_moon", "magic", 0);
		instance.regTEBlock(MagicInit.maceBird, ClimateCore.PACKAGE_ID, "dcs_magicmace_bird", "magic", 0);
		instance.regTEBlock(MagicInit.maceIce, ClimateCore.PACKAGE_ID, "dcs_magicmace_ice", "magic", 0);
		instance.regTEBlock(MagicInit.maceOcean, ClimateCore.PACKAGE_ID, "dcs_magicmace_ocean", "magic", 0);
		instance.regTEBlock(MagicInit.maceBurn, ClimateCore.PACKAGE_ID, "dcs_magicmace_burn", "magic", 0);
		instance.regTEBlock(MagicInit.maceFlower, ClimateCore.PACKAGE_ID, "dcs_magicmace_flower", "magic", 0);
		instance.regTEBlock(MagicInit.maceGlory, ClimateCore.PACKAGE_ID, "dcs_magicmace_glory", "magic", 0);
	}

}
