package defeatedcrow.hac.main.recipes;

import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class LoadingOreRecipe {
	public static void add(RecipeResourcesMain res) {
		// dust -> dustBlock
		DCRecipe.jsonShapedRecipe("main", new ItemStack(MainInit.dustBlock, 1, 0), new Object[] {
			"XXX",
			"XXX",
			"XXX",
			'X',
			"dustCopper" });

		DCRecipe.jsonShapedRecipe("main", new ItemStack(MainInit.dustBlock, 1, 1), new Object[] {
			"XXX",
			"XXX",
			"XXX",
			'X',
			"dustZinc" });

		DCRecipe.jsonShapedRecipe("main", new ItemStack(MainInit.dustBlock, 1, 2), new Object[] {
			"XXX",
			"XXX",
			"XXX",
			'X',
			"dustNickel" });

		DCRecipe.jsonShapedRecipe("main", new ItemStack(MainInit.dustBlock, 1, 3), new Object[] {
			"XXX",
			"XXX",
			"XXX",
			'X',
			"dustSilver" });

		DCRecipe.jsonShapedRecipe("main", new ItemStack(MainInit.dustBlock, 1, 8), new Object[] {
			"XXX",
			"XXX",
			"XXX",
			'X',
			"dustTin" });

		DCRecipe.jsonShapedRecipe("main", new ItemStack(MainInit.dustBlock, 1, 13), new Object[] {
			"XXX",
			"XXX",
			"XXX",
			'X',
			"dustAluminium" });

		DCRecipe.jsonShapedRecipe("main", 2, new ItemStack(MainInit.dustBlock, 1, 13), new Object[] {
			"XXX",
			"XXX",
			"XXX",
			'X',
			"dustAluminum" });

		DCRecipe.jsonShapedRecipe("main", new ItemStack(MainInit.dustBlock, 1, 14), new Object[] {
			"XXX",
			"XXX",
			"XXX",
			'X',
			"dustBismuth" });

		DCRecipe.jsonShapelessRecipe("main", new ItemStack(MainInit.dustBlock, 1, 4), new Object[] {
			"dustCopper",
			"dustCopper",
			"dustCopper",
			"dustCopper",
			"dustCopper",
			"dustCopper",
			"dustZinc",
			"dustZinc",
			"dustZinc" });

		DCRecipe.jsonShapelessRecipe("main", new ItemStack(MainInit.dustBlock, 1, 6), new Object[] {
			"dustCopper",
			"dustCopper",
			"dustCopper",
			"dustCopper",
			"dustCopper",
			"dustCopper",
			"dustNickel",
			"dustNickel",
			"dustZinc" });

		DCRecipe.jsonShapelessRecipe("main", new ItemStack(MainInit.dustBlock, 1, 9), new Object[] {
			"dustCopper",
			"dustCopper",
			"dustCopper",
			"dustCopper",
			"dustCopper",
			"dustCopper",
			"dustCopper",
			"dustTin",
			"dustTin" });

		DCRecipe.jsonShapedRecipe("main", 2, new ItemStack(MainInit.dustBlock, 1, 9), new Object[] {
			"XXX",
			"XXX",
			"XXX",
			'X',
			"dustBronze" });

		DCRecipe.jsonShapelessRecipe("main", new ItemStack(MainInit.dustBlock, 1, 5), new Object[] {
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustGraphite" });

		DCRecipe.jsonShapelessRecipe("main", 2, new ItemStack(MainInit.dustBlock, 1, 5), new Object[] {
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustGraphite" });

		DCRecipe.jsonShapelessRecipe("main", 3, new ItemStack(MainInit.dustBlock, 1, 5), new Object[] {
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustCoal" });

		DCRecipe.jsonShapelessRecipe("main", 4, new ItemStack(MainInit.dustBlock, 1, 5), new Object[] {
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustCoal" });

		DCRecipe.jsonShapelessRecipe("main", new ItemStack(MainInit.dustBlock, 1, 7), new Object[] {
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"gemCelestite",
			"dustLime" });

		DCRecipe.jsonShapelessRecipe("main", new ItemStack(MainInit.dustBlock, 1, 10), new Object[] {
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustChromium",
			"dustChromium",
			"dustNickel" });

		DCRecipe.jsonShapelessRecipe("main", 2, new ItemStack(MainInit.dustBlock, 1, 10), new Object[] {
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustChromium",
			"dustChromium",
			"dustNickel" });

		DCRecipe.jsonShapelessRecipe("main", new ItemStack(MainInit.dustBlock, 1, 11), new Object[] {
			"dustGold",
			"dustGold",
			"dustGold",
			"dustGold",
			"dustGold",
			"dustGold",
			"dustGold",
			"dustGold",
			"dustGold" });

		DCRecipe.jsonShapelessRecipe("main", new ItemStack(MainInit.dustBlock, 1, 12), new Object[] {
			"dustTitanium",
			"dustTitanium",
			"dustTitanium",
			"dustTitanium",
			"dustTitanium",
			"dustTitanium",
			"dustTitanium",
			"dustAluminium",
			"dustChromium" });

		DCRecipe.jsonShapelessRecipe("main", 2, new ItemStack(MainInit.dustBlock, 1, 12), new Object[] {
			"dustTitanium",
			"dustTitanium",
			"dustTitanium",
			"dustTitanium",
			"dustTitanium",
			"dustTitanium",
			"dustTitanium",
			"dustAluminum",
			"dustChromium" });

		DCRecipe.jsonShapelessRecipe("main", new ItemStack(MainInit.dustBlock, 1, 15), new Object[] {
			"dustBismuth",
			"dustBismuth",
			"gemCelestite",
			"dustCopper",
			"dustCopper",
			"dustCopper",
			"dustLime",
			"dustLime",
			"dustLime" });

		DCRecipe.jsonShapelessRecipe("main", new ItemStack(MainInit.dustBlock_2, 8, 1), new Object[] {
			"dustCrystal",
			"dustCrystal",
			"dustCrystal",
			"dustCrystal",
			"dustCrystal",
			"dustCrystal",
			"dustCrystal",
			"dustCrystal",
			"dustBorax" });

		DCRecipe.jsonShapelessRecipe("main", new ItemStack(MainInit.dustBlock_2, 1, 2), new Object[] {
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustManganese",
			"dustChromium",
			"dustMolybdenum" });

		DCRecipe.jsonShapelessRecipe("main", 2, new ItemStack(MainInit.dustBlock_2, 1, 2), new Object[] {
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustManganese",
			"dustChromium",
			"dustMolybdenum" });

		DCRecipe.jsonShapelessRecipe("main", new ItemStack(MainInit.dustBlock_2, 1, 3), new Object[] {
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustIron",
			"dustManganese",
			"dustManganese",
			"dustCoal" });

		DCRecipe.jsonShapelessRecipe("main", 2, new ItemStack(MainInit.dustBlock_2, 1, 3), new Object[] {
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustMagnetite",
			"dustManganese",
			"dustManganese",
			"dustCoal" });

		// dustBlock -> dust
		DCRecipe.jsonShapelessRecipe("main_container", 2, new ItemStack(MainInit.oreDust, 9, 0), new Object[] {
			new ItemStack(MainInit.dustBlock, 1, 0) });

		DCRecipe.jsonShapelessRecipe("main_container", 2, new ItemStack(MainInit.oreDust, 9, 1), new Object[] {
			new ItemStack(MainInit.dustBlock, 1, 1) });

		DCRecipe.jsonShapelessRecipe("main_container", 2, new ItemStack(MainInit.oreDust, 9, 2), new Object[] {
			new ItemStack(MainInit.dustBlock, 1, 2) });

		DCRecipe.jsonShapelessRecipe("main_container", 2, new ItemStack(MainInit.oreDust, 9, 3), new Object[] {
			new ItemStack(MainInit.dustBlock, 1, 3) });

		DCRecipe.jsonShapelessRecipe("main_container", 2, new ItemStack(MainInit.oreDust, 9, 5), new Object[] {
			new ItemStack(MainInit.dustBlock, 1, 5) });

		DCRecipe.jsonShapelessRecipe("main_container", 2, new ItemStack(MainInit.oreDust, 9, 7), new Object[] {
			new ItemStack(MainInit.dustBlock, 1, 7) });

		DCRecipe.jsonShapelessRecipe("main_container", 2, new ItemStack(MainInit.oreDust, 9, 8), new Object[] {
			new ItemStack(MainInit.dustBlock, 1, 8) });

		DCRecipe.jsonShapelessRecipe("main_container", 2, new ItemStack(MainInit.oreDust, 9, 4), new Object[] {
			new ItemStack(MainInit.dustBlock, 1, 11) });

		DCRecipe.jsonShapelessRecipe("main_container", 2, new ItemStack(MainInit.oreDust, 9, 11), new Object[] {
			new ItemStack(MainInit.dustBlock, 1, 13) });

		DCRecipe.jsonShapelessRecipe("main_container", 2, new ItemStack(MainInit.oreDust, 9, 12), new Object[] {
			new ItemStack(MainInit.dustBlock, 1, 14) });

		DCRecipe.jsonShapelessRecipe("main_container", 2, new ItemStack(MainInit.miscDust, 1, 1), new Object[] {
			new ItemStack(MainInit.dustBlock_2, 1, 1) });

		DCRecipe.jsonShapelessRecipe("main_container", 2, new ItemStack(Items.IRON_INGOT, 9, 0), new Object[] {
			new ItemStack(MainInit.heatedMetalBlock, 1, 2) });

		// ingot <-> metalBlock
		ingotRecipe(new ItemStack(MainInit.metalBlockNew, 1, 0), new ItemStack(MainInit.oreIngot, 9, 1), "Zinc");
		ingotRecipe(new ItemStack(MainInit.metalBlockNew, 1, 1), new ItemStack(MainInit.oreIngot, 9, 0), "Copper");
		ingotRecipe(new ItemStack(MainInit.metalBlockNew, 1, 2), new ItemStack(MainInit.oreIngot, 9, 8), "Tin");
		ingotRecipe(new ItemStack(MainInit.metalBlockNew, 1, 3), new ItemStack(MainInit.oreIngot, 9, 15), "Lead");
		ingotRecipe(new ItemStack(MainInit.metalBlockNew, 1, 4), new ItemStack(MainInit.oreIngot, 9, 12), "Aluminum");
		ingotRecipe(new ItemStack(MainInit.metalBlockNew, 1, 5), new ItemStack(MainInit.oreIngot, 9, 2), "Nickel");
		ingotRecipe(new ItemStack(MainInit.metalBlockNew, 1, 6), new ItemStack(MainInit.oreIngot, 9, 3), "Silver");
		ingotRecipe(new ItemStack(MainInit.metalBlockNew, 1, 7), new ItemStack(MainInit.oreIngot, 9, 13), "Bismuth");

		ingotRecipe(new ItemStack(MainInit.metalBlockAlloy, 1, 0), new ItemStack(MainInit.oreIngot, 9, 4), "Brass");
		ingotRecipe(new ItemStack(MainInit.metalBlockAlloy, 1, 1), new ItemStack(MainInit.oreIngot, 9, 9), "Bronze");
		ingotRecipe(new ItemStack(MainInit.metalBlockAlloy, 1, 2), new ItemStack(MainInit.oreIngot, 9, 5), "Steel");
		ingotRecipe(new ItemStack(MainInit.metalBlockAlloy, 1, 3), new ItemStack(MainInit.oreIngot, 9,
				6), "Nickelsilver");
		ingotRecipe(new ItemStack(MainInit.metalBlockAlloy, 1, 4), new ItemStack(MainInit.oreIngot, 9,
				17), "ToolSteel");
		ingotRecipe(new ItemStack(MainInit.metalBlockAlloy, 1, 5), new ItemStack(MainInit.oreIngot, 9,
				10), "StainlessSteel");
		ingotRecipe(new ItemStack(MainInit.metalBlockAlloy, 1, 6), new ItemStack(MainInit.oreIngot, 9, 7), "Magnet");
		ingotRecipe(new ItemStack(MainInit.metalBlockAlloy, 1, 7), new ItemStack(MainInit.oreIngot, 9,
				11), "TitaniumAlloy");
		ingotRecipe(new ItemStack(MainInit.metalBlockAlloy, 1, 8), new ItemStack(MainInit.oreIngot, 9, 14), "BSCCO");
		ingotRecipe(new ItemStack(MainInit.metalBlockAlloy, 1, 9), new ItemStack(MainInit.oreIngot, 9,
				18), "Mangalloy");

		gemRecipe(new ItemStack(MainInit.gemBlock, 1, 0), new ItemStack(MainInit.gems, 4, 0), "ChalcedonyBlue");
		gemRecipe(new ItemStack(MainInit.gemBlock, 1, 1), new ItemStack(MainInit.gems, 4, 1), "ChalcedonyRed");
		gemRecipe(new ItemStack(MainInit.gemBlock, 1, 2), new ItemStack(MainInit.gems, 4, 2), "ChalcedonyWhite");
		gemRecipe(new ItemStack(MainInit.gemBlock, 1, 3), new ItemStack(MainInit.gems, 4, 3), "Gypsum");
		gemRecipe(new ItemStack(MainInit.gemBlock, 1, 4), new ItemStack(MainInit.gems, 4, 4), "Sapphire");
		gemRecipe(new ItemStack(MainInit.gemBlock, 1, 5), new ItemStack(MainInit.gems, 4, 8), "Salt");
		gemRecipe(new ItemStack(MainInit.gemBlock, 1, 7), new ItemStack(MainInit.gems, 4, 11), "Schorl");
		gemRecipe(new ItemStack(MainInit.gemBlock, 1, 9), new ItemStack(MainInit.gems, 4, 12), "Serpentine");
		gemRecipe(new ItemStack(MainInit.gemBlock, 1, 10), new ItemStack(MainInit.gems, 4, 13), "Olivine");
		gemRecipe(new ItemStack(MainInit.gemBlock, 1, 11), new ItemStack(MainInit.gems, 4, 14), "Garnet");

		DCRecipe.jsonShapedRecipe("main_container", new ItemStack(MainInit.gemBlock, 1, 8), new Object[] {
			"XXX",
			"X X",
			"XXX",
			'X',
			"gemChalcedony" });

		DCRecipe.jsonShapedRecipe("main_container", 2, new ItemStack(MainInit.gemBlock, 1, 8), new Object[] {
			"XXX",
			"X X",
			"XXX",
			'X',
			"gemQuartz" });

		DCRecipe.jsonShapelessRecipe("main_container", 2, new ItemStack(MainInit.gems, 8, 2), new Object[] {
			new ItemStack(MainInit.gemBlock, 1, 8) });

		DCRecipe.jsonShapedRecipe("main", new ItemStack(MainInit.gemBlock, 4, 12), new Object[] {
			"XX",
			"XX",
			'X',
			new ItemStack(Blocks.BEDROCK, 1, 0) });

		DCRecipe.jsonShapelessRecipe("main", new ItemStack(Blocks.BEDROCK, 1, 0), new Object[] {
			new ItemStack(MainInit.gemBlock, 1, 12) });
	}

	static void ingotRecipe(ItemStack block, ItemStack ingot, String name) {
		DCRecipe.jsonShapedRecipe("main_container", block, new Object[] { "XXX", "XXX", "XXX", 'X', "ingot" + name });

		DCRecipe.jsonShapelessRecipe("main_container", ingot, new Object[] { "block" + name });
	}

	static void gemRecipe(ItemStack block, ItemStack gem, String name) {
		DCRecipe.jsonShapedRecipe("main_container", block, new Object[] { "XX", "XX", 'X', "gem" + name });

		DCRecipe.jsonShapelessRecipe("main_container", gem, new Object[] { "block" + name });
	}

}
