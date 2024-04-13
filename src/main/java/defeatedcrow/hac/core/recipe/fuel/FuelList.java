package defeatedcrow.hac.core.recipe.fuel;

import defeatedcrow.hac.api.recipe.FuelTypeDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;

public class FuelList {

	public static void init() {
		addBiomassFuel(new ItemStack(Items.CHARCOAL), 1600);
		addBiomassFuel(new ItemStack(FoodInit.BIOMASS_BRIQUET.get()), 1600);
		addBiomassFuel(ItemTags.LEAVES, 100);
		addBiomassFuel(ItemTags.SAPLINGS, 100);
		addBiomassFuel(TagDC.ItemTag.FUEL_BIOMASS, 200);
	}

	private static void addBiomassFuel(ItemStack input, int time) {
		ResourceLocation res = DCUtil.getRes(input.getItem()).orElse(new ResourceLocation(ClimateCore.MOD_ID, "main/null_item"));
		String fName = res.getPath().replace('/', '_');
		FuelConfig.INSTANCE.addRecipe(fName, new DeviceFuel(FuelTypeDC.BIOMASS.toString(), time, "empty", Ingredient.of(input)));
	}

	private static void addBiomassFuel(TagKey<Item> input, int time) {
		ResourceLocation res = input.location();
		String fName = res.getPath().replace('/', '_');
		FuelConfig.INSTANCE.addRecipe(fName, new DeviceFuel(FuelTypeDC.BIOMASS.toString(), time, "empty", Ingredient.of(input)));
	}

	private static void addThermalFuel(ItemStack input, int time) {
		ResourceLocation res = DCUtil.getRes(input.getItem()).orElse(new ResourceLocation(ClimateCore.MOD_ID, "main/null_item"));
		String fName = res.getPath().replace('/', '_');
		FuelConfig.INSTANCE.addRecipe(fName, new DeviceFuel(FuelTypeDC.THERMAL.toString(), time, "empty", Ingredient.of(input)));
	}

	private static void addThermalFuel(TagKey<Item> input, int time) {
		ResourceLocation res = input.location();
		String fName = res.getPath().replace('/', '_');
		FuelConfig.INSTANCE.addRecipe(fName, new DeviceFuel(FuelTypeDC.THERMAL.toString(), time, "empty", Ingredient.of(input)));
	}

	private static void addFluidFuel(TagKey<Fluid> fluid, int time) {
		ResourceLocation res = fluid.location();
		String fName = res.getPath().replace('/', '_');
		FuelConfig.INSTANCE.addRecipe(fName, new DeviceFuel(FuelTypeDC.FLUID.toString(), time, res.toString(), Ingredient.EMPTY));
	}

	private static void addGasFuel(TagKey<Fluid> fluid, int time) {
		ResourceLocation res = fluid.location();
		String fName = res.getPath().replace('/', '_');
		FuelConfig.INSTANCE.addRecipe(fName, new DeviceFuel(FuelTypeDC.GAS.toString(), time, res.toString(), Ingredient.EMPTY));
	}

}
