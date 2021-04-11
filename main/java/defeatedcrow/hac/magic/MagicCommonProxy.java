package defeatedcrow.hac.magic;

import defeatedcrow.hac.magic.block.TileCubeFlame;
import defeatedcrow.hac.magic.block.TileCubeIce;
import defeatedcrow.hac.magic.block.TileLotusCandle;
import defeatedcrow.hac.magic.block.TileLotusCandleBlack;
import defeatedcrow.hac.magic.block.TilePictureBG;
import defeatedcrow.hac.magic.block.TilePictureGU;
import defeatedcrow.hac.magic.block.TilePictureRW;
import defeatedcrow.hac.magic.block.TilePictureUR;
import defeatedcrow.hac.magic.block.TilePictureWB;
import defeatedcrow.hac.magic.block.TileTimeCage;
import defeatedcrow.hac.magic.block.TileVeinBeacon;
import defeatedcrow.hac.magic.entity.EntityBlackDog;
import defeatedcrow.hac.magic.entity.EntityFlowerTurret;
import defeatedcrow.hac.magic.entity.EntityMagicCushion;
import defeatedcrow.hac.magic.proj.EntityFireBarrier;
import defeatedcrow.hac.magic.proj.EntityHealBarrier;
import defeatedcrow.hac.magic.proj.EntityMobBarrier;
import defeatedcrow.hac.magic.proj.EntityProjBarrier;
import defeatedcrow.hac.magic.proj.EntityProjBlackSpit;
import defeatedcrow.hac.magic.proj.EntityProjIceSpit;
import defeatedcrow.hac.magic.proj.EntityProjLightSpit;
import defeatedcrow.hac.magic.proj.EntityProjRedSpit;
import defeatedcrow.hac.magic.proj.EntityProjWhiteSpit;
import defeatedcrow.hac.main.util.DCRegistryUtil;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MagicCommonProxy {

	public static void loadEntity() {
		DCRegistryUtil.addEntity(EntityMobBarrier.class, "magic", "circle_0");
		DCRegistryUtil.addEntity(EntityProjBarrier.class, "magic", "circle_1");
		DCRegistryUtil.addEntity(EntityHealBarrier.class, "magic", "circle_2");
		DCRegistryUtil.addEntity(EntityFireBarrier.class, "magic", "circle_3");

		DCRegistryUtil.addEntity(EntityProjLightSpit.class, "magic", "proj_light", 1);
		DCRegistryUtil.addEntity(EntityProjIceSpit.class, "magic", "proj_ice", 1);
		DCRegistryUtil.addEntity(EntityProjWhiteSpit.class, "magic", "proj_white", 1);
		DCRegistryUtil.addEntity(EntityProjRedSpit.class, "magic", "proj_red", 1);
		DCRegistryUtil.addEntity(EntityProjBlackSpit.class, "magic", "proj_black", 1);

		DCRegistryUtil.addEntity(EntityBlackDog.class, "magic", "dcs_blackdog", 5);

		DCRegistryUtil.addEntity(EntityFlowerTurret.class, "magic", "dcs_magic_flower_turret", 5);

		DCRegistryUtil.addEntity(EntityMagicCushion.class, "magic", "dcs_magic_cushion", 5);
	}

	public static void loadTE() {
		GameRegistry.registerTileEntity(TileCubeIce.class, "dcs_te_cube_ice");
		GameRegistry.registerTileEntity(TileCubeFlame.class, "dcs_te_cube_flame");
		GameRegistry.registerTileEntity(TileLotusCandle.class, "dcs_te_lotus_candle");
		GameRegistry.registerTileEntity(TileLotusCandleBlack.class, "dcs_te_lotus_candle_black");
		GameRegistry.registerTileEntity(TileVeinBeacon.class, "dcs_te_vein_beacon");

		GameRegistry.registerTileEntity(TileTimeCage.class, "dcs_te_time_cage");

		GameRegistry.registerTileEntity(TilePictureUR.class, "dcs_te_picture_u");
		GameRegistry.registerTileEntity(TilePictureRW.class, "dcs_te_picture_r");
		GameRegistry.registerTileEntity(TilePictureWB.class, "dcs_te_picture_w");
		GameRegistry.registerTileEntity(TilePictureBG.class, "dcs_te_picture_b");
		GameRegistry.registerTileEntity(TilePictureGU.class, "dcs_te_picture_g");
	}

}
