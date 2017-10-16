package defeatedcrow.hac.magic;

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
import defeatedcrow.hac.main.worldgen.DCRegistryUtil;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MagicCommonProxy {

	public static void loadEntity() {
		DCRegistryUtil.addEntity(EntityProjSilver.class, "magic", "proj_silver");
		DCRegistryUtil.addEntity(EntityProjChalW.class, "magic", "proj_0");
		DCRegistryUtil.addEntity(EntityProjChalB.class, "magic", "proj_1");
		DCRegistryUtil.addEntity(EntityProjChalR.class, "magic", "proj_2");
		DCRegistryUtil.addEntity(EntityProjSapW.class, "magic", "proj_3");
		DCRegistryUtil.addEntity(EntityProjSapB.class, "magic", "proj_4");
		DCRegistryUtil.addEntity(EntityProjSapR.class, "magic", "proj_5");
		DCRegistryUtil.addEntity(EntityProjCryM.class, "magic", "proj_6");
		DCRegistryUtil.addEntity(EntityProjCryC.class, "magic", "proj_7");
		DCRegistryUtil.addEntity(EntityProjCryL.class, "magic", "proj_8");
		DCRegistryUtil.addEntity(EntityProjLapC.class, "magic", "proj_9");
		DCRegistryUtil.addEntity(EntityProjLapS.class, "magic", "proj_10");
		DCRegistryUtil.addEntity(EntityProjLapM.class, "magic", "proj_11");
		DCRegistryUtil.addEntity(EntityProjSchB.class, "magic", "proj_12");
		DCRegistryUtil.addEntity(EntityProjSchC.class, "magic", "proj_13");
		DCRegistryUtil.addEntity(EntityProjClmD.class, "magic", "proj_14");
		DCRegistryUtil.addEntity(EntityProjClmM.class, "magic", "proj_15");

		DCRegistryUtil.addEntity(EntityMobBarrier.class, "magic", "circle_0");
		DCRegistryUtil.addEntity(EntityProjBarrier.class, "magic", "circle_1");
		DCRegistryUtil.addEntity(EntityHealBarrier.class, "magic", "circle_2");

		DCRegistryUtil.addEntity(EntityProjLightSpit.class, "magic", "proj_light");
		DCRegistryUtil.addEntity(EntityProjIceSpit.class, "magic", "proj_ice");
		DCRegistryUtil.addEntity(EntityProjWhiteSpit.class, "magic", "proj_white");
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
