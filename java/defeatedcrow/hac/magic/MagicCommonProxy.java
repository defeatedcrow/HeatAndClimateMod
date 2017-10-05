package defeatedcrow.hac.magic;

import defeatedcrow.hac.core.ClimateCore;
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
import defeatedcrow.hac.main.ClimateMain;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MagicCommonProxy {

	public static void loadEntity() {
		EntityRegistry.registerModEntity(EntityProjSilver.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_silver",
				100, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjChalW.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_0", 101,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjChalB.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_1", 102,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjChalR.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_2", 103,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjSapW.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_3", 104,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjSapB.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_4", 105,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjSapR.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_5", 106,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjCryM.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_6", 107,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjCryC.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_7", 108,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjCryL.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_8", 109,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjLapC.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_9", 110,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjLapS.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_10", 111,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjLapM.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_11", 112,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjSchB.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_12", 113,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjSchC.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_13", 114,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjClmD.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_14", 115,
				ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjClmM.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_15", 116,
				ClimateMain.instance, 128, 5, true);

		EntityRegistry.registerModEntity(EntityMobBarrier.class, ClimateCore.PACKAGE_BASE + "entity.magic.circle_0",
				117, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjBarrier.class, ClimateCore.PACKAGE_BASE + "entity.magic.circle_1",
				118, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityHealBarrier.class, ClimateCore.PACKAGE_BASE + "entity.magic.circle_2",
				119, ClimateMain.instance, 128, 5, true);

		EntityRegistry.registerModEntity(EntityProjLightSpit.class,
				ClimateCore.PACKAGE_BASE + "entity.magic.proj_light", 120, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjIceSpit.class, ClimateCore.PACKAGE_BASE + "entity.magic.proj_ice",
				121, ClimateMain.instance, 128, 5, true);
		EntityRegistry.registerModEntity(EntityProjWhiteSpit.class,
				ClimateCore.PACKAGE_BASE + "entity.magic.proj_white", 122, ClimateMain.instance, 128, 5, true);
	}

	public static void loadTE() {
		GameRegistry.registerTileEntity(TileIceCluster.class, "dcs_te_cluster_ice");
		GameRegistry.registerTileEntity(TileInfernalFlame.class, "dcs_te_infernal_flame");
		GameRegistry.registerTileEntity(TileLotusCandle.class, "dcs_te_lotus_candle");
		GameRegistry.registerTileEntity(TileLotusCandleBlack.class, "dcs_te_lotus_candle_black");

		GameRegistry.registerTileEntity(TileMaceLight.class, "dcs_te_magicmace_light");
		GameRegistry.registerTileEntity(TileMaceMoon.class, "dcs_te_magicmace_moon");
		GameRegistry.registerTileEntity(TileMaceBird.class, "dcs_te_magicmace_bird");
		GameRegistry.registerTileEntity(TileMaceIce.class, "dcs_te_magicmace_ice");
		GameRegistry.registerTileEntity(TileMaceOcean.class, "dcs_te_magicmace_ocean");
		GameRegistry.registerTileEntity(TileMaceBurn.class, "dcs_te_magicmace_burn");
		GameRegistry.registerTileEntity(TileMaceFlower.class, "dcs_te_magicmace_flower");
		GameRegistry.registerTileEntity(TileMaceGlory.class, "dcs_te_magicmace_glory");

		GameRegistry.registerTileEntity(TileTimeCage.class, "dcs_te_time_cage");
	}

}
