package defeatedcrow.hac.magic.recipe;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class MagicRecipeRegister {

	private static final RecipeResourcesMain res = new RecipeResourcesMain("magic");

	public static void load() {
		loadItemRecipes();
		loadPotionRecipe();
	}

	static void loadItemRecipes() {

		// drop
		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 3, 0), new Object[] {
			"gemChalcedonyBlue",
			"dyeBlue" });

		DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.colorDrop, 3, 0), new Object[] {
			"gemVivianite",
			"dyeBlue" });

		DCRecipe.jsonShapelessRecipe("magic", 3, new ItemStack(MagicInit.colorDrop, 1, 0), new Object[] {
			"gemLapis",
			"dyeBlue" });

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 3, 1), new Object[] {
			"gemMalachite",
			"dyeGreen" });

		DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.colorDrop, 1, 1), new Object[] {
			"gemSerpentine",
			"dyeGreen" });

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 3, 2), new Object[] {
			"gemChalcedonyRed",
			"dyeRed" });

		DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.colorDrop, 3, 2), new Object[] {
			"gemChiastolite",
			"dyeRed" });

		DCRecipe.jsonShapelessRecipe("magic", 3, new ItemStack(MagicInit.colorDrop, 1, 2), new Object[] {
			"dustRedstone",
			"dyeRed" });

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 3, 3), new Object[] {
			"gemCrudeOil",
			"dyeBlack" });

		DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.colorDrop, 1, 3), new Object[] {
			"obsidian",
			"dyeBlack" });

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 3, 4), new Object[] {
			"gemChalcedonyWhite",
			"dyeWhite" });

		DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.colorDrop, 1, 4), new Object[] {
			"gemQuartz",
			"dyeWhite" });

		DCRecipe.jsonShapelessRecipe("magic", 3, new ItemStack(MagicInit.colorDrop, 1, 4), new Object[] {
			"gemSakura",
			"dyeWhite" });

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 5), new Object[] {
			"gemSapphire",
			"dropBlue" });

		DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.colorDrop, 1, 5), new Object[] {
			"gemLarimar",
			"dropBlue" });

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 6), new Object[] {
			"gemPeridot",
			"dropGreen" });

		DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.colorDrop, 1, 6), new Object[] {
			"gemEmerald",
			"dropGreen" });

		DCRecipe.jsonShapelessRecipe("magic", 3, new ItemStack(MagicInit.colorDrop, 1, 6), new Object[] {
			"gemFluorite",
			"dropGreen" });

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 7), new Object[] {
			"gemGarnet",
			"dropRed" });

		DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.colorDrop, 1, 7), new Object[] {
			"gemAmethyst",
			"dropRed" });

		DCRecipe.jsonShapelessRecipe("magic", 3, new ItemStack(MagicInit.colorDrop, 1, 7), new Object[] {
			new ItemStack(MainInit.oreItem, 1, 10),
			"dropRed" });

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 8), new Object[] {
			"gemSchorl",
			"dropBlack" });

		DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.colorDrop, 1, 8), new Object[] {
			"gemIolite",
			"dropBlack" });

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorDrop, 1, 9), new Object[] {
			"gemRutile",
			"dropWhite" });

		DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.colorDrop, 1, 9), new Object[] {
			"gemDiamond",
			"dropWhite" });

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorCube, 1, 0), new Object[] {
			"gemMoonstone",
			"extractBlue",
			"blockElestial" });

		DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.colorCube, 1, 0), new Object[] {
			"gemAquamarine",
			"extractBlue",
			"blockElestial" });

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorCube, 1, 1), new Object[] {
			"gemJadeite",
			"extractGreen",
			"blockElestial" });

		DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.colorCube, 1, 1), new Object[] {
			"gemTsavorite",
			"extractGreen",
			"blockElestial" });

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorCube, 1, 2), new Object[] {
			"gemKunzite",
			"extractRed",
			"blockElestial" });

		DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.colorCube, 1, 2), new Object[] {
			"gemRuby",
			"extractRed",
			"blockElestial" });

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorCube, 1, 3), new Object[] {
			"gemOpal",
			"extractBlack",
			"blockElestial" });

		DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.colorCube, 1, 3), new Object[] {
			"gemAlexandrite",
			"extractBlack",
			"blockElestial" });

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.colorCube, 1, 4), new Object[] {
			"gemCelestite",
			"extractWhite",
			"blockElestial" });

		DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.colorCube, 1, 4), new Object[] {
			"gemTopaz",
			"extractWhite",
			"blockElestial" });

		// ring
		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.colorRing, 1, 0), new Object[] {
			"X",
			"Y",
			"Z",
			'X',
			"dustCrystal",
			'Y',
			"dropBlue",
			'Z',
			"ingotSilver" });

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.colorRing, 1, 1), new Object[] {
			"X",
			"Y",
			"Z",
			'X',
			"dustCrystal",
			'Y',
			"dropGreen",
			'Z',
			"ingotSilver" });

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.colorRing, 1, 2), new Object[] {
			"X",
			"Y",
			"Z",
			'X',
			"dustCrystal",
			'Y',
			"dropRed",
			'Z',
			"ingotSilver" });

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.colorRing, 1, 3), new Object[] {
			"X",
			"Y",
			"Z",
			'X',
			"dustCrystal",
			'Y',
			"dropBlack",
			'Z',
			"ingotSilver" });

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.colorRing, 1, 4), new Object[] {
			"X",
			"Y",
			"Z",
			'X',
			"dustCrystal",
			'Y',
			"dropWhite",
			'Z',
			"ingotSilver" });

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.colorRing2, 1, 0), new Object[] {
			"X",
			"Y",
			"Z",
			'X',
			"dustCrystal",
			'Y',
			"dropBlue",
			'Z',
			"ingotGold" });

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.colorRing2, 1, 1), new Object[] {
			"X",
			"Y",
			"Z",
			'X',
			"dustCrystal",
			'Y',
			"dropGreen",
			'Z',
			"ingotGold" });

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.colorRing2, 1, 2), new Object[] {
			"X",
			"Y",
			"Z",
			'X',
			"dustCrystal",
			'Y',
			"dropRed",
			'Z',
			"ingotGold" });

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.colorRing2, 1, 3), new Object[] {
			"X",
			"Y",
			"Z",
			'X',
			"dustCrystal",
			'Y',
			"dropBlack",
			'Z',
			"ingotGold" });

		DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.colorRing2, 1, 4), new Object[] {
			"X",
			"Y",
			"Z",
			'X',
			"dustCrystal",
			'Y',
			"dropWhite",
			'Z',
			"ingotGold" });

		// another

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(Items.BLAZE_POWDER, 1, 0), new Object[] {
			"dropRed",
			"gunpowder" });

		DCRecipe.jsonShapelessRecipe("magic", new ItemStack(Items.GLOWSTONE_DUST, 1, 0), new Object[] {
			"dropWhite",
			"gunpowder" });

		// advanced
		if (ModuleConfig.magic_advanced) {
			// pendant
			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorPendant, 1, 0), new Object[] {
				"ZZZ",
				" Y ",
				" X ",
				'X',
				"dustCrystal",
				'Y',
				"extractBlue",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorPendant, 1, 1), new Object[] {
				"ZZZ",
				" Y ",
				" X ",
				'X',
				"dustCrystal",
				'Y',
				"extractGreen",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorPendant, 1, 2), new Object[] {
				"ZZZ",
				" Y ",
				" X ",
				'X',
				"dustCrystal",
				'Y',
				"extractRed",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorPendant, 1, 3), new Object[] {
				"ZZZ",
				" Y ",
				" X ",
				'X',
				"dustCrystal",
				'Y',
				"extractBlack",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorPendant, 1, 4), new Object[] {
				"ZZZ",
				" Y ",
				" X ",
				'X',
				"dustCrystal",
				'Y',
				"extractWhite",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorPendant2, 1, 0), new Object[] {
				"ZZZ",
				" Y ",
				" X ",
				'X',
				"dustCrystal",
				'Y',
				"extractBlue",
				'Z',
				"ingotGold" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorPendant2, 1, 1), new Object[] {
				"ZZZ",
				" Y ",
				" X ",
				'X',
				"dustCrystal",
				'Y',
				"extractGreen",
				'Z',
				"ingotGold" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorPendant2, 1, 2), new Object[] {
				"ZZZ",
				" Y ",
				" X ",
				'X',
				"dustCrystal",
				'Y',
				"extractRed",
				'Z',
				"ingotGold" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorPendant2, 1, 3), new Object[] {
				"ZZZ",
				" Y ",
				" X ",
				'X',
				"dustCrystal",
				'Y',
				"extractBlack",
				'Z',
				"ingotGold" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorPendant2, 1, 4), new Object[] {
				"ZZZ",
				" Y ",
				" X ",
				'X',
				"dustCrystal",
				'Y',
				"extractWhite",
				'Z',
				"ingotGold" });

			// badge
			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorBadge, 1, 0), new Object[] {
				" X ",
				"ZYZ",
				" Z ",
				'X',
				"gemDiamond",
				'Y',
				"cubeBlue",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorBadge, 1, 1), new Object[] {
				" X ",
				"ZYZ",
				" Z ",
				'X',
				"gemDiamond",
				'Y',
				"cubeGreen",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorBadge, 1, 2), new Object[] {
				" X ",
				"ZYZ",
				" Z ",
				'X',
				"gemDiamond",
				'Y',
				"cubeRed",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorBadge, 1, 3), new Object[] {
				" X ",
				"ZYZ",
				" Z ",
				'X',
				"gemDiamond",
				'Y',
				"cubeBlack",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorBadge, 1, 4), new Object[] {
				" X ",
				"ZYZ",
				" Z ",
				'X',
				"gemDiamond",
				'Y',
				"cubeWhite",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", 2, new ItemStack(MagicInit.colorBadge, 1, 0), new Object[] {
				" X ",
				"ZYZ",
				" Z ",
				'X',
				new ItemStack(MainInit.gems_white, 1, 4),
				'Y',
				"cubeBlue",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", 2, new ItemStack(MagicInit.colorBadge, 1, 1), new Object[] {
				" X ",
				"ZYZ",
				" Z ",
				'X',
				new ItemStack(MainInit.gems_white, 1, 4),
				'Y',
				"cubeGreen",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", 2, new ItemStack(MagicInit.colorBadge, 1, 2), new Object[] {
				" X ",
				"ZYZ",
				" Z ",
				'X',
				new ItemStack(MainInit.gems_white, 1, 4),
				'Y',
				"cubeRed",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", 2, new ItemStack(MagicInit.colorBadge, 1, 3), new Object[] {
				" X ",
				"ZYZ",
				" Z ",
				'X',
				new ItemStack(MainInit.gems_white, 1, 4),
				'Y',
				"cubeBlack",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", 2, new ItemStack(MagicInit.colorBadge, 1, 4), new Object[] {
				" X ",
				"ZYZ",
				" Z ",
				'X',
				new ItemStack(MainInit.gems_white, 1, 4),
				'Y',
				"cubeWhite",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorGauntlet, 1, 0), new Object[] {
				"XX",
				"YZ",
				"WW",
				'X',
				"ingotSteel",
				'Y',
				"extractBlue",
				'Z',
				"extractBlack",
				'W',
				"feather" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorGauntlet, 1, 1), new Object[] {
				"XX",
				"YZ",
				"WW",
				'X',
				"ingotToolSteel",
				'Y',
				"extractGreen",
				'Z',
				"extractWhite",
				'W',
				new ItemStack(Items.GLOWSTONE_DUST) });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorGauntlet, 1, 2), new Object[] {
				"XX",
				"YZ",
				"WW",
				'X',
				"ingotSteel",
				'Y',
				"extractRed",
				'Z',
				"extractGreen",
				'W',
				"leather" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorGauntlet, 1, 3), new Object[] {
				"XX",
				"YZ",
				"WW",
				'X',
				"ingotTitaniumAlloy",
				'Y',
				"extractBlack",
				'Z',
				"extractRed",
				'W',
				new ItemStack(Items.GHAST_TEAR) });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.colorGauntlet, 1, 4), new Object[] {
				"XX",
				"YZ",
				"WW",
				'X',
				"ingotSilver",
				'Y',
				"extractWhite",
				'Z',
				"extractBlue",
				'W',
				"blockWool" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.pictureBlue, 1, 0), new Object[] {
				"XYZ",
				'X',
				"cubeBlue",
				'Y',
				new ItemStack(Items.PAINTING),
				'Z',
				"cubeRed" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.pictureRed, 1, 0), new Object[] {
				"XYZ",
				'X',
				"cubeRed",
				'Y',
				new ItemStack(Items.PAINTING),
				'Z',
				"cubeWhite" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.pictureGreen, 1, 0), new Object[] {
				"XYZ",
				'X',
				"cubeGreen",
				'Y',
				new ItemStack(Items.PAINTING),
				'Z',
				"cubeBlue" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.pictureBlack, 1, 0), new Object[] {
				"XYZ",
				'X',
				"cubeBlack",
				'Y',
				new ItemStack(Items.PAINTING),
				'Z',
				"cubeGreen" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.pictureWhite, 1, 0), new Object[] {
				"XYZ",
				'X',
				"cubeWhite",
				'Y',
				new ItemStack(Items.PAINTING),
				'Z',
				"cubeBlack" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 0), new Object[] {
				"paper",
				"paper",
				"paper",
				"dropBlue" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 1), new Object[] {
				"paper",
				"paper",
				"paper",
				"dropGreen" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 2), new Object[] {
				"paper",
				"paper",
				"paper",
				"dropRed" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 3), new Object[] {
				"paper",
				"paper",
				"paper",
				"dropBlack" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 4), new Object[] {
				"paper",
				"paper",
				"paper",
				"dropWhite" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 5), new Object[] {
				"paper",
				"paper",
				"paper",
				"extractBlue" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 6), new Object[] {
				"paper",
				"paper",
				"paper",
				"extractGreen" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 7), new Object[] {
				"paper",
				"paper",
				"paper",
				"extractRed" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 8), new Object[] {
				"paper",
				"paper",
				"paper",
				"extractBlack" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 9), new Object[] {
				"paper",
				"paper",
				"paper",
				"extractWhite" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 10), new Object[] {
				"paper",
				"paper",
				"paper",
				"cubeBlue" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 11), new Object[] {
				"paper",
				"paper",
				"paper",
				"cubeGreen" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 12), new Object[] {
				"paper",
				"paper",
				"paper",
				"cubeRed" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 13), new Object[] {
				"paper",
				"paper",
				"paper",
				"cubeBlack" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard, 3, 14), new Object[] {
				"paper",
				"paper",
				"paper",
				"cubeWhite" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard2, 3, 0), new Object[] {
				"paper",
				"paper",
				"paper",
				"extractBlue",
				"extractGreen" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard2, 3, 1), new Object[] {
				"paper",
				"paper",
				"paper",
				"extractGreen",
				"extractBlack" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard2, 3, 2), new Object[] {
				"paper",
				"paper",
				"paper",
				"extractRed",
				"extractBlue" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard2, 3, 3), new Object[] {
				"paper",
				"paper",
				"paper",
				"extractBlack",
				"extractWhite" });

			DCRecipe.jsonShapelessRecipe("magic_advanced", new ItemStack(MagicInit.magicCard2, 3, 4), new Object[] {
				"paper",
				"paper",
				"paper",
				"extractWhite",
				"extractRed" });

			// buimeOrb
			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.biomeOrb, 1, 0), new Object[] {
				"XYX",
				"YZY",
				"XYX",
				'X',
				new ItemStack(MainInit.selenite, 1, 0),
				'Y',
				"gemPeridot",
				'Z',
				"cubeWhite" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.biomeOrb, 1, 1), new Object[] {
				"XYX",
				"YZY",
				"XYX",
				'X',
				new ItemStack(MainInit.selenite, 1, 0),
				'Y',
				"gemPeridot",
				'Z',
				"cubeGreen" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.biomeOrb, 1, 2), new Object[] {
				"XYX",
				"YZY",
				"XYX",
				'X',
				new ItemStack(MainInit.selenite, 1, 0),
				'Y',
				"gemPeridot",
				'Z',
				"cubeRed" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.biomeOrb, 1, 3), new Object[] {
				"XYX",
				"YZY",
				"XYX",
				'X',
				new ItemStack(MainInit.selenite, 1, 0),
				'Y',
				"gemPeridot",
				'Z',
				"cubeBlue" });

			DCRecipe.jsonShapedRecipe("magic_advanced", 2, new ItemStack(MagicInit.timeCage, 1, 0), new Object[] {
				"XYX",
				"YZY",
				"XYX",
				'X',
				"ingotSilver",
				'Y',
				new ItemStack(MainInit.selenite, 1, 0),
				'Z',
				new ItemStack(MachineInit.gemcore, 1, 1) });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.gemBootsBird, 1, 0), new Object[] {
				"XYX",
				"XZX",
				'X',
				"stringMagic",
				'Y',
				"cubeWhite",
				'Z',
				"ingotSilver" });

			DCRecipe.jsonShapedRecipe("magic_advanced", new ItemStack(MagicInit.gemBootsFish, 1, 0), new Object[] {
				"XYX",
				"XZX",
				'X',
				"stringMagic",
				'Y',
				"cubeBlue",
				'Z',
				"ingotSilver" });
		}

		if (ModuleConfig.food) {
			DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.lotusCandle, 1, 0), new Object[] {
				"XXX",
				"X X",
				"XXX",
				'X',
				new ItemStack(FoodInit.petals, 1, 0) });

			DCRecipe.jsonShapelessRecipe("magic", new ItemStack(FoodInit.petals, 8, 0), new Object[] {
				new ItemStack(MagicInit.lotusCandle, 1, 0) });

			DCRecipe.jsonShapedRecipe("magic", new ItemStack(MagicInit.lotusCandleBlack, 1, 0), new Object[] {
				"XXX",
				"X X",
				"XXX",
				'X',
				new ItemStack(FoodInit.petals, 1, 1) });

			DCRecipe.jsonShapelessRecipe("magic", new ItemStack(FoodInit.petals, 8, 1), new Object[] {
				new ItemStack(MagicInit.lotusCandleBlack, 1, 0) });

			DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.coldLamp, 1, 0), new Object[] {
				new ItemStack(MainInit.oilLamp, 1, 0),
				"extractBlue" });

			DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.coldLamp, 1, 1), new Object[] {
				new ItemStack(MainInit.oilLamp, 1, 1),
				"extractBlue" });

			DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.coldLamp, 1, 2), new Object[] {
				new ItemStack(MainInit.oilLamp, 1, 2),
				"extractBlue" });

			DCRecipe.jsonShapelessRecipe("magic", 2, new ItemStack(MagicInit.coldLamp, 1, 3), new Object[] {
				new ItemStack(MainInit.oilLamp, 1, 3),
				"extractBlue" });

			DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.coldLamp, 1, 0), new Object[] {
				new ItemStack(MainInit.oilLamp, 1, 0),
				"dustAmmoniumNitrate" });

			DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.coldLamp, 1, 1), new Object[] {
				new ItemStack(MainInit.oilLamp, 1, 1),
				"dustAmmoniumNitrate" });

			DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.coldLamp, 1, 2), new Object[] {
				new ItemStack(MainInit.oilLamp, 1, 2),
				"dustAmmoniumNitrate" });

			DCRecipe.jsonShapelessRecipe("magic", new ItemStack(MagicInit.coldLamp, 1, 3), new Object[] {
				new ItemStack(MainInit.oilLamp, 1, 3),
				"dustAmmoniumNitrate" });
		}

		// elestial
		GameRegistry.addSmelting(new ItemStack(MainInit.gemBlock, 1, 8), new ItemStack(MagicInit.elestial, 1, 0), 0.5F);

		// climate
		ClimateAPI.registerBlock.registerHeatBlock(MagicInit.coldLamp, 32767, DCHeatTier.COOL);

		DCRecipe.jsonShapelessRecipe("magic", 4, new ItemStack(MagicInit.colorDrop, 1, 0), new Object[] {
			"petalLotus",
			"dyeBlue" });

		DCRecipe.jsonShapelessRecipe("magic", 4, new ItemStack(MagicInit.colorDrop, 1, 1), new Object[] {
			"petalLotus",
			"dyeGreen" });

		DCRecipe.jsonShapelessRecipe("magic", 4, new ItemStack(MagicInit.colorDrop, 1, 2), new Object[] {
			"petalLotus",
			"dyeRed" });

		DCRecipe.jsonShapelessRecipe("magic", 4, new ItemStack(MagicInit.colorDrop, 1, 3), new Object[] {
			"petalLotus",
			"dyeBlack" });

		DCRecipe.jsonShapelessRecipe("magic", 4, new ItemStack(MagicInit.colorDrop, 1, 4), new Object[] {
			"petalLotus",
			"dyeWhite" });

		DCRecipe.jsonShapelessRecipe("magic", 4, new ItemStack(MagicInit.colorDrop, 1, 5), new Object[] {
			"petalBlackLotus",
			"dyeBlue" });

		DCRecipe.jsonShapelessRecipe("magic", 4, new ItemStack(MagicInit.colorDrop, 1, 6), new Object[] {
			"petalBlackLotus",
			"dyeGreen" });

		DCRecipe.jsonShapelessRecipe("magic", 4, new ItemStack(MagicInit.colorDrop, 1, 7), new Object[] {
			"petalBlackLotus",
			"dyeRed" });

		DCRecipe.jsonShapelessRecipe("magic", 4, new ItemStack(MagicInit.colorDrop, 1, 8), new Object[] {
			"petalBlackLotus",
			"dyeBlack" });

		DCRecipe.jsonShapelessRecipe("magic", 4, new ItemStack(MagicInit.colorDrop, 1, 9), new Object[] {
			"petalBlackLotus",
			"dyeWhite" });

	}

	public static void loadOres() {
		OreDictionary.registerOre("dropBlue", new ItemStack(MagicInit.colorDrop, 1, 0));
		OreDictionary.registerOre("dropGreen", new ItemStack(MagicInit.colorDrop, 1, 1));
		OreDictionary.registerOre("dropRed", new ItemStack(MagicInit.colorDrop, 1, 2));
		OreDictionary.registerOre("dropBlack", new ItemStack(MagicInit.colorDrop, 1, 3));
		OreDictionary.registerOre("dropWhite", new ItemStack(MagicInit.colorDrop, 1, 4));
		OreDictionary.registerOre("extractBlue", new ItemStack(MagicInit.colorDrop, 1, 5));
		OreDictionary.registerOre("extractGreen", new ItemStack(MagicInit.colorDrop, 1, 6));
		OreDictionary.registerOre("extractRed", new ItemStack(MagicInit.colorDrop, 1, 7));
		OreDictionary.registerOre("extractBlack", new ItemStack(MagicInit.colorDrop, 1, 8));
		OreDictionary.registerOre("extractWhite", new ItemStack(MagicInit.colorDrop, 1, 9));
		OreDictionary.registerOre("cubeBlue", new ItemStack(MagicInit.colorCube, 1, 5));
		OreDictionary.registerOre("cubeGreen", new ItemStack(MagicInit.colorCube, 1, 6));
		OreDictionary.registerOre("cubeRed", new ItemStack(MagicInit.colorCube, 1, 7));
		OreDictionary.registerOre("cubeBlack", new ItemStack(MagicInit.colorCube, 1, 8));
		OreDictionary.registerOre("cubeWhite", new ItemStack(MagicInit.colorCube, 1, 9));
		OreDictionary.registerOre("blockElestial", new ItemStack(MagicInit.elestial, 1, 0));
	}

	static void loadOldrecipe() {}

	static void loadPotionRecipe() {
		PotionType awkward = PotionType.getPotionTypeForName("awkward");
		PotionType bird = ForgeRegistries.POTION_TYPES.getValue(new ResourceLocation("dcs_climate", "dcs.bird"));
		PotionType fish = ForgeRegistries.POTION_TYPES.getValue(new ResourceLocation("dcs_climate", "dcs.ocean"));
		PotionType heavy = ForgeRegistries.POTION_TYPES.getValue(new ResourceLocation("dcs_climate", "dcs.heavyboots"));
		PotionType nimble = ForgeRegistries.POTION_TYPES.getValue(new ResourceLocation("dcs_climate", "dcs.nimble"));
		ItemStack awk = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM, 1, 0), awkward);
		ItemStack awk_spr = PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION, 1, 0), awkward);
		ItemStack awk_lng = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION, 1, 0), awkward);
		ItemStack bir = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM, 1, 0), bird);
		ItemStack bir_spr = PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION, 1, 0), bird);
		ItemStack bir_lng = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION, 1, 0), bird);
		ItemStack fi = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM, 1, 0), fish);
		ItemStack fi_spr = PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION, 1, 0), fish);
		ItemStack fi_lng = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION, 1, 0), fish);
		ItemStack hv = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM, 1, 0), heavy);
		ItemStack hv_spr = PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION, 1, 0), heavy);
		ItemStack hv_lng = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION, 1, 0), heavy);
		ItemStack nm = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM, 1, 0), nimble);
		ItemStack nm_spr = PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION, 1, 0), nimble);
		ItemStack nm_lng = PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION, 1, 0), nimble);

		BrewingRecipeRegistry.addRecipe(awk, "dropWhite", bir);
		BrewingRecipeRegistry.addRecipe(awk, "dropBlue", fi);
		BrewingRecipeRegistry.addRecipe(awk, "dropRed", nm);
		BrewingRecipeRegistry.addRecipe(awk, "dropGreen", hv);

		BrewingRecipeRegistry.addRecipe(awk_spr, "dropWhite", bir_spr);
		BrewingRecipeRegistry.addRecipe(awk_spr, "dropBlue", fi_spr);
		BrewingRecipeRegistry.addRecipe(awk_spr, "dropRed", nm_spr);
		BrewingRecipeRegistry.addRecipe(awk_spr, "dropGreen", hv_spr);

		BrewingRecipeRegistry.addRecipe(awk_lng, "dropWhite", bir_lng);
		BrewingRecipeRegistry.addRecipe(awk_lng, "dropBlue", fi_lng);
		BrewingRecipeRegistry.addRecipe(awk_lng, "dropRed", nm_lng);
		BrewingRecipeRegistry.addRecipe(awk_lng, "dropGreen", hv_lng);

		BrewingRecipeRegistry.addRecipe(bir, "gunpowder", bir_spr);
		BrewingRecipeRegistry.addRecipe(fi, "gunpowder", fi_spr);
		BrewingRecipeRegistry.addRecipe(nm, "gunpowder", nm_spr);
		BrewingRecipeRegistry.addRecipe(hv, "gunpowder", hv_spr);

		BrewingRecipeRegistry.addRecipe(bir_spr, new ItemStack(Items.DRAGON_BREATH), bir_lng);
		BrewingRecipeRegistry.addRecipe(fi_spr, new ItemStack(Items.DRAGON_BREATH), fi_lng);
		BrewingRecipeRegistry.addRecipe(nm_spr, new ItemStack(Items.DRAGON_BREATH), nm_lng);
		BrewingRecipeRegistry.addRecipe(hv_spr, new ItemStack(Items.DRAGON_BREATH), hv_lng);
	}

}
