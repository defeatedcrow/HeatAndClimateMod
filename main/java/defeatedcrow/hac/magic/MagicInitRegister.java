package defeatedcrow.hac.magic;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCMaterialReg;
import defeatedcrow.hac.magic.block.BlockBiomeGlass;
import defeatedcrow.hac.magic.block.BlockColdLamp;
import defeatedcrow.hac.magic.block.BlockColorCube;
import defeatedcrow.hac.magic.block.BlockCubeFlame;
import defeatedcrow.hac.magic.block.BlockCubeIce;
import defeatedcrow.hac.magic.block.BlockElestial;
import defeatedcrow.hac.magic.block.BlockLotusCandle;
import defeatedcrow.hac.magic.block.BlockPictureBG;
import defeatedcrow.hac.magic.block.BlockPictureGU;
import defeatedcrow.hac.magic.block.BlockPictureRW;
import defeatedcrow.hac.magic.block.BlockPictureUR;
import defeatedcrow.hac.magic.block.BlockPictureWB;
import defeatedcrow.hac.magic.block.BlockTimeCage;
import defeatedcrow.hac.magic.block.BlockVeinBeacon;
import defeatedcrow.hac.magic.item.ItemArmorGemBoots;
import defeatedcrow.hac.magic.item.ItemColorBadge;
import defeatedcrow.hac.magic.item.ItemColorCard;
import defeatedcrow.hac.magic.item.ItemColorCard2;
import defeatedcrow.hac.magic.item.ItemColorCard3;
import defeatedcrow.hac.magic.item.ItemColorDrop;
import defeatedcrow.hac.magic.item.ItemColorGauntlet;
import defeatedcrow.hac.magic.item.ItemColorPendant;
import defeatedcrow.hac.magic.item.ItemColorPendant2;
import defeatedcrow.hac.magic.item.ItemColorRing;
import defeatedcrow.hac.magic.item.ItemColorRing2;
import defeatedcrow.hac.magic.item.ItemEXPGem;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.DCArmorMaterial;
import defeatedcrow.hac.main.util.DCMaterialEnum;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EntityEquipmentSlot;

public class MagicInitRegister {

	private MagicInitRegister() {}

	public static void load() {
		if (ModuleConfig.magic) {
			loadBlocks();
			loadItems();

			loadCreativeTab();
		}
	}

	static void loadItems() {

		MagicInit.expGem = new ItemEXPGem().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_expgem");
		DCMaterialReg.registerItem(MagicInit.expGem, ClimateCore.PACKAGE_BASE + "_expgem", ClimateMain.MOD_ID);

		MagicInit.colorDrop = new ItemColorDrop().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_color");
		DCMaterialReg.registerItem(MagicInit.colorDrop, ClimateCore.PACKAGE_BASE + "_color", ClimateMain.MOD_ID);

		MagicInit.colorRing = new ItemColorRing().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_color_ring");
		DCMaterialReg.registerItem(MagicInit.colorRing, ClimateCore.PACKAGE_BASE + "_color_ring", ClimateMain.MOD_ID);

		if (ModuleConfig.magic_advanced) {

			MagicInit.colorRing2 = new ItemColorRing2().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_color_ring2");
			DCMaterialReg
					.registerItem(MagicInit.colorRing2, ClimateCore.PACKAGE_BASE + "_color_ring2", ClimateMain.MOD_ID);

			MagicInit.colorPendant = new ItemColorPendant()
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_color_pendant");
			DCMaterialReg
					.registerItem(MagicInit.colorPendant, ClimateCore.PACKAGE_BASE + "_color_pendant", ClimateMain.MOD_ID);

			MagicInit.colorPendant2 = new ItemColorPendant2()
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_color_pendant2");
			DCMaterialReg
					.registerItem(MagicInit.colorPendant2, ClimateCore.PACKAGE_BASE + "_color_pendant2", ClimateMain.MOD_ID);

			MagicInit.colorBadge = new ItemColorBadge().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_color_badge");
			DCMaterialReg
					.registerItem(MagicInit.colorBadge, ClimateCore.PACKAGE_BASE + "_color_badge", ClimateMain.MOD_ID);

			MagicInit.colorGauntlet = new ItemColorGauntlet()
					.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_color_gauntlet");
			DCMaterialReg
					.registerItem(MagicInit.colorGauntlet, ClimateCore.PACKAGE_BASE + "_color_gauntlet", ClimateMain.MOD_ID);

			MagicInit.magicCard = new ItemColorCard().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_magic_card");
			DCMaterialReg
					.registerItem(MagicInit.magicCard, ClimateCore.PACKAGE_BASE + "_magic_card", ClimateMain.MOD_ID);

			MagicInit.magicCard3 = new ItemColorCard3().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_magic_card_m2");
			DCMaterialReg
					.registerItem(MagicInit.magicCard3, ClimateCore.PACKAGE_BASE + "_magic_card_m2", ClimateMain.MOD_ID);

			MagicInit.magicCard2 = new ItemColorCard2().setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_magic_card_m");
			DCMaterialReg
					.registerItem(MagicInit.magicCard2, ClimateCore.PACKAGE_BASE + "_magic_card_m", ClimateMain.MOD_ID);

			MagicInit.gemBootsBird = new ItemArmorGemBoots(DCArmorMaterial.DC_CHALCEDONY, DCMaterialEnum.CHALCEDONY,
					EntityEquipmentSlot.FEET, "blue").setCreativeTab(ClimateMain.magic)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_gemboots_blue");
			DCMaterialReg
					.registerItem(MagicInit.gemBootsBird, ClimateCore.PACKAGE_BASE + "_gemboots_blue", ClimateMain.MOD_ID);

			MagicInit.gemBootsFish = new ItemArmorGemBoots(DCArmorMaterial.DC_CHALCEDONY, DCMaterialEnum.CHALCEDONY,
					EntityEquipmentSlot.FEET, "green").setCreativeTab(ClimateMain.magic)
							.setUnlocalizedName(ClimateCore.PACKAGE_BASE + "_gemboots_green");
			DCMaterialReg
					.registerItem(MagicInit.gemBootsFish, ClimateCore.PACKAGE_BASE + "_gemboots_green", ClimateMain.MOD_ID);
		}
	}

