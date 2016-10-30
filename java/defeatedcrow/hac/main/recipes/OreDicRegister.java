package defeatedcrow.hac.main.recipes;

import defeatedcrow.hac.core.recipe.ConvertTargetList;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDicRegister {
	public static void load() {
		loadOres();
	}

	static void loadOres() {

		// ores
		OreDictionary.registerOre("oreGypsum", new ItemStack(MainInit.ores, 1, 0));
		OreDictionary.registerOre("oreIron", new ItemStack(MainInit.ores, 1, 1));
		OreDictionary.registerOre("oreChalcedonyB", new ItemStack(MainInit.ores, 1, 2));
		OreDictionary.registerOre("oreSapphire", new ItemStack(MainInit.ores, 1, 3));
		OreDictionary.registerOre("oreIron", new ItemStack(MainInit.ores, 1, 4));
		OreDictionary.registerOre("oreMagnetite", new ItemStack(MainInit.ores, 1, 5));
		OreDictionary.registerOre("oreCopper", new ItemStack(MainInit.ores, 1, 6));
		OreDictionary.registerOre("oreNickel", new ItemStack(MainInit.ores, 1, 7));
		OreDictionary.registerOre("oreZinc", new ItemStack(MainInit.ores, 1, 8));
		OreDictionary.registerOre("oreChalcedonyW", new ItemStack(MainInit.ores, 1, 9));
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

		// metals
		OreDictionary.registerOre("ingotCopper", new ItemStack(MainInit.oreIngot, 1, 0));
		OreDictionary.registerOre("ingotZinc", new ItemStack(MainInit.oreIngot, 1, 1));
		OreDictionary.registerOre("ingotNickel", new ItemStack(MainInit.oreIngot, 1, 2));
		OreDictionary.registerOre("ingotSilver", new ItemStack(MainInit.oreIngot, 1, 3));
		OreDictionary.registerOre("ingotBrass", new ItemStack(MainInit.oreIngot, 1, 4));
		OreDictionary.registerOre("ingotSteel", new ItemStack(MainInit.oreIngot, 1, 5));
		OreDictionary.registerOre("ingotNickelsilver", new ItemStack(MainInit.oreIngot, 1, 6));
		OreDictionary.registerOre("ingotMagnet", new ItemStack(MainInit.oreIngot, 1, 7));
		OreDictionary.registerOre("ingotTin", new ItemStack(MainInit.oreIngot, 1, 8));
		OreDictionary.registerOre("ingotBronze", new ItemStack(MainInit.oreIngot, 1, 9));
		OreDictionary.registerOre("ingotInox", new ItemStack(MainInit.oreIngot, 1, 10));
		OreDictionary.registerOre("ingotSUS", new ItemStack(MainInit.oreIngot, 1, 10));

		OreDictionary.registerOre("dustCopper", new ItemStack(MainInit.oreDust, 1, 0));
		OreDictionary.registerOre("dustZinc", new ItemStack(MainInit.oreDust, 1, 1));
		OreDictionary.registerOre("dustNickel", new ItemStack(MainInit.oreDust, 1, 2));
		OreDictionary.registerOre("dustSilver", new ItemStack(MainInit.oreDust, 1, 3));
		OreDictionary.registerOre("dustGold", new ItemStack(MainInit.oreDust, 1, 4));
		OreDictionary.registerOre("dustIron", new ItemStack(MainInit.oreDust, 1, 5));
		OreDictionary.registerOre("dustSteel", new ItemStack(MainInit.oreDust, 1, 6));
		OreDictionary.registerOre("dustMagnetite", new ItemStack(MainInit.oreDust, 1, 7));
		OreDictionary.registerOre("dustTin", new ItemStack(MainInit.oreDust, 1, 8));
		OreDictionary.registerOre("dustChromium", new ItemStack(MainInit.oreDust, 1, 9));
		OreDictionary.registerOre("dyeGreen", new ItemStack(MainInit.oreDust, 1, 9));

		OreDictionary.registerOre("blockCopper", new ItemStack(MainInit.metalBlock, 1, 0));
		OreDictionary.registerOre("blockZinc", new ItemStack(MainInit.metalBlock, 1, 1));
		OreDictionary.registerOre("blockNickel", new ItemStack(MainInit.metalBlock, 1, 2));
		OreDictionary.registerOre("blockSilver", new ItemStack(MainInit.metalBlock, 1, 3));
		OreDictionary.registerOre("blockBrass", new ItemStack(MainInit.metalBlock, 1, 4));
		OreDictionary.registerOre("blockSteel", new ItemStack(MainInit.metalBlock, 1, 5));
		OreDictionary.registerOre("blockNickelsilver", new ItemStack(MainInit.metalBlock, 1, 6));
		OreDictionary.registerOre("blockMagnet", new ItemStack(MainInit.metalBlock, 1, 7));
		OreDictionary.registerOre("blockTin", new ItemStack(MainInit.metalBlock, 1, 8));
		OreDictionary.registerOre("blockBronze", new ItemStack(MainInit.metalBlock, 1, 9));
		OreDictionary.registerOre("blockSUS", new ItemStack(MainInit.metalBlock, 1, 10));

		// gems
		OreDictionary.registerOre("gemChalcedony", new ItemStack(MainInit.gems, 1, 0));
		OreDictionary.registerOre("gemChalcedony", new ItemStack(MainInit.gems, 1, 1));
		OreDictionary.registerOre("gemChalcedony", new ItemStack(MainInit.gems, 1, 2));
		OreDictionary.registerOre("gemGypsum", new ItemStack(MainInit.gems, 1, 3));
		OreDictionary.registerOre("gemSapphire", new ItemStack(MainInit.gems, 1, 4));
		OreDictionary.registerOre("gemMalachite", new ItemStack(MainInit.gems, 1, 5));
		OreDictionary.registerOre("gemCelestite", new ItemStack(MainInit.gems, 1, 6));
		OreDictionary.registerOre("gemClam", new ItemStack(MainInit.gems, 1, 7));
		OreDictionary.registerOre("gemSalt", new ItemStack(MainInit.gems, 1, 8));
		OreDictionary.registerOre("gemNiter", new ItemStack(MainInit.gems, 1, 9));
		OreDictionary.registerOre("gemSulfur", new ItemStack(MainInit.gems, 1, 10));
		OreDictionary.registerOre("gemSchorl", new ItemStack(MainInit.gems, 1, 11));

		OreDictionary.registerOre("blockGemChalcedony", new ItemStack(MainInit.gemBlock, 1, 0));
		OreDictionary.registerOre("blockGemChalcedony", new ItemStack(MainInit.gemBlock, 1, 1));
		OreDictionary.registerOre("blockGemChalcedony", new ItemStack(MainInit.gemBlock, 1, 2));
		OreDictionary.registerOre("blockGemGypsum", new ItemStack(MainInit.gemBlock, 1, 3));
		OreDictionary.registerOre("blockSapphire", new ItemStack(MainInit.gemBlock, 1, 4));
		OreDictionary.registerOre("blockSalt", new ItemStack(MainInit.gemBlock, 1, 5));
		OreDictionary.registerOre("blockMarble", new ItemStack(MainInit.gemBlock, 1, 6));
		OreDictionary.registerOre("blockGemSchorl", new ItemStack(MainInit.gemBlock, 1, 7));

		// misc
		OreDictionary.registerOre("dustGraphite", new ItemStack(MainInit.miscDust, 1, 0));
		OreDictionary.registerOre("dustCoal", new ItemStack(MainInit.miscDust, 1, 0));
		OreDictionary.registerOre("dustCrystal", new ItemStack(MainInit.miscDust, 1, 1));
		OreDictionary.registerOre("dustLime", new ItemStack(MainInit.miscDust, 1, 2));
		OreDictionary.registerOre("dustWood", new ItemStack(MainInit.miscDust, 1, 3));
		OreDictionary.registerOre("pulpWood", new ItemStack(MainInit.miscDust, 1, 3));
		OreDictionary.registerOre("dustPresscake", new ItemStack(MainInit.miscDust, 1, 4));
		OreDictionary.registerOre("dustAsh", new ItemStack(MainInit.miscDust, 1, 5));
		OreDictionary.registerOre("dustNiter", new ItemStack(MainInit.miscDust, 1, 6));
		OreDictionary.registerOre("dustSulfur", new ItemStack(MainInit.miscDust, 1, 7));

		// material
		OreDictionary.registerOre("itemString", new ItemStack(MainInit.materials, 1, 0));
		OreDictionary.registerOre("itemString", new ItemStack(MainInit.materials, 1, 1));
		OreDictionary.registerOre("string", new ItemStack(MainInit.materials, 1, 0));
		OreDictionary.registerOre("string", new ItemStack(MainInit.materials, 1, 1));
		OreDictionary.registerOre("itemCloth", new ItemStack(MainInit.materials, 1, 2));
		OreDictionary.registerOre("itemCloth", new ItemStack(MainInit.materials, 1, 3));
		OreDictionary.registerOre("itemLinenCloth", new ItemStack(MainInit.materials, 1, 2));
		OreDictionary.registerOre("itemCottonCloth", new ItemStack(MainInit.materials, 1, 3));
		OreDictionary.registerOre("gearWood", new ItemStack(MainInit.materials, 1, 4));
		OreDictionary.registerOre("gearBrass", new ItemStack(MainInit.materials, 1, 5));
		OreDictionary.registerOre("gearArroy", new ItemStack(MainInit.materials, 1, 6));
		OreDictionary.registerOre("gearNickelsilver", new ItemStack(MainInit.materials, 1, 6));
		OreDictionary.registerOre("gearSteel", new ItemStack(MainInit.materials, 1, 7));

		// tool
		OreDictionary.registerOre("toolNormalYagen", new ItemStack(MainInit.stoneYagen, 1, 32767));
		OreDictionary.registerOre("toolNormalYagen", new ItemStack(MainInit.brassYagen, 1, 32767));

		// food
		OreDictionary.registerOre("dustSalt", new ItemStack(MainInit.foodMaterials, 1, 0));
		OreDictionary.registerOre("foodSalt", new ItemStack(MainInit.foodMaterials, 1, 0));
		OreDictionary.registerOre("foodFlour", new ItemStack(MainInit.foodMaterials, 1, 1));
		OreDictionary.registerOre("dustFlour", new ItemStack(MainInit.foodMaterials, 1, 1));
		OreDictionary.registerOre("foodRice", new ItemStack(MainInit.foodMaterials, 1, 2));

		/* 変換除外 */
		ConvertTargetList.addExclusing(new ItemStack(MainInit.cardboard, 1, 5));
		ConvertTargetList.addExclusing(new ItemStack(MainInit.materials, 1, 5));

		/* バニラ補足 */
		OreDictionary.registerOre("blockTallGrass", new ItemStack(Blocks.TALLGRASS, 1, 0));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(Blocks.TALLGRASS, 1, 1));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(Blocks.TALLGRASS, 1, 2));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(Blocks.DOUBLE_PLANT, 1, 2));
		OreDictionary.registerOre("blockTallGrass", new ItemStack(Blocks.DOUBLE_PLANT, 1, 3));
	}

}
