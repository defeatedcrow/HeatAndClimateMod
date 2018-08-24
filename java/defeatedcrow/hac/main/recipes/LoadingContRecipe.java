package defeatedcrow.hac.main.recipes;

import defeatedcrow.hac.core.DCRecipe;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.util.RecipeResourcesMain;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

public class LoadingContRecipe {

	public static void add(RecipeResourcesMain res) {
		ItemStack[] logs = new ItemStack[] {
				new ItemStack(Blocks.LOG, 8, 0),
				new ItemStack(Blocks.LOG, 8, 1),
				new ItemStack(Blocks.LOG, 8, 2),
				new ItemStack(Blocks.LOG, 8, 3),
				new ItemStack(Blocks.LOG2, 8, 0),
				new ItemStack(Blocks.LOG2, 8, 1),
				new ItemStack(Items.COAL, 8, 1)
		};
		for (int i = 0; i < logs.length; i++) {
			ItemStack c2 = logs[i].copy();
			c2.setCount(1);
			DCRecipe.jsonShapedRecipe("main_container", new ItemStack(MainInit.logCont, 1, i), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					c2
			});

			DCRecipe.jsonShapelessRecipe("main_container", logs[i], new Object[] {
					new ItemStack(MainInit.logCont, 1, i)
			});
		}

		ItemStack[] crops = new ItemStack[] {
				new ItemStack(Items.APPLE, 8, 0),
				new ItemStack(Items.POTATO, 8, 0),
				new ItemStack(Items.CARROT, 8, 0),
				new ItemStack(Blocks.PUMPKIN, 8, 0),
				new ItemStack(Blocks.MELON_BLOCK, 8, 0),
				new ItemStack(Blocks.CACTUS, 8, 0),
				new ItemStack(Items.REEDS, 8, 0),
				new ItemStack(Items.NETHER_WART, 8, 0),
				new ItemStack(Items.DYE, 8, EnumDyeColor.BROWN.getDyeDamage()),
				new ItemStack(MainInit.bakedApple, 8, 0),
				new ItemStack(Items.BAKED_POTATO, 8, 0),
				new ItemStack(Items.BEETROOT, 8, 0)
		};
		for (int i = 0; i < crops.length; i++) {
			ItemStack c2 = crops[i].copy();
			c2.setCount(1);

			DCRecipe.jsonShapedRecipe("main_container", new ItemStack(MainInit.cropCont, 1, i), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					c2
			});

			DCRecipe.jsonShapelessRecipe("main_container", crops[i], new Object[] {
					new ItemStack(MainInit.cropCont, 1, i)
			});
		}

		ItemStack[] misc = new ItemStack[] {
				new ItemStack(Items.CLAY_BALL, 8, 0),
				new ItemStack(Items.FISH, 8, 0),
				new ItemStack(Items.LEATHER, 8, 0),
				new ItemStack(Items.RABBIT_HIDE, 8, 0)
		};
		for (int i = 0; i < misc.length; i++) {
			ItemStack c2 = misc[i].copy();
			c2.setCount(1);

			DCRecipe.jsonShapedRecipe("main_container", new ItemStack(MainInit.miscCont, 1, i), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					c2
			});

			DCRecipe.jsonShapelessRecipe("main_container", misc[i], new Object[] {
					new ItemStack(MainInit.miscCont, 1, i)
			});
		}

		ItemStack[] enemy = new ItemStack[] {
				new ItemStack(Items.ROTTEN_FLESH, 8, 0),
				new ItemStack(Items.BONE, 8, 0),
				new ItemStack(Items.SPIDER_EYE, 8, 0),
				new ItemStack(Items.ENDER_PEARL, 8, 0),
				new ItemStack(Items.GUNPOWDER, 8, 0),
				new ItemStack(Items.BLAZE_ROD, 8, 0)
		};
		for (int i = 0; i < enemy.length; i++) {
			ItemStack c2 = enemy[i].copy();
			c2.setCount(1);

			DCRecipe.jsonShapedRecipe("main_container", new ItemStack(MainInit.dropCont, 1, i), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					c2
			});

			DCRecipe.jsonShapelessRecipe("main_container", enemy[i], new Object[] {
					new ItemStack(MainInit.dropCont, 1, i)
			});
		}

		ItemStack[] meat = new ItemStack[] {
				new ItemStack(Items.BEEF, 8, 0),
				new ItemStack(Items.PORKCHOP, 8, 0),
				new ItemStack(Items.CHICKEN, 8, 0),
				new ItemStack(Items.MUTTON, 8, 0),
				new ItemStack(Items.EGG, 8, 0)
		};
		for (int i = 0; i < meat.length; i++) {
			ItemStack c2 = meat[i].copy();
			c2.setCount(1);

			DCRecipe.jsonShapedRecipe("main_container", new ItemStack(MainInit.cardboard, 1, i), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					c2
			});

			DCRecipe.jsonShapelessRecipe("main_container", meat[i], new Object[] {
					new ItemStack(MainInit.cardboard, 1, i)
			});
		}

		ItemStack[] dust = new ItemStack[] {
				new ItemStack(Items.SUGAR, 8, 0),
				new ItemStack(MainInit.foodMaterials, 8, 0),
				new ItemStack(MainInit.foodMaterials, 8, 1),
				new ItemStack(MainInit.foodMaterials, 8, 2),
				new ItemStack(MainInit.foodMaterials, 8, 3),
				new ItemStack(Items.WHEAT_SEEDS, 8)
		};
		for (int i = 0; i < dust.length; i++) {
			ItemStack c2 = dust[i].copy();
			c2.setCount(1);

			DCRecipe.jsonShapedRecipe("main_container", new ItemStack(MainInit.dustBags, 1, i), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					c2
			});

			DCRecipe.jsonShapelessRecipe("main_container", dust[i], new Object[] {
					new ItemStack(MainInit.dustBags, 1, i)
			});
		}

		DCRecipe.jsonShapedRecipe("main_container", new ItemStack(MainInit.cardboard, 1, 5), new Object[] {
				"XXX",
				"X X",
				"XXX",
				'X',
				new ItemStack(Blocks.WOOL, 1, 32767)
		});

		DCRecipe.jsonShapelessRecipe("main_container", new ItemStack(Blocks.WOOL, 8, 0), new Object[] {
				new ItemStack(MainInit.cardboard, 1, 5)
		});

		DCRecipe.jsonShapedRecipe("main_container", new ItemStack(MainInit.cropBasket, 1, 11), new Object[] {
				"XXX",
				"X X",
				"XXX",
				'X',
				new ItemStack(MainInit.silkworm, 1, 2)
		});

		DCRecipe.jsonShapelessRecipe("main_container", new ItemStack(MainInit.silkworm, 8, 2), new Object[] {
				new ItemStack(MainInit.cropBasket, 1, 11)
		});
	}

}