	static void loadBlocks() {
		MagicInit.colorCube = new BlockColorCube(Material.GLASS, ClimateCore.PACKAGE_BASE + "_color_cube");
		DCMaterialReg.registerBlock(MagicInit.colorCube, ClimateCore.PACKAGE_BASE + "_color_cube", ClimateMain.MOD_ID);

		MagicInit.clusterIce = new BlockCubeIce(ClimateCore.PACKAGE_BASE + "_cube_ice");
		DCMaterialReg.registerBlock(MagicInit.clusterIce, ClimateCore.PACKAGE_BASE + "_cube_ice", ClimateMain.MOD_ID);

		MagicInit.infernalFlame = new BlockCubeFlame(ClimateCore.PACKAGE_BASE + "_cube_flame");
		DCMaterialReg
				.registerBlock(MagicInit.infernalFlame, ClimateCore.PACKAGE_BASE + "_cube_flame", ClimateMain.MOD_ID);

		MagicInit.veinBeacon = new BlockVeinBeacon(ClimateCore.PACKAGE_BASE + "_beacon_vein");
		DCMaterialReg
				.registerBlock(MagicInit.veinBeacon, ClimateCore.PACKAGE_BASE + "_beacon_vein", ClimateMain.MOD_ID);

		MagicInit.elestial = new BlockElestial(Material.GLASS, ClimateCore.PACKAGE_BASE + "_ore_elestial");
		DCMaterialReg.registerBlock(MagicInit.elestial, ClimateCore.PACKAGE_BASE + "_ore_elestial", ClimateMain.MOD_ID);

		MagicInit.lotusCandle = new BlockLotusCandle(ClimateCore.PACKAGE_BASE + "_lotus_candle_white", false);
		DCMaterialReg
				.registerBlock(MagicInit.lotusCandle, ClimateCore.PACKAGE_BASE + "_lotus_candle_white", ClimateMain.MOD_ID);

		MagicInit.lotusCandleBlack = new BlockLotusCandle(ClimateCore.PACKAGE_BASE + "_lotus_candle_black", true);
		DCMaterialReg
				.registerBlock(MagicInit.lotusCandleBlack, ClimateCore.PACKAGE_BASE + "_lotus_candle_black", ClimateMain.MOD_ID);

		MagicInit.coldLamp = new BlockColdLamp(ClimateCore.PACKAGE_BASE + "_build_coldlamp");
		DCMaterialReg
				.registerBlock(MagicInit.coldLamp, ClimateCore.PACKAGE_BASE + "_build_coldlamp", ClimateMain.MOD_ID);

		MagicInit.biomeOrb = new BlockBiomeGlass(ClimateCore.PACKAGE_BASE + "_device_biomeglass");
		DCMaterialReg
				.registerBlock(MagicInit.biomeOrb, ClimateCore.PACKAGE_BASE + "_device_biomeglass", ClimateMain.MOD_ID);

		if (ModuleConfig.magic_advanced) {

			MagicInit.pictureBlue = new BlockPictureUR(ClimateCore.PACKAGE_BASE + "_magic_picture_u");
			DCMaterialReg
					.registerBlock(MagicInit.pictureBlue, ClimateCore.PACKAGE_BASE + "_magic_picture_u", ClimateMain.MOD_ID);

			MagicInit.pictureGreen = new BlockPictureGU(ClimateCore.PACKAGE_BASE + "_magic_picture_g");
			DCMaterialReg
					.registerBlock(MagicInit.pictureGreen, ClimateCore.PACKAGE_BASE + "_magic_picture_g", ClimateMain.MOD_ID);

			MagicInit.pictureRed = new BlockPictureRW(ClimateCore.PACKAGE_BASE + "_magic_picture_r");
			DCMaterialReg
					.registerBlock(MagicInit.pictureRed, ClimateCore.PACKAGE_BASE + "_magic_picture_r", ClimateMain.MOD_ID);

			MagicInit.pictureBlack = new BlockPictureBG(ClimateCore.PACKAGE_BASE + "_magic_picture_b");
			DCMaterialReg
					.registerBlock(MagicInit.pictureBlack, ClimateCore.PACKAGE_BASE + "_magic_picture_b", ClimateMain.MOD_ID);

			MagicInit.pictureWhite = new BlockPictureWB(ClimateCore.PACKAGE_BASE + "_magic_picture_w");
			DCMaterialReg
					.registerBlock(MagicInit.pictureWhite, ClimateCore.PACKAGE_BASE + "_magic_picture_w", ClimateMain.MOD_ID);

			MagicInit.timeCage = new BlockTimeCage(ClimateCore.PACKAGE_BASE + "_time_cage");
			DCMaterialReg
					.registerBlock(MagicInit.timeCage, ClimateCore.PACKAGE_BASE + "_time_cage", ClimateMain.MOD_ID);

		}
	}

