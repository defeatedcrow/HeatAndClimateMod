package defeatedcrow.hac.main.recipes.device;

import java.util.List;

import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RegisterMillRecipe {

	public static void load() {
		// new ores
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 0), new ItemStack(MainInit.oreItem, 1,
				5), 0.15F, "oreRed");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 1), new ItemStack(MainInit.oreItem, 1,
				6), 0.15F, "oreGreen");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 2), new ItemStack(MainInit.oreItem, 1,
				7), 0.15F, "oreBlue");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 3), new ItemStack(MainInit.oreItem, 1,
				8), 0.15F, "oreWhite");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 4), new ItemStack(MainInit.oreItem, 1,
				9), 0.15F, "oreBlack");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 5), new ItemStack(MainInit.oreItem, 1,
				10), 0.15F, "oreLargeRed");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 6), new ItemStack(MainInit.oreItem, 1,
				11), 0.15F, "oreLargeGreen");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 7), new ItemStack(MainInit.oreItem, 1,
				12), 0.15F, "oreLargeBlue");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 8), new ItemStack(MainInit.oreItem, 1,
				13), 0.15F, "oreLargeWhite");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreItem, 2, 9), new ItemStack(MainInit.oreItem, 1,
				14), 0.15F, "oreLargeBlack");

		// old ores
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 0), "oreCopper");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 1), "oreZinc");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 2), "oreNickel");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 3), "oreSilver");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 4), "oreGold");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 5), "oreIron");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 7), "oreMagnetite");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 8), "oreTin");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 9), "oreChromium");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 10), "oreTitanium");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 11), "oreAluminium");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 11), "oreAluminum");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 12), "oreBismuth");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 13), "oreLead");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 14), "oreManganese");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 2, 15), "oreMolybdenum");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_blue, 2, 0), "oreChalcedonyBlue");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_white, 2, 0), "oreChalcedonyWhite");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_layer, 3, 0), "oreGypsum");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_blue, 2, 1), "oreSapphire");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_layer, 3, 1), "oreSalt");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_layer, 2, 2), "oreNiter");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_layer, 2, 3), "oreSulfur");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_black, 2, 1), "oreSchorl");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_green, 4, 3), "oreSerpentine");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_red, 2, 1), "oreGarnet");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 2, 7), new ItemStack(MainInit.gems_layer, 1,
				0), 0.5F, "oreTravertine");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_red, 2, 5), "oreRuby");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_blue, 2, 5), "oreAquamarine");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_white, 2, 3), "oreSakura");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_black, 2, 3), "oreIolite");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_white, 2, 5), "oreTopaz");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_red, 2, 3), "oreAmethyst");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_blue, 2, 4), "oreLarimar");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.gems_green, 2, 4), "oreFluorite");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 3, 2), "oreLime");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 2, 9), "oreApatite");
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.QUARTZ, 2, 0), "oreQuartz");
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.EMERALD, 2, 0), "oreEmerald");
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.DIAMOND, 2, 0), "oreDiamond");
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.DYE, 3, 4), "oreLapis");
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.REDSTONE, 4, 0), "oreRedstone");
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.COAL, 2, 0), "oreCoal");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.SAND, 4, 0), new ItemStack(MainInit.oreDust, 1,
				11), 0.5F, new ItemStack(Blocks.RED_SANDSTONE, 1, 32767));
		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(MainInit.oreDust, 1,
				11), 0.25F, new ItemStack(Blocks.SAND, 1, 1));
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 4, 2), new ItemStack(MainInit.miscDust, 1,
				9), 1F, new ItemStack(Blocks.BONE_BLOCK, 1, 0));
		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(MainInit.miscDust, 1,
				7), 0.15F, "netherrack");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 0), new ItemStack(Items.COAL, 1, 0));
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 1), "gemQuartz");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 1), "gemChalcedony");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 2, 6), "gemNiter");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 2, 7), "gemSulfur");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 8), "gemGarnet");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 0), "gemSalt");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 10), "gemRutile");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 11), "gemBauxite");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 12), "gemBismuth");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 2, 9), "gemApatite");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 11), "gemSerpentine");
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.DYE, 1, EnumDyeColor.GREEN
				.getDyeDamage()), "gemMalachite");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 9), "gemVivianite");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 12), "gemSakura");

		// ingot粉砕
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 0), "ingotCopper");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 1), "ingotZinc");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 2), "ingotNickel");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 3), "ingotSilver");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 4), "ingotGold");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 5), "ingotIron");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 8), "ingotTin");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 11), "ingotAluminium");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 11), "ingotAluminum");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 12), "ingotBismuth");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 13), "ingotLead");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.oreDust, 1, 14), "ingotManganese");

		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), "treeSapling");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), "blockTallgrass");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), "treeLeaves");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), new ItemStack(Items.POISONOUS_POTATO,
				1, 0));

		// 以下バニラ
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 4, 3), "logWood");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 1), new ItemStack(MainInit.foodDust,
				1, 0), 0.25F, "cropWheat");
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 3), "cropPotato");
		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), null, 0F, "cobblestone");
		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Items.FLINT, 1,
				0), 1.0F, new ItemStack(Blocks.GRAVEL, 1, 0));
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.BLAZE_POWDER, 3, 0), new ItemStack(Items.BLAZE_POWDER, 1,
				0), 0.5F, "stickBlaze");
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.DYE, 5, 15), new ItemStack(Items.DYE, 2,
				15), 0.5F, "bone");

		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.SAND, 4, 0), null, 0.0F, new ItemStack(Blocks.SANDSTONE,
				1, 32767));
		RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.miscDust, 1, 1), new ItemStack(MainInit.miscDust, 1,
				10), 0.5F, new ItemStack(Blocks.SAND, 1, 0));
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.GLOWSTONE_DUST, 4, 0), new ItemStack(Blocks.GLOWSTONE));
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.BLAZE_POWDER, 1, 0), new ItemStack(Blocks.MAGMA));
		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.ICE, 1, 0), null, 0.0F, new ItemStack(
				Blocks.PACKED_ICE));
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.NETHERBRICK, 4, 0), null, 0F, new ItemStack(
				Blocks.NETHER_BRICK, 1, 0));
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.NETHERBRICK, 6, 0), null, 0F, new ItemStack(
				Blocks.NETHER_BRICK_STAIRS, 1, 0));
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.NETHERBRICK, 4, 0), null, 0F, new ItemStack(
				Blocks.NETHER_BRICK_FENCE, 1, 0));
		RecipeAPI.registerMills.addRecipe(new ItemStack(Blocks.END_STONE, 1, 0), null, 0F, new ItemStack(
				Blocks.END_BRICKS, 1, 0));
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.STRING, 4, 0), null, 0F, new ItemStack(Blocks.WOOL, 1,
				32767));
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.STRING, 4, 0), "blockWool");
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.CLAY_BALL, 4, 0), null, 0F, new ItemStack(
				Blocks.STAINED_HARDENED_CLAY, 1, 32767));
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.CLAY_BALL, 4, 0), null, 0F, new ItemStack(
				Blocks.HARDENED_CLAY, 1, 0));
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.CLAY_BALL, 1, 0), "dirt");
		RecipeAPI.registerMills.addRecipe(new ItemStack(Items.SUGAR, 3, 0), new ItemStack(MainInit.foodDust, 1,
				2), 0.25F, "sugarcane");
		// plugin
		List<ItemStack> dust_o = OreDictionary.getOres("dustOsmium");
		if (!dust_o.isEmpty()) {
			ItemStack ret = new ItemStack(dust_o.get(0).getItem(), 2, dust_o.get(0).getItemDamage());
			RecipeAPI.registerMills.addRecipe(ret, "oreOsmium");
		}

		// machine
		if (ModuleConfig.machine) {
			RecipeAPI.registerMills.addRecipe(new ItemStack(MachineInit.reagent, 2, 0), "gemCrudeOil");
		}

		// food
		if (ModuleConfig.food) {
			RecipeAPI.registerMills.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 2), "seedRice");

			RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(MainInit.miscDust, 1,
					4), 0.25F, new ItemStack(FoodInit.seeds, 2, 5));

			RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(MainInit.foodDust, 1,
					4), 0.25F, new ItemStack(FoodInit.seeds, 2, 9));

			RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(MainInit.miscDust, 1,
					4), 0.25F, "cropOlive");

			RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 8, 0), new ItemStack(MainInit.miscDust, 1,
					4), 0.25F, new ItemStack(MainInit.cropBasket, 1, 7));

			RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(MainInit.miscDust, 1,
					4), 0.25F, new ItemStack(Items.WHEAT_SEEDS, 8, 0));

			RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(MainInit.miscDust, 1,
					4), 0.25F, new ItemStack(Items.BEETROOT_SEEDS, 8, 0));

			RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(MainInit.miscDust, 1,
					4), 0.25F, new ItemStack(Items.MELON_SEEDS, 8, 0));

			RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(MainInit.miscDust, 1,
					4), 0.25F, new ItemStack(Items.PUMPKIN_SEEDS, 8, 0));

			RecipeAPI.registerMills.addRecipe(new ItemStack(FoodInit.dropOil, 1, 0), new ItemStack(MainInit.miscDust, 1,
					4), 0.25F, "cropWalnut");

		}
	}

}
