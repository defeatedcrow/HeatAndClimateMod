package defeatedcrow.hac.main.recipes.device;

import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.config.ModuleConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class RegisterCrusherRecipe {

	public static void load() {

		if (ModuleConfig.machine) {
			// SUS
			ItemStack sus = new ItemStack(MachineInit.rotaryBlade, 1, 0);

			// Titanium
			ItemStack ti = new ItemStack(MachineInit.rotaryBlade, 1, 1);

			// Screen
			ItemStack sc = new ItemStack(MachineInit.rotaryBlade, 1, 2);

			// foods
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 3, 0), sus, "gemSalt");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 3, 1), new ItemStack(
					MainInit.foodDust, 1, 0), 0.5F, sus, "cropWheat");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 2), new ItemStack(
					MainInit.foodDust, 1, 0), 0.5F, sus, "seedRice");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 2), new ItemStack(
					MainInit.foodDust, 1, 0), 0.5F, new ItemStack(MainInit.foodDust, 1,
							1), 0.5F, null, sus, "cropRice");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(FoodInit.seeds, 1, 4), new ItemStack(MainInit.foodDust,
					1, 1), 0.25F, null, 0F, new FluidStack(MainInit.tomatoJuice, 100), sus, "cropCoffee");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), null, 0F, new FluidStack(
					MainInit.lemon, 250), sus, "cropLemon");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, new FluidStack(
					MainInit.oil, 400), sus, "cropOlive");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, new FluidStack(
					MainInit.oil, 200), sus, "seedCotton");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1, 4), null, 0F, new FluidStack(
					MainInit.oil, 200), sus, "cropSoybean");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, new FluidStack(
					MainInit.oil, 100), sus, "dustBran");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, new FluidStack(
					MainInit.oil, 50), sus, "listAllseeds");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 1, 4), null, 0F, new FluidStack(
					MainInit.oil, 200), new ItemStack(MachineInit.rotaryBlade, 1, 0), "cropWalnut");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.SUGAR, 6, 0), new ItemStack(MainInit.foodDust, 1,
					7), 1.0F, new ItemStack(MainInit.foodDust, 1, 2), 1.0F, sus, "sugarcane");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.SUGAR, 2, 0), new ItemStack(MainInit.foodDust, 1,
					2), 0.2F, new ItemStack(MainInit.foodDust, 1, 1), 0.2F, sus, "cropBeetroot");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 3, 3), new ItemStack(
					MainInit.foodDust, 1, 1), 0.5F, sus, "cropPotato");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 3), new ItemStack(
					MainInit.foodDust, 1, 1), 0.5F, sus, "foodRice");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 2, 3), new ItemStack(
					MainInit.foodDust, 1, 1), 0.5F, sus, "foodFlour");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 3), new ItemStack(
					MainInit.foodDust, 1, 1), 0.5F, sus, new ItemStack(Items.POISONOUS_POTATO));

			if (OreDictionary.doesOreNameExist("plantRoot")) {
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 1, 3), new ItemStack(
						MainInit.foodDust, 1, 0), 0.5F, new ItemStack(MainInit.foodDust, 1, 1), 0.5F, sus, "plantRoot");
			}

			if (OreDictionary.doesOreNameExist("cropCorn")) {
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodMaterials, 3, 3), new ItemStack(
						MainInit.foodDust, 1, 0), 0.5F, new ItemStack(MainInit.foodDust, 1, 1), 0.5F, sus, "cropCorn");
			}

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.BLAZE_POWDER, 4, 0), new ItemStack(
					MainInit.miscDust, 1, 7), 0.5F, new ItemStack(MainInit.miscDust, 1, 6), 0.5F, sus, "stickBlaze");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.DYE, 6, 15), new ItemStack(MainInit.miscDust, 1,
					9), 1.0F, sus, "bone");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), new ItemStack(
					MainInit.miscDust, 1, 3), 0.5F, sus, "treeSapling");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), new ItemStack(
					MainInit.miscDust, 1, 3), 0.5F, sus, "treeLeaves");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.foodDust, 1, 1), sus, "blockTallgrass");

			// new ores
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 0), sus, "oreRed");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 1), sus, "oreGreen");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 2), sus, "oreBlue");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 3), sus, "oreWhite");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 4), sus, "oreBlack");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 5), sus, "oreLargeRed");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 6), sus, "oreLargeGreen");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 7), sus, "oreLargeBlue");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 8), sus, "oreLargeWhite");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 3, 9), sus, "oreLargeBlack");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 0), new ItemStack(MainInit.oreItem,
					1, 5), 0.5F, new ItemStack(MainInit.oreItem, 1, 10), 0.1F, ti, "oreRed");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 1), new ItemStack(MainInit.oreItem,
					1, 6), 0.5F, new ItemStack(MainInit.oreItem, 1, 11), 0.1F, ti, "oreGreen");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 2), new ItemStack(MainInit.oreItem,
					1, 7), 0.5F, new ItemStack(MainInit.oreItem, 1, 12), 0.1F, ti, "oreBlue");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 3), new ItemStack(MainInit.oreItem,
					1, 8), 0.5F, new ItemStack(MainInit.oreItem, 1, 13), 0.1F, ti, "oreWhite");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 4), new ItemStack(MainInit.oreItem,
					1, 9), 0.5F, new ItemStack(MainInit.oreItem, 1, 14), 0.1F, ti, "oreBlack");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 5), new ItemStack(MainInit.oreItem,
					1, 10), 0.3F, new ItemStack(MainInit.gems_red, 1, 1), 0.05F, ti, "oreLargeRed");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 6), new ItemStack(MainInit.oreItem,
					1, 11), 0.3F, new ItemStack(MainInit.gems_green, 1, 1), 0.05F, ti, "oreLargeGreen");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 7), new ItemStack(MainInit.oreItem,
					1, 12), 0.3F, new ItemStack(MainInit.gems_blue, 1, 1), 0.05F, ti, "oreLargeBlue");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 8), new ItemStack(MainInit.oreItem,
					1, 13), 0.3F, new ItemStack(MainInit.gems_white, 1, 1), 0.05F, ti, "oreLargeWhite");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreItem, 2, 9), new ItemStack(MainInit.oreItem,
					1, 14), 0.3F, new ItemStack(MainInit.gems_black, 1, 1), 0.05F, ti, "oreLargeBlack");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_red, 1, 0), new ItemStack(
					MainInit.gems_red, 1, 1), 0.3F, new ItemStack(MainInit.gems_red, 1, 2), 0.03F, sc, "oreRed");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_green, 1, 0), new ItemStack(
					MainInit.gems_green, 1, 1), 0.3F, new ItemStack(MainInit.gems_green, 1, 2), 0.03F, sc, "oreGreen");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_blue, 1, 0), new ItemStack(
					MainInit.gems_blue, 1, 1), 0.3F, new ItemStack(MainInit.gems_blue, 1, 2), 0.03F, sc, "oreBlue");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_white, 1, 0), new ItemStack(
					MainInit.gems_white, 1, 1), 0.3F, new ItemStack(MainInit.gems_white, 1, 2), 0.03F, sc, "oreWhite");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_black, 1, 0), new ItemStack(
					MainInit.gems_black, 1, 1), 0.3F, new ItemStack(MainInit.gems_black, 1, 2), 0.03F, sc, "oreBlack");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_red, 1, 1), new ItemStack(
					MainInit.gems_red, 1, 2), 0.3F, sc, "oreLargeRed");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_green, 1, 1), new ItemStack(
					MainInit.gems_green, 1, 2), 0.3F, sc, "oreLargeGreen");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_blue, 1, 1), new ItemStack(
					MainInit.gems_blue, 1, 2), 0.3F, sc, "oreLargeBlue");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_white, 1, 1), new ItemStack(
					MainInit.gems_white, 1, 2), 0.3F, sc, "oreLargeWhite");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_black, 1, 1), new ItemStack(
					MainInit.gems_black, 1, 2), 0.3F, sc, "oreLargeBlack");

			// ores
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 0), new ItemStack(MainInit.miscDust,
					1, 2), 0.2F, new ItemStack(Items.DYE, 1, 4), 0.1F, null, ti, "oreCopper");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 11), new ItemStack(
					MainInit.miscDust, 1, 2), 0.2F, new ItemStack(MainInit.gems_blue, 1,
							1), 0.05F, null, ti, "oreAluminum");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 14), new ItemStack(
					MainInit.miscDust, 1, 2), 0.2F, new ItemStack(MainInit.gems_red, 1,
							2), 0.05F, null, ti, "oreManganese");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 8), new ItemStack(Items.CLAY_BALL,
					1, 0), 0.2F, new ItemStack(Items.REDSTONE, 1, 0), 0.1F, null, ti, "oreTin");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 2), new ItemStack(Items.CLAY_BALL,
					1, 0), 0.2F, new ItemStack(Items.EMERALD, 1, 0), 0.05F, null, ti, "oreNickel");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 9), new ItemStack(Items.CLAY_BALL,
					1, 0), 0.2F, new ItemStack(MainInit.gems_green, 1, 2), 0.05F, null, ti, "oreChromium");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 1), new ItemStack(
					MainInit.gems_layer, 1, 0), 0.2F, new ItemStack(MainInit.gems_blue, 1,
							0), 0.1F, null, ti, "oreZinc");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 13), new ItemStack(
					MainInit.gems_layer, 1, 0), 0.2F, new ItemStack(MainInit.oreDust, 1,
							3), 0.05F, null, ti, "oreLead");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 12), new ItemStack(
					MainInit.gems_layer, 1, 0), 0.2F, new ItemStack(MainInit.gems_blue, 1,
							2), 0.05F, null, ti, "oreBismuth");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 5), new ItemStack(MainInit.miscDust,
					1, 1), 0.2F, new ItemStack(MainInit.gems_white, 1, 0), 0.03F, null, ti, "oreIron");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 4), new ItemStack(MainInit.miscDust,
					1, 1), 0.2F, new ItemStack(Items.DIAMOND, 1, 0), 0.05F, null, ti, "oreGold");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 10), new ItemStack(
					MainInit.miscDust, 1, 1), 0.2F, new ItemStack(MainInit.gems_white, 1,
							2), 0.05F, null, ti, "oreTitanium");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 7), new ItemStack(MainInit.miscDust,
					1, 13), 0.2F, new ItemStack(Items.BLAZE_POWDER, 1, 0), 0.05F, null, ti, "oreMagnetite");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 3), new ItemStack(MainInit.miscDust,
					1, 13), 0.2F, new ItemStack(MainInit.gems_black, 1, 1), 0.05F, null, ti, "oreSilver");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 15), new ItemStack(
					MainInit.miscDust, 1, 13), 0.2F, new ItemStack(MainInit.gems_black, 1,
							2), 0.05F, null, ti, "oreMolybdenum");

			// layer
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_layer, 4, 0), new ItemStack(
					MainInit.gems_layer, 1, 1), 0.1F, ti, "oreGypsum");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 4, 2), new ItemStack(
					Items.PRISMARINE_SHARD), 0.1F, ti, "oreLime");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_layer, 4, 1), new ItemStack(
					MainInit.oreDust, 1, 5), 0.1F, ti, "oreSalt");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_layer, 2, 2), new ItemStack(
					MainInit.miscDust, 1, 9), 0.1F, null, ti, "oreNiter");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_layer, 2, 3), new ItemStack(
					Items.BLAZE_POWDER, 1, 0), 0.1F, null, ti, "oreSulfur");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_green, 2, 3), new ItemStack(
					MainInit.oreDust, 1, 2), 0.1F, null, ti, "oreSerpentine");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_layer, 1, 3), new ItemStack(
					MainInit.gems_layer, 2, 0), 1F, null, ti, "oreTravertine");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_layer, 3, 6), new ItemStack(
					MainInit.gems_blue, 1, 3), 1F, null, ti, "oreApatite");

			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_red, 2, 5), new ItemStack(MainInit.oreItem,
					1, 10), 0.3F, null, sc, "oreRuby");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_blue, 2, 5), new ItemStack(Items.EMERALD,
					1, 0), 0.3F, null, sc, "oreAquamarine");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_white, 2, 3), new ItemStack(
					MainInit.gems_red, 1, 4), 0.3F, null, sc, "oreSakura");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_black, 2, 3), new ItemStack(
					MainInit.gems_black, 1, 5), 0.03F, null, sc, "oreIolite");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_white, 2, 5), new ItemStack(
					MainInit.oreItem, 1, 13), 0.3F, null, sc, "oreTopaz");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_red, 2, 3), new ItemStack(
					MainInit.gems_black, 1, 4), 0.3F, null, sc, "oreAmethyst");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_blue, 2, 4), new ItemStack(
					MainInit.gems_layer, 1, 6), 0.3F, null, sc, "oreLarimar");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_green, 2, 4), new ItemStack(
					MainInit.gems_green, 1, 5), 0.3F, null, sc, "oreFluorite");

			// vanilla
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.QUARTZ, 3, 0), new ItemStack(MainInit.gems_white,
					1, 0), 0.1F, null, sc, "oreQuartz");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.EMERALD, 3, 0), new ItemStack(MainInit.gems_red, 1,
					2), 0.05F, null, sc, "oreEmerald");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.DIAMOND, 3, 0), new ItemStack(MainInit.miscDust, 1,
					12), 0.15F, null, sc, "oreDiamond");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.DYE, 8, 4), new ItemStack(MainInit.oreDust, 1,
					0), 1F, null, sc, "oreLapis");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.REDSTONE, 8, 0), new ItemStack(MainInit.oreDust, 1,
					9), 0.25F, null, sc, "oreRedstone");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.COAL, 3, 0), new ItemStack(MainInit.miscDust, 1,
					0), 1F, null, sc, "oreCoal");

			// old
			if (!ModuleConfig.delete_old) {
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_blue, 2, 0), new ItemStack(
						Items.QUARTZ, 1, 0), 0.05F, new ItemStack(MainInit.gems_blue, 1,
								1), 0.1F, null, ti, "oreChalcedonyBlue");
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_white, 2, 0), new ItemStack(
						MainInit.oreDust, 1, 3), 0.25F, new ItemStack(MainInit.oreDust, 1,
								4), 0.1F, null, ti, "oreChalcedonyWhite");
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_blue, 2, 1), new ItemStack(
						MainInit.oreDust, 1, 13), 0.1F, new ItemStack(MainInit.gems_blue, 1,
								0), 0.1F, null, ti, "oreSapphire");
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_black, 2, 1), new ItemStack(
						MainInit.gems_red, 1, 2), 0.03F, new ItemStack(MainInit.gems_red, 1,
								3), 0.03F, null, ti, "oreSchorl");
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_red, 2, 1), new ItemStack(
						MainInit.gems_blue, 1, 2), 0.1F, new ItemStack(MainInit.oreDust, 2,
								14), 0.03F, null, ti, "oreGarnet");
				RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.gems_layer, 2, 6), new ItemStack(
						MainInit.miscDust, 1, 6), 0.5F, new ItemStack(Items.DYE, 1, 15), 0.5F, null, ti, "oreApatite");
			}

			// ex
			if (!OreDictionary.getOres("dustOsmium").isEmpty()) {
				ItemStack os = OreDictionary.getOres("dustOsmium").get(0);
				if (!OreDictionary.getOres("dustIridium").isEmpty()) {
					ItemStack gem = OreDictionary.getOres("dustIridium").get(0);
					RecipeAPI.registerCrushers.addRecipe(new ItemStack(os.getItem(), 2, os
							.getItemDamage()), gem, 0.1F, null, 0F, null, ti, "oreOsmium");
				} else {
					RecipeAPI.registerCrushers.addRecipe(new ItemStack(os.getItem(), 2, os
							.getItemDamage()), null, 0F, null, 0F, null, ti, "oreOsmium");
				}
			}

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
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 14), ti, "ingotManganese");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 0), ti, new ItemStack(Items.COAL,
					1, 0));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 1), ti, "gemQuartz");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 1), ti, "gemChalcedony");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 11), ti, "gemSapphire");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 11), new ItemStack(MainInit.oreDust,
					1, 9), 0.5F, ti, "gemRuby");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 6, 3), ti, "logWood");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 3, 6), ti, "gemNiter");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 3, 7), ti, "gemSulfur");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 8), ti, "gemGarnet");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 10), ti, "gemRutile");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 2, 11), ti, "gemBauxite");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.oreDust, 1, 12), ti, "gemBismuth");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 3, 9), ti, "gemApatite");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 11), ti, "gemSerpentine");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 2, 12), ti, "gemSakura");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN
					.getDyeDamage()), ti, "gemMalachite");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MachineInit.reagent, 2, 0), new ItemStack(
					MainInit.miscDust, 1, 13), 1.0F, ti, "gemCrudeOil");

			if (!OreDictionary.getOres("dustLithium").isEmpty()) {
				ItemStack dust = OreDictionary.getOres("dustLithium").get(0).copy();
				RecipeAPI.registerCrushers.addRecipe(dust, ti, "gemKunzite");
			}

			if (!OreDictionary.getOres("dustObsidian").isEmpty()) {
				ItemStack dust = OreDictionary.getOres("dustObsidian").get(0).copy();
				dust.setCount(2);
				RecipeAPI.registerCrushers.addRecipe(dust, new ItemStack(MainInit.miscDust, 1, 1), 1F, ti, "obsidian");
			}

			// 以下バニラ
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), new ItemStack(MainInit.gems_white,
					1, 0), 0.03F, ti, new ItemStack(Blocks.COBBLESTONE, 1, 0));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), new ItemStack(Items.QUARTZ, 1,
					0), 0.04F, ti, new ItemStack(Blocks.STONE, 1, 1));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), new ItemStack(MainInit.miscDust, 1,
					12), 0.05F, ti, new ItemStack(Blocks.STONE, 1, 3));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.GRAVEL, 1, 0), new ItemStack(MainInit.miscDust, 1,
					12), 0.05F, ti, new ItemStack(Blocks.STONE, 1, 5));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Items.FLINT, 1,
					0), 1.0F, ti, new ItemStack(Blocks.GRAVEL, 1, 0));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.SAND, 4, 0), ti, new ItemStack(Blocks.SANDSTONE,
					1, 32767));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.SAND, 4, 0), new ItemStack(MainInit.oreDust, 1,
					11), 1F, null, 0F, null, ti, new ItemStack(Blocks.RED_SANDSTONE, 1, 32767));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(MainInit.oreDust, 1,
					11), 1F, null, 0F, null, ti, new ItemStack(Blocks.SAND, 1, 1));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.CLAY_BALL, 1, 0), new ItemStack(MainInit.miscDust,
					1, 2), 1F, new ItemStack(MainInit.miscDust, 1, 10), 0.1F, null, ti, new ItemStack(Blocks.SAND, 1,
							0));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(MainInit.miscDust, 4, 2), new ItemStack(
					MainInit.miscDust, 1, 9), 1F, new ItemStack(MainInit.miscDust, 1, 6), 0.5F, null, ti, new ItemStack(
							Blocks.BONE_BLOCK, 1, 0));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.GLOWSTONE_DUST, 4, 0), ti, new ItemStack(
					Blocks.GLOWSTONE));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.BLAZE_POWDER, 2, 0), ti, new ItemStack(
					Blocks.MAGMA));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.ICE, 1, 0), null, 0.0F, ti, new ItemStack(
					Blocks.PACKED_ICE));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.NETHERBRICK, 4, 0), null, 0F, ti, new ItemStack(
					Blocks.NETHER_BRICK, 1, 0));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.NETHERBRICK, 6, 0), null, 0F, ti, new ItemStack(
					Blocks.NETHER_BRICK_STAIRS, 1, 0));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.NETHERBRICK, 4, 0), null, 0F, ti, new ItemStack(
					Blocks.NETHER_BRICK_FENCE, 1, 0));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.END_STONE, 1, 0), null, 0F, ti, new ItemStack(
					Blocks.END_BRICKS, 1, 0));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.STRING, 4, 0), null, 0F, ti, new ItemStack(
					Blocks.WOOL, 1, 32767));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.CLAY_BALL, 4, 0), null, 0F, ti, new ItemStack(
					Blocks.STAINED_HARDENED_CLAY, 1, 32767));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.CLAY_BALL, 4, 0), null, 0F, ti, new ItemStack(
					Blocks.HARDENED_CLAY, 1, 0));
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Items.CLAY_BALL, 1, 0), new ItemStack(MainInit.miscDust,
					1, 1), 0.25F, null, ti, "dirt");
			RecipeAPI.registerCrushers.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(MainInit.miscDust, 1,
					7), 0.5F, null, ti, "netherrack");
		}
	}

}
