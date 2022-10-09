package defeatedcrow.hac.core.config;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.EnumSeason;
import defeatedcrow.hac.core.climate.register.ArmorItemRegister;
import defeatedcrow.hac.core.climate.register.ArmorMaterialRegister;
import defeatedcrow.hac.core.climate.register.BlockClimateRegister;
import defeatedcrow.hac.core.climate.register.MobResistanceRegister;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public class CoreConfigDC {
	private CoreConfigDC() {}

	public static final CoreConfigDC INSTANCE = new CoreConfigDC();

	// debug
	public static String debugPass = "Input the password here";
	private final String BR = System.getProperty("line.separator");
	public static EnumSeason debugForceSeason = null;

	// key
	public static int charmWarpKey = 0x2D; // X
	public static int gauntletKey = 0x2E; // C
	public static int sitKey = 0x0F; // Tab

	public static int altJumpKey = -1;
	public static int altSneakKey = -1;

	public static int[] ranges = new int[] { 2, 1, 1 };
	public static double[] seasonEffects = new double[] { 0.05D, 0.4D, -0.05D, -0.4D };
	public static double[] weatherEffects = new double[] { -0.2D, 0.2D };
	public static double nightEffect = -0.2D;

	public static boolean wall = true;

	// render
	public static boolean showAltTips = true;
	public static boolean showDamageIcon = true;
	public static int iconX = 0;
	public static int iconY = 0;
	public static double waterFix = 0.01D;
	public static double lavaFix = 0.01D;
	public static boolean hudEffect = true;

	public static boolean enableHUD = true;
	public static int HUD_color = 0;
	public static int[] offsetHUD = { 3, 160 };
	public static boolean showBiome = true;
	public static boolean showSeason = true;
	public static boolean showDay = true;
	public static boolean showClimate = true;
	public static int[] offsetBiome = { 0, -30 };
	public static int[] offsetSeason = { 0, -10 };
	public static int[] offsetClimate = { 34, 2 };

	// difficulty
	public static boolean climateDam = true;
	public static boolean peacefulDam = false;
	public static int damageDifficulty = 1; // 0-2
	public static boolean burntFood = false;
	public static double food_amount = 1.0D;

	// entity
	public static boolean mobClimateDamage = true;
	public static boolean sharePotionWithRidingMob = true;
	public static int entityInterval = 100;
	public static String[] entityBlackList = new String[] { "minecraft:squid", "minecraft:bat", "minecraft:villager", "ModID:entityRegistryName" };
	public static final List<EntityType<?>> blackListEntity = Lists.newArrayList();

	// recipe
	public static boolean enableVanilla = false;
	public static boolean enableVanillaCrop = true;
	public static boolean enableFarmland = true;
	public static boolean enableIce = true;
	public static boolean enableSnow = true;
	public static int updateFrequency = 3;
	public static boolean disableCustomRecipe = false;
	public static boolean enableDropItemSmelting = false;

	// world
	public static boolean enableFreezeDrop = true;
	public static boolean enableDeepWater = true;
	public static boolean enableUnderLake = true;
	public static boolean enableForestLake = true;
	public static int droughtFrequency = 15;
	public static boolean enableSeasonEffect = true;
	public static boolean enableSeasonTemp = true;
	public static boolean enableWeatherEffect = true;
	public static boolean enableTimeEffect = true;
	public static boolean enableSubmergedCave = false;
	public static int cropUpdateInterval = 80;

	// time
	public static int yearLength = 240;
	public static boolean enableRealTime = false;
	public static boolean enableSouthernHemisphere = false;
	public static int[] springDate = { 59, 150 };
	public static int[] summerDate = { 151, 242 };
	public static int[] autumnDate = { 243, 303 };
	public static int[] winterDate = { 304, 58 };
	public static int[] dayTime = { 6, 17 };
	public static EnumSeason overYear = EnumSeason.WINTER;
	public static int startDate = 40;
	public static String dateFormat = "yyyy/MM/dd";

	// hardmode
	public static boolean harderVanilla = false;
	public static boolean infernalInferno = false;
	public static boolean enableSuffocation = false;
	public static boolean enableHumidity = false;
	public static boolean tightUnderworld = false;
	public static boolean harderMachine = false;
	public static boolean harderCrop = false;
	public static boolean harderMagic = false;
	public static int harderMagicCost = 0;
	public static double harderMagicCostAmount = 1.0D;

	public static String[] updateBlackList = new String[] { "minecraft:leaves", "minecraft:leaves2", "minecraft:tallgrass", "ModID:sampleBlock" };
	public static final List<Block> blackListBlock = Lists.newArrayList();

	public static double getSeasonTempOffset(EnumSeason season) {
		switch (season) {
		case AUTUMN:
			return seasonEffects[2];
		case SPRING:
			return seasonEffects[0];
		case SUMMER:
			return seasonEffects[1];
		case WINTER:
			return seasonEffects[3];
		default:
			return seasonEffects[0];

		}
	}

	public static void leadBlockNames() {
		blackListBlock.clear();
		blackListEntity.clear();
		blackListBlock.addAll(DCUtil.getBlockListFromStrings(updateBlackList, "Tick Update Invalid List"));
		blackListEntity.addAll(DCUtil.getEntityListFromStrings(entityBlackList, "Climate Damage Invalid List"));
	}

	static String getFormat(String s) {
		if (s == null) {
			return "yyyy/MM/dd";
		} else {
			if (s.contains("yyyy") && s.contains("MM") && s.contains("dd")) {
				return s;
			} else {
				return "yyyy/MM/dd";
			}
		}
	}

	public static void loadConfig() {
		BlockClimateRegister.loadFiles();
		ArmorItemRegister.loadFiles();
		ArmorMaterialRegister.loadFiles();
		MobResistanceRegister.loadFiles();
		VeinTableConfig.loadFiles();

		BlockClimateRegister.initFile();
		ArmorItemRegister.initFile();
		ArmorMaterialRegister.initFile();
		MobResistanceRegister.initFile();
		VeinTableConfig.initFile();

	}
}
