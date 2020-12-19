package defeatedcrow.hac.main.recipes;

import defeatedcrow.hac.core.recipe.ConvertTargetList;
import defeatedcrow.hac.food.recipes.FoodRecipes;
import defeatedcrow.hac.machine.recipes.MachineRecipes;
import defeatedcrow.hac.magic.recipe.MagicRecipeRegister;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.config.OredicConfig;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDicRegister {
	public static void load() {
		loadOres();
		if (ModuleConfig.machine) {
			MachineRecipes.loadOres();
		}
		if (ModuleConfig.magic) {
			MagicRecipeRegister.loadOres();
		}
		if (ModuleConfig.food) {
			FoodRecipes.loadOres();
		}
	}

	static void loadOres() {

		/* config target */
		// ores
		OredicConfig.regOreDic(OredicConfig.ores[0], new ItemStack(MainInit.oreNew, 1, 0));
		OredicConfig.regOreDic(OredicConfig.ores[1], new ItemStack(MainInit.oreNew, 1, 1));
		OredicConfig.regOreDic(OredicConfig.ores[2], new ItemStack(MainInit.oreNew, 1, 2));
		OredicConfig.regOreDic(OredicConfig.ores[3], new ItemStack(MainInit.oreNew, 1, 3));
		OredicConfig.regOreDic(OredicConfig.ores[4], new ItemStack(MainInit.oreNew, 1, 4));
		OredicConfig.regOreDic(OredicConfig.ores[5], new ItemStack(MainInit.oreNew, 1, 5));
		OredicConfig.regOreDic(OredicConfig.ores[6], new ItemStack(MainInit.oreNew, 1, 6));
		OredicConfig.regOreDic(OredicConfig.ores[7], new ItemStack(MainInit.oreNew, 1, 7));
		OredicConfig.regOreDic(OredicConfig.ores[8], new ItemStack(MainInit.oreNew, 1, 8));
		OredicConfig.regOreDic(OredicConfig.ores[9], new ItemStack(MainInit.oreNew, 1, 9));
		OredicConfig.regOreDic(OredicConfig.ores[10], new ItemStack(MainInit.oreNew, 1, 10));
		OredicConfig.regOreDic(OredicConfig.ores[11], new ItemStack(MainInit.oreNew, 1, 11));
		OredicConfig.regOreDic(OredicConfig.ores[12], new ItemStack(MainInit.oreNew, 1, 12));
		OredicConfig.regOreDic(OredicConfig.ores[13], new ItemStack(MainInit.oreNew, 1, 13));
		OredicConfig.regOreDic(OredicConfig.ores[14], new ItemStack(MainInit.oreNew, 1, 14));

		OredicConfig.regOreDic(OredicConfig.layers[0], new ItemStack(MainInit.layerNew, 1, 0));
		OredicConfig.regOreDic(OredicConfig.layers[1], new ItemStack(MainInit.layerNew, 1, 1));
		OredicConfig.regOreDic(OredicConfig.layers[2], new ItemStack(MainInit.layerNew, 1, 2));
		OredicConfig.regOreDic(OredicConfig.layers[3], new ItemStack(MainInit.layerNew, 1, 3));
		OredicConfig.regOreDic(OredicConfig.layers[4], new ItemStack(MainInit.layerNew, 1, 4));
		OredicConfig.regOreDic(OredicConfig.layers[5], new ItemStack(MainInit.layerNew, 1, 5));
		OredicConfig.regOreDic(OredicConfig.layers[6], new ItemStack(MainInit.layerNew, 1, 6));
		OredicConfig.regOreDic(OredicConfig.layers[7], new ItemStack(MainInit.layerNew, 1, 7));

		OredicConfig.regOreDic(OredicConfig.layers2[0], new ItemStack(MainInit.skarnOre, 1, 0));
		OredicConfig.regOreDic(OredicConfig.layers2[1], new ItemStack(MainInit.skarnOre, 1, 1));
		OredicConfig.regOreDic(OredicConfig.layers2[2], new ItemStack(MainInit.skarnOre, 1, 2));
		OredicConfig.regOreDic(OredicConfig.layers2[3], new ItemStack(MainInit.skarnOre, 1, 3));
		OredicConfig.regOreDic(OredicConfig.layers2[4], new ItemStack(MainInit.skarnOre, 1, 4));
		OredicConfig.regOreDic(OredicConfig.layers2[5], new ItemStack(MainInit.skarnOre, 1, 5));
		OredicConfig.regOreDic(OredicConfig.layers2[6], new ItemStack(MainInit.skarnOre, 1, 6));
		OredicConfig.regOreDic(OredicConfig.layers2[7], new ItemStack(MainInit.skarnOre, 1, 7));
		OredicConfig.regOreDic(OredicConfig.layers2[8], new ItemStack(MainInit.skarnOre, 1, 8));

		OredicConfig.regOreDic(OredicConfig.items[0], new ItemStack(MainInit.oreItem, 1, 0));
		OredicConfig.regOreDic(OredicConfig.items[1], new ItemStack(MainInit.oreItem, 1, 1));
		OredicConfig.regOreDic(OredicConfig.items[2], new ItemStack(MainInit.oreItem, 1, 2));
		OredicConfig.regOreDic(OredicConfig.items[3], new ItemStack(MainInit.oreItem, 1, 3));
		OredicConfig.regOreDic(OredicConfig.items[4], new ItemStack(MainInit.oreItem, 1, 4));
		OredicConfig.regOreDic(OredicConfig.items[5], new ItemStack(MainInit.oreItem, 1, 5));
		OredicConfig.regOreDic(OredicConfig.items[6], new ItemStack(MainInit.oreItem, 1, 6));
		OredicConfig.regOreDic(OredicConfig.items[7], new ItemStack(MainInit.oreItem, 1, 7));
		OredicConfig.regOreDic(OredicConfig.items[8], new ItemStack(MainInit.oreItem, 1, 8));
		OredicConfig.regOreDic(OredicConfig.items[9], new ItemStack(MainInit.oreItem, 1, 9));
		OredicConfig.regOreDic(OredicConfig.items[10], new ItemStack(MainInit.oreItem, 1, 10));
		OredicConfig.regOreDic(OredicConfig.items[11], new ItemStack(MainInit.oreItem, 1, 11));
		OredicConfig.regOreDic(OredicConfig.items[12], new ItemStack(MainInit.oreItem, 1, 12));
		OredicConfig.regOreDic(OredicConfig.items[13], new ItemStack(MainInit.oreItem, 1, 13));
		OredicConfig.regOreDic(OredicConfig.items[14], new ItemStack(MainInit.oreItem, 1, 14));

		// metals
		OredicConfig.regOreDic(OredicConfig.ingots[0], new ItemStack(MainInit.oreIngot, 1, 0));
		OredicConfig.regOreDic(OredicConfig.ingots[1], new ItemStack(MainInit.oreIngot, 1, 1));
		OredicConfig.regOreDic(OredicConfig.ingots[2], new ItemStack(MainInit.oreIngot, 1, 2));
		OredicConfig.regOreDic(OredicConfig.ingots[3], new ItemStack(MainInit.oreIngot, 1, 3));
		OredicConfig.regOreDic(OredicConfig.ingots[4], new ItemStack(MainInit.oreIngot, 1, 4));
		OredicConfig.regOreDic(OredicConfig.ingots[5], new ItemStack(MainInit.oreIngot, 1, 5));
		OredicConfig.regOreDic(OredicConfig.ingots[6], new ItemStack(MainInit.oreIngot, 1, 6));
		OredicConfig.regOreDic(OredicConfig.ingots[7], new ItemStack(MainInit.oreIngot, 1, 7));
		OredicConfig.regOreDic(OredicConfig.ingots[8], new ItemStack(MainInit.oreIngot, 1, 8));
		OredicConfig.regOreDic(OredicConfig.ingots[9], new ItemStack(MainInit.oreIngot, 1, 9));
		OredicConfig.regOreDic(OredicConfig.ingots[10], new ItemStack(MainInit.oreIngot, 1, 10));
		OredicConfig.regOreDic(OredicConfig.ingots[11], new ItemStack(MainInit.oreIngot, 1, 11));
		OredicConfig.regOreDic(OredicConfig.ingots[12], new ItemStack(MainInit.oreIngot, 1, 12));
		OredicConfig.regOreDic(OredicConfig.ingots[13], new ItemStack(MainInit.oreIngot, 1, 13));
		OredicConfig.regOreDic(OredicConfig.ingots[14], new ItemStack(MainInit.oreIngot, 1, 14));
		OredicConfig.regOreDic(OredicConfig.ingots[15], new ItemStack(MainInit.oreIngot, 1, 15));
		OredicConfig.regOreDic(OredicConfig.ingots[16], new ItemStack(MainInit.oreIngot, 1, 16));
		OredicConfig.regOreDic(OredicConfig.ingots[17], new ItemStack(MainInit.oreIngot, 1, 17));
		OredicConfig.regOreDic(OredicConfig.ingots[18], new ItemStack(MainInit.oreIngot, 1, 18));

		OredicConfig.regOreDic(OredicConfig.dusts[0], new ItemStack(MainInit.oreDust, 1, 0));
		OredicConfig.regOreDic(OredicConfig.dusts[1], new ItemStack(MainInit.oreDust, 1, 1));
		OredicConfig.regOreDic(OredicConfig.dusts[2], new ItemStack(MainInit.oreDust, 1, 2));
		OredicConfig.regOreDic(OredicConfig.dusts[3], new ItemStack(MainInit.oreDust, 1, 3));
		OredicConfig.regOreDic(OredicConfig.dusts[4], new ItemStack(MainInit.oreDust, 1, 4));
		OredicConfig.regOreDic(OredicConfig.dusts[5], new ItemStack(MainInit.oreDust, 1, 5));
		OredicConfig.regOreDic(OredicConfig.dusts[6], new ItemStack(MainInit.oreDust, 1, 6));
		OredicConfig.regOreDic(OredicConfig.dusts[7], new ItemStack(MainInit.oreDust, 1, 7));
		OredicConfig.regOreDic(OredicConfig.dusts[8], new ItemStack(MainInit.oreDust, 1, 8));
		OredicConfig.regOreDic(OredicConfig.dusts[9], new ItemStack(MainInit.oreDust, 1, 9));
		OredicConfig.regOreDic(OredicConfig.dusts[10], new ItemStack(MainInit.oreDust, 1, 10));
		OredicConfig.regOreDic(OredicConfig.dusts[11], new ItemStack(MainInit.oreDust, 1, 11));
		OredicConfig.regOreDic(OredicConfig.dusts[12], new ItemStack(MainInit.oreDust, 1, 12));
		OredicConfig.regOreDic(OredicConfig.dusts[13], new ItemStack(MainInit.oreDust, 1, 13));
		OredicConfig.regOreDic(OredicConfig.dusts[14], new ItemStack(MainInit.oreDust, 1, 14));
		OredicConfig.regOreDic(OredicConfig.dusts[15], new ItemStack(MainInit.oreDust, 1, 15));

		OredicConfig.regOreDic(OredicConfig.metals[0], new ItemStack(MainInit.metalBlockNew, 1, 0));
		OredicConfig.regOreDic(OredicConfig.metals[1], new ItemStack(MainInit.metalBlockNew, 1, 1));
		OredicConfig.regOreDic(OredicConfig.metals[2], new ItemStack(MainInit.metalBlockNew, 1, 2));
		OredicConfig.regOreDic(OredicConfig.metals[3], new ItemStack(MainInit.metalBlockNew, 1, 3));
		OredicConfig.regOreDic(OredicConfig.metals[4], new ItemStack(MainInit.metalBlockNew, 1, 4));
		OredicConfig.regOreDic(OredicConfig.metals[5], new ItemStack(MainInit.metalBlockNew, 1, 5));
		OredicConfig.regOreDic(OredicConfig.metals[6], new ItemStack(MainInit.metalBlockNew, 1, 6));
		OredicConfig.regOreDic(OredicConfig.metals[7], new ItemStack(MainInit.metalBlockNew, 1, 7));

		OredicConfig.regOreDic(OredicConfig.alloys[0], new ItemStack(MainInit.metalBlockAlloy, 1, 0));
		OredicConfig.regOreDic(OredicConfig.alloys[1], new ItemStack(MainInit.metalBlockAlloy, 1, 1));
		OredicConfig.regOreDic(OredicConfig.alloys[2], new ItemStack(MainInit.metalBlockAlloy, 1, 2));
		OredicConfig.regOreDic(OredicConfig.alloys[3], new ItemStack(MainInit.metalBlockAlloy, 1, 3));
		OredicConfig.regOreDic(OredicConfig.alloys[4], new ItemStack(MainInit.metalBlockAlloy, 1, 4));
		OredicConfig.regOreDic(OredicConfig.alloys[5], new ItemStack(MainInit.metalBlockAlloy, 1, 5));
		OredicConfig.regOreDic(OredicConfig.alloys[6], new ItemStack(MainInit.metalBlockAlloy, 1, 6));
		OredicConfig.regOreDic(OredicConfig.alloys[7], new ItemStack(MainInit.metalBlockAlloy, 1, 7));
		OredicConfig.regOreDic(OredicConfig.alloys[8], new ItemStack(MainInit.metalBlockAlloy, 1, 8));
		OredicConfig.regOreDic(OredicConfig.alloys[9], new ItemStack(MainInit.metalBlockAlloy, 1, 9));

		// gems
		OredicConfig.regOreDic(OredicConfig.gems[0], new ItemStack(MainInit.gems_red, 1, 0));
		OredicConfig.regOreDic(OredicConfig.gems[14], new ItemStack(MainInit.gems_red, 1, 1));
		OredicConfig.regOreDic(OredicConfig.gems[21], new ItemStack(MainInit.gems_red, 1, 2));
		OredicConfig.regOreDic(OredicConfig.gems[25], new ItemStack(MainInit.gems_red, 1, 3));
		OredicConfig.regOreDic(OredicConfig.gems[26], new ItemStack(MainInit.gems_red, 1, 4));
		OredicConfig.regOreDic(OredicConfig.gems[27], new ItemStack(MainInit.gems_red, 1, 5));
		OredicConfig.regOreDic(OredicConfig.gems[0], new ItemStack(MainInit.gems_blue, 1, 0));
		OredicConfig.regOreDic(OredicConfig.gems[4], new ItemStack(MainInit.gems_blue, 1, 1));
		OredicConfig.regOreDic(OredicConfig.gems[20], new ItemStack(MainInit.gems_blue, 1, 2));
		OredicConfig.regOreDic(OredicConfig.gems[28], new ItemStack(MainInit.gems_blue, 1, 3));
		OredicConfig.regOreDic(OredicConfig.gems[29], new ItemStack(MainInit.gems_blue, 1, 4));
		OredicConfig.regOreDic(OredicConfig.gems[30], new ItemStack(MainInit.gems_blue, 1, 5));
		OredicConfig.regOreDic(OredicConfig.gems[5], new ItemStack(MainInit.gems_green, 1, 0));
		OredicConfig.regOreDic(OredicConfig.gems[13], new ItemStack(MainInit.gems_green, 1, 1));
		OredicConfig.regOreDic(OredicConfig.gems[19], new ItemStack(MainInit.gems_green, 1, 2));
		OredicConfig.regOreDic(OredicConfig.gems[12], new ItemStack(MainInit.gems_green, 1, 3));
		OredicConfig.regOreDic(OredicConfig.gems[31], new ItemStack(MainInit.gems_green, 1, 4));
		OredicConfig.regOreDic(OredicConfig.gems[32], new ItemStack(MainInit.gems_green, 1, 5));
		OredicConfig.regOreDic(OredicConfig.gems[2], new ItemStack(MainInit.gems_white, 1, 0));
		OredicConfig.regOreDic(OredicConfig.gems[15], new ItemStack(MainInit.gems_white, 1, 1));
		OredicConfig.regOreDic(OredicConfig.gems[6], new ItemStack(MainInit.gems_white, 1, 2));
		OredicConfig.regOreDic(OredicConfig.gems[33], new ItemStack(MainInit.gems_white, 1, 3));
		OredicConfig.regOreDic(OredicConfig.gems[24], new ItemStack(MainInit.gems_white, 1, 4));
		OredicConfig.regOreDic(OredicConfig.gems[34], new ItemStack(MainInit.gems_white, 1, 5));
		OredicConfig.regOreDic(OredicConfig.gems[23], new ItemStack(MainInit.gems_black, 1, 0));
		OredicConfig.regOreDic(OredicConfig.gems[11], new ItemStack(MainInit.gems_black, 1, 1));
		OredicConfig.regOreDic(OredicConfig.gems[22], new ItemStack(MainInit.gems_black, 1, 2));
		OredicConfig.regOreDic(OredicConfig.gems[35], new ItemStack(MainInit.gems_black, 1, 3));
		OredicConfig.regOreDic(OredicConfig.gems[36], new ItemStack(MainInit.gems_black, 1, 4));
		OredicConfig.regOreDic(OredicConfig.gems[7], new ItemStack(MainInit.gems_black, 1, 5));
		OredicConfig.regOreDic(OredicConfig.gems[3], new ItemStack(MainInit.gems_layer, 1, 0));
		OredicConfig.regOreDic(OredicConfig.gems[8], new ItemStack(MainInit.gems_layer, 1, 1));
		OredicConfig.regOreDic(OredicConfig.gems[9], new ItemStack(MainInit.gems_layer, 1, 2));
		OredicConfig.regOreDic(OredicConfig.gems[10], new ItemStack(MainInit.gems_layer, 1, 3));
		OredicConfig.regOreDic(OredicConfig.gems[16], new ItemStack(MainInit.gems_layer, 1, 4));
		OredicConfig.regOreDic(OredicConfig.gems[17], new ItemStack(MainInit.gems_layer, 1, 5));
		OredicConfig.regOreDic(OredicConfig.gems[18], new ItemStack(MainInit.gems_layer, 1, 6));

		OredicConfig.regOreDic(OredicConfig.gemb[0], new ItemStack(MainInit.gemBlock, 1, 0));
		OredicConfig.regOreDic(OredicConfig.gemb[1], new ItemStack(MainInit.gemBlock, 1, 1));
		OredicConfig.regOreDic(OredicConfig.gemb[2], new ItemStack(MainInit.gemBlock, 1, 2));
		OredicConfig.regOreDic(OredicConfig.gemb[3], new ItemStack(MainInit.gemBlock, 1, 3));
		OredicConfig.regOreDic(OredicConfig.gemb[4], new ItemStack(MainInit.gemBlock, 1, 4));
		OredicConfig.regOreDic(OredicConfig.gemb[5], new ItemStack(MainInit.gemBlock, 1, 5));
		OredicConfig.regOreDic(OredicConfig.gemb[6], new ItemStack(MainInit.gemBlock, 1, 6));
		OredicConfig.regOreDic(OredicConfig.gemb[7], new ItemStack(MainInit.gemBlock, 1, 7));
		OredicConfig.regOreDic(OredicConfig.gemb[8], new ItemStack(MainInit.gemBlock, 1, 8));
		OredicConfig.regOreDic(OredicConfig.gemb[9], new ItemStack(MainInit.gemBlock, 1, 9));
		OredicConfig.regOreDic(OredicConfig.gemb[10], new ItemStack(MainInit.gemBlock, 1, 10));
		OredicConfig.regOreDic(OredicConfig.gemb[11], new ItemStack(MainInit.gemBlock, 1, 11));

		OredicConfig.regOreDic(OredicConfig.gear[0], new ItemStack(MainInit.gears, 1, 0));
		OredicConfig.regOreDic(OredicConfig.gear[1], new ItemStack(MainInit.gears, 1, 1));
		OredicConfig.regOreDic(OredicConfig.gear[2], new ItemStack(MainInit.gears, 1, 2));
		OredicConfig.regOreDic(OredicConfig.gear[3], new ItemStack(MainInit.gears, 1, 3));
		OredicConfig.regOreDic(OredicConfig.gear[4], new ItemStack(MainInit.gears, 1, 4));

		/* no config */
		// misc
		OreDictionary.registerOre("dustGraphite", new ItemStack(MainInit.miscDust, 1, 0));
		OreDictionary.registerOre("dustCoal", new ItemStack(MainInit.miscDust, 1, 0));
		OreDictionary.registerOre("dyeBlack", new ItemStack(MainInit.miscDust, 1, 0));
		OreDictionary.registerOre("dustCrystal", new ItemStack(MainInit.miscDust, 1, 1));
		OreDictionary.registerOre("dustLime", new ItemStack(MainInit.miscDust, 1, 2));
		OreDictionary.registerOre("dustWood", new ItemStack(MainInit.miscDust, 1, 3));
		OreDictionary.registerOre("pulpWood", new ItemStack(MainInit.miscDust, 1, 3));
		OreDictionary.registerOre("dustPresscake", new ItemStack(MainInit.miscDust, 1, 4));
		OreDictionary.registerOre("dustAsh", new ItemStack(MainInit.miscDust, 1, 5));
		OreDictionary.registerOre("dustNiter", new ItemStack(MainInit.miscDust, 1, 6));
		OreDictionary.registerOre("dustSaltpeter", new ItemStack(MainInit.miscDust, 1, 6));
		OreDictionary.registerOre("dustSulfur", new ItemStack(MainInit.miscDust, 1, 7));
		OreDictionary.registerOre("dustGarnet", new ItemStack(MainInit.miscDust, 1, 8));
		OreDictionary.registerOre("dustApatite", new ItemStack(MainInit.miscDust, 1, 9));
		OreDictionary.registerOre("dustBorax", new ItemStack(MainInit.miscDust, 1, 10));
		OreDictionary.registerOre("dustChrysotile", new ItemStack(MainInit.miscDust, 1, 11));
		OreDictionary.registerOre("dustAsbest", new ItemStack(MainInit.miscDust, 1, 11));
		OreDictionary.registerOre("dustMica", new ItemStack(MainInit.miscDust, 1, 12));
		OreDictionary.registerOre("gemSlate", new ItemStack(MainInit.miscDust, 1, 13));
		OreDictionary.registerOre("dustBran", new ItemStack(MainInit.foodDust, 1, 0));
		OreDictionary.registerOre("dustPlant", new ItemStack(MainInit.foodDust, 1, 1));
		OreDictionary.registerOre("dropMolasses", new ItemStack(MainInit.foodDust, 1, 2));
		OreDictionary.registerOre("listAllsugar", new ItemStack(MainInit.foodDust, 1, 2));
		OreDictionary.registerOre("dustMalt", new ItemStack(MainInit.foodDust, 1, 3));
		OreDictionary.registerOre("foodMalt", new ItemStack(MainInit.foodDust, 1, 3));
		OreDictionary.registerOre("dustSoycake", new ItemStack(MainInit.foodDust, 1, 4));
		OreDictionary.registerOre("dustPresscake", new ItemStack(MainInit.foodDust, 1, 4));
		OreDictionary.registerOre("foodSoypulp", new ItemStack(MainInit.foodDust, 1, 5));
		OreDictionary.registerOre("foodFlour", new ItemStack(MainInit.foodDust, 1, 5));
		OreDictionary.registerOre("foodBakingSoda", new ItemStack(MainInit.foodDust, 1, 6));
		OreDictionary.registerOre("dustBagasse", new ItemStack(MainInit.foodDust, 1, 7));
		OreDictionary.registerOre("dustDraff", new ItemStack(MainInit.foodDust, 1, 7));
		OreDictionary.registerOre("dustWood", new ItemStack(MainInit.foodDust, 1, 7));

		// material
		OreDictionary.registerOre("string", new ItemStack(Items.STRING, 1, 0));
		OreDictionary.registerOre("string", new ItemStack(MainInit.clothes, 1, 0));
		OreDictionary.registerOre("string", new ItemStack(MainInit.clothes, 1, 1));
		OreDictionary.registerOre("itemCloth", new ItemStack(MainInit.clothes, 1, 2));
		OreDictionary.registerOre("itemCloth", new ItemStack(MainInit.clothes, 1, 3));
		OreDictionary.registerOre("itemLinenCloth", new ItemStack(MainInit.clothes, 1, 2));
		OreDictionary.registerOre("itemCottonCloth", new ItemStack(MainInit.clothes, 1, 3));
		OreDictionary.registerOre("string", new ItemStack(MainInit.clothes, 1, 4));
		OreDictionary.registerOre("itemCloth", new ItemStack(MainInit.clothes, 1, 5));
		OreDictionary.registerOre("itemAsbestCloth", new ItemStack(MainInit.clothes, 1, 5));
		OreDictionary.registerOre("string", new ItemStack(MainInit.clothes, 1, 6));
		OreDictionary.registerOre("itemCloth", new ItemStack(MainInit.clothes, 1, 7));
		OreDictionary.registerOre("itemSilkCloth", new ItemStack(MainInit.clothes, 1, 7));
		OreDictionary.registerOre("string", new ItemStack(MainInit.clothes, 1, 8));
		OreDictionary.registerOre("stringMagic", new ItemStack(MainInit.clothes, 1, 8));
		OreDictionary.registerOre("itemCloth", new ItemStack(MainInit.clothes, 1, 9));
		OreDictionary.registerOre("itemMagicCloth", new ItemStack(MainInit.clothes, 1, 9));
		OreDictionary.registerOre("string", new ItemStack(MainInit.clothes, 1, 10));
		OreDictionary.registerOre("stringLinen", new ItemStack(MainInit.clothes, 1, 10));
		OreDictionary.registerOre("itemCloth", new ItemStack(MainInit.clothes, 1, 11));
		OreDictionary.registerOre("itemLinenCloth", new ItemStack(MainInit.clothes, 1, 11));

		OreDictionary.registerOre("patternSuit", new ItemStack(MainInit.patternPaper, 1, 0));
		OreDictionary.registerOre("patternCoat", new ItemStack(MainInit.patternPaper, 1, 1));
		OreDictionary.registerOre("patternJacket", new ItemStack(MainInit.patternPaper, 1, 2));
		OreDictionary.registerOre("patternHoodie", new ItemStack(MainInit.patternPaper, 1, 3));
		OreDictionary.registerOre("patternShirt", new ItemStack(MainInit.patternPaper, 1, 4));
		OreDictionary.registerOre("patternPants", new ItemStack(MainInit.patternPaper, 1, 5));
		OreDictionary.registerOre("patternSkirt", new ItemStack(MainInit.patternPaper, 1, 6));
		OreDictionary.registerOre("patternBoots", new ItemStack(MainInit.patternPaper, 1, 7));
		OreDictionary.registerOre("patternWear", new ItemStack(MainInit.patternPaper, 1, 8));

		OreDictionary.registerOre("blockGlass", new ItemStack(MainInit.syntheticBlock, 1, 32767));

		OreDictionary.registerOre("blockSkarn", new ItemStack(MainInit.skarnBlock, 1, 0));
		OreDictionary.registerOre("blockHornfels", new ItemStack(MainInit.skarnBlock, 1, 1));
		OreDictionary.registerOre("blockGreisen", new ItemStack(MainInit.skarnBlock, 1, 2));

		// tool
		OreDictionary.registerOre("toolNormalYagen", new ItemStack(MainInit.stoneYagen, 1, 32767));
		OreDictionary.registerOre("toolNormalYagen", new ItemStack(MainInit.brassYagen, 1, 32767));
		OreDictionary.registerOre("toolSpindle", new ItemStack(MainInit.handSpindle, 1, 0));
		OreDictionary.registerOre("soap", new ItemStack(MainInit.repairPutty, 1, 2));

		// food
		OreDictionary.registerOre("dustSalt", new ItemStack(MainInit.foodMaterials, 1, 0));
		OreDictionary.registerOre("foodSalt", new ItemStack(MainInit.foodMaterials, 1, 0));
		OreDictionary.registerOre("foodFlour", new ItemStack(MainInit.foodMaterials, 1, 1));
		OreDictionary.registerOre("dustFlour", new ItemStack(MainInit.foodMaterials, 1, 1));
		OreDictionary.registerOre("foodRice", new ItemStack(MainInit.foodMaterials, 1, 2));
		OreDictionary.registerOre("dustStarch", new ItemStack(MainInit.foodMaterials, 1, 3));

		OreDictionary.registerOre("foodSausage", new ItemStack(MainInit.bakedApple, 1, 3));

		// container
		OreDictionary.registerOre("containerLog", new ItemStack(MainInit.logCont, 1, 0));
		OreDictionary.registerOre("containerLog", new ItemStack(MainInit.logCont, 1, 1));
		OreDictionary.registerOre("containerLog", new ItemStack(MainInit.logCont, 1, 2));
		OreDictionary.registerOre("containerLog", new ItemStack(MainInit.logCont, 1, 3));
		OreDictionary.registerOre("containerLog", new ItemStack(MainInit.logCont, 1, 4));
		OreDictionary.registerOre("containerLog", new ItemStack(MainInit.logCont, 1, 5));
		OreDictionary.registerOre("containerCharcoal", new ItemStack(MainInit.logCont, 1, 6));

		OreDictionary.registerOre("containerApple", new ItemStack(MainInit.cropCont, 1, 0));
		OreDictionary.registerOre("containerPotato", new ItemStack(MainInit.cropCont, 1, 1));
		OreDictionary.registerOre("containerCarrot", new ItemStack(MainInit.cropCont, 1, 2));
		OreDictionary.registerOre("containerPumpkin", new ItemStack(MainInit.cropCont, 1, 3));
		OreDictionary.registerOre("containerMelon", new ItemStack(MainInit.cropCont, 1, 4));
		OreDictionary.registerOre("containerCactus", new ItemStack(MainInit.cropCont, 1, 5));
		OreDictionary.registerOre("containerReeds", new ItemStack(MainInit.cropCont, 1, 6));
		OreDictionary.registerOre("containerNetherWart", new ItemStack(MainInit.cropCont, 1, 7));
		OreDictionary.registerOre("containerCocoa", new ItemStack(MainInit.cropCont, 1, 8));
		OreDictionary.registerOre("containerBeetroot", new ItemStack(MainInit.cropCont, 1, 11));

		OreDictionary.registerOre("containerRottenFlesh", new ItemStack(MainInit.dropCont, 1, 0));
		OreDictionary.registerOre("containerBone", new ItemStack(MainInit.dropCont, 1, 1));
		OreDictionary.registerOre("containerSpiderEye", new ItemStack(MainInit.dropCont, 1, 2));
		OreDictionary.registerOre("containerEnderPearl", new ItemStack(MainInit.dropCont, 1, 3));
		OreDictionary.registerOre("containerGunpowder", new ItemStack(MainInit.dropCont, 1, 4));
		OreDictionary.registerOre("containerBlazeRod", new ItemStack(MainInit.dropCont, 1, 4));

		OreDictionary.registerOre("containerClay", new ItemStack(MainInit.miscCont, 1, 0));
		OreDictionary.registerOre("containerFish", new ItemStack(MainInit.miscCont, 1, 1));
		OreDictionary.registerOre("containerLeather", new ItemStack(MainInit.miscCont, 1, 2));
		OreDictionary.registerOre("containerRabbitHide", new ItemStack(MainInit.miscCont, 1, 3));
		OreDictionary.registerOre("containerFeather", new ItemStack(MainInit.miscCont, 1, 4));

		OreDictionary.registerOre("containerBeef", new ItemStack(MainInit.cardboard, 1, 0));
		OreDictionary.registerOre("containerPork", new ItemStack(MainInit.cardboard, 1, 1));
		OreDictionary.registerOre("containerChicken", new ItemStack(MainInit.cardboard, 1, 2));
		OreDictionary.registerOre("containerMutton", new ItemStack(MainInit.cardboard, 1, 3));
		OreDictionary.registerOre("containerMeatRaw", new ItemStack(MainInit.cardboard, 1, 0));
		OreDictionary.registerOre("containerMeatRaw", new ItemStack(MainInit.cardboard, 1, 1));
		OreDictionary.registerOre("containerMeatRaw", new ItemStack(MainInit.cardboard, 1, 2));
		OreDictionary.registerOre("containerMeatRaw", new ItemStack(MainInit.cardboard, 1, 3));
		OreDictionary.registerOre("containerEgg", new ItemStack(MainInit.cardboard, 1, 4));
		OreDictionary.registerOre("containerWool", new ItemStack(MainInit.cardboard, 1, 5));

		OreDictionary.registerOre("containerSugar", new ItemStack(MainInit.dustBags, 1, 0));
		OreDictionary.registerOre("containerSalt", new ItemStack(MainInit.dustBags, 1, 1));
		OreDictionary.registerOre("containerFlour", new ItemStack(MainInit.dustBags, 1, 2));
		OreDictionary.registerOre("containerRiceFood", new ItemStack(MainInit.dustBags, 1, 3));
		OreDictionary.registerOre("containerStarch", new ItemStack(MainInit.dustBags, 1, 4));
		OreDictionary.registerOre("containerSeed", new ItemStack(MainInit.dustBags, 1, 5));
		OreDictionary.registerOre("containerSoybean", new ItemStack(MainInit.dustBags, 1, 6));
		OreDictionary.registerOre("containerRiceSeed", new ItemStack(MainInit.dustBags, 1, 7));

		OreDictionary.registerOre("containerRice", new ItemStack(MainInit.cropBasket, 1, 0));
		OreDictionary.registerOre("containerOnion", new ItemStack(MainInit.cropBasket, 1, 1));
		OreDictionary.registerOre("containerSpinach", new ItemStack(MainInit.cropBasket, 1, 2));
		OreDictionary.registerOre("containerTomato", new ItemStack(MainInit.cropBasket, 1, 3));
		OreDictionary.registerOre("containerCoffee", new ItemStack(MainInit.cropBasket, 1, 4));
		OreDictionary.registerOre("containerCotton", new ItemStack(MainInit.cropBasket, 1, 5));
		OreDictionary.registerOre("containerLemon", new ItemStack(MainInit.cropBasket, 1, 6));
		OreDictionary.registerOre("containerOlive", new ItemStack(MainInit.cropBasket, 1, 7));
		OreDictionary.registerOre("containerTeaLeaf", new ItemStack(MainInit.cropBasket, 1, 8));
		OreDictionary.registerOre("containerLotusSeed", new ItemStack(MainInit.cropBasket, 1, 9));
		OreDictionary.registerOre("containerMulberry", new ItemStack(MainInit.cropBasket, 1, 10));
		OreDictionary.registerOre("containerSilk", new ItemStack(MainInit.cropBasket, 1, 11));
		OreDictionary.registerOre("containerGarlic", new ItemStack(MainInit.cropBasket, 1, 12));
		OreDictionary.registerOre("containerLettuce", new ItemStack(MainInit.cropBasket, 1, 13));
		OreDictionary.registerOre("containerGinger", new ItemStack(MainInit.cropBasket, 1, 14));

		OreDictionary.registerOre("containerBean", new ItemStack(MainInit.cropJute, 1, 0));
		OreDictionary.registerOre("containerChilipepper", new ItemStack(MainInit.cropJute, 1, 1));
		OreDictionary.registerOre("containerWalnut", new ItemStack(MainInit.cropJute, 1, 2));
		OreDictionary.registerOre("containerDate", new ItemStack(MainInit.cropJute, 1, 3));
		OreDictionary.registerOre("containerGrape", new ItemStack(MainInit.cropJute, 1, 4));
		OreDictionary.registerOre("containerMalt", new ItemStack(MainInit.cropJute, 1, 5));

		OreDictionary.registerOre("containerBran", new ItemStack(MainInit.dustCake, 1, 0));
		OreDictionary.registerOre("containerPresscake", new ItemStack(MainInit.dustCake, 1, 1));
		OreDictionary.registerOre("containerAsh", new ItemStack(MainInit.dustCake, 1, 2));
		OreDictionary.registerOre("containerPlantDust", new ItemStack(MainInit.dustCake, 1, 3));
		OreDictionary.registerOre("containerSoycake", new ItemStack(MainInit.dustCake, 1, 4));
		OreDictionary.registerOre("containerDraff", new ItemStack(MainInit.dustCake, 1, 5));

		/* 変換除外 */
		ConvertTargetList.addExclusing(new ItemStack(MainInit.cardboard, 1, 5));
		ConvertTargetList.addExclusing(new ItemStack(MainInit.clothes, 1, 1));
		ConvertTargetList.addExclusing(new ItemStack(MainInit.foodMaterials, 1, 1));
		ConvertTargetList.addExclusing(new ItemStack(MainInit.builds, 1, 6));
		ConvertTargetList.addExclusing(new ItemStack(MainInit.miscCont, 1, 2));
		ConvertTargetList.addExclusing(new ItemStack(MainInit.miscCont, 1, 3));

		// old
		OredicConfig.regOreDic(OredicConfig.gems[0], new ItemStack(MainInit.gems, 1, 0));
		OredicConfig.regOreDic(OredicConfig.gems[1], new ItemStack(MainInit.gems, 1, 1));
		OredicConfig.regOreDic(OredicConfig.gems[2], new ItemStack(MainInit.gems, 1, 2));
		OredicConfig.regOreDic(OredicConfig.gems[3], new ItemStack(MainInit.gems, 1, 3));
		OredicConfig.regOreDic(OredicConfig.gems[4], new ItemStack(MainInit.gems, 1, 4));
		OredicConfig.regOreDic(OredicConfig.gems[5], new ItemStack(MainInit.gems, 1, 5));
		OredicConfig.regOreDic(OredicConfig.gems[6], new ItemStack(MainInit.gems, 1, 6));
		OredicConfig.regOreDic(OredicConfig.gems[7], new ItemStack(MainInit.gems, 1, 7));
		OredicConfig.regOreDic(OredicConfig.gems[8], new ItemStack(MainInit.gems, 1, 8));
		OredicConfig.regOreDic(OredicConfig.gems[9], new ItemStack(MainInit.gems, 1, 9));
		OredicConfig.regOreDic(OredicConfig.gems[10], new ItemStack(MainInit.gems, 1, 10));
		OredicConfig.regOreDic(OredicConfig.gems[11], new ItemStack(MainInit.gems, 1, 11));
		OredicConfig.regOreDic(OredicConfig.gems[12], new ItemStack(MainInit.gems, 1, 12));
		OredicConfig.regOreDic(OredicConfig.gems[13], new ItemStack(MainInit.gems, 1, 13));
		OredicConfig.regOreDic(OredicConfig.gems[14], new ItemStack(MainInit.gems, 1, 14));
		OredicConfig.regOreDic(OredicConfig.gems[15], new ItemStack(MainInit.gems, 1, 15));
		OredicConfig.regOreDic(OredicConfig.gems[16], new ItemStack(MainInit.gems, 1, 16));
		OredicConfig.regOreDic(OredicConfig.gems[17], new ItemStack(MainInit.gems, 1, 17));
		OredicConfig.regOreDic(OredicConfig.gems[18], new ItemStack(MainInit.gems, 1, 18));
		OredicConfig.regOreDic(OredicConfig.gems[19], new ItemStack(MainInit.gems, 1, 19));
		OredicConfig.regOreDic(OredicConfig.gems[20], new ItemStack(MainInit.gems, 1, 20));
		OredicConfig.regOreDic(OredicConfig.gems[21], new ItemStack(MainInit.gems, 1, 21));
		OredicConfig.regOreDic(OredicConfig.gems[22], new ItemStack(MainInit.gems, 1, 22));
		OredicConfig.regOreDic(OredicConfig.gems[23], new ItemStack(MainInit.gems, 1, 23));
		OredicConfig.regOreDic(OredicConfig.gems[24], new ItemStack(MainInit.gems, 1, 24));

		// deprecated
		if (!ModuleConfig.delete_old) {
			OreDictionary.registerOre("oreGypsum", new ItemStack(MainInit.ores, 1, 0));
			OreDictionary.registerOre("oreIron", new ItemStack(MainInit.ores, 1, 1));
			OreDictionary.registerOre("oreChalcedonyBlue", new ItemStack(MainInit.ores, 1, 2));
			OreDictionary.registerOre("oreSapphire", new ItemStack(MainInit.ores, 1, 3));
			OreDictionary.registerOre("oreIron", new ItemStack(MainInit.ores, 1, 4));
			OreDictionary.registerOre("oreMagnetite", new ItemStack(MainInit.ores, 1, 5));
			OreDictionary.registerOre("oreCopper", new ItemStack(MainInit.ores, 1, 6));
			OreDictionary.registerOre("oreNickel", new ItemStack(MainInit.ores, 1, 7));
			OreDictionary.registerOre("oreZinc", new ItemStack(MainInit.ores, 1, 8));
			OreDictionary.registerOre("oreChalcedonyWhite", new ItemStack(MainInit.ores, 1, 9));
			OreDictionary.registerOre("oreQuartz", new ItemStack(MainInit.ores, 1, 10));
			OreDictionary.registerOre("oreGold", new ItemStack(MainInit.ores, 1, 11));
			OreDictionary.registerOre("oreSilver", new ItemStack(MainInit.ores, 1, 12));
			OreDictionary.registerOre("oreDiamond", new ItemStack(MainInit.ores, 1, 13));
			OreDictionary.registerOre("oreEmerald", new ItemStack(MainInit.ores, 1, 14));
			OreDictionary.registerOre("oreMagnetite", new ItemStack(MainInit.ores, 1, 15));
			OreDictionary.registerOre("oreLime", new ItemStack(MainInit.ores_2, 1, 0));
			OreDictionary.registerOre("oreSalt", new ItemStack(MainInit.ores_2, 1, 1));
			OreDictionary.registerOre("oreNiter", new ItemStack(MainInit.ores_2, 1, 2));
			OreDictionary.registerOre("oreSulfur", new ItemStack(MainInit.ores_2, 1, 3));
			OreDictionary.registerOre("oreTin", new ItemStack(MainInit.ores_2, 1, 4));
			OreDictionary.registerOre("oreSchorl", new ItemStack(MainInit.ores_2, 1, 5));
			OreDictionary.registerOre("oreSerpentine", new ItemStack(MainInit.ores_2, 1, 6));
			OreDictionary.registerOre("oreGarnet", new ItemStack(MainInit.ores_2, 1, 7));
			OreDictionary.registerOre("oreNickel", new ItemStack(MainInit.ores_2, 1, 8));
			OreDictionary.registerOre("oreBismuth", new ItemStack(MainInit.ores_2, 1, 9));
			OreDictionary.registerOre("oreAluminium", new ItemStack(MainInit.ores_2, 1, 10));
			OreDictionary.registerOre("oreAluminum", new ItemStack(MainInit.ores_2, 1, 10));
			OreDictionary.registerOre("oreTitanium", new ItemStack(MainInit.ores_2, 1, 11));
			OreDictionary.registerOre("oreApatite", new ItemStack(MainInit.ores_2, 1, 12));

		}

	}
}
