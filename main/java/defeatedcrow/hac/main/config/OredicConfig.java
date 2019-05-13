package defeatedcrow.hac.main.config;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.oredict.OreDictionary;

public class OredicConfig {

	private OredicConfig() {}

	public static final OredicConfig INSTANCE = new OredicConfig();
	private final String BR = System.getProperty("line.separator");
	String[][] test = { {} };

	public static String[][] ores = new String[][] { { "oreRed" }, { "oreGreen" }, { "oreBlue" }, { "oreWhite" }, {
		"oreBlack" }, { "oreLargeRed" }, { "oreLargeGreen" }, { "oreLargeBlue" }, { "oreLargeWhite" }, {
			"oreLargeBlack" }, { "oreRed" }, { "oreLargeRed" }, { "oreBlack" }, { "oreLargeBlack" }, { "oreSulfur" } };

	public static String[][] layers = new String[][] { { "oreGypsum" }, { "oreLime" }, { "oreApatite" }, { "oreNiter" },
		{ "oreSulfur" }, { "oreSalt" }, { "oreSerpentine" } };

	public static String[][] items = new String[][] { { "oreCopper" }, { "oreTin" }, { "oreZinc" }, { "oreIron" }, {
		"oreMagnetite" }, { "oreAluminum" }, { "oreNickel" }, { "oreLead" }, { "oreGold" }, { "oreSilver" }, {
			"oreManganese" }, { "oreChrome", "oreChromium" }, { "oreBismuth" }, { "oreTitanium" }, {
				"oreMolybdenum" } };

	public static String[][] ingots = new String[][] { { "ingotCopper" }, { "ingotZinc" }, { "ingotNickel" }, {
		"ingotSilver" }, { "ingotBrass" }, { "ingotSteel" }, { "ingotNickelsilver" }, { "ingotMagnet" }, { "ingotTin" },
		{ "ingotBronze" }, { "ingotSUS", "ingotStainlessSteel" }, { "ingotTitaniumAlloy" }, { "ingotAluminium",
			"ingotAluminum" }, { "ingotBismuth" }, { "ingotBSCCO" }, { "ingotLead" }, { "ingotManganese" }, {
				"ingotToolSteel" } };

	public static String[][] dusts = new String[][] { { "dustCopper" }, { "dustZinc" }, { "dustNickel" }, {
		"dustSilver" }, { "dustGold" }, { "dustIron" }, { "dustSteel" }, { "dustMagnetite" }, { "dustTin" }, {
			"dustChromium", "dyeGreen" }, { "dustTitanium" }, { "dustAluminium", "dustAluminum" }, { "dustBismuth" }, {
				"dustLead" }, { "dustManganese" }, { "dustToolSteel" } };

	public static String[][] metals = new String[][] { { "blockZinc" }, { "blockCopper" }, { "blockTin" }, {
		"blockLead" }, { "blockAluminum" }, { "blockNickel" }, { "blockSilver" }, { "blockBismuth" } };

	public static String[][] alloys = new String[][] { { "blockBrass" }, { "blockBronze" }, { "blockSteel" }, {
		"blockNickelsilver" }, { "blockToolSteel" }, { "blockSUS", "blockStainlessSteel" }, { "blockMagnet" }, {
			"blockTitaniumAlloy" }, { "blockBSCCO" } };

	public static String[][] gems = new String[][] { { "gemChalcedony", "gemChalcedonyBlue" }, { "gemChalcedony",
		"gemChalcedonyRed" }, { "gemChalcedony", "gemChalcedonyWhite" }, { "gemGypsum" }, { "gemSapphire" }, {
			"gemMalachite" }, { "gemCelestite" }, { "gemClam", "gemOpal" }, { "gemSalt" }, { "gemNiter" }, {
				"gemSulfur" }, { "gemSchorl" }, { "gemSerpentine" }, { "gemPeridot", "gemOlivine" }, { "gemGarnet" }, {
					"gemRutile" }, { "gemBauxite" }, { "gemBismuth" }, { "gemApatite" }, { "gemJadeite" }, {
						"gemMoonstone" }, { "gemKunzite", "gemSpodumene" }, { "gemOpal" }, { "gemCrudeOil" }, {
							"gemTausonite" } };

