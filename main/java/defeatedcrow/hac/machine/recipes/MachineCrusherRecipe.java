package defeatedcrow.hac.machine.recipes;

import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.MainCoreConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class MachineCrusherRecipe {

	static void load() {

		// SUS
		ItemStack sus = new ItemStack(MachineInit.rotaryBlade, 1, 0);

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 3,
				0), null, 0F, null, 0F, null, sus, "gemSalt");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 3, 1), new ItemStack(
				MainInit.foodDust, 1, 0), 0.5F, null, 0F, null, sus, "cropWheat");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 2), new ItemStack(
				MainInit.foodDust, 1, 0), 0.5F, null, 0F, null, sus, "seedRice");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 2), new ItemStack(
				MainInit.foodDust, 1, 0), 0.5F, new ItemStack(MainInit.foodDust, 1, 1), 0.5F, null, sus, "cropRice");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(FoodInit.seeds, 1, 4), new ItemStack(MainInit.foodDust, 1,
				1), 0.25F, null, 0F, new FluidStack(MainInit.tomatoJuice, 100), sus, "cropCoffee");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, null, 0F, new FluidStack(
				MainInit.lemon, 250), sus, "cropLemon");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, null, 0F, new FluidStack(
				MainInit.oil, 400), sus, "cropOlive");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, null, 0F, new FluidStack(
				MainInit.oil, 200), sus, "seedCotton");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1, 4), null, 0F, null, 0F, new FluidStack(
				MainInit.oil, 200), sus, "cropSoybean");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, null, 0F, new FluidStack(
				MainInit.oil, 100), sus, "dustBlan");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, null, 0F, new FluidStack(
				MainInit.oil, 50), sus, new ItemStack(Items.WHEAT_SEEDS, 1, 0));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, new FluidStack(
				MainInit.oil, 200), new ItemStack(MachineInit.rotaryBlade, 1, 0), "cropWalnut");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.SUGAR, 4, 0), new ItemStack(MainInit.foodDust, 1,
				2), 0.5F, new ItemStack(MainInit.foodDust, 1, 1), 0.5F, null, sus, "sugarcane");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.SUGAR, 2, 0), new ItemStack(MainInit.foodDust, 1,
				2), 0.2F, new ItemStack(MainInit.foodDust, 1, 1), 0.2F, null, sus, "cropBeetroot");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 3, 3), new ItemStack(
				MainInit.foodDust, 1, 1), 0.5F, null, 0F, null, sus, "cropPotato");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 3), new ItemStack(
				MainInit.foodDust, 1, 1), 0.5F, null, 0F, null, sus, "foodRice");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 3), new ItemStack(
				MainInit.foodDust, 1, 1), 0.5F, null, 0F, null, sus, "foodFlour");

		if (OreDictionary.doesOreNameExist("plantRoot")) {
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 3), new ItemStack(
					MainInit.foodDust, 1, 0), 0.5F, new ItemStack(MainInit.foodDust, 1,
							1), 0.5F, null, sus, "plantRoot");
		}

		if (OreDictionary.doesOreNameExist("cropCorn")) {
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 3, 3), new ItemStack(
					MainInit.foodDust, 1, 0), 0.5F, new ItemStack(MainInit.foodDust, 1,
							1), 0.5F, null, sus, "cropCorn");
		}

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.BLAZE_POWDER, 4, 0), new ItemStack(MainInit.miscDust,
				1, 7), 0.5F, new ItemStack(MainInit.miscDust, 1, 6), 0.5F, null, sus, "stickBlaze");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.DYE, 6, 15), new ItemStack(MainInit.miscDust, 1,
				9), 1.0F, null, 0F, null, sus, "bone");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), new ItemStack(MainInit.miscDust, 1,
				3), 0.5F, null, sus, "treeSapling");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), new ItemStack(MainInit.miscDust, 1,
				3), 0.5F, null, sus, "treeLeaves");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1,
				1), null, 0.0F, null, sus, "blockTallGrass");

		// Titanium
		ItemStack ti = new ItemStack(MachineInit.rotaryBlade, 1, 1);

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 3, 0), new ItemStack(MainInit.gems, 1,
				5), 0.1F, new ItemStack(MainInit.oreDust, 1, 4), 0.1F, new FluidStack(MainInit.sulfuricAcid,
						50), ti, "oreCopper");

		if (MainCoreConfig.lead) {
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 3, 1), new ItemStack(MainInit.oreDust,
					1, 13), 0.25F, new ItemStack(MainInit.oreDust, 1, 3), 0.1F, new FluidStack(MainInit.sulfuricAcid,
							50), ti, "oreZinc");
		} else {
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 3, 1), new ItemStack(MainInit.oreDust,
					1, 5), 0.25F, new ItemStack(MainInit.oreDust, 1, 3), 0.1F, new FluidStack(MainInit.sulfuricAcid,
							50), ti, "oreZinc");
		}

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 3, 5), new ItemStack(MainInit.oreDust, 1,
				10), 0.05F, new ItemStack(MainInit.gems, 1, 1), 0.1F, null, ti, "oreIron");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 3, 7), new ItemStack(MainInit.oreDust, 1,
				9), 0.25F, new ItemStack(MainInit.oreDust, 1, 10), 0.05F, null, ti, "oreMagnetite");

		if (!OreDictionary.getOres("dustCobalt").isEmpty()) {
			ItemStack cob = OreDictionary.getOres("dustCobalt").get(0);
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 3, 2), new ItemStack(MainInit.oreDust,
					1, 5), 0.25F, cob, 0.05F, null, ti, "oreNickel");
		} else {
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 3, 2), new ItemStack(MainInit.oreDust,
					1, 5), 0.25F, new ItemStack(MainInit.gems, 1, 12), 0.05F, null, ti, "oreNickel");
		}

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 3, 4), new ItemStack(MainInit.oreDust, 1,
				3), 0.25F, new ItemStack(MainInit.oreDust, 1, 0), 0.1F, null, ti, "oreGold");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 3, 3), new ItemStack(MainInit.oreDust, 1,
				4), 0.25F, null, 0F, new FluidStack(MainInit.sulfuricAcid, 50), ti, "oreSilver");

		if (!OreDictionary.getOres("gemRuby").isEmpty()) {
			ItemStack gem = OreDictionary.getOres("gemRuby").get(0);
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 3, 11), new ItemStack(Items.CLAY_BALL,
					1, 0), 0.25F, gem, 0.03F, null, ti, "oreAluminum");
		} else {
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 3, 11), new ItemStack(Items.CLAY_BALL,
					1, 0), 0.25F, new ItemStack(MainInit.gems, 1, 4), 0.03F, null, ti, "oreAluminum");
		}

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems, 3, 3), new ItemStack(MainInit.gems, 1,
				8), 0.25F, new ItemStack(MainInit.gems, 1, 6), 0.03F, null, ti, "oreGypsum");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems, 3, 0), new ItemStack(Items.QUARTZ, 1,
				0), 0.05F, new ItemStack(MainInit.gems, 1, 4), 0.1F, null, ti, "oreChalcedonyBlue");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems, 3, 2), new ItemStack(MainInit.oreDust, 1,
				3), 0.25F, new ItemStack(MainInit.oreDust, 1, 4), 0.1F, null, ti, "oreChalcedonyWhite");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems, 3, 4), new ItemStack(MainInit.oreDust, 1,
				13), 0.1F, new ItemStack(MainInit.gems, 1, 0), 0.1F, null, ti, "oreSapphire");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 4, 2), new ItemStack(
				Items.PRISMARINE_SHARD), 0.1F, new ItemStack(MainInit.gems, 1, 7), 0.03F, null, ti, "oreLime");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems, 4, 8), new ItemStack(MainInit.oreDust, 1,
				5), 0.25F, null, 0F, null, ti, "oreSalt");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems, 3, 9), new ItemStack(MainInit.gems, 1,
				3), 0.25F, new ItemStack(MainInit.miscDust, 1, 9), 0.1F, null, ti, "oreNiter");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems, 3, 10), new ItemStack(Items.BLAZE_POWDER, 1,
				0), 0.1F, new ItemStack(Items.GLOWSTONE_DUST, 1, 0), 0.05F, new FluidStack(MainInit.sulfuricAcid,
						50), ti, "oreSulfur");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 3, 8), new ItemStack(MainInit.oreDust, 1,
				10), 0.1F, new ItemStack(MainInit.gems, 1, 11), 0.03F, null, ti, "oreTin");

		if (!OreDictionary.getOres("gemAmethyst").isEmpty()) {
			ItemStack gem = OreDictionary.getOres("gemAmethyst").get(0);
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems, 3, 11), new ItemStack(MainInit.gems, 1,
					21), 0.03F, gem, 0.03F, null, ti, "oreSchorl");
		} else {
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems, 3, 11), new ItemStack(MainInit.gems, 1,
					21), 0.03F, new ItemStack(Items.EMERALD, 1, 0), 0.1F, null, ti, "oreSchorl");
		}

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems, 3, 12), new ItemStack(MainInit.oreDust, 1,
				2), 0.25F, new ItemStack(MainInit.gems, 1, 13), 0.1F, null, ti, "oreSerpentine");

		if (!OreDictionary.getOres("dustManganese").isEmpty()) {
			ItemStack gem = OreDictionary.getOres("dustManganese").get(0);
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems, 3, 14), new ItemStack(MainInit.gems, 1,
					20), 0.1F, gem, 0.03F, null, ti, "oreGarnet");
		} else {
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems, 3, 14), new ItemStack(MainInit.gems, 1,
					20), 0.1F, new ItemStack(MainInit.gems, 1, 19), 0.03F, null, ti, "oreGarnet");
		}

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 3, 10), new ItemStack(MainInit.gems, 1,
				15), 1F, new ItemStack(Items.QUARTZ, 1, 0), 0.25F, null, ti, "oreTitanium");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 3, 12), new ItemStack(MainInit.oreDust, 1,
				1), 0.5F, new ItemStack(MainInit.oreDust, 1, 0), 0.25F, null, ti, "oreBismuth");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems, 3, 18), new ItemStack(MainInit.miscDust, 1,
				6), 0.5F, new ItemStack(Items.DYE, 1, 15), 0.5F, null, ti, "oreApatite");

		if (!OreDictionary.getOres("oreLead").isEmpty()) {
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 3, 13), new ItemStack(MainInit.oreDust,
					1, 1), 0.25F, new ItemStack(MainInit.oreDust, 1, 3), 0.1F, null, ti, "oreLead");
		}

		if (!OreDictionary.getOres("dustOsmium").isEmpty()) {
			ItemStack os = OreDictionary.getOres("dustOsmium").get(0);
			if (!OreDictionary.getOres("dustIridium").isEmpty()) {
				ItemStack gem = OreDictionary.getOres("dustIridium").get(0);
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(os.getItem(), 3, os
						.getItemDamage()), gem, 0.1F, null, 0F, null, ti, "oreOsmium");
			} else {
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(os.getItem(), 3, os
						.getItemDamage()), null, 0F, null, 0F, null, ti, "oreOsmium");
			}
		}

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.QUARTZ, 3, 0), new ItemStack(MainInit.gems, 1,
				1), 0.1F, new ItemStack(Items.REDSTONE, 1, 0), 0.1F, null, ti, "oreQuartz");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.EMERALD, 3, 0), new ItemStack(MainInit.gems, 1,
				2), 0.25F, new ItemStack(MainInit.gems, 1, 21), 0.03F, null, ti, "oreEmerald");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.DIAMOND, 3, 0), new ItemStack(MainInit.gems, 1,
				13), 0.1F, new ItemStack(MainInit.miscDust, 1, 12), 0.15F, null, ti, "oreDiamond");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.DYE, 8, 4), new ItemStack(MainInit.oreDust, 1,
				0), 1F, new ItemStack(MainInit.gems, 1, 20), 0.03F, null, ti, "oreLapis");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.REDSTONE, 8, 0), new ItemStack(MainInit.oreDust, 1,
				9), 0.25F, new ItemStack(MainInit.gems, 1, 1), 0.25F, null, ti, "oreRedstone");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.COAL, 4, 0), new ItemStack(MainInit.miscDust, 1,
				0), 1F, new ItemStack(Items.CLAY_BALL, 1, 0), 0.5F, null, ti, "oreCoal");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.SAND, 4, 0), new ItemStack(MainInit.oreDust, 1,
				11), 1F, null, 0F, null, ti, new ItemStack(Blocks.RED_SANDSTONE, 1, 32767));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(MainInit.oreDust, 1,
				11), 1F, null, 0F, null, ti, new ItemStack(Blocks.SAND, 1, 1));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.CLAY_BALL, 1, 0), new ItemStack(MainInit.miscDust, 1,
				2), 1F, new ItemStack(MainInit.miscDust, 1, 10), 0.1F, null, ti, new ItemStack(Blocks.SAND, 1, 0));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 4, 2), new ItemStack(MainInit.miscDust, 1,
				9), 1F, new ItemStack(MainInit.miscDust, 1, 6), 0.5F, null, ti, new ItemStack(Blocks.BONE_BLOCK, 1, 0));

		// ingot粉砕

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 0), ti, "ingotCopper");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 1), ti, "ingotZinc");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 2), ti, "ingotNickel");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 3), ti, "ingotSilver");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 4), ti, "ingotGold");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 5), ti, "ingotIron");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 8), ti, "ingotTin");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 11), ti, "ingotAluminium");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 11), ti, "ingotAluminum");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 12), ti, "ingotBismuth");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 13), ti, "ingotLead");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 0), ti, new ItemStack(Items.COAL, 1,
				0));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 1), ti, "gemQuartz");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 1), ti, "gemChalcedony");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 11), ti, "gemSapphire");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 6, 3), ti, "logWood");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 6), ti, "gemNiter");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 7), ti, "gemSulfur");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 8), ti, "gemGarnet");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 10), ti, "gemRutile");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 11), ti, "gemBauxite");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 12), ti, "gemBismuth");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 9), ti, "gemApatite");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 11), ti, "gemSerpentine");

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN
				.getDyeDamage()), ti, "gemMalachite");

		if (!OreDictionary.getOres("dustLithium").isEmpty()) {
			ItemStack dust = OreDictionary.getOres("dustLithium").get(0);
			RecipeAPI.registerCrushers.addRecipe(dust, ti, "gemKunzite");
		}

		if (!OreDictionary.getOres("dustObsidian").isEmpty()) {
			ItemStack dust = OreDictionary.getOres("dustObsidian").get(0).copy();
			dust.setCount(2);
			RecipeAPI.registerCrushers.addRecipe(dust, new ItemStack(MainInit.miscDust, 1, 1), 1F, ti, "obsidian");
		}

		// 以下バニラ
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), ti, new ItemStack(Blocks.COBBLESTONE,
				1, 0));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), new ItemStack(Items.QUARTZ, 1,
				0), 0.03F, ti, new ItemStack(Blocks.STONE, 1, 1));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), new ItemStack(MainInit.miscDust, 1,
				12), 0.03F, ti, new ItemStack(Blocks.STONE, 1, 3));
		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), new ItemStack(MainInit.gems, 1,
				20), 0.03F, ti, new ItemStack(Blocks.STONE, 1, 5));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Items.FLINT, 1,
				0), 1.0F, ti, new ItemStack(Blocks.GRAVEL, 1, 0));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.GLOWSTONE_DUST, 4, 0), ti, new ItemStack(
				Blocks.GLOWSTONE));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.BLAZE_POWDER, 1, 0), ti, new ItemStack(Blocks.MAGMA));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.ICE, 1, 0), null, 0.0F, ti, new ItemStack(
				Blocks.PACKED_ICE));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.NETHERBRICK, 4, 0), null, 0F, ti, new ItemStack(
				Blocks.NETHER_BRICK, 1, 0));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.NETHERBRICK, 6, 0), null, 0F, ti, new ItemStack(
				Blocks.NETHER_BRICK_STAIRS, 1, 0));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.NETHERBRICK, 4, 0), null, 0F, ti, new ItemStack(
				Blocks.NETHER_BRICK_FENCE, 1, 0));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.END_STONE, 4, 0), null, 0F, ti, new ItemStack(
				Blocks.END_BRICKS, 1, 0));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.STRING, 4, 0), null, 0F, ti, new ItemStack(Blocks.WOOL,
				1, 32767));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.CLAY_BALL, 4, 0), null, 0F, ti, new ItemStack(
				Blocks.STAINED_HARDENED_CLAY, 1, 32767));

		RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.CLAY_BALL, 4, 0), null, 0F, ti, new ItemStack(
				Blocks.HARDENED_CLAY, 1, 0));
	}

}