	static void loadCreativeTab() {
		MagicInit.colorDrop.setCreativeTab(ClimateMain.magic);
		MagicInit.colorRing.setCreativeTab(ClimateMain.magic);
		MagicInit.colorRing2.setCreativeTab(ClimateMain.magic);
		MagicInit.colorPendant2.setCreativeTab(ClimateMain.magic);

		MagicInit.colorCube.setCreativeTab(ClimateMain.magic);
		MagicInit.expGem.setCreativeTab(ClimateMain.magic);
		MagicInit.lotusCandle.setCreativeTab(ClimateMain.magic);
		MagicInit.lotusCandleBlack.setCreativeTab(ClimateMain.magic);

		MagicInit.coldLamp.setCreativeTab(ClimateMain.magic);
		MagicInit.biomeOrb.setCreativeTab(ClimateMain.magic);

		if (ModuleConfig.magic_advanced) {

			MagicInit.colorPendant.setCreativeTab(ClimateMain.magic);
			MagicInit.colorBadge.setCreativeTab(ClimateMain.magic);
			MagicInit.colorGauntlet.setCreativeTab(ClimateMain.magic);
			MagicInit.magicCard.setCreativeTab(ClimateMain.magic);
			MagicInit.magicCard3.setCreativeTab(ClimateMain.magic);
			MagicInit.magicCard2.setCreativeTab(ClimateMain.magic);

			MagicInit.pictureBlue.setCreativeTab(ClimateMain.magic);
			MagicInit.pictureGreen.setCreativeTab(ClimateMain.magic);
			MagicInit.pictureRed.setCreativeTab(ClimateMain.magic);
			MagicInit.pictureBlack.setCreativeTab(ClimateMain.magic);
			MagicInit.pictureWhite.setCreativeTab(ClimateMain.magic);

			MagicInit.timeCage.setCreativeTab(ClimateMain.magic);

		}

		// MagicInit.clusterIce.setCreativeTab(ClimateMain.magic);
		// MagicInit.infernalFlame.setCreativeTab(ClimateMain.magic);
	}
}
