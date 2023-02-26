package defeatedcrow.hac.food.recipe;

import java.util.function.Supplier;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class BrewingRecipeDC {

	public static final BrewingRecipeDC INSTANCE = new BrewingRecipeDC();

	public static void init() {
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)), Ingredient.of(TagDC.ItemTag.CROP_NIGHTSHADE),
			PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WEAKNESS));
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.WATER)), Ingredient.of(TagDC.ItemTag.CROP_NIGHTSHADE),
			PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.WEAKNESS));
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), Potions.WATER)), Ingredient.of(TagDC.ItemTag.CROP_NIGHTSHADE),
			PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), Potions.WEAKNESS));

		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)), Ingredient.of(TagDC.ItemTag.CROP_LANTERN),
			PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.FIRE_RESISTANCE));
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.AWKWARD)), Ingredient.of(TagDC.ItemTag.CROP_LANTERN),
			PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.FIRE_RESISTANCE));
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), Potions.AWKWARD)), Ingredient.of(TagDC.ItemTag.CROP_LANTERN),
			PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), Potions.FIRE_RESISTANCE));

		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)), Ingredient.of(TagDC.ItemTag.CROP_LAVENDER),
			PotionUtils.setPotion(new ItemStack(Items.POTION), CoreInit.COLD_RES_POTION.get()));
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.AWKWARD)), Ingredient.of(TagDC.ItemTag.CROP_LAVENDER),
			PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), CoreInit.COLD_RES_POTION.get()));
		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), Potions.AWKWARD)), Ingredient.of(TagDC.ItemTag.CROP_LAVENDER),
			PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), CoreInit.COLD_RES_POTION.get()));

		BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), CoreInit.COLD_RES_POTION.get())), Ingredient.of(Tags.Items.DUSTS_REDSTONE),
			PotionUtils.setPotion(new ItemStack(Items.POTION), CoreInit.COLD_RES_LONG.get()));

		// BrewingRecipeRegistry.addRecipe(INSTANCE.new Recipe(TagDC.ItemTag.CROP_LANTERN, () -> Potions.AWKWARD, false));
	}

	public class Recipe implements IBrewingRecipe {

		public final TagKey<Item> ingredient;
		public final Supplier<Potion> potion;
		public final boolean needAwkward;

		public Recipe(TagKey<Item> in, Supplier<Potion> output, boolean b) {
			ingredient = in;
			potion = output;
			needAwkward = b;
		}

		@Override
		public boolean isInput(ItemStack stack) {
			Item item = stack.getItem();
			if (item == Items.POTION) {
				Potion p = PotionUtils.getPotion(stack);
				return needAwkward ? p == Potions.AWKWARD : p == Potions.WATER;
			}
			return true;
		}

		@Override
		public boolean isIngredient(ItemStack stack) {
			return stack.is(ingredient);
		}

		@Override
		public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
			if (!input.isEmpty() && !ingredient.isEmpty() && isInput(input) && isIngredient(ingredient)) {
				ItemStack result = PotionUtils.setPotion(new ItemStack(Items.POTION), potion.get());
				if (result != input) {
					return result;
				}
				return ItemStack.EMPTY;
			}

			return ItemStack.EMPTY;
		}
	}
}