	public static String[][] gemb = new String[][] { { "blockChalcedonyBlue" }, { "blockChalcedonyRed" }, {
		"blockChalcedonyWhite" }, { "blockGypsum" }, { "blockSaphhire" }, { "blockSalt" }, { "blockMarble" }, {
			"blockSchorl" }, {}, { "blockSerpentine" }, { "blockOlivine" }, { "blockGarnet" } };

	public static String[][] gear = new String[][] { { "gearWood" }, { "gearBrass" }, { "gearAlloy",
		"gearNickelsilver" }, { "gearSteel" }, { "gearToolSteel" } };

	public void load(Configuration cfg) {

		// try {
		cfg.load();

		cfg.addCustomCategoryComment("1_ores", "If you want to delete oredic, please empty it.");
		cfg.addCustomCategoryComment("2_metals", "If you want to delete oredic, please empty it.");
		cfg.addCustomCategoryComment("3_gems", "If you want to delete oredic, please empty it.");
		cfg.addCustomCategoryComment("4_gears", "If you want to delete oredic, please empty it.");

		Property o0 = cfg.get("1_ores", "1_00_RedOre", ores[0]);
		Property o1 = cfg.get("1_ores", "1_01_GreenOre", ores[1]);
		Property o2 = cfg.get("1_ores", "1_02_BlueOre", ores[2]);
		Property o3 = cfg.get("1_ores", "1_03_WhiteOre", ores[3]);
		Property o4 = cfg.get("1_ores", "1_04_BlackOre", ores[4]);
		Property o5 = cfg.get("1_ores", "1_05_LargeRedOre", ores[5]);
		Property o6 = cfg.get("1_ores", "1_06_LargeGreenOre", ores[6]);
		Property o7 = cfg.get("1_ores", "1_07_LargeBlueOre", ores[7]);
		Property o8 = cfg.get("1_ores", "1_08_LargeWhiteOre", ores[8]);
		Property o9 = cfg.get("1_ores", "1_09_LargeBlackOre", ores[9]);
		Property o10 = cfg.get("1_ores", "1_10_NetherRedOre", ores[10]);
		Property o11 = cfg.get("1_ores", "1_11_NetherLargeRedOre", ores[11]);
		Property o12 = cfg.get("1_ores", "1_12_NetherBlackOre", ores[12]);
		Property o13 = cfg.get("1_ores", "1_13_NetherlargeBlackOre", ores[13]);
		Property o14 = cfg.get("1_ores", "1_14_NetherSulfurOre", ores[14]);

		Property l0 = cfg.get("1_ores", "2_00_GypsumOre", layers[0]);
		Property l1 = cfg.get("1_ores", "2_01_LimeOre", layers[1]);
		Property l2 = cfg.get("1_ores", "2_02_ApatiteOre", layers[2]);
		Property l3 = cfg.get("1_ores", "2_03_NiterOre", layers[3]);
		Property l4 = cfg.get("1_ores", "2_04_SulfurOre", layers[4]);
		Property l5 = cfg.get("1_ores", "2_05_SaltOre", layers[5]);
		Property l6 = cfg.get("1_ores", "2_06_SerpentineOre", layers[6]);

		Property c0 = cfg.get("1_ores", "3_00_CopperOreItem", items[0]);
		Property c1 = cfg.get("1_ores", "3_01_TinOreItem", items[1]);
		Property c2 = cfg.get("1_ores", "3_02_ZincOreItem", items[2]);
		Property c3 = cfg.get("1_ores", "3_03_IronOreItem", items[3]);
		Property c4 = cfg.get("1_ores", "3_04_MagnetiteOreItem", items[4]);
		Property c5 = cfg.get("1_ores", "3_05_AluminumOreItem", items[5]);
		Property c6 = cfg.get("1_ores", "3_06_NickelOreItem", items[6]);
		Property c7 = cfg.get("1_ores", "3_07_LeadOreItem", items[7]);
		Property c8 = cfg.get("1_ores", "3_08_GoldOreItem", items[8]);
		Property c9 = cfg.get("1_ores", "3_09_SilverOreItem", items[9]);
		Property c10 = cfg.get("1_ores", "3_10_ManganeseOreItem", items[10]);
		Property c11 = cfg.get("1_ores", "3_11_ChromeOreItem", items[11]);
		Property c12 = cfg.get("1_ores", "3_12_BismuthOreItem", items[12]);
		Property c13 = cfg.get("1_ores", "3_13_TitaniumOreItem", items[13]);
		Property c14 = cfg.get("1_ores", "3_14_MolybdenumOreItem", items[14]);

		Property i0 = cfg.get("2_metals", "4_00_CopperIngot", ingots[0]);
		Property i1 = cfg.get("2_metals", "4_01_ZincIngot", ingots[1]);
		Property i2 = cfg.get("2_metals", "4_02_NickelIngot", ingots[2]);
		Property i3 = cfg.get("2_metals", "4_03_SilverIngot", ingots[3]);
		Property i4 = cfg.get("2_metals", "4_04_BrassIngot", ingots[4]);
		Property i5 = cfg.get("2_metals", "4_05_SteelIngot", ingots[5]);
		Property i6 = cfg.get("2_metals", "4_06_NickelsilverIngot", ingots[6]);
		Property i7 = cfg.get("2_metals", "4_07_MagnetIngot", ingots[7]);
		Property i8 = cfg.get("2_metals", "4_08_TinIngot", ingots[8]);
		Property i9 = cfg.get("2_metals", "4_09_BronzeIngot", ingots[9]);
		Property i10 = cfg.get("2_metals", "4_10_SUSIngot", ingots[10]);
		Property i11 = cfg.get("2_metals", "4_11_TitaniumAlloyIngot", ingots[11]);
		Property i12 = cfg.get("2_metals", "4_12_AluminumIngot", ingots[12]);
		Property i13 = cfg.get("2_metals", "4_13_BismuthIngot", ingots[13]);
		Property i14 = cfg.get("2_metals", "4_14_BSCCOIngot", ingots[14]);
		Property i15 = cfg.get("2_metals", "4_15_LeadIngot", ingots[15]);
		Property i16 = cfg.get("2_metals", "4_16_ManganeseIngot", ingots[16]);
		Property i17 = cfg.get("2_metals", "4_17_ToolSteelIngot", ingots[17]);

		Property d0 = cfg.get("2_metals", "5_00_CopperDust", dusts[0]);
		Property d1 = cfg.get("2_metals", "5_01_ZincDust", dusts[1]);
		Property d2 = cfg.get("2_metals", "5_02_NickelDust", dusts[2]);
		Property d3 = cfg.get("2_metals", "5_03_SilverDust", dusts[3]);
		Property d4 = cfg.get("2_metals", "5_04_GoldDust", dusts[4]);
		Property d5 = cfg.get("2_metals", "5_05_IronDust", dusts[5]);
		Property d6 = cfg.get("2_metals", "5_06_SteelDust", dusts[6]);
		Property d7 = cfg.get("2_metals", "5_07_MagnetiteDust", dusts[7]);
		Property d8 = cfg.get("2_metals", "5_08_TinDust", dusts[8]);
		Property d9 = cfg.get("2_metals", "5_09_ChromeDust", dusts[9]);
		Property d10 = cfg.get("2_metals", "5_10_AluminumDust", dusts[10]);
		Property d11 = cfg.get("2_metals", "5_11_TitaniumDust", dusts[11]);
		Property d12 = cfg.get("2_metals", "5_12_BismuthDust", dusts[12]);
		Property d13 = cfg.get("2_metals", "5_13_LeadDust", dusts[13]);
		Property d14 = cfg.get("2_metals", "5_14_ManganeseDust", dusts[14]);
		Property d15 = cfg.get("2_metals", "5_15_MolybdenumDust", dusts[15]);

		Property m0 = cfg.get("2_metals", "6_00_ZincBlock", metals[0]);
		Property m1 = cfg.get("2_metals", "6_01_CopperBlock", metals[1]);
		Property m2 = cfg.get("2_metals", "6_02_TinBlock", metals[2]);
		Property m3 = cfg.get("2_metals", "6_03_LeadBlock", metals[3]);
		Property m4 = cfg.get("2_metals", "6_04_AluminumBlock", metals[4]);
		Property m5 = cfg.get("2_metals", "6_05_NickelBlock", metals[5]);
		Property m6 = cfg.get("2_metals", "6_06_SilverBlock", metals[6]);
		Property m7 = cfg.get("2_metals", "6_07_BismuthBlock", metals[7]);

		Property a0 = cfg.get("2_metals", "7_00_BrassBlock", alloys[0]);
		Property a1 = cfg.get("2_metals", "7_01_BronzeBlock", alloys[1]);
		Property a2 = cfg.get("2_metals", "7_02_SteelBlock", alloys[2]);
		Property a3 = cfg.get("2_metals", "7_03_NickelsilverBlock", alloys[3]);
		Property a4 = cfg.get("2_metals", "7_04_ToolSteelBlock", alloys[4]);
		Property a5 = cfg.get("2_metals", "7_05_SUSBlock", alloys[5]);
		Property a6 = cfg.get("2_metals", "7_06_MagnetBlock", alloys[6]);
		Property a7 = cfg.get("2_metals", "7_07_TitaniumAlloyBlock", alloys[7]);
		Property a8 = cfg.get("2_metals", "7_08_BSCCOBlock", alloys[8]);

		Property g0 = cfg.get("3_gems", "8_00_BlueChalcedony", gems[0]);
		Property g1 = cfg.get("3_gems", "8_01_RedChalcedony", gems[1]);
		Property g2 = cfg.get("3_gems", "8_02_WhiteChalcedony", gems[2]);
		Property g3 = cfg.get("3_gems", "8_03_Gypsum", gems[3]);
		Property g4 = cfg.get("3_gems", "8_04_Sapphire", gems[4]);
		Property g5 = cfg.get("3_gems", "8_05_Malachite", gems[5]);
		Property g6 = cfg.get("3_gems", "8_06_Celestite", gems[6]);
		Property g7 = cfg.get("3_gems", "8_07_Clam", gems[7]);
		Property g8 = cfg.get("3_gems", "8_08_Salt", gems[8]);
		Property g9 = cfg.get("3_gems", "8_09_Niter", gems[9]);
		Property g10 = cfg.get("3_gems", "8_10_Sulfur", gems[10]);
		Property g11 = cfg.get("3_gems", "8_11_Schorl", gems[11]);
		Property g12 = cfg.get("3_gems", "8_12_Serpentine", gems[12]);
		Property g13 = cfg.get("3_gems", "8_13_Peridot", gems[13]);
		Property g14 = cfg.get("3_gems", "8_14_Garnet", gems[14]);
		Property g15 = cfg.get("3_gems", "8_15_Rutile", gems[15]);
		Property g16 = cfg.get("3_gems", "8_16_Bauxite", gems[16]);
		Property g17 = cfg.get("3_gems", "8_17_Bismuth", gems[17]);
		Property g18 = cfg.get("3_gems", "8_18_Apatite", gems[18]);
		Property g19 = cfg.get("3_gems", "8_19_Jadeite", gems[19]);
		Property g20 = cfg.get("3_gems", "8_20_Moonstone", gems[20]);
		Property g21 = cfg.get("3_gems", "8_21_Kunzite", gems[21]);
		Property g22 = cfg.get("3_gems", "8_22_Opal", gems[22]);
		Property g23 = cfg.get("3_gems", "8_23_CrudeOilGem", gems[23]);
		Property g24 = cfg.get("3_gems", "8_24_Tausonite", gems[24]);

		Property b0 = cfg.get("3_gems", "9_00_BlueChalcedonyBlock", gemb[0]);
		Property b1 = cfg.get("3_gems", "9_01_RedChalcedonyBlock", gemb[1]);
		Property b2 = cfg.get("3_gems", "9_02_WhiteChalcedonyBlock", gemb[2]);
		Property b3 = cfg.get("3_gems", "9_03_GypsumBlock", gemb[3]);
		Property b4 = cfg.get("3_gems", "9_04_SapphireBlock", gemb[4]);
		Property b5 = cfg.get("3_gems", "9_05_SaltBlock", gemb[5]);
		Property b6 = cfg.get("3_gems", "9_06_MarbleBlock", gemb[6]);
		Property b7 = cfg.get("3_gems", "9_07_SchorlBlock", gemb[7]);
		Property b8 = cfg.get("3_gems", "9_08_CompressedChalcedonyBlock", gemb[8]);
		Property b9 = cfg.get("3_gems", "9_09_SerpentineBlock", gemb[9]);
		Property b10 = cfg.get("3_gems", "9_10_OlivineBlock", gemb[10]);
		Property b11 = cfg.get("3_gems", "9_11_GarnetBlock", gemb[11]);

		Property r0 = cfg.get("4_gears", "10_00_WoodenGear", gear[0]);
		Property r1 = cfg.get("4_gears", "10_01_BrassGear", gear[1]);
		Property r2 = cfg.get("4_gears", "10_02_AlloyGear", gear[2]);
		Property r3 = cfg.get("4_gears", "10_03_SteelGear", gear[3]);
		Property r4 = cfg.get("4_gears", "10_04_ToolSteelGear", gear[4]);

		ores[0] = o0.getStringList();
		ores[1] = o1.getStringList();
		ores[2] = o2.getStringList();
		ores[3] = o3.getStringList();
		ores[4] = o4.getStringList();
		ores[5] = o5.getStringList();
		ores[6] = o6.getStringList();
		ores[7] = o7.getStringList();
		ores[8] = o8.getStringList();
		ores[9] = o9.getStringList();
		ores[10] = o10.getStringList();
		ores[11] = o11.getStringList();
		ores[12] = o12.getStringList();
		ores[13] = o13.getStringList();
		ores[14] = o14.getStringList();

		layers[0] = l0.getStringList();
		layers[1] = l1.getStringList();
		layers[2] = l2.getStringList();
		layers[3] = l3.getStringList();
		layers[4] = l4.getStringList();
		layers[5] = l5.getStringList();
		layers[6] = l6.getStringList();

		items[0] = c0.getStringList();
		items[1] = c1.getStringList();
		items[2] = c2.getStringList();
		items[3] = c3.getStringList();
		items[4] = c4.getStringList();
		items[5] = c5.getStringList();
		items[6] = c6.getStringList();
		items[7] = c7.getStringList();
		items[8] = c8.getStringList();
		items[9] = c9.getStringList();
		items[10] = c10.getStringList();
		items[11] = c11.getStringList();
		items[12] = c12.getStringList();
		items[13] = c13.getStringList();
		items[14] = c14.getStringList();

		ingots[0] = i0.getStringList();
		ingots[1] = i1.getStringList();
		ingots[2] = i2.getStringList();
		ingots[3] = i3.getStringList();
		ingots[4] = i4.getStringList();
		ingots[5] = i5.getStringList();
		ingots[6] = i6.getStringList();
		ingots[7] = i7.getStringList();
		ingots[8] = i8.getStringList();
		ingots[9] = i9.getStringList();
		ingots[10] = i10.getStringList();
		ingots[11] = i11.getStringList();
		ingots[12] = i12.getStringList();
		ingots[13] = i13.getStringList();
		ingots[14] = i14.getStringList();
		ingots[15] = i15.getStringList();
		ingots[16] = i16.getStringList();
		ingots[17] = i17.getStringList();

		dusts[0] = d0.getStringList();
		dusts[1] = d1.getStringList();
		dusts[2] = d2.getStringList();
		dusts[3] = d3.getStringList();
		dusts[4] = d4.getStringList();
		dusts[5] = d5.getStringList();
		dusts[6] = d6.getStringList();
		dusts[7] = d7.getStringList();
		dusts[8] = d8.getStringList();
		dusts[9] = d9.getStringList();
		dusts[10] = d10.getStringList();
		dusts[11] = d11.getStringList();
		dusts[12] = d12.getStringList();
		dusts[13] = d13.getStringList();
		dusts[14] = d14.getStringList();
		dusts[15] = d15.getStringList();

		metals[0] = m0.getStringList();
		metals[1] = m1.getStringList();
		metals[2] = m2.getStringList();
		metals[3] = m3.getStringList();
		metals[4] = m4.getStringList();
		metals[5] = m5.getStringList();
		metals[6] = m6.getStringList();
		metals[7] = m7.getStringList();

		alloys[0] = a0.getStringList();
		alloys[1] = a1.getStringList();
		alloys[2] = a2.getStringList();
		alloys[3] = a3.getStringList();
		alloys[4] = a4.getStringList();
		alloys[5] = a5.getStringList();
		alloys[6] = a6.getStringList();
		alloys[7] = a7.getStringList();
		alloys[8] = a8.getStringList();

		gems[0] = g0.getStringList();
		gems[1] = g1.getStringList();
		gems[2] = g2.getStringList();
		gems[3] = g3.getStringList();
		gems[4] = g4.getStringList();
		gems[5] = g5.getStringList();
		gems[6] = g6.getStringList();
		gems[7] = g7.getStringList();
		gems[8] = g8.getStringList();
		gems[9] = g9.getStringList();
		gems[10] = g10.getStringList();
		gems[11] = g11.getStringList();
		gems[12] = g12.getStringList();
		gems[13] = g13.getStringList();
		gems[14] = g14.getStringList();
		gems[15] = g15.getStringList();
		gems[16] = g16.getStringList();
		gems[17] = g17.getStringList();
		gems[18] = g18.getStringList();
		gems[19] = g19.getStringList();
		gems[20] = g20.getStringList();
		gems[21] = g21.getStringList();
		gems[22] = g22.getStringList();
		gems[23] = g23.getStringList();
		gems[24] = g24.getStringList();

		gemb[0] = b0.getStringList();
		gemb[1] = b1.getStringList();
		gemb[2] = b2.getStringList();
		gemb[3] = b3.getStringList();
		gemb[4] = b4.getStringList();
		gemb[5] = b5.getStringList();
		gemb[6] = b6.getStringList();
		gemb[7] = b7.getStringList();
		gemb[8] = b8.getStringList();
		gemb[9] = b9.getStringList();
		gemb[10] = b10.getStringList();
		gemb[11] = b11.getStringList();

		gear[0] = r0.getStringList();
		gear[1] = r1.getStringList();
		gear[2] = r2.getStringList();
		gear[3] = r3.getStringList();
		gear[4] = r4.getStringList();

		// } catch (Exception e) {
		// e.printStackTrace();
		// } finally {
		cfg.save();
		// }

	}

	public static void regOreDic(String[] ore, ItemStack item) {
		if (!DCUtil.isEmpty(item) && ore != null && ore.length > 0) {
			for (String name : ore) {
				OreDictionary.registerOre(name, item);
			}
		}
	}

}
